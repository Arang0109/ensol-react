import { useEffect, useState } from "react";
import { useNavigate, useParams } from 'react-router-dom';

import { fetchCompanyDetail, updateCompany, deleteCompany } from "api/CompanyApi";

import { CustomButton } from "components/buttons";

import CompanyInfo from "features/company/CompanyInfo";
import { WorkplaceTable, WorkplaceCreateForm } from "features/workplace";

import useCompanyForm from 'hooks/useCompanyForm';
import useModifyForm from "hooks/useModifyForm";

export default function ManageCompanyDetail() {
	const [initialData, setInitialData] = useState(null);
	const [workplaces, setWorkplaces] = useState([]);
	const [showModal, setShowModal] = useState(false);

	const { company, setCompany, errors, validate } = useCompanyForm(initialData);
	const { companyId } = useParams();

	const { readOnly, buttonText, handleModify } = useModifyForm({
		data: company,
		validate: () => validate(),
		onUpdate: updateCompany,
	})

	const navigate = useNavigate();

	// 데이터 로딩
	useEffect(() => {
		fetchCompanyDetail(companyId)
			.then((res) => {
				setInitialData(res.data.company);
				setWorkplaces(res.data.workplaces);
			})
			.catch(console.error);
	}, [companyId]);

	if (!company?.companyName) return <p>로딩 중...</p>;

	const handleDeleteCompany = async () => {
		if (window.confirm("삭제하시겠습니까?")) {
			try {
				await deleteCompany(companyId);
				navigate('/companies');
			} catch(error) {
				alert('삭제에 실패했습니다.')
			}
		}
	}

	const handleShowModal = () => {
    setShowModal(true);
  }

	return(
		<div className="container-fluid">
			<div className="border rounded p-4 bg-body-tertiary">
				<h4 className="fw-bold">{company.companyName}</h4>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>{company.address} | 등록일: {company.regDate}</div>
					<div className="mt-2">
						<CustomButton
							text={buttonText}
							onClick={handleModify} />
						<CustomButton
							text={'삭제'}
							onClick={handleDeleteCompany} />
					</div>
				<hr />
				<CompanyInfo
					company={company}
					setCompany={setCompany}
					errors={errors}
					readOnly={readOnly}/>
			</div>
			<div className="border rounded mt-4 p-4 bg-body-tertiary">
				<h5 className="fw-bold">측정대상 사업장 목록</h5>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>총 {workplaces.length}개 사업장</div>
				<hr />
				<div className="m-1">
					<CustomButton 
						text={'+ 사업장 등록'} 
						onClick={handleShowModal} />
				</div>
				<WorkplaceTable workplaces={workplaces} setWorkplaces={setWorkplaces} company={company} />
			</div>
			<WorkplaceCreateForm
				showModal={showModal}
				setShowModal={setShowModal}
				setWorkplaces={setWorkplaces}
				company={company}/>
		</div>
	);
}
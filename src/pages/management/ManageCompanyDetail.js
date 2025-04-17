import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { fetchCompanyDetail, updateCompany, deleteCompany } from "api/managementApi";
import { Button } from "react-bootstrap";

import CompanyInfo from "features/company/CompanyInfo";
import ModalContainer from "components/modals/ModalContainer";
import { WorkplaceTable, WorkplaceFormBody } from "features/workplace";
import useCompanyForm from 'hooks/useCompanyForm';


export default function ManageCompanyDetail({ theme }) {
	const { companyId } = useParams();
	const [readOnly, setReadOnly] = useState(true)
	const [modifyBtn, setModifyBtn] = useState("정보 수정하기");
	const [initialData, setInitialData] = useState(null);
	const [workplaces, setWorkplaces] = useState();
	const [workplace, setWorkplace] = useState(
		{
      workplaceName: '',
      businessType: '',
      mainProduction: '',
      workplaceSize: '',
			address: '',
    });
	const [showModal, setShowModal] = useState(false);
	const { company, setCompany, errors, validate } = useCompanyForm(initialData);

	const navigate = useNavigate();

	console.log(company);

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
				const deleted = await deleteCompany(companyId);
				navigate('/companies');
			} catch(error) {
				alert('삭제에 실패했습니다.')
			}
		}
	}

	const handleModifyCompany = async (e) => {
		if (readOnly) {
			setModifyBtn("정보 저장하기");
			setReadOnly(false);
			return;
		}

		if (!validate()) return;

		try {
			const saved = await updateCompany(company);
			setCompany(company);
			alert('수정되었습니다!');
			setReadOnly(true);
    	setModifyBtn("정보 수정하기")
		} catch (error) {
			alert('수정에 실패했습니다.');
		}
	}

	const handleShowModal = () => {
			setShowModal(true);
		}
	
		const handleSubmit = async () => {
			console.log("on click");
		};

	const handleRowDoubleClick = (rowIndex) => {
    const workplace = workplaces[rowIndex];
    if (workplace?.workplaceId) {
      navigate(`/workplaces/${workplace.workplaceId}`);
    }
  };

	return(
		<div className="container-fluid">
			<div className="border rounded p-4 bg-body-tertiary">
				<h4 className="fw-bold">{company.companyName}</h4>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>{company.address} | 등록일: {company.regDate}</div>
					<div className="mt-2">
						<Button
							className="m-1"
							variant={theme === 'dark' ? 'outline-light' : 'outline-dark'}
							size="sm"
							onClick={handleModifyCompany}>{modifyBtn}
						</Button>
						<Button
							className="m-1"
							variant={theme === 'dark' ? 'outline-light' : 'outline-dark'}
							size="sm"
							onClick={handleDeleteCompany}>업체 삭제
						</Button>
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
				<hr />
				<div className="m-2">
					<Button className="m-1" variant={theme === 'dark' ? 'outline-light' : 'outline-dark'} size="sm" onClick={handleShowModal}>+ 사업장 등록</Button>
				</div>
				<WorkplaceTable 
					workplaces={workplaces}
					onDoubleClick={handleRowDoubleClick}/>
				<ModalContainer
					show={showModal}
					onClose={() => setShowModal(false)}
					onSubmit={handleSubmit}
					title="측정대상 사업장 등록"
				>
					<WorkplaceFormBody
						companyName={company.companyName}
						workplace={workplace}
						setWorkplace={setWorkplace}
						errors={errors}
					/>
				</ModalContainer>
			</div>
		</div>
	);
}
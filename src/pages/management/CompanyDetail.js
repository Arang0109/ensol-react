import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import { fetchCompanyDetail, updateCompany } from "api/managementApi";
import { Button } from "react-bootstrap";

import CompanyInfo from "features/company/CompanyInfo";
import useCompanyForm from 'hooks/useCompanyForm';


export default function CompanyDetail({ theme }) {
	const { companyId } = useParams();
	const [readOnly, setReadOnly] = useState(true)
	const [modifyBtn, setModifyBtn] = useState("정보 수정하기");
	const [initialData, setInitialData] = useState(null);
	const { company, setCompany, errors, validate } = useCompanyForm(initialData);

	// 데이터 로딩
	useEffect(() => {
		fetchCompanyDetail(companyId)
			.then((res) => setInitialData(res.data.company))
			.catch(console.error);
	}, [companyId]);

	if (!company?.companyName) return <p>로딩 중...</p>;

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

	return(
		<div className="container-fluid">
			<div className="border rounded p-4 bg-body-tertiary">
				<h4 className="fw-bold">{company.companyName}</h4>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>{company.address} | 등록일: {company.regDate}</div>
					<Button
						className="mt-3"
						variant={theme === 'dark' ? 'outline-light' : 'outline-dark'}
						size="sm"
						onClick={handleModifyCompany}>{modifyBtn}</Button>
				<hr />
				<CompanyInfo
					company={company}
					setCompany={setCompany}
					errors={errors}
					readOnly={readOnly}/>
			</div>
		</div>
	)
}
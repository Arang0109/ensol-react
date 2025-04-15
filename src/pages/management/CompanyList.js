import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

import { fetchCompanyList, createCompany } from 'api/managementApi';
import ModalContainer from 'components/modals/ModalContainer';
import { CompanyTable, CompanyFormBody } from 'features/company';
import useCompanyForm from 'hooks/useCompanyForm';

export default function CompanyList({ theme }) {
  // useState 작성
  const [companies, setCompanies] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const { company, setCompany, errors, validate } = useCompanyForm(
    {
      companyName: '',
      ceoName: '',
      bizNumber: '',
      address: '',
    }
  );

  const navigate = useNavigate();

  useEffect(() => {
    fetchCompanyList()
    .then(setCompanies)
    .catch(console.error);
  }, []);

  const handleShowModal = () => {
    setShowModal(true);
  }

  const handleSubmit = async () => {
    if (!validate()) return;

    try {
      const saved = await createCompany(company);
      alert('업체가 등록되었습니다!');
      setShowModal(false);
  
      // 등록 후 목록 다시 불러오기
      const updatedList = await fetchCompanyList();
      setCompanies(updatedList);
    } catch (error) {
      alert('등록에 실패했습니다. 입력값을 확인해주세요.');
    }
  };

  const handleRowDoubleClick = (rowIndex) => {
    const company = companies[rowIndex];
    if (company?.companyId) {
      navigate(`/companies/${company.companyId}`);
    }
  };

  return (
    <div className="container-fluid mt-4">
      <div className="border p-4" style={{
        backgroundColor: theme === 'dark' ? '#31363F' : '#EEEEEE',
      }}>
        <div className="m-2">
        <Button className="m-1" variant={theme === 'dark' ? 'outline-light' : 'outline-dark'} size="sm" onClick={handleShowModal}>+ 업체 등록</Button>
        <Button className="m-1" variant={theme === 'dark' ? 'outline-light' : 'outline-dark'} size="sm">- 업체 삭제</Button>
        </div>
        <CompanyTable data={companies} onDoubleClick={handleRowDoubleClick} />
        <ModalContainer
          show={showModal}
          onClose={() => setShowModal(false)}
          onSubmit={handleSubmit}
          title="측정대행 의뢰업체 등록"
        >
          <CompanyFormBody
            company={company}
            setCompany={setCompany}
            errors={errors}
          />
        </ModalContainer>
      </div>
    </div>
  );
}

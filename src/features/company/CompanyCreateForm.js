import { Form, Modal } from 'react-bootstrap';
import useBizNumberFormatter from 'hooks/useBizNumberFormatter';

import ModalContainer from 'components/modals/ModalContainer';

import { createCompany } from 'api/managementApi';

import useCompanyForm from 'hooks/useCompanyForm';

export default function CompanyCreateForm({ showModal, setShowModal, fetchCompanyList, setCompanies }) {
  const { company, setCompany, errors, validate } = useCompanyForm(
    {
      companyName: '',
      ceoName: '',
      bizNumber: '',
      address: '',
    }
  );

  const formatBizNumber = useBizNumberFormatter();

  const handleChange = (e) => {
    const { name, value } = e.target;
    const newValue = name === 'bizNumber' ? formatBizNumber(value) : value;

    setCompany((prev) => ({ ...prev, [name]: newValue }));
  };

  const handleSubmit = async () => {
    if (!validate()) return;

    try {
      await createCompany(company);
      alert('업체가 등록되었습니다!');
      setShowModal(false);
  
      // 등록 후 목록 다시 불러오기
      const updatedList = await fetchCompanyList();
      setCompanies(updatedList);
    } catch (error) {
      alert('등록에 실패했습니다. 입력값을 확인해주세요.');
    }
  };

  return (
    <ModalContainer
      show={showModal}
      onClose={() => setShowModal(false)}
      onSubmit={handleSubmit}
      title="측정대행 의뢰업체 등록"
    >
      <Form>
        <Form.Group className="mb-2">
          <Form.Label>측정대행 의뢰업체(필수)</Form.Label>
          <Form.Control
            name="companyName"
            value={company.companyName}
            onChange={handleChange}
            isInvalid={!!errors.companyName}
          />
          <Form.Control.Feedback type="invalid">
            {errors.companyName}
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>대표자</Form.Label>
          <Form.Control
            name="ceoName"
            value={company.ceoName}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>사업지 주소</Form.Label>
          <Form.Control
            name="address"
            value={company.address}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>사업자번호</Form.Label>
          <Form.Control
            name="bizNumber"
            value={company.bizNumber}
            onChange={handleChange}
            placeholder="000-00-00000"
            isInvalid={!!errors.bizNumber}
          />
          <Form.Control.Feedback type="invalid">
            {errors.bizNumber}
          </Form.Control.Feedback>
        </Form.Group>
      </Form>
    </ModalContainer>
  );
}

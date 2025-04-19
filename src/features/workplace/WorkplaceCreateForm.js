import { Form } from 'react-bootstrap';

import ModalContainer from 'components/modals/ModalContainer';

import { fetchCompanyDetail } from 'api/CompanyApi';
import { createWorkplace } from 'api/WorkplaceApi';

import useWorkplaceForm from 'hooks/useWorkplaceForm';

export default function WorkplaceCreateForm({ showModal, setShowModal, setWorkplaces, company }) {
  const { workplace, setWorkplace, errors, validate } = useWorkplaceForm(
    {
      companyId: company.companyId,
      workplaceName: '',
      businessType: '',
      mainProduction: '',
      workplaceSize: '',
      address: '',
    }
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    const newValue = value;

    setWorkplace((prev) => ({ ...prev, [name]: newValue }));
  };

  const handleSubmit = async () => {
    if (!validate()) return;

    try {
      await createWorkplace(workplace);
      alert('사업장이 등록되었습니다!');
      setShowModal(false);
  
      // 등록 후 목록 다시 불러오기
      fetchCompanyDetail(company.companyId)
        .then((res) => {
				setWorkplaces(res.data.workplaces);
			})
			.catch(console.error);
    } catch (error) {
      alert('등록에 실패했습니다. 입력값을 확인해주세요.');
    }
  };

  return (
    <ModalContainer
      show={showModal}
      onClose={() => setShowModal(false)}
      onSubmit={handleSubmit}
      title="측정대상 사업장 등록"
    >
      <Form>
        <Form.Group className="mb-2">
          <Form.Label>측정대행 의뢰업체</Form.Label>
          <Form.Control
            name="companyName"
            value={company.companyName}
            onChange={handleChange}
            readOnly
            disabled
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>측정대상 사업장(필수)</Form.Label>
          <Form.Control
            name="workplaceName"
            value={workplace.workplaceName}
            onChange={handleChange}
            isInvalid={!!errors.workplaceName}
          />
          <Form.Control.Feedback type="invalid">
            {errors.workplaceName}
          </Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>주소</Form.Label>
          <Form.Control
            name="address"
            value={workplace.address}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>업종</Form.Label>
          <Form.Control
            name="businessType"
            value={workplace.businessType}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>주생산 품목</Form.Label>
          <Form.Control
            name="mainProduction"
            value={workplace.mainProduction}
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-2">
          <Form.Label>사업장 종별</Form.Label>
          <Form.Select
            name="workplaceSize"
            value={workplace.workplaceSize}
            onChange={handleChange}
            aria-label="사업장 종별">
            <option value="">선택하세요</option>
            <option value="1">1종</option>
            <option value="2">2종</option>
            <option value="3">3종</option>
            <option value="4">4종</option>
            <option value="5">5종</option>
          </Form.Select>
        </Form.Group>
      </Form>
    </ModalContainer>
  );
}

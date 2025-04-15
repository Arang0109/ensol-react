import { Form } from 'react-bootstrap';
import useBizNumberFormatter from 'hooks/useBizNumberFormatter';

export default function CompanyFormBody({ company, setCompany, errors }) {
  const formatBizNumber = useBizNumberFormatter();

  const handleChange = (e) => {
    const { name, value } = e.target;
    const newValue = name === 'bizNumber' ? formatBizNumber(value) : value;

    setCompany((prev) => ({ ...prev, [name]: newValue }));
  };

  return (
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
  );
}

import { Form } from 'react-bootstrap';

export default function CompanyFormBody({ companyName, workplace, setWorkplace, errors }) {

  const handleChange = (e) => {
    const { name, value } = e.target;
    const newValue = value;

    setWorkplace((prev) => ({ ...prev, [name]: newValue }));
  };

  return (
    <Form>
      <Form.Group className="mb-2">
        <Form.Label>측정대행 의뢰업체</Form.Label>
        <Form.Control
          name="companyName"
          value={companyName}
          disabled
          readOnly
        />
        <Form.Control.Feedback type="invalid">
          {errors.companyName}
        </Form.Control.Feedback>
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
          {errors.companyName}
        </Form.Control.Feedback>
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
        <Form.Select aria-label="Default select example">
          <option value="1">1종</option>
          <option value="2">2종</option>
          <option value="3">3종</option>
          <option value="3">4종</option>
          <option value="3">5종</option>
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-2">
        <Form.Label>사업지 주소</Form.Label>
        <Form.Control
          name="address"
          value={workplace.address}
          onChange={handleChange}
        />
      </Form.Group>
    </Form>
  );
}

// src/components/modals/CompanyRegisterModal.js
import { Modal, Button, Form } from 'react-bootstrap';

export default function CompanyRegisterModal({ show, onClose, onSubmit, formData, onChange, errors }) {
  return (
    <Modal show={show} onHide={onClose} backdrop="static">
      <Modal.Header closeButton>
        <Modal.Title>업체 등록</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-2">
            <Form.Label>업체명</Form.Label>
            <Form.Control
              name="companyName"
              value={formData.companyName}
              onChange={onChange}
              isInvalid={!!errors.companyName}
            />
            <Form.Control.Feedback type="invalid">{errors.companyName}</Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-2">
            <Form.Label>대표자</Form.Label>
            <Form.Control
              name="ceoName"
              value={formData.ceoName}
              onChange={onChange}
            />
          </Form.Group>

          <Form.Group className="mb-2">
            <Form.Label>사업자번호</Form.Label>
            <Form.Control
              name="bizNumber"
              value={formData.bizNumber}
              onChange={onChange}
              isInvalid={!!errors.bizNumber}
              placeholder="000-00-00000"
            />
            <Form.Control.Feedback type="invalid">{errors.bizNumber}</Form.Control.Feedback>
          </Form.Group>

          <Form.Group className="mb-2">
            <Form.Label>주소</Form.Label>
            <Form.Control
              name="address"
              value={formData.address}
              onChange={onChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>취소</Button>
        <Button variant="primary" onClick={onSubmit}>등록</Button>
      </Modal.Footer>
    </Modal>
  );
}

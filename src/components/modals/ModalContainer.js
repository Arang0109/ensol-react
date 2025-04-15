import { Modal, Button } from 'react-bootstrap';

export default function ModalContainer({
  show,
  onClose,
  onSubmit,
  title,
  children
}) {
  return (
    <Modal show={show} onHide={onClose} backdrop="static" keyboard={false}>
      <Modal.Header closeButton>
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>{children}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>취소</Button>
        <Button variant="primary" onClick={onSubmit}>등록</Button>
      </Modal.Footer>
    </Modal>
  );
}
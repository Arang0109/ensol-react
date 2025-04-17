import { Modal, Button } from 'react-bootstrap';
import { CustomButton } from 'components/buttons';

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
        <CustomButton text="취소" onClick={onClose} />
        <CustomButton text="등록" onClick={onSubmit} />
      </Modal.Footer>
    </Modal>
  );
}
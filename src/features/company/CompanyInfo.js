import { Form, Row, Col } from 'react-bootstrap';
import useBizNumberFormatter from 'hooks/useBizNumberFormatter';

export default function CompanyInfo({ company, setCompany, errors, readOnly }) {
	const formatBizNumber = useBizNumberFormatter();

	const handleChange = (e) => {
		const { name, value } = e.target;
		setCompany((prev) => ({ ...prev, [name]: value }));
	};

	return(
		<Form>
			<Row className="mb-3">
				<Col md={6}>
					<Form.Group controlId="companyName">
						<Form.Label>측정대행 의뢰업체</Form.Label>
						<Form.Control
							type="text"
							value={company?.companyName || ''}
							name="companyName"
							isInvalid={!!errors.companyName}
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>

				<Col md={6}>
					<Form.Group controlId="address">
						<Form.Label>사업지 주소</Form.Label>
						<Form.Control
							type="text"
							value={company?.address || ''}
							name="address"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
			</Row>

			<Row className="mb-3">
				<Col md={6}>
					<Form.Group controlId="ceoName">
						<Form.Label>대표자</Form.Label>
						<Form.Control
							type="text"
							value={company?.ceoName || ''}
							name="ceoName"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
				<Col md={6}>
					<Form.Group controlId="bizNumber">
						<Form.Label>사업자번호</Form.Label>
						<Form.Control
							type="text"
							value={company?.bizNumber || ''}
							name="bizNumber"
							isInvalid={!!errors.bizNumber}
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
			</Row>
		</Form>
	)
}
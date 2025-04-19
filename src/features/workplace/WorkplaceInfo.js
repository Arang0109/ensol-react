import { Form, Row, Col } from 'react-bootstrap';

export default function WorkplaceInfo({ workplace, setWorkplace, errors, readOnly }) {

	const handleChange = (e) => {
		const { name, value } = e.target;
		setWorkplace((prev) => ({ ...prev, [name]: value }));
	};

	return(
		<Form>
			<Row className="mb-3">
				<Col md={6}>
					<Form.Group controlId="workplaceName">
						<Form.Label>측정대상 사업장</Form.Label>
						<Form.Control
							type="text"
							value={workplace?.workplaceName || ''}
							name="workplaceName"
							isInvalid={!!errors.workplaceName}
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>

				<Col md={6}>
					<Form.Group controlId="address">
						<Form.Label>사업지 주소</Form.Label>
						<Form.Control
							type="text"
							value={workplace?.address || ''}
							name="address"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
			</Row>

			<Row className="mb-3">
				<Col md={4}>
					<Form.Group controlId="businessType">
						<Form.Label>업종</Form.Label>
						<Form.Control
							type="text"
							value={workplace?.businessType || ''}
							name="businessType"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
				<Col md={4}>
					<Form.Group controlId="mainProduction">
						<Form.Label>주생산 품목</Form.Label>
						<Form.Control
							type="text"
							value={workplace?.mainProduction || ''}
							name="mainProduction"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
				<Col md={4}>
					<Form.Group controlId="workplaceSize">
						<Form.Label>사업장 종별</Form.Label>
						<Form.Control
							type="text"
							value={workplace?.workplaceSize + ' 종' || ''}
							name="workplaceSize"
							readOnly = {readOnly}
							onChange={handleChange}/>
					</Form.Group>
				</Col>
			</Row>
		</Form>
	)
}
import React from 'react';
import { Container, Navbar, Nav, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default function Headers() {
	return(
		<Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">ENSolution</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar-nav" />
        <Navbar.Collapse id="navbar-nav">
          <Nav className="me-auto">
						<NavDropdown title="일정" id="nav-dropdown-schedule">
              <NavDropdown.Item href="#action/3.1">일정 등록</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">자가측정부 일정</NavDropdown.Item>
            </NavDropdown>
						<NavDropdown title="관리" id="nav-dropdown-management">
              <NavDropdown.Item as={Link} to="/companies">측정대행 의뢰업체</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/workplaces">측정대상 사업장</NavDropdown.Item>
							<NavDropdown.Item href="#action/3.5">측정 시설</NavDropdown.Item>
							<NavDropdown.Divider />
							<NavDropdown.Item href="#action/3.6">측정 항목</NavDropdown.Item>
            </NavDropdown>
						<NavDropdown title="문서" id="nav-dropdown-document">
              <NavDropdown.Item href="#action/3.3">인수인계서</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.4">차량운행일지</NavDropdown.Item>
							<NavDropdown.Divider />
							<NavDropdown.Item href="#action/3.6">측정가용점수</NavDropdown.Item>
            </NavDropdown>
          </Nav>
					<Nav>
						<Nav.Link href="#login">로그인</Nav.Link>
					</Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
	);
}
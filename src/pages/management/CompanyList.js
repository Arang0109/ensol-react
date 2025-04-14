import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

import { fetchCompanyList } from '../../api/managementApi';
import CompanyRegisterModal from '../../components/modals/CompanyRegisterModal';
import CompanyTable from '../../components/tables/CompanyTable';

import axios from 'axios';

export default function CompanyList() {
  const [companies, setCompanies] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({
    companyName: '', ceoName: '', bizNumber: '', address: '', regDate: '',
  });
  const navigate = useNavigate();

  const [errors, setErrors] = useState({});

  const formatBizNumber = (value) => {
    const onlyNums = value.replace(/\D/g, ''); // 숫자만 추출
  
    if (onlyNums.length < 4) return onlyNums;
    if (onlyNums.length < 6) return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3)}`;
    if (onlyNums.length <= 10)
      return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3, 5)}-${onlyNums.slice(5, 10)}`;
    
    return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3, 5)}-${onlyNums.slice(5, 10)}`;
  };

  const validate = () => {
    const newErrors = {};
  
    if (!formData.companyName.trim()) {
      newErrors.companyName = '업체명을 입력해주세요.';
    }
  
    const bizRegex = /^\d{3}-\d{2}-\d{5}$/;
    if (!bizRegex.test(formData.bizNumber)) {
      newErrors.bizNumber = '사업자번호는 000-00-00000 형식이어야 합니다.';
    }
  
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // 에러가 없으면 true
  };

  useEffect(() => {
    fetchCompanyList().then(setCompanies).catch(console.error);
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
  
    let formattedValue = value;
    if (name === 'bizNumber') {
      formattedValue = formatBizNumber(value);
    }
  
    setFormData(prev => ({ ...prev, [name]: formattedValue }));
  };

  const handleSubmit = async () => {
    if (!validate()) return;

    try {
      const res = await axios.post('http://localhost:8080/api/management/companies', formData);
      alert('✅ 등록 성공!');
      setCompanies(prev => [...prev, res.data.data]);
      setFormData({ companyName: '', ceoName: '', bizNumber: '', address: '', regDate: '' });
      setShowModal(false);
    } catch (err) {
      alert('❌ 등록 실패');
      console.error(err);
    }
  };

  const handleRowDoubleClick = (rowIndex) => {
    const company = companies[rowIndex];
    if (company?.companyId) {
      navigate(`/company/${company.companyId}`);
    }
  };

  return (
    <div className="container-fluid mt-4">
      <Button variant="dark" onClick={() => setShowModal(true)}>+ 업체 등록</Button>
      <CompanyTable data={companies} onDoubleClick={handleRowDoubleClick} />
      <CompanyRegisterModal
        show={showModal}
        onClose={() => setShowModal(false)}
        onSubmit={handleSubmit}
        formData={formData}
        onChange={handleChange}
        errors={errors}
      />
    </div>
  );
}

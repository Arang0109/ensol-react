// hooks/useCompanyForm.js
import { useEffect, useState } from 'react';

export default function useCompanyForm(initialData) {
  const [company, setCompany] = useState(initialData || {});
  const [errors, setErrors] = useState({});
  const [initialized, setInitialized] = useState(false);

  useEffect(() => {
    if (initialData && !initialized) {
      setCompany(initialData);
      setInitialized(true);
    }
  }, [initialData, initialized]);

  const validate = () => {
    const newErrors = {};
    if (!company.companyName.trim()) {
      newErrors.companyName = '업체명을 입력해주세요.';
    }
    if (!/^\d{3}-\d{2}-\d{5}$/.test(company.bizNumber)) {
      newErrors.bizNumber = '사업자번호는 000-00-00000 형식이어야 합니다.';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  return { company, setCompany, errors, validate };
}

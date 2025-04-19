import { useEffect, useState } from 'react';

export default function useWorkplaceForm(initialData) {
  const [workplace, setWorkplace] = useState(initialData || {});
  const [errors, setErrors] = useState({});
  const [initialized, setInitialized] = useState(false);

  useEffect(() => {
    if (initialData && !initialized) {
      setWorkplace(initialData);
      setInitialized(true);
    }
  }, [initialData, initialized]);

  const validate = () => {
    const newErrors = {};
    if (!workplace.workplaceName.trim()) {
      newErrors.workplaceName = '사업장을 입력해주세요.';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  return { workplace, setWorkplace, errors, validate };
}

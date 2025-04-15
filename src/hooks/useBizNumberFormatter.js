// src/hooks/useBizNumberFormatter.js
import { useCallback } from 'react';

export default function useBizNumberFormatter() {
  const format = useCallback((value) => {
    const onlyNums = value.replace(/\D/g, '');

    if (onlyNums.length < 4) return onlyNums;
    if (onlyNums.length < 6) return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3)}`;
    if (onlyNums.length <= 10)
      return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3, 5)}-${onlyNums.slice(5, 10)}`;
    
    return `${onlyNums.slice(0, 3)}-${onlyNums.slice(3, 5)}-${onlyNums.slice(5, 10)}`;
  }, []);

  return format;
}

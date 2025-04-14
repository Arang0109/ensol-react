import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/management';

export const fetchCompanyList = async () => {
  const response = await axios.get(`${API_BASE}/companies`);
  return response.data;
};
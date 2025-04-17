import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/management';

export const fetchCompanyList = async () => {
  const response = await axios.get(`${API_BASE}/companies`);
  return response.data;
};

export const fetchWorkplaceList = async () => {
  const response = await axios.get(`${API_BASE}/workplaces`);
  return response.data;
}

export const fetchCompanyDetail = async (companyId) => {
  const response = await axios.get(`${API_BASE}/companies/${companyId}`);
  return response.data;
}

export const createCompany = async (companyData) => {
  const response = await axios.post(`${API_BASE}/companies`, companyData);
  return response.data;
};

export const updateCompany = async (companyData) => {
  const response = await axios.patch(`${API_BASE}/companies/${companyData.companyId}`, companyData);
}

export const deleteCompany = async (companyId) => {
  const response = await axios.delete(`${API_BASE}/companies/${companyId}`)
  return response.data;
}
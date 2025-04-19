import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/management/stacks';

export const fetchStackList = async () => {
  const response = await axios.get(`${API_BASE}`);
  return response.data;
}
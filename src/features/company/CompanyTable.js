import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import DataTable from 'react-data-table-component';

import { fetchCompanyList  } from 'api/managementApi';

import { CustomButton } from 'components/buttons';

import { CompanyCreateForm } from 'features/company';

const columns = [
	{
		name: '측정대행 의뢰업체',
		cell: row => (
      <Link to={`/companies/${row.companyId}`} style={{ textDecoration: 'none' }}>
        {row.companyName}
      </Link>
    ),
    sortable: true
	},
	{ name: '대표자', selector: row => row.ceoName },
  { name: '사업지 주소', selector: row => row.address },
  { name: '사업자번호', selector: row => row.bizNumber },
];

export default function CompanyTable() {
  const [showModal, setShowModal] = useState(false);
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    fetchCompanyList()
    .then(setCompanies)
    .catch(console.error);
  }, []);

  const handleShowModal = () => {
    setShowModal(true);
  }

  return (
    <div>
      <div className="m-2">
        <CustomButton text={'+ 업체 등록'} onClick={handleShowModal} />
      </div>
      <DataTable
        columns={columns}
        data={companies}
      />
      <CompanyCreateForm
        showModal={showModal}
        setShowModal={setShowModal}
        fetchCompanyList={fetchCompanyList}
        setCompanies={setCompanies} />
    </div>
    
  );
}

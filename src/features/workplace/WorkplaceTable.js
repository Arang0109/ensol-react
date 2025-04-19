import { Link } from 'react-router-dom';
import DataTable from 'react-data-table-component';

const columns = [
  {
    name: '측정대상 사업장',
    selector: row => row.workplaceName,
    cell: row => (
      <Link to={`/workplaces/${row.workplaceId}`} style={{ textDecoration: 'none' }}>
        {row.workplaceName}
      </Link>
    ),
    sortable: true
  },
  { name: '주소', selector: row => row.address },
  { name: '업종', selector: row => row.businessType },
  { name: '주생산 품목', selector: row => row.mainProduction },
  { name: '사업장 종별', selector: row => row.workplaceSize + " 종" },
  { name: '등록일', selector: row => row.regDate },
];

export default function WorkplaceTable({ workplaces }) {
	return (
		<div>
      <DataTable
        columns={columns}
        data={workplaces}
      />
    </div>
	);
}
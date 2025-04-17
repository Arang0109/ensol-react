import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { fetchWorkplaceList } from 'api/managementApi';
import { WorkplaceTable } from 'features/workplace';

export default function ManageWorkplace({ theme }) {
	// useState 작성
	const [workplaces, setWorkplaces] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
		fetchWorkplaceList()
		.then(setWorkplaces)
		.catch(console.error);
	}, []);

	const handleRowDoubleClick = (rowIndex) => {
    const workplace = workplaces[rowIndex];
    if (workplace?.workplaceId) {
      navigate(`/workplaces/${workplace.workplaceId}`);
    }
  };

	return (
		<div className="container-fluid mt-4">
			<div className="border p-4" style={{
				backgroundColor: theme === 'dark' ? '#31363F' : '#EEEEEE',
			}}>
				<h4 className="fw-bold">측정대상 사업장 목록</h4><span>총 {workplaces.length}개 사업장</span>
        <hr/>
				<WorkplaceTable workplaces={workplaces} onDoubleClick={handleRowDoubleClick}/>
			</div>
		</div>
	);
}

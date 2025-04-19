import { useEffect, useState } from 'react';

import { fetchWorkplaceList } from 'api/WorkplaceApi';

import { WorkplaceTable } from 'features/workplace';

export default function ManageWorkplace() {
	// useState 작성
	const [workplaces, setWorkplaces] = useState([]);

	useEffect(() => {
		fetchWorkplaceList()
		.then(setWorkplaces)
		.catch(console.error);
	}, []);

	return (
		<div className="container-fluid mt-4">
			<div className="border p-4" style={{
        backgroundColor: 'var(--bg-color)',
        color: 'var(--text-color)'
				}}>
				<h4 className="fw-bold">측정대상 사업장 목록</h4><span>총 {workplaces.length}개 사업장</span>
        <hr/>
				<WorkplaceTable workplaces={workplaces}/>
			</div>
		</div>
	);
}

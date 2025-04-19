import { useEffect, useState } from 'react';

import { fetchStackList } from 'api/StackApi';

import { StackTable } from 'features/stack';

export default function ManageStack() {
	// useState 작성
	const [stacks, setStacks] = useState([]);

	useEffect(() => {
		fetchStackList()
		.then(setStacks)
		.catch(console.error);
	}, [stacks]);

	return (
		<div className="container-fluid mt-4">
			<div className="border p-4" style={{
        backgroundColor: 'var(--bg-color)',
        color: 'var(--text-color)'
				}}>
				<h4 className="fw-bold">배출 시설 목록</h4><span>총 {stacks.length}개 시설</span>
        <hr/>
				<StackTable stacks={stacks}/>
			</div>
		</div>
	);
}

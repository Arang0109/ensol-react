import { useEffect, useState } from "react";
import { useNavigate, useParams } from 'react-router-dom';

import { fetchWorkplaceDetail, updateWorkplace, deleteWorkplace } from "api/WorkplaceApi";

import { CustomButton } from "components/buttons";

import WorkplaceInfo from "features/workplace/WorkplaceInfo";
import { StackTable } from "features/stack";
import useWorkplaceForm from "hooks/useWorkplaceForm";

export default function ManageWorkplaceDetail() {
	const [readOnly, setReadOnly] = useState(true)
	const [modifyBtn, setModifyBtn] = useState("수정");
	const [initialData, setInitialData] = useState(null);
	const [stacks, setStacks] = useState([]);

	const { workplace, setWorkplace, errors, validate } = useWorkplaceForm(initialData);
	const { workplaceId } = useParams();

	const navigate = useNavigate();

	// 데이터 로딩
	useEffect(() => {
		fetchWorkplaceDetail(workplaceId)
			.then((res) => {
				console.log(res);
				setInitialData(res.data.workplace);
				setStacks(res.data.stacks);
			})
			.catch(console.error);
	}, [workplaceId]);

	if (!workplace?.workplaceName) return <p>로딩 중...</p>;

	const handleDeleteWorkplace = async () => {
		if (window.confirm("삭제하시겠습니까?")) {
			try {
				await deleteWorkplace(workplaceId);
				navigate('/workplaces');
			} catch(error) {
				alert('삭제에 실패했습니다.')
			}
		}
	}

	const handleModifyWorkplace = async (e) => {
		if (readOnly) {
			setModifyBtn("저장");
			setReadOnly(false);
			return;
		}

		if (!validate()) return;

		try {
			await updateWorkplace(workplace);
			setWorkplace(workplace);
			alert('수정되었습니다!');
			setReadOnly(true);
    	setModifyBtn("수정")
		} catch (error) {
			alert('수정에 실패했습니다.');
		}
	}

	return(
		<div className="container-fluid">
			<div className="border rounded p-4 bg-body-tertiary">
				<h4 className="fw-bold">{workplace.workplaceName}</h4>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>{workplace.address} | 등록일: {workplace.regDate}</div>
					<div className="mt-2">
						<CustomButton
							text={modifyBtn}
							onClick={handleModifyWorkplace} />
						<CustomButton
							text={'삭제'}
							onClick={handleDeleteWorkplace} />
					</div>
					
				<hr />
				<WorkplaceInfo
					workplace={workplace}
					setWorkplace={setWorkplace}
					errors={errors}
					readOnly={readOnly}/>
			</div>
			<div className="border rounded mt-4 p-4 bg-body-tertiary">
				<h5 className="fw-bold">배출구 시설 목록</h5>
				<div className="text-muted" style={{
						fontSize:"0.75rem"
					}}>총 {stacks.length}개 시설</div>
				<hr />
				<StackTable stacks={stacks} />
			</div>
		</div>
	);
}
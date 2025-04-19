import { useParams } from "react-router-dom";

export default function ManageStackDetail() {
	const { stackId } = useParams();

	return(
		<div className="container-fluid">
					<div className="border rounded p-4 bg-body-tertiary">
						<h4 className="fw-bold">{stackId}</h4>
						<div className="text-muted" style={{
								fontSize:"0.75rem"
							}}> | 등록일: </div>
					</div>
				</div>
	);
}
import { HotTable	} from "@handsontable/react-wrapper";
import { useRef } from "react";

export default function WorkplaceTable({ workplaces, onDoubleClick }) {
	const lastClickRef = useRef({ row: null, time: 0 });

	return (
		<HotTable
      data={workplaces}
      stretchH="all"
      readOnly={true}
      columns={[
        { data: 'workplaceId' },
				{ data: 'compnayId' },
        { data: 'isSelect', type: 'checkbox'},
        { data: 'workplaceName', type: 'text' },
        { data: 'mainProduction' },
        { data: 'businessType' },
				{ data: 'workplaceSize' },
				{ data: 'address' },
        { data: 'regDate' }
      ]}
      colHeaders={['사업장ID', '의뢰업체ID', 'selceted', '측정대상 사업장', '주생산 품목', '업종', '사업장 종별', '주소', '등록일']}
      hiddenColumns={{ columns: [0,1], indicators: false }}
      height="auto"
      autoWrapRow={true}
      autoWrapCol={true}
      licenseKey="non-commercial-and-evaluation"
			afterOnCellMouseDown={(e, coords) => {
        const now = Date.now();
        const last = lastClickRef.current;

        if (last.row === coords.row && now - last.time < 150) {
          onDoubleClick(coords.row);
        }

        lastClickRef.current = { row: coords.row, time: now };
      }}
    />
	);
}
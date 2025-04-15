// src/components/tables/CompanyTable.js
import { HotTable } from '@handsontable/react-wrapper';
import { useRef } from 'react';

export default function CompanyTable({ data, onDoubleClick }) {
  const lastClickRef = useRef({ row: null, time: 0 });

  return (
    <HotTable
      data={data}
      stretchH="all"
      readOnly={true}
      columns={[
        { data: 'companyId' },
        { data: 'companyName' },
        { data: 'ceoName' },
        { data: 'bizNumber' },
        { data: 'address' },
        { data: 'regDate' }
      ]}
      colHeaders={['ID', '업체명', '대표자', '사업자번호', '주소', '등록일']}
      hiddenColumns={{ columns: [0], indicators: false }}
      height="auto"
      autoWrapRow={true}
      autoWrapCol={true}
      licenseKey="non-commercial-and-evaluation"
      afterOnCellMouseDown={(e, coords) => {
        const now = Date.now();
        const last = lastClickRef.current;

        if (last.row === coords.row && now - last.time < 250) {
          onDoubleClick(coords.row);
        }

        lastClickRef.current = { row: coords.row, time: now };
      }}
    />
  );
}

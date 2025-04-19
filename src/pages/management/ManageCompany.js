import { CompanyTable } from "features/company";

export default function ManageCompany() {
  return (
    <div className="container-fluid mt-4">
      <div className="border p-4" style={{
        backgroundColor: 'var(--bg-color)',
        color: 'var(--text-color)'
      }}>
        <h4 className="fw-bold">측정대행 의뢰업체 목록</h4>
        <hr/>
        <CompanyTable />
      </div>
    </div>
  );
}
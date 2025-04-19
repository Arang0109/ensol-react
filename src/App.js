import 'App.css';
import { Routes, Route } from 'react-router-dom'

import MainPage from 'pages/home/MainPage';
import { 
  ManageCompany, ManageCompanyDetail,
  ManageWorkplace, ManageWorkplaceDetail,
  ManageStack, ManageStackDetail,
  ManagePollutant } from 'pages/management';

import { Header }  from 'components/sementics';

import { ThemeProvider } from 'context/ThemeContext';

function App() {
  return (
    <>
      <ThemeProvider>
        <Header />
        <main className="container mt-4">
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/companies" element={<ManageCompany />} />
            <Route path="/companies/:companyId" element={<ManageCompanyDetail />} />
            <Route path="/workplaces" element={<ManageWorkplace />} />
            <Route path="/workplaces/:workplaceId" element={<ManageWorkplaceDetail />} />
            <Route path="/stacks" element={<ManageStack />} />
            <Route path="/stacks/:stackId" element={<ManageStackDetail />} />
            <Route path="/pollutants" element={<ManagePollutant />} />
          </Routes>
        </main>
      </ThemeProvider>
    </>
  );
}

export default App;

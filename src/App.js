import 'App.css';
import { Routes, Route } from 'react-router-dom'

import MainPage from 'pages/home/MainPage';
import { ManageCompany, ManageCompanyDetail, ManageWorkplace } from 'pages/management';

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
          </Routes>
        </main>
      </ThemeProvider>
    </>
  );
}

export default App;

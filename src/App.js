import 'App.css';
import { Routes, Route } from 'react-router-dom'
import Header from 'components/sementics/Header';
import MainPage from 'pages/home/MainPage';
import { CompanyList, CompanyDetail, WorkplaceList } from 'pages/management';
import { useState, useEffect } from 'react';

function App() {
  const [theme, setTheme] = useState('dark');

  useEffect(() => {
    document.documentElement.setAttribute('data-bs-theme', theme);
  }, [theme]);

  const toggleTheme = () => {
    setTheme(prev => (prev === 'dark' ? 'light' : 'dark'));
  };
  
  return (
    <>
      <Header theme={theme} toggleTheme={toggleTheme} />
      <main className="container mt-4">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/companies" element={<CompanyList theme={theme} />} />
          <Route path="/companies/:companyId" element={<CompanyDetail theme={theme} />}></Route>
          <Route path="/workplaces" element={<WorkplaceList />} />
        </Routes>
      </main>
    </>
  );
}

export default App;

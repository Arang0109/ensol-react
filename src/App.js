import './App.css';
import { Routes, Route } from 'react-router-dom'
import Header from './components/fragments/Header';
import MainPage from './pages/home/MainPage';
import { CompanyList, WorkplaceList } from './pages/management';
import { useEffect } from 'react';

function App() {
  useEffect(() => {
    document.documentElement.setAttribute('data-bs-theme', 'dark');
  }, []);
  
  return (
    <>
      <Header />
      <main className="container mt-4">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/companies" element={<CompanyList />} />
          <Route path="/workplaces" element={<WorkplaceList />} />
        </Routes>
      </main>
    </>
  );
}

export default App;

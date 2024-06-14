import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Layout } from 'antd';
import Navbar from './components/navbar';
import SideBar from './components/sidebar';
import Client from './pages/ClientPage';
const { Content } = Layout;
const App: React.FC = () => {
  return (
    <Router>
      <Layout style={{ minHeight: '100vh' }}>
        <Navbar />
        <Layout>
          <SideBar />
          <Layout style={{ marginLeft: 256 }}>
            <Content style={{ padding: '24px' }}>
              <Routes>
                <Route path="/khach-hang" element={<Client />} />
              </Routes>
            </Content>
          </Layout>
        </Layout>
      </Layout>
    </Router>
  );
};

export default App;

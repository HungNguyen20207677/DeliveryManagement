import React from 'react';
import { Layout, Menu, Avatar, Dropdown } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import './navbar.css';
import logo from '../assets/logo-sapo-white.svg';
const { Header } = Layout;

const Navbar: React.FC = () => {
  const menu = (
    <Menu>
      <Menu.Item key="0">
        <a href="https://www.example.com/profile">Profile</a>
      </Menu.Item>
      <Menu.Item key="1">
        <a href="https://www.example.com/logout">Logout</a>
      </Menu.Item>
    </Menu>
  );

  return (
    <Layout>
      <Header className="header">
        <div className="logo">
          <img src={logo} alt="Logo" className="logo-img" />
          <span className="logo-text">SapoDelivery</span>
        </div>
        <Dropdown overlay={menu} trigger={['click']}>
          <div className="user-menu">
            <Avatar icon={<UserOutlined />} />
            <span className="username">Nguyễn Quốc Hùng</span>
          </div>
        </Dropdown>
      </Header>
    </Layout>
  );
};

export default Navbar;

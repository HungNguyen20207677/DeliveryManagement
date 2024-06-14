import React from 'react';
import {
  UserOutlined,
  ShoppingCartOutlined,
  TeamOutlined,
  BarChartOutlined,
  LogoutOutlined,
} from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Layout, Menu, Button } from 'antd';
import { Link } from 'react-router-dom';

const { Sider } = Layout;

type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
  {
    key: '1',
    icon: <UserOutlined />,
    label: <Link to="/phan-quyen">Phân quyền</Link>,
  },
  {
    key: '2',
    icon: <ShoppingCartOutlined />,
    label: <Link to="/don-hang">Đơn hàng</Link>,
  },
  {
    key: '3',
    icon: <UserOutlined />,
    label: <Link to="/khach-hang">Khách hàng</Link>,
  },
  {
    key: '4',
    icon: <TeamOutlined />,
    label: <Link to="/nhan-vien-giao-hang">Nhân viên giao hàng</Link>,
  },
  {
    key: '5',
    icon: <BarChartOutlined />,
    label: <Link to="/thong-ke">Thống kê</Link>,
  },
];

const SideBar: React.FC = () => {
  return (
    <Sider
      width={256}
      style={{
        backgroundColor: '#14122b',
        height: '100vh',
        position: 'fixed',
        left: 0,
        top: 64,
      }}>
      <div className="logo" />
      <Menu
        defaultSelectedKeys={['3']}
        mode="inline"
        theme="dark"
        items={items}
      />
      <Button
        type="primary"
        icon={<LogoutOutlined />}
        style={{
          backgroundColor: 'red',
          borderColor: 'red',
          width: '100%',
          position: 'absolute',
          bottom: 0,
          left: 0,
        }}>
        Đăng xuất
      </Button>
    </Sider>
  );
};

export default SideBar;

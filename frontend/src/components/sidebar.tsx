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

const { Sider } = Layout;

type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
  { key: '1', icon: <UserOutlined />, label: 'Phân quyền' },
  { key: '2', icon: <ShoppingCartOutlined />, label: 'Đơn hàng' },
  { key: '3', icon: <UserOutlined />, label: 'Khách hàng' },
  { key: '4', icon: <TeamOutlined />, label: 'Nhân viên giao hàng' },
  { key: '5', icon: <BarChartOutlined />, label: 'Thống kê' },
];

const SideBar: React.FC = () => {
  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Sider width={256} style={{ backgroundColor: '#14122b' }}>
        <Menu
          defaultSelectedKeys={['1']}
          defaultOpenKeys={['sub1']}
          mode="inline"
          theme="dark"
          items={items}
          style={{ height: 'calc(100% - 64px)' }}
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
    </Layout>
  );
};

export default SideBar;

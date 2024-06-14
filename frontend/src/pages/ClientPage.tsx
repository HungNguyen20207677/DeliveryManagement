import React, { useState } from 'react';
import { Table, Button, Modal, Form, Input, message, Popconfirm } from 'antd';
import { EditOutlined, DeleteOutlined } from '@ant-design/icons';

const initialData = [
  {
    id: 1,
    name: 'Lê Trường Giang',
    phone: '(225) 555-0118',
    address:
      'Số 1, Đường Tràng Tiền, Phường Tràng Tiền, Quận Hoàn Kiếm, Hà Nội',
    email: 'jane@microsoft.com',
  },
  {
    id: 2,
    name: 'Lê Trường Giang',
    phone: '(225) 555-0118',
    address:
      'Số 1, Đường Tràng Tiền, Phường Tràng Tiền, Quận Hoàn Kiếm, Hà Nội',
    email: 'jane@microsoft.com',
  },
  {
    id: 3,
    name: 'Lê Trường Giang',
    phone: '(225) 555-0118',
    address:
      'Số 1, Đường Tràng Tiền, Phường Tràng Tiền, Quận Hoàn Kiếm, Hà Nội',
    email: 'jane@microsoft.com',
  },
  {
    id: 4,
    name: 'Lê Trường Giang',
    phone: '(225) 555-0118',
    address:
      'Số 1, Đường Tràng Tiền, Phường Tràng Tiền, Quận Hoàn Kiếm, Hà Nội',
    email: 'jane@microsoft.com',
  },
];

const ClientPage: React.FC = () => {
  const [data, setData] = useState(initialData);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [currentClient, setCurrentClient] = useState(null);
  const [form] = Form.useForm();

  const showModal = (client = null) => {
    setCurrentClient(client);
    if (client) {
      form.setFieldsValue(client);
      setIsEdit(true);
    } else {
      form.resetFields();
      setIsEdit(false);
    }
    setIsModalVisible(true);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  const handleDelete = id => {
    setData(data.filter(client => client.id !== id));
    message.success('Khách hàng đã được xóa');
  };

  const handleOk = () => {
    form
      .validateFields()
      .then(values => {
        if (isEdit) {
          setData(
            data.map(client =>
              client.id === currentClient.id
                ? { ...values, id: currentClient.id }
                : client,
            ),
          );
          message.success('Khách hàng đã được cập nhật');
        } else {
          const newId = data.length ? data[data.length - 1].id + 1 : 1;
          setData([...data, { ...values, id: newId }]);
          message.success('Khách hàng mới đã được thêm');
        }
        setIsModalVisible(false);
      })
      .catch(info => {
        console.log('Validate Failed:', info);
      });
  };

  const columns = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    { title: 'Tên', dataIndex: 'name', key: 'name' },
    { title: 'số điện thoại', dataIndex: 'phone', key: 'phone' },
    { title: 'Địa chỉ', dataIndex: 'address', key: 'address' },
    { title: 'email', dataIndex: 'email', key: 'email' },
    {
      title: 'Hành động',
      key: 'action',
      render: (_, record) => (
        <>
          <Button icon={<EditOutlined />} onClick={() => showModal(record)} />
          <Popconfirm
            title="Bạn có chắc muốn xóa khách hàng này không?"
            onConfirm={() => handleDelete(record.id)}
            okText="Có"
            cancelText="Không">
            <Button icon={<DeleteOutlined />} />
          </Popconfirm>
        </>
      ),
    },
  ];

  return (
    <div>
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          marginBottom: 16,
        }}>
        <h2>Khách hàng</h2>
        <Button type="primary" onClick={() => showModal()}>
          Thêm tài khoản
        </Button>
      </div>
      <Table columns={columns} dataSource={data} rowKey="id" />
      <Modal
        title={isEdit ? 'Chỉnh sửa khách hàng' : 'Thêm khách hàng mới'}
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}>
        <Form form={form} layout="vertical" name="clientForm">
          <Form.Item
            name="name"
            label="Tên"
            rules={[{ required: true, message: 'Vui lòng nhập tên' }]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="phone"
            label="số điện thoại"
            rules={[
              { required: true, message: 'Vui lòng nhập số điện thoại' },
            ]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="address"
            label="Địa chỉ"
            rules={[{ required: true, message: 'Vui lòng nhập địa chỉ' }]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="email"
            label="Email"
            rules={[
              { required: true, message: 'Vui lòng nhập email' },
              { type: 'email', message: 'Email không hợp lệ' },
            ]}>
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
};

export default ClientPage;

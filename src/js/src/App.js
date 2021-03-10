import './App.css';
import React, { Component } from 'react'; 
import { getAllCustomers } from './Client';
import { LoadingOutlined, SearchOutlined } from '@ant-design/icons';
import {
  Table,
  Avatar,
  Spin,
  Modal,
  Input,
  Button,
  Space
} from 'antd';
import Highlighter from 'react-highlight-words';
import Container from './Container';
import Footer from './Footer';
import AddCustomerForm from './forms/AddCustomerForm';

const getIndicatorIcon = () =>  <LoadingOutlined style={{ fontSize: 24 }} spin />;


class App extends Component {


    state = {
      customers: [],
      isFetching: false,
      isChangeStatusModalVisible: false,
      searchText: '',
      searchedColumn: ''
    }

    componentDidMount(){
      this.fetchCustomers();
    }

    openChangeStatusModal = () => {
      this.setState({isChangeStatusModalVisible: true})
    }

    closeChangeStatusModal = () => {
      this.setState({isChangeStatusModalVisible: false})
    }


    fetchCustomers= () => {
      this.setState({
        isFetching: true
      });

      getAllCustomers()
      .then(res=> res.json()
      .then(customers => {
        this.setState(
          {
            customers,
            isFetching: false
          }
        );
        console.log(customers);
      }))
      .catch(error =>{
        //console.log(error.error.message);
        this.setState({
          isFetching: false
        });
      })
    }

    getColumnSearchProps = dataIndex => ({
      filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters }) => (
        <div style={{ padding: 8 }}>
          <Input
            ref={node => {
              this.searchInput = node;
            }}
            placeholder={`Search ${dataIndex}`}
            value={selectedKeys[0]}
            onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
            onPressEnter={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
            style={{ width: 188, marginBottom: 8, display: 'block' }}
          />
          <Space>
            <Button
              type="primary"
              onClick={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
              icon={<SearchOutlined />}
              size="small"
              style={{ width: 90 }}
            >
              Search
            </Button>
            <Button onClick={() => this.handleReset(clearFilters)} size="small" style={{ width: 90 }}>
              Reset
            </Button>
          </Space>
        </div>
      ),
      filterIcon: filtered => <SearchOutlined style={{ color: filtered ? '#1890ff' : undefined }} />,
      onFilter: (value, record) =>
        record[dataIndex]
          ? record[dataIndex].toString().toLowerCase().includes(value.toLowerCase())
          : '',
      onFilterDropdownVisibleChange: visible => {
        if (visible) {
          setTimeout(() => this.searchInput.select(), 100);
        }
      },
      render: text =>
        this.state.searchedColumn === dataIndex ? (
          <Highlighter
            highlightStyle={{ backgroundColor: '#ffc069', padding: 0 }}
            searchWords={[this.state.searchText]}
            autoEscape
            textToHighlight={text ? text.toString() : ''}
          />
        ) : (
          text
        ),
    });
  
    handleSearch = (selectedKeys, confirm, dataIndex) => {
      confirm();
      this.setState({
        searchText: selectedKeys[0],
        searchedColumn: dataIndex,
      });
    };
  
    handleReset = clearFilters => {
      clearFilters();
      this.setState({ searchText: '' });
    };

    render() {
      const { customers, isFetching, isChangeStatusModalVisible } = this.state;

      if(isFetching){
        return(
          <Container>
            <Spin indicator={getIndicatorIcon()}/>
          </Container>
        )
      }

      if(customers){
        
        const columns = [
          {
            title: '',
            dataIndex: 'avatar',
            render: (text, customer) => (
              <Avatar size='large'>
                {`${customer.name.charAt(0).toUpperCase()}`}
              </Avatar>
            ),
            width: '10%'
          },
          {
            title: 'name',
            dataIndex: 'name',
            key: 'name',
            width: '15%',
            ...this.getColumnSearchProps('name'),
          },
          {
            title: 'address',
            dataIndex: 'address',
            key: 'address',
            width: '15%'
          },
          {
            title: 'VIN',
            dataIndex: 'vin',
            key: 'vin',
            width: '15%'
          },
          {
            title: 'Reg Nr',
            dataIndex: 'regNr',
            key: 'regNr',
            width: '15%'
          },
          {
            title: 'status',
            dataIndex: 'status',
            key: 'status',
            width: '10%',
            ...this.getColumnSearchProps('status'),
          },


        ]; 

        return (
        <Container>
          <Table 
            dataSource={customers} 
            columns={columns}
            rowKey='vin'
            pagination={false}
            size='middle'
            />
          <Modal
            title="Change Car Status"
            visible={isChangeStatusModalVisible}
            onOk={this.closeChangeStatusModal}
            onCancel={this.closeChangeStatusModal}
            width={800}>
  
          <AddCustomerForm
            onSuccess = {() => {
              this.closeChangeStatusModal();
              this.fetchCustomers();
            }}
          />
          </Modal>
          <Footer
          handleChangeStatusClickEvent={this.openChangeStatusModal}
          />
        </Container>
        )
      }

      return <h1>No customers found</h1>
    }

  
}

export default App;

import React from 'react';
import Container from './Container';
import { Button } from 'antd';
import './style/Footer.css'

const Footer = (props) => (
    <div className="footer">
        <Container>
            <Button onClick={() => props.handleChangeStatusClickEvent() } type="primary"> Change Car Status</Button>
        </Container>
    </div>
)

export default Footer;
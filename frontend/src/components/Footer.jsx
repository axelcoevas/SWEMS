import React, { Component } from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import logo from "../assets/images/android-chrome-192x192.png";
import "../style/Footer.css";

export default class Footer extends Component {
    render() {
        return (
            <div className='footer mt-5'>
                <Card className='footer-card'>
                    <Card.Body>
                        <Container>
                            <Row>
                                <Col>
                                    <Row>
                                        <Col>
                                            <img
                                                className='footer-logo'
                                                src={logo}
                                                alt='logo'
                                            />
                                        </Col>
                                        <Col>
                                            <p className='footer-p'>SWEMS</p>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <p>
                                            We are creating High Quality
                                            Resources and tools to Aid
                                            developers during the developement
                                            of their projects
                                        </p>
                                    </Row>
                                </Col>
                                <Col>
                                    <Row>
                                        <h5>Men&uacute;</h5>
                                        <p>
                                            <Link to='/login'>
                                                Iniciar sesi&oacute;n
                                            </Link>
                                        </p>
                                        <p>
                                            <Link to='/signup'>
                                                Reg&iacute;strate
                                            </Link>
                                        </p>
                                    </Row>
                                </Col>
                                <Col>
                                    <Row>
                                        <h5>Men&uacute;</h5>
                                    </Row>
                                </Col>
                            </Row>
                        </Container>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}

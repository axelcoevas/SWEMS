import React, { Component } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../style/Signup.css";

export default class Signup extends Component {
    render() {
        return (
            <div className='pb-5'>
                <Card className='mx-auto my-4 signup-card'>
                    <Card.Body>
                        <Card.Title className='py-4'>Registro</Card.Title>
                        <Form>
                            <Container>
                                <Row>
                                    <Col>
                                        <Form.Group
                                            className='pb-3 pt-2 mx-auto signup-form-control'
                                            controllId='firstname'
                                        >
                                            <Form.Control
                                                type='text'
                                                placeholder='Nombre'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group
                                            className='pb-3 pt-2 mx-auto signup-form-control'
                                            controllId='surename'
                                        >
                                            <Form.Control
                                                type='text'
                                                placeholder='Apellido'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col xs={9}>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='email'
                                        >
                                            <Form.Control
                                                type='email'
                                                placeholder='E-mail'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='email'
                                        >
                                            <Form.Control
                                                type='number'
                                                placeholder='edad'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='occupation'
                                        >
                                            <Form.Control
                                                type='text'
                                                placeholder='Ocupaci&oacute;n'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='place'
                                        >
                                            <Form.Control
                                                type='text'
                                                placeholder='Instituci&oacute;n/Empresa'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='password'
                                        >
                                            <Form.Control
                                                type='password'
                                                placeholder='Contrase&ntilde;a'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group
                                            className='py-3 mx-auto signup-form-control'
                                            controllId='password2'
                                        >
                                            <Form.Control
                                                type='password'
                                                placeholder='Confirmar contrase&ntilde;a'
                                                size='lg'
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                            </Container>
                            <Button
                                className='mt-4 mb-3 mx-auto signup-button'
                                size='lg'
                                type='submit'
                            >
                                Iniciar Sesión
                            </Button>
                            <hr className='mx-auto signup-hr signup-form-control' />
                            <p>
                                ¿Ya tienes cuenta?&nbsp;
                                <Link to='/login'>Inicia Sesi&oacute;n</Link>
                            </p>
                        </Form>
                    </Card.Body>
                </Card>
            </div>
        );
    }

    componentDidMount() {
        window.scrollTo(0, 0);
    }
}

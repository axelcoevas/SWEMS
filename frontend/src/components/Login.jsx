import React, { Component } from "react";
import { Button, Card, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../style/Login.css";

export default class Login extends Component {
    render() {
        return (
            <div className='pb-5'>
                <Card className='mx-auto my-5 login-card'>
                    <Card.Body>
                        <Card.Title className='py-4'>Iniciar Sesión</Card.Title>
                        <Form>
                            <Form.Group
                                className='py-3 mx-auto login-form-control'
                                controllId='email'
                            >
                                <Form.Control
                                    type='email'
                                    placeholder='E-mail'
                                    size='lg'
                                />
                            </Form.Group>
                            <Form.Group
                                className='py-3 mx-auto login-form-control'
                                controllId='email'
                            >
                                <Form.Control
                                    type='password'
                                    placeholder='Contraseña'
                                    size='lg'
                                />
                            </Form.Group>
                            <Button
                                className='my-5 mx-auto login-button'
                                size='lg'
                                type='submit'
                            >
                                Iniciar Sesión
                            </Button>
                            <hr className='mx-auto login-hr login-form-control' />
                            <p>
                                ¿A&uacute;n no tienes cuenta?&nbsp;
                                <Link to='/signup'>Reg&iacute;strate</Link>
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

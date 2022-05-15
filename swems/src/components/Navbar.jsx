import React, { Component } from "react";
import { Nav, Button } from "react-bootstrap";
import "../style/Navbar.css";
import logo from "../assets/images/android-chrome-512x512.png";
import { Link } from "react-router-dom";

export default class Navbar extends Component {
    render() {
        return (
            <div>
                <Nav className='justify-content-center navbar'>
                    <Nav.Item>
                        <Nav.Link href='/'>
                            <img
                                src={logo}
                                alt='logo'
                                className='navbar-logo'
                            />
                        </Nav.Link>
                    </Nav.Item>
                    <span className='navbar-spacer'></span>
                    <Nav.Item>
                        <Nav.Link as={Link} to='/about'>
                            Acerca
                        </Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link as={Link} to='/contact'>
                            Contacto
                        </Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link as={Link} to='/login'>
                            Iniciar Sesi&oacute;n
                        </Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link as={Link} to='/signup'>
                            <Button variant='primary'>Reg&iacute;strate</Button>
                        </Nav.Link>
                    </Nav.Item>
                </Nav>
            </div>
        );
    }
}

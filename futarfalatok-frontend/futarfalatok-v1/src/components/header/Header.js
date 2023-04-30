import React from "react";
import { faBowlFood } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Button from "react-bootstrap/Button";
import { NavLink } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "./header.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Header = () => {
    useEffect(() => {
        document.title = "FutárFalatok";
    }, []);

    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null && localStorage.getItem("role") != null);
    const userToken = localStorage.getItem("token");
    const userRole = localStorage.getItem("role");
    
    const navigate = useNavigate();

    function handleLogout() {
        setIsLoggedIn(false);
        localStorage.clear();
        navigate("/login");
    }

    return (
    <div>
        {isLoggedIn && userRole === "ROLE_USER" ? (
            <Navbar bg="success" variant="dark" expand="lg" className="fw-bold">
            <Container fluid>
                <Navbar.Brand>
                    <FontAwesomeIcon icon={faBowlFood} size="2xl" style={{color: "#ffffff",}} />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll"/>
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="ms-auto my-2 my-lg-0 navlink"
                        style={{maxHeight: '150px'}}
                        navbarScroll
                    >
                        <NavLink className="navlink" to="/">Főoldal</NavLink>
                        <NavLink className="navlink" to="/restaurants">Éttermek</NavLink>
                    </Nav>
                    <Button variant="outline-light" onClick={handleLogout} to="/login">Kijelentkezés</Button>                
                </Navbar.Collapse>
            </Container>
        </Navbar>
        ) : isLoggedIn && userRole === "ROLE_ADMIN" ? (
            <Navbar bg="success" variant="dark" expand="lg" className="fw-bold">
                <Container fluid>
                    <Navbar.Brand>
                        <FontAwesomeIcon icon={faBowlFood} size="2xl" style={{color: "#ffffff",}} />
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll">
                        <Nav
                            className="ms-auto my-2 my-lg-0 navlink"
                            style={{maxHeight: '150px'}}
                            navbarScroll
                        >
                            <NavLink className="navlink" to="/">Főoldal</NavLink>
                            <NavLink className="navlink" to="/users">Felhasználók</NavLink>
                            <NavLink className="navlink" to="/admin-restaurants">Éttermek</NavLink>
                            <NavLink className="navlink" to="/dishes">Ételek</NavLink>
                            <NavLink className="navlink" to="/reviews">Értékelések</NavLink>
                            <NavLink className="navlink" to="/orders">Rendelések</NavLink>
                        </Nav>
                        <Button variant="outline-light" onClick={handleLogout} to="/login">Kijelentkezés</Button>              
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        ) : (
            <Navbar bg="success" variant="dark" expand="lg" className="fw-bold">
                <Container fluid>
                    <Navbar.Brand>
                        <FontAwesomeIcon icon={faBowlFood} size="2xl" style={{color: "#ffffff",}} />
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbarScroll"/>
                    <Navbar.Collapse id="navbarScroll">
                        <Nav
                            className="ms-auto my-2 my-lg-0 navlink"
                            style={{maxHeight: '150px'}}
                            navbarScroll
                        >
                            <NavLink className="navlink" to="/">Főoldal</NavLink>
                            <NavLink className="navlink" to="/restaurants">Éttermek</NavLink>
                        </Nav>
                        <Button variant="outline-light" className="me-2" href="/login">Bejelentkezés</Button>
                        <Button variant="outline-light" href="/registration">Regisztráció</Button>                
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        )

        }
    </div>
    );
}

export default Header
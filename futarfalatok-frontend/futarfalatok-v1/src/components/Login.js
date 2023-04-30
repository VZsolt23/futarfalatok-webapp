import React, { useState, useEffect } from "react";
import { Form, Button } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', {email , password});

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("role", response.data.role);
      localStorage.setItem("user_id", response.data.user_id);
      setIsLoggedIn(true);

    } catch (error) {
      toast.error('Sikertelen bejelentkezés!', {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  };

  useEffect(() => {
    const storedIsLoggedIn = localStorage.getItem("token");
    if (storedIsLoggedIn !== null) {
      setIsLoggedIn(true);
    }
    
  }, []);

  if (isLoggedIn) {
    navigate("/");
    window.location.reload();
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
      <ToastContainer />
        <div className="col-md-6">
          <Form className="m-auto my-5 border border-success rounded p-4 text-success" onSubmit={handleLogin}>
            <h1 className="text-center mb-4">Bejelentkezés</h1>
            <Form.Group controlId="formEmail">
              <div className="text-start">
                <Form.Label>Email:</Form.Label>
              </div>
              <Form.Control type="email" value={email} onChange={handleEmailChange} required/>
            </Form.Group>
            <Form.Group controlId="formPassword">
              <div className="text-start">
                <Form.Label>Jelszó:</Form.Label>
              </div>
              <Form.Control type="password" value={password} onChange={handlePasswordChange} required/>
            </Form.Group>
            <Button variant="success" type="submit" className="mt-3 w-100">Log in</Button>
          </Form>
        </div>
      </div>
    </div>
  );
}

export default Login
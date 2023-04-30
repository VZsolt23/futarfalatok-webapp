import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

const Registration = () => {
  const [lastname, setLastname] = useState('');
  const [firstname, setFirstname] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [password, setPassword] = useState('');
  const [passwordMatch, setPasswordMatch] = useState('');

  const handleSubmit = async(event) => {
    event.preventDefault();
    if (password !== passwordMatch) {
      toast.error('A jelszavak nem egyeznek meg!', {
        position: toast.POSITION.TOP_CENTER,
      });
    } else {
      try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/register', {
          firstName: firstname,
          lastName: lastname,
          email: email,
          password: password,
          phoneNumber: phoneNumber
        });
        console.log(response);

        toast.success('Sikeres regisztráció!', {
          position: toast.POSITION.TOP_CENTER,
        });
      } catch (error) {
        toast.error('Sikertelen regisztráció!', {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <Form className="m-auto my-5 border border-success rounded p-4 text-success" onSubmit={handleSubmit}>
            <h1 className="mb-5">Regisztráció</h1>
            <ToastContainer />
            <Form.Group controlId="formLastname">
              <div className="text-start"> 
                <Form.Label>Vezetéknév:</Form.Label>
              </div>
              <Form.Control type="text" value={lastname} onChange={(event) => setLastname(event.target.value)} required/>
            </Form.Group>
            <Form.Group controlId="formFirstname">
              <div className="text-start"> 
                <Form.Label>Keresztnév:</Form.Label>
              </div>
              <Form.Control type="text" value={firstname} onChange={(event) => setFirstname(event.target.value)} required/>
            </Form.Group>
            <Form.Group controlId="formEmail">
              <div className="text-start"> 
                <Form.Label>Email:</Form.Label>
              </div>
              <Form.Control type="email" value={email} onChange={(event) => setEmail(event.target.value)} required/>
            </Form.Group>
            <Form.Group controlId="formPhoneNumber">
              <div className="text-start"> 
                <Form.Label>Telefonszám:</Form.Label>
              </div>
              <Form.Control type="tel" value={phoneNumber} pattern="[0-9]{11}" onChange={(event) => setPhoneNumber(event.target.value)} required/>
            </Form.Group>
            <Form.Group controlId="formPassword">
              <div className="text-start"> 
                <Form.Label>Jelszó:</Form.Label>
              </div>
              <Form.Control type="password" value={password} onChange={(event) => setPassword(event.target.value)} required/>
            </Form.Group>
            <Form.Group controlId="formPasswordMatch">
              <div className="text-start"> 
                <Form.Label>Jelszó megerősítés:</Form.Label>
              </div>
              <Form.Control type="password" value={passwordMatch} onChange={(event) => setPasswordMatch(event.target.value)} required/>
            </Form.Group>
            <Button variant="success" type="submit" className="mt-3">Regisztráció</Button>
          </Form>
        </div>
      </div>
    </div>
  );
}
export default Registration
import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

const UserUpdate = () => {
    const { id } = useParams();
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
    const [user, setUser] = useState([]);
    const [lastname, setLastname] = useState('');
    const [firstname, setFirstname] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [password, setPassword] = useState('');
    const userToken = localStorage.getItem('token');
    const userRole = localStorage.getItem('role');
    
    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
            Authorization: `Bearer ${userToken}`
        }
    })

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await authAxios.get(`api/v1/users/${id}`);
                setUser(result.data);

                setLastname(result.data.lastName);
                setFirstname(result.data.firstName);
                setEmail(result.data.email);
                setRole(result.data.role);
                setPhoneNumber(result.data.phoneNumber);
                setPassword(result.data.password);
            } catch (error) {
                console.log(error);
            }
        };
    
        fetchData();
      }, [id]);

      const handleSubmit = () => {
        try {
            const requestBody = {
                id: user.id,
                firstName: firstname,
                lastName: lastname,
                email: email,
                role: role,
                phoneNumber: phoneNumber
            }
            authAxios.put('api/v1/users', requestBody)
            .then(response => {
                toast.success('Sikeres módosítás!', {
                    position: toast.POSITION.TOP_CENTER,
                  });
            })
        } catch(error) {
            toast.success('Sikertelen módosítás!', {
                position: toast.POSITION.TOP_CENTER,
              });
        }
    }

    if (userRole === 'ROLE_ADMIN') {
        return(
            <div className="container mt-5">
                <div className="row justify-content-center">
                    <div className="col-md-6">
                    <Form className="m-auto my-5 border border-success rounded p-4 text-success" onSubmit={handleSubmit}>
                        <h1 className="mb-5">{user.email}</h1>
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
                        <Form.Group controlId="formRole">
                        <div className="text-start"> 
                            <Form.Label>Szerepkör:</Form.Label>
                        </div>
                        <Form.Control type="text" value={role} onChange={(event) => setRole(event.target.value)} required/>
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
                        <Form.Control type="password" value={password} disabled required/>
                        </Form.Group>
                        <Button variant="success" type="submit" className="mt-3">Módosít</Button>
                    </Form>
                    </div>
                </div>
            </div>
        ); 
    } else {
        return (
            <div className='container p-5'>
                <h1>Ezt nem szabad látnod! :(</h1>
            </div>
        );
    }
}

export default UserUpdate
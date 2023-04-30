import React, { useState, useEffect } from "react";
import { Form, Button } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

const CreateRestaurant = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
    const [restaurant, setRestaurant] = useState([]);
    const [delivery, setDelivery] = useState('');
    const [name, setName] = useState('');
    const [rating, setRating] = useState('');
    const userToken = localStorage.getItem('token');
    const userRole = localStorage.getItem('role');
    
    const authAxios = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
            Authorization: `Bearer ${userToken}`
        }
    })

      const handleSubmit = () => {
        try {
            const requestBody = {
                id: restaurant.id,
                name: name,
                rating: rating,
                deliveryFee: delivery
            }
            authAxios.post('api/v1/restaurants/create', requestBody)
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
                        <h1 className="mb-5">Étterem hozzáadása</h1>
                        <ToastContainer />
                        <Form.Group controlId="formName">
                        <div className="text-start"> 
                            <Form.Label>Elnevezés:</Form.Label>
                        </div>
                        <Form.Control type="text" value={name} onChange={(event) => setName(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formDelivery">
                        <div className="text-start"> 
                            <Form.Label>Szállítási díj:</Form.Label>
                        </div>
                        <Form.Control type="number" value={delivery} onChange={(event) => setDelivery(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formRating">
                        <div className="text-start"> 
                            <Form.Label>Értékelés:</Form.Label>
                        </div>
                        <Form.Control type="number" value={rating} min={1} max={5} onChange={(event) => setRating(event.target.value)} required/>
                        </Form.Group>
                        <Button variant="success" type="submit" className="mt-3">Hozzáad</Button>
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

export default CreateRestaurant
import React, { useState, useEffect } from "react";
import { Form, Button } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

const DishToRestaurant = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
    const [restaurantId, setRestaurantId] = useState([]);
    const [dishId, setDishId] = useState('');
    const [price, setPrice] = useState('');
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
            authAxios.put(`api/v1/restaurant-dish/${restaurantId}/${dishId}/${price}`)
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
                        <h1 className="mb-5">Étel hozzárendelése étteremhez</h1>
                        <ToastContainer />
                        <Form.Group controlId="formRestaurant">
                        <div className="text-start"> 
                            <Form.Label>Étterem azonosító:</Form.Label>
                        </div>
                        <Form.Control type="number" value={restaurantId} onChange={(event) => setRestaurantId(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formDish">
                        <div className="text-start"> 
                            <Form.Label>Étel azonosító:</Form.Label>
                        </div>
                        <Form.Control type="number" value={dishId} onChange={(event) => setDishId(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formPrice">
                        <div className="text-start"> 
                            <Form.Label>Ár:</Form.Label>
                        </div>
                        <Form.Control type="number" value={price} onChange={(event) => setPrice(event.target.value)} required/>
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

export default DishToRestaurant
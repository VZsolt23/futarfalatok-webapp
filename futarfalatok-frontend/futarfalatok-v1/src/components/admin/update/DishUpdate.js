import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

const DishUpdate = () => {
    const { id } = useParams();
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
    const [dish, setDish] = useState([]);
    const [calories, setCalories] = useState('');
    const [carbohydrates, setCarbohydrates] = useState('');
    const [fat, setFat] = useState('');
    const [name, setName] = useState('');
    const [protein, setProtein] = useState('');
    const [course, setCourse] = useState('');
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
                const result = await authAxios.get(`api/v1/dishes/${id}`);
                setDish(result.data);

                setCalories(result.data.calories);
                setCarbohydrates(result.data.carbohydrates);
                setFat(result.data.fat);
                setName(result.data.name);
                setProtein(result.data.protein);
                setCourse(result.data.course);
            } catch (error) {
                console.log(error);
            }
        };
    
        fetchData();
      }, [id]);

    const handleSubmit = () => {
        try {
            const requestBody = {
                id: dish.id,
                name: name,
                course: course,
                calories: calories,
                protein: protein,
                carbohydrates: carbohydrates,
                fat: fat
            }
            authAxios.put('api/v1/dishes', requestBody)
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
                        <h1 className="mb-5">{dish.name}</h1>
                        <ToastContainer />
                        <Form.Group controlId="formName">
                        <div className="text-start"> 
                            <Form.Label>Elnevezés:</Form.Label>
                        </div>
                        <Form.Control type="text" value={name} onChange={(event) => setName(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formCalories">
                        <div className="text-start"> 
                            <Form.Label>Kalória:</Form.Label>
                        </div>
                        <Form.Control type="text" value={calories} onChange={(event) => setCalories(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formFat">
                        <div className="text-start"> 
                            <Form.Label>Zsír:</Form.Label>
                        </div>
                        <Form.Control type="text" value={fat} onChange={(event) => setFat(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formCarbohydrates">
                        <div className="text-start"> 
                            <Form.Label>Szénhidrát:</Form.Label>
                        </div>
                        <Form.Control type="text" value={carbohydrates} onChange={(event) => setCarbohydrates(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formProtein">
                        <div className="text-start"> 
                            <Form.Label>Fehérje:</Form.Label>
                        </div>
                        <Form.Control type="text" value={protein} onChange={(event) => setProtein(event.target.value)} required/>
                        </Form.Group>
                        <Form.Group controlId="formCourse">
                        <div className="text-start"> 
                            <Form.Label>Típus:</Form.Label>
                        </div>
                        <Form.Control type="text" value={course} onChange={(event) => setCourse(event.target.value)} required/>
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

export default DishUpdate
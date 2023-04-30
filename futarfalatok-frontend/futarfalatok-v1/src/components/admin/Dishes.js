import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const Dishes = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
  const [dishes, setDishes] = useState([]);
  const userToken = localStorage.getItem('token');
  const userRole = localStorage.getItem('role');
  const navigate = useNavigate();

  const authAxios = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        Authorization: `Bearer ${userToken}`
    }
  })

  useEffect(() => {
    const fetchData = async () => {
        try {
            const result = await authAxios.get('api/v1/dishes');
            setDishes(result.data);
        } catch (error) {
            console.log(error);
        }
    };

      fetchData();
  }, []);

  function handleButtonClick(id) {
    navigate(`/dishes/${id}`);
  }
  
    if (userRole === 'ROLE_ADMIN') {
        return (
            <div>
            <ToastContainer />
            <table className="table table-hover mt-3 mb-5 p-5">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Név</th>
                    <th>Kalória</th>
                    <th>Szénhidrát</th>
                    <th>Fehérje</th>
                    <th>Típus</th>
                    <th>Művelet</th>
                </tr>
                </thead>
                <tbody>
                {dishes.map((dish) => (
                    <tr key={dish.id}>
                    <td>{dish.id}</td>
                    <td>{dish.name}</td>
                    <td>{dish.calories}</td>
                    <td>{dish.carbohydrates}</td>
                    <td>{dish.protein}</td>
                    <td>{dish.course}</td>
                    <td className="text-center">
                        <button className="btn btn-success badge-pill mx-1" onClick={() => handleButtonClick(dish.id)}>Módosítás</button>
                        <button className="btn btn-danger badge-pill mx-1" onClick={(e) => {
                      const url = `http://localhost:8080/api/v1/dishes/${dish.id}`;
                      fetch(url, {
                        method: 'DELETE',
                        headers: {
                          Authorization: `Bearer ${userToken}`,
                          'Content_Type': 'application/json'
                        },
                      })
                        .then((response) => {
                          if (!response.ok) {
                            throw new Error('Valami félrecsúszott!');
                          }
                          alert("Sikeres törlés!");
                          window.location.reload();
                        })
                        .catch((e) => {
                          console.log(e);
                        })
                    }}>Törlés</button>
                    </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div>
              <a href='/dishes/create' className='btn btn-success m-2'>Új étel felvétele</a>
              <a href='/dish-to-restaurant' className='btn btn-success m-2'>Étel hozzárendelése</a>
            </div>
            </div>
        );
    } else {
        return (
            <div className='container p-5'>
                <h1>Ezt nem szabad látnod! :(</h1>
            </div>
        )
    }
};

export default Dishes
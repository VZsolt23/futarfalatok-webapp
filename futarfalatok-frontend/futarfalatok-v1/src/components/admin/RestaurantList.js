import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const RestaurantList = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
  const [restaurants, setRestaurants] = useState([]);
  const userToken = localStorage.getItem('token');
  const userRole = localStorage.getItem('role');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
        try {
            const result = await axios.get('api/v1/restaurants');
            setRestaurants(result.data);
        } catch (error) {
            console.log('Most se jó!!!');
        }
    };

      fetchData();
  }, []);

  function handleButtonClick(id) {
    navigate(`/restaurants/${id}/update`);
  }
  
    if (userRole === 'ROLE_ADMIN') {
        return (
            <div>
            <ToastContainer />
            <table className="table table-hover mt-3 mb-5 p-5">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Szállítási díj</th>
                    <th>Értékelés</th>
                    <th>Művelet</th>
                </tr>
                </thead>
                <tbody>
                {restaurants.map((restaurant) => (
                    <tr key={restaurant.id}>
                    <td>{restaurant.id}</td>
                    <td>{restaurant.name}</td>
                    <td>{restaurant.deliveryFee}</td>
                    <td>{restaurant.rating}</td>
                    <td className="text-center">
                        <button className="btn btn-success badge-pill mx-1" onClick={() => handleButtonClick(restaurant.id)}>Módosítás</button>
                        <button className="btn btn-danger badge-pill mx-1" onClick={(e) => {
                      const url = `http://localhost:8080/api/v1/restaurants/${restaurant.id}`;
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
              <a href='/restaurants/create' className='btn btn-success m-2'>Új étterem felvétele</a>
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

export default RestaurantList
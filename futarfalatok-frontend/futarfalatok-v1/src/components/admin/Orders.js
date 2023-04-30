import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const UserOrders = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
  const [userOrders, setOrders] = useState([]);
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
            const result = await authAxios.get('api/v1/orders');
            setOrders(result.data);
        } catch (error) {
            console.log(error);
        }
    };
      fetchData();
  }, []);
  
    if (userRole === 'ROLE_ADMIN') {
        return (
            <div>
            <ToastContainer />
            <table className="table table-hover mt-3 mb-5 p-5">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Név</th>
                    <th>Email</th>
                    <th>Szállítási idő</th>
                    <th>Dátum</th>
                    <th>Ár</th>
                    <th>Művelet</th>
                </tr>
                </thead>
                <tbody>
                {userOrders.map((userOrder) => (
                    <tr key={userOrder.id}>
                    <td>{userOrder.id}</td>
                    <td>{userOrder.user.lastName + " " + userOrder.user.firstName}</td>
                    <td>{userOrder.user.email}</td>
                    <td>{userOrder.deliveryTime}</td>
                    <td>{userOrder.orderDate}</td>
                    <td>{userOrder.price}</td>
                    <td className="text-center">
                        <button className="btn btn-danger badge-pill mx-1" onClick={(e) => {
                      const url = `http://localhost:8080/api/v1/orders/${userOrder.id}`;
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

export default UserOrders
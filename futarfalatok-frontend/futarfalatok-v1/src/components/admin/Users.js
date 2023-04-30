import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const User = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
  const [users, setUsers] = useState([]);
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
            const result = await authAxios.get('api/v1/users');
            setUsers(result.data);
        } catch (error) {
            console.log('Most se jó!!!');
        }
    };

    fetchData();
  }, []);

  function handleButtonClick(id) {
    navigate(`/users/${id}`);
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
                <th>Email</th>
                <th>Role</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.lastName + " " + user.firstName}</td>
                  <td>{user.email}</td>
                  <td>{user.role}</td>
                  <td className="text-center">
                    <button className="btn btn-success badge-pill mx-1" onClick={() => handleButtonClick(user.id)}>Módosítás</button>
                    <button className="btn btn-danger badge-pill mx-1" onClick={(e) => {
                      const url = `http://localhost:8080/api/v1/users/${user.id}`;
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

export default User
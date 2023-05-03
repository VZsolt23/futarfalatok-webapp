import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Button, ListGroup, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const RestaurantDetail = () => {
  const { id } = useParams();
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem("token") != null);
  const [restaurant, setRestaurant] = useState();
  const [dishes, setDishes] = useState([]);
  const [notFound, setNotFound] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);
  const [selectedDishIds, setSelectedDishIds] = useState([]);
  const [priceSum, setPriceSum] = useState(0);
  const userToken = localStorage.getItem('token');
  const [comment, setComment] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/restaurants/${id}`)
      .then(response => { 
        if (response.status === 200) {
          setIsSuccess(true);
          axios.get(`http://localhost:8080/api/v1/restaurants/${id}/dishes`)
            .then(dishResponse => setDishes(dishResponse.data))
            .catch(error => console.log(error))
          setRestaurant(response.data)
        } 
      })
      .catch(error => {
        if (error.response.status === 404) {
          setNotFound(true);
        } else {
          return(
            <div className='p-5'>
              <h1>Valami hiba történt!</h1>
            </div>
          );
        }
      });
  }, [id]);

  const handleDishSelection = (dishId) => {
    if (selectedDishIds.includes(dishId)) {
      setSelectedDishIds(selectedDishIds.filter((id) => id !== dishId));
      setPriceSum(priceSum - dishes.find(dish => dish.id === dishId).priceOfDish);
      console.log("Pénz: " + priceSum);
    } else {
      setSelectedDishIds([...selectedDishIds, dishId]);
      setPriceSum(priceSum + dishes.find(dish => dish.id === dishId).priceOfDish);
      console.log("Pénz: " + priceSum);
    }
  };

  const authAxios = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        Authorization: `Bearer ${userToken}`,
        "Content-Type": "application/json"
    }
  })

  const handleOrderItems = async () => {
    if (!isLoggedIn) {
      navigate("/login");
    } else {
      console.log(selectedDishIds);
      const userId = localStorage.getItem("user_id");
      try {
        const orderData = {
          userId: userId,
          restaurantId: restaurant.id,
          dishes: selectedDishIds,
          price: priceSum,
        };
        const result = await authAxios.post('api/v1/orders/create', orderData);
        alert(result.data.user.email + "\n" + result.data.restaurant.name + "\n" + result.data.deliveryTime + "\n" + result.data.price);
        navigate('/');
      } catch (error) {
        console.log(error);
      }
    }
  };

  const handleSubmit = () => {
    try {
      if (isLoggedIn) {
        const userId = localStorage.getItem("user_id");
        const requestBody = {
          userId: userId,
          restaurantId: restaurant.id,
          comment: comment
        }
        authAxios.post("api/v1/reviews/create", requestBody);
      } else {
        navigate("/login");
      }
    } catch (error) {
      console.log(error);
    }
  }

  if (isSuccess) {
    return (
      <div className='p-5'>
        <h2>{restaurant.name}</h2>
        <p>Értékelés: {restaurant.rating}</p>
        <p>Szállítási díj: {restaurant.deliveryFee}</p>
        <h3>Ételek:</h3>
        <ListGroup className='m-5'>
      {dishes.map((dish) => (
        <ListGroup.Item
          key={dish.id}
          active={selectedDishIds.includes(dish.id)}
          onClick={() => handleDishSelection(dish.id)}
          className='m-2'
        >
          <div className="d-flex justify-content-between align-items-center">
            <span>{dish.name}</span>
            <span>{dish.priceOfDish} Ft</span>
          </div>
        </ListGroup.Item>
      ))}
      <Button disabled={!selectedDishIds.length} onClick={handleOrderItems} variant="success" className='m-1'>
        Megrendelés
      </Button>
    </ListGroup>

        <div className='mt-3 border border-success rounded p-3'>
          <h5>Kommentek</h5>
          {restaurant.restaurant_reviews.map(review => <p key={review.id}>{review.body}</p>)}
        </div>
        <div className='mt-3'>
        <Form className="m-auto my-5 border border-success rounded p-4 text-success" onSubmit={handleSubmit}>
            <h1 className="mb-5">Komment írása</h1>
            <Form.Group controlId="formLastname">
              <div className="text-start"> 
                <Form.Label>Szöveg:</Form.Label>
              </div>
              <Form.Control as="textarea" value={comment} onChange={(event) => setComment(event.target.value)} required/>
            </Form.Group>
            <Button disabled={!comment} variant="success" type="submit" className="mt-3">Küldés</Button>
          </Form>
        </div>
      </div>
    );

  } else if (notFound) {
      return(
        <div className='container p-5'>
          <h1>Nincs ilyen étterem</h1>
          <h2>Próbáld újra!</h2>
        </div>
      );
  }

}

export default RestaurantDetail;
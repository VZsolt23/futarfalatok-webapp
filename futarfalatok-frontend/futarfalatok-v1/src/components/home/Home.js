import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import axios from "axios";
import { CardGroup, Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const Home = () => {
  const [restaurants, setRestaurants] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/restaurants')
      .then(response => setRestaurants(response.data))
      .catch(error => console.error(error.response));
  }, []);

  console.log(restaurants);
    return (
      <div className="container p-5">
        <h1>Üdvözöl a FutárFalatok weboldal!</h1>
        <h2>Gyors és minőségi kiszállítás!</h2>
        <h3>Itt az első három étterem, de több is van!</h3>
        <CardGroup className="p-3">
        {restaurants.slice(0,3).map((restaurant) => (
        <Card key={restaurant.id} className="m-2" style={{ width: '100%' }}>
          <Card.Img variant="top" src="restaurant_img.png" />
          <Card.Body>
            <Card.Title>{restaurant.name}</Card.Title>
            <Card.Text>
              {restaurant.rating}
            </Card.Text>
            <Button variant="success" href={`http://localhost:3000/restaurants/${restaurant.id}`}>Részletek</Button>
          </Card.Body>
        </Card>
        ))}
        </CardGroup>
      </div>
      );
}

export default Home
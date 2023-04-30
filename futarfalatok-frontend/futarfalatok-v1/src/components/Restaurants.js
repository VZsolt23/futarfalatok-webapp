import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import axios from "axios";
import {Row, Col, CardGroup, Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const Restaurants = () => {
  const [restaurants, setRestaurants] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/restaurants')
      .then(response => setRestaurants(response.data))
      .catch(error => console.error(error.response));
  }, []);

  console.log(restaurants);
    return (
      <Container fluid className="p-3 mb-5">
        <Row xs={1} md={2} lg={3}>
        {restaurants.map((restaurant) => (
          <Col key={restaurant.id} className="p-2">
        <Card className="p-1">
          <Card.Img variant="top" src="restaurant_img.png" />
          <Card.Body>
            <Card.Title>{restaurant.name}</Card.Title>
            <Card.Text>
              {restaurant.rating}
            </Card.Text>
            <Button variant="success" href={`http://localhost:3000/restaurants/${restaurant.id}`}>Részletek</Button>
          </Card.Body>
        </Card>
        </Col>
        ))}
        </Row>
      </Container>
      );
}

export default Restaurants
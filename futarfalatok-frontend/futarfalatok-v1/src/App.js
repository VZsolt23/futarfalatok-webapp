import logo from './logo.svg';
import './App.css';
import Layout from './components/Layout';
import { Routes, Route } from 'react-router-dom';
import Home from './components/home/Home';
import Login from './components/Login';
import Registration from './components/Registration';
import User from './components/admin/Users';
import RestaurantList from './components/admin/RestaurantList';
import RestaurantDetail from './components/RestaurantDetail';
import Restaurants from './components/Restaurants';
import Review from './components/admin/Reviews';
import Dishes from './components/admin/Dishes';
import UserOrders from './components/admin/Orders';
import UserUpdate from './components/admin/update/UserUpdate';
import DishUpdate from './components/admin/update/DishUpdate';
import RestaurantUpdate from './components/admin/update/RestaurantUpdate';
import CreateDish from './components/admin/create/CreateDish';
import CreateRestaurant from './components/admin/create/CreateRestaurant';
import DishToRestaurant from './components/admin/create/DishToRestaurant';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route path='/' element={<Home />}/>
          <Route path='/login' element={<Login />}/>
          <Route path='/registration' element={<Registration />}/>
          <Route path='/users' element={<User />} />
          <Route exact path='/users/:id' element={<UserUpdate />}/>
          <Route path='/admin-restaurants' element={<RestaurantList />}/>
          <Route path='/restaurants' element={<Restaurants />}/>
          <Route exact path='/restaurants/:id' element={<RestaurantDetail />} />
          <Route exact path='/restaurants/:id/update' element={<RestaurantUpdate />} />
          <Route path='/restaurants/create' element={<CreateRestaurant />}/>
          <Route path='/reviews' element={<Review />}/>
          <Route path='/dishes' element={<Dishes />}/>
          <Route path='/dish-to-restaurant' element={<DishToRestaurant />}/>
          <Route exact path='/dishes/:id' element={<DishUpdate />}/>
          <Route path='/dishes/create' element={<CreateDish />}/>
          <Route path='/orders' element={<UserOrders />}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;

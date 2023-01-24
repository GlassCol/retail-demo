
import React from 'react';
import { useNavigate } from 'react-router-dom';
import ShoppingCartService from '../service/ShoppingCartService';

const ItemCard = ({ item, activeUser }) => {
  const navigate = useNavigate();

  const addToCart = () => {
    ShoppingCartService.addToShoppingCart(item);
}
const removeFromCart = () => {
    ShoppingCartService.removeFromShoppingCart(item.id);
}

  return (
    <div className="Item" key={item.id}>
      <div className='inventoryItem'>
        <div>
          <p>{item.description}</p>
        </div>

        <div>
          <img src={item.image === "N/A" ? item.image : "https://via.placeholder.com/400"} alt={item.name} />
        </div>

        <div>
          <h4>{item.quantity} - in stock</h4>
          <h3>{item.name}</h3>
          <span>{item.price}$</span>
          <thead>
            <tr>
              <th scope='col'>
              <button className='cardButton' onClick={() => addToCart()}>Add To Cart</button>
              </th>
              {activeUser === 'admin' &&
              <th scope='col'>
              <button className='cardButton' onClick={() => navigate('/editItem/' + item.id)}>Edit Item</button>
              </th>
              }
              
              {activeUser === 'admin' &&
              <th scope='col'>
              <button className='cardButton' onClick={() => removeFromCart()}>Delete Item</button>
              </th>
            }
            </tr>
          </thead>
        </div>
      </div>
    </div>
  );
}

export default ItemCard;
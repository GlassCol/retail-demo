
import React from 'react';
import { useNavigate } from 'react-router-dom';

const ItemCard = ({ item, addToCart, activeUser }) => {
  const navigate = useNavigate();
  return (
    <div className="Item" key={item.id}>
      <div className='inventoryItem'>
        <div>
          <p>{item.description}</p>
        </div>

        <div>
          <img src={item.Poster !== "N/A" ? item.Poster : "https://via.placeholder.com/400"} alt={item.name} />
        </div>

        <div>
          <span>{item.stock} in stock</span>
          <h3>{item.name}</h3>
          <h4>{item.price}</h4>
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
            </tr>
          </thead>
        </div>
      </div>
    </div>
  );
}

export default ItemCard;
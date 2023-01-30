
import React from 'react';
import { useNavigate } from 'react-router-dom';
import InventoryItemService from '../service/InventoryItemService';
import ShoppingCartService from '../service/ShoppingCartService';

const ItemCard = ({ item, activeUser }) => {
  const navigate = useNavigate();

  const addToCart = () => {
    item.userId = activeUser.data.id;
    ShoppingCartService.addToShoppingCart(item);
    alert(item.name + " has been added to your cart")
  }
  const removeItem = () => {
    InventoryItemService.deleteInventoryItem(item.id);
    alert(item.name + " has been removed from the inventory")
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
              {activeUser.data !== '' &&
                <th scope='col'>
                  <button className='cardButton' onClick={() => addToCart()}>Add To Cart</button>
                </th>
              }
              {activeUser.data.username === 'admin' &&
                <th scope='col'>
                  <button className='cardButton' onClick={() => navigate('/editItem/' + item.id)}>Edit Item</button>
                </th>
              }

              {activeUser.data.username === 'admin' &&
                <th scope='col'>
                  <button className='cardButton' onClick={() => removeItem()}>Delete Item</button>
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
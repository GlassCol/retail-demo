
import React from 'react';

const OrderedItemCard = ({ item }) => {

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
        </div>
      </div>
    </div>
  );
}

export default OrderedItemCard;
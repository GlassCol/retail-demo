
import React from 'react';

const ItemCard = ({ item }) => {
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
      </div>
      </div>
    </div>
  );
}

export default ItemCard;
import React from 'react';
import ShoppingCartService from '../service/ShoppingCartService';
const ShoppingCartLine = ({ item }) => {
    return (
        <div className="Item" key={item.id}>
            <div className="shoppingCartLine">
                <span>
                    <h2>{item.name} - {item.price}$</h2>
                    <button className='shoppingCartLineButton' onClick={
                        ShoppingCartService.deleteFromShoppingCart(item.id)
                    }>
                        Remove</button>
                </span>
            </div>

        </div>
    );
}

export default ShoppingCartLine;
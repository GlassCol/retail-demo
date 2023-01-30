import React, { useState, useEffect } from 'react'
import '../src/css/App.css';
import ShoppingCartLine from './component/ShoppingCartLine';
import ShoppingCartService from './service/ShoppingCartService';
import OrderService from './service/OrderService';
import UserService from './service/UserService';

const ShoppingCart = () => {
    const [items, setItems] = useState([]);
    const [activeUser, setActiveUser] = useState(UserService.getCurrentUser());
    const [tax, setTax] = useState(0);
    const [itemCost, setItemCost] = useState(0);

    const getItems = async () => {
        setActiveUser(await UserService.getCurrentUser());
        const response = await ShoppingCartService.getAllShoppingCartItems();
        setItems(response.data);
    }

    const getTax = () => {
        setTax(itemCost * 0.06);
    }

    const getItemCost = () => {
        let itemCost = 0;
        Array.from(items).forEach(item => {
            itemCost += item.price;
        })
        setItemCost(itemCost);
    }

    const getTotalCost = () => {
        return getItemCost() + getTax();
    }

    const makeOrder = async () => {
        let tempOrder = {
            id: null,
            userId: activeUser.data.id,
            itemQuantity: items.length,
            price: itemCost,
            discount: 0,
            tax: tax,
            status: 'Pending'
        }
        try {
            console.log(tempOrder);
            await OrderService.createOrder(tempOrder).then(() => {
                ShoppingCartService.clearShoppingCart();
                alert('Order Placed');
            });
        } catch (error) {
            console.log(error);
            alert('Order Failed');
        }
    }
    useEffect(() => {
        getItems();
        getTotalCost();
    }, [items]);

    return (
        <>
            <div>
                <nav className="nav">
                    <ul>
                        <li>
                            <a href="/">Home</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div className="app">
                <h1>Shopping Cart</h1>
                {items?.length > 0 ? (
                    <div className="container">

                        {items.map((item) => (
                            <div className='shoppingCartLineContainer'>
                                <ShoppingCartLine key={item.id} item={item} getItems={getItems} />
                            </div>
                        ))}
                        <div className='totalContainer'>
                            <h2>
                                Subtotal: ${itemCost}<br /><br />
                                Tax: ${tax.toFixed(2)}<br /><br />
                                Total: ${itemCost + tax}
                            </h2>
                        </div>
                        <div className='submitButtonContainer'>
                            <button className="submitOrderButton" onClick={() => { ShoppingCartService.clearShoppingCart() }}>Clear Cart</button>
                        </div>
                        <div className='submitButtonContainer'>
                            <button className="submitOrderButton" onClick={() => { makeOrder() }}>Checkout</button>
                        </div>
                    </div>
                ) : (
                    <div className="empty">
                        <h2>Your Cart is Empty</h2>
                    </div>
                )}
            </div>
        </>
    )
}

export default ShoppingCart
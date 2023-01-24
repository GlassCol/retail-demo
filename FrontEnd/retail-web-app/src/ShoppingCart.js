import React, { useState, useEffect } from 'react'
import '../src/css/App.css';
import ShoppingCartLine from './component/ShoppingCartLine';
import ShoppingCartService from './service/ShoppingCartService';
import { useParams } from 'react-router-dom';

const ShoppingCart = () => {
    const [items, setItems] = useState([]);
    const { user } = useParams();

    const getItems = async () => {
        const response = await ShoppingCartService.getAllShoppingCartItems();
        setItems(response.data);
    }
    useEffect(() => {
        getItems();
    }, []);

    return (
        <>
            <div>
                <nav className="nav">
                    <ul>
                        <li>
                            {user === "admin" ? (
                            <a href='/admin'>Home</a>
                            ) : (
                            <a href='/user'>Home</a>
                            )}
                        </li>
                    </ul>
                </nav>
            </div>
            <div className="app">
                <h1>Shopping Cart</h1>
                {items?.length > 0 ? (
                    <div className="container">

                        {items.map((item) => (
                            <ShoppingCartLine key={item.id} item={item} />
                        ))}
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
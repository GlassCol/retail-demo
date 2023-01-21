import React from 'react';
import { useState } from 'react';
import InventoryItemService from './service/InventoryItemService';
import '../src/css/App.css';

const AddItem = () => {
    const [item, setItem] = useState({
        id: "",
        name: "",
        description: "",
        price: "",
        quantity: "",
        poster: ""
    });

    const handleChange = (e) => {
        setItem({ ...item, [e.target.name]: e.target.value });
    };

    const saveItem = (e) => {
        e.preventDefault();
        InventoryItemService.saveItem(item).then((response) => {
            console.log(response);
        }).catch((error) => {
            console.log(error);
        })
    };

    return (
        <>
            <div>
                <nav className="nav">
                    <ul>
                        <li>
                            <a href='/admin'>Home</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div className="app">
                <h1>Add Item</h1>
                <div className="search">
                    <input type='text'
                        placeholder='Name...'
                        value={item.name}
                        onChange={handleChange}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Description...'
                        value={item.description}
                        onChange={handleChange}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Price...'
                        value={item.price}
                        onChange={handleChange}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Quantity...'
                        value={item.quantity}
                        onChange={handleChange}
                    />
                </div>
                <div className="search">
                    <input type="file"
                        placeholder='Poster...'
                        value={item.poster}
                        onChange={handleChange}
                    />
                    <h1>Item Banner</h1>
                </div>
                <button className="loginButton" onClick={saveItem}>Submit</button>
            </div>
        </>
    )
}

export default AddItem;
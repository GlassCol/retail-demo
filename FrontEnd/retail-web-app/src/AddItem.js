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
        discount: "",
        image: "N/A",
        orderId: 0
    });

    const handleChange = (e) => {
        const value = e.target.value;
        setItem({ ...item, [e.target.name]: value });
    };

    const saveItem = (e) => {
        e.preventDefault();
        InventoryItemService.createInventoryItem(item).then((response) => {
            console.log(response);
        }).catch((error) => {
            console.log(error);
        })
        alert("Item added successfully");
    };

    return (
        <>
            <div>
                <nav className="nav">
                    <ul>
                        <li>
                            <a href='/'>Home</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div className="app">
                <h1>Add Item</h1>
                <div className="search">
                    <input type='text'
                        placeholder='Name...'
                        name='name'
                        value={item.name}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Description...'
                        name='description'
                        value={item.description}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Price...'
                        name='price'
                        value={item.price}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Quantity...'
                        name='quantity'
                        value={item.quantity}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder='Discount...'
                        name='discount'
                        value={item.discount}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="file"
                        placeholder='Banner...'
                        name='image'
                        value={item.image}
                        onChange={(e) => handleChange(e)}
                    />
                    <h1>Item Banner</h1>
                </div>
                <button className="loginButton" onClick={(e) => saveItem(e)}>Submit</button>
            </div>
        </>
    )
}

export default AddItem;
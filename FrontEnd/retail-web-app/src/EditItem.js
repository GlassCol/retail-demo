import React, { useEffect } from 'react';
import { useState } from 'react';
import InventoryItemService from './service/InventoryItemService';
import '../src/css/App.css';
import { useParams } from 'react-router-dom';

const EditItem = () => {
            let { id } = useParams();

    const [item, setItem] = useState({
        id: useParams().id,
        name: "",
        description: "",
        price: "",
        quantity: "",
        discount: "",
        image: "",
        orderId: 0
    });
    const handleChange = (e) => {
        const value = e.target.value;
        setItem({ ...item, [e.target.name]: value });
    };

    const saveItem = (e) => {
        e.preventDefault();
        console.log(item.id)
        InventoryItemService.updateInventoryItem(item, item.id).then((response) => {
            console.log(response);
            alert("Item Updated Successfully");
        }).catch((error) => {
            console.log(error);
        })
    };

    const getItem = () => {
        InventoryItemService.getInventoryItemById(item.id).then((response) => {
            setItem(() => response.data);

        }).catch((error) => {
            console.log(error);
        })
    };
    useEffect(() => {
        getItem();
    }, [id]);
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
                <h1>Edit Item</h1>
                <div className="search">
                    <input type='text'
                        placeholder={item.name}
                        name='name'
                        value={item.name}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder={item.description}
                        name='description'
                        value={item.description}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder={item.price}
                        name='price'
                        value={item.price}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder={item.quantity}
                        name='quantity'
                        value={item.quantity}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="text"
                        placeholder={item.discount}
                        name='discount'
                        value={item.discount}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type="file"
                        placeholder={item.poster}
                        name='poster'
                        value={item.poster}
                        onChange={(e) => handleChange(e)}
                    />
                    <h1>Item Banner</h1>
                </div>
                <button className="loginButton" onClick={saveItem}>Submit</button>
            </div>
        </>
    )
}

export default EditItem;
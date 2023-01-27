import React, { useEffect } from 'react';
import { useState } from 'react';
import InventoryItemService from './service/InventoryItemService';
import searchIcon from './search.svg';
import ItemCard from './component/ItemCard';
import '../src/css/App.css';
import NavBar from './component/NavBar';
import UserService from './service/UserService';



const Home = () => {
    const [items, setItems] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [activeUser, setActiveUser] = useState(UserService.getCurrentUser());

    const searchProduct = async (name) => {

        try {
            const response = await InventoryItemService.getInventoryItemByName(name);
            setItems(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        const getItems = async () => {
            setActiveUser(await UserService.getCurrentUser());
            const response = await InventoryItemService.getAllInventoryItems();
            setItems(response.data);
        }
        getItems();
    }, []);

    return (
        <>
            <NavBar activeUser={activeUser} />
            <div className='app'>

                <h1>Retailer Web</h1>
                <div className="search">
                    <input type='text'
                        placeholder='Search...'
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <img src={searchIcon}
                        alt='search'
                        onClick={() => { searchProduct(searchTerm) }}
                    />
                </div>
                {items?.length > 0 ? (
                    <div className="container">

                        {items.map((item) => (
                            <ItemCard item={item} key={item.id} activeUser={activeUser} />
                        ))}
                    </div>
                ) : (
                    <div className="empty">
                        <h2>No items found</h2>
                    </div>
                )}
            </div>
        </>
    );
}

export default Home;
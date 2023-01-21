import React from 'react';
import { useEffect, useState } from 'react';
import InventoryItemService from './service/InventoryItemService';
import searchIcon from './search.svg';
import ItemCard from './component/ItemCard';
import '../src/css/App.css';
import NavBar from './component/NavBar';
import { useParams } from 'react-router-dom';

const Home = () => {
    const [items, setItems] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    let { activeUser } = useParams();
    const [isLoggedIn, setIsLoggedin] = useState( useParams().activeUser ? true : false);

        const searchProduct = async (name) => {
            const response = await InventoryItemService.getInventoryItemByName(name);
            const data = await response.json();

            setItems(data.Search);
        }

        useEffect(() => {
            searchProduct("test");
        }, []);
        return (
            <>
                <NavBar isLoggedIn={isLoggedIn} activeUser={activeUser}/>
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

                    {items?.length > 0
                        ? (
                            <div className="container">
                                {items.map((item) => (
                                    <ItemCard item={item} />
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
import React, { useEffect } from 'react';
import { useState } from 'react';
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
    const [shoppingCart, setShoppingCart] = useState([]);
    const [loading, setLoading] = useState(false);

        const searchProduct = async (name) => {
            setLoading(true);
            try{
            const response = await InventoryItemService.getInventoryItemByName(name);
            setItems(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        }

        const addToCart = (item) => {
            setShoppingCart((shoppingCart) => [...shoppingCart, item]);
        }
        const removeFromCart = (item) => {
            setShoppingCart(shoppingCart.filter((i) => i !== item));
        }

        useEffect(() => {
            const getItems = async () => {
                setLoading(true);
                const response = await InventoryItemService.getAllInventoryItems();
                setItems(response.data);
                setLoading(false);
            }
            getItems();
        }, []);

            return (
            <>

                <NavBar activeUser={activeUser}/>
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
                                    <ItemCard item={item} key={item.id} addToCart={addToCart} removeFromCart={removeFromCart} activeUser={activeUser} />
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
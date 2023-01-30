import React from "react";
import { useState, useEffect } from "react";
import OrderedItemCard from "./component/OrderedItemCard";
import OrderedItemService from "./service/OrderedItemService";
import OrderService from "./service/OrderService";
import '../src/css/App.css';
const OrderView = () => {
    const [items, setItems] = useState([]);
    const [activeOrder, setActiveOrder] = useState(OrderService.getViewingOrder());
    const [loading, setLoading] = useState(true);
    const getOrder = async () => {
        if (loading) {
            try {
                const response = await OrderService.getViewingOrder();
                setActiveOrder(response.data);
                setLoading(false);
            }
            catch (error) {
                console.log(error);
            }
        }
    }
    const getItems = async () => {
        if (activeOrder.id !== undefined) {
            let itemResponse = await OrderedItemService.getOrderedItemsByOrderId(activeOrder.id);
            return itemResponse
        }
    }


    useEffect(() => {
        getOrder();
    }, [loading]);

    useEffect(() => {
        getItems().then(result => setItems(result.data));
    }, [activeOrder]);



    return (
        <>
            <div>
                <nav className="nav">
                    <ul>
                        <li>
                            <a href='/'>Home</a>
                        </li>
                        <li>
                            <a href='/orders'>Back</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div className='app'>
                <h1>Order # {activeOrder.id}</h1>
                <div className="container">
                    {items?.length > 0 ? (
                        <div className="container">
                            {items.map((item) => (
                                <OrderedItemCard item={item} key={item.id} />
                            ))}
                        </div>
                    ) : (
                        <h3>No items in order</h3>
                    )}
                </div>
            </div>
        </>
    );
}

export default OrderView;
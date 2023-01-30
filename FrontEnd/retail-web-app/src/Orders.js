import React from "react";
import { useState, useEffect } from "react";
import OrderLine from "./component/OrderLine";
import OrderService from "./service/OrderService";
import UserService from "./service/UserService";

const Orders = () => {

    const [orders, setOrders] = useState([]);
    const [activeUser, setActiveUser] = useState([]);
    const [loading, setLoading] = useState(true);

    const getUser = async () => {
        try {
            if(loading){
            const response = await UserService.getCurrentUser();
            setActiveUser(response.data);
                setLoading(false);
            }
        } catch (error) {
            console.log(error);
        }
    }

    const getOrders = async () => {
        try {
            if(activeUser.id !== undefined){
            const response = await OrderService.getOrdersByUserId(activeUser.id);
            setOrders(response.data);
            }
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getUser();
        getOrders();
    }, [loading]);

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
                <h1>Orders</h1>
                {orders?.length > 0 ? (
                    <div className="container">
                        {orders.map((order) => (
                            <div className='orderContainer' key={"div" + order.id}>
                                <OrderLine key={order.id} order={order} />
                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="container">
                        <h2>No previous orders found</h2>
                    </div>
                )}
            </div>
        </>
    )

}

export default Orders;
import React from "react";
import { useNavigate } from "react-router-dom";
import OrderService from "../service/OrderService";
const OrderLine = ({ order }) => {
    
    const navigate = useNavigate();
    const viewOrder = () => {
        OrderService.setViewingOrder(order);
        navigate("/orders/view");
    };
    return (

        <div className="orderLine">
            <div>
            <h2>Order Number: {order.id}</h2>
            <h3>Items Ordered: {order.itemQuantity}</h3>
            <h3>Order Total: ${order.total}</h3>
            <thead>
                <tr>
                    <th scope="col">
                    <button onClick={() => viewOrder()}>
                        View Order
                    </button>
                    </th>
                </tr>
            </thead>
            </div>
        </div>

    );
}

export default OrderLine;
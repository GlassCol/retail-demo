import axios from 'axios';
const API_URL = 'http://localhost:9080/OrderApi/v1/orders';

class OrderService {
    
        getAllOrders() {
            return axios.get(API_URL);
        }
    
        getOrderById(orderId) {
            return axios.get(API_URL + '/' + orderId);
        }

        getOrdersByUserId(userId) {
            return axios.get(API_URL + '/user/' + userId);
        }

        getViewingOrder() {
            return axios.get(API_URL + '/viewOrder');
        }

        setViewingOrder(order) {
            return axios.post(API_URL + '/viewOrder', order);
        }
    
        createOrder(order) {
            return axios.post(API_URL, order);
        }
    
        updateOrder(order, orderId) {
            return axios.put(API_URL + '/' + orderId, order);
        }
    
        deleteOrder(orderId) {
            return axios.delete(API_URL + '/' + orderId);
        }
    }

export default new OrderService()
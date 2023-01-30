import axios from 'axios';
const API_URL = 'http://localhost:9080/OrderedItemApi/v1/orderedItems';

class OrderedItemService {
    
        getAllOrderedItems() {
            return axios.get(API_URL);
        }
    
        getOrderedItemById(orderedItemId) {
            return axios.get(API_URL + '/' + orderedItemId);
        }
    
        getOrderedItemsByOrderId(orderId) {
            return axios.get(API_URL + '/order/' + orderId);
        }
    
        createOrderedItem(orderedItem) {
            return axios.post(API_URL, orderedItem);
        }
    
        updateOrderedItem(orderedItem, orderedItemId) {
            return axios.put(API_URL + '/' + orderedItemId, orderedItem);
        }
    
        deleteOrderedItem(orderedItemId) {
            return axios.delete(API_URL + '/' + orderedItemId);
        }
}

export default new OrderedItemService()
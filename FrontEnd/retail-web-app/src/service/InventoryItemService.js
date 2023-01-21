import axios from "axios";
const API_URL = 'http://localhost:9080/inventoryItemApi/v1/inventoryItems';

class InventoryItemService {

    getAllInventoryItems() {
        return axios.get(API_URL);
    }

    getInventoryItemById(inventoryItemId) {
        return axios.get(API_URL + '/' + inventoryItemId);
    }

    getInventoryItemByName(inventoryItemName) {
        return axios.get(API_URL + '/byName/' + inventoryItemName);
    }

    createInventoryItem(inventoryItem) {
        return axios.post(API_URL, inventoryItem);
    }

    updateInventoryItem(inventoryItem, inventoryItemId) {
        return axios.put(API_URL + '/' + inventoryItemId, inventoryItem);
    }

    deleteInventoryItem(inventoryItemId) {
        return axios.delete(API_URL + '/' + inventoryItemId);
    }
}

export default new InventoryItemService();
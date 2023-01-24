import axios from "axios";
const CART_API_URL = 'http://localhost:9080/ShoppingCartApi/v1/shoppingCart';

class ShoppingCartService {

    addToShoppingCart(item) {
        return axios.post(CART_API_URL, item);
    }

    getAllShoppingCartItems() {
        return axios.get(CART_API_URL);
    }

    deleteFromShoppingCart(itemId) {
        return axios.delete(CART_API_URL + '/' + itemId);
    }

    clearShoppingCart() {
        return axios.delete(CART_API_URL);
    }
}

export default new ShoppingCartService();
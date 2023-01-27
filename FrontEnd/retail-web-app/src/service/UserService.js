import axios from "axios";
const USER_API_URL = 'http://localhost:9080/userApi/v1/user';

class UserService {
        createUser(user) {
            return axios.post(USER_API_URL, user);
        }

        getCurrentUser() {
            return axios.get('http://localhost:9080/userApi/v1/currentUser');
        }

        checkLogin(userName, password) {
            return axios.get(USER_API_URL + '/' + userName + '/' + password);
        }
    
        getUser(userName) {
            return axios.get(USER_API_URL + '/' + userName);
        }

        setCurrentUser(user) {
            console.log(user);
            return axios.post(USER_API_URL + '/currentUser', user);
        }

        deleteUser(userId) {
            return axios.delete(USER_API_URL + '/' + userId);
        }
        resetUser() {
            return axios.post(USER_API_URL + '/reset');
        }
}

export default new UserService();
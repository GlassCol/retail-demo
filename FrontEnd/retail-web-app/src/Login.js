import { useEffect, useState } from "react";
import '../src/css/App.css';
import { useNavigate } from 'react-router-dom';
import NavBar from './component/NavBar';
import UserService from "./service/UserService";

const Login = () => {
    const navigate = useNavigate();
    const [activeUser, setActiveUser] = useState(UserService.getCurrentUser());

    const [username, setUserName] = useState('');
    const [password, setPassword] = useState('');

    const login = async () => {
        if (username === '' || password === '') {
            alert('Username and Password May Not Be Blank');
        } else {
            if ( (await UserService.checkLogin(username, password)).data === true) {
                console.log(await UserService.getUser(username));
                let user = await UserService.getUser(username);
                console.log(user.data);
                await UserService.setCurrentUser(user.data);
                console.log(await UserService.getCurrentUser());
                navigate('/');
            } else {
                alert('Invalid credentials');
            }
        }
    }

    useEffect(() => {
        const fetchActiveUser = async () => {
            setActiveUser(await UserService.getCurrentUser());
        }
        fetchActiveUser();
    }, []);

    return (
        <>
            <NavBar activeUser={activeUser} />
            <div className='app'>
                <h1>Login</h1>
                <div className="search">
                    <input type='text'
                        placeholder='Username'
                        value={username}
                        onChange={(e) => setUserName(e.target.value)}
                    />
                </div>
                <div className="search">
                    <input type='password'
                        placeholder='Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button className='loginButton' onClick={login}>Login</button>
            </div>
        </>
    )
}

export default Login;
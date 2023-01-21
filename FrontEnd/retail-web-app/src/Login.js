import { useState } from "react";
import '../src/css/App.css';
import { useNavigate } from 'react-router-dom';
import NavBar from './component/NavBar';

const Login = ({ toggleIsLoggedIn, isLoggedIn, logout, activeUser }) => {
    const navigate = useNavigate();

const [username, setUserName] = useState('');
const [password, setPassword] = useState('');

    const login = () => {
        if ((username === 'admin' && password === 'admin') ||
            (username === 'user' && password === 'user')) {
            navigate('/' + username);
        } else {
            alert('Invalid credentials');
        }
    }
    return (
        <>
            <NavBar isLoggedIn={isLoggedIn} activeUser={activeUser} />
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
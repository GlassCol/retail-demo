import { useState, useEffect } from "react";
import '../src/css/App.css';
import { useNavigate } from 'react-router-dom';
import NavBar from './component/NavBar';
import UserService from './service/UserService';

const CreateUser = () => {
    const navigate = useNavigate();
    const [activeUser, setActiveUser] = useState(UserService.getCurrentUser());

    const [user, setUser] = useState({
        username: '',
        password: ''
    });

    const createUser = async () => {
        if (user.username !== '' && user.password !== '') {
            if (await UserService.checkLogin(user.username, user.password) !== null) {
                await UserService.createUser(user);
                await UserService.setCurrentUser(await UserService.getUser(user.username));
                alert("User Created");
                navigate('/');
            } else {
                alert('Username Already Taken');
            }
        } else {
            alert('Username and Password May Not Be Blank');
        }
    }

    const handleChange = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

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
                <h1>Create an Account</h1>
                <div className="search">
                    <input type='text'
                        placeholder='Username'
                        value={user.username}
                        name='username'
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <div className="search">
                    <input type='password'
                        placeholder='Password'
                        value={user.password}
                        name='password'
                        onChange={(e) => handleChange(e)}
                    />
                </div>
                <button className='loginButton' onClick={createUser}>Submit</button>
            </div>
        </>
    )
}

export default CreateUser;
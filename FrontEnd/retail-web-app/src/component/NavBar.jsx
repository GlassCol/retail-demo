import React from 'react'
import UserService from '../service/UserService'

function NavBar({ activeUser }) {
    return (
        <div>
            <nav className="nav">
                <ul>
                    <li>
                        <a href={'/'}>Home</a>
                    </li>
                    {activeUser.data === '' ? (<></>) : (
                        <li>
                            <a href='/'>Orders</a>
                        </li>)}
                    {activeUser.data === '' ? (<></>) : (
                        <li>
                            <a href={'/ShoppingCart'}>Shopping Cart</a>
                        </li>)}
                        {console.log(activeUser.data)}
                        {activeUser.data === undefined ? (<></>) : (
                            activeUser.data.username === 'admin' ? (
                        <li>
                            <a href='/AddItem'>Add New Item</a>
                        </li>
                    ) : (<></>)
                        )}
                    {activeUser.data === '' ? (
                        <li>
                            <a href='/Login'>Login</a>
                        </li>
                    ) : (
                        <li>
                            <a href='/' onClick={UserService.resetUser}>Logout</a>
                        </li>
                    )}
                    {activeUser.data === '' ? (
                    <li>
                        <a href='/createUser'>Register</a>
                    </li>
                    ) : (<></>)}
                </ul>
            </nav>
        </div>
    )
}

export default NavBar
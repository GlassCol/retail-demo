import React from 'react'


function NavBar({ isLoggedIn, logout, activeUser }) {
    return (
        <div>
            <nav className="nav">
                <ul>
                    <li>
                        <a href='/'>Home</a>
                    </li>
                    {isLoggedIn ? (
                        <li>
                            <a href='/'>Orders</a>
                        </li>
                    ) : (<></>)}
                    {isLoggedIn ? (
                        <li>
                            <a href='/'>Shopping Cart</a>
                        </li>
                    ) : (<></>)}
                    {activeUser === 'admin' ? (
                        <li>
                            <a href='/AddItem'>Add New Item</a>
                        </li>
                    ) : (<></>)}
                    {isLoggedIn ? (
                        <li>
                            <a href='/'>Logout</a>
                        </li>
                    ) : (
                        <li>
                            <a href='/Login'>Login</a>
                        </li>)}
                </ul>
            </nav>
        </div>
    )
}

export default NavBar
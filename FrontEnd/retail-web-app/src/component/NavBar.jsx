import React from 'react'


function NavBar({ activeUser }) {
    return (
        <div>
            <nav className="nav">
                <ul>
                    <li>
                        <a href='/'>Home</a>
                    </li>
                    {activeUser === undefined ? (<></>) : (
                        <li>
                            <a href='/'>Orders</a>
                        </li>)}
                    {activeUser === undefined ? (<></>) : (
                        <li>
                            <a href='/'>Shopping Cart</a>
                        </li>)}
                    {activeUser === 'admin' ? (
                        <li>
                            <a href='/AddItem'>Add New Item</a>
                        </li>
                    ) : (<></>)}
                    {activeUser === undefined ? (
                        <li>
                            <a href='/Login'>Login</a>
                        </li>
                    ) : (
                        <li>
                            <a href='/'>Logout</a>
                        </li>
                    )}
                </ul>
            </nav>
        </div>
    )
}

export default NavBar
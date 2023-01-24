import React from 'react'
import ShoppingCartService from '../service/ShoppingCartService'
import { Link } from 'react-router-dom'

function NavBar({ activeUser }) {
    return (
        <div>
            <nav className="nav">
                <ul>
                    <li>
                        <a href={'/' + activeUser}>Home</a>
                    </li>
                    {activeUser === undefined ? (<></>) : (
                        <li>
                            <a href='/'>Orders</a>
                        </li>)}
                    {activeUser === undefined ? (<></>) : (
                        <li>
                            <Link to={`/ShoppingCart/${activeUser}`}>Shopping Cart</Link>
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
                            <a href='/' onClick={ShoppingCartService.clearShoppingCart}>Logout</a>
                        </li>
                    )}
                </ul>
            </nav>
        </div>
    )
}

export default NavBar
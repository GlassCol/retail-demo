import React from "react";
import Home from "./Home";
import AddItem from "./AddItem";
import EditItem from "./EditItem";
import Login from "./Login";
import ShoppingCart from "./ShoppingCart";
import CreateUser from "./CreateUser";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="*" element={<h1>404: Not Found</h1>} />
        <Route path="/login" element={<Login />} />
        <Route path="/addItem" element={<AddItem />} />
        <Route path="/editItem/:id" element={<EditItem />} />
        <Route path="/shoppingCart/" element={<ShoppingCart />} />
        <Route path="/createUser" element={<CreateUser />} />
      </Routes>
    </Router>
  );
}

export default App;
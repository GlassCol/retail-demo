import React from "react";
import Home from "./Home";
import AddItem from "./AddItem";
import Login from "./Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/:activeUser" element={<Home />} />
        <Route path="*" element={<h1>404: Not Found</h1>} />
        <Route path="/login" element={<Login />} />
        <Route path="/addItem" element={<AddItem />} />
      </Routes>
    </Router>
  );
}

export default App;
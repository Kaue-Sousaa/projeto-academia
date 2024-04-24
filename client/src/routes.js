import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from "./pages/Login";
import Initial from "./pages/Initial";
import Obout from "./pages/Obout";
import Register from "./pages/CadastroUsuario";
import Home from "./pages/Home";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Initial />} />
        <Route path="/login" element={<Login />} />
        <Route path="/obout" element={<Obout />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}

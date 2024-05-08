import React from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Initial from "./pages/Initial";
import About from "./pages/About";
import Register from "./pages/CadastroUsuario";
import Home from "./pages/Home";
import Treino from "./pages/CadastroTreinoAluno";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Initial />} />
        <Route path="/login" element={<Login />} />
        <Route path="/about" element={<About />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/treino-aluno" element={<Treino />} />
        <Route path="*"  element={<Navigate to="/" />}/>
      </Routes>
    </BrowserRouter>
  );
}

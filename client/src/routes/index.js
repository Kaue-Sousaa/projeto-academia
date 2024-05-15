import React, { Suspense } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "../pages/Login";
import Initial from "../pages/Initial";
import About from "../pages/About";
import Register from "../pages/CadastroUsuario";
import Home from "../pages/Home";
import Treino from "../pages/CadastroTreinoAluno";
import PrivateRoute from "./Route";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Suspense fallback={<h1>Carregando...</h1>}></Suspense>
      <Routes>
        <Route path="/" exact element={<Initial />} />
        <Route path="/login" element={<Login />} />
        <Route path="/about" element={<About />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/home"
          element={
            <PrivateRoute>
              <Home />
            </PrivateRoute>
          }
        />
        <Route path="/treino-aluno" element={<Treino />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

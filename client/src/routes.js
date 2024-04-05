import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Login from './pages/Login'
import Home from './pages/Home'
import Obout from "./pages/Obout";

export default function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={<Home/>}/>
                <Route path="/login"  element={<Login/>}/>
                <Route path="/obout" element={<Obout/>}/>
            </Routes>
        </BrowserRouter>
    );
}
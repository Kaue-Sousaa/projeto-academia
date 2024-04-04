import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Login from './pages/Login'
import Home from './pages/Home'

export default function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={<Login/>}/>
                <Route path="/home" element={<Home/>}/>
            </Routes>
        </BrowserRouter>
    );
}
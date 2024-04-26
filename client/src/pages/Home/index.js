import React from "react";
import { Calendar } from "rsuite";

import "rsuite/Calendar/styles/index.css";
import Head from "../../component/Header";
import Footer from "../../component/Footer";

import "./styles.css";

export default function Home() {
  const user = localStorage.getItem("sessionName");
  return (
    <div className="home-container">
      <Head name={user}></Head>
      <div className="calendar-container">
        <h1>Seja Bem Vindo</h1>
        <Calendar />
      </div>
      <Footer></Footer>
    </div>
  );
}

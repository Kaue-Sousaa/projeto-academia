import React from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";

import Head from "../../component/Header";
import Footer from "../../component/Footer";

import "./styles.css";

export default function Home() {
  const user = localStorage.getItem("sessionName");
  return (
    <div className="home-container">
      <Head name={user}></Head>
      <h1>Seja Bem Vindo</h1>
      <div className="calendar-container">
        {/*  <Calendar /> */}
        <Fullcalendar
          plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
          initialView={"dayGridMonth"}
          headerToolbar={{
            start: "today prev,next",
            center: "title",
            translation: "português",
            end: "dayGridMonth,timeGridWeek,timeGridDay",
          }}
          locale={ptBrLocale}
          height={"100vh"}
        />
      </div>
      <Footer></Footer>
    </div>
  );
}

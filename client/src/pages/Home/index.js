import React, { useState } from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";
import { Modal, Col, Row } from "react-bootstrap";

import Head from "../../component/Header";
import Footer from "../../component/Footer";
import "./styles.css";

export default function Home() {
  const user = localStorage.getItem("sessionName");
  const [showModal, setShowModal] = useState(false);
  const [aula, setAula] = useState(null);

  function renderEventContent(eventInfo) {
    setAula({
      title: eventInfo?.event?._def?.title,
      id: eventInfo?.event?._def?.publicId,
      video: urlVideos[eventInfo?.event?._def?.publicId],
      descricao: eventInfo?.event?._def?.extendedProps?.descricao,
    });

    setShowModal(!showModal);
  }

  const urlVideos = {
    SUPERIORES: "https://www.youtube.com/embed/5zrLskUH9dk?si=Z3rY_FWU2FPeNKSa",
    INFERIORES: "https://www.youtube.com/embed/gbzlzNoxPXs?si=8p06I5NsPSlTA--W",
  };

  return (
    <div className="principal">
      <Head name={user}></Head>
      <div className="home-container">
        <h1>Seja Bem Vindo</h1>
        <div className="calendar-container">
          <Fullcalendar
            height={"60vh"}
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            initialView={"dayGridMonth"}
            headerToolbar={{
              start: "",
              center: "title",
            }}
            locale={ptBrLocale}
            eventClick={renderEventContent}
            events={[
              {
                id: "SUPERIORES",
                title: "Aula de superiores",
                date: "2024-04-29",
                descricao: "addasdsdsadasdasdsadsa",
              },
              {
                id: "INFERIORES",
                title: "Aula de inferiores",
                date: "2024-04-29",
                descricao: "addasdsdsadasdasdsadsa",
              },
            ]}
          />
        </div>
        <Footer></Footer>
      </div>
      <Modal
        show={showModal}
        onHide={() => {
          setShowModal(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>{aula?.title}:</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ display: "flex", justifyContent: "center" }}>
          <Row>
            <Col>
              <iframe
                width="560"
                height="315"
                src={aula?.video}
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin"
                allowfullscreen
              ></iframe>
            </Col>
            <Col>
              <p style={{ color: "#333" }}>{aula?.descricao}</p>
            </Col>
          </Row>
        </Modal.Body>
      </Modal>
    </div>
  );
}

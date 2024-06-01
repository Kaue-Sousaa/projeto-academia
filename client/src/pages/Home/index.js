import React, { useEffect, useState } from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";
import { Modal, Col, Row } from "react-bootstrap";

import Head from "../../component/Header";
import Footer from "../../component/Footer";
import moment from "moment";
import "./styles.css";
import api from "../../services/api";

export default function Home() {
  const user = localStorage.getItem("sessionName");
  const [showModal, setShowModal] = useState(false);
  const [dados, setDados] = useState();
  const [events, setEvents] = useState([]);
  const [treino, setTreino] = useState(null);

  useEffect(() => {
    const usuario = localStorage.getItem("ACAD");
    getTreinoAlunoPorEmail(JSON.parse(usuario)?.email);
  }, []);

  const getTreinoAlunoPorEmail = async (email) => {
    const response = await api.get(`/treino/aluno?email=${email}`);
    if (response?.status === 200) {
      const treinosData = response?.data.map((treino) => {
        const data = treino?.horario
          ?.split(" ")[0]
          ?.split("/")
          ?.reverse()
          ?.join("-");

        return {
          id: treino?.id,
          title: treino?.nomeAluno,
          date: data,
        };
      });
      setTreino(treinosData);
    }
  };

  function renderEventContent(eventInfo) {
    setTreino({
      ...treino,
    });

    setShowModal(!showModal);
  }

  const urlVideos = {
    SUPERIORES: "https://www.youtube.com/embed/5zrLskUH9dk?si=Z3rY_FWU2FPeNKSa",
    INFERIORES: "https://www.youtube.com/embed/gbzlzNoxPXs?si=8p06I5NsPSlTA--W",
  };

  return (
    <div className="principal">
      <Head name={user?.nomeAluno}></Head>
      <div className="home-container">
        <h1>Seja Bem Vindo</h1>
        <div className="calendar-container">
          <Fullcalendar
            height={"60vh"}
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            initialView={"dayGridMonth"}
            headerToolbar={{
              start: "today",
              center: "title",
              end: "prev,next",
            }}
            locale={ptBrLocale}
            eventClick={renderEventContent}
            events={treino}
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
          <Modal.Title>{treino?.title}:</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ display: "flex", justifyContent: "center" }}>
          <Row>
            <Col>
              <iframe
                width="560"
                height="315"
                src={treino?.video}
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin"
                allowfullscreen
              ></iframe>
            </Col>
            <Col>
              <p style={{ color: "#333" }}>{treino?.descricao}</p>
            </Col>
          </Row>
        </Modal.Body>
      </Modal>
    </div>
  );
}

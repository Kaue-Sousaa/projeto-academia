import React, { useEffect, useState } from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";
import { Modal, Col, Row } from "react-bootstrap";

import Head from "../../component/Header";
import Footer from "../../component/Footer";
import Tabela from "./../../component/Table";
import TimePicker from "react-bootstrap-time-picker";

import "./styles.css";
import api from "../../services/api";

export default function Home() {
  const user = localStorage.getItem("sessionName");
  const [showModal, setShowModal] = useState(false);
  const [dados, setDados] = useState([]);
  const [events, setEvents] = useState([]);
  const [treino, setTreino] = useState(null);

  useEffect(() => {
    const usuario = localStorage.getItem("ACAD");
    if (usuario) {
      getTreinoAlunoPorEmail(JSON.parse(usuario)?.email);
    }
  }, []);

  const colunas = ["Nome", "Data", "Horário", "Treino"];

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
          ...treino,
        };
      });
      setEvents(treinosData);
    }
  };

  const buscarTreinoPorId = async (id) => {
    const response = await api.get(`/treino/${id}`);
    if (response.status === 200) {
      const treinoAluno = response.data;
      const subcategoriaResponse = await api.get(
        `/subCategoria/${treinoAluno.subCategoria}`
      );
      const subCategoria = subcategoriaResponse?.data?.subCategoria;

      setTreino({
        id: treinoAluno?.id,
        nomeAluno: treinoAluno?.nomeAluno,
        descricao: treinoAluno?.descricao,
        horario: treinoAluno?.horario,
        treino: subCategoria,
      });

      setDados([
        {
          id: treinoAluno?.id,
          nome: treinoAluno?.nomeAluno,
          data: treinoAluno?.horario?.split(" ")[0],
          horario: (
            <TimePicker disabled value={treinoAluno?.horario?.split(" ")[1]} />
          ),
          treino: subCategoria,
        },
      ]);
    }
  };

  function renderEventContent(eventInfo) {
    const treinoId = eventInfo.event.id;
    buscarTreinoPorId(treinoId);
    setShowModal(true);
  }

  const urlVideos = {
    QUADRÍCEPS: "https://www.youtube.com/embed/gbzlzNoxPXs?si=8p06I5NsPSlTA--W",
    PEITO: "https://www.youtube.com/embed/Snrsab_agjc",
    POSTERIOR: "https://www.youtube.com/embed/lGL7iCbAgRQ",
    ABDÔMEN: "https://www.youtube.com/embed/NIcnysLjTPU",
    GLUTEO: "https://www.youtube.com/embed/232THOS5Xjo",
    TRÍCEPS: "https://www.youtube.com/embed/uH3Aa6iX_lo",
    BÍCEPS: "https://www.youtube.com/embed/_b5Zun1ylCY",
    OMBRO: "https://www.youtube.com/embed/CEI_jdo6e3U",
    TRAPÉZIO: "https://www.youtube.com/embed/eo0yWjMvS84",
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
            events={events}
          />
        </div>
        <Footer></Footer>
      </div>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>
            Treinos do dia: {treino?.horario?.split(" ")[0]}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ display: "flex", justifyContent: "center" }}>
          <Row>
            <Col>
              {treino?.treino && urlVideos[treino.treino.toUpperCase()] ? (
                <iframe
                  width="560"
                  height="315"
                  src={urlVideos[treino.treino.toUpperCase()]}
                  title="YouTube video player"
                  frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                  referrerpolicy="strict-origin-when-cross-origin"
                  allowfullscreen
                ></iframe>
              ) : (
                <p>Vídeo não disponível</p>
              )}
            </Col>
            <Col>
              <p style={{ color: "#333" }}>{treino?.treino}</p>
            </Col>
          </Row>
        </Modal.Body>
      </Modal>
    </div>
  );
}

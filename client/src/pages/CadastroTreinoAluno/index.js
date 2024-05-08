import React, { useState } from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";
import { Modal, Col, Row, Form, Button } from "react-bootstrap";
import TimePicker from "react-bootstrap-time-picker";
import Tabela from "./../../component/Table";

import Head from "../../component/Header";
import Footer from "../../component/Footer";
import "./styles.css";
import moment from "moment";

export default function CadastrarTreinoAluno() {
  const user = localStorage.getItem("sessionName");
  const [showModalRegister, setShowModalRegister] = useState(false);
  const [showModalUserList, setShowModalUserList] = useState(false);
  const [form, setForm] = useState({
    aluno: "",
    horario: "",
    categoria: "",
    subcat: "",
  });
  const [date, setDate] = useState(null);
  const [time, setTime] = useState(0);

  const handleTimeChange = (time) => {
    setTime(time);
    setForm({
      ...form,
      horario: time,
    });
  };

  function renderEventContent(eventInfo) {
    setDate(moment(eventInfo?.date).format("DD/MM/YYYY"));

    if (eventInfo?.jsEvent?.srcElement?.childElementCount === 1) {
      setShowModalRegister(true);
    } else {
      setShowModalUserList(true);
    }
  }

  const handleForm = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (values) => {
    values.preventDefault();
    console.log("values: ", form);
  };

  const [dados, setDados] = useState([
    { id: 1, nome: "João", horario: 30 },
    { id: 2, nome: "Maria", horario: 25 },
    { id: 3, nome: "José", horario: 40 },
  ]);

  const colunas = ["ID", "Nome", "Horário"];

  const handleRemove = (index) => {
    const novosDados = [...dados];
    novosDados.splice(index, 1);
    setDados(novosDados);
  };

  return (
    <div className="principal">
      <Head name={user}></Head>
      <div className="home-container">
        <h1>Cadastro de treino</h1>
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
            dateClick={renderEventContent}
            event
            events={[
              {
                id: "id_022",
                title: "Aluno 98",
                date: "2024-04-30",
              },
              {
                id: "id_0225",
                title: "Aluno 09",
                date: "2024-04-29",
              },
            ]}
          />
        </div>
        <Footer></Footer>
      </div>
      <Modal
        show={showModalRegister}
        onHide={() => {
          setShowModalRegister(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Adicionar treino: {date}</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ display: "flex", justifyContent: "center" }}>
          <Form className="form-dados" onSubmit={handleSubmit}>
            <Row>
              <Form.Label>Aluno: </Form.Label>
              <Form.Select
                size="lg"
                name="aluno"
                onChange={(e) => handleForm(e)}
              >
                <option value={"id_1"}>Aluno A</option>
                <option value={"id_2"}>Aluno B</option>
                <option value={"id_3"}>Aluno C</option>
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Categoria: </Form.Label>
              <Form.Select
                size="lg"
                name="categoria"
                onChange={(e) => handleForm(e)}
              >
                <option value={"id_1"}>Categoria A</option>
                <option value={"id_2"}>Categoria B</option>
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Sub-Categoria: </Form.Label>
              <Form.Select
                size="lg"
                name="subcat"
                onChange={(e) => handleForm(e)}
              >
                <option value={"id_1"}>Treino A</option>
                <option value={"id_2"}>Treino B</option>
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Horário: </Form.Label>
              <TimePicker
                name="horario"
                onChange={handleTimeChange}
                className="form-select form-select-lg"
                start="04:00"
                end="22:00"
                step={60}
                value={time}
              />
            </Row>
            <Button className="button-registro" variant="primary" type="submit">
              Adicionar
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
      <Modal
        show={showModalUserList}
        onHide={() => {
          setShowModalUserList(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Lista de alunos: {date}</Modal.Title>
        </Modal.Header>
        <Modal.Body
          style={{
            display: "flex",
            justifyContent: "center",
            padding: "1rem 5rem !important",
          }}
        >
          <Tabela colunas={colunas} data={dados} onRemove={handleRemove} />
        </Modal.Body>
      </Modal>
    </div>
  );
}

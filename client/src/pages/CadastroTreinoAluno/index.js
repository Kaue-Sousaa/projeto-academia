import React, { useState, useEffect } from "react";
import Fullcalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import ptBrLocale from "@fullcalendar/core/locales/pt-br";
import { Modal, Row, Form, Button } from "react-bootstrap";
import TimePicker from "react-bootstrap-time-picker";
import Tabela from "./../../component/Table";

import Head from "../../component/Header";
import Footer from "../../component/Footer";
import "./styles.css";
import moment from "moment";
import api from "../../services/api";

export default function CadastrarTreinoAluno() {
  const user = localStorage.getItem("sessionName");
  const [showModalRegister, setShowModalRegister] = useState(false);
  const [showModalUserList, setShowModalUserList] = useState(false);
  const [form, setForm] = useState({
    aluno: "0",
    categoria: "0",
    subCategoria: "0",
    horario: "0",
  });
  const [date, setDate] = useState(null);
  const [time, setTime] = useState(0);

  const [alunos, setAlunos] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [subCategorias, setSubCategorias] = useState([]);

  useEffect(() => {
    buscarTodasSubCategorias();
    buscarTodasCategorias();
    getAllAluno();
  }, []);

  const buscarTodasCategorias = async () => {
    const response = await api.get("/categoria");
    if (response?.status !== 200) {
      console.log("nenhuma categoria encontrada");
    } else {
      setCategorias(response?.data);
      console.log(response?.data);
    }
  };

  const buscarTodasSubCategorias = async () => {
    const response = await api.get("/subCategoria");
    if (response?.status !== 200) {
      console.log("nenhuma sub-categoria encontrada");
    } else {
      setSubCategorias(response?.data);
      console.log(response?.data);
    }
  };

  const getAllAluno = async () => {
    const response = await api.get("usuario");
    if (response?.status !== 200) {
      console.log("Nenhum aluno encontrado!");
    } else {
      setAlunos(response?.data);
      console.log(response?.data);
    }
  };

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

  const handleSubmit = async (values) => {
    values.preventDefault();
    console.log(form);
    try {
      const response = await api.post("/treino/cadastro", form);
      if (response?.status === 200) {
        console.log(response?.data);
      }
    } catch (error) {
      console.log(error);
    }
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
                <option key={"0"} value={"0"}>
                  Selecione
                </option>
                {alunos?.map((aluno) => (
                  <option key={aluno?.nome} value={aluno?.id}>
                    {aluno?.nome} {aluno?.sobreNome}
                  </option>
                ))}
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Categoria: </Form.Label>
              <Form.Select
                size="lg"
                name="categoria"
                onChange={(e) => handleForm(e)}
              >
                <option key={"0"} value={"0"}>
                  Selecione
                </option>
                {categorias?.map((categoria) => (
                  <option key={categoria?.categoria} value={categoria?.id}>
                    {categoria?.categoria}
                  </option>
                ))}
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Sub-Categoria: </Form.Label>
              <Form.Select
                size="lg"
                name="subCategoria"
                onChange={(e) => handleForm(e)}
              >
                <option key={"0"} value={"0"}>
                  Selecione
                </option>
                {subCategorias?.map((subCategoria) => (
                  <option
                    key={subCategoria?.subCategoria}
                    value={subCategoria?.id}
                  >
                    {subCategoria?.subCategoria}
                  </option>
                ))}
              </Form.Select>
            </Row>
            <Row>
              <Form.Label>Horário: </Form.Label>
              <TimePicker
                name="horario"
                onChange={handleTimeChange}
                className="form-select form-select-lg"
                start="05:00"
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

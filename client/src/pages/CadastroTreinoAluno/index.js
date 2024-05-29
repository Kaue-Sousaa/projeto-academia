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
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [idToRemove, setIdToRemove] = useState(null);
  const [form, setForm] = useState({
    nomeAluno: "0",
    categoria: "0",
    subCategoria: "0",
    horario: "0",
  });
  const [date, setDate] = useState(null);
  const [time, setTime] = useState(0);
  const [dados, setDados] = useState([]);

  const [alunos, setAlunos] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [subCategorias, setSubCategorias] = useState([]);
  const [treinos, setTreinos] = useState([]);

  useEffect(() => {
    buscarTodosTreino();
    buscarTodasSubCategorias();
    buscarTodasCategorias();
    getAllAluno();
  }, []);

  const buscarTodosTreino = async () => {
    const response = await api.get("/treino");
    if (response?.status !== 200) {
      console.log("nenhum treino encontrado");
    } else {
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
      setTreinos(treinosData);
    }
  };

  const buscarTodasCategorias = async () => {
    const response = await api.get("/categoria");
    if (response?.status !== 200) {
      console.log("nenhuma categoria encontrada");
    } else {
      setCategorias(response?.data);
    }
  };

  const buscarTodasSubCategorias = async () => {
    const response = await api.get("/subCategoria");
    if (response?.status !== 200) {
      console.log("nenhuma sub-categoria encontrada");
    } else {
      setSubCategorias(response?.data);
    }
  };

  const getAllAluno = async () => {
    const response = await api.get("usuario");
    if (response?.status !== 200) {
      console.log("Nenhum aluno encontrado!");
    } else {
      setAlunos(response?.data);
    }
  };

  const handleTimeChange = (time) => {
    setTime(time);
    setForm({
      ...form,
      horario: `${date}  ${time}`,
    });
  };

  function renderEventContent(eventInfo) {
    setDate(moment(eventInfo?.date).format("DD/MM/YYYY"));
    setForm({
      ...form,
      date: moment(eventInfo?.date).format("DD/MM/YYYY"),
    });
    if (eventInfo?.jsEvent?.srcElement?.childElementCount === 1) {
      setShowModalRegister(true);
    } else {
      getAlunoForData(moment(eventInfo?.date).format("DD-MM-YYYY"));
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
    try {
      const response = await api.post("/treino/cadastro", form);
      if (response?.status === 200) {
        buscarTodosTreino();
        setShowModalRegister(false);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const colunas = ["Nome", "Data", "Horário", "Treino"];

  const getAlunoForData = async (dia) => {
    try {
      const response = await api.get(`/treino/alunos/${dia}`);
      if (response?.status !== 200) {
        console.log("Nenhum aluno encontrado!");
      } else {
        const nomeSubCatPromises = response?.data?.map(async (alunoDay) => {
          const subCategoria = await getSubCategoriaPorId(
            alunoDay?.subCategoria
          );
          return {
            ...alunoDay,
            nomeSubCategoria: subCategoria?.subCategoria,
          };
        });

        const alunosDataComSubCat = await Promise.all(nomeSubCatPromises);

        let alunosData = alunosDataComSubCat?.map((alunoDay) => ({
          id: alunoDay?.id,
          nome: alunoDay?.nomeAluno,
          data: alunoDay?.horario?.split(" ")[0],
          horario: (
            <>
              <TimePicker disabled value={alunoDay?.horario?.split(" ")[1]} />
            </>
          ),
          treino: alunoDay?.nomeSubCategoria,
        }));

        setDados(alunosData);
        setShowModalUserList(true);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const getSubCategoriaPorId = async (id) => {
    try {
      const response = await api.get(`/subCategoria/${id}`);
      if (response?.status !== 200) {
        console.log("Nenhuma sub-categoria encontrada");
        return null;
      }
      return response?.data;
    } catch (error) {
      console.log(error);
      return null;
    }
  };

  const handleRemove = async (id) => {
    setShowConfirmationModal(true);
    setIdToRemove(id);
  };

  const confirmExclusion = async () => {
    try {
      await api.delete(`/treino/${idToRemove}`);
      const novosDados = dados.filter((item) => item.id !== idToRemove);
      setDados(novosDados);
      buscarTodosTreino();
      setShowConfirmationModal(false);
    } catch (error) {
      console.error("Erro ao excluir treino:", error);
    }
  };

  const cancelExclusion = () => {
    setShowConfirmationModal(false);
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
            events={treinos}
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
                name="nomeAluno"
                onChange={(e) => handleForm(e)}
              >
                <option key={"0"} value={"0"}>
                  Selecione
                </option>
                {alunos?.map((aluno) => (
                  <option
                    key={aluno?.id}
                    value={aluno?.nome + " " + aluno?.sobreNome}
                  >
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
          <Tabela
            colunas={colunas}
            data={dados}
            onRemove={(id) => handleRemove(id)}
          />
          <Modal
            show={showConfirmationModal}
            onHide={cancelExclusion}
            keyboard={false}
          >
            <Modal.Header closeButton>
              <Modal.Title>Confirmação</Modal.Title>
            </Modal.Header>
            <Modal.Body>Tem certeza que deseja excluir?</Modal.Body>
            <Modal.Footer>
              <Button variant="primary" onClick={confirmExclusion}>
                Confirmar
              </Button>
              <Button variant="secondary" onClick={cancelExclusion}>
                Cancelar
              </Button>
            </Modal.Footer>
          </Modal>
        </Modal.Body>
      </Modal>
    </div>
  );
}

import React, { useEffect, useState } from "react";
import { Form, Button, Col, Row, Modal } from "react-bootstrap";

import api from "../../services/api";

import Header from "../../component/Header";
import Footer from "../../component/Footer";

import "./styles.css";
import { useLocation, useNavigate } from "react-router-dom";

export default function RegistroUsuario() {
  const [formData, setFormData] = useState({
    nome: "",
    sobreNome: "",
    email: "",
    cpf: "",
    dataNascimento: "",
    telefone: "",
    senha: "",
    confirmarSenha: "",
    genero: "",
    role: "USER",
  });

  const handleFormEdit = (event, name) => {
    setFormData({
      ...formData,
      [name]: event.target.value,
    });
  };

  const navigate = useNavigate();
  const handleForm = async (event) => {
    try {
      event.preventDefault();
      const response = await api.post("/usuario/cadastro", formData);
      if (response.status === 200) {
        navigate("/login", {});
      }
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const [showModal, setShowModal] = useState(false);

  const [plano, setPlano] = useState(null);

  const [status, setStatus] = useState([
    {
      tipo: "Plano Mensal",
      value: "R$ 60,00",
      descricao: [
        "Segunda a Sábado",
        "2 Horas Diárias",
        "Todos os equipamentos",
      ],
    },

    {
      tipo: "Plano Trimestral",
      value: "R$ 40,00",
      descricao: [
        "Segunda-feira",
        "Dieta de um plano",
        "4 Horas Diárias",
        "Todos os equipamentos",
        "Avaliação corporal bimestral",
      ],
    },

    {
      tipo: "Plano Anual",
      value: "R$ 100,00",
      descricao: [
        "Segunda a Sábado",
        "Todas as horas do dia",
        "Todos os equipamentos",
        "Avaliação corporal bimestral",
        "Plano de dieta",
      ],
    },
  ]);

  const location = useLocation();
  useEffect(() => {
    if (location?.state?.plano) {
      setPlano(location?.state?.plano);
    } else {
      setShowModal(true);
    }
  }, []);

  const handleModal = () => {
    setShowModal(!showModal);
  };

  return (
    <div className="registro-container">
      <div className="body-container">
        <Header />
      </div>
      <div className="form-container">
        <Form className="form-dados" onSubmit={handleForm}>
          <h1>Cadastro</h1>
          <p className="register-subtitle">
            Já tem cadastro?<a href="/login"> Clique aqui </a>para fazer o
            login.
          </p>
          <Row>
            <Form.Group className="nome">
              <Form.Label>Nome</Form.Label>
              <Form.Control
                type="text"
                name="nome"
                value={formData.nome}
                onChange={(e) => handleFormEdit(e, "nome")}
                required
              />
            </Form.Group>
            <Form.Group controlId="sobrenome">
              <Form.Label>Sobrenome</Form.Label>
              <Form.Control
                size="sm"
                type="text"
                name="sobrenome"
                value={formData.sobreNome}
                onChange={(e) => handleFormEdit(e, "sobreNome")}
                required
              />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group controlId="email">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                name="email"
                value={formData.email}
                onChange={(e) => handleFormEdit(e, "email")}
                required
              />
            </Form.Group>
            <Form.Group controlId="cpf">
              <Form.Label>CPF</Form.Label>
              <Form.Control
                type="text"
                name="cpf"
                value={formData.cpf}
                onChange={(e) => handleFormEdit(e, "cpf")}
                required
              />
            </Form.Group>
          </Row>
          <Row>
            {/*  <Col> */}
            <Form.Group controlId="dataNascimento">
              <Form.Label>Data de Nascimento</Form.Label>
              <Form.Control
                type="date"
                name="dataNascimento"
                value={formData.dataNascimento}
                onChange={(e) => handleFormEdit(e, "dataNascimento")}
                required
              />
            </Form.Group>
            {/*  </Col> */}
            <Col>
              <Row>
                <Form.Group controlId="telefone">
                  <Form.Label>Telefone</Form.Label>
                  <Form.Control
                    type="tel"
                    name="telefone"
                    value={formData.telefone}
                    onChange={(e) => handleFormEdit(e, "telefone")}
                    required
                  />
                </Form.Group>
              </Row>
            </Col>
          </Row>
          <Row>
            <Form.Group className="senha">
              <Form.Label>Senha</Form.Label>
              <Form.Control
                type="password"
                name="nome"
                value={formData.senha}
                onChange={(e) => handleFormEdit(e, "senha")}
                required
              />
            </Form.Group>
            <Form.Group className="confirmarSenha">
              <Form.Label>Confirmar Senha</Form.Label>
              <Form.Control
                type="password"
                name="confirmarSenha"
                value={formData.confirmarSenha}
                onChange={(e) => handleFormEdit(e, "confirmarSenha")}
                required
              />
            </Form.Group>
          </Row>

          <Form.Group
            controlId="genero"
            style={{ display: "flex", alignItems: "center" }}
          >
            <Form.Check
              type="radio"
              label="Masculino"
              name="genero"
              value="MASCULINO"
              checked={formData.genero === "MASCULINO"}
              onChange={(e) => handleFormEdit(e, "genero")}
              inline
            />
            <Form.Check
              type="radio"
              label="Feminino"
              name="genero"
              value="FEMININO"
              checked={formData.genero === "FEMININO"}
              onChange={(e) => handleFormEdit(e, "genero")}
              inline
            />
            <Form.Check
              type="radio"
              label="Outros"
              name="genero"
              value="OUTROS"
              checked={formData.genero === "OUTROS"}
              onChange={(e) => handleFormEdit(e, "genero")}
              inline
            />
          </Form.Group>
          <Button className="button-registro" variant="primary" type="submit">
            Registrar
          </Button>
        </Form>
        <section className="plano">
          <a href="#">
            <div className="mensal">
              {plano !== null ? (
                <div>
                  <h2>{plano?.tipo}</h2>
                  <h1>{plano?.value}</h1>
                  {plano?.descricao?.map((item) => (
                    <p>{item}</p>
                  ))}
                  <button onClick={handleModal}>Alterar Plano</button>
                </div>
              ) : (
                <div>
                  <h2>Escolha seu plano:</h2>
                </div>
              )}
            </div>
          </a>
        </section>
      </div>
      <div className="div-footer-container">
        <Footer />
      </div>
      <Modal
        show={showModal}
        onHide={() => {
          if (plano !== null) {
            handleModal();
          }
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Escolher Plano:</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <section className="plans">
            {status?.map((plano, index) => (
              <a>
                <div className="mensal">
                  <div>
                    <h2>{plano?.tipo}</h2>
                    <h1>{plano?.value}</h1>
                    {plano?.descricao?.map((item) => (
                      <p>{item}</p>
                    ))}
                  </div>
                  <button
                    onClick={() => {
                      setPlano(plano);
                      setShowModal(false);
                    }}
                  >
                    Matricule-se
                  </button>
                </div>
              </a>
            ))}
          </section>
        </Modal.Body>
      </Modal>
    </div>
  );
}

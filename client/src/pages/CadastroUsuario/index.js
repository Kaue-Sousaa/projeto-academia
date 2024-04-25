import React, { useEffect, useState } from "react";
import { Form, Button, Col, Row, Modal } from "react-bootstrap";

import api from "../../services/api";

import Header from "../../component/Header";
import Footer from "../../component/Footer";

import "./styles.css";
import { Link, useLocation } from "react-router-dom";

export default function RegistroUsuario() {
  const [nome, setNome] = useState("");
  const [sobreNome, setSobreNome] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [dataNascimento, setDataNascimento] = useState("");
  const [telefone, setTelefone] = useState("");
  const [genero, setGenero] = useState("");
  const [role, setRole] = useState("");
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
        <Form className="form-dados">
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
                /*  value={usuario.nome}
                  onChange={handleChange} */
                required
              />
            </Form.Group>
            <Form.Group controlId="sobrenome">
              <Form.Label>Sobrenome</Form.Label>
              <Form.Control
                size="sm"
                type="text"
                name="sobrenome"
                /* value={usuario.sobrenome}
                  onChange={handleChange} */
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
                /* value={usuario.email}
              onChange={handleChange} */
                required
              />
            </Form.Group>
            <Form.Group controlId="cpf">
              <Form.Label>CPF</Form.Label>
              <Form.Control
                type="text"
                name="cpf"
                /* value={usuario.cpf}
              onChange={handleChange} */
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
                /* value={usuario.dataNascimento}
                  onChange={handleChange} */
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
                    /* value={usuario.telefone}
              onChange={handleChange} */
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
                /*  value={usuario.senha}
                  onChange={handleChange} */
                required
              />
            </Form.Group>
            <Form.Group className="confirmarSenha">
              <Form.Label>Confirmar Senha</Form.Label>
              <Form.Control
                type="password"
                name="confirmarSenha"
                /* value={usuario.confirmarSenha}
                  onChange={handleChange} */
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
              /* checked={usuario.genero === "MASCULINO"}
              onChange={handleChange} */
              inline
            />
            <Form.Check
              type="radio"
              label="Feminino"
              name="genero"
              value="FEMININO"
              /* checked={usuario.genero === "FEMININO"}
              onChange={handleChange} */
              inline
            />
            <Form.Check
              type="radio"
              label="Outros"
              name="genero"
              value="OUTROS"
              /* checked={usuario.genero === "OUTROS"}
              onChange={handleChange} */
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

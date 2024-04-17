import React, { useEffect, useState } from "react";
import { Form, Button, Col, Row } from "react-bootstrap";

import api from "../../services/api";

import Header from "../../component/Header";
import Footer from "../../component/Footer";
import "./styles.css";
import { useLocation } from "react-router-dom";

export default function RegistroUsuario() {
  const [nome, setNome] = useState("");
  const [sobreNome, setSobreNome] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [dataNascimento, setDataNascimento] = useState("");
  const [telefone, setTelefone] = useState("");
  const [genero, setGenero] = useState("");
  const [role, setRole] = useState("");

  const [plano, setPlano] = useState(null);

  const location = useLocation();
  useEffect(() => {
    if (location?.state?.plano) {
      setPlano(location?.state?.plano);
    }
  }, []);

  return (
    <div className="registro-container">
      <Header />
      <div className="form-container">
        <Form className="form-dados">
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
              <Form.Group controlId="nacionalidade">
                <Form.Label>Nacionalidade</Form.Label>
                <Form.Control
                  type="text"
                  name="nacionalidade"
                  /* value={usuario.nacionalidade}
                  onChange={handleChange} */
                  required
                />
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Row>
              <Form.Group>
                <Form.Label>DDI</Form.Label>
                <Form.Control
                  type="ddi"
                  name="ddi"
                  /* value={usuario.telefone}
              onChange={handleChange} */
                  required
                />
              </Form.Group>

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
          </Row>
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
      <Footer />
    </div>
  );
}

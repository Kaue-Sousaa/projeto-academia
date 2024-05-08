import React, { useState } from "react";

import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Row from "react-bootstrap/Row";
import "./styles.css";

import Header from "../../component/Header";
import Footer from "../../component/Footer";
import logo from "../../assets/favicon.png";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [cpf, setCpf] = useState("");
  const [form, setForm] = useState({});

  const handleCpfChange = (event) => {
    const inputValue = event.target.value.replace(/\D/g, "");
    setCpf(inputValue);
  };

  const handleSubmit = (values) => {
    values.preventDefault();
    localStorage.setItem("credenciais", form);
    console.log("values: ", form);
  };

  const navigate = useNavigate();

  return (
    <div className="body-container">
      <Header />
      <div className="login-container">
        <Form /* validated={validated} */ onSubmit={handleSubmit}>
          <Form.Group as={Col} controlId="validationCustom01">
            <h1>Login</h1>
            <p className="login-subtitle">
              Se você já é cliente, insira seus dados:
            </p>
            <Form.Control
              required
              type="text"
              placeholder="CPF"
              name="cpf"
              onChange={(e) => {
                setForm({ ...form, [e.target.name]: e.target.value });
              }}
            />
            <Form.Control
              type="password"
              placeholder="Senha"
              required
              name="senha"
              onChange={(e) => {
                setForm({ ...form, [e.target.name]: e.target.value });
              }}
            />
          </Form.Group>
          <div className="login-button">
            <Button
              className="button-register"
              onClick={() => {
                navigate("/register");
              }}
            >
              AINDA NÃO SOU CLIENTE
            </Button>
            <Button type="submit" className="button-access">
              ACESSAR
            </Button>
          </div>
        </Form>
      </div>
      <div>
        <Footer />
      </div>
    </div>
  );
}

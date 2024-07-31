import React, { useState } from "react";

import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import api from "../../services/api";

import "./styles.css";

import Header from "../../component/Header";
import Footer from "../../component/Footer";

import { useNavigate } from "react-router-dom";
import { Toast } from "react-bootstrap";

export default function Login() {
  const [form, setForm] = useState({});
  const [error, setError] = useState("");
  const [showToast, setShowToast] = useState(false);
  const handleSubmit = async (values) => {
    values.preventDefault();
    try {
      const response = await api.post("/auth/login", form);
      if (response.status === 200) {
        localStorage.setItem(
          process.env.REACT_APP_AUTH,
          JSON.stringify(response.data)
        );
        navigate("/home", {});
      }
    } catch (error) {
      setError(error?.response?.data?.message);
      setShowToast(true);
    }
  };

  const handleCPFChange = (e) => {
    const value = e.target.value.replace(/\D/g, "");
    setForm({ ...form, [e.target.name]: value });
  };

  const navigate = useNavigate();

  return (
    <div className="body-container">
      <Header />
      <div className="login-container">
        <Form onSubmit={handleSubmit}>
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
              value={form.cpf || ""}
              onChange={handleCPFChange}
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
        <Toast
          onClose={() => setShowToast(false)}
          show={showToast}
          delay={3000}
          autohide
          style={{
            position: "fixed",
            top: 20,
            right: 20,
            zIndex: 8000,
          }}
        >
          <Toast.Header>
            <strong className="me-auto">Erro ao fazer login</strong>
          </Toast.Header>
          <Toast.Body>{error}</Toast.Body>
        </Toast>
      </div>
    </div>
  );
}

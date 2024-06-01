import React, { useState, useEffect } from "react";
import { Modal, Button, Form, Row, Col, Alert } from "react-bootstrap";
import auth from "../../services/auth";
import api from "../../services/api";

import "./styles.css";

const EditUserModal = ({ show, handleClose }) => {
  const user = auth.getUser();
  const [formData, setFormData] = useState({
    nome: "",
    sobreNome: "",
    email: "",
    senha: "",
    confirmarSenha: "",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    setSuccessMessage("");
    buscarUsuarioPorEmail(user?.email);
  }, []);

  const buscarUsuarioPorEmail = async (email) => {
    try {
      const response = await api.get(`/usuario/user?email=${email}`);
      const userData = response?.data;
      setFormData({
        id: userData.id,
        nome: userData.nome,
        sobreNome: userData.sobreNome,
        email: userData.email,
        cpf: userData.cpf,
        dataNascimento: userData.dataNascimento,
        telefone: userData.telefone,
        genero: userData.genero,
        senha: null,
      });
    } catch (error) {
      console.error("Erro ao buscar os dados do usuário:", error);
    }
  };

  const handleChange = (e) => {
    setSuccessMessage("");
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setErrorMessage("");
      const response = await api.put("/usuario/atualizar", formData);
      if (response.status === 200) {
        setSuccessMessage("Dados atualizados com sucesso!");
        setTimeout(() => {
          setSuccessMessage("");
          handleClose();
        }, 1000);
      }
    } catch (error) {
      setErrorMessage(error.response?.data?.message);
    }
  };

  const handleModalClose = () => {
    setFormData({ ...formData, senha: "", confirmarSenha: "" });
    setErrorMessage("");
    setSuccessMessage("");
    handleClose();
  };

  const handleModalHide = () => {
    setFormData({ ...formData, senha: "", confirmarSenha: "" });
    setErrorMessage("");
  };

  return (
    <div className="FormEdit">
      <Modal show={show} onHide={handleModalClose} onExited={handleModalHide}>
        <Modal.Header closeButton>
          <Modal.Title>Editar Informações</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {successMessage && <Alert variant="success">{successMessage}</Alert>}
          {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
          <Form onSubmit={handleSubmit}>
            <Row>
              <Col md={6}>
                <Form.Group controlId="formName">
                  <Form.Label>Nome</Form.Label>
                  <Form.Control
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="formSurname">
                  <Form.Label>Sobrenome</Form.Label>
                  <Form.Control
                    type="text"
                    name="sobreNome"
                    value={formData.sobreNome}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Row>
              <Col md={6}>
                <Form.Group controlId="formEmail">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="formBirthDate">
                  <Form.Label>Data de Nascimento</Form.Label>
                  <Form.Control
                    type="date"
                    name="dataNascimento"
                    value={formData.dataNascimento}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Row>
              <Col md={6}>
                <Form.Group controlId="formSurname">
                  <Form.Label>CPF</Form.Label>
                  <Form.Control
                    type="text"
                    name="cpf"
                    value={formData.cpf}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="formPhone">
                  <Form.Label>Telefone</Form.Label>
                  <Form.Control
                    type="text"
                    name="telefone"
                    value={formData.telefone}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Row>
              <Col md={6}>
                <Form.Group controlId="formPassword">
                  <Form.Label>Senha</Form.Label>
                  <Form.Control
                    type="password"
                    name="senha"
                    value={formData.senha}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="formPassword">
                  <Form.Label>Confirmar Senha</Form.Label>
                  <Form.Control
                    type="password"
                    name="confirmarSenha"
                    value={formData.confirmarSenha}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Row>
              <Col>
                <Form.Label>Gênero</Form.Label>
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
                    onChange={(e) => handleChange(e, "genero")}
                    inline
                  />
                  <Form.Check
                    type="radio"
                    label="Feminino"
                    name="genero"
                    value="FEMININO"
                    checked={formData.genero === "FEMININO"}
                    onChange={(e) => handleChange(e, "genero")}
                    inline
                  />
                  <Form.Check
                    type="radio"
                    label="Outros"
                    name="genero"
                    value="OUTROS"
                    checked={formData.genero === "OUTROS"}
                    onChange={(e) => handleChange(e, "genero")}
                    inline
                  />
                </Form.Group>
              </Col>
            </Row>
            <Button variant="primary" type="submit">
              Salvar
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default EditUserModal;

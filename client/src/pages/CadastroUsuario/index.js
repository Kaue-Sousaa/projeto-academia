import React from "react";
import Header from "../../component/Header"
import "./styles.css";

import Form from 'react-bootstrap/Form';



function TextControlsExample() {
  return (
    <div>
    <Header/>
    <Form>
      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="name@example.com" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>Example textarea</Form.Label>
        <Form.Control as="textarea" rows={3} />
      </Form.Group>
    </Form>
    </div>
    
  );
}

export default TextControlsExample;

/* export default function Registro() {
  return (
    <div className="registro-container">
      <form className="formulario-registro">
        <h1>Cadastro</h1>
        <p>
          Já tem cadastro? <a href="/">Clique aqui</a> para fazer o login.
        </p>
        <input type="text" name="nome" id="idNomeCompleto" placeholder="nome" />
        <input
          type="text"
          name="sobreNome"
          id="idSobreNome"
          placeholder="Sobrenome"
        />
        <input type="text" name="email" id="idEmail" placeholder="E-mail" />
        <input type="text" name="cpf" id="idCpf" placeholder="CPF" />
        <input
          type="text"
          name="dataNascimento"
          id="idDataNascimento"
          placeholder="Data de Nascimento"
        />
        <input type="text" placeholder="Nacionalidade" />
        <input type="text" placeholder="DDI*" />
        <div className="genero-container">
          <div>
            <label htmlFor="masc">Masculino</label>
            <input type="radio" id="masc" name="gender" value="male" />
          </div>
          <div>
            <label htmlFor="fem">Feminino</label>
            <input type="radio" id="fem" name="gender" value="fem" />
          </div>
          <div>
            <label htmlFor="outro">Outro</label>
            <input type="radio" id="outro" name="gender" value="outro" />
          </div>
        </div>
        <button>Criar Conta</button>
      </form>
    </div>
  ); */
/* } */

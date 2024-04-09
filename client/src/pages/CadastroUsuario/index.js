import React from "react";

import "./styles.css";

export default function Registro() {
  return (
    <div className="registro-container">
      <h1>Cadastro</h1>
      <p>
        Já tem cadastro? <a href="/">Clique aqui</a> para fazer o login.
      </p>
      <form className="formulario-registro">
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
        <button>Criar Conta</button>
      </form>
    </div>
  );
}

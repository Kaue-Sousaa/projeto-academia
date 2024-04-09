import React from "react";
import { useState } from "react";
import "./styles.css";

import logo from "../../assets/favicon.png";

export default function Login() {
  const [cpf, setCpf] = useState("");

  const handleCpfChange = (event) => {
    const inputValue = event.target.value.replace(/\D/g, "");
    setCpf(inputValue);
  };

  return (
    <div className="login-container">
      <div className="img-container">
        <img src={logo} alt="logo" />
        <p>Metabolism</p>
      </div>
      <section className="form">
        <form>
          <h1>Login</h1>
          <input
            maxLength={11}
            value={cpf}
            onChange={handleCpfChange}
            placeholder="CPF"
          />
          <input type="password" placeholder="Senha" />

          <button className="button" type="submit">
            Acessar
          </button>
        </form>
      </section>
    </div>
  );
}

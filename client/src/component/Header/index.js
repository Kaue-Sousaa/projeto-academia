import React from "react";
import { Link } from "react-router-dom";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import auth from "../../services/auth";
import { BsPersonFill } from "react-icons/bs";

import "./styles.css";

export default function HeadeWeb(props) {
  const storage = auth.isAuthenticated();

  return (
    <div className="cabecalho">
      <div className="divSolo">
        <section>
          <Link to="/" className="Metabolism">
            <h1>
              Metabolism<span> Fitness</span>
            </h1>
          </Link>
        </section>
      </div>
      <nav>
        <Link to="/">INICIO</Link>
        <Link to="/about">SOBRE NÓS</Link>
        {!storage && <Link to="/login">LOGIN</Link>}

        {storage && (
          <DropdownButton
            id="user-dropdown"
            title={
              <>
                <BsPersonFill
                  size={30}
                  style={{
                    padding: "4px",
                    borderRadius: "50%",
                    background: "#cecece",
                    marginRight: "5%",
                  }}
                />
                Kaue Sousa
              </>
            }
          >
            <Dropdown.Item as="button">E-mail: k@gmail.com</Dropdown.Item>
            <Dropdown.Item as="button">Editar senha</Dropdown.Item>
            <Dropdown.Item as="button">Sair</Dropdown.Item>
          </DropdownButton>
        )}
      </nav>
    </div>
  );
}

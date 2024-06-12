import React, { useState } from "react";
import { Link } from "react-router-dom";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import auth from "../../services/auth";
import { BsPersonFill } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import EditUserModal from "../EditUserModal";

import "./styles.css";

export default function HeaderWeb(props) {
  const storage = auth.isAuthenticated();
  const navigate = useNavigate();
  const user = storage ? auth.getUser() : null;

  const [showModal, setShowModal] = useState(false);

  const handleLogout = () => {
    localStorage.clear();
    sessionStorage.clear();
    navigate("/");
  };
  const handleEditClick = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

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
        {/* <Link to="/about">SOBRE NÓS</Link> */}
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
                {user ? user.usuario : "Usuário"}
              </>
            }
          >
            {/* <Dropdown.Item as="button">
              E-mail: {user ? user.email : "email@exemplo.com"}
            </Dropdown.Item> */}
            <Dropdown.Item as="button" onClick={handleEditClick}>
              Editar
            </Dropdown.Item>
            <Dropdown.Item as="button" onClick={handleLogout}>
              Sair
            </Dropdown.Item>
          </DropdownButton>
        )}
      </nav>
      <EditUserModal show={showModal} handleClose={handleCloseModal} />
    </div>
  );
}

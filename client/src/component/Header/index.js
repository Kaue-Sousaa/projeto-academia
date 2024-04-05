import React from "react";
import { Link } from "react-router-dom";

import "./styles.css";

export default function HeadeWeb(props) {
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
        <Link to="/">HOME</Link>
        <Link to="/obout">OBOUT US</Link>
        <Link to="/login">LOGIN</Link>
      </nav>
    </div>
  );
}

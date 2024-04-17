import React, { useState } from "react";

import Heade from "../../component/Header";
import musculacao from "../../assets/musculacao.png";
import karate from "../../assets/karate.png";
import esteira from "../../assets/esteira.png";
import Foooter from "../../component/Footer";

import "./styles.css";
import { useNavigate } from "react-router-dom";

export default function HomeWeb() {
  const [status, setStatus] = useState([
    {
      tipo: "Mensal Plan",
      value: "$ 50,99",
      descricao: ["Monday to Saturday", "2 Daily Hours", "All Equipment"],
    },

    {
      tipo: "Quarterly Plan",
      value: "$ 40,99",
      descricao: [
        "Monday",
        "One plan diet",
        "4 Daily Hours",
        "All Equipment",
        "Body evaluation bimonthly",
      ],
    },

    {
      tipo: "Anual Plan",
      value: "$ 36,99",
      descricao: [
        "Monday to Saturday",
        "All hours the day",
        "All Equipment",
        "Body evaluation bimonthly",
        "Plan diet mensal",
      ],
    },
  ]);

  const navigate = useNavigate();

  return (
    <div className="principal">
      <div className="fundo">
        <Heade></Heade>
        <section className="text">
          <div>
            <p>THE EVOLUTION OF OUR CUSTOMERS COMES FIRST</p>
          </div>
        </section>
        <div className="esports">
          <section className="services">
            <div>
              <img src={karate} alt="Karate" />
              <h2>Martial Arts</h2>
              <p>
                Train Judo Free with sensei <br />
                Matheus Luna
              </p>
            </div>
            <div>
              <img src={musculacao} alt="Musculacao" />
              <h2>
                Professional <br />
                Training
              </h2>
              <p>
                Be guied by trained and prepared <br />
                professionals to serve you.{" "}
              </p>
            </div>
            <div>
              <img src={esteira} alt="Funcional" />
              <h2>Functional</h2>
              <p>
                We offer several functional activities <br />
                such as dance, swimming, karate and <br /> more.
              </p>
            </div>
          </section>
        </div>
        <div className="register">
          <div>
            <h1>DOWNLOAD OUR APP AND CONTACT US NOW FOR MORE OFFERS</h1>
            <h2>WHERE HEALTH AND BEAUTY FITNESS ARE.</h2>

            <a href="/register">
              <button>Go to Register</button>
            </a>
          </div>
        </div>

        <section className="plans">
          {status?.map((plano, index) => (
            <a
              onClick={() => {
                navigate("/register", { state: { plano: plano } });
              }}
            >
              <div className="mensal">
                <div>
                  <h2>{plano?.tipo}</h2>
                  <h1>{plano?.value}</h1>
                  {plano?.descricao?.map((item) => (
                    <p>{item}</p>
                  ))}
                </div>
                <button>Matricule-se</button>
              </div>
            </a>
          ))}
        </section>
        <Foooter />
      </div>
    </div>
  );
}

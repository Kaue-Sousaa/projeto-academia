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
      tipo: "Plano Mensal",
      value: "R$ 60,00",
      descricao: [
        "Segunda a Sábado",
        "2 Horas Diárias",
        "Todos os equipamentos",
      ],
    },

    {
      tipo: "Plano Trimestral",
      value: "R$ 40,00",
      descricao: [
        "Segunda-feira",
        "Dieta de um plano",
        "4 Horas Diárias",
        "Todos os equipamentos",
        "Avaliação corporal bimestral",
      ],
    },

    {
      tipo: "Plano Anual",
      value: "R$ 100,00",
      descricao: [
        "Segunda a Sábado",
        "Todas as horas do dia",
        "Todos os equipamentos",
        "Avaliação corporal bimestral",
        "Plano de dieta",
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
            <p>A EVOLUÇÃO DOS NOSSOS CLIENTES ESTÁ EM PRIMEIRO LUGAR</p>
          </div>
        </section>
        <div className="esports">
          <section className="services">
            <div>
              <img src={karate} alt="Karate" />
              <h2>Artes marciais</h2>
              <p>
                Treine Judô grátis com o sensei <br />
                Matheus Luna
              </p>
            </div>
            <div>
              <img src={musculacao} alt="Musculacao" />
              <h2>
                Personal <br />
                Training
              </h2>
              <p>
                Seja orientado por profissionais treinados <br />e preparados
                para atendê-lo.{" "}
              </p>
            </div>
            <div>
              <img src={esteira} alt="Funcional" />
              <h2>Funcional</h2>
              <p>
                Oferecemos diversas atividades <br />
                funcionais <br /> como dança, natação, caratê e muito mais.
              </p>
            </div>
          </section>
        </div>
        <div className="register">
          <div>
            <h1>ENTRE EM CONTATO AGORA PARA MAIS OFERTAS</h1>
            <h2>ONDE ESTÃO A SAÚDE E A BELEZA FITNESS.</h2>

            <a href="/register">
              <button>Matricule-se</button>
            </a>
          </div>
        </div>

        <section className="plans">
          {status?.map((plano, index) => (
            <a>
              <div className="mensal">
                <div>
                  <h2>{plano?.tipo}</h2>
                  <h1>{plano?.value}</h1>
                  {plano?.descricao?.map((item) => (
                    <p>{item}</p>
                  ))}
                </div>
                <button
                  onClick={() => {
                    navigate("/register", { state: { plano: plano } });
                  }}
                >
                  Matricule-se
                </button>
              </div>
            </a>
          ))}
        </section>
        <Foooter />
      </div>
    </div>
  );
}

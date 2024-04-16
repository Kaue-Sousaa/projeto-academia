import React from "react";

import Heade from "../../component/Header";
import musculacao from "../../assets/musculacao.png";
import karate from "../../assets/karate.png";
import esteira from "../../assets/esteira.png";
import Foooter from "../../component/Footer";

import "./styles.css";

export default function HomeWeb() {
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
          <a href="/register">
            <div className="mensal">
              <div>
                <h2>Mensal Plan</h2>
                <h1>$ 50,99</h1>
                <p>Monday to Saturday</p>
                <p>2 Daily Hours</p>
                <p>All Equipment</p>
              </div>
              <button>Matricule-se</button>
            </div>
          </a>
          <a href="/register">
            <div className="mensal">
              <div>
                <h2>Quarterly Plan</h2>
                <h1>$ 40,99</h1>
                <p>Monday</p>
                <p>4 Daily Hours</p>
                <p>All Equipment</p>
                <p>Body evaluation bimonthly</p>
                <p>One plan diet</p>
              </div>
              <button>Matricule-se</button>
            </div>
          </a>
          <a href="/register">
            <div className="mensal">
              <div>
                <h2>Anual Plan</h2>
                <h1>$ 36,99</h1>
                <p>Monday to Saturday</p>
                <p>All hours the day</p>
                <p>All Equipment</p>
                <p>Body evaluation mensal</p>
                <p>Plan diet mensal</p>
              </div>
              <button>Matricule-se</button>
            </div>
          </a>
        </section>
        <Foooter />
      </div>
    </div>
  );
}

import React from "react";
import gps from "../../assets/gps.png";
import phone from "../../assets/phone.png";
import email from "../../assets/mail.png";
import whats from "../../assets/whatsapp.png";
import face from "../../assets/facebook.png";
import insta from "../../assets/instagram.png";
import "./styles.css";

export default function Foooter() {
  return (
    <div className="container">
      <section className="faixa">
        <div>
          <img src={gps} alt="Localization" />
          <p>R.Paulo José de Macêdo, 17</p> <br />
          <p>Bairro São Benedito</p>
        </div>
        <div>
          <img src={phone} alt="Contact" />
          <p>(85) 98161-3615</p>
        </div>
        <div>
          <img src={email} alt="Email" />
          <p>
            metabolismfitness
            <br />
            @gmail.com
          </p>
        </div>
      </section>
      <footer>
        <div className="redes">
          <h1 className="bio">
            Metabolism <span> Fitness</span>
          </h1>
          <div className="sociais">
            <div>
              <img src={insta} alt="Instagram" />
              <p>@metabolismfitness</p>
            </div>
            <div>
              <img src={face} alt="Facebook" />
              <p>Metabolism Fitness</p>
            </div>
            <div>
              <img src={whats} alt="Whatsapp" />
              <p>(85) 98161-3615</p>
            </div>
          </div>
        </div>

        <div className="end">
          <p className="copy">
            @2024 Academia Metabolism. Todos os direitos reservados.
          </p>
        </div>
      </footer>
    </div>
  );
}

import React from 'react';

import './styles.css'

export default function HomeWeb(){
    return (
    <div className="home-page">
      <header className="header">
        <h1>Bem-vindo à Academia Metabolism</h1>
        <p>Transforme seu corpo, transforme sua vida!</p>
      </header>
      <section className="about-section">
        <h2>Sobre Nós</h2>
        <p>A Academia Metabolism é dedicada a ajudar você a atingir seus objetivos de fitness. Oferecemos uma variedade de programas de treinamento, equipamentos de última geração e instrutores experientes para ajudá-lo em sua jornada de fitness.</p>
      </section>
      <section className="services-section">
        <h2>Nossos Serviços</h2>
        <ul>
          <li>Aulas de grupo</li>
          <li>Personal Training</li>
          <li>Equipamentos de última geração</li>
          <li>Programas de treinamento personalizado</li>
          <li>Sauna e Spa</li>
        </ul>
      </section>
      <section className="contact-section">
        <h2>Entre em Contato</h2>
        <p>Estamos ansiosos para ajudá-lo a começar sua jornada de Metabolism. Entre em contato conosco para mais informações ou para agendar uma visita.</p>
        <p>Telefone: (XX) XXXX-XXXX</p>
        <p>Email: info@academiametabolism.com</p>
      </section>
      <footer className="footer">
        <p>&copy; 2024 Academia Metabolism. Todos os direitos reservados.</p>
      </footer>
    </div>
      );
}

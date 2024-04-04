import React from 'react';
import { useState } from 'react';
import './styles.css';

export default function  Login(){
    const [cpf, setCpf] = useState('');

    const handleCpfChange = (event) => {
        const inputValue = event.target.value.replace(/\D/g, '');
        setCpf(inputValue);
    };

    return (
        <div className="login-container">
            <section className="form">
                <form>
                    <h1>Login</h1>
                    <input maxLength={11}  value={cpf} onChange={handleCpfChange} placeholder="CPF"/>
                    <input type="password" placeholder='Senha'/>

                    <button className='button' type='submit'>Acessar</button>
                </form>
            </section>
        </div>  
    );
}
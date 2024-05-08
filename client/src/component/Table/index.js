import React from 'react';
import Table from 'react-bootstrap/Table';
import { AiOutlineDelete } from 'react-icons/ai'; 
const Tabela = ({ colunas, data, onRemove }) => {
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {colunas.map((coluna, index) => (
            <th key={index}>{coluna}</th>
          ))}
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index}>
            {Object.values(item).map((valor, idx) => (
              <td key={idx}>{valor}</td>
            ))}
            <td>
              <button onClick={() => onRemove(index)}><AiOutlineDelete /></button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default Tabela;

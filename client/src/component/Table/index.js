import React from "react";
import Table from "react-bootstrap/Table";
import { AiOutlineDelete } from "react-icons/ai";

const Tabela = ({ colunas, data, onRemove }) => {
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {colunas?.map((coluna, index) => (
            <th key={index}>{coluna}</th>
          ))}
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        {data?.map((item) => (
          <tr key={item.id}>
            <td>{item.nome}</td>
            <td>{item.data}</td>
            <td>{item.horario}</td>
            <td>{item.treino}</td>
            <td>
              <button onClick={() => onRemove(item.id)}>
                <AiOutlineDelete />
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default Tabela;

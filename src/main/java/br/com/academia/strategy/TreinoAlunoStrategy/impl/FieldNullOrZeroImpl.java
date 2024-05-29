package br.com.academia.strategy.TreinoAlunoStrategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.exception.RequiredObjectIsNullException;
import br.com.academia.strategy.TreinoAlunoStrategy.ValidarTreinoAlunoStrategy;
import lombok.SneakyThrows;

@Component
public class FieldNullOrZeroImpl implements ValidarTreinoAlunoStrategy {

    @SneakyThrows
    @Override
    public void validar(TreinoAlunoDto request) {
        if (!isValidFieldNullAndZero(request)) {
            throw new RequiredObjectIsNullException("O(s) campo(s) não pode(m) ter o valor vazio");
        }
    }

    private boolean isValidFieldNullAndZero(TreinoAlunoDto treinoAlunoDto) {
        return validateAlunoField(treinoAlunoDto.nomeAluno())
                && validateCategoriaField(treinoAlunoDto.categoria())
                && validateSubCategoriaField(treinoAlunoDto.subCategoria())
                && validateHorarioField(treinoAlunoDto.horario());
    }

    private boolean validateAlunoField(String nomeAluno) {
        return nomeAluno != null && !"0".equals(nomeAluno);
    }

    private boolean validateCategoriaField(Integer categoria) {
        return categoria != null && categoria != 0;
    }

    private boolean validateSubCategoriaField(Integer subCategoria) {
        return subCategoria != null && subCategoria != 0;
    }

    private boolean validateHorarioField(String horario) {
        return horario != null && !"0".equals(horario);
    }
}
package br.com.academia.strategy.impl;

import org.springframework.stereotype.Component;

import br.com.academia.dto.TreinoAlunoDto;
import br.com.academia.strategy.ValidarTreinoAlunoStrategy;
import lombok.SneakyThrows;

@Component
public class FieldNullOrZero implements ValidarTreinoAlunoStrategy{

    @SneakyThrows
    @Override
    public void validar(TreinoAlunoDto request) {
        if(!isValidFieldNullAndZero(request)){
            throw new UnsupportedOperationException("O(s) campo(s) não pode(m) ter o valor vazio"); 
        }
    }

    private boolean isValidFieldNullAndZero(TreinoAlunoDto treinoAlunoDto){
        return validateAlunoField(treinoAlunoDto.aluno())
            && validateCategoriaField(treinoAlunoDto.categoria())
            && validateSubCategoriaField(treinoAlunoDto.subCategoria())
            && validateHorarioField(treinoAlunoDto.horario());
    }

    private boolean validateAlunoField(Integer idAluno) {
        return idAluno != null && idAluno != 0;
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

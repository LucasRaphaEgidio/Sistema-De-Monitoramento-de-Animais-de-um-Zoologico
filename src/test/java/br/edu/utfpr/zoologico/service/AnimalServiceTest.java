package br.edu.utfpr.zoologico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.edu.utfpr.zoologico.exception.ValidacaoException;
import br.edu.utfpr.zoologico.model.Especie;
import br.edu.utfpr.zoologico.model.TipoAnimal;
import org.junit.jupiter.api.Test;

/** Valida as regras de negocio dos animais. Todas falham antes de acessar o banco. */
class AnimalServiceTest {
    private final AnimalService service = new AnimalService();
    private final Especie leao = new Especie(1L, "Leao", TipoAnimal.MAMIFERO, "Felino");

    private String erro(String nome, int idade, double peso, String sexo, Especie especie, String caracteristica) {
        return assertThrows(ValidacaoException.class,
                () -> service.cadastrar(nome, idade, peso, sexo, especie, caracteristica)).getMessage();
    }

    @Test
    void recusaNomeVazio() {
        assertEquals("Informe o nome do animal.", erro("  ", 4, 150, "Macho", leao, "Curta"));
    }

    @Test
    void recusaNomeNulo() {
        assertEquals("Informe o nome do animal.", erro(null, 4, 150, "Macho", leao, "Curta"));
    }

    @Test
    void recusaIdadeNegativa() {
        assertEquals("A idade nao pode ser negativa.", erro("Nala", -1, 150, "Femea", leao, "Curta"));
    }

    @Test
    void recusaPesoZeroOuNegativo() {
        assertEquals("O peso deve ser maior que zero.", erro("Nala", 4, 0, "Femea", leao, "Curta"));
        assertEquals("O peso deve ser maior que zero.", erro("Nala", 4, -2, "Femea", leao, "Curta"));
    }

    @Test
    void recusaSexoVazio() {
        assertEquals("Informe o sexo do animal.", erro("Nala", 4, 150, "", leao, "Curta"));
    }

    @Test
    void recusaAnimalSemEspecie() {
        assertEquals("Cadastre e selecione uma especie.", erro("Nala", 4, 150, "Femea", null, "Curta"));
    }

    @Test
    void recusaCaracteristicaEspecificaVazia() {
        assertEquals("Informe a caracteristica especifica do animal.", erro("Nala", 4, 150, "Femea", leao, " "));
    }

    @Test
    void atualizacaoAplicaAsMesmasRegras() {
        assertThrows(ValidacaoException.class, () -> service.atualizar(1L, "", 4, 150, "Femea", leao, "Curta"));
    }

    @Test
    void exclusaoExigeAnimalSelecionado() {
        assertThrows(ValidacaoException.class, () -> service.excluir(null));
    }
}

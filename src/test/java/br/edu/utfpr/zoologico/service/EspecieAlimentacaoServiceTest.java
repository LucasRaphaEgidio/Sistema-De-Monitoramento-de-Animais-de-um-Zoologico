package br.edu.utfpr.zoologico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.edu.utfpr.zoologico.exception.ValidacaoException;
import br.edu.utfpr.zoologico.model.TipoAnimal;
import org.junit.jupiter.api.Test;

/** Valida as regras de negocio de especies e alimentacao. Todas falham antes de acessar o banco. */
class EspecieAlimentacaoServiceTest {
    private final EspecieService especieService = new EspecieService();
    private final AlimentacaoService alimentacaoService = new AlimentacaoService();

    @Test
    void especieExigeNome() {
        ValidacaoException erro = assertThrows(ValidacaoException.class,
                () -> especieService.cadastrar(" ", TipoAnimal.AVE, "Ave"));
        assertEquals("Informe o nome da especie.", erro.getMessage());
    }

    @Test
    void especieExigeClasse() {
        ValidacaoException erro = assertThrows(ValidacaoException.class,
                () -> especieService.cadastrar("Tucano", null, "Ave"));
        assertEquals("Selecione a classe da especie.", erro.getMessage());
    }

    @Test
    void alimentacaoExigeAnimal() {
        assertThrows(ValidacaoException.class, () -> alimentacaoService.registrar(null, "Carne", 2));
        assertThrows(ValidacaoException.class, () -> alimentacaoService.listarPorAnimal(null));
    }

    @Test
    void alimentacaoExigeAlimento() {
        ValidacaoException erro = assertThrows(ValidacaoException.class,
                () -> alimentacaoService.registrar(1L, "", 2));
        assertEquals("Informe o alimento.", erro.getMessage());
    }

    @Test
    void alimentacaoExigeQuantidadePositiva() {
        ValidacaoException erro = assertThrows(ValidacaoException.class,
                () -> alimentacaoService.registrar(1L, "Carne", 0));
        assertEquals("A quantidade deve ser maior que zero.", erro.getMessage());
    }
}

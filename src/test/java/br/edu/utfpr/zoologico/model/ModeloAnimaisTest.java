package br.edu.utfpr.zoologico.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ModeloAnimaisTest {
    private Animal criar(TipoAnimal tipo, String caracteristica) {
        Especie especie = new Especie(1L, "Especie", tipo, "Descricao");
        return AnimalFactory.criar(7L, "Nome", 3, 10.5, "Femea", especie, caracteristica);
    }

    @Test
    void fabricaCriaCadaSubclasseConformeOTipo() {
        assertInstanceOf(Ave.class, criar(TipoAnimal.AVE, "Azul"));
        assertInstanceOf(Mamifero.class, criar(TipoAnimal.MAMIFERO, "Curta"));
        assertInstanceOf(Reptil.class, criar(TipoAnimal.REPTIL, "Lisas"));
    }

    @Test
    void subclassesInformamOProprioTipo() {
        assertEquals(TipoAnimal.AVE, criar(TipoAnimal.AVE, "Azul").getTipo());
        assertEquals(TipoAnimal.MAMIFERO, criar(TipoAnimal.MAMIFERO, "Curta").getTipo());
        assertEquals(TipoAnimal.REPTIL, criar(TipoAnimal.REPTIL, "Lisas").getTipo());
    }

    @Test
    void animalGuardaAtributosGeraisEEspecifico() {
        Animal animal = criar(TipoAnimal.MAMIFERO, "Curta");
        assertEquals(7L, animal.getId());
        assertEquals("Nome", animal.getNome());
        assertEquals(3, animal.getIdade());
        assertEquals(10.5, animal.getPeso());
        assertEquals("Femea", animal.getSexo());
        assertEquals("Especie", animal.getEspecie().getNome());
        assertEquals("Curta", animal.getCaracteristicaEspecifica());
    }

    @Test
    void cadaClasseTemSeuRotuloDeCaracteristica() {
        assertEquals("Cor das penas", TipoAnimal.AVE.getRotuloCaracteristica());
        assertEquals("Tipo de pelagem", TipoAnimal.MAMIFERO.getRotuloCaracteristica());
        assertEquals("Tipo de escamas", TipoAnimal.REPTIL.getRotuloCaracteristica());
    }

    @Test
    void especiesSemIdentificadorNaoSaoIguais() {
        Especie a = new Especie(null, "Leao", TipoAnimal.MAMIFERO, "");
        Especie b = new Especie(null, "Leao", TipoAnimal.MAMIFERO, "");
        assertNotEquals(a, b);
    }
}

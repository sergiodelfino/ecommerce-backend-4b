package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        Categoria categoriaSalva =
                categoriaRepositorio.findById(categoria.getId()).orElseThrow();

        assertEquals("Categoria Teste", categoriaSalva.getNome());
    }

    @Test
    @Order(2)
    public void deveBuscarUmaCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Busca");
        categoria.setDescricao("Descrição Busca");

        categoriaRepositorio.save(categoria);

        Short id = categoria.getId();

        Categoria categoriaEncontrada =
                categoriaRepositorio.findById(id).orElseThrow();

        assertNotNull(categoriaEncontrada);
        assertEquals("Categoria Busca", categoriaEncontrada.getNome());
        assertEquals("Descrição Busca", categoriaEncontrada.getDescricao());
    }

    @Test
    @Order(3)
    public void deveListarTodasAsCategorias() {
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Categoria Teste 1");
        categoria1.setDescricao("Descrição Teste 1");
        categoriaRepositorio.save(categoria1);

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Categoria Teste 2");
        categoria2.setDescricao("Descrição Teste 2");
        categoriaRepositorio.save(categoria2);

        List<Categoria> categorias = categoriaRepositorio.findAll();

        assertTrue(categorias.stream()
                .anyMatch(categoria ->
                        categoria.getNome().equals("Categoria Teste 1")));

        assertTrue(categorias.stream()
                .anyMatch(categoria ->
                        categoria.getNome().equals("Categoria Teste 2")));
    }

    @Test
    @Order(4)
    public void deveAlterarUmaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Antiga");
        categoria.setDescricao("Descrição Antiga");

        categoriaRepositorio.save(categoria);

        Short id = categoria.getId();

        categoria.setNome("Categoria Alterada");

        categoriaRepositorio.save(categoria);

        Categoria categoriaAlterada =
                categoriaRepositorio.findById(id).orElseThrow();

        assertEquals(id, categoriaAlterada.getId());
        assertEquals("Categoria Alterada", categoriaAlterada.getNome());
    }

    @Test
    @Order(5)
    public void deveExcluirUmaCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Excluir");
        categoria.setDescricao("Descrição Excluir");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        categoriaRepositorio.deleteById(categoria.getId());

        assertFalse(categoriaRepositorio.existsById(categoria.getId()));
    }
}
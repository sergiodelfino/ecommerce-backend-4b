package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmProduto() {
        Produto produto = new Produto();
        produto.setNome("Nome Teste");
        produto.setDescricao("Descrição Teste");
        produto.setEstoque(Short.parseShort("1"));
        produto.setPreco(new BigDecimal("1.00"));
        produto.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));

        produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(produto.getId()));
        assertEquals("Nome Teste",
                produtoRepositorio.findById(produto.getId()).orElseThrow().getNome());
    }

    @Test
    @Order(2)
    public void deveBuscarUmProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Produto Busca");
        produto.setDescricao("Descrição Busca");
        produto.setEstoque(Short.parseShort("2"));
        produto.setPreco(new BigDecimal("20.00"));
        produto.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));

        produtoRepositorio.save(produto);

        Integer id = produto.getId();

        Produto produtoEncontrado =
                produtoRepositorio.findById(id).orElseThrow();

        assertNotNull(produtoEncontrado);
        assertEquals("Produto Busca", produtoEncontrado.getNome());
        assertEquals("Descrição Busca", produtoEncontrado.getDescricao());
    }

    @Test
    @Order(3)
    public void deveListarTodosOsProdutos() {
        Produto produto1 = new Produto();
        produto1.setNome("Produto Teste 1");
        produto1.setDescricao("Descrição Teste 1");
        produto1.setEstoque(Short.parseShort("1"));
        produto1.setPreco(new BigDecimal("10.00"));
        produto1.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));
        produtoRepositorio.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Produto Teste 2");
        produto2.setDescricao("Descrição Teste 2");
        produto2.setEstoque(Short.parseShort("2"));
        produto2.setPreco(new BigDecimal("20.00"));
        produto2.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));
        produtoRepositorio.save(produto2);

        List<Produto> produtos = produtoRepositorio.findAll(Sort.by("nome"));

        assertTrue(produtos.stream()
                .anyMatch(produto -> produto.getNome().equals("Produto Teste 1")));

        assertTrue(produtos.stream()
                .anyMatch(produto -> produto.getNome().equals("Produto Teste 2")));
    }

    @Test
    @Order(4)
    public void deveAlterarUmProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto Antigo");
        produto.setDescricao("Descrição Antiga");
        produto.setEstoque(Short.parseShort("5"));
        produto.setPreco(new BigDecimal("50.00"));
        produto.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));

        produtoRepositorio.save(produto);

        Integer id = produto.getId();

        produto.setNome("Produto Alterado");

        produtoRepositorio.save(produto);

        Produto produtoAlterado = produtoRepositorio.findById(id).orElseThrow();

        assertEquals(id, produtoAlterado.getId());
        assertEquals("Produto Alterado", produtoAlterado.getNome());
    }

    @Test
    @Order(5)
    public void deveExcluirUmProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Produto Excluir");
        produto.setDescricao("Descrição Excluir");
        produto.setEstoque(Short.parseShort("1"));
        produto.setPreco(new BigDecimal("1.00"));
        produto.setCategoria(categoriaRepositorio.getReferenceById(Short.valueOf("1")));

        produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(produto.getId()));

        produtoRepositorio.deleteById(produto.getId());

        assertFalse(produtoRepositorio.existsById(produto.getId()));
    }
}
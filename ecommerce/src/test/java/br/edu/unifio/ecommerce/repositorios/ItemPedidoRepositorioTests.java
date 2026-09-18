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

import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmItemPedido() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("3500.00"));
        itemPedido.setPedido(pedidoRepositorio.findById(1).orElseThrow());
        itemPedido.setProduto(produtoRepositorio.findById(2).orElseThrow());

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(itemPedidoRepositorio.existsById(itemPedido.getId()));

        ItemPedido itemPedidoSalvo =
                itemPedidoRepositorio.findById(itemPedido.getId()).orElseThrow();

        assertEquals(2, itemPedidoSalvo.getQuantidade());
    }

    @Test
    @Order(2)
    public void deveBuscarUmItemPedidoPorId() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("80.00"));
        itemPedido.setPedido(pedidoRepositorio.findById(1).orElseThrow());
        itemPedido.setProduto(produtoRepositorio.findById(3).orElseThrow());

        itemPedidoRepositorio.save(itemPedido);

        Integer id = itemPedido.getId();

        ItemPedido itemPedidoEncontrado =
                itemPedidoRepositorio.findById(id).orElseThrow();

        assertNotNull(itemPedidoEncontrado);
        assertEquals(2, itemPedidoEncontrado.getQuantidade());
        assertEquals(new BigDecimal("80.00"), itemPedidoEncontrado.getValorUnitario());
    }

    @Test
    @Order(3)
    public void deveListarTodosOsItensPedido() {
        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setQuantidade(3);
        itemPedido1.setValorUnitario(new BigDecimal("80.00"));
        itemPedido1.setPedido(pedidoRepositorio.findById(1).orElseThrow());
        itemPedido1.setProduto(produtoRepositorio.findById(3).orElseThrow());
        itemPedidoRepositorio.save(itemPedido1);

        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setQuantidade(4);
        itemPedido2.setValorUnitario(new BigDecimal("120.00"));
        itemPedido2.setPedido(pedidoRepositorio.findById(2).orElseThrow());
        itemPedido2.setProduto(produtoRepositorio.findById(4).orElseThrow());
        itemPedidoRepositorio.save(itemPedido2);

        List<ItemPedido> itensPedido = itemPedidoRepositorio.findAll();

        assertTrue(itensPedido.stream()
                .anyMatch(item ->
                        item.getQuantidade().equals(3)));

        assertTrue(itensPedido.stream()
                .anyMatch(item ->
                        item.getQuantidade().equals(4)));
    }

    @Test
    @Order(4)
    public void deveAlterarUmItemPedido() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));
        itemPedido.setPedido(pedidoRepositorio.findById(1).orElseThrow());
        itemPedido.setProduto(produtoRepositorio.findById(5).orElseThrow());

        itemPedidoRepositorio.save(itemPedido);

        Integer id = itemPedido.getId();

        itemPedido.setQuantidade(5);

        itemPedidoRepositorio.save(itemPedido);

        ItemPedido itemPedidoAlterado =
                itemPedidoRepositorio.findById(id).orElseThrow();

        assertEquals(id, itemPedidoAlterado.getId());
        assertEquals(5, itemPedidoAlterado.getQuantidade());
    }

    @Test
    @Order(5)
    public void deveExcluirUmItemPedidoPorId() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));
        itemPedido.setPedido(pedidoRepositorio.findById(1).orElseThrow());
        itemPedido.setProduto(produtoRepositorio.findById(5).orElseThrow());

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(itemPedidoRepositorio.existsById(itemPedido.getId()));

        itemPedidoRepositorio.deleteById(itemPedido.getId());

        assertFalse(itemPedidoRepositorio.existsById(itemPedido.getId()));
    }
}
package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 10, 10, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        assertTrue(pedidoRepositorio.existsById(pedido.getId_pedido()));

        Pedido pedidoSalvo =
                pedidoRepositorio.findById(pedido.getId_pedido()).orElseThrow();

        assertEquals("Pendente", pedidoSalvo.getStatus());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 10, 11, 0));
        pedido.setStatus("Busca");
        pedido.setValorTotal(new BigDecimal("600.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Integer id = pedido.getId_pedido();

        Pedido pedidoEncontrado =
                pedidoRepositorio.findById(id).orElseThrow();

        assertNotNull(pedidoEncontrado);
        assertEquals("Busca", pedidoEncontrado.getStatus());
        assertEquals(new BigDecimal("600.00"), pedidoEncontrado.getValorTotal());
    }

    @Test
    @Order(3)
    public void deveListarTodosOsPedidos() {
        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.of(2026, 9, 10, 12, 0));
        pedido1.setStatus("Teste 1");
        pedido1.setValorTotal(new BigDecimal("700.00"));
        pedido1.setCliente(clienteRepositorio.findById(1).orElseThrow());
        pedidoRepositorio.save(pedido1);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.of(2026, 9, 10, 13, 0));
        pedido2.setStatus("Teste 2");
        pedido2.setValorTotal(new BigDecimal("800.00"));
        pedido2.setCliente(clienteRepositorio.findById(2).orElseThrow());
        pedidoRepositorio.save(pedido2);

        List<Pedido> pedidos = pedidoRepositorio.findAll();

        assertTrue(pedidos.stream()
                .anyMatch(pedido ->
                        pedido.getStatus().equals("Teste 1")));

        assertTrue(pedidos.stream()
                .anyMatch(pedido ->
                        pedido.getStatus().equals("Teste 2")));
    }

    @Test
    @Order(4)
    public void deveAlterarUmPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 10, 14, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("900.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Integer id = pedido.getId_pedido();

        pedido.setStatus("Pago");

        pedidoRepositorio.save(pedido);

        Pedido pedidoAlterado =
                pedidoRepositorio.findById(id).orElseThrow();

        assertEquals(id, pedidoAlterado.getId_pedido());
        assertEquals("Pago", pedidoAlterado.getStatus());
    }

    @Test
    @Order(5)
    public void deveExcluirUmPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 10, 15, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("1000.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        assertTrue(pedidoRepositorio.existsById(pedido.getId_pedido()));

        pedidoRepositorio.deleteById(pedido.getId_pedido());

        assertFalse(pedidoRepositorio.existsById(pedido.getId_pedido()));
    }
}
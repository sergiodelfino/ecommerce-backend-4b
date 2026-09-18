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

import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoRepositorioTests {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmPagamento() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 10, 10, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 10, 10, 5));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Pix");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(pagamentoRepositorio.existsById(pagamento.getId()));

        Pagamento pagamentoSalvo =
                pagamentoRepositorio.findById(pagamento.getId()).orElseThrow();

        assertEquals("Aprovado", pagamentoSalvo.getStatus());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPagamentoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 11, 10, 0));
        pedido.setStatus("Busca");
        pedido.setValorTotal(new BigDecimal("600.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("600.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 11, 10, 5));
        pagamento.setStatus("Busca");
        pagamento.setTipo("Pix");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        Integer id = pagamento.getId();

        Pagamento pagamentoEncontrado =
                pagamentoRepositorio.findById(id).orElseThrow();

        assertNotNull(pagamentoEncontrado);
        assertEquals(new BigDecimal("600.00"), pagamentoEncontrado.getValor());
        assertEquals("Busca", pagamentoEncontrado.getStatus());
    }

    @Test
    @Order(3)
    public void deveListarTodosOsPagamentos() {
        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.of(2026, 9, 12, 10, 0));
        pedido1.setStatus("Teste 1");
        pedido1.setValorTotal(new BigDecimal("700.00"));
        pedido1.setCliente(clienteRepositorio.findById(1).orElseThrow());
        pedidoRepositorio.save(pedido1);

        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(new BigDecimal("700.00"));
        pagamento1.setData(LocalDateTime.of(2026, 9, 12, 10, 5));
        pagamento1.setStatus("Teste 1");
        pagamento1.setTipo("Pix");
        pagamento1.setPedido(pedido1);
        pagamentoRepositorio.save(pagamento1);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.of(2026, 9, 12, 11, 0));
        pedido2.setStatus("Teste 2");
        pedido2.setValorTotal(new BigDecimal("800.00"));
        pedido2.setCliente(clienteRepositorio.findById(2).orElseThrow());
        pedidoRepositorio.save(pedido2);

        Pagamento pagamento2 = new Pagamento();
        pagamento2.setValor(new BigDecimal("800.00"));
        pagamento2.setData(LocalDateTime.of(2026, 9, 12, 11, 5));
        pagamento2.setStatus("Teste 2");
        pagamento2.setTipo("Cartao");
        pagamento2.setPedido(pedido2);
        pagamentoRepositorio.save(pagamento2);

        List<Pagamento> pagamentos = pagamentoRepositorio.findAll();

        assertTrue(pagamentos.stream()
                .anyMatch(pagamento ->
                        pagamento.getStatus().equals("Teste 1")));

        assertTrue(pagamentos.stream()
                .anyMatch(pagamento ->
                        pagamento.getStatus().equals("Teste 2")));
    }

    @Test
    @Order(4)
    public void deveAlterarUmPagamento() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 13, 10, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("900.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("900.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 13, 10, 5));
        pagamento.setStatus("Pendente");
        pagamento.setTipo("Boleto");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        Integer id = pagamento.getId();

        pagamento.setStatus("Aprovado");

        pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoAlterado =
                pagamentoRepositorio.findById(id).orElseThrow();

        assertEquals(id, pagamentoAlterado.getId());
        assertEquals("Aprovado", pagamentoAlterado.getStatus());
    }

    @Test
    @Order(5)
    public void deveExcluirUmPagamentoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 14, 10, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("1000.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("1000.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 14, 10, 5));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Pix");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(pagamentoRepositorio.existsById(pagamento.getId()));

        pagamentoRepositorio.deleteById(pagamento.getId());

        assertFalse(pagamentoRepositorio.existsById(pagamento.getId()));
    }
}
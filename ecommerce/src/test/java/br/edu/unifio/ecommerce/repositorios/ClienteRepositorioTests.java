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

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    @Order(1)
    public void deveSalvarUmCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setTelefone("14999990000");

        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId_cliente()));

        Cliente clienteSalvo =
                clienteRepositorio.findById(cliente.getId_cliente()).orElseThrow();

        assertEquals("Cliente Teste", clienteSalvo.getNome());
    }

    @Test
    @Order(2)
    public void deveBuscarUmClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Busca");
        cliente.setEmail("busca@teste.com");
        cliente.setTelefone("14999990001");

        clienteRepositorio.save(cliente);

        Integer id = cliente.getId_cliente();

        Cliente clienteEncontrado =
                clienteRepositorio.findById(id).orElseThrow();

        assertNotNull(clienteEncontrado);
        assertEquals("Cliente Busca", clienteEncontrado.getNome());
        assertEquals("busca@teste.com", clienteEncontrado.getEmail());
    }

    @Test
    @Order(3)
    public void deveListarTodosOsClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Cliente Teste 1");
        cliente1.setEmail("cliente1@teste.com");
        cliente1.setTelefone("14999990002");
        clienteRepositorio.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Cliente Teste 2");
        cliente2.setEmail("cliente2@teste.com");
        cliente2.setTelefone("14999990003");
        clienteRepositorio.save(cliente2);

        List<Cliente> clientes = clienteRepositorio.findAll();

        assertTrue(clientes.stream()
                .anyMatch(cliente ->
                        cliente.getNome().equals("Cliente Teste 1")));

        assertTrue(clientes.stream()
                .anyMatch(cliente ->
                        cliente.getNome().equals("Cliente Teste 2")));
    }

    @Test
    @Order(4)
    public void deveAlterarUmCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Antigo");
        cliente.setEmail("antigo@teste.com");
        cliente.setTelefone("14999990004");

        clienteRepositorio.save(cliente);

        Integer id = cliente.getId_cliente();

        cliente.setNome("Cliente Alterado");

        clienteRepositorio.save(cliente);

        Cliente clienteAlterado =
                clienteRepositorio.findById(id).orElseThrow();

        assertEquals(id, clienteAlterado.getId_cliente());
        assertEquals("Cliente Alterado", clienteAlterado.getNome());
    }

    @Test
    @Order(5)
    public void deveExcluirUmClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Excluir");
        cliente.setEmail("excluir@teste.com");
        cliente.setTelefone("14999990005");

        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId_cliente()));

        clienteRepositorio.deleteById(cliente.getId_cliente());

        assertFalse(clienteRepositorio.existsById(cliente.getId_cliente()));
    }
}
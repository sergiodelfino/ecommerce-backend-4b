package br.edu.unifio.ecommerce.recursos;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifio.ecommerce.entidades.Produto;
import br.edu.unifio.ecommerce.repositorios.ProdutoRepositorio;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping ("produtos") 
public class ProdutoRecurso {
    private final ProdutoRepositorio produtoRepositorio;

    public ProdutoRecurso(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }
    
    @GetMapping 
    public List<Produto> listar () {
        return produtoRepositorio.findAll(Sort.by("nome"));
    }

    @GetMapping ("{id}")
    public Produto buscarPorId (@PathVariable  Integer id) {
        return produtoRepositorio.findById(id).orElseThrow();
    }
}

package br.edu.unifio.ecommerce.recursos;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifio.ecommerce.entidades.Produto;
import br.edu.unifio.ecommerce.repositorios.ProdutoRepositorio;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Produto inserir (@RequestBody Produto produto) {
        return produtoRepositorio.save(produto);
    }

    @DeleteMapping ("{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Integer id) {
        produtoRepositorio.deleteById(id);
    }
}

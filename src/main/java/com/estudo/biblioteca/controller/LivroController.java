package com.estudo.biblioteca.controller;

import com.estudo.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LivroController {
    private final List<Livro> livros = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping("/")
    public String inicio() {
        return "redirect:/livros";
    }

    @GetMapping("/livros")
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        return "livros";
    }

    @PostMapping("/livros")
    public String cadastrar(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam String categoria,
            @RequestParam String isbn) {
        Livro livro = new Livro();
        livro.setId(proximoId);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setIsbn(isbn);
        livros.add(livro);
        proximoId++;
        return "redirect:/livros";
    }

    @PostMapping("/livros/{id}/editar")
    public String editar(
            @PathVariable long id,
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam String categoria,
            @RequestParam String isbn) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setCategoria(categoria);
                livro.setIsbn(isbn);
                break;
            }
        }
        return "redirect:/livros";
    }

    @PostMapping("/livros/{id}/excluir")
    public String excluir(@PathVariable long id) {
        livros.removeIf(livro -> livro.getId() == id);
        return "redirect:/livros";
    }
}

package com.estudo.biblioteca.controller;

import com.estudo.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AutorController {
    private final List<Autor> autores = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping("/autores")
    public String listar(Model model) {
        model.addAttribute("autores", autores);
        return "autores";
    }

    @PostMapping("/autores")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String nacionalidade) {
        Autor autor = new Autor();
        autor.setId(proximoId);
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        autores.add(autor);
        proximoId++;
        return "redirect:/autores";
    }

    @PostMapping("/autores/{id}/excluir")
    public String excluir(@PathVariable long id) {
        autores.removeIf(autor -> autor.getId() == id);
        return "redirect:/autores";
    }
}

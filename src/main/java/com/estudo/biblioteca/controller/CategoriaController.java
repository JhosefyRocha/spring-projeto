package com.estudo.biblioteca.controller;

import com.estudo.biblioteca.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoriaController {
    private final List<Categoria> categorias = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping("/categorias")
    public String listar(Model model) {
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @PostMapping("/categorias")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String descricao) {
        Categoria categoria = new Categoria();
        categoria.setId(proximoId);
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categorias.add(categoria);
        proximoId++;
        return "redirect:/categorias";
    }

    @PostMapping("/categorias/{id}/excluir")
    public String excluir(@PathVariable long id) {
        categorias.removeIf(categoria -> categoria.getId() == id);
        return "redirect:/categorias";
    }
}

package com.estudo.biblioteca.controller;

import com.estudo.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmprestimoController {
    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping("/emprestimos")
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimos";
    }

    @PostMapping("/emprestimos")
    public String cadastrar(
            @RequestParam String livro,
            @RequestParam String usuario,
            @RequestParam String dataEmprestimo,
            @RequestParam String dataDevolucao) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(proximoId);
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimos.add(emprestimo);
        proximoId++;
        return "redirect:/emprestimos";
    }

    @PostMapping("/emprestimos/{id}/excluir")
    public String excluir(@PathVariable long id) {
        emprestimos.removeIf(emprestimo -> emprestimo.getId() == id);
        return "redirect:/emprestimos";
    }
}

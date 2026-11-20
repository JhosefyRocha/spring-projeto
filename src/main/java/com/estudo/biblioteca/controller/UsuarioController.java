package com.estudo.biblioteca.controller;

import com.estudo.biblioteca.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {
    private final List<Usuario> usuarios = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @PostMapping("/usuarios")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String telefone) {
        Usuario usuario = new Usuario();
        usuario.setId(proximoId);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuarios.add(usuario);
        proximoId++;
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/{id}/excluir")
    public String excluir(@PathVariable long id) {
        usuarios.removeIf(usuario -> usuario.getId() == id);
        return "redirect:/usuarios";
    }
}

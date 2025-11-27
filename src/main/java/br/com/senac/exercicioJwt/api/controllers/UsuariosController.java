package br.com.senac.exercicioJwt.api.controllers;

import br.com.senac.exercicioJwt.api.dtos.UsuariosRequestDTO;
import br.com.senac.exercicioJwt.api.services.UsuariosService;
import br.com.senac.exercicioJwt.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuariosRequestDTO usuario) {
        try {
            return ResponseEntity
                    .created(null)
                    .body(usuariosService.cadastrarUsuario(usuario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuariosRequestDTO usuario) {
        try {
            return ResponseEntity.ok(usuariosService.loginUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }
}

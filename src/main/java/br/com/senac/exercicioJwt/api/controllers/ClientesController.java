package br.com.senac.exercicioJwt.api.controllers;

import br.com.senac.exercicioJwt.api.dtos.ClientesRequestDTO;
import br.com.senac.exercicioJwt.api.entidades.Clientes;
import br.com.senac.exercicioJwt.api.services.ClientesService;
import br.com.senac.exercicioJwt.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarTodos() {
        return ResponseEntity.ok(clientesService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ClientesRequestDTO cliente) {
        try {
            return ResponseEntity.ok(clientesService.criar(cliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.response(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                           @RequestBody ClientesRequestDTO cliente) {
        try {
            return ResponseEntity.ok(clientesService.atualizar(id, cliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.response(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            clientesService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.response(e.getMessage()));
        }
    }
}

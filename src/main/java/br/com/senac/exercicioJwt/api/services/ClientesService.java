package br.com.senac.exercicioJwt.api.services;

import br.com.senac.exercicioJwt.api.dtos.ClientesRequestDTO;
import br.com.senac.exercicioJwt.api.entidades.Clientes;
import br.com.senac.exercicioJwt.api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }

    public Clientes criar(ClientesRequestDTO cliente) {

        return clientesRepositorio.save(this.clientesRequestDtoParaCliente(cliente));
    }

    public Clientes atualizar(Long id, ClientesRequestDTO cliente) {
        if(!clientesRepositorio.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Clientes clientePersist = this.clientesRequestDtoParaCliente(cliente);
        clientePersist.setId(id);

        return clientesRepositorio.save(clientePersist);
    }

    public void deletar(Long id) {
        if(!clientesRepositorio.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    private Clientes clientesRequestDtoParaCliente(ClientesRequestDTO entrada) {
        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setEmail(entrada.getEmail());
        saida.setDataNascimento(entrada.getDataNascimento());

        return saida;
    }
}

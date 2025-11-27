package br.com.senac.exercicioJwt.api.services;

import br.com.senac.exercicioJwt.api.dtos.ClientesRequestDTO;
import br.com.senac.exercicioJwt.api.entidades.Clientes;
import br.com.senac.exercicioJwt.api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }

    public Clientes criar(ClientesRequestDTO cliente) {
        Optional<Clientes> clienteResult =
                clientesRepositorio.fingByDocumento(cliente.getDocumento());
        if(clienteResult.isPresent()) {
            throw new RuntimeException("Documento já cadastrado no banco de dados");
        }

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
        String documento =
                entrada.getDocumento()
                        .replace(".","")
                        .replace("-", "");

        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setDocumento(documento);
        saida.setEmail(entrada.getEmail());
        saida.setDataNascimento(entrada.getDataNascimento());

        return saida;
    }
}

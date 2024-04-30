package com.arturlogan.projetoclienteebac.services;

import com.arturlogan.projetoclienteebac.entities.Cliente;
import com.arturlogan.projetoclienteebac.entities.interfaces.IClienteDAO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    public ClienteMapDAO(){
        this.map = new HashMap<>();
    }

    private Map<Long, Cliente> map;


    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null){
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if (clienteCadastrado != null) {
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> bucarTodos() {
        return List.of();
    }
}

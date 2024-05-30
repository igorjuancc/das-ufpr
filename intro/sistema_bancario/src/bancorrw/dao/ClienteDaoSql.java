/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class ClienteDaoSql implements ClienteDao{
    private ClienteDaoSql(){
    }
    private static ClienteDaoSql dao;
    public static ClienteDaoSql getClienteDaoSql(){
        throw new RuntimeException("Não implementado. Implemente aqui");
    }  
    private String selectAll = 
        "SELECT "+ 
            "id_cliente, " +
            "nome, " +
            "cpf, " +
            "data_nascimento, " +
            "cartao_credito " +
        "FROM " +
            "clientes ";
    private String selectById = selectAll + " " + 
            "WHERE "+
                "id_cliente=?";
    private String insertCliente = 
        "INSERT INTO " +
            "clientes " +
            "(nome," +
            "cpf," +
            "data_nascimento, " +
            "cartao_credito) " +
        "VALUES" +
            "(?,?,?,?)";
    private String updateCliente = 
        "UPDATE " +
            "clientes " +
        "SET " + 
            "nome=?, " +
            "cpf=?, " +
            "data_nascimento=?, " +
            "cartao_credito=? " +
        "WHERE id_cliente = ?";
    private String deleteById = 
        "DELETE FROM "+
            "clientes "+
        "WHERE id_cliente=?";
    private String deleteAll = 
        "DELETE FROM "+
            "clientes ";
    private final String ressetAIPessoas = "ALTER TABLE clientes AUTO_INCREMENT =1";
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";
    @Override
    public void add(Cliente cliente) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public Cliente getById(long id) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui"); 
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void deleteAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }
    
}

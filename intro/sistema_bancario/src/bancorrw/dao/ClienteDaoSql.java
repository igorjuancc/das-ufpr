/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        if (dao == null)
            return dao = new ClienteDaoSql();            
        else 
            return dao; 
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
            "cartao_credito=?, " +
            "id_conta_corrente=? " +
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
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAdiciona = connection.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getCpf());
            stmtAdiciona.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmtAdiciona.setString(4, cliente.getCartaoCredito());
            stmtAdiciona.execute();
            
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            cliente.setId(i);            
        }        
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectAll);
                ResultSet rs = stmtLista.executeQuery()) {
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                long idCliente = rs.getLong("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                String cartaoCredito = rs.getString("cartao_credito");                
                clientes.add(new Cliente(idCliente, nome, cpf, dataNascimento, cartaoCredito));                                
            }
            return clientes;            
        }
    }

    @Override
    public Cliente getById(long id) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectById)) {
            stmtLista.setLong(1, id);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    long idCliente = rs.getLong("id_cliente");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                    String cartaoCredito = rs.getString("cartao_credito");
                    return new Cliente(idCliente, nome, cpf, dataNascimento, cartaoCredito);
                } else {
                    throw new SQLException("Cliente não encontrado com id=" + id);
                }               
            }             
        }
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(updateCliente)) {
            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getCpf());
            stmtAtualiza.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmtAtualiza.setString(4, cliente.getCartaoCredito());
            stmtAtualiza.setLong(6, cliente.getId());
            if ((cliente.getContaCorrente() != null) && (cliente.getContaCorrente().getId() >= 0)) 
                stmtAtualiza.setLong(5, cliente.getContaCorrente().getId());
            else
                stmtAtualiza.setLong(5, 0);
            stmtAtualiza.execute();
        }
    }

    @Override
    public void delete(Cliente cliente) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtExcluir = connection.prepareStatement(deleteById)) {
            stmtExcluir.setLong(1, cliente.getId());
            stmtExcluir.execute();            
            if (cliente.getContaCorrente() != null) {
                ContaCorrenteDao corDao = DaoFactory.getContaCorrenteDao(DaoType.SQL);
                corDao.delete(cliente.getContaCorrente());
            }            
            cliente.setId(-1);
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll)) {
            stmtExcluir.executeUpdate();
        }
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtRessetAIPessoas = connection.prepareStatement(ressetAIPessoas)) {
            stmtRessetAIPessoas.executeUpdate();
        }
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtRessetAIContas = connection.prepareStatement(ressetAIContas)) {
            stmtRessetAIContas.executeUpdate();
        }
    }    
}

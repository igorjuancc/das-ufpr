/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaCorrente;
import java.sql.Connection;
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
public class ContaCorrenteDaoSql implements ContaCorrenteDao{
    private ContaCorrenteDaoSql(){
    }
    private static ContaCorrenteDaoSql dao;
    public static ContaCorrenteDaoSql getContaCorrenteDaoSql(){
        if (dao == null)
            return dao = new ContaCorrenteDaoSql();            
        else           
            return dao;   
    } 
    private String insertContaCorrente = 
        "INSERT INTO " +
            "contas_corrente " +
            "(id_conta," +
            "limite," +
            "taxa_juros_limite) " +
        "VALUES" +
            "(?,?,?)";
    private String insertConta = 
        "INSERT INTO " +
            "contas " +
            "(id_cliente," +
            "saldo) " +
        "VALUES" +
            "(?,?)";
    
    private String updateClienteIdContaCorrente = 
        "UPDATE " +
            "clientes " +
        "SET " + 
            "id_conta_corrente=? " +
        "WHERE id_cliente = ?";
    private String updateContaCorrente = 
        "UPDATE " +
            "contas_corrente " +
        "SET " + 
            "limite=? ," +
            "taxa_juros_limite=? " +
        "WHERE id_conta = ?";    
    private String updateConta = 
        "UPDATE " +
            "contas " +
        "SET " + 
            "saldo=? " +
        "WHERE id_conta = ?";    
    private String selectByCliente = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente "+
                        "WHERE "+
                            "contas.id_cliente=?";
        private String selectById = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente "+
                        "WHERE "+
                            "contas.id_conta=?";
    private String selectAll = 
                        "SELECT "+
                            "contas_corrente.id_conta, "+
                            "saldo, "+
                            "limite, "+
                            "taxa_juros_limite, "+
                            "clientes.id_cliente,"+
                            "nome, "+
                            "cpf, "+
                            "data_nascimento, "+
                            "cartao_credito "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta "+
                        "INNER JOIN "+
                            "clientes "+
                        "ON "+
                            "contas.id_conta=clientes.id_conta_corrente ";   
    private String deleteById = 
                        "DELETE FROM "+
                            "contas " +
                        "WHERE " +
                            "id_conta=?";
    private String deleteAll = 
                        "DELETE " +
                            "contas,contas_corrente "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_corrente "+
                        "ON "+
                            "contas.id_conta=contas_corrente.id_conta ";            
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";
    @Override
    public void add(ContaCorrente contaCorrente) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAdicionaConta = connection.prepareStatement(insertConta, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmtAdicionaCorrente = connection.prepareStatement(insertContaCorrente)) {
            stmtAdicionaConta.setLong(1, contaCorrente.getCliente().getId());
            stmtAdicionaConta.setDouble(2, contaCorrente.getSaldo());
            stmtAdicionaConta.execute();
            
            ResultSet rs = stmtAdicionaConta.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            contaCorrente.setId(id);  
            
            stmtAdicionaCorrente.setLong(1, contaCorrente.getId());
            stmtAdicionaCorrente.setDouble(2, contaCorrente.getLimite());
            stmtAdicionaCorrente.setDouble(3, contaCorrente.getTaxaJurosLimite());
            stmtAdicionaCorrente.execute();    
            
            ClienteDao clienteDao = DaoFactory.getClienteDao(DaoType.SQL);
            clienteDao.update(contaCorrente.getCliente());
        } 
    }

    @Override
    public List<ContaCorrente> getAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectAll);
                ResultSet rs = stmtLista.executeQuery()) {
            List<ContaCorrente> contas = new ArrayList();
            while (rs.next()) {
                long idConta = rs.getLong("id_conta");
                double saldoConta = rs.getDouble("saldo");
                double limiteConta = rs.getDouble("limite");
                double taxaJurosLimiteConta = rs.getDouble("taxa_juros_limite");
                long idCliente = rs.getLong("id_cliente");
                String nomeCliente = rs.getString("nome");
                String cpfCliente = rs.getString("cpf");
                LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimentoCliente, cartaoCreditoCliente);
                ContaCorrente contaCorrente = new ContaCorrente(limiteConta, taxaJurosLimiteConta, idConta, cliente, saldoConta); 
                contas.add(contaCorrente);
            }
            return contas;            
        }    
    }

    @Override
    public ContaCorrente getById(long id) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectById)) {
            stmtLista.setLong(1, id);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    long idConta = rs.getLong("id_conta");
                    double saldoConta = rs.getDouble("saldo");
                    double limiteConta = rs.getDouble("limite");
                    double taxaJurosLimiteConta = rs.getDouble("taxa_juros_limite");
                    long idCliente = rs.getLong("id_cliente");
                    String nomeCliente = rs.getString("nome");
                    String cpfCliente = rs.getString("cpf");
                    LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                    String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                    Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimentoCliente, cartaoCreditoCliente);
                    return new ContaCorrente(limiteConta, taxaJurosLimiteConta, idConta, cliente, saldoConta);
                } else {
                    throw new SQLException("Conta corrente não encontrado com id=" + id);
                }               
            }             
        }  
    }

    @Override
    public void update(ContaCorrente contaCorrente) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAtualizaConta = connection.prepareStatement(updateConta);
                PreparedStatement stmtAtualizaContaCorrente = connection.prepareStatement(updateContaCorrente)) {
            stmtAtualizaConta.setDouble(1, contaCorrente.getSaldo());
            stmtAtualizaConta.setLong(2, contaCorrente.getId());
            stmtAtualizaConta.execute();
            
            stmtAtualizaContaCorrente.setDouble(1, contaCorrente.getLimite());
            stmtAtualizaContaCorrente.setDouble(2, contaCorrente.getTaxaJurosLimite());
            stmtAtualizaContaCorrente.setLong(3, contaCorrente.getId());
            stmtAtualizaContaCorrente.execute();             
        } 
    }

    @Override
    public void delete(ContaCorrente contaCorrente) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtExcluir = connection.prepareStatement(deleteById)) {
            stmtExcluir.setLong(1, contaCorrente.getId());
            stmtExcluir.execute();
            contaCorrente.setId(-1);
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll)) {
            stmtExcluir.executeUpdate();
        }
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtRessetAIContas = connection.prepareStatement(ressetAIContas)) {
            stmtRessetAIContas.executeUpdate();
        }
    }

    @Override
    public ContaCorrente getContaCorrenteByCliente(Cliente cliente) throws Exception{
         try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectById)) {
            stmtLista.setLong(1, cliente.getId());
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    long idConta = rs.getLong("id_conta");
                    double saldoConta = rs.getDouble("saldo");
                    double limiteConta = rs.getDouble("limite");
                    double taxaJurosLimiteConta = rs.getDouble("taxa_juros_limite");
                    long idCliente = rs.getLong("id_cliente");
                    String nomeCliente = rs.getString("nome");
                    String cpfCliente = rs.getString("cpf");
                    LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                    String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                    cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimentoCliente, cartaoCreditoCliente);
                    return new ContaCorrente(limiteConta, taxaJurosLimiteConta, idConta, cliente, saldoConta);
                } else {
                    throw new SQLException("Conta corrente de cliente não encontrado com id=" + cliente.getId());
                }               
            }             
        }    
    }    
}

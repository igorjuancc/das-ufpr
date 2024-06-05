/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaInvestimento;
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
public class ContaInvestimentoDaoSql implements ContaInvestimentoDao{
    private ContaInvestimentoDaoSql(){
    }
    private static ContaInvestimentoDaoSql dao;
    public static ContaInvestimentoDaoSql getContaInvestimentoDaoSql(){
        if(dao==null)
            return dao = new ContaInvestimentoDaoSql();
        else
            return dao;
    } 
    private String insertContaInvstimento = 
        "INSERT INTO " +
            "contas_investimento " +
            "(id_conta," +
            "taxa_remuneracao_investimento," +
            "montante_minimo," +
            "deposito_minimo) " +
        "VALUES" +
            "(?,?,?,?)";
    private String insertConta = 
        "INSERT INTO " +
            "contas " +
            "(id_cliente," +
            "saldo) " +
        "VALUES" +
            "(?,?)";
    private String selectAll = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente ";
    private String selectById = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente "+
        "WHERE "+
            "contas.id_conta=?";
    private String selectByCliente = 
        "SELECT "+
            "contas_investimento.id_conta, "+
            "saldo, "+
            "taxa_remuneracao_investimento, "+
            "montante_minimo, "+
            "deposito_minimo, "+
            "clientes.id_cliente,"+
            "nome, "+
            "cpf, "+
            "data_nascimento, "+
            "cartao_credito "+
        "FROM "+
            "contas "+
        "INNER JOIN "+
            "contas_investimento "+
        "ON "+
            "contas.id_conta=contas_investimento.id_conta "+
        "INNER JOIN "+
            "clientes "+
        "ON "+
            "contas.id_cliente=clientes.id_cliente "+
        "WHERE "+
            "contas.id_cliente=?";

    private String updateContaInvestimento = 
        "UPDATE " +
            "contas_investimento " +
        "SET " + 
            "taxa_remuneracao_investimento=? ," +
            "montante_minimo=? ," +            
            "deposito_minimo=? " +
        "WHERE id_conta = ?";    
    private String updateConta = 
        "UPDATE " +
            "contas " +
        "SET " + 
            "saldo=? " +
        "WHERE id_conta = ?";   
    private String deleteById = 
                        "DELETE FROM "+
                            "contas " +
                        "WHERE " +
                            "id_conta=?";
    private String deleteAll = 
                        "DELETE " +
                            "contas,contas_investimento "+
                        "FROM "+
                            "contas "+
                        "INNER JOIN "+
                            "contas_investimento "+
                        "ON "+
                            "contas.id_conta=contas_investimento.id_conta ";     
    private final String ressetAIContas = "ALTER TABLE contas AUTO_INCREMENT =1";

    @Override
    public void add(ContaInvestimento conta) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAdicionaConta = connection.prepareStatement(insertConta, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmtAdicionaInvestimento = connection.prepareStatement(insertContaInvstimento)) {
            stmtAdicionaConta.setLong(1, conta.getCliente().getId());
            stmtAdicionaConta.setDouble(2, conta.getSaldo());
            stmtAdicionaConta.execute();
            
            ResultSet rs = stmtAdicionaConta.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            conta.setId(id);  
            
            stmtAdicionaInvestimento.setLong(1, conta.getId());
            stmtAdicionaInvestimento.setDouble(2, conta.getTaxaRemuneracaoInvestimento());
            stmtAdicionaInvestimento.setDouble(3, conta.getMontanteMinimo());
            stmtAdicionaInvestimento.setDouble(4, conta.getDepositoMinimo());
            stmtAdicionaInvestimento.execute();    
        }       
    }

    @Override
    public List<ContaInvestimento> getAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectAll);
                ResultSet rs = stmtLista.executeQuery()) {
            List<ContaInvestimento> contas = new ArrayList();
            while (rs.next()) {
                long idConta = rs.getLong("id_conta");
                double saldoConta = rs.getDouble("saldo");
                double taxaConta = rs.getDouble("taxa_remuneracao_investimento");
                double montanteConta = rs.getDouble("montante_minimo");
                double depositoConta = rs.getDouble("deposito_minimo");
                long idCliente = rs.getLong("id_cliente");
                String nomeCliente = rs.getString("nome");
                String cpfCliente = rs.getString("cpf");
                LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimentoCliente, cartaoCreditoCliente);
                ContaInvestimento ci = new ContaInvestimento(taxaConta, montanteConta, depositoConta, saldoConta, idConta, cliente);
                contas.add(ci);
            }
            return contas;            
        }    
    }

    @Override
    public ContaInvestimento getById(long id) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectById)) {
            stmtLista.setLong(1, id);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    long idConta = rs.getLong("id_conta");
                    double saldoConta = rs.getDouble("saldo");
                    double taxaConta = rs.getDouble("taxa_remuneracao_investimento");
                    double montanteConta = rs.getDouble("montante_minimo");
                    double depositoConta = rs.getDouble("deposito_minimo");
                    long idCliente = rs.getLong("id_cliente");
                    String nomeCliente = rs.getString("nome");
                    String cpfCliente = rs.getString("cpf");
                    LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                    String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                    Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimentoCliente, cartaoCreditoCliente);
                    return new ContaInvestimento(taxaConta, montanteConta, depositoConta, saldoConta, idConta, cliente);
                } else {
                    throw new SQLException("Conta investimento não encontrado com id=" + id);
                }  
            }
        } 
    }

    @Override
    public void update(ContaInvestimento conta) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtAtualizaConta = connection.prepareStatement(updateConta);
                PreparedStatement stmtAtualizaContaInvestimento = connection.prepareStatement(updateContaInvestimento)) {
            stmtAtualizaConta.setDouble(1, conta.getSaldo());
            stmtAtualizaConta.setLong(2, conta.getId());
            stmtAtualizaConta.execute();
            
            stmtAtualizaContaInvestimento.setDouble(1, conta.getTaxaRemuneracaoInvestimento());
            stmtAtualizaContaInvestimento.setDouble(2, conta.getMontanteMinimo());
            stmtAtualizaContaInvestimento.setDouble(3, conta.getDepositoMinimo());
            stmtAtualizaContaInvestimento.setLong(4, conta.getId());
            stmtAtualizaContaInvestimento.execute();             
        }
    }

    @Override
    public void delete(ContaInvestimento conta) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtExcluir = connection.prepareStatement(deleteById)) {
            stmtExcluir.setLong(1, conta.getId());
            stmtExcluir.execute();
            conta.setId(-1);
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
    public List<ContaInvestimento> getContasInvestimentoByCliente(Cliente cliente) throws Exception  {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement stmtLista = connection.prepareStatement(selectAll);
                ResultSet rs = stmtLista.executeQuery()) {
            while (rs.next()) {
                long idConta = rs.getLong("id_conta");
                double saldoConta = rs.getDouble("saldo");
                double taxaConta = rs.getDouble("taxa_remuneracao_investimento");
                double montanteConta = rs.getDouble("montante_minimo");
                double depositoConta = rs.getDouble("deposito_minimo");
                long idCliente = rs.getLong("id_cliente");
                String nomeCliente = rs.getString("nome");
                String cpfCliente = rs.getString("cpf");
                LocalDate dataNascimentoCliente = rs.getDate("data_nascimento").toLocalDate();
                String cartaoCreditoCliente = rs.getString("cartao_credito");
                    
                cliente.setId(idCliente);
                cliente.setNome(nomeCliente);
                cliente.setCpf(cpfCliente);
                cliente.setDataNascimento(dataNascimentoCliente);
                cliente.setCartaoCredito(cartaoCreditoCliente);                
                ContaInvestimento contaInvestimento = new ContaInvestimento(taxaConta, montanteConta, depositoConta, saldoConta, idConta, cliente);
            }
            return cliente.getContasInvestimento();            
        }            
    }    
}

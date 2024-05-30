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
        throw new RuntimeException("Não implementado. Implemente aqui");
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
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public List<ContaCorrente> getAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");    
    }

    @Override
    public ContaCorrente getById(long id) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");   
    }

    @Override
    public void update(ContaCorrente contaCorrente) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void delete(ContaCorrente contaCorrente) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void deleteAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public ContaCorrente getContaCorrenteByCliente(Cliente cliente) throws Exception{
        throw new RuntimeException("Não implementado. Implemente aqui");   
    }
    
}

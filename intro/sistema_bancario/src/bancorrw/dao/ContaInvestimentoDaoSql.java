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
        throw new RuntimeException("Não implementado. Implemente aqui");       
    }

    @Override
    public List<ContaInvestimento> getAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public ContaInvestimento getById(long id) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui"); 
    }

    @Override
    public void update(ContaInvestimento conta) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void delete(ContaInvestimento conta) throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public void deleteAll() throws Exception {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }

    @Override
    public List<ContaInvestimento> getContasInvestimentoByCliente(Cliente cliente) throws Exception  {
        throw new RuntimeException("Não implementado. Implemente aqui");
    }
    
}

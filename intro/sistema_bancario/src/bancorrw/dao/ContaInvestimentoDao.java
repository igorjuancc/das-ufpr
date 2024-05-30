/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaInvestimento;
import java.util.List;


/**
 *
 * @author rafae
 */
public interface ContaInvestimentoDao extends Dao<ContaInvestimento>{

    public List<ContaInvestimento> getContasInvestimentoByCliente(Cliente cliente)  throws Exception ;
    
}

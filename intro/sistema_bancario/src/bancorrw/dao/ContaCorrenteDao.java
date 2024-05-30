/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaCorrente;

/**
 *
 * @author rafae
 */
public interface ContaCorrenteDao extends Dao<ContaCorrente>{

    public ContaCorrente getContaCorrenteByCliente(Cliente cliente) throws Exception;
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.dao;

import static bancorrw.dao.DaoType.SQL;

/**
 *
 * @author rafae
 */
public class DaoFactory {
    private DaoFactory(){
    }
    
    public static ClienteDao getClienteDao(DaoType type){
        throw new RuntimeException("Não implementado. Implemente aqui");
    }
    
    public static ContaCorrenteDao getContaCorrenteDao(DaoType type){
        throw new RuntimeException("Não implementado. Implemente aqui");
    }
    
    public static ContaInvestimentoDao getContaInvestimentoDao(DaoType type){
        throw new RuntimeException("Não implementado. Implemente aqui");
    }
    
}

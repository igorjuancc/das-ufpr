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
        switch(type) {
            case SQL:
                return ClienteDaoSql.getClienteDaoSql();
            default: 
                throw new RuntimeException("Tipo não existe:" + type);                
        }        
    }
    
    public static ContaCorrenteDao getContaCorrenteDao(DaoType type){
        switch(type) {
            case SQL:
                return ContaCorrenteDaoSql.getContaCorrenteDaoSql();
            default: 
                throw new RuntimeException("Tipo não existe:" + type);                
        } 
    }
    
    public static ContaInvestimentoDao getContaInvestimentoDao(DaoType type){
        switch(type) {
            case SQL:
                return ContaInvestimentoDaoSql.getContaInvestimentoDaoSql();
            default: 
                throw new RuntimeException("Tipo não existe:" + type);                
        } 
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.conta;

import bancorrw.cliente.Cliente;

/**
 *
 * @author rafae
 */
public class ContaCorrente extends Conta{
    private double limite;
    private double taxaJurosLimite;

    public ContaCorrente(double limite, double taxaJurosLimite, long id, Cliente cliente, double saldo) {
        super(id, cliente, saldo);
        this.limite = limite;
        this.taxaJurosLimite = taxaJurosLimite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getTaxaJurosLimite() {
        return taxaJurosLimite;
    }

    public void setTaxaJurosLimite(double taxaJuros) {
        this.taxaJurosLimite = taxaJuros;
    }    
    
    @Override
    public void aplicaJuros() {
        
    }
}

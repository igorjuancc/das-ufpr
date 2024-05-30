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
    private double taxaJuros;

    public ContaCorrente(double limite, double taxaJuros, long id, Cliente cliente, double saldo) {
        super(id, cliente, saldo);
        this.limite = limite;
        this.taxaJuros = taxaJuros;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getTaxaJurosLimite() {
        return taxaJuros;
    }

    public void setTaxaJurosLimite(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }    
    
    @Override
    public void aplicaJuros() {
        
    }
}

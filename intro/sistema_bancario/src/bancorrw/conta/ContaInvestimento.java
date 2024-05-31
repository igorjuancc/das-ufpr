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
public class ContaInvestimento extends Conta{
    private double taxaRemuneracaoInvestimento;
    private double montanteMinimo;
    private double depositoMinimo;

    public ContaInvestimento(double taxaRemuneracaoInvestimento, double montanteMinimo, double depositoMinimo, double saldo, long id, Cliente cliente) {
        super(id, cliente, saldo);
        if (super.getSaldo() < montanteMinimo)
            throw new RuntimeException("Saldo não pode ser menor que montante mínimo.");
        this.taxaRemuneracaoInvestimento = taxaRemuneracaoInvestimento;
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
        this.getCliente().addContaInvestimento(this);
    }

    public double getTaxaRemuneracaoInvestimento() {
        return taxaRemuneracaoInvestimento;
    }

    public void setTaxaRemuneracaoInvestimento(double taxaRemuneracaoInvestimento) {
        this.taxaRemuneracaoInvestimento = taxaRemuneracaoInvestimento;
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public void aplicaJuros() {
        double valorJuros = super.getSaldo() * this.taxaRemuneracaoInvestimento;
        super.deposita(valorJuros);
    }
    
    @Override
    public void saca(double valor) {
        if ((super.getSaldo() - valor) < this.montanteMinimo) {
            throw new RuntimeException("Saldo insuficiente para saque. Valor Saque=" + valor + " Saldo=" + super.getSaldo() + " Montante Minimo=" + this.montanteMinimo);             
        }        
        super.saca(valor);        
    }   
    
    @Override
    public void deposita(double valor) {
        if (valor < this.depositoMinimo) {
            throw new RuntimeException("Valor do depóstio não atingiu o mínimo. Valor Depósito=" + valor + " Depóstio Mínimo=" + this.depositoMinimo);
        }
        
        super.deposita(valor);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.cliente;

import bancorrw.conta.ContaCorrente;
import bancorrw.conta.ContaInvestimento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class Cliente extends Pessoa{
    private List<ContaInvestimento> contasInvestimento;
    private ContaCorrente contaCorrente;
    private String cartaoCredito;

    public Cliente(long id, String nome, String cpf, LocalDate dataNascimento, String cartaoCredito) {
        super(id, nome, cpf, dataNascimento);
        this.cartaoCredito = cartaoCredito;
        this.contasInvestimento = new ArrayList();
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        if ((this.contaCorrente != null) && (this.contaCorrente.getSaldo() != 0)) {
            throw new RuntimeException("Não pode modificar a conta corrente, pois saldo da original não está zerado. "
                    + "Para fazer isso primeiro zere o saldo da conta do cliente. Saldo=" + this.contaCorrente.getSaldo());            
        } 
        this.contaCorrente = contaCorrente;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }    

    public List<ContaInvestimento> getContasInvestimento() {
        return contasInvestimento;
    }  
    
    public double getSaldoTotalCliente() {
        double total = 0;
        for (ContaInvestimento ci : this.contasInvestimento) {
            total += ci.getSaldo();
        }
        return total;
    }
    
    public void addContaInvestimento(ContaInvestimento contaInvestimento) {
        if (this.contasInvestimento == null)
            this.contasInvestimento = new ArrayList();
        this.contasInvestimento.add(contaInvestimento);
    }
}

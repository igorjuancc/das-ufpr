/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import bancorrw.cliente.Cliente;
import bancorrw.conta.ContaCorrente;
import bancorrw.conta.ContaInvestimento;
import bancorrw.dao.ClienteDao;
import bancorrw.dao.ContaCorrenteDao;
import bancorrw.dao.ContaInvestimentoDao;
import bancorrw.dao.DaoFactory;
import bancorrw.dao.DaoType;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


/**
 *
 * @author rafae
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteBancoRrw{
    private ClienteDao cliDao;
    private ContaCorrenteDao corDao;
    private ContaInvestimentoDao invDao;
    
    public TesteBancoRrw() {
        /*cliDao = DaoFactory.getClienteDao(DaoType.SQL);
        corDao = DaoFactory.getContaCorrenteDao(DaoType.SQL);
        invDao = DaoFactory.getContaInvestimentoDao(DaoType.SQL);*/
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        //exlcui todos os registros do BD
        //cliDao.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    private void inicializaBD() throws Exception{
        if(cliDao==null){
            cliDao = DaoFactory.getClienteDao(DaoType.SQL);
            corDao = DaoFactory.getContaCorrenteDao(DaoType.SQL);
            invDao = DaoFactory.getContaInvestimentoDao(DaoType.SQL);
        }
        //exlcui todos os registros do BD
        cliDao.deleteAll();
    }
    @Test
    public void t01verificaEstruturaClassePessoa() throws ClassNotFoundException {
        Class c = Class.forName("bancorrw.cliente.Pessoa");
        //Verifica se a classe Pessoa é abstrata
        assertEquals("A classe Pessoa precisa ser abstrata.",true,Modifier.isAbstract(c.getModifiers()));
        //Verifica se classe Pessoa possui 4 atributos
        assertEquals("A classe ContaCorrente deve possuir 4 atributos.",4,c.getDeclaredFields().length);
        //Verifica o tipo desses 4 atributos
        assertEquals("O tipo do primeiro atributo da Classe Pessoa deve ser double.","long",c.getDeclaredFields()[0].getType().getTypeName());
        assertEquals("O tipo do segundo atributo da Classe Pessoa deve ser String.","java.lang.String",c.getDeclaredFields()[1].getType().getTypeName());
        assertEquals("O tipo do terceiro atributo da Classe Pessoa deve ser double.","java.lang.String",c.getDeclaredFields()[2].getType().getTypeName());
        assertEquals("O tipo do quarto atributo da Classe Pessoa deve ser double.","java.time.LocalDate",c.getDeclaredFields()[3].getType().getTypeName());
        //Verifica o nome desses 4 atributos        
        assertEquals("O nome do primeiro atributo da Classe Pessoa deve ser id.","id",c.getDeclaredFields()[0].getName());
        assertEquals("O nome do segundo atributo da Classe Pessoa deve ser nome.","nome",c.getDeclaredFields()[1].getName());
        assertEquals("O nome do terceir atributo da Classe Pessoa deve ser cpf.","cpf",c.getDeclaredFields()[2].getName());
        assertEquals("O nome do quarto atributo da Classe Pessoa deve ser dataNascimento.","dataNascimento",c.getDeclaredFields()[3].getName());
        //Verifica se os 4 atributos são privados da Classe ContaCorrente.
        assertEquals("Use o modificador private para o atributo id da classe Pessoa.",true,Modifier.isPrivate(c.getDeclaredFields()[0].getModifiers()));
        assertEquals("Use o modificador private para o atributo nome da classe Pessoa.",true,Modifier.isPrivate(c.getDeclaredFields()[1].getModifiers()));
        assertEquals("Use o modificador private para o atributo cpf da classe Pessoa.",true,Modifier.isPrivate(c.getDeclaredFields()[3].getModifiers()));
        assertEquals("Use o modificador private para o atributo dataNacimento da classe Pessoa.",true,Modifier.isPrivate(c.getDeclaredFields()[3].getModifiers()));
        //Verifica se classe pessoa tem 8 métodos deve ter a classe pessoa.
        assertEquals("A classe pessoa deve ter 8 métodos. Olhe na especificação.",8,c.getDeclaredMethods().length);
        
    }
    
    @Test
    public void t02verificaEstruturaClasseContaCorrente() throws ClassNotFoundException {
        Class c = Class.forName("bancorrw.conta.ContaCorrente");
        //Verifica o nome da classe pai.
        assertEquals("A classe ContaCorrente não está herdando da classe conta.","bancorrw.conta.Conta",c.getSuperclass().getName());
        //Verifica se a classe ContaCorrente é real
        assertEquals("A classe ContaCorrente não pode ser abstrata.",false,Modifier.isAbstract(c.getModifiers()));
        //Verifica se a classe Conta é abstrata
        assertEquals("A classe Conta precisa ser abstrata.",true,Modifier.isAbstract(c.getSuperclass().getModifiers()));
        //Verifica se o primeiro e o segundo atributos são do tipo double da classe ContaCorrente
        //Verifica se classe ContaCorrente possui 2 atributos
        assertEquals("A classe ContaCorrente deve possuir 2 atributos.",2,c.getDeclaredFields().length);
        //Verifica o tipo desses 2 atributos
        assertEquals("O tipo do primeiro atributo da Classe ContaCorrente deve ser double.","double",c.getDeclaredFields()[0].getType().getTypeName());
        assertEquals("O tipo do segundo atributo da Classe ContaCorrente deve ser double.","double",c.getDeclaredFields()[1].getType().getTypeName());
        //Verifica o nome desses 2 atributos        
        assertEquals("O nome do primeiro atributo da Classe ContaCorrente deve ser limite.","limite",c.getDeclaredFields()[0].getName());
        assertEquals("O nome do segundo atributo da Classe ContaCorrente deve ser taxaJurosLimite.","taxaJurosLimite",c.getDeclaredFields()[1].getName());
        //Verifica se os 2 atributos são privados da Classe ContaCorrente.
        assertEquals("Use o modificador private para o atributo limite.",true,Modifier.isPrivate(c.getDeclaredFields()[0].getModifiers()));
        assertEquals("Use o modificador private para o atributo taxaJurosLimite.",true,Modifier.isPrivate(c.getDeclaredFields()[1].getModifiers()));
    }

    @Test
    public void t03verificaEstruturaClasseContaInvestimento() throws ClassNotFoundException {
        Class c = Class.forName("bancorrw.conta.ContaInvestimento");
        //Verifica o nome da classe pai.
        assertEquals("A classe ContaCorrente não está herdando da classe conta.","bancorrw.conta.Conta",c.getSuperclass().getName());
        //Verifica se a classe ContaCorrente é real
        assertEquals("A classe ContaInvestimento não pode ser abstrata.",false,Modifier.isAbstract(c.getModifiers()));
        //Verifica se a classe Conta é abstrata
        assertEquals("A classe Conta precisa ser abstrata.",true,Modifier.isAbstract(c.getSuperclass().getModifiers()));
        //Verifica se o primeiro e o segundo atributos são do tipo double da classe ContaCorrente
        //Verifica se classe ContaCorrente possui 3 atributos
        assertEquals("A classe ContaInvestimento deve possuir 3 atributos.",3,c.getDeclaredFields().length);
        //Verifica o tipo desses 3 atributos
        assertEquals("O tipo do primeiro atributo da Classe ContaInvestimento deve ser double.","double",c.getDeclaredFields()[0].getType().getTypeName());
        assertEquals("O tipo do segundo atributo da Classe ContaInvestimento deve ser double.","double",c.getDeclaredFields()[1].getType().getTypeName());
        assertEquals("O tipo do terceiro atributo da Classe ContaInvestimento deve ser double.","double",c.getDeclaredFields()[2].getType().getTypeName());
        //Verifica o nome desses 3 atributos        
        assertEquals("O nome do primeiro atributo da Classe ContaInvestimento deve ser limite.","taxaRemuneracaoInvestimento",c.getDeclaredFields()[0].getName());
        assertEquals("O nome do segundo atributo da Classe ContaInvestimento deve ser taxaJurosLimite.","montanteMinimo",c.getDeclaredFields()[1].getName());
        assertEquals("O nome do terceiro atributo da Classe ContaInvestimento deve ser limite.","depositoMinimo",c.getDeclaredFields()[2].getName());
        //Verifica se os 3 atributos são privados da Classe ContaInvestimento.
        assertEquals("ContaInvestimento: Use o modificador private para o atributo taxaRemuneracaoInvestimento.",true,Modifier.isPrivate(c.getDeclaredFields()[0].getModifiers()));
        assertEquals("ContaInvestimento: Use o modificador private para o atributo montanteMinimo.",true,Modifier.isPrivate(c.getDeclaredFields()[1].getModifiers()));
        assertEquals("ContaInvestimento: Use o modificador private para o atributo depositoMinimo.",true,Modifier.isPrivate(c.getDeclaredFields()[2].getModifiers()));
    }
    
    @Test
    public void t04criarContaCorrenteSaldoZero() throws Exception{
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        double saldo = c.getSaldo();
        assertEquals("Era esperado um saldo zerado para conta criada.",0.0, saldo,0.0);
    }
    
    @Test
    public void t05criaContaCorrenteComSaldo2000() throws Exception {
        Cliente cliente = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 2000);
        assertEquals(conta,cliente.getContaCorrente());
        assertEquals(cliente.getContaCorrente().getSaldo(),2000.0,0.0);
    }
    
    @Test
    public void t06manipulaContaCorrenteDepositar50() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.deposita(50);
        double saldo = c.getSaldo();
        assertEquals(50.0, saldo,0.0);
    }
    
    @Test
    public void t07manipulaContaCorrenteDepositar100Deposita20Saca60() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.deposita(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.deposita(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        c.saca(80);
        assertEquals(40.0, c.getSaldo(),0.0);
    }
    
    @Test
    public void t08manipulaContaCorrenteDepositar100Deposita20Saca1000() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.deposita(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.deposita(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        c.saca(1000);
        assertEquals(-880.0, c.getSaldo(),0.0);
    }

    @Test
    public void t09manipulaContaCorrenteLimiteDepositar100Deposita20Saca1300() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.deposita(100);
        assertEquals(100.0, c.getSaldo(),0.0);
        c.deposita(20);
        assertEquals(120.0, c.getSaldo(),0.0);
        //Nesse caso a exceção deve ocorrer com a mensagem especificada. O teste fica verde quando a exceção ocorre.
        try{
            c.saca(1300);
            fail("Deveria ter ocorrido uma exceção pois houve uma tentativa de saque que passou do limite da conta: "
                    + "Valor Saque=1300 Saldo="+c.getSaldo()+" Limite="+c.getLimite());
        }catch(Exception ex){
            //Verifica a mensagem da exceção
            assertEquals("Verifique a mensagem da exceção: ",
                            "Saldo insuficiente na conta."+
                            "\nValor saque=1300.0"+
                            "\nSaldo="+c.getSaldo()+
                            "\nLimite="+c.getLimite(),
                            ex.getMessage());
        }
        //Verifica se a conta continua com 120
        assertEquals(120.0, c.getSaldo(),0.0);
    }
    
    @Test
    public void t10manipulaContaCorrenteDepositarNegativo50() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        
        //Depósito de valor negativo causa exceção. O teste é para ver se a exceção é lançada nesse caso. Se a exceção for lançada com a mensagem correta, o teste fica verde.
        try{
            c.deposita(-50);
            fail("Deveria ter levantado uma exceção, pois valor negativo para o depósito não é válido.");
        }catch(Exception ex){
            //Verifica a mensagem da exceção
            assertEquals("Valor do depósito não pode ser negativo ou zero. Valor=-50.0",ex.getMessage());            
        }
        //Verifica se a conta continua com 0
        assertEquals(0.0, c.getSaldo(),0.0);
    }
    @Test
    public void t11manipulaContaCorrenteSaqueNegativo100() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        
        //Depósito de valor negativo causa exceção. O teste é para ver se a exceção é lançada nesse caso. Se a exceção for lançada com a mensagem correta, o teste fica verde.
        try{
            c.saca(-100);
            fail("Deveria ter levantado uma exceção, pois valor negativo para o depósito não é válido.");
        }catch(Exception ex){
            //Verifica a mensagem da exceção
            assertEquals("Valor do saque não pode ser negativo ou zero. Valor=-100.0",ex.getMessage());            
        }
        //Verifica se a conta continua com 0
        assertEquals(0.0, c.getSaldo(),0.0);
    }
    
    @Test
    public void t12manipulaContaCorrenteDeposita100AplicaJuros() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.deposita(100);
        c.aplicaJuros();
        //Valor do saldo continua o mesmo, pois o saldo era igual ou superior a zero (100)
        assertEquals(100.0, c.getSaldo(),0.0);
        
    }
    
    @Test
    public void t13manipulaContaCorrenteSaca100AplicaJuros() throws Exception {
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        c.saca(100);
        c.aplicaJuros();
        //Nesse caso a conta está 100 negativa (usando o limite). Então aplicaremos o juros indicado no construtor (5%).
        assertEquals(-105.0, c.getSaldo(),0.0);
        
    }
    
    @Test
    public void t14trocaContaCorrenteDeCliente() throws Exception {
        Cliente cliente = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 2000);
        conta.saca(2000);
        ContaCorrente conta2 = new ContaCorrente(1000,0.05,-1, cliente, 1500);
        assertEquals(conta2,cliente.getContaCorrente());
        assertEquals(cliente.getContaCorrente().getSaldo(),1500.0,0.0);
    }
    
    @Test
    public void t15verificaSaldoZeroParaTrocarContaCorrente() throws Exception {
        Cliente cliente = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 2000);
        try{
            ContaCorrente conta2 = new ContaCorrente(1000,0.05,-1, cliente, 1500);
            fail("Deveria ter levantado exceção, pois a conta original do cliente está com saldo 2000 e não é zero. Para trocar de uma conta para outra o saldo precisa ser zero.");
        }catch(Exception ex){
            assertEquals("Mensagem da exceção está errada.","Não pode modificar a conta corrente, pois saldo da original não está zerado. "
                    + "Para fazer isso primeiro zere o saldo da conta do cliente. Saldo=2000.0",ex.getMessage());
        }
    }
    
    @Test
    public void t16criarContaInvestimento() throws Exception{
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        double saldo = c.getSaldo();
        assertEquals("Era esperado um saldo de 1000 para conta criada.",1000.0, saldo,0.0);
    }
    
    @Test
    public void t17manipularContaInvestimentoDepositoInicialMenorQueMontanteMinimo() throws Exception{
        try{
            ContaInvestimento c = new ContaInvestimento(0.02,1000,100,500,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
            fail("Deveria ter levantando exceção: Depósto Inicial não pode ser menor que montante mínimo.");
        }catch(Exception ex){
            assertEquals("Saldo não pode ser menor que montante mínimo.",ex.getMessage());
        }
    
    
    }
    
    @Test
    public void t18manipularContaInvestimentoDepositarMinimo() throws Exception {
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        c.deposita(100);
        double saldo = c.getSaldo();
        assertEquals(1100.0, saldo,0.0);
    }
    
    @Test
    public void t19manipularContaInvestimentoDepositar1000Sacar500() throws Exception {
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        c.deposita(1000);
        assertEquals(2000.0, c.getSaldo(),0.0);
        c.saca(500);
        assertEquals(1500.0, c.getSaldo(),0.0);
        c.saca(200);
        assertEquals(1300.0, c.getSaldo(),0.0);
    }
    
    @Test
    public void t20manipularContaInvestimentoDepositar1000Sacar1100() throws Exception {
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        c.deposita(1000);
        assertEquals(2000.0, c.getSaldo(),0.0);
        try{
            c.saca(1100);
            fail("Deveria ter levantado exceção: Saldo insuficiente para saque. Valor Saque=1100.0 Saldo=2000.0 Montante Minimo=1000.0");
        }catch(Exception ex){
            assertEquals("Saldo insuficiente para saque. Valor Saque=1100.0 Saldo=2000.0 Montante Minimo=1000.0",ex.getMessage());
        }
        assertEquals(2000.0, c.getSaldo(),0.0);
    }

    @Test
    public void t21manipularContaInvestimentoLimiteDepositarMenosQueMinimo() throws Exception {
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        try{
            c.deposita(90);
            fail("Deveria ter levantado exceção:");
        }catch(Exception ex){
            assertEquals("Valor do depóstio não atingiu o mínimo. Valor Depósito=90.0 Depóstio Mínimo=100.0",ex.getMessage());
        }
        assertEquals(1000.0, c.getSaldo(),0.0);

    }
 
    
    @Test
    public void t22manipularContaInvestimentoAplica1000AplicaJuros() throws Exception {
        ContaInvestimento c = new ContaInvestimento(0.02,1000,100,1000,-1, new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111"));
        c.deposita(1000);
        c.aplicaJuros();
        //Nesse caso a conta está 100 negativa (usando o limite). Então aplicaremos o juros indicado no construtor (5%).
        assertEquals(2040.0, c.getSaldo(),0.0);
        
    }    
    
     @Test
    public void t23crudClienteAdd() throws Exception {
        inicializaBD();
        //Adiciona cliente no BD
        Cliente c1 = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        cliDao.add(c1);
        //Verifica se o id gerado é 1. (Primeiro do BD)
        assertEquals("Quando inserir o cliente no BD, recupere o ID gerado e sete no objeto do cliente.",1, c1.getId());  
     }   

    @Test
    public void t24crudClienteGetById() throws Exception {
        inicializaBD();
        //Adiciona cliente no BD
        Cliente c1 = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        cliDao.add(c1);
        //Recupera cliente do BD por id
        Cliente c2 = cliDao.getById(c1.getId());
        assertEquals("Rafael", c2.getNome());
        assertEquals("333", c2.getCpf());
        assertEquals(LocalDate.of(2000, Month.MARCH, 1),c2.getDataNascimento());
     }

    @Test
    public void t25crudClienteUpdate() throws Exception {
        inicializaBD();
        //Adiciona cliente no BD
        Cliente c1 = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        cliDao.add(c1);
        //Atualiza cliente
        c1.setCartaoCredito("777");
        cliDao.update(c1);
        Cliente c2 = cliDao.getById(c1.getId());
        assertEquals("777", c2.getCartaoCredito());
     }
    
    @Test
    public void t26crudClienteDelete() throws Exception {
        inicializaBD();
        //Adiciona cliente no BD
        Cliente c1 = new Cliente(-1,  "Rafael", "333", LocalDate.of(2000, Month.MARCH, 1),"111");
        cliDao.add(c1);
        //Verifica se temos um cliente no total
        assertEquals(1,cliDao.getAll().size()); 
        //Exclui cliente
        cliDao.delete(c1);
        //Depois de exlcuir verifica se temos 0 clientes no total
        assertEquals(0,cliDao.getAll().size());   
        //Verifica se o objeto Cliente ficou com id=-1 (Isso indica que não está no BD)
        assertEquals(-1,c1.getId());  
     }

    @Test
    public void t27crudContaCorrenteAdd() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 500);
        conta.saca(200);
        cliDao.add(cliente);
        corDao.add(conta);
        //Verifica se o número da conta=1. (Primeiro do BD)
        assertEquals("Quando inserir a conta corrente no BD, recupere o ID gerado e sete no objeto da conta.",1, conta.getNumero());
    }

    @Test
    public void t28crudContaCorrenteGetById() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 500);
        conta.saca(200);
        cliDao.add(cliente);
        corDao.add(conta);
        //Recupera conta do BD por id
        ContaCorrente conta2 = corDao.getById(conta.getId());
        //Verifica os atributos da conta e do cliente
        assertEquals(1, conta2.getNumero());
        assertEquals("Marcelo", conta2.getCliente().getNome());
        assertEquals("111", conta2.getCliente().getCartaoCredito());
        assertEquals("0886", conta2.getCliente().getCpf());
        assertEquals(LocalDate.of(1995,2,3), conta2.getCliente().getDataNascimento());
        assertEquals(1, conta2.getCliente().getContaCorrente().getNumero());
        assertEquals(300.0, conta2.getSaldo(),0.0);
        assertEquals(1000.0, conta2.getLimite(),0.0);
        assertEquals(0.05, conta2.getTaxaJurosLimite(),0.0);
        assertEquals(1, conta2.getId());   
     }
    @Test
    public void t29crudContaCorrenteUpdate() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 500);
        conta.saca(200);
        cliDao.add(cliente);
        corDao.add(conta);

        //Atualiza conta (saldo)
        conta.deposita(400);
        conta.setLimite(1500);
        conta.setTaxaJurosLimite(0.06);
        corDao.update(conta);
        //Verofifica se ataulaizou no banco
        ContaCorrente conta2 = corDao.getById(conta.getId());
        assertEquals(700, conta2.getSaldo(),0.0);
        assertEquals(1500, conta2.getLimite(),0.0);
        assertEquals(0.06, conta2.getTaxaJurosLimite(),0.0); 
     }    
    @Test
    public void t30crudContaCorrenteDelete() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente conta = new ContaCorrente(1000,0.05,-1, cliente, 500);
        conta.saca(200);
        cliDao.add(cliente);
        corDao.add(conta);

        //Exclui cliente
        corDao.delete(conta);
        //Verifoca se a quantidade de contas correntes é zero.
        assertEquals(0,corDao.getAll().size());  
        //Verifica se a Conta Corrente ficou com id=-1 (Isso indica que não está no BD)
        assertEquals(-1,conta.getId());  
        
        
     }

        @Test
    public void t31crudContaInvestimentoAdd() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaInvestimento conta1 = new ContaInvestimento(0.02,1000,100,1500,-1, cliente);
        ContaInvestimento conta2 = new ContaInvestimento(0.01,2000,200,2500,-1, cliente);
        conta1.deposita(200);
        conta2.deposita(200);
        cliDao.add(cliente);
        invDao.add(conta1);
        invDao.add(conta2);
        
        //Verifica se o número da conta=1 e 2. (Primeiros do BD)
        assertEquals("No momento da dao adicionar a conta investimento no BD obtenha o número gerado pelo BD do ID e sete o id do objeto.",1, conta1.getNumero());
        assertEquals("No momento da dao adicionar a conta investimento no BD obtenha o número gerado pelo BD do ID e sete o id do objeto.",2, conta2.getNumero());

     }    

    @Test
    public void t32crudContaInvestimentoGetById() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaInvestimento conta1 = new ContaInvestimento(0.02,1000,100,1500,-1, cliente);
        ContaInvestimento conta2 = new ContaInvestimento(0.01,2000,200,2500,-1, cliente);
        conta1.deposita(200);
        conta2.deposita(200);
        cliDao.add(cliente);
        invDao.add(conta1);
        invDao.add(conta2);

        //Recupera conta do BD por id
        ContaInvestimento conta1BD = invDao.getById(conta1.getId());
        ContaInvestimento conta2BD = invDao.getById(conta2.getId());
       
        //Verifica os atributos da conta 1
        assertEquals(1, conta1BD.getNumero());
        assertEquals("Marcelo", conta1BD.getCliente().getNome());
        assertEquals("111", conta1BD.getCliente().getCartaoCredito());
        assertEquals("0886", conta1BD.getCliente().getCpf());
        assertEquals(LocalDate.of(1995,2,3), conta1BD.getCliente().getDataNascimento());
        assertEquals(1, conta1BD.getCliente().getContasInvestimento().get(0).getNumero());
        assertEquals(1700.0, conta1BD.getSaldo(),0.0);
        assertEquals(100.0, conta1BD.getDepositoMinimo(),0.0);
        assertEquals(1000.0, conta1BD.getMontanteMinimo(),0.0);
        assertEquals(0.02, conta1BD.getTaxaRemuneracaoInvestimento(),0.0);
        assertEquals(1, conta1BD.getId());
        //Verifica os atributos da conta 2
        assertEquals(2, conta2BD.getNumero());
        assertEquals("Marcelo", conta2BD.getCliente().getNome());
        assertEquals("111", conta2BD.getCliente().getCartaoCredito());
        assertEquals("0886", conta2BD.getCliente().getCpf());
        assertEquals(LocalDate.of(1995,2,3), conta2BD.getCliente().getDataNascimento());
        assertEquals(2, conta2BD.getCliente().getContasInvestimento().get(0).getNumero());
        assertEquals(2700.0, conta2BD.getSaldo(),0.0);
        assertEquals(200.0, conta2BD.getDepositoMinimo(),0.0);
        assertEquals(2000.0, conta2BD.getMontanteMinimo(),0.0);
        assertEquals(0.01, conta2BD.getTaxaRemuneracaoInvestimento(),0.0);
        assertEquals(2, conta2BD.getId());
        
   
     }     
    
        @Test
    public void t33crudContaInvestimentoUpdate() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaInvestimento conta1 = new ContaInvestimento(0.02,1000,100,1500,-1, cliente);
        ContaInvestimento conta2 = new ContaInvestimento(0.01,2000,200,2500,-1, cliente);
        conta1.deposita(200);
        conta2.deposita(200);
        cliDao.add(cliente);
        invDao.add(conta1);
        invDao.add(conta2);

        

        //Atualiza conta (saldo)
        conta1.saca(500);
        conta1.setDepositoMinimo(200);
        conta1.setMontanteMinimo(700);
        conta1.setTaxaRemuneracaoInvestimento(0.01);
        invDao.update(conta1);
        //Recupera conta atualizada do BD
        ContaInvestimento conta1BD = invDao.getById(conta1.getId());
        assertEquals(1200, conta1BD.getSaldo(),0.0);
        assertEquals(200, conta1BD.getDepositoMinimo(),0.0);
        assertEquals(700, conta1BD.getMontanteMinimo(),0.0); 
     }  
    
    @Test
    public void t34crudContaInvestimentoDelete() throws Exception {
        inicializaBD();
        //Adiciona conta no BD
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaInvestimento conta1 = new ContaInvestimento(0.02,1000,100,1500,-1, cliente);
        ContaInvestimento conta2 = new ContaInvestimento(0.01,2000,200,2500,-1, cliente);
        conta1.deposita(200);
        conta2.deposita(200);
        cliDao.add(cliente);
        invDao.add(conta1);
        invDao.add(conta2);
        
 
        //Exclui cota
        //Verifica se tem 2 na lista antes de exluir
        assertEquals(2,invDao.getAll().size());    
        invDao.delete(conta1);
        //Verifica se tem depois de excluir uma conta.
        assertEquals(1,invDao.getAll().size());    
     }    
    
    @Test
    public void t35verificaSeAContaCorrenteFoiSetadaNoCliente() throws Exception {
        inicializaBD();
        //Cria uma conta corrente
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        cliDao.add(cliente);
        corDao.add(c);
        //Pega a referência da conta corrente que está no cliente. Deve ser mesma. Ou seja c=c2.
        ContaCorrente c2 = corDao.getContaCorrenteByCliente(cliente);
        ContaCorrente c3 = c2.getCliente().getContaCorrente();
        //A conta do cliente deve ter o mesmo ID da conta que foi criada
        assertEquals("A conta do cliente deve ter o mesmo identificador da conta que foi craida. ", c.getId(),c3.getId());
        assertEquals("A conta do cliente deve ter o mesmo identificador da conta que foi craida. ", c2.getId(),c3.getId());
        
    }
    @Test
    public void t36manipulaSaldoDaContaCorrenteEGravaBdERecuperaSaldo() throws Exception {
        inicializaBD();
        //Cria uma conta corrente
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente c = new ContaCorrente(1000,0.05,-1, cliente, 0);
        cliDao.add(cliente);
        corDao.add(c);
        //Manipula Saldo
        cliente.getContaCorrente().deposita(2000);
        //Faz update da Conta
        corDao.update(cliente.getContaCorrente());
        //Recupera conta do BD
        ContaCorrente c2 = corDao.getContaCorrenteByCliente(cliente);
        //Verifica se Conta Corrente c é do mesmo id da c2
        assertEquals("A conta do cliente deve ter o mesmo identificador da conta que foi craida. ", c.getId(),c2.getId());
        //Verifica se o slado de c2 é 2000
        assertEquals(2000.0,c2.getSaldo(),0.0);
        
    }
    @Test
    public void t37cria4ContasSalvaNoBDeRecuperaTodas() throws Exception {
        inicializaBD();
        //1
        Cliente cl1 = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente co1 = new ContaCorrente(1000,0.05,-1, cl1, 300);   
        //2
        Cliente cl2 = new Cliente(-1,"Rodrigo","0884",LocalDate.of(1996,4,3),"112");
        ContaCorrente co2 = new ContaCorrente(500,0.04,-1, cl2, 200);          
        //3
        Cliente cl3 = new Cliente(-1,"Maria","081",LocalDate.of(1997,1,13),"113");
        ContaCorrente co3 = new ContaCorrente(1500,0.03,-1, cl3, 300);  
        //4
        Cliente cl4 = new Cliente(-1,"Viviane","089",LocalDate.of(1998,3,23),"114");
        ContaCorrente co4 = new ContaCorrente(3000,0.02,-1, cl4, 400);
        
        //Grava clientes no BD
        cliDao.add(cl1);
        cliDao.add(cl2);
        cliDao.add(cl3);
        cliDao.add(cl4);
        
        //Grava contas no BD
        corDao.add(co1);
        corDao.add(co2);
        corDao.add(co3);
        corDao.add(co4);
        
        //Recupera todas do BD
        List<ContaCorrente> lista = corDao.getAll();
        //Verifica se temos 4 contas recuperadas
        assertEquals(4, lista.size());
        //Verifica a soma dos saldos de todas essas contas
        double somaSaldo=0.0;
        for(ContaCorrente cor:lista){
            somaSaldo += cor.getSaldo();
        }
        assertEquals(1200.0,somaSaldo,0.0);
    }

    @Test
    public void t38testaContaCorrenteDeleteAll() throws Exception {
        inicializaBD();
        //1
        Cliente cl1 = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente co1 = new ContaCorrente(1000,0.05,-1, cl1, 300);   
        //2
        Cliente cl2 = new Cliente(-1,"Rodrigo","0884",LocalDate.of(1996,4,3),"112");
        ContaCorrente co2 = new ContaCorrente(500,0.04,-1, cl2, 200);          
        //3
        Cliente cl3 = new Cliente(-1,"Maria","081",LocalDate.of(1997,1,13),"113");
        ContaCorrente co3 = new ContaCorrente(1500,0.03,-1, cl3, 300);  
        //4
        Cliente cl4 = new Cliente(-1,"Viviane","089",LocalDate.of(1998,3,23),"114");
        ContaCorrente co4 = new ContaCorrente(3000,0.02,-1, cl4, 400);
        
        //Grava clientes no BD
        cliDao.add(cl1);
        cliDao.add(cl2);
        cliDao.add(cl3);
        cliDao.add(cl4);
        
        //Grava contas no BD
        corDao.add(co1);
        corDao.add(co2);
        corDao.add(co3);
        corDao.add(co4);
        
        //Recupera todas do BD
        List<ContaCorrente> lista = corDao.getAll();
        //Verifica se temos 4 contas recuperadas
        assertEquals(4, lista.size());
        
        //Testa o deleteAll
        corDao.deleteAll();
        //Recupera todas do BD
        List<ContaCorrente> lista2 = corDao.getAll();
        //Verifica se temos 0 contas recuperadas
        assertEquals(0, lista2.size());  
        
    }
  
    @Test
    public void t39verificaSeAContaInvestimentoFoiSetadaNoCliente() throws Exception {
        inicializaBD();
        Cliente cliente = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        //Cria uma conta corrente
        ContaInvestimento contaInv1 = new ContaInvestimento(0.02,1000,100,1000,-1, cliente);
        ContaInvestimento contaInv2 = new ContaInvestimento(0.01,5000,500,5000,-1, cliente);
        //Pega a referência da conta corrente que está no cliente. Deve ser mesma. Ou seja c=c2.
        ContaInvestimento contaInv11 = contaInv1.getCliente().getContasInvestimento().get(0);
        //A conta do cliente deve ter a mesma referência da conta que foi criada.
        assertEquals("A conta do cliente deve ter a mesma referência da conta que foi criada. "
                + "Na última linha do construtor da ContaCorrente sete a referência da ContaCorrente no cliente.", contaInv1,contaInv11);

        
        cliDao.add(cliente);
        invDao.add(contaInv1);
        invDao.add(contaInv2);
        //Pega as contas investimentos de um cliente
        List<ContaInvestimento> contasInvestimentos = invDao.getContasInvestimentoByCliente(cliente);
        //Verifica se carregou o Cliente na conta Investimento
        assertEquals("Marcelo", contasInvestimentos.get(0).getCliente().getNome());
        assertEquals("Marcelo", contasInvestimentos.get(1).getCliente().getNome());
        //Verifica se carregou as Contas Investementos no cliente
        assertEquals(2, cliente.getContasInvestimento().size());
        //Verifica se a soma dos saldos bate
        assertEquals(6000.0, cliente.getSaldoTotalCliente(),0.0);
        
        
        Cliente clienteBD = cliDao.getById(cliente.getId());
        List<ContaInvestimento> lista = clienteBD.getContasInvestimento();
        
        
    }
    
    @Test
    public void t40testaContaInvestimentoDeleteAll() throws Exception {
        inicializaBD();
        //1
        Cliente cl1 = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaInvestimento ci1 = new ContaInvestimento(0.05,1000,500,1000,-1, cl1);   
        //2
        ContaInvestimento ci2 = new ContaInvestimento(0.06,2000,600,2000,-1, cl1);            
        //3
        ContaInvestimento ci3 = new ContaInvestimento(0.07,3000,700,3000,-1, cl1);     
        //4
        ContaInvestimento ci4 = new ContaInvestimento(0.08,4000,800,4000,-1, cl1);   
        
        //Grava clientes no BD
        cliDao.add(cl1);
        
        //Grava contas no BD
        invDao.add(ci1);
        invDao.add(ci2);
        invDao.add(ci3);
        invDao.add(ci4);
        
        //Recupera todas do BD
        List<ContaInvestimento> lista = invDao.getAll();
        //Verifica se temos 4 contas recuperadas
        assertEquals(4, lista.size());
        
        //Testa o deleteAll
        invDao.deleteAll();
        //Recupera todas do BD
        List<ContaInvestimento> lista2 = invDao.getAll();
        //Verifica se temos 0 contas recuperadas
        assertEquals(0, lista2.size());  
        
    }
    
    @Test
    public void t41testaSeODeleteAllDaContaCorrenteNaoEliminaTodasAsContasInvestiemntosDoCliente() throws Exception {
        inicializaBD();
        //Cria 4 contas correntes (uma para cada cliente)
        //1
        Cliente cl1 = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente co1 = new ContaCorrente(1000,0.05,-1, cl1, 300);   
        //2
        Cliente cl2 = new Cliente(-1,"Rodrigo","0884",LocalDate.of(1996,4,3),"112");
        ContaCorrente co2 = new ContaCorrente(500,0.04,-1, cl2, 200);          
        //3
        Cliente cl3 = new Cliente(-1,"Maria","081",LocalDate.of(1997,1,13),"113");
        ContaCorrente co3 = new ContaCorrente(1500,0.03,-1, cl3, 300);  
        //4
        Cliente cl4 = new Cliente(-1,"Viviane","089",LocalDate.of(1998,3,23),"114");
        ContaCorrente co4 = new ContaCorrente(3000,0.02,-1, cl4, 400);
        
        //Grava clientes no BD
        cliDao.add(cl1);
        cliDao.add(cl2);
        cliDao.add(cl3);
        cliDao.add(cl4);
        
        //Grava contas no BD
        corDao.add(co1);
        corDao.add(co2);
        corDao.add(co3);
        corDao.add(co4);


        //Cria  4 Contas Investimento para o primeiro cliente.
        ContaInvestimento ci1 = new ContaInvestimento(0.05,1000,500,1000,-1, cl1);   
        //2
        ContaInvestimento ci2 = new ContaInvestimento(0.06,2000,600,2000,-1, cl1);            
        //3
        ContaInvestimento ci3 = new ContaInvestimento(0.07,3000,700,3000,-1, cl1);     
        //4
        ContaInvestimento ci4 = new ContaInvestimento(0.08,4000,800,4000,-1, cl1);   
        
        //Grava contas no BD
        invDao.add(ci1);
        invDao.add(ci2);
        invDao.add(ci3);
        invDao.add(ci4);
        
        //Recupera todas as contas correntes do BD
        List<ContaCorrente> listaContaCorrente = corDao.getAll();
        //Recupera todas  as contas investimento do BD
        List<ContaInvestimento> listaContasInvestimento = invDao.getAll();
        //Verifica se temos 4 contas correntes recuperadas
        assertEquals(4, listaContaCorrente.size());
        //Verifica se temos 4 contas investimento recuperadas
        assertEquals(4, listaContasInvestimento.size());
       
        //Exclui as contas correntes.
        corDao.deleteAll();
        //Recupera novamente as 2 listas (contas correntes e investimentos)
        List<ContaCorrente> listaContaCorrente2 = corDao.getAll();
        List<ContaInvestimento> listaContasInvestimento2 = invDao.getAll();
        //Verifica se temos 0 contas correntes recuperadas
        assertEquals(0, listaContaCorrente2.size());  
        //Verifica se as 4 contas investimento continuam lá
        assertEquals(4, listaContasInvestimento2.size());          
        
    }
    
    @Test
    public void t42testaSeODeleteAllDaContaInvestimentoNaoEliminaTodasAsContasCorrentesDoCliente() throws Exception {
        inicializaBD();
        //Cria 4 contas correntes (uma para cada cliente)
        //1
        Cliente cl1 = new Cliente(-1,"Marcelo","0886",LocalDate.of(1995,2,3),"111");
        ContaCorrente co1 = new ContaCorrente(1000,0.05,-1, cl1, 300);   
        //2
        Cliente cl2 = new Cliente(-1,"Rodrigo","0884",LocalDate.of(1996,4,3),"112");
        ContaCorrente co2 = new ContaCorrente(500,0.04,-1, cl2, 200);          
        //3
        Cliente cl3 = new Cliente(-1,"Maria","081",LocalDate.of(1997,1,13),"113");
        ContaCorrente co3 = new ContaCorrente(1500,0.03,-1, cl3, 300);  
        //4
        Cliente cl4 = new Cliente(-1,"Viviane","089",LocalDate.of(1998,3,23),"114");
        ContaCorrente co4 = new ContaCorrente(3000,0.02,-1, cl4, 400);
        
        //Grava clientes no BD
        cliDao.add(cl1);
        cliDao.add(cl2);
        cliDao.add(cl3);
        cliDao.add(cl4);
        
        //Grava contas corrente no BD
        corDao.add(co1);
        corDao.add(co2);
        corDao.add(co3);
        corDao.add(co4);


        //Cria  4 Contas Investimento para o primeiro cliente.
        ContaInvestimento ci1 = new ContaInvestimento(0.05,1000,500,1000,-1, cl1);   
        //2
        ContaInvestimento ci2 = new ContaInvestimento(0.06,2000,600,2000,-1, cl1);            
        //3
        ContaInvestimento ci3 = new ContaInvestimento(0.07,3000,700,3000,-1, cl1);     
        //4
        ContaInvestimento ci4 = new ContaInvestimento(0.08,4000,800,4000,-1, cl1);   
        
        //Grava contas investimento no BD
        invDao.add(ci1);
        invDao.add(ci2);
        invDao.add(ci3);
        invDao.add(ci4);
        
        //Recupera todas as contas correntes do BD
        List<ContaCorrente> listaContaCorrente = corDao.getAll();
        //Recupera todas  as contas investimento do BD
        List<ContaInvestimento> listaContasInvestimento = invDao.getAll();
        //Verifica se temos 4 contas correntes recuperadas
        assertEquals(4, listaContaCorrente.size());
        //Verifica se temos 4 contas investimento recuperadas
        assertEquals(4, listaContasInvestimento.size());
       
        //Exclui as contas investimento.
        invDao.deleteAll();
        //Recupera novamente as 2 listas (contas correntes e investimentos)
        List<ContaCorrente> listaContaCorrente2 = corDao.getAll();
        List<ContaInvestimento> listaContasInvestimento2 = invDao.getAll();
        //Verifica se temos 4 contas correntes recuperadas
        assertEquals(4, listaContaCorrente2.size());  
        //Verifica se as 4 contas investimento foram apagadas
        assertEquals(0, listaContasInvestimento2.size());          
        
    }

}

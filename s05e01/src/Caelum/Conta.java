package Caelum;
public class Conta {
    private String titular;
    private int identificador;
    static int nextId = 0;
    private float saldo;

    public Conta(String titular) {
        this.setTitular(titular);
        this.identificador = nextId;
        nextId += 1;
    }

    public Conta() {
        this.identificador = nextId;
        nextId += 1;
    }
 
    public int getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void sacar(Conta conta, float dinheiro){
        if(dinheiro > conta.saldo){
            System.out.println("Você não tem dinheiro em conta.");
        }else{
            conta.saldo -= dinheiro;
            System.out.println("Saque efetuado com sucesso.");
        }
    }
    
    public void depositar(Conta conta, float dinheiro){
        conta.saldo =+ dinheiro;
        System.out.println("O deposito foi efetuado com sucesso.");
    }
    
    public void status(){
        System.out.println("Titular: " + this.titular);
        System.out.println("Indentificador " + this.identificador);
        
    }
    
}

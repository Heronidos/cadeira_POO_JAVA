package s09e01;

import java.util.ArrayList;
import java.util.Scanner;

class Agencia {

    private ArrayList<Cliente> clientes;
    private ArrayList<Conta> contas;

    public Agencia() {
        this.clientes = new ArrayList();
        this.contas = new ArrayList();
    }

    public boolean buscar(int identificador) {

        for (Conta c : contas) {
            if (identificador == c.getIdentificador()) {
                return true;
            }
        }
        System.out.println("fail: conta nao encontrada");
        return false;
    }

    public void depositar(int identificador, double dinheiro) {
        if (this.buscar(identificador)) {
            this.contas.get(identificador).setSaldo(this.contas.get(identificador).getSaldo() + dinheiro);
        }
    }

    public boolean sacar(int identificador, double dinheiro) {
        if (this.buscar(identificador)) {
            if(this.contas.get(identificador).getSaldo() >= dinheiro){
                this.contas.get(identificador).setSaldo(this.contas.get(identificador).getSaldo() - dinheiro);
                return true;
            }else{
                System.out.println("fail: saldo insuficiente");
            }
        }
        return false;
    }
    
    public void transferir(int identificador1, int identificador2, double dinheiro){
        if (this.buscar(identificador1) && this.buscar(identificador2)){
            if(this.sacar(identificador1, dinheiro)){
                this.depositar(identificador2, dinheiro);
                
            }
        }
    }

    @Override
    public String toString() {
        String saida = "";
        for (Conta c : this.contas) {
            saida += c + "\n";
        }
        return saida;
    }

    public void addCliente(String idCliente) {
        for (Cliente c : clientes) {
            if (idCliente.equals(c.getIdCliente())) {
                System.out.println("fail: cliente já cadastrado");
                return;
            }
        }
        Cliente c = new Cliente(idCliente);
        Conta corrente = new ContaCorrente(idCliente);
        Conta poupanca = new ContaPoupanca(idCliente);
        this.clientes.add(c);
        c.addConta(corrente);
        c.addConta(poupanca);
        this.contas.add(corrente);
        this.contas.add(poupanca);
    }
}

abstract class Conta {

    static int nextId = 0;
    private int identificador;
    private String tipo;
    private String dono;
    private double saldo;

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return this.getIdentificador() + ":" + this.getDono() + ":" + this.getSaldo() + ":" + this.getTipo();
    }
}

class ContaCorrente extends Conta {

    public ContaCorrente(String idCliente) {
        this.setTipo("CC");
        this.setIdentificador(nextId);
        this.setDono(idCliente);
        nextId++;
    }

}

class ContaPoupanca extends Conta {

    public ContaPoupanca(String idCliente) {
        this.setTipo("CP");
        this.setIdentificador(nextId);
        this.setDono(idCliente);
        nextId++;
    }
}

class Cliente {

    private String idCliente;
    private ArrayList<Conta> contas;

    public String getIdCliente() {
        return idCliente;

    }

    public Cliente(String idCliente) {
        this.idCliente = idCliente;
        this.contas = new ArrayList();
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }
}

public class Controller {

    public static void main(String[] args) {
        Agencia bradesco = new Agencia();
        Scanner captInfor = new Scanner(System.in);
        String vetOption[];

        OUTER:
        while (true) {
            vetOption = (captInfor.nextLine()).split(" ");
            switch (vetOption[0]) {
                case "deposito":
                    bradesco.depositar(Integer.parseInt(vetOption[1]), Double.parseDouble(vetOption[2]));
                    break;
                case "saque":
                    bradesco.sacar(Integer.parseInt(vetOption[1]), Double.parseDouble(vetOption[2]));
                    break;
                case "transf":
                    bradesco.transferir(Integer.parseInt(vetOption[1]), Integer.parseInt(vetOption[2]), Double.parseDouble(vetOption[3]));
                    break;
                case "addCli":
                    bradesco.addCliente(vetOption[1]);
                    break;
                case "show":
                    System.out.println(bradesco);
                    break;
                case "end":
                    break OUTER;
                default:
                    System.out.println("fail: comando inválido");
                    break;
            }

        }
    }

}

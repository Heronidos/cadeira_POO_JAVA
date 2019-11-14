package s11.agiota;

import static java.lang.System.in;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Sistema {

    private Map<String, Cliente> clientes;
    private Map<Integer, Transacao> transacoes;
    private double saldo;

    public Sistema(double saldo) {
        this.clientes = new TreeMap<>();
        this.transacoes = new TreeMap<>();
        this.saldo = saldo;
    }
    
    
}

class Transacao {

    static int nextId = 0;
    private final int id;
    private final String idCliente;
    private final double valor;

    public Transacao(String idCliente, double valor) {
        this.id = nextId;
        this.idCliente = idCliente;
        this.valor = valor;
        nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String retorno = "id:" + id + "[" + idCliente + " " + valor + "]";
        return retorno;
    }

}

class Cliente {

    private final String clienteId;
    private final String nome;
    private double saldo;
    private Map<Integer, Transacao> transacoes;

    public Cliente(String clienteId, String nome) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.saldo = 0;
        this.transacoes = new TreeMap<>();
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        String cliente = "";

        return cliente;
    }

}

public class Controller {

    public static void main(String[] args) {

        Scanner capInfor = new Scanner(in);
        String vetCapInfor[];

        OUTER:
        while (true) {
            vetCapInfor = (capInfor.nextLine().split(" "));
            Sistema sistema = new Sistema(0);
            switch (vetCapInfor[0]) {
                case "init":
                    sistema = new Sistema(Double.parseDouble(vetCapInfor[1]));
                    break;
                case "addCli":
                    String nome = "";
                    for (int i = 2; i < vetCapInfor.length; i++) {
                        nome = " " + vetCapInfor[i];
                    }
                    //sistema.addCliente(vetCapInfor[1], nome);
                    break;
                case "historico":
                    //sistema.historico();
                    break;
                case "filtrar":

                    break;
                case "emprestar":
                    //sistema.emprestar(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "receber":
                    //sistema.receber(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "end":
                    break OUTER;
                default:
                    System.out.println("fail: comando invalido");

            }
        }
    }

}

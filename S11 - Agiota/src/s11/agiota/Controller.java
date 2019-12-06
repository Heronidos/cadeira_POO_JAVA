package s11.agiota;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Sistema {

    Repositorio<String, Cliente> clientes;
    Repositorio<Integer, Transacao> transacoes;
    private double saldo;

    public Sistema(double saldo) {
        this.clientes = new Repositorio("cliente");
        this.transacoes = new Repositorio("transacao");
        this.saldo = saldo;
    }

    public void emprestar(String chave, double valor) {
        if (this.saldo < valor) {
            //throw new RuntimeException("fail: fundos insuficientes");
            System.out.println("fail: fundos insuficientes");
        } else {
            Transacao transacao = new Transacao(chave, valor);
            this.saldo = this.saldo - valor;
            this.clientes.get(chave).receber(valor);
            this.clientes.get(chave).transacoes.add(transacao.getId(), transacao);
            this.transacoes.add(transacao.getId(), transacao);
        }
    }

    public void receber(String chave, double valor) {
        valor = valor * -1;
        Transacao transacao = new Transacao(chave, valor);
        this.saldo = this.saldo - valor;
        this.clientes.get(chave).receber(valor);
        this.clientes.get(chave).transacoes.add(transacao.getId(), transacao);
        this.transacoes.add(transacao.getId(), transacao);

    }

    public String resumo() {
        return this.clientes + "saldo : " + this.saldo;
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
    Repositorio<Integer, Transacao> transacoes;

    public Cliente(String clienteId, String nome) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.saldo = 0;
        this.transacoes = new Repositorio("transacao");
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

    public void receber(double valor) {
        this.saldo += valor;
    }

    @Override
    public String toString() {
        return this.clienteId + " : " + this.nome + " : " + this.saldo;
    }

}

class Repositorio<K, V> {

    String tipo;
    Map<K, V> infor;

    public Repositorio(String typename) {
        this.tipo = typename;
        this.infor = new TreeMap();
    }

    boolean exists(K k) {
        return this.infor.get(k) != null;
    }

    void add(K k, V v) {
        V value = this.infor.get(k);
        if (value != null) {
            System.out.println(this.tipo + " " + k + " ja existe");
            //throw new RuntimeException(this.typename + " " + k + " ja existe");
        } else {
            this.infor.put(k, v);
            System.out.println("done");
        }
    }

    V get(K k) {
        V value = this.infor.get(k);
        if (value == null) {
            System.out.println(infor);
            System.out.println(this.tipo + " " + k + " nao existe");
            //throw new RuntimeException(this.typename + " " + k + " nao existe");
        }
        return value;
    }

    V remove(K k) {
        V value = this.infor.remove(k);
        if (value == null) {
            System.out.println(this.tipo + " " + k + " nao existe");
            //throw new RuntimeException(this.typename + " " + k + " nao existe");
        }
        return value;
    }

    ArrayList<V> getAll() {
        ArrayList<V> retorno = new ArrayList();
        for (K chave : this.infor.keySet()) {
            retorno.add(infor.get(chave));
        }
        return retorno;
    }

    public String toString() {
        String saida = "";
        for (V v : this.getAll()) {
            saida += v + "\n";
        }
        return saida;
    }
}

public class Controller {

    public static void main(String[] args) {

        Scanner capInfor = new Scanner(in);
        String vetCapInfor[];
        Sistema sistema = new Sistema(0);

        /*Cliente cliente = new Cliente("Heronidos", "Heron Veríssimo");
         sistema.clientes.add("Heronidos", cliente);
         sistema.emprestar("Heronidos", 500);
         sistema = new Sistema(1500);
         sistema.clientes.add("Heronidos", cliente);
         sistema.emprestar("Heronidos", 500);
         System.out.println(sistema.clientes.get("Heronidos"));
         System.out.println(sistema.transacoes);
         System.out.println(sistema.clientes);*/
        OUTER:
        while (true) {

            vetCapInfor = (capInfor.nextLine().split(" "));

            switch (vetCapInfor[0]) {
                case "init":
                    sistema = new Sistema(Double.parseDouble(vetCapInfor[1]));
                    break;
                case "addCli":
                    String nome = "";
                    for (int i = 2; i < vetCapInfor.length; i++) {
                        nome += vetCapInfor[i] + " ";
                    }
                    sistema.clientes.add(vetCapInfor[1], new Cliente(vetCapInfor[1], nome));
                    break;
                case "emprestar":
                    sistema.emprestar(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "resumo":
                    System.out.println(sistema.resumo());
                    break;
                case "historico":
                    System.out.println(sistema.transacoes);
                    break;
                case "filtrar":
                    System.out.println(sistema.clientes.get(vetCapInfor[1]).transacoes + "saldo: " + sistema.clientes.get(vetCapInfor[1]).getSaldo());
                    break;
                case "receber":
                    sistema.receber(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "end":
                    break OUTER;
                default:
                    System.out.println("fail: comando invalido");

            }
        }

    }

}

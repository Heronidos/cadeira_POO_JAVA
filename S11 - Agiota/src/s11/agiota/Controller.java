package s11.agiota;

import static java.lang.System.in;
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
    
    public void emprestar(String chave, double valor){
        Transacao transacao = new Transacao(chave, valor);
        this.saldo = this.saldo - valor;
        System.out.println(this.clientes.get(chave));
        //this.clientes.get(chave).receber(valor);
        this.clientes.get(chave).transacoes.add(transacao.getId(), transacao);
        this.transacoes.add(transacao.getId(), transacao);
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

    public void receber(double valor){
        this.saldo += valor;
    }
    
    @Override
    public String toString() {
        return this.clienteId + " : " + this.nome + ": " + this.saldo;
    }

}

class Repositorio<K, V>{
	String typename;
	Map<K, V> data = new TreeMap<K, V>();
	public Repositorio(String typename) {
		this.typename = typename;
	}
	
	boolean exists(K k) {
		return this.data.get(k) != null;
	}
	
	void add(K k, V t) {
		V value = this.data.get(k);
		if(value != null){
			throw new RuntimeException(this.typename + " " + k + " ja existe");
                }else{
                    this.data.put(k, t);
                    System.out.println("done");
                }
	}
	
	V get(K k) {
		V value = this.data.get(k);
		if(value == null)
			throw new RuntimeException(this.typename + " " + k + " nao existe");
		return value;
	}
	
	V remove(K k) {
		V value = this.data.remove(k);
		if(value == null)
			throw new RuntimeException(this.typename + " " + k + " nao existe");
		return value;
	}
	Collection<V> getAll(){
		return this.data.values();
	}
	public String toString() {
		String saida = "[ ";
		for(K key : this.data.keySet())
			saida += key + " ";
		return saida + "]";
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
                        nome += vetCapInfor[i] + " ";
                    }
                    sistema.clientes.add(vetCapInfor[1], new Cliente(vetCapInfor[1], nome));
                    break;
                case "historico":
                    //sistema.historico();
                    break;
                case "filtrar":

                    break;
                case "emprestar":
                    sistema.emprestar(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "receber":
                    //sistema.receber(vetCapInfor[1], Double.parseDouble(vetCapInfor[2]));
                    break;
                case "mostrar":
                    System.out.println(sistema.clientes);
                    break;
                case "end":
                    break OUTER;
                default:
                    System.out.println("fail: comando invalido");

            }
        }
    }

}

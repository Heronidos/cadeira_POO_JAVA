package s13.whatsapp.v2;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

class Grupo {

    Repositorio<NaCaixa> msgNaCaixa;
    private final String nome;
    Repositorio<Usuario> usuarios;

    public Grupo(String nome, Usuario usuario) {
        this.nome = nome;
        this.msgNaCaixa = new Repositorio<>("Mensagens nao lidas");
        this.msgNaCaixa.add(usuario.getNome(), new NaCaixa(usuario));
        this.usuarios = new Repositorio<>("Usuario");
        this.usuarios.add(usuario.getNome(), usuario);
        usuario.grupos.add(nome, this);

    }

    public String getNome() {
        return nome;
    }

    public String mostrarUsuarios() {
        String retorno = "[ ";
        for (Usuario u : usuarios.getAll()) {
            retorno += u.getNome() + " ";
        }
        retorno += "]";

        return retorno;
    }

    public void adcUsuario(Usuario usuario) {
        this.usuarios.add(usuario.getNome(), usuario);
        this.msgNaCaixa.add(usuario.getNome(), new NaCaixa(usuario));
        usuario.grupos.add(nome, this);
    }

    public void entregarMsg(Mensagem mensagem) {
        ArrayList<Mensagem> a = new ArrayList<>();
        for (NaCaixa n : msgNaCaixa.getAll()) {
            if (n.getUsuario().getNome() != mensagem.getNomeUsuario()) {
                if (this.msgNaCaixa.get(n.getUsuario().getNome()).getMensagens() != null) {
                    a = this.msgNaCaixa.get(n.getUsuario().getNome()).getMensagens();
                }
                a.add(mensagem);
                this.msgNaCaixa.get(n.getUsuario().getNome()).setMensagens(a);
            }
        }
    }

    public int contarNaCaixa(String nomeUsuario) {
        int retorno = 0;
        retorno = this.msgNaCaixa.get(nomeUsuario).getMensagens().size();
        return retorno;
    }
}

class ConversaIndividual extends Grupo{

    
    public ConversaIndividual(String nome, Usuario remetente, Usuario correspondente) {
        super(nome, remetente);
    }

}

class Notificacao {

    private String nomeGrupo;
    private int contadorNaoLidas;

    public Notificacao(String nomeGrupo, int contadorNaoLidas) {
        this.nomeGrupo = nomeGrupo;
        this.contadorNaoLidas = contadorNaoLidas;
    }

    @Override
    public String toString() {
        if (contadorNaoLidas > 0) {
            return (nomeGrupo + "(" + contadorNaoLidas + ")");
        } else {
            return nomeGrupo;
        }
    }

}

class NaCaixa {

    private Usuario usuario;
    private ArrayList<Mensagem> mensagens;

    public NaCaixa(Usuario usuario) {
        this.usuario = usuario;
        this.mensagens = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(ArrayList<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < this.mensagens.size(); i++) {
            if (i == this.mensagens.size() - 1) {
                retorno += this.mensagens.get(i);
            } else if (this.mensagens.size() == 0) {
                retorno += "nao ha novas mensagens";
            } else {
                retorno += this.mensagens.get(i) + "\n";
            }
        }
        return retorno;
    }

}

class Mensagem {

    private final String nomeUsuario;
    private final String texto;

    public Mensagem(String nomeUsuario, String texto) {
        this.nomeUsuario = nomeUsuario;
        this.texto = texto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return ("[" + nomeUsuario + ": " + texto + "]");
    }

}

class Usuario {

    Repositorio<Grupo> grupos;
    private final String nome;

    public Usuario(String nome) {
        this.nome = nome;
        this.grupos = new Repositorio<>("Grupo");
    }

    public String getNome() {
        return nome;
    }

    public String mostrarGrupos() {
        String retorno = "[ ";
        for (Grupo g : grupos.getAll()) {
            retorno += g.getNome() + " ";
        }
        retorno += "]";

        return retorno;
    }

    public void sair(String grupo) {
        this.grupos.get(grupo).msgNaCaixa.remove(this.nome);
        this.grupos.get(grupo).usuarios.remove(this.nome);
        this.grupos.remove(grupo);

    }

    public void enviarMensagem(String grupo, String strMensagem) {
        Mensagem mensagem = new Mensagem(this.nome, strMensagem);
        this.grupos.get(grupo).entregarMsg(mensagem);

    }

    public void lerMensagens(String grupo) {
        if (this.grupos.get(grupo).msgNaCaixa.get(this.nome) != null) {
            System.out.println(this.grupos.get(grupo).msgNaCaixa.get(this.nome));
            this.grupos.get(grupo).msgNaCaixa.get(this.nome).getMensagens().clear();
        }

    }

    public void mostrarNotificacoes() {
        String notificacoes = "[ ";
        for (Grupo g : this.grupos.getAll()) {
            Notificacao notificacao = new Notificacao(g.getNome(), g.contarNaCaixa(this.nome));
        
            notificacoes += notificacao + " ";
        }
        notificacoes += "]";
        System.out.println(notificacoes);
    }
}

class Whatsapp {

    Repositorio<Usuario> usuarios;
    Repositorio<Conversa> conversas ;

    public Whatsapp() {
        this.usuarios = new Repositorio<>("Usuario");
        this.grupos = new Repositorio<>("Grupo");
    }

    public void fazerConvite(String usuario, String convidado, String grupo) {
        if (this.grupos.get(grupo).usuarios.get(usuario) != null) {
            this.grupos.get(grupo).adcUsuario(this.usuarios.get(convidado));
        }
    }

    public void mostrarUsuarios() {
        String retorno = "[ ";

        for (Usuario u : usuarios.getAll()) {
            retorno += u.getNome() + " ";
        }

        retorno += "]";
        System.out.println(retorno);
    }

}

class Repositorio<T> {

    Map<String, T> objetos;
    String nomeTipo;

    public Repositorio(String nomeTipo) {
        objetos = new TreeMap<>();
        this.nomeTipo = nomeTipo;
    }

    public void add(String chave, T coisa) {
        T t = objetos.get(chave);
        if (t == null) {
            objetos.put(chave, coisa);
            System.out.println("done");
        } else {
            throw new RuntimeException(nomeTipo + " " + chave + " ja existe.");
            //System.out.println(nomeTipo + " " + chave + " ja existe.");
        }
    }

    public void remove(String chave) {
        this.get(chave);
        objetos.remove(chave);
        System.out.println("done");
    }

    public T get(String chave) {
        T t = objetos.get(chave);
        if (t == null) {
            throw new RuntimeException(nomeTipo + " " + chave + " nao existe.");
            //System.out.println(nomeTipo + " " + chave + " nao existe.");
        }
        return t;
    }

    public ArrayList<T> getAll() {
        ArrayList<T> retorno = new ArrayList<>();
        for (String chave : objetos.keySet()) {
            retorno.add(objetos.get(chave));
        }
        return retorno;
    }
}

public class Controller {

    public static void main(String[] args) {
        Whatsapp whatsapp = new Whatsapp();
        Scanner capInfor = new Scanner(System.in);
        String capInforVet[];
        Usuario usuario;
        OUTER:
        while (true) {
            try {
                capInforVet = ((capInfor.nextLine()).split(" "));
                switch (capInforVet[0]) {
                    case "adcUsuario":
                        whatsapp.usuarios.add(capInforVet[1], new Usuario(capInforVet[1]));
                        break;
                    case "todosUsuarios":
                        whatsapp.mostrarUsuarios();
                        break;
                    case "novoGrupo":
                        whatsapp.grupos.add(capInforVet[2], new Grupo(capInforVet[2], whatsapp.usuarios.get(capInforVet[1])));
                        break;
                    case "fazerConvite":
                        whatsapp.fazerConvite(capInforVet[1], capInforVet[2], capInforVet[3]);
                        break;
                    case "mostrarGrupos":
                        System.out.println((whatsapp.usuarios.get(capInforVet[1])).mostrarGrupos());
                        break;
                    case "usuariosDoGrupo":
                        System.out.println((whatsapp.grupos.get(capInforVet[1])).mostrarUsuarios());
                        break;
                    case "tirar":
                        whatsapp.usuarios.get(capInforVet[1]).sair(capInforVet[2]);
                        break;
                    case "enviarMensagem":
                        for (int i = 4; i < capInforVet.length; i++) {
                            capInforVet[3] += " " + capInforVet[i];
                        }
                        whatsapp.usuarios.get(capInforVet[1]).enviarMensagem(capInforVet[2], capInforVet[3]);
                        break;
                    case "lerMensagens":
                        whatsapp.usuarios.get(capInforVet[1]).lerMensagens(capInforVet[2]);
                        break;
                    case "verNotificacoes":
                        whatsapp.usuarios.get(capInforVet[1]).mostrarNotificacoes();
                        break;
                    case "fim":
                        break OUTER;
                    default:
                        System.out.println("Comando invalido.");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
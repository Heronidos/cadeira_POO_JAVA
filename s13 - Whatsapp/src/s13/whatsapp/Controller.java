package s13.whatsapp;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

class Grupo {

    private String nome;
    Repositorio<Usuario> usuarios;

    public Grupo(String nome, Usuario usuario) {
        this.nome = nome;
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
}

class Usuario {

    Repositorio<Grupo> grupos;
    private String nome;

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
    
    public void sair(String grupo){
        this.grupos.get(grupo).usuarios.remove(this.nome);
        this.grupos.remove(grupo);
        
    
    }
}

class Whatsapp {

    Repositorio<Usuario> usuarios;
    Repositorio<Grupo> grupos;

    public Whatsapp() {
        this.usuarios = new Repositorio<>("Usuario");
        this.grupos = new Repositorio<>("Grupo");
    }

    public void fazerConvite(String usuario, String convidado, String grupo) {
        if (this.grupos.get(grupo) != null) {
            (this.grupos.get(grupo).usuarios).add(convidado, this.usuarios.get(convidado));
            (this.usuarios.get(convidado).grupos).add(grupo, this.grupos.get(grupo));
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
            //throw new RuntimeException(nomeTipo + " " + chave + " ja existe.");
            System.out.println(nomeTipo + " " + chave + " ja existe.");
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
            //throw new RuntimeException(nomeTipo + " " + chave + " nao existe.");
            System.out.println(nomeTipo + " " + chave + " nao existe.");
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
            //try {
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
                case "fim":
                    break OUTER;
                default:
                    System.out.println("Comando invalido.");
            }
            //}catch(RuntimeException e){
            //    System.out.println(e.getMessage());
            //}
        }

    }

}

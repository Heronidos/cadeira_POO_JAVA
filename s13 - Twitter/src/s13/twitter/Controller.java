package s13.twitter;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

class Tweet {

    static int nextId = 0;
    private final int idTweet;
    private final String userName;
    private final String msg;
    private ArrayList<String> likes;

    public Tweet(String userName, String msg) {
        this.idTweet = nextId;
        this.userName = userName;
        this.msg = msg;
        this.likes = new ArrayList<>();
        nextId++;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public int getIdTweet() {
        return idTweet;
    }

    public String getUserName() {
        return userName;
    }

    public String getMsg() {
        return msg;
    }

    public void darLike(String username) {
        likes.add(username);
    }

    @Override
    public String toString() {
        if (likes.size() > 0) {
            String nomes = "";
            for (String t : likes) {
                nomes += t + " ";
            }
            return idTweet + ":" + userName + "( " + msg + " )" + "[ " + nomes + "]";
        } else {
            return idTweet + ":" + userName + "( " + msg + " )";
        }

    }
}

class Usuario {

    private String nome;
    private int naoLidos;
    Repositorio<String, Usuario> seguidores;
    Repositorio<String, Usuario> seguidos;
    Repositorio<Integer, Tweet> meusTweets;
    Repositorio<Integer, Tweet> timeLine;

    public Usuario(String nome) {
        this.nome = nome;
        this.seguidores = new Repositorio<>("seguidor");
        this.seguidos = new Repositorio<>("seguido");
        this.meusTweets = new Repositorio<>("meu tweet");
        this.timeLine = new Repositorio<>("timeline");

    }

    public int getNaoLidos() {
        return naoLidos;
    }

    public void setNaoLidos(int naoLidos) {
        this.naoLidos = naoLidos;
    }

    public String getNome() {
        return nome;
    }

    public String timeLine() {
        String retorno = "";
        this.naoLidos = 0;
        for (Tweet t : timeLine.getAll()) {
            retorno = t + "\n";
        }
        return retorno;
    }

    public String unRead() {
        String retorno = "";
        for (int i = this.timeLine.getAll().size() - this.naoLidos; i < this.timeLine.getAll().size(); i++) {
            retorno = this.timeLine.getAll().get(i) + "\n";
        }
        this.naoLidos = 0;
        return retorno;
    }

    public String show() {
        return this.nome + "\n  seguidores [ " + seguidores.toString().replaceAll("\n", " ") + "]" + "\n  seguidos [ " + seguidos.toString().replaceAll("\n", " ") + "]";
    }

    public void twittar(Tweet tweet) {
        this.meusTweets.add(tweet.getIdTweet(), tweet);
        for (Usuario seguidor : this.seguidores.getAll()) {
            seguidor.setNaoLidos(seguidor.getNaoLidos() + 1);
            seguidor.timeLine.add(tweet.getIdTweet(), tweet);
        }
        System.out.println("done");
    }

    @Override
    public String toString() {
        return this.nome;
    }

}

public class Controller {

    public static void main(String[] args) {
        Repositorio<String, Usuario> usuarios = new Repositorio<>("usuario");
        Repositorio<Integer, Tweet> timeLine = new Repositorio<>("tweets");

        /*
         usuarios.add("Heron", new Usuario("Heron"));
         usuarios.add("Amorim", new Usuario("Amorim"));
         usuarios.add("Aley", new Usuario("Aley"));
         usuarios.add("Heron", new Usuario("Heron"));

         usuarios.get("Heron").seguidos.add("Amorim", usuarios.get("Amorim"));
         usuarios.get("Amorim").seguidores.add("Heron", usuarios.get("Heron"));

         Tweet tweet2 = new Tweet("Amorim", "Iae glr gay");
         usuarios.get("Amorim").twittar(tweet2);
         timeLine.add(tweet2.getIdTweet(), tweet2);

         System.out.println(usuarios.get("Heron").show());
         */
        Scanner captInfor = new Scanner(in);

        OUTER:
        while (true) {
            String vetCapInfor[] = captInfor.nextLine().split(" ");
            switch (vetCapInfor[0]) {
                case "addUser":
                    usuarios.add(vetCapInfor[1], new Usuario(vetCapInfor[1]));
                    break;
                case "show":
                    for (Usuario u : usuarios.getAll()) {
                        System.out.println(u.show());
                    }
                    break;
                case "follow":
                    usuarios.get(vetCapInfor[1]).seguidos.add(vetCapInfor[2], usuarios.get(vetCapInfor[2]));
                    usuarios.get(vetCapInfor[2]).seguidores.add(vetCapInfor[1], usuarios.get(vetCapInfor[1]));
                    break;
                case "twittar":
                    String mensagem = "";
                    for (int i = 2; i < vetCapInfor.length; i++) {
                        mensagem += vetCapInfor[i] + " ";
                    }
                    Tweet tweet = new Tweet(vetCapInfor[1], mensagem);
                    usuarios.get(vetCapInfor[1]).twittar(tweet);
                    timeLine.add(tweet.getIdTweet(), tweet);
                    break;
                case "timeline":
                    System.out.println(usuarios.get(vetCapInfor[1]).timeLine());
                    break;
                case "unread":
                    System.out.println(usuarios.get(vetCapInfor[1]).unRead());
                    break;
                case "like":
                    timeLine.get(Integer.parseInt(vetCapInfor[2])).darLike(usuarios.get(vetCapInfor[1]).getNome());
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

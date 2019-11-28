/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s13.twitter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Repositorio<K, T> {

    Map<K, T> dados;

    public Repositorio() {
        //this.nametype = nametype;
        this.dados = new TreeMap<K, T>();
    }

    public void add(K key, T t) {
        if (dados.get(key) == null) {
            dados.put(key, t);
        } else {
            throw new RuntimeException(key + " já existe");
        }
    }

    public T get(K key) {
        if (dados.get(key) != null) {
            return dados.get(key);
        } else {
            throw new RuntimeException(key + " não existe");
        }
    }

    public void del(K key) {
        if (dados.get(key) != null) {
            System.out.println("removendo " + key);
            dados.remove(key);
        } else {
            throw new RuntimeException(" não existe");
        }
    }

    public ArrayList<T> getAll() {
        ArrayList<T> out = new ArrayList<T>();
        for (K key : dados.keySet()) {
            out.add(dados.get(key));
        }
        return out;
    }

    @Override
    public String toString() {
        String out = "";

        for (K k : dados.keySet()) {
            out += dados.get(k);
        }
        out += "";
        return out;
    }

}

class Twitter {

    Repositorio<String, Usuario> usuarios;
    //Repositorio<Integer, Tweet> tweets;

    Twitter() {
        usuarios = new Repositorio<String, Usuario>();
    }

    public Repositorio<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void seguir(String seguido, String seguidor) {
        usuarios.get(seguido).getSeguidos().add(seguidor, usuarios.get(seguidor));

        usuarios.get(seguidor).getSeguidores().add(seguido, usuarios.get(seguido));
    }

    public void twittar(String usuario, String msg) {
        usuarios.get(usuario).getTweets().add(new Tweet(usuario, msg));
    }

    public void like(String likador, String user, int t) {
        Tweet p = usuarios.get(user).getTweets().get(t);
        String f = this.usuarios.get(likador).getId();
        p.idLikadores.add(f);
        p.setLikes(p.getLikes() + 1);
    }

}

class Usuario {

    Repositorio<String, Usuario> seguidores;
    Repositorio<String, Usuario> seguidos;
    String id;

    ArrayList<Tweet> tweets;

    public Usuario(String id) {
        this.id = id;
        this.seguidores = new Repositorio<String, Usuario>();
        this.seguidos = new Repositorio<String, Usuario>();
        this.tweets = new ArrayList<Tweet>();
    }

    public Repositorio<String, Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Repositorio<String, Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public Repositorio<String, Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(Repositorio<String, Usuario> seguidos) {
        this.seguidos = seguidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        /*
         return "" + this.id + "\n" + "  seguindo " + this.seguidos + "\n" + "  seguidores "
         + this.seguidores + "\n";
         */
        String out = "\n    Seguidores [ ";
        for (Usuario s : seguidores.getAll()) {
            out += s.getId() + ", ";
        }
        out += "]\n    Seguidos [ ";
        for (Usuario s : seguidos.getAll()) {
            out += s.getId() + ", ";
        }
        return this.id + out + "]\n";
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public String timeLine() {
        String out = " ";
        int x = 0;
        for (Tweet t : this.tweets) {
            out += "[ " + x + " = " + t + ", ";
            x++;
        }
        return this.id + out + "\n";

    }

}

class Tweet {

    String idUser;
    String msg;
    int likes;

    ArrayList<String> idLikadores;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Tweet(String idUser, String msg) {
        this.idUser = idUser;
        this.msg = msg;
        this.likes = 0;
        this.idLikadores = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return msg + " / likes = " + likes + " /] " + idLikadores;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}

public class Controller {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.usuarios.add("Jacquin", new Usuario("Jacquin"));
        twitter.usuarios.add("Tompero", new Usuario("Tompero"));
        twitter.seguir("Jacquin", "Tompero");
        twitter.seguir("Tompero", "Jacquin");

        twitter.twittar("Jacquin", "Só falta você sz");
        twitter.like("Tompero", "Jacquin", 0);
        System.out.println(twitter.usuarios.get("Jacquin").timeLine());
        System.out.println(twitter.usuarios.get("Tompero").timeLine());
    }

}

package s11.matricula;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Aluno {

    String idAluno;
    ArrayList<Disciplina> disciplinas;

    public Aluno(String idAluno) {
        this.idAluno = idAluno;
        this.disciplinas = new ArrayList<>();
    }

    public void matricular(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            System.out.println("fail: disciplina ja matriculada");
        } else {
            this.disciplinas.add(disciplina);
            System.out.println("done");
        }
    }

    public void desmatricular(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
            System.out.println("done");
        } else {
            System.out.println("fail: disciplina nao matriculada");
        }
    }

    public String getIdAluno() {
        return idAluno;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        String retorno = idAluno + " [ ";
        for (Disciplina d : disciplinas) {
            retorno += d.getIdDiscp() + " ";
        }

        return retorno + "]";
    }

}

class Disciplina {

    String idDiscp;
    ArrayList<Aluno> alunos;

    public Disciplina(String idDiscp) {
        this.idDiscp = idDiscp;
        this.alunos = new ArrayList();
    }

    public void matricular(Aluno aluno) {
        if (alunos.contains(aluno)) {
            System.out.println("fail: aluno ja matriculado");
        } else {
            this.alunos.add(aluno);
        }
    }

    public void desmatricular(Aluno aluno) {
        if (alunos.contains(aluno)) {
            alunos.remove(aluno);
        } else {
            System.out.println("fail: aluno nao matriculado");
        }
    }

    public String getIdDiscp() {
        return idDiscp;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public String toString() {
        String retorno = idDiscp + " [ ";
        for (Aluno a : alunos) {
            retorno += a.getIdAluno() + " ";
        }

        return retorno + "]";
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
        String saida = this.tipo + "s: \n";
        for (V v : this.getAll()) {
            saida += v + "\n";
        }
        return saida;
    }
}

public class Controller {

    public static void main(String[] args) {

        Repositorio<String, Disciplina> disciplinas = new Repositorio<>("disciplina");
        Repositorio<String, Aluno> alunos = new Repositorio<>("aluno");
        /*
         alunos.add("Heron", new Aluno("Heron"));
         alunos.add("Robson", new Aluno("Robson"));
         alunos.add("Heron", new Aluno("Heron"));
         alunos.add("Amorim", new Aluno("Amorim"));

         System.out.println("\n");

         disciplinas.add("Matematica", new Disciplina("Matematica"));
         disciplinas.add("Portugues", new Disciplina("Portugues"));
         disciplinas.add("POO", new Disciplina("POO"));
         disciplinas.add("Matematica", new Disciplina("Matematica"));

         System.out.println("\n");

         disciplinas.get("Matematica").matricular(alunos.get("Heron"));
         alunos.get("Heron").matricular(disciplinas.get("Matematica"));
         disciplinas.get("Matematica").matricular(alunos.get("Amorim"));
         alunos.get("Amorim").matricular(disciplinas.get("Matematica"));
         disciplinas.get("Matematica").matricular(alunos.get("Amorim"));
         alunos.get("Amorim").matricular(disciplinas.get("Matematica"));
         */
        Scanner captInfor = new Scanner(in);

        OUTER:
        while (true) {
            String vetCapInfor[] = captInfor.nextLine().split(" ");
            switch (vetCapInfor[0]) {
                case "nwalu":
                    for (int i = 1; i < vetCapInfor.length; i++) {
                        alunos.add(vetCapInfor[i], (new Aluno(vetCapInfor[i])));
                    }
                    break;
                case "nwdis":
                    for (int i = 1; i < vetCapInfor.length; i++) {
                        disciplinas.add(vetCapInfor[i], (new Disciplina(vetCapInfor[i])));
                    }
                    break;
                case "la":
                    System.out.println(alunos);
                    System.out.println(disciplinas);
                    break;
                case "mat":
                    for (int i = 2; i < vetCapInfor.length; i++) {
                        alunos.get(vetCapInfor[1]).matricular(disciplinas.get(vetCapInfor[i]));
                        disciplinas.get(vetCapInfor[i]).matricular(alunos.get(vetCapInfor[1]));
                    }
                    break;
                case "rmmat":
                    for (int i = 2; i < vetCapInfor.length; i++) {
                        alunos.get(vetCapInfor[1]).desmatricular(disciplinas.get(vetCapInfor[i]));
                        disciplinas.get(vetCapInfor[i]).desmatricular(alunos.get(vetCapInfor[1]));
                    }
                    break;
                case "rmalu":
                    for (Disciplina d : disciplinas.getAll()) {
                        d.alunos.remove(alunos.get(vetCapInfor[1]));
                    }
                    alunos.remove(vetCapInfor[1]);
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

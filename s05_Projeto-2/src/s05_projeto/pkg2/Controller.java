package s05_projeto.pkg2;

import java.util.Scanner;

class Lapiseira {

    private double ponta;
    private Grafite carga;

    public Lapiseira(double ponta) {
        this.ponta = ponta;
        this.carga = new Grafite(ponta, 4);
    }

    public Grafite getCarga() {
        return carga;
    }

    public void setCarga(Grafite carga) {
        this.carga = carga;
    }

    public double getPonta() {
        return ponta;
    }

    public void escrever() {
        if (carga.getDurabilidade() > 0 && this.ponta > 0) {
            System.out.println("Você está escrevendo");
            this.carga.usarGrafite();
        } else if (this.ponta <= 0) {
            System.out.println("fail: tamanho de ponta inválido");
        } else {
            System.out.println("fail: lapiseira vazia");
        }
    }

    public void colcarGrafite(double pontaGrafite, int durabilidade) {
        if (pontaGrafite == this.ponta && carga.getDurabilidade() == 0) {
            carga = new Grafite(pontaGrafite, durabilidade);
            System.out.println("Você colocou o grafite");
        } else if (carga.getDurabilidade() > 0) {
            System.out.println("fail: lapiseira ainda carregada");
        } else {
            System.out.println("fail: grafite de expessura errada");
        }
    }

}

class Grafite {

    private double ponta;
    private int durabilidade;

    public Grafite(double ponta, int durabilidade) {
        this.ponta = ponta;
        this.durabilidade = durabilidade;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public double getPonta() {
        return ponta;
    }

    public void usarGrafite() {
        if (this.durabilidade > 0) {
            this.durabilidade--;
        }
    }

}

public class Controller {

    public static void main(String[] args) {
        Lapiseira lapiseira = new Lapiseira(0);
        Scanner captInfor = new Scanner(System.in);
        
        OUTER:
        while (true) {

            String vetCapInfor[] = (captInfor.nextLine()).split(" ");
            switch (vetCapInfor[0]) {
                case "init":
                    lapiseira = new Lapiseira(Double.parseDouble(vetCapInfor[1]));
                    break;
                case "escrever":
                    lapiseira.escrever();
                    break;
                case "colocarGrafite":
                    lapiseira.colcarGrafite(Double.parseDouble(vetCapInfor[1]), Integer.parseInt(vetCapInfor[2]));
                    break;
                case "end":
                    break OUTER;
            }
        }
    }

}

package s07.projeto.topic;

import java.util.ArrayList;

public class Topic {

    private int cadeirasPreferenciais;
    private int capacidadeMax;
    private ArrayList<Passageiro> cadeiras = new ArrayList();

    public Topic(int capacidadeMax, int cadeirasPreferenciais) {
        this.cadeirasPreferenciais = cadeirasPreferenciais;
        this.capacidadeMax = capacidadeMax;
        for (int i = 0; i < this.capacidadeMax; i++) {
            this.cadeiras.add(null);
        }

    }

    public int getCadeirasPreferenciais() {
        return cadeirasPreferenciais;
    }

    public void setCadeirasPreferenciais(int cadeirasPreferenciais) {
        this.cadeirasPreferenciais = cadeirasPreferenciais;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public String getCadeirasStr() {
        String cadeiras = "[ ";
        for (int i = 0; i < this.capacidadeMax; i++) {
            if (i < this.cadeirasPreferenciais) {
                if (this.cadeiras.get(i) == null) {
                    cadeiras += "@ ";
                } else {
                    cadeiras += ("@" + (this.cadeiras.get(i)).getIdNome() + ":" + (this.cadeiras.get(i)).getIdade() + " ");
                }
            } else {
                if (this.cadeiras.get(i) == null) {
                    cadeiras += "= ";
                } else {
                    cadeiras += ("=" + (this.cadeiras.get(i)).getIdNome() + ":" + (this.cadeiras.get(i)).getIdade() + " ");
                }
            }
        }
        cadeiras += "]";

        return cadeiras;
    }

    public void setPassageiro(int idade, Passageiro passageiro) {

        for (Passageiro p : cadeiras) {
            if (p != null) {
                if (passageiro.getIdNome().equals(p.getIdNome())) {
                    System.out.println("fail: pass ja esta na topic");
                    return;
                }
            }

        }
        boolean lotada = true;
        for(Passageiro p : cadeiras){
            if(p == null)
                lotada = false;
        }
        if (lotada) {
            System.out.println("fail: topic lotada");
            return;
        }
        if (passageiro.getIdade() < 60) {
            for (int i = (this.cadeirasPreferenciais); i <= this.capacidadeMax; i++) {

                if (i == this.capacidadeMax) {
                    i = 0;
                }
                if (this.cadeiras.get(i) == null) {
                    this.cadeiras.set(i, passageiro);
                    return;
                } else {

                }
            }
        } else {
            for (int i = 0; i < this.capacidadeMax; i++) {
                if (this.cadeiras.get(i) == null) {
                    this.cadeiras.set(i, passageiro);
                    return;
                } else {

                }
            }
        }

    }

    public void tirarPassageiro(String idNome) {

        for (int i = 0; i < this.capacidadeMax; i++) {
            if (this.cadeiras.get(i) != null && this.cadeiras.get(i).getIdNome().equals(idNome)) {
                this.cadeiras.set(i, null);
                return;
            }else if(i == this.capacidadeMax - 1){
                System.out.println("fail: pass nao esta na topic");
                return;
            }
        }
    }
}

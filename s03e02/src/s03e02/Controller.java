//Atividade da Classe Peixe
package s03e02;
import java.util.Scanner;

class Pexe {
    int barriga = 0;
    int maxBarriga = 50;
    boolean vivo = true;
    
    void show(){
        System.out.println("Barriga: " + barriga);
        System.out.println("Tá vivo: " + vivo + "\n");
        
    }
    
    void alimentar(int adc){
        if(vivo){
            barriga += adc;
            if(barriga < maxBarriga){
                System.out.println("Que delícia, heim.");
            }else if(barriga == maxBarriga){
                System.out.println("O bixim tá chei");
                vivo = false;
            }else if(barriga > maxBarriga){
                System.out.println("Muito bem, matou o bixim impasinado.");
            }
        }
        
    }
    
    void esperar(int tempo){
        if(vivo){
            barriga -= tempo;
            if(barriga > 0){
                System.out.println("A feel moments later...");
            }else if(barriga == 0){
                System.out.println("Meu fi quer que o bixim morra de fome é?");
            }else if(barriga < 0) {
                System.out.println("Matou o bixim de fome, parabéns heim.");
                vivo = false;
            }
            
        }
    }
}

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Digite algo:");
        String line;
        //System.out.println("Você digitou " + line);
        
        Pexe dourado = new Pexe();
        
        
        
        OUTER:
        while (true) {
            System.out.println("Digite esperar e o quanto esperar ou alimentar e a quantidade de alimento:");
            line = scanner.nextLine();
            String[] vet = line.split(" ");
            switch (vet[0]) {
                case "alimentar":
                    dourado.alimentar(Integer.parseInt(vet[1]));
                    break;
                case "esperar":
                    dourado.esperar(Integer.parseInt(vet[1]));
                    break;
                case "end":
                    break OUTER;
                default:
                    System.out.println("Comando inválido!!!");
                    break;
            }
            dourado.show();
        }
    }
    
}

package s03e01;

import java.util.Scanner;

class Ventilador {
    boolean ligado;
    int velocidade;
    boolean girando;
    
    void ligar(){
        if(ligado){
            System.out.println("Já tá ligado, mano...");
        }else{
            ligado = true;
            System.out.println("You ligou o ventilador!");
        }
    }
    
    void desligar(){
        if(ligado){
            ligado = false;
            System.out.println("You desligou o ventilador!");
        }else{
            System.out.println("Mano, tá desligado já...");
        }
    }

    void girar(){
        if(girando){
            System.out.println("O ventilador já está girando, bro...");
        }else{
            girando = true;
            System.out.println("O ventilador começou a girar!");
        }
        
    }

    void pararDeGirar(){
        if(girando){
            girando = false;
            System.out.println("O ventilador parou de girar!");
        }else{
            System.out.println("O ventilador já está parado...");
        }
        
    }

    void aumentarVelocidade(){
        if(velocidade < 3){
            velocidade++;
        }else{
            System.out.println("Aqui não é rebolation. Só vai até a velocidade 3.");
        }
        
    }

    void diminuirVelocidade(){
        if(velocidade > 0){
            velocidade--;
        }else{
            System.out.println("Quer tirar da tumada não? Já tá na velocidade 0.");
        }
        
    }
    
    void mostrarStatus(){
        System.out.println("Ligado: " + ligado + "\nVelocidade: " + velocidade + "\nGirando: " + girando);
        if((ligado == true) && (velocidade > 0)){ 
            System.out.println("Ventilando...");
        }else{
            System.out.println("Está quiente, manito.");
        }
    
    }
    
}

public class Controller {
    public static void main(String[] args){
        Ventilador arno = new Ventilador();
        Scanner captarInfor = new Scanner(System.in);
        String option;
        System.out.println("Funções do ventilador: ligar, desligar, ocilar, parar de ocilar, aumentar velocidadde, baixar velociadade e mostrar status.");
        
        while(true){
            System.out.println("Digite a função que você quer que o ventilador execute:");
            option = captarInfor.nextLine();
            
            switch (option) {
                case "ligar":
                    arno.ligar();
                    break;
                case "desligar":
                    arno.desligar();
                    break;
                case "ocilar":
                    arno.girar();
                    break;
                case "parar de ocilar":
                    arno.pararDeGirar();
                    break;
                case "aumentar velocidade":
                    arno.aumentarVelocidade();
                    break;
                case "diminuir velocidade":
                    arno.diminuirVelocidade();
                    break;
                default:
                    System.out.println("Comando inválido!!!");
                    break;
            }
            
        }
    }
        
        
    
}



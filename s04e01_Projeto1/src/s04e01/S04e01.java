package s04e01;
import java.util.Scanner;

public class S04e01{
    public static void main(String[] args) {
        String option;
        String optionVet[];
        Scanner captarInfor = new Scanner(System.in);
        System.out.println("Qual seu nome?");
        String name = captarInfor.nextLine();
        Personagem player = new Personagem(name, 20, true);
        Personagem villager = new Personagem("Robson", 30, true);
        
        OUTER:
        while (true) {
            if(player.isConversando()){
                System.out.println("Quer continuar conversando?");
                option = captarInfor.nextLine();
                OUTER_1:
                switch (option) {
                    case "Sim":
                        System.out.println("Você e " + villager.getNome() + " ainda estão conversando.");
                        break;
                    case "Nope":
                        System.out.println("Você quer parar de interagir ou quer bater em " + villager.getNome() + "?");
                        option = captarInfor.nextLine();
                        optionVet = option.split(" ");
                        switch (optionVet[0]) {
                            case "Parar":
                                System.out.println("Você parou o programa e a interação...");
                                break OUTER;
                            case "Bater":
                                player.bater(villager, Integer.parseInt(optionVet[1]));
                                break OUTER_1;
                        }
                    default:
                        System.out.println("Não entendi, poderia repetir?");
                }
            }else{
                System.out.println(player.getNome() +", você gostaria de interagir com " + villager.getNome() + "? (Conversar ou bater e quanto bater )");
                
                option = captarInfor.nextLine();
                optionVet = option.split(" ");
                switch (optionVet[0]) {
                    case "Sim":
                        player.iniciarInteragir(villager);
                        if(optionVet[1].equals("conversar")){
                            player.iniciarConversa(villager);
                        }else{
                            player.bater(villager, Integer.parseInt(optionVet[2]));
                        }
                        break;
                    case "Nope":
                        System.out.println("Você encerrou o programa e a interação...");
                        break OUTER;
                    default:
                        System.out.println("Não entendi poderia repetir?");
                        break;
                }
            }
        }
        
        
    }


}
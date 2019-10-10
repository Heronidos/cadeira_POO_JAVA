package s07.projeto.topic;

import java.util.ArrayList;
import java.util.Scanner;

public class S07ProjetoTopic {

    public static void main(String[] args) {
        Scanner captInfor = new Scanner(System.in);
        String vetCaptInfo[];
        Topic topic = new Topic(0, 0);
        

        OUTER:
        while (true) {
            vetCaptInfo = (captInfor.nextLine()).split(" ");
            switch (vetCaptInfo[0]) {
                case "init":
                    topic = new Topic(Integer.parseInt(vetCaptInfo[1]), Integer.parseInt(vetCaptInfo[2]));
                    break;
                case "show":
                    System.out.println(topic.getCadeirasStr());
                    break;
                case "in":
                    Passageiro pass = new Passageiro(vetCaptInfo[1], Integer.parseInt(vetCaptInfo[2]));
                    topic.setPassageiro(Integer.parseInt(vetCaptInfo[2]), pass);
                    break;
                case "out": 
                    topic.tirarPassageiro(vetCaptInfo[1]);
                    break;
                case "end":
                    break OUTER;
                    
                default:
                    System.out.println("Comando inválido");
                    break;
                    
            }

        }
    }

}

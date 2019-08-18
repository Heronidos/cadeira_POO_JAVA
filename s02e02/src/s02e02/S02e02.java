package s02e02;
public class S02e02 {
    public static void main(String[] args) {
        Ilustrador Eu = new Ilustrador();
        Eu.desenhar();
        System.out.println("Estou cansado? " + Eu.cansado);
        System.out.println("Vou tomar café.");
        Eu.tomar_cafe();
        System.out.println("Ainda cansado? " + Eu.cansado);
    }
    
}

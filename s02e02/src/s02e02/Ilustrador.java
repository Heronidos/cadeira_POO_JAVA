package s02e02;
public class Ilustrador {
    String tipo_de_traco;
    float preco_trabalho;
    boolean cansado;
    
    void tomar_cafe(){
        System.out.println("Tomando café...");;
        cansado = false;
    }
    
    void desenhar(){
        System.out.println("Desenhando...");
        Desenho Pedido = new Desenho();
        cansado = true;
        Pedido.tipo_de_traco = this.tipo_de_traco;
    }
        
    
}

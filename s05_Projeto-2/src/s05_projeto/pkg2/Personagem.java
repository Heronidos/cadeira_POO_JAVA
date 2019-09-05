package s05_projeto.pkg2;
public class Personagem {
    
    private String nome;
    private int pontosDeVida;
    private boolean vivo;
    private boolean conversando;
    private boolean interagindo;
    private Item inventario[];
    
    

    public Personagem(String nome, int pontosDeVida, boolean vivo) {
        this.nome = nome;
        this.pontosDeVida = pontosDeVida;
        this.vivo = vivo;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isConversando() {
        return conversando;
    }

    public void setConversando(boolean conversando) {
        this.conversando = conversando;
    }
    
    public void iniciarConversa(Personagem p){
        this.setConversando(true);
        p.setConversando(true);
        System.out.println("Você e " + p.getNome() + " estão conversando.");
    }
    
    public void pararConversa(Personagem p){
        this.setConversando(false);
        p.setConversando(false);
        System.out.println("Você parou de conversar com " + p.getNome());
    }
    
    public void bater(Personagem p, int qtd){
        p.setPontosDeVida(p.getPontosDeVida() - qtd);
        if(0 > p.pontosDeVida){
            System.out.println("Você matou " + p.getNome() + ".");
        }else{
            System.out.println("Você bateu " + qtd + " vezes em " + p.getNome());
        }
    }

    public boolean isInteragindo() {
        return interagindo;
    }

    public void setInteragindo(boolean interagindo) {
        this.interagindo = interagindo;
    }
    
    public void iniciarInteragir(Personagem p){
        this.setInteragindo(true);
        p.setInteragindo(true);
    
    }
    
    public void pararInteragir(Personagem p){
        this.setInteragindo(false);
        p.setInteragindo(false);
    }
        
}


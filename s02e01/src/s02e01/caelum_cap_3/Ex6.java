//0 1 1 2 3 5 8 13 21 34 55 89 144

package s02e01.caelum_cap_3;
public class Ex6 {
    public static void main(String[] args){
        int n = 0;
        int n2 = 1;
        int aux;
        while (n <= 200){
            System.out.println(n);
            aux = n;
            n = n2;
            n2 = aux + n;
            
        }
    }
    
}

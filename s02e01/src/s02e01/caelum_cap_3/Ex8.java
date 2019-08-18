package s02e01.caelum_cap_3;
/* @author Herônidos
*/
public class Ex8 {
    public static void main (String[] args){
        for(int i = 1; i < 10; i++){
            for(int i2 = 1; i2 <= i; i2++){
                System.out.print(i*i2 + " ");
            }
            System.out.println();
        }
    }
}

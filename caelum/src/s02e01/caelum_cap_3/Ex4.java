package s02e01.caelum_cap_3;

public class Ex4 {

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) {
            int fatorial = 1;
            for(int i2 = i; i2 > 1; i2--){
                    fatorial = fatorial * i2;
            }
            System.out.println(fatorial);
			
        }
    }

}

package s10e01;

import java.util.Map;
import java.util.TreeMap;

public class S10e01 {
    public static void main(String[] args) {
        Map<Integer, String>teste = new TreeMap<>();
        teste.put(1, "teste");
        System.out.println(teste.get(2));
        System.out.println(teste.get(1));
    }
    
}

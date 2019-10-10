package s07e01;

import java.util.ArrayList;

public class Sala {
    private ArrayList<Cliente> cadeiras;
    private int capacidadeMax;

    public Sala(int capacidadeMax) {
        for(int i = 0; i < capacidadeMax; i++){
            cadeiras.add(null);
        }
    }
    
    
    
}

package utilitarios;

import java.util.Random;

public class GeradorAleatorio {

    public static int sortearNumero(int minimo, int maximo){
        Random random = new Random();
        return minimo + random.nextInt(maximo - minimo + 1);
    }
}
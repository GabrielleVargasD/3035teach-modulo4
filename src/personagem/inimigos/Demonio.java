package personagem.inimigos;

import personagem.Personagem;
import utilitarios.GeradorAleatorio;

import java.util.Random;

public class Demonio extends Personagem {
    public Demonio() {
        super("Demonio", 110, GeradorAleatorio.sortearNumero(17, 20), 35);
    }

    @Override
    public void atacar(Personagem personagem){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 20){
            personagem.tomarDano(getAtaque()*2, getNome());
        } else{
            personagem.tomarDano(getAtaque(), getNome());
        }
    }

}

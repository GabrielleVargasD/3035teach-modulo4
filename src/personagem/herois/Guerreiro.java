package personagem.herois;

import personagem.Personagem;
import java.util.Random;

public class Guerreiro extends Personagem {
    private int forca = 10;

    public Guerreiro(String nome) {
        super(nome, 130, 25, 20);
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Vida");
    }

    //METODOS PROPRIOS
    @Override
    public void atacar(Personagem personagem){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 30){
            personagem.tomarDano(getAtaque()+forca, getNome());
        } else{
            personagem.tomarDano(getAtaque(), getNome());
        }
    }

    //GET e SET
    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }
}

package personagem.herois;

import personagem.Personagem;

import java.util.List;
import java.util.Random;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 120, 30, 25);
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Vida");
    }

    //METODOS PROPRIOS
    @Override
    public int tomarDano(int dano, String nomeAtacante) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 30) {
            System.out.println("\n" + nomeAtacante + " atacou " + getNome() + "!");
            System.out.println(getNome() + " desviou do ataque! Nenhum dano recebido.");
            return getVida();
        }

        return super.tomarDano(dano, nomeAtacante);
    }

}

package personagem.herois;

import personagem.Personagem;
import java.util.Random;

public class Mago extends Personagem {
    private int mana = 100;
    private int manaMaxima = 100;

    public Mago(String nome) {
        super(nome, 80, 35, 30);
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Vida");
        adicionarItem("Poção de Mana");
    }

    //METODOS PROPRIOS
    @Override
    public void atacar(Personagem personagem){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (mana >= 5){
            if (chance < 30 && mana >=10){
                personagem.tomarDano(getAtaque(), getNome());
                personagem.tomarDano(getAtaque(), getNome());
                mana = mana - 10;
            } else{
                personagem.tomarDano(getAtaque(), getNome());
                mana = mana - 5;
            }
        } else {
            System.out.println("Sem mana suficiente!! Não foi possível atacar");
        }
    }

    public boolean podeUsarPocaoMana(){
        return mana < manaMaxima;
    }

    public int usarPocaoMana(){
        if (getInventario().contains("Poção de Mana")){
            getInventario().remove("Poção de Mana");
            mana = mana + 20;
            if (mana > manaMaxima){
                mana = manaMaxima;
            }
            return mana;
        } else {
            return mana;
        }
    }

    @Override
    public void regenerar() {
        super.regenerar();
        mana = manaMaxima;
    }

    //GET E SET
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}

package personagem.inimigos;

import personagem.Personagem;
import utilitarios.GeradorAleatorio;

import java.util.Random;

public class Aranha extends Personagem {
    private boolean ataqueEmGrupo = false;

    public Aranha() {
        super("Aranha", 20, GeradorAleatorio.sortearNumero(4, 7), 10);
    }

    //METODOS PROPRIOS
    public boolean ataqueEmGrupoDisponivel(int quantidadeAranhas){
        ataqueEmGrupo = quantidadeAranhas >= 2;
        return ataqueEmGrupo;
    }

    @Override
    public void atacar(Personagem personagem){
        if(ataqueEmGrupo){
            personagem.tomarDano(getAtaque() + 3, getNome());
        } else{
            personagem.tomarDano(getAtaque(), getNome());
        }
    }

// GET E SET
    public boolean getAtaqueEmGrupo() {
        return ataqueEmGrupo;
    }

    public void setAtaqueEmGrupo(boolean ataqueEmGrupo) {
        this.ataqueEmGrupo = ataqueEmGrupo;
    }
}
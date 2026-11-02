package personagem.inimigos;

import personagem.Personagem;
import utilitarios.GeradorAleatorio;

public class Zumbi extends Personagem {
    public Zumbi() {
        super("Zumbi", 60, GeradorAleatorio.sortearNumero(12, 16), 20);
    }

    @Override
    protected int reduzirDanoRecebido(int dano) {
        int reducaoDano = dano / 4;
        return dano - reducaoDano;
    }

}

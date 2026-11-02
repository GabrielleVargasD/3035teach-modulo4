package personagem;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private int defesaMaxima;
    private boolean defendendo;
    private List<String> inventario;

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.defesaMaxima = defesa;
        this.inventario = new ArrayList<>();
    }

    //Metodos gerais
    public void atacar(Personagem personagem){
        personagem.tomarDano(ataque, nome);
    }

    public void defender(){
        defendendo = true;
    }

    public int tomarDano(int dano, String nomeAtacante) {
        int danoOriginal = dano;
        dano = reduzirDanoRecebido(dano);

        if (defendendo) {
            dano = dano / 2;
        }

        int danoFinal = dano;
        int danoVida = 0;


        if (defesa > 0) {

            if (dano <= defesa) {
                defesa = defesa - dano;

            } else {
                danoVida = dano - defesa;
                defesa = 0;
                vida = vida - danoVida;
            }

        } else {
            danoVida = dano;
            vida = vida - danoVida;
        }

        if (vida < 0) {
            vida = 0;
        }

        System.out.println("\n" + nomeAtacante + " atacou " + nome + "!");
        System.out.println("Dano original: " + danoOriginal);
        System.out.println("Dano após reduções: " + danoFinal);
        System.out.println("Dano recebido na vida: " + danoVida);

        return vida;
    }

    public void regenerar(){
        vida = vidaMaxima;
        defesa = defesaMaxima;
        defendendo = false;
    }

    public boolean podeUsarPocaoVida(){
        return vida < vidaMaxima;
    }

    public boolean estaVivo(){
    if(vida <= 0){
        return false;
    } else {
        return true;
    }
    }

    public void adicionarItem(String item){
        inventario.add(item);
    }

    public int usarPocaoVida(){
        if (inventario.contains("Poção de Vida")){
            inventario.remove("Poção de Vida");
            vida = vida + 40;
            if (vidaMaxima < vida){
                vida = vidaMaxima;
            }
            return vida;
        } else {
            return vida;
        }
    }

    protected int reduzirDanoRecebido(int dano) {
        return dano;
    }

    //GET
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public List<String> getInventario() {
        return inventario;
    }

    public boolean getDefendendo() {
        return defendendo;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getDefesaMaxima() {
        return defesaMaxima;
    }

    //SET
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }

    public void setInventario(List<String> inventario) {
        this.inventario = inventario;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
}

package batalha;

import personagem.Personagem;
import personagem.herois.Mago;
import personagem.inimigos.Aranha;
import utilitarios.GeradorAleatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Batalha {

    private Personagem heroi;
    private List<Personagem> inimigos;
    private boolean batalhaAcontecendo;
    private int turno;
    private Scanner teclado;
    private boolean acaoValida;

    public Batalha(Personagem heroi, Scanner teclado) {
        this.heroi = heroi;
        this.inimigos = new ArrayList<>();
        this.batalhaAcontecendo = true;
        this.turno=1;
        this.teclado = teclado;
    }

    public void adicionarInimigo(Personagem inimigo){
        inimigos.add(inimigo);
    }

    public void exibirInimigos() {
        for (int i= 0; i < inimigos.size(); i++){
            Personagem inimigo = inimigos.get(i);
            if (inimigo.estaVivo()){
                int numero = i + 1;
                System.out.println("(" + numero + ") - " + inimigo.getNome() + " | Vida: " + inimigo.getVida() +
                        " | Defesa: " + inimigo.getDefesa() +
                        " | Ataque: " + inimigo.getAtaque());
            }
        }
        System.out.println("(0) Voltar");
    }

    public boolean turnoDoHeroi(int escolha) {
        acaoValida = false;
        switch (escolha) {
            case 1: // ATACAR
                exibirInimigos();
                int indice = teclado.nextInt();
                if (indice == 0){
                    acaoValida=false;
                    break;
                }
                indice--;
                atacarInimigo(indice);
                break;

            case 2: //DEFENDER
                heroi.defender();
                acaoValida=true;
                break;

            case 3: //USAR ITEM
                if (heroi.getInventario().isEmpty()){
                    System.out.println("Sem itens disponíveis!");
                    acaoValida = false;
                    break;
                }

                exibirInventarioHeroi();
                escolha = teclado.nextInt();

                if (escolha == 0){
                    acaoValida = false;
                    break;
                }

                else if (escolha == 1){
                    if (heroi.getInventario().contains("Poção de Vida")){
                        if (heroi.podeUsarPocaoVida()){
                            heroi.usarPocaoVida();
                            acaoValida = true;
                        } else {
                            System.out.println("Sua vida já está cheia!");
                            acaoValida = false;
                        }
                    } else {
                        System.out.println("Você não possui essa poção!");
                    }
                }

                else if (heroi instanceof Mago && escolha == 2){
                    Mago mago = (Mago) heroi;

                    if (heroi.getInventario().contains("Poção de Mana")){
                        if(((Mago) heroi).podeUsarPocaoMana()){
                            mago.usarPocaoMana();
                            acaoValida = true;
                        } else {
                            System.out.println("Sua mana já está cheia!");
                            acaoValida=false;
                        }
                    } else {
                        System.out.println("Você não possui essa poção!");
                    }
                }

                else {
                    System.out.println("Opção inválida!");
                }

                break;

            default:
                System.out.println("Opção inválida");
        }
        return acaoValida;
    }

    public void atacarInimigo(int indice) {
        if (indice >= inimigos.size() || indice < 0){
            System.out.println("Inimigo não encontrado");
            acaoValida = false;
            return;
        }
        Personagem inimigo = inimigos.get(indice);
        if (inimigo.estaVivo()){
            if (heroi instanceof Mago && ((Mago) heroi).getMana() < 5){
                System.out.println("Mana insuficiente! Use uma poção ou escolha outra ação.");
                acaoValida = false;
                return;
            }
            heroi.atacar(inimigo);
            acaoValida=true;
        } else {
            System.out.println("Inimigo já derrotado!");
            acaoValida = false;
        }
    }

    public void atualizarAtaqueEmGrupo() {
            int numAranha = 0;

            for (int i = 0; i < inimigos.size(); i++) {
                Personagem inimigo = inimigos.get(i);

                if (inimigo instanceof Aranha && inimigo.estaVivo()) {
                    numAranha++;
                }
            }

            for (int i = 0; i < inimigos.size(); i++) {
                Personagem inimigo = inimigos.get(i);

                if (inimigo instanceof Aranha) {
                    Aranha aranha = (Aranha) inimigo;
                    aranha.ataqueEmGrupoDisponivel(numAranha);
                }
            }
        }

    public void turnoDosInimigos() {
        atualizarAtaqueEmGrupo();

        for (int i = 0; i < inimigos.size(); i++) {

            if (!heroi.estaVivo()) {
                break;
            }

            Personagem inimigo = inimigos.get(i);

            if (inimigo.estaVivo()) {

                int chance = GeradorAleatorio.sortearNumero(1, 100);

                if (chance <= 20) {
                    inimigo.defender();

                    System.out.println("\n" + inimigo.getNome() + " decidiu se defender!");
                } else {
                    inimigo.atacar(heroi);
                }
            }
        }

        heroi.setDefendendo(false);
    }

    public boolean iniciarBatalha(){
        do{
            if (turno%2 !=0){ //Impar - herói
                exibirAcaoHeroi();
                int escolha = teclado.nextInt();
                boolean acaoRealizada = turnoDoHeroi(escolha);
                if (!acaoRealizada){
                    continue;
                }
            } else { //Par - inimigos
                turnoDosInimigos();
            }
            verificarFimBatalha();
            turno++;
        } while(batalhaAcontecendo);
        return heroi.estaVivo();
    }

    public void verificarFimBatalha(){
        int inimigosMortos = 0;
        if (!heroi.estaVivo()){
            System.out.println("Você foi derrotado!");
            batalhaAcontecendo=false;
        }
        for (int i= 0; i < inimigos.size(); i++){
            Personagem inimigo = inimigos.get(i);
            if (!inimigo.estaVivo()){
                inimigosMortos++;
            }
        }
        if (inimigos.size() == inimigosMortos){
            System.out.println("Todos os inimigos foram derrotados!");
            batalhaAcontecendo=false;
        }
    }

    public void exibirAcaoHeroi(){
        System.out.println("===========================");
        System.out.println("Nome: " + heroi.getNome());
        System.out.println("Vida: " + heroi.getVida());
        if (heroi instanceof Mago){
            System.out.println("Mana: " + ((Mago) heroi).getMana());
        }
        System.out.println("Defesa: " + heroi.getDefesa());
        System.out.println("===========================\n");
        System.out.println("========== SEU TURNO ==========\n" +
                "1 - Atacar \n" +
                "2 - Defender \n" +
                "3 - Usar item\n" +
                "Escolha sua ação: ");
    }

    public void exibirInventarioHeroi(){
        for (int i=0; i < heroi.getInventario().size(); i++){
            System.out.println("Item: " + heroi.getInventario().get(i));
        }
        System.out.println("(0) Voltar\n" + "(1) - Usar Poção de vida");

        if (heroi instanceof Mago){
            System.out.println("(2) - Usar Poção de Mana\n");
        }
    }

}
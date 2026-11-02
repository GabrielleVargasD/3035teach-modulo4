package batalha;

import personagem.Personagem;
import personagem.herois.Arqueiro;
import personagem.herois.Guerreiro;
import personagem.herois.Mago;
import personagem.inimigos.Aranha;
import personagem.inimigos.Demonio;
import personagem.inimigos.Zumbi;
import utilitarios.GeradorAleatorio;

import java.util.Scanner;

public class Jogo {
    private Scanner teclado;
    private String nomeHeroi;
    private Personagem heroi;
    private int escolha;

    public Jogo(Scanner teclado) {
        this.teclado=teclado;
    }

    public void iniciarJogo(){
        System.out.println("Bem vindo a Arena dos campeões!\n" +
                "Sua missão é derrotar todos os inimigos por seu caminho e chegar até o final das 5 fases vivo! Boa sorte!\n");
        do {
            System.out.println("Primeiro, informe o nome do seu herói:");
            nomeHeroi = teclado.nextLine();
            if (nomeHeroi.isBlank()){
                System.out.println("Por favor, informe um nome válido");
            }
        } while (nomeHeroi.isBlank()); //Evita q nome seja vazio

        boolean classeEscolhida = false;
        do {
            System.out.println("Sua jornada como " + nomeHeroi + " vai começar! Mas antes, escolha sua classe: \n" +
                    "(1)Guerreiro \n(2)Mago \n(3)Arqueiro \n(4)Informações");

            if (teclado.hasNextInt()) { //Verifica se a entrada é int
                escolha = teclado.nextInt();
            } else {
                System.out.println("Entrada inválida! Digite um número.");
                teclado.next();
                continue;
            }

            switch (escolha){
                case 1:
                    heroi = new Guerreiro(nomeHeroi);
                    classeEscolhida = true;
                    break;
                case 2:
                    heroi = new Mago(nomeHeroi);
                    classeEscolhida = true;
                    break;
                case 3:
                    heroi = new Arqueiro(nomeHeroi);
                    classeEscolhida = true;
                    break;
                case 4:
                    exibirInformacoesClasses();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (!classeEscolhida);
        System.out.println("Parabéns! Você acabou de criar seu herói! \n");
        exibirStatusHeroi();

        System.out.println("\nPreparesse para iniciar sua jornada!\n");

        for (int fase = 1; fase <= 5; fase++){

            boolean venceuFase = iniciarFase(fase);

            if (!venceuFase){
                System.out.println("Fim de jogo! Você não conseguiu completar a jornada.");
                return;
            }
        }

        System.out.println("\nPARABÉNS! VOCÊ VENCEU TODAS AS 5 FASES!");

    }

    public boolean iniciarFase(int fase){
        System.out.println("\n========== FASE " + fase + " ==========");

        if (fase > 1){
            heroi.regenerar();
        }

        Batalha batalha = new Batalha(heroi, teclado);
        sortearInimigos(batalha, fase);

        return batalha.iniciarBatalha();
    }
    public void sortearInimigos(Batalha batalha, int fase){

        switch (fase){
            case 1:
                int escolha = GeradorAleatorio.sortearNumero(1, 2);
                if (escolha==1){
                    System.out.println("Uma aranha apareceu!");
                    batalha.adicionarInimigo(new  Aranha());
                } else {
                    System.out.println("Duas aranhas apareceram!");
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Aranha());
                }
                break;
            case 2:
                escolha = GeradorAleatorio.sortearNumero(1, 2);
                if (escolha==1){
                    System.out.println("Três aranhas apareceram!");
                    batalha.adicionarInimigo(new  Aranha());
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Aranha());
                } else {
                    System.out.println("Duas aranhas e um zumbi apareceram!");
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Zumbi());
                }
                break;
            case 3:
                escolha = GeradorAleatorio.sortearNumero(1, 2);
                if (escolha==1){
                    System.out.println("Uma aranha e dois zumbis apareceram!");
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Zumbi());
                    batalha.adicionarInimigo(new Zumbi());
                } else {
                    System.out.println("Dois zumbis apareceram!");
                    batalha.adicionarInimigo(new Zumbi());
                    batalha.adicionarInimigo(new Zumbi());
                }
                break;
            case 4:
                escolha = GeradorAleatorio.sortearNumero(1, 2);
                if (escolha==1){
                    System.out.println("Dois zumbis e uma aranha apareceram!");
                    batalha.adicionarInimigo(new Zumbi());
                    batalha.adicionarInimigo(new Zumbi());
                    batalha.adicionarInimigo(new  Aranha());
                } else {
                    System.out.println("Duas aranhas e um zumbi apareceram!");
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Aranha());
                    batalha.adicionarInimigo(new Zumbi());
                }
                break;
            case 5:
                    System.out.println("O chão treme e... Um demonio apareceu! (Socorro!)");
                    batalha.adicionarInimigo(new Demonio());
                break;
        }
    }

    public void exibirInformacoesClasses() {
        System.out.println("=====================\n" +
                "Guerreiro:\n" +
                "Pontos de vida: " + 130 +  "\n" +
                "Força de ataque: " + 25 + "\n" +
                "Pontos de defesa: " + 20 + "\n" +
                "Itens de inicio: " + "3 Poções de vida(+40 de vida)" + "\n" +
                "Habilidade especial: 30% de chance de dar +10 de dano em um ataque" +  "\n" +
                "=====================" +  "\n" +
                "Mago:" + "\n" +
                "Pontos de vida: " + 80 + "\n" +
                "Pontos de Mana: " + 100 + "\n" +
                "Força de ataque: " + 35 + "\n" +
                "Pontos de defesa: " + 30 + "\n" +
                "Itens de inicio: " + "2 Poções de vida(+40 de vida) e " + "1 Poção de Mana(+20 de mana)"+ "\n" +
                "Habilidade especial: 30% de chance de lançar duas bolas de fogo em um ataque só" + "\n" +
                "Observação: Mana são os pontos de magia que são utilizados para atacar, cada ataque gasta 5 pontos.\n" +
                "=====================" + "\n" +
                "Arqueiro:" + "\n" +
                "Pontos de vida: " + 120 + "\n" +
                "Força de ataque: " + 30 +  "\n" +
                "Pontos de defesa: " + 25 + "\n" +
                "Itens de inicio: " + "3 Poções de vida(+40 de vida)" + "\n" +
                "Habilidade especial: 30% de chance de evitar completamente o dano do ataque inimigo\n"
                + "=====================" + "\n"
        );
    }

    public void exibirStatusHeroi() {
        System.out.println("Dados do seu herói: ");
        System.out.println("Nome: " + heroi.getNome());
        System.out.println("Vida: " + heroi.getVida());
        System.out.println("Ataque: " + heroi.getAtaque());
        System.out.println("Defesa: " + heroi.getDefesa());
        for (int i=0; i < heroi.getInventario().size(); i++){
            System.out.println("Item: " + heroi.getInventario().get(i));
        }



    }
}
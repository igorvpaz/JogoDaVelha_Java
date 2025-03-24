import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cria os jogadores
        System.out.print("Digite o nome do primeiro jogador: ");
        Jogador jogador1 = new Jogador(scanner.nextLine());
        System.out.print("Digite o nome do segundo jogador: ");
        Jogador jogador2 = new Jogador(scanner.nextLine());

        // Pergunta o tamanho do tabuleiro
        System.out.print("Digite o tamanho do tabuleiro (ex: 3, 5, 7): ");
        int tamanho = scanner.nextInt();

        // Cria o objeto JogoDaVelha
        JogoDaVelha jogo = new JogoDaVelha(tamanho);

        // Define o caractere inicial do jogador
        char jogadorAtual = 'X';
        Jogador jogadorDaVez = jogador1;

        boolean jogarNovamente = true;

        while (jogarNovamente) {
            System.out.println("\nTabuleiro Atual:");
            System.out.println(jogo.toString());

            // Faz a jogada
            int linha, coluna;
            boolean jogadaValida = false;
            while (!jogadaValida) {
                System.out.println(jogadorDaVez.getNome() + " (" + jogadorAtual + "), informe a linha e a coluna da jogada:");
                linha = scanner.nextInt();
                coluna = scanner.nextInt();
                jogadaValida = jogo.realizaJogada(linha, coluna, jogadorAtual);
                if (!jogadaValida) {
                    System.out.println("Jogada inválida. Tente novamente.");
                }
            }

            // Verifica se algum jogador ganhou
            if (jogo.verificaGanhador()) {
                System.out.println("\n" + jogadorDaVez.getNome() + " venceu!");
                jogadorDaVez.adicionarPontos(1);
                System.out.println(jogador1);
                System.out.println(jogador2);
                System.out.println("Deseja jogar novamente? (s/n)");
                char resposta = scanner.next().charAt(0);
                jogarNovamente = (resposta == 's' || resposta == 'S');
                if (jogarNovamente) {
                    jogo = new JogoDaVelha(tamanho); // Reinicia o jogo
                }
            } else {
                // Troca de jogador
                if (jogadorAtual == 'X') {
                    jogadorAtual = 'O';
                    jogadorDaVez = jogador2;
                } else {
                    jogadorAtual = 'X';
                    jogadorDaVez = jogador1;
                }
            }
        }

        // Salva o vencedor final no arquivo
        try (FileWriter writer = new FileWriter("resultado.txt")) {
            if (jogador1.getPontos() > jogador2.getPontos()) {
                writer.write("Vencedor: " + jogador1.getNome() + "\n");
            } else if (jogador2.getPontos() > jogador1.getPontos()) {
                writer.write("Vencedor: " + jogador2.getNome() + "\n");
            } else {
                writer.write("Empate\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

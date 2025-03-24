public class JogoDaVelha {
    private char[][] tabuleiro;
    private int tamanho;

    public JogoDaVelha(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new char[tamanho][tamanho];
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    public boolean realizaJogada(int linha, int coluna, char jogador) {
        if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho || tabuleiro[linha][coluna] != '-') {
            return false;
        }
        tabuleiro[linha][coluna] = jogador;
        return true;
    }

    public boolean verificaGanhador() {
        // Verifica linhas e colunas
        for (int i = 0; i < tamanho; i++) {
            if (verificaLinha(i) || verificaColuna(i)) {
                return true;
            }
        }

        // Verifica diagonais
        if (verificaDiagonalPrincipal() || verificaDiagonalSecundaria()) {
            return true;
        }

        return false;
    }

    private boolean verificaLinha(int linha) {
        char jogador = tabuleiro[linha][0];
        if (jogador == '-') return false;
        for (int i = 1; i < tamanho; i++) {
            if (tabuleiro[linha][i] != jogador) return false;
        }
        return true;
    }

    private boolean verificaColuna(int coluna) {
        char jogador = tabuleiro[0][coluna];
        if (jogador == '-') return false;
        for (int i = 1; i < tamanho; i++) {
            if (tabuleiro[i][coluna] != jogador) return false;
        }
        return true;
    }

    private boolean verificaDiagonalPrincipal() {
        char jogador = tabuleiro[0][0];
        if (jogador == '-') return false;
        for (int i = 1; i < tamanho; i++) {
            if (tabuleiro[i][i] != jogador) return false;
        }
        return true;
    }

    private boolean verificaDiagonalSecundaria() {
        char jogador = tabuleiro[0][tamanho - 1];
        if (jogador == '-') return false;
        for (int i = 1; i < tamanho; i++) {
            if (tabuleiro[i][tamanho - 1 - i] != jogador) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                sb.append(tabuleiro[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

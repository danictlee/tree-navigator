import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Arvore {
    private char[][] arvore;
    private int linhas;
    private int colunas;

    private int posicaoInicialX;
    private int posicaoInicialY;

    private int x, y;

    private Stack<Ponto> pontos = new Stack<Ponto>();

    private int soma;
    private int maiorSoma;
    private int numeroOperacoes;
    private String direcao;

    public Arvore(String caminhoArquivo) throws IOException {
        this.numeroOperacoes = 0;
        carregarArvore(caminhoArquivo);
    }

    public int getNumeroOperacoes() {
        return numeroOperacoes;
    }

    private void incrementarOperacoes() {
        numeroOperacoes++;
    }

    private void carregarArvore(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String[] dimensoes = reader.readLine().split(" ");
        linhas = Integer.parseInt(dimensoes[0]);
        colunas = Integer.parseInt(dimensoes[1]);

        arvore = new char[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            String linha = reader.readLine();
            for (int j = 0; j < colunas; j++) {
                arvore[i][j] = linha.charAt(j);
            }
        }
        reader.close();

        // Achar posição inicial
        posicaoInicialX = linhas - 1;

        for (int y = 0; y < colunas; y++) {
            char atual = arvore[posicaoInicialX][y];
            if (atual == 'V' || atual == 'W' || atual == '/' || atual == '\\' || atual == '|') {
                posicaoInicialY = y;
            }
        }

        x = posicaoInicialX;
        y = posicaoInicialY;
    }

    public void rastrearGalho() {
        long startTime = System.nanoTime(); 
        char atual = arvore[x][y];
        System.out.println("Iniciando a leitura da árvore...");
        direcao = "esquerda";

        if (atual == '/') {
            direcao = "direita";
        } else if (atual == '|') {
            direcao = "cima";
        } else if (atual == '\\') {
            direcao = "esquerda";
        }

        while (true) {
            if (x < 0 || x >= linhas || y < 0 || y >= colunas) {
                break;
            }

            atual = arvore[x][y];
            verifica(atual);
            incrementarOperacoes();
            if (direcao.equals("esquerda")) {
                x--;
                y--;
            } else if (direcao.equals("direita")) {
                x--;
                y++;
            } else if (direcao.equals("cima")) {
                x--;
            }
        }

        long endTime = System.nanoTime(); 
        long duration = (endTime - startTime) / 1_000_000; 
        System.out.println("Tempo para encontrar o galho com maior soma: " + duration + " ms");
    }

    public void verifica(char atual) {
        if (Character.isDigit(atual)) {
            soma += Character.getNumericValue(atual);
        } else {
            if (atual == 'V') {
                Ponto ponto = new Ponto(x, y, 'V', soma);
                pontos.push(ponto);
                direcao = "esquerda";
            } else if (atual == 'W') {
                Ponto ponto = new Ponto(x, y, 'W', soma);
                pontos.push(ponto);
                direcao = "esquerda";
            } else if (atual == '#') {
                if (soma > maiorSoma) {
                    maiorSoma = soma;
                }
                if (pontos.isEmpty()) {
                    System.out.println("Galho com a maior soma: " + maiorSoma);
                    return;
                }

                Ponto ponto = pontos.pop();
                if (ponto.getChar() == 'W') {
                    pontos.push(new Ponto(ponto.getX(), ponto.getY(), 'V', ponto.getSoma()));
                    direcao = "cima";
                } else if (ponto.getChar() == 'V') {
                    direcao = "direita";
                }
                x = ponto.getX();
                y = ponto.getY();
                soma = ponto.getSoma();
            }
        }
    }
}

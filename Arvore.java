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

    private String direcao;

    private int count;

    public Arvore(String caminhoArquivo) throws IOException {
        carregarArvore(caminhoArquivo);
    }

    private void carregarArvore(String caminhoArquivo) throws IOException {

        // Ler arvore
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

        rastrearGalho();
    }

    public void rastrearGalho() {

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
            atual = arvore[x][y];
            verifica(atual);
            
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

    }

    public void verifica(char atual) {
        count++;
        if (Character.isDigit(atual)) {
            soma += Character.getNumericValue(atual);
        }

        else {
            if (atual == 'V') {
                Ponto ponto = new Ponto(x, y, 'V', soma);
                pontos.push(ponto);
                direcao = "esquerda";
            } else if (atual == 'W') {
                Ponto ponto = new Ponto(x, y, 'W', soma);
                pontos.push(ponto);
                direcao = "esquerda";
            } else if (atual == '#') {
                if (soma > maiorSoma){
                    maiorSoma = soma;
                }
                if (pontos.isEmpty()){
                    System.out.println("Galho com a maior soma: " + maiorSoma);
                    System.out.println("Numero de operacoes: " + count);
                    System.exit(0);
                }

                Ponto ponto = pontos.pop();
                if(ponto.getChar() == 'W') {
                    pontos.push(new Ponto(ponto.getX(), ponto.getY(),'V', ponto.getSoma()));
                    direcao = "cima";
                } else if(ponto.getChar() == 'V') {
                    direcao = "direita";
                }
                x = ponto.getX();
                y = ponto.getY();
                soma = ponto.getSoma();
            }

        }

    }

}

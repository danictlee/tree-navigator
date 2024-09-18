import java.io.IOException;

public class Main {
    public static void main (String[] args){
        try {
        
            String caminhoArquivo = "casosDeTeste/casof500.txt";

            Arvore arvore = new Arvore(caminhoArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

import java.io.IOException;

public class Main {
    public static void main (String[] args){
        try {
            String caminhoArquivo = "casosDeTesteHeredia/casof30.txt";
            
            Arvore arvore = new Arvore(caminhoArquivo);
            arvore.rastrearGalho();
            
            System.out.println("Número de operações: " + arvore.getNumeroOperacoes());
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
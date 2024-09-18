public class Ponto {
    private int x;
    private int y;
    private char c;
    private int soma;

    public Ponto (int x, int y, char c, int soma){
        this.x = x;
        this.y = y;
        this.c = c;
        this.soma = soma;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public char getChar(){
        return c;
    }

    public void setChar(char c){
        this.c = c;
    }

    public int getSoma(){
        return soma;
    }

    public void setSoma(int soma){
        this.soma = soma;
    }

}

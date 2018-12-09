package pt.ulusofona.lp2.crazyChess;

public class Rei extends CrazyPiece{

    private byte id = 0;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    Rei() {

        this.moveBaixo = true;
        this.moveEsquerda = true;
        this.moveDireita = true;
        this.moveCima = true;
        this.moveCimaDireita = true;
        this.moveCimaEsquerda = true;
        this.moveBaixoDireita = true;
        this.moveBaixoEsquerda = true;
        this.maxMovHorizontal = 1;
        this.maxMovVertical = 1;
        this.minMovHorizontal = 1;
        this.minMovVertical = 1;

    }

}

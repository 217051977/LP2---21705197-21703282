package pt.ulusofona.lp2.crazyChess;

public class PoneiMagico extends CrazyPiece{

    private byte id = 2;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    PoneiMagico() {

        this.moveBaixo = true;
        this.moveEsquerda = true;
        this.moveDireita = true;
        this.moveCima = true;
        this.moveCimaDireita = false;
        this.moveCimaEsquerda = false;
        this.moveBaixoDireita = false;
        this.moveBaixoEsquerda = false;
        this.maxMovHorizontal = 2;
        this.maxMovVertical = 2;
        this.minMovHorizontal = 2;
        this.minMovVertical = 2;

    }

//  pode passar por cima de outras pecas

}

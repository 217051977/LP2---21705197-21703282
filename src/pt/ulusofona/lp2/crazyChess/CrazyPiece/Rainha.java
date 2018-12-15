package pt.ulusofona.lp2.crazyChess.CrazyPiece;

import pt.ulusofona.lp2.crazyChess.MainsClasses.CrazyPiece;

public class Rainha extends CrazyPiece {

    private byte id = 1;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    public Rainha() {

        this.moveBaixo = true;
        this.moveEsquerda = true;
        this.moveDireita = true;
        this.moveCima = true;
        this.moveCimaDireita = true;
        this.moveCimaEsquerda = true;
        this.moveBaixoDireita = true;
        this.moveBaixoEsquerda = true;
        this.maxMovHorizontal = 5;
        this.maxMovVertical = 5;
        this.minMovHorizontal = 1;
        this.minMovVertical = 1;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode comer outra rainha.

}

package pt.ulusofona.lp2.crazyChess.CrazyPiece;

import pt.ulusofona.lp2.crazyChess.MainsClasses.CrazyPiece;

public class TorreH extends CrazyPiece {

    private byte id = 4;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    public TorreH() {

        this.moveBaixo = false;
        this.moveEsquerda = true;
        this.moveDireita = true;
        this.moveCima = false;
        this.moveCimaDireita = false;
        this.moveCimaEsquerda = false;
        this.moveBaixoDireita = false;
        this.moveBaixoEsquerda = false;
        this.maxMovHorizontal = 1;  // corrigir max de pacos
        this.maxMovVertical = 1;    // corrigir max de pacos
        this.minMovHorizontal = 0;
        this.minMovVertical = 0;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

package pt.ulusofona.lp2.crazyChess.CrazyPiece;

import pt.ulusofona.lp2.crazyChess.MainsClasses.CrazyPiece;

public class Lebre extends CrazyPiece {

    private byte id = 6;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    public Lebre() {

        this.moveBaixo = false;
        this.moveEsquerda = false;
        this.moveDireita = false;
        this.moveCima = false;
        this.moveCimaDireita = true;
        this.moveCimaEsquerda = true;
        this.moveBaixoDireita = true;
        this.moveBaixoEsquerda = true;
        this.maxMovHorizontal = 1;
        this.maxMovVertical = 1;
        this.minMovHorizontal = 1;
        this.minMovVertical = 1;

    }

//  so pode mover nos turnos pares.

}

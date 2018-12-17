package pt.ulusofona.lp2.crazyChess;

import pt.ulusofona.lp2.crazyChess.CrazyPiece;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico() {

//      set the movement of the Ponei magico type piece
        super.moveBaixo = false;
        super.moveEsquerda = false;
        super.moveDireita = false;
        super.moveCima = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.valorRelativo = 5;

    }

    public PoneiMagico(int id, String name) {

//      set the movement of the Ponei magico type piece
        super.moveBaixo = false;
        super.moveEsquerda = false;
        super.moveDireita = false;
        super.moveCima = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.valorRelativo = 5;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

//  pode passar por cima de outras pecas

}

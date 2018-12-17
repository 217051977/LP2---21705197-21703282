package pt.ulusofona.lp2.crazyChess;

import pt.ulusofona.lp2.crazyChess.CrazyPiece;

public class Rainha extends CrazyPiece {

    Rainha() {

//      set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 1 (Queen)
        super.type = 1;

//      set the relative value
        super.valorRelativo = 8;

    }

    public Rainha(int id, String name) {

//      set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 1 (Queen)
        super.type = 1;

//      set the relative value
        super.valorRelativo = 8;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode comer outra rainha.

}

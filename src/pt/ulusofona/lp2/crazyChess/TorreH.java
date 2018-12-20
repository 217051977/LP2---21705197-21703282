package pt.ulusofona.lp2.crazyChess;

import pt.ulusofona.lp2.crazyChess.CrazyPiece;

public class TorreH extends CrazyPiece {

    TorreH() {

//      set the movement of the H Tower type piece
        super.moveVertical = false;
        super.moveDiagonal = false;

//      set how much it can move
        super.maxMovHorizontal = Integer.MAX_VALUE;
        super.maxMovVertical = 0;

//      set how much it has to move
        super.minMovHorizontal = 1;

//      set the type piece as 4 (H Tower)
        super.type = 4;

//      set the relative value
        super.valorRelativo = 3;

    }

    public TorreH(int id, String name) {

//      set the movement of the H Tower type piece
        super.moveVertical = false;
        super.moveDiagonal = false;

//      set how much it can move
        super.maxMovHorizontal = 1;
        super.maxMovVertical = 1;

//      set how much it has to move
        super.minMovHorizontal = 0;
        super.minMovVertical = 0;

//      set the type piece as 4 (H Tower)
        super.type = 4;

//      set the relative value
        super.valorRelativo = 3;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

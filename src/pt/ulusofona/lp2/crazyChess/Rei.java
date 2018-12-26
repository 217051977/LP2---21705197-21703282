package pt.ulusofona.lp2.crazyChess;

import pt.ulusofona.lp2.crazyChess.CrazyPiece;
import pt.ulusofona.lp2.crazyChess.Position;

public class Rei extends CrazyPiece {

    Rei() {

//      set the type piece as 0 (king)
        super.type = 0;
/*
//      set the relative value
        super.relativeValue = Integer.MAX_VALUE;
*/
    }

    public Rei(int id, String name) {

//      set the type piece as 0 (king)
        super.type = 0;
/*
//      set the relative value
        super.relativeValue = Integer.MAX_VALUE;
*/

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

}

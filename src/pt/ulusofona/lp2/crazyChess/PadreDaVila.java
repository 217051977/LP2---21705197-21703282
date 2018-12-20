package pt.ulusofona.lp2.crazyChess;

import pt.ulusofona.lp2.crazyChess.CrazyPiece;

public class PadreDaVila extends CrazyPiece {

    PadreDaVila() {

//      set the movement of the Padre da vila type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 3;
        super.maxMovVertical = 3;

//      set how much it has to move
        super.minMovHorizontal = 1;
        super.minMovVertical = 1;

//      set the type piece as 3 (Padre da vila)
        super.type = 3;

//      set the relative value
        super.valorRelativo = 3;

    }

    public PadreDaVila(int id, String name) {

//      set the movement of the Padre da vila type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 3;
        super.maxMovVertical = 3;

//      set how much it has to move
        super.minMovHorizontal = 1;
        super.minMovVertical = 1;

//      set the type piece as 3 (Padre da vila)
        super.type = 3;

//      set the relative value
        super.valorRelativo = 3;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode estar a menos de 2 casa da rainha adversaria.

}

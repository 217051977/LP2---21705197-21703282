package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Lebre extends CrazyPiece {

    Lebre() {

//      set the movement of the Lebre type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set the type piece as 6 (Lebre)
        super.type = 6;

//      set the relative value
        super.relativeValue = 2;

    }

    public Lebre(int id, String name) {

//      set the movement of the Lebre type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it has to move
        super.minMovHorizontal = 1;
        super.minMovVertical = 1;

//      set the type piece as 6 (Lebre)
        super.type = 6;

//      set the relative value
        super.relativeValue = 2;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        super.possiblesPositions.removeAll(possiblesPositions);

        if (shift.getCount() % 2 == 0 || shift.getCount() == 0 ) {

            super.possiblesPositions_Diagonal(boardSize);

            super.possiblesPositions_RemovePosition(crazyPiecesInGame);

        }

        return possiblesPositions;

    }

//  so pode mover nos turnos pares.

}

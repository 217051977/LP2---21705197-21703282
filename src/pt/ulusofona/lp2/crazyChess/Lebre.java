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

//      set typeName as this className
        super.typeName = "Lebre";

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        super.possiblesPositions.clear();

        if (shift.getCount() % 2 == 0 || shift.getCount() == 0 ) {

            super.possiblesPositions_Diagonal(boardSize);

            super.possiblesPositions_RemovePosition(crazyPiecesInGame);

        }

        return possiblesPositions;

    }

//  so pode mover nos turnos pares.

}

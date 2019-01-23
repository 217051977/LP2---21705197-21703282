package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class TorreV extends CrazyPiece {

    TorreV() {

//      set the movement of the V Tower type piece
        super.moveHorizontal = false;
        super.moveDiagonal = false;

//      set how much it can move
        super.maxMovHorizontal = 0;
        super.maxMovVertical = Integer.MAX_VALUE;

//      set the type piece as 5 (V Tower)
        super.type = 5;

//      set the nPoints value
        super.nPoints = 3;

//      set the relative value
        super.relativeValue = String.valueOf(super.nPoints);

//      set typeName as this className
        super.typeName = "TorreV";

    }

    @Override
    protected void possiblesPositions_Vertical(int boardSize) {

//      up
        for (int up = position.getyActual() - minMovVertical - 1; up >= position.getyActual() - maxMovVertical; up--) {

            if (up >= 0) {

                possiblesPositions.add(super.createPosition_Vertical(up));

            } else {

                break;

            }

        }

//      down
        for (int down = position.getyActual() + minMovVertical + 1; down < boardSize; down++) {

            possiblesPositions.add(createPosition_Vertical(down));

        }

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        super.possiblesPositions.clear();

        possiblesPositions_Vertical(boardSize);

        super.possiblesPositions_RemovePosition(crazyPiecesInGame);

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

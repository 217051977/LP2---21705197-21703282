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

//      set the impossibility to change type!
        super.canChangeType = false;

//      set the relative value
        super.relativeValue = 3;

    }

    public TorreV(int id, String name) {

//      set the movement of the V Tower type piece
        super.moveHorizontal = false;
        super.moveDiagonal = false;
//      set how much it can move
        super.maxMovHorizontal = 0;
        super.maxMovVertical = 0;

//      set how much it has to move
        super.minMovHorizontal = 1;
        super.minMovVertical = 1;

//      set the type piece as 5 (V Tower)
        super.type = 5;

//      set the impossibility to change type!
        super.canChangeType = false;

//      set the relative value
        super.relativeValue = 3;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

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
    public List<Position> possiblesPositions(int boardSize) {

        possiblesPositions_Vertical(boardSize);

        super.possiblesPositions_RemovePosition();

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

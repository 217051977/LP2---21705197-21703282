package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class TorreH extends CrazyPiece {

    TorreH() {

//      set the movement of the H Tower type piece
        super.moveVertical = false;
        super.moveDiagonal = false;

//      set how much it can move
        super.maxMovHorizontal = Integer.MAX_VALUE;
        super.maxMovVertical = 0;

//      set the type piece as 4 (H Tower)
        super.type = 4;

//      set the relative value
        super.relativeValue = 3;

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
        super.relativeValue = 3;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    protected void possiblesPositions_Horizontal(int boardSize) {

//      left
        for (int left = position.getxActual() - minMovHorizontal - 1; left >= position.getxActual() - maxMovHorizontal; left--) {

            if (left >= 0) {

                possiblesPositions.add(super.createPosition_Horizontal(left));

            } else {

                break;

            }

        }

//      right
        for (int right = position.getxActual() + minMovHorizontal + 1; right < boardSize; right++) {

            possiblesPositions.add(super.createPosition_Horizontal(right));

        }

    }

    @Override
    public List<Position> possiblesPositions(int boardSize) {

        possiblesPositions_Horizontal(boardSize);

        super.possiblesPositions_RemovePosition();

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

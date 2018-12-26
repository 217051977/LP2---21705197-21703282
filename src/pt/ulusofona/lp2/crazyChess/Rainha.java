package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends CrazyPiece {

    Rainha() {

//      set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 1 (Queen)
        super.type = 1;

//      set the relative value
        super.relativeValue = 8;

    }

    public Rainha(int id, String name) {

//      set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 1 (Queen)
        super.type = 1;

//      set the relative value
        super.relativeValue = 8;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    public List<Position> possiblesPositions(int boardSize) {

        possiblesPositions.removeAll(possiblesPositions);

        super.possiblesPositions_Horizontal(boardSize);

        super.possiblesPositions_Vertical(boardSize);

        super.possiblesPositions_Diagonal(boardSize);

        super.possiblesPositions_RemovePosition();
        List<Position> positionsBarrier = new ArrayList<>();

        for (CrazyPiece thisPiece : Simulador.crazyPiecesInGame) {

            if (thisPiece.getType() == 1) {

                positionsBarrier.add(thisPiece.getPosition());

            } else if (thisPiece.getType() == 3) {

                positionsBarrier = thisPiece.getPosition().oneSquareBarrier(boardSize);

            }

        }

        CrazyPiece priestAuxiliary = new PadreDaVila();
        ((PadreDaVila) priestAuxiliary).removePiecesInSideBarrier(possiblesPositions, positionsBarrier);

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode comer outra rainha.

}

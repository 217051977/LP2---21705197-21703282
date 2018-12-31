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

//      set typeName as this className
        super.typeName = "Rainha";

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        possiblesPositions.clear();

        super.possiblesPositions_Horizontal(boardSize);

        super.possiblesPositions_Vertical(boardSize);

        super.possiblesPositions_Diagonal(boardSize);

        super.possiblesPositions_RemovePosition(crazyPiecesInGame);

        List<Position> positionsBarrier = new ArrayList<>();

        for (CrazyPiece thisPiece : crazyPiecesInGame) {

            if (thisPiece.getType() == 1) {

                positionsBarrier.add(thisPiece.getPosition());

            } else if (thisPiece.getType() == 3) {

                if (thisPiece.getIDTeam() != shift.getIdTeam()) {

                    positionsBarrier.addAll(thisPiece.getPosition().oneSquareBarrier(boardSize));

                }

            }

        }

        PadreDaVila priestAuxiliary = new PadreDaVila();
        priestAuxiliary.removePiecesInSideBarrier(possiblesPositions, positionsBarrier);

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode comer outra rainha.

}

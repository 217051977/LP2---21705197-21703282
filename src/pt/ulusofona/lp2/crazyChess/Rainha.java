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

//      set the nPoints value
        super.nPoints = 8;

//      set the relative value
        super.relativeValue = String.valueOf(super.nPoints);

//      set typeName as this className
        super.typeName = "Rainha";

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        super.possiblesPositions(boardSize, crazyPiecesInGame, shift);

        List<Position> positionsBarrier = new ArrayList<>();

        for (CrazyPiece thisPiece : crazyPiecesInGame) {

            if (thisPiece.getInGame()) {

                if (thisPiece.getType() == 1 || (thisPiece.getType() == 7 && thisPiece.getPieceId() == 1)) {

                    positionsBarrier.add(thisPiece.getPosition());

                } else if (thisPiece.getType() == 3 || (thisPiece.getType() == 7 && thisPiece.getPieceId() == 3)) {

                    if (thisPiece.getIDTeam() != shift.getIdTeam()) {

                        positionsBarrier.addAll(thisPiece.getPosition().oneSquareBarrier(boardSize));

                    }

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

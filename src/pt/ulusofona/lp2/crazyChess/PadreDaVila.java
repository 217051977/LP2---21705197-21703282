package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PadreDaVila extends CrazyPiece {

    PadreDaVila() {

//      set the movement of the Padre da vila type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 3;
        super.maxMovVertical = 3;

//      set the type piece as 3 (Padre da vila)
        super.type = 3;

//      set the relative value
        super.relativeValue = 3;

//      set typeName as this className
        super.typeName = "Padre da Vila";

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        super.possiblesPositions_Diagonal(boardSize);

        super.possiblesPositions_RemovePosition(crazyPiecesInGame);

        List<Position> positionsBarrier = new ArrayList<>();

        for (CrazyPiece thisPiece : crazyPiecesInGame) {

            if (thisPiece.getType() == 1 || (thisPiece.getType() == 7 && thisPiece.getPieceId() == 1)) {

                if (thisPiece.getInGame()) {

                    if (thisPiece.getIDTeam() != shift.getIdTeam()) {

                        positionsBarrier.addAll(thisPiece.getPosition().oneSquareBarrier(boardSize));

                    }

                }

            }

        }

        return removePiecesInSideBarrier(possiblesPositions, positionsBarrier);

    }

    protected List<Position> removePiecesInSideBarrier(List<Position> possiblesPositions, List<Position> positionsBarrier) {

        List<Position> POSITIONS_TO_ERASE = new ArrayList<>();

        for (Position thisPosition : possiblesPositions) {

            for (Position positionsEraser : positionsBarrier) {

                if (thisPosition.equals(positionsEraser)) {

                    POSITIONS_TO_ERASE.add(thisPosition);

                }

            }

        }

        possiblesPositions.removeAll(POSITIONS_TO_ERASE);

        return possiblesPositions;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode estar a menos de 2 casa da rainha adversaria.

}

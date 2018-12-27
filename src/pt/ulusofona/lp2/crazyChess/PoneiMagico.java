package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico() {

//      set the movement of the Ponei magico type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.relativeValue = 5;

    }

    public PoneiMagico(int id, String name) {

//      set the movement of the Ponei magico type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.relativeValue = 5;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        searchForAPiece_DownRight(boardSize, crazyPiecesInGame);
        searchForAPiece_DownLeft(boardSize, crazyPiecesInGame);
        searchForAPiece_UpLeft(boardSize, crazyPiecesInGame);
        searchForAPiece_UpRight(boardSize, crazyPiecesInGame);

        return possiblesPositions;

    }

    private void searchForAPiece_DownRight(int boardSize, List<CrazyPiece> crazyPiecesInGame) {

        boolean pieceFound;

        int minRight = super.position.getxActual() + 1;
        int maxRight = super.position.getxActual() + 2;
        int minDown = super.position.getyActual() + 1;
        int maxDown = super.position.getyActual() + 2;

        if (maxRight < boardSize && maxDown < boardSize) {

//      check down
            pieceFound = checkPieceInTheWay(minDown, maxDown, super.position.getxActual(), true, boardSize, crazyPiecesInGame);

//      if there wasn't a piece in the way check right
            if (!pieceFound) {

                pieceFound = checkPieceInTheWay(minRight, maxRight, super.position.getyActual() + 2, false, boardSize, crazyPiecesInGame);

            }
//      if there was a piece in the way
            if (pieceFound) {

//          check right
                pieceFound = checkPieceInTheWay(minRight, maxRight, super.position.getyActual(), false, boardSize, crazyPiecesInGame);

//          if there wasn't a piece in the way check down
                if (!pieceFound) {

                    pieceFound = checkPieceInTheWay(minDown, maxDown, super.position.getxActual() + 2, true, boardSize, crazyPiecesInGame);

                }

            }

            if (!pieceFound) {

                addSuggestion(2, 2);

            }

        }

    }

    private void searchForAPiece_DownLeft(int boardSize, List<CrazyPiece> crazyPiecesInGame) {

        boolean pieceFound;

        int minLeft = super.position.getxActual() - 2;
        int maxLeft = super.position.getxActual() - 1;
        int minDown = super.position.getyActual() + 1;
        int maxDown = super.position.getyActual() + 2;

        if (maxLeft >= 0 && maxDown < boardSize) {

//      check down
            pieceFound = checkPieceInTheWay(minDown, maxDown, super.position.getxActual(), true, boardSize, crazyPiecesInGame);

//      if there wasn't a piece in the way check left
            if (!pieceFound) {

                pieceFound = checkPieceInTheWay(minLeft, maxLeft, super.position.getyActual() + 2, false, boardSize, crazyPiecesInGame);

            }
//      if there was a piece in the way
            if (pieceFound) {

//          check left
                pieceFound = checkPieceInTheWay(minLeft, maxLeft, super.position.getyActual(), false, boardSize, crazyPiecesInGame);

//          if there wasn't a piece in the way check down
                if (!pieceFound) {

                    pieceFound = checkPieceInTheWay(minDown, maxDown, super.position.getxActual() - 2, true, boardSize, crazyPiecesInGame);

                }

            }

            if (!pieceFound) {

                addSuggestion(-2, 2);

            }

        }

    }

    private void searchForAPiece_UpLeft(int boardSize, List<CrazyPiece> crazyPiecesInGame) {

        boolean pieceFound;

        int minUp = super.position.getyActual() - 2;
        int maxUp = super.position.getyActual() - 1;
        int minLeft = super.position.getxActual() - 2;
        int maxLeft = super.position.getxActual() - 1;

        if (minUp >= 0 && minLeft >= 0) {

//          check up
            pieceFound = checkPieceInTheWay(minUp, maxUp, super.position.getxActual(), true, boardSize, crazyPiecesInGame);

//          if there wasn't a piece in the way check left
            if (!pieceFound) {

                pieceFound = checkPieceInTheWay(minLeft, maxLeft, super.position.getyActual() - 2, false, boardSize, crazyPiecesInGame);

            }
//          if there was a piece in the way
            if (pieceFound) {

//              check left
                pieceFound = checkPieceInTheWay(minLeft, maxLeft, super.position.getyActual(), false, boardSize, crazyPiecesInGame);

//              if there wasn't a piece in the way check up
                if (!pieceFound) {

                    pieceFound = checkPieceInTheWay(minUp, maxUp, super.position.getxActual() - 2, true, boardSize, crazyPiecesInGame);

                }

            }

            if (!pieceFound) {

                addSuggestion(-2, -2);

            }

        }

    }

    private void searchForAPiece_UpRight(int boardSize,  List<CrazyPiece> crazyPiecesInGame) {

        boolean pieceFound;

        int minUp = super.position.getyActual() - 2;
        int maxUp = super.position.getyActual() - 1;
        int minRight = super.position.getxActual() + 1;
        int maxRight = super.position.getxActual() + 2;

        if (minUp >= 0 && maxRight < boardSize) {

//          check up
            pieceFound = checkPieceInTheWay(minUp, maxUp, super.position.getxActual(), true, boardSize, crazyPiecesInGame);

//          if there wasn't a piece in the way check right
            if (!pieceFound) {

                pieceFound = checkPieceInTheWay(minRight, maxRight, super.position.getyActual() - 2, false, boardSize, crazyPiecesInGame);

            }
//          if there was a piece in the way
            if (pieceFound) {

//              check right
                pieceFound = checkPieceInTheWay(minRight, maxRight, super.position.getyActual(), false, boardSize, crazyPiecesInGame);

//              if there wasn't a piece in the way check up
                if (!pieceFound) {

                    pieceFound = checkPieceInTheWay(minUp, maxUp, super.position.getxActual() + 2, true, boardSize, crazyPiecesInGame);

                }

            }

            if (!pieceFound) {

                addSuggestion(2, -2);

            }

        }

    }

    private boolean checkPieceInTheWay(int min, int max,int otherCoordinate , boolean vertical, int boardSize,  List<CrazyPiece> crazyPiecesInGame) {

        Position thisPosition;
        boolean pieceFound = false;

        for (int mov = min; mov <= max; mov++) {

            if (vertical) {

                thisPosition = new Position(otherCoordinate, mov);

            } else {

                thisPosition = new Position(mov, otherCoordinate);

            }

            pieceFound = findPieceIn(thisPosition,  crazyPiecesInGame);

            if (pieceFound) {

                break;

            }


        }

        return pieceFound;

    }

    private boolean findPieceIn(Position thisPosition,  List<CrazyPiece> crazyPiecesInGame) {

        boolean pieceFound = false;

        for (CrazyPiece thisPiece : crazyPiecesInGame) {

            if (thisPiece.getPosition().equals(thisPosition)) {

                if (thisPiece.getType() == 0) {

                    pieceFound = true;
                    break;

                }

            }

        }

        return pieceFound;

    }

    private void addSuggestion(int horizontal, int vertical) {

        Position suggestedPosition = new Position((super.position.getxActual() + horizontal), (super.position.getyActual() + vertical));
        possiblesPositions.add(suggestedPosition);

    }

//  pode passar por cima de outras pecas

}

package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {

    public Joker() {

//      Because it stats as a queen:
        //  set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 7 (Joker)
        super.type = 7;

//      set the impossibility to change type!
        super.canChangeType = true; // for now

//      set the relative value
        super.relativeValue = 4;

    }

    public Joker(int id, String name) {

//      Because it stats as a queen:
        //  set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 7 (Joker)
        super.type = 7;

//      set the impossibility to change type!
        super.canChangeType = true; // for now

//      set the relative value
        super.relativeValue = 4;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    public void changePieceType() {

        if (pieceId == 6) {

            pieceId = 1;

        } else {

            pieceId++;

        }

    }

    @Override
    public void undoPieceType() {

        if (pieceId != 1) {

            pieceId--;

        } else {

            pieceId = 6;

        }

    }

    @Override
    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        possiblesPositions.removeAll(possiblesPositions);


        switch (pieceId) {

            case 1: {

                CrazyPiece queenJoker = new Rainha();
                queenJoker.setPosition(position);
//                queenJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = queenJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }
            break;

            case 2 : {

                CrazyPiece ponyJoker = new PoneiMagico();
                ponyJoker.setPosition(position);
//                ponyJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = ponyJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }
            break;

            case 3 : {

                CrazyPiece priestJoker = new PadreDaVila();
                priestJoker.setPosition(position);
//                priestJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = priestJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }
            break;

            case 4 : {

                CrazyPiece towerHJoker = new TorreH();
                towerHJoker.setPosition(position);
//                towerHJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = towerHJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }
            break;

            case 5 : {

                CrazyPiece towerVJoker = new TorreV();
                towerVJoker.setPosition(position);
//                towerVJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = towerVJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }
            break;

            case 6 : {

                CrazyPiece bunnyJoker = new Lebre();
                bunnyJoker.setPosition(position);
//                bunnyJoker.setPosition(new Position(super.position.getxActual(), super.position.getyActual()));

                possiblesPositions = bunnyJoker.possiblesPositions(boardSize, crazyPiecesInGame, shift);

            }

        }

        return possiblesPositions;

    }

}

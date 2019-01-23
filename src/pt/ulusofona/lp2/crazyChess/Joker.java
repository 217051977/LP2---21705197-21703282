package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Joker extends CrazyPiece {

    public Joker() {

        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 7 (Joker)
        super.type = 7;

//      set the nPoints value
        super.nPoints = 4;

//      set the relative value
        super.relativeValue = String.valueOf(super.nPoints);

//      set typeName as this className
        super.typeName = "Joker/Rainha";

    }

    public String getTypeName() {

        return typeName;

    }

    public void nextType() {

        if (super.pieceId == 6) {

            super.pieceId = 1;
            super.typeName = "Joker/Rainha";

        } else {

            pieceId++;

            switch (pieceId) {

                case 2 : {

                    super.typeName = "Joker/Ponei Mágico";

                }
                break;

                case 3 : {

                    super.typeName = "Joker/Padre da Vila";

                }
                break;

                case 4 : {

                    super.typeName = "Joker/TorreH";

                }
                break;

                case 5 : {

                    super.typeName = "Joker/TorreV";

                }
                break;

                case 6 : {

                    super.typeName = "Joker/Lebre";

                }

            }

        }

    }

    public void changePieceType(int pieceType) {

        pieceId = (byte) pieceType;

        switch (pieceId) {

            case 1 : {

                super.typeName = "Joker/Rainha";

            }
            break;

            case 2 : {

                super.typeName = "Joker/Ponei Mágico";

            }
            break;

            case 3 : {

                super.typeName = "Joker/Padre da Vila";

            }
            break;

            case 4 : {

                super.typeName = "Joker/TorreH";

            }
            break;

            case 5 : {

                super.typeName = "Joker/TorreV";

            }
            break;

            case 6 : {

                super.typeName = "Joker/Lebre";

            }

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

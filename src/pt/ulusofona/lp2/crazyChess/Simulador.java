package pt.ulusofona.lp2.crazyChess;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//  corrigir metodo verificar jogada

public class Simulador {

    private int boardSize;
    private static int numberOfBlackPiecesCaptured = 0;
    private static int numberOfWhitePiecesCaptured = 0;
    private static int numberOfValidPlaysByBlackTeam = 0;
    private static int numberOfValidPlaysByWhiteTeam = 0;
    private static int numberOfInvalidPlaysByBlackTeam = 0;
    private static int numberOfInvalidPlaysByWhiteTeam = 0;
    static List<CrazyPiece> crazyPiecesInGame = new ArrayList<>();
    List<CrazyPiece> allCrazyPieces = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<String> suggestedPlay = new ArrayList<>();
    List<String> scores = new ArrayList<>();
    List<Team> team = new ArrayList<>();
    static Shift shift = new Shift();
    public static boolean firstCapture = false;

//    Constructor
    public Simulador() {}//*********************************************************************************************

    public Simulador(int boardSize) {

        this.boardSize = boardSize;

    }//*****************************************************************************

//    gets
    public int getTamanhoTabuleiro() {

        return boardSize;

    }//****************************************************************************

    public List<CrazyPiece> getPecasMalucas() {

        return crazyPiecesInGame;

    }//*******************************************************************

    public List<String> getAutores() {

        if (authors.size() == 0) {

            this.authors.add("Bruno Miguel Dias Leal, nº 21705197");
            this.authors.add("João Domingos, nº 21703282");

        }

        return authors;

    }//****************************************************************************

    public List<String> getResultados() {

        return scores;

    }//*************************************************************************

    public int getIDPeca(int x, int y) {

        Position position = new Position(x, y);

        for (CrazyPiece piece: crazyPiecesInGame){

            if (piece.getPosition().equals(position)) {

                return piece.getId();

            }

        }

        //System.out.println("Piece not founded!");

        return 0;

    }//**************************************************************************

    public int getIDEquipaAJogar() {

        return shift.getIdTeam();

    }//******************************************************************************

    public int getNumberOfBlackPiecesCaptured() {

        return numberOfBlackPiecesCaptured;

    }//*****************************************************************

    public int getNumberOfWhitePiecesCaptured() {

        return numberOfWhitePiecesCaptured;

    }//*****************************************************************

    public int getNumberOfValidPlaysByBlackTeam() {

        return numberOfValidPlaysByBlackTeam;

    }//***************************************************************

    public int getNumberOfValidPlaysByWhiteTeam() {

        return numberOfValidPlaysByWhiteTeam;

    }//***************************************************************

    public int getNumberOfInvalidPlaysByBlackTeam() {

        return numberOfInvalidPlaysByBlackTeam;

    }//*************************************************************

    public int getNumberOfInvalidPlaysByWhiteTeam() {

        return numberOfInvalidPlaysByWhiteTeam;

    }//*************************************************************

    public Shift getShift() {

        return shift;

    }//*************************************************************************************

    public boolean getFirstCapture() {

        return firstCapture;

    }//****************************************************************************

    public List<String> obterSugestoesJogada(int xO, int yO) {

        List<Position> possiblesPositions;

//      clear the list
        suggestedPlay.removeAll(suggestedPlay);

//      Create a new position with the destination
        Position origin = new Position(xO, yO);

//      For each piece on crazyPiecesInGame variable
        for (CrazyPiece thisPiece : crazyPiecesInGame) {

//          Check if the this piece is in the position created
            if (thisPiece.getPosition().equals(origin)) {

//              Check if this piece belongs to the team that is playing
                if (thisPiece.getIDTeam() == shift.getIdTeam()) {

//                  Create a list with all the positions possibles for the piece moved
                    possiblesPositions = thisPiece.possiblesPositions(boardSize);

                    for (Position thisPosition : possiblesPositions) {

                        suggestedPlay.add("\"" + thisPosition.getxActual() + "," + thisPosition.getyActual() + "\"");

                    }

                }

            }

        }

        if (suggestedPlay.isEmpty()) {

//          Add to the list
            suggestedPlay.add("Pedido inválido");

        }

//      return the list
        return suggestedPlay;

    }//****************************************************

//  Undo Play
    public void anularJogadaAnterior() {



    }

//  Save Game
    public boolean gravarJogo(File ficheiroDestino) {

//        try {
//
//        }catch (FileNotFoundException e) {
//
//            return false;
//
//        }

        return true;

    }

    public boolean iniciaJogo(File ficheiroInicial) {

        try {

            Scanner scan = new Scanner(ficheiroInicial);

            if (!scan.hasNextLine()) {

                throw new NullPointerException();

            }

            int nPieces = 0,
                    nPiecesMaxIndex,
                    boardSizeMaxIndex = 0,
                    nLines = 0,
                    yPosition = 0;
            String[] piecesInfo;
            String[] boardInfo;

            while (scan.hasNextLine()) {

                String linha = scan.nextLine();
                System.out.println(linha);
                nPiecesMaxIndex = nPieces + 2;
                boardSizeMaxIndex = boardSize + nPiecesMaxIndex;

                /*

                tenho de ver se n pieces != npiecesmax
                tenho de ver se n npiecesmax e != maxtabuleiro

                 */

//                quatro partes;
//
//                1 - dimensoes do tabuleiro => int
//                2 - quantidade peças existentes no tabuleiro
//                3 - descreve as pieces existentes no tabuleiro
//                4 - conteudo inicial do tabuleiro ( posicao das pieces)

                if (nLines == 0) {

                    try {

                        boardSize = Integer.parseInt(linha);

                    }catch (ArithmeticException notAnInteger) {

                        return false;

                    }

                } else if (nLines == 1) {

                    try {

                        nPieces = Integer.parseInt(linha);

                    }catch (ArithmeticException notAnInteger) {

                        return false;

                    }

                } else if (nLines < nPiecesMaxIndex) {

                    piecesInfo = linha.split(":");

                    if (piecesInfo.length != 4) {

                        throw new NullPointerException();

                    }

                    for (int i = 0; i < 4; i++) {

                        if (i == 3) {

                            try {

                                Integer.parseInt(piecesInfo[i]);
                                return false;

                            } catch (Exception e) {

                                Ignore isAPiece;

                            }

                        }else {

                            try {

                                Integer.parseInt(piecesInfo[i]);

                            } catch (Exception e) {

                                return false;

                            }

                        }

                    }

                    CrazyPiece piece;


                    if (Integer.parseInt(piecesInfo[1]) == 0) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new ReiPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new ReiBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 1) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new RainhaPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new RainhaBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 2) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new PoneiMagicoPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new PoneiMagicoBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 3) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new PadreDaVilaPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new PadreDaVilaBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 4) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new TorreHPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new TorreHBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 5) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new TorreVPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new TorreVBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 6) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new LebrePreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new LebreBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 7) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new JokerPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new JokerBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }
//
//                    } else if (Integer.parseInt(piecesInfo[1]) == 8) {
//
//                        piece = new Rei();
//
                    } else {

                        throw new NumberFormatException();

                    }

                    crazyPiecesInGame.add(piece);

                } else if (nLines < boardSizeMaxIndex) {

                    boardInfo = linha.split(":");

                    if (boardInfo.length != boardSize) {

                        throw new NullPointerException();

                    }

                    for (int index = 0; index < boardInfo.length; index++) {

                        if (Integer.parseInt(boardInfo[index]) != 0) {

                            for (CrazyPiece piece : crazyPiecesInGame) {

                                if (piece.getId() == Integer.parseInt(boardInfo[index])) {

                                    Position position = new Position(index, yPosition);
                                    piece.setPosition(position);
                                    piece.isInGame();
                                    System.out.println(piece);
                                    break;

                                }

                            }

                        }

                    }

                    yPosition++;

                } else {

                    System.out.println("There's too much info!");
                    return false;

                }

                nLines++;

            }

            if (nLines == boardSizeMaxIndex) {

                allCrazyPieces.addAll(crazyPiecesInGame);
                return true;

            }

            return false;

        } catch (FileNotFoundException e) {

            System.out.println(ficheiroInicial.getName() + " not found!");

            return false;

        } catch (NumberFormatException notInt) {

            System.out.println("The file inputs are not valid!");

            return false;

        } catch (NullPointerException notEnoughInfo) {

            System.out.println("There's information missing in the file!");

            return false;

        }

    }//*************************************************************

//  Processes the play
    public boolean processaJogada(int xO, int yO, int xD, int yD) {

//      If the positionOrigin is in side the board
        if (xO >= 0 && xO < boardSize && yO >= 0 && yO < boardSize) {

//          Check if the move is between the board rang
            if (xD >= 0 && xD <= (boardSize - 1) && yD >= 0 && yD <= (boardSize - 1)) {

//              If the originPosition is different dorm the destinyPosition
                if (xO != xD || yO != yD) {

//                  Creates the positionOrigin as the origin position
                    Position origin = new Position(xO, yO);

                    Position destiny = new Position(xD, yD);

//                  For every piece in the list of pieces in the game
                    for (CrazyPiece thiPiece : crazyPiecesInGame) {

//                      If the position of this piece is in the positionOrigin
                        if (thiPiece.getPosition().equals(origin)) {

//                          Check if it belongs to the playing team
                            if (thiPiece.getIDTeam() == shift.getIdTeam()) {

//                              return the value returned of the move method of this piece
                                return thiPiece.move(destiny, boardSize);

                            }

//                          If the piece doesn't belong to the playing team leaves the cycle
                            break;

                        }

                    }

                }

            }

        }

//      Add an invalid attempt to the playing team
        addScoresStatsInvalid();

//      Returns false
        return false;

    }//***********************************************

//  falta a pontuacao
    public boolean jogoTerminado() {

        int nreiBranco = 0;
        int nreiPreto = 0;

        if (crazyPiecesInGame.size() == 0) {

            return true;

        } else if (shift.getCountNoCapture() == 10 && firstCapture) {

            addScoreStatsToPrint("EMPATE");

            shift.resetCount();
            shift.resetCountNoCapture();

            return true;

        } else {

            for (CrazyPiece piece : crazyPiecesInGame) {

                if (piece.getType() == 0) {

                    if (piece.getInGame()) {

                        if (piece.getIDTeam() == 10) {

                            nreiPreto++;

                        } else if (piece.getIDTeam() == 20) {

                            nreiBranco++;

                        }

                    }

                }

            }

            if (nreiPreto == 0) {

                System.out.println("VENCERAM AS BRANCAS");

                addScoreStatsToPrint("VENCERAM AS BRANCAS");

                return true;

            } else if (nreiBranco == 0) {

                System.out.println("VENCERAM AS PRETAS");

                addScoreStatsToPrint("VENCERAM AS PRETAS");

                return true;

            } else if (nreiPreto == 1 && nreiBranco == 1) {

                addScoreStatsToPrint("EMPATE");

                return true;

            } else {

                return false;

            }

        }

    }//******************************************************************************

    private void changeJokerType() {

        for (CrazyPiece piece : crazyPiecesInGame) {

            if (piece.getType() == 7) {

                piece.changePieceType();

                break;

            }

        }

    }

    private void setScores_Capture(CrazyPiece piece) {

//      If the team that is playing is:
        switch (shift.getIdTeam()) {

            case 10: {

//              Add this score to it
                addScoresStats(0, 1, 0, 1);

            }
            break;

            case 20: {

//              Add this score to it
                addScoresStats(1, 0, 1, 0);

            }

        }

//      Set firstCapture as tue
        firstCapture = true;

//      Reset the number of shifts that it hasn't had an capture
        shift.resetCountNoCapture();

//      Change any joker pieceType in the game
        changeJokerType();

    }//************************************************************

    public static void addScoresStats(int numberOfBlackPiecesCaptured, int numberOfWhitePiecesCaptured,
                                      int numberOfValidPlaysByWhiteTeam, int numberOfValidPlaysByBlackTeam) {

        Simulador.numberOfBlackPiecesCaptured += numberOfBlackPiecesCaptured;
        Simulador.numberOfWhitePiecesCaptured += numberOfWhitePiecesCaptured;
        Simulador.numberOfValidPlaysByWhiteTeam += numberOfValidPlaysByWhiteTeam;
        Simulador.numberOfValidPlaysByBlackTeam += numberOfValidPlaysByBlackTeam;

    }//*****

    public static void addScoresStatsInvalid() {

        switch (shift.getIdTeam()) {

            case 10: {

                numberOfInvalidPlaysByBlackTeam++;

            }break;

            case 20: {

                numberOfInvalidPlaysByWhiteTeam++;

            }

        }

    }//******************************************************************

    private void addScoreStatsToPrint(String s) {

        scores.add("JOGO DE CRAZY CHESS");
        scores.add("Resultado: " + s);
        scores.add("---");
        scores.add("Team das Pretas");
        scores.add(String.valueOf(numberOfWhitePiecesCaptured));
        scores.add(String.valueOf(numberOfValidPlaysByBlackTeam));
        scores.add(String.valueOf(numberOfInvalidPlaysByBlackTeam));
        scores.add("Team das Brancas");
        scores.add(String.valueOf(numberOfBlackPiecesCaptured));
        scores.add(String.valueOf(numberOfValidPlaysByWhiteTeam));
        scores.add(String.valueOf(numberOfInvalidPlaysByWhiteTeam));

    }//*****************************************************************

}
package pt.ulusofona.lp2.crazyChess;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//  corrigir metodo verificar jogada

public class Simulador {

    private int boardSize;
    private int numberOfBlackPiecesCaptured = 0;
    private int numberOfWhitePiecesCaptured = 0;
    private int numberOfValidPlaysByBlackTeam = 0;
    private int numberOfValidPlaysByWhiteTeam = 0;
    private int numberOfInvalidPlaysByBlackTeam = 0;
    private int numberOfInvalidPlaysByWhiteTeam = 0;
    List<CrazyPiece> crazyPiecesInGame = new ArrayList<>();
    private List<CrazyPiece> allCrazyPieces = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<String> suggestedPlay = new ArrayList<>();
    List<String> scores = new ArrayList<>();
    Shift shift = new Shift();
    public boolean firstCapture = false;
    private Position previousPosition;
    private CrazyPiece previousCrazyPiece;
    private CrazyPiece crazyPiece_Removed_From_The_Game;
    private List<CrazyPiece> crazyPiece_Removed_From_The_Game_Aux = new ArrayList<>();
    private int hasCaughtAPiece = 0;
    private int previousCountNoCapture = -1;
    private boolean hasMadeUndo = false;

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

    public int getPreviousCountNoCapture() {

        return previousCountNoCapture;

    }//**********************************************************************




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
                    possiblesPositions = thisPiece.possiblesPositions(boardSize, crazyPiecesInGame, shift);

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

//      If there was already a move
        if (shift.getCount() > 0) {

//          If there wasn't an undo made
            if (!hasMadeUndo) {

//              undo shiftChange
                shift.removeCount();

//              If there was a capture
                if (previousCountNoCapture != 0) {

                    if (previousCountNoCapture != -1) {

//                      Get the previous countNoCapture
                        shift.undoCountNoCapture(previousCountNoCapture);

                    }
//                  Remove 1 unit from the number pieces captured
                    hasCaughtAPiece--;

//                  If it was the first capture
                    if (hasCaughtAPiece == 0) {

//                      set the firstCapture as false
                        firstCapture = false;

                    }

//                  Search in ALL pieces
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      If thisPiece is the same piece that was moved
                        if (thisPiece.equals(previousCrazyPiece)) {

//                          Undo the movement that thisPiece as made
                            thisPiece.undoMov(previousPosition);

                        }

                    }

//                  Re-add the piece removed from game back to it
                    crazyPiecesInGame.add(crazyPiece_Removed_From_The_Game);

                }
//              If there was no capture
                else {

//                  undo remove one unit to countNoCapture
                    shift.removeCountNoCapture();

//                  Search in the pieces in game
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      If thisPiece is the same piece that was moved
                        if (thisPiece.equals(previousCrazyPiece)) {

//                          Undo the movement that thisPiece as made
                            thisPiece.undoMov(previousPosition);

                        }

                    }

                }

                for (CrazyPiece thisPiece : crazyPiecesInGame) {

                    if (thisPiece.getType() == 7) {

                        thisPiece.undoPieceType();

                    }

                }

//              set hasMadeUndo as true
                hasMadeUndo = true;

            }

        }

    }//**************************************************************************

//  Save Game
    public boolean gravarJogo(File ficheiroDestino) {

        boolean pieceFounded;

        String newLine = System.getProperty("line.separator");

        try {

            FileWriter writer = new FileWriter(ficheiroDestino);

            writer.write(String.valueOf(boardSize));
            writer.write(newLine);
            writer.write(String.valueOf(allCrazyPieces.size()));
            writer.write(newLine);

            for (CrazyPiece thisPiece : allCrazyPieces) {

                writer.write(thisPiece.saveFile_ToString());
                writer.write(newLine);

            }

            for (int line = 0 ; line < boardSize; line++) {

                for (int column = 0; column < boardSize; column++) {

                    pieceFounded = false;

                    Position thisPosition = new Position(column, line);

                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

                        if (thisPiece.getPosition().equals(thisPosition)) {

                            writer.write(String.valueOf(thisPiece.getId()));

                            pieceFounded = true;

                            break;

                        }

                    }

                    if (!pieceFounded) {

                        writer.write(String.valueOf(0));

                    }

                    if (column != boardSize - 1) {

                        writer.write(":");

                    }

                }

                writer.write(newLine);

            }

            writer.write(String.valueOf(shift.getIdTeam()));
            writer.write(setTeamStats(numberOfValidPlaysByBlackTeam, numberOfWhitePiecesCaptured, numberOfInvalidPlaysByBlackTeam));
            writer.write(setTeamStats(numberOfValidPlaysByWhiteTeam, numberOfBlackPiecesCaptured, numberOfInvalidPlaysByWhiteTeam));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }//*************************************************************

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

                String line = scan.nextLine();
                System.out.println(line);
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

                        boardSize = Integer.parseInt(line);

                    }catch (ArithmeticException notAnInteger) {

                        return false;

                    }

                } else if (nLines == 1) {

                    try {

                        nPieces = Integer.parseInt(line);

                    }catch (ArithmeticException notAnInteger) {

                        return false;

                    }

                } else if (nLines < nPiecesMaxIndex) {

                    piecesInfo = line.split(":");

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

                    boardInfo = line.split(":");

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

        previousCountNoCapture = 0;

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
                                String score = thiPiece.move(destiny, boardSize, crazyPiecesInGame, crazyPiece_Removed_From_The_Game_Aux, shift);

                                if (score.equals("")) {

                                    addScoresStatsInvalid();

                                    return false;

                                } else {

                                    String[] values = score.split(",");

                                    addScoresStats(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]));

                                    if (Integer.parseInt(values[0]) != 0 || Integer.parseInt(values[1]) != 0) {

                                        firstCapture = true;

                                        hasCaughtAPiece++;

                                        if (shift.getCountNoCapture() == 0) {

                                            previousCountNoCapture = -1;

                                        }

                                        else {

                                            previousCountNoCapture = shift.getCountNoCapture();

                                        }

                                        shift.resetCountNoCapture();

                                        crazyPiece_Removed_From_The_Game = crazyPiece_Removed_From_The_Game_Aux.get(0);

                                        crazyPiece_Removed_From_The_Game_Aux.remove(crazyPiece_Removed_From_The_Game);

                                    } else {

                                        shift.addCountNoCapture();

                                    }

                                    shift.addCount();

                                    changeJokerType();

                                    previousCrazyPiece = thiPiece;

                                    previousPosition = origin;

                                    hasMadeUndo = false;

                                    return true;

                                }

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

//      set nWhiteKing and nBlackKing as 0
        int nWhiteKing = 0;
        int nBlackKing = 0;

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

                            nBlackKing++;

                        } else if (piece.getIDTeam() == 20) {

                            nWhiteKing++;

                        }

                    }

                }

            }

            if (crazyPiecesInGame.size() <= 2) {

                if (nBlackKing == 0) {

                    System.out.println("VENCERAM AS BRANCAS");

                    addScoreStatsToPrint("VENCERAM AS BRANCAS");

                    return true;

                } else if (nWhiteKing == 0) {

                    System.out.println("VENCERAM AS PRETAS");

                    addScoreStatsToPrint("VENCERAM AS PRETAS");

                    return true;

                } else if (nBlackKing == 1 && nWhiteKing == 1) {

                    addScoreStatsToPrint("EMPATE");

                    return true;

                }

            }

            return false;

        }

    }//******************************************************************************

    private void changeJokerType() {

        for (CrazyPiece piece : crazyPiecesInGame) {

            if (piece.getType() == 7) {

                piece.changePieceType();

                break;

            }

        }

    }//******************************************************************************

    private void addScoresStats(int numberOfBlackPiecesCaptured, int numberOfWhitePiecesCaptured,
                                      int numberOfValidPlaysByWhiteTeam, int numberOfValidPlaysByBlackTeam) {

        this.numberOfBlackPiecesCaptured += numberOfBlackPiecesCaptured;
        this.numberOfWhitePiecesCaptured += numberOfWhitePiecesCaptured;
        this.numberOfValidPlaysByWhiteTeam += numberOfValidPlaysByWhiteTeam;
        this.numberOfValidPlaysByBlackTeam += numberOfValidPlaysByBlackTeam;

    }//*****

    private void addScoresStatsInvalid() {

        switch (shift.getIdTeam()) {

            case 10: {

                this.numberOfInvalidPlaysByBlackTeam++;

            }break;

            case 20: {

                this.numberOfInvalidPlaysByWhiteTeam++;

            }

        }

    }//************************************************************************

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

    private String setTeamStats(int numberOfValidPlays, int numberOfPiecesCaptured, int numberOfInvalidPlays) {

        return ":" + numberOfValidPlays +
                ":" + numberOfPiecesCaptured +
                ":" + numberOfInvalidPlays;

    }//***

}
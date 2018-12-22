package pt.ulusofona.lp2.crazyChess;

import jdk.nashorn.internal.ir.annotations.Ignore;
import pt.ulusofona.lp2.crazyChess.CrazyPiece.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//  corrigir metodo verificar jogada

public class Simulador {

    private int boardSize;
    private int numberOfBlackPiecesCaptured = 0,
               numberOfWhitePiecesCaptured = 0,
               numberOfValidPlaysByBlackTeam = 0,
               numberOfValidPlaysByWhiteTeam = 0,
               numberOfInvalidPlaysByBlackTeam = 0,
               numberOfInvalidPlaysByWhiteTeam = 0;
    List<CrazyPiece> crazyPiecesInGame = new ArrayList<>(), allCrazyPieces = new ArrayList<>();
    private List<String> authors = new ArrayList<>(), suggestedPlay = new ArrayList<>();
    List<String> scores = new ArrayList<>();
    List<Team> team = new ArrayList<>();
    Shift shift = new Shift();
    boolean firstCapture = false;

//    Constructor
    public Simulador() {}

    public Simulador(int boardSize) {

        this.boardSize = boardSize;

    }


//    gets
    public int getTamanhoTabuleiro() {

        return boardSize;

    }

    public List<CrazyPiece> getPecasMalucas() {

        return crazyPiecesInGame;

    }

    public List<String> getAutores() {

        if (authors.size() == 0) {

            this.authors.add("Bruno Miguel Dias Leal, nº 21705197");
            this.authors.add("João Domingos, nº 21703282");

        }

        return authors;

    }

    public List<String> getResultados() {

        return scores;

    }

    public int getIDPeca(int x, int y) {

        Position position = new Position(x, y);

        for (CrazyPiece piece: crazyPiecesInGame){

            if (piece.getPosition().equals(position)) {

                return piece.getId();

            }

        }

        //System.out.println("Piece not founded!");

        return 0;

    }

    public int getIDEquipaAJogar() {

        return shift.getIdTeam();

    }

    public int getNumberOfBlackPiecesCaptured() {

        return numberOfBlackPiecesCaptured;

    }

    public int getNumberOfWhitePiecesCaptured() {

        return numberOfWhitePiecesCaptured;

    }

    public int getNumberOfValidPlaysByBlackTeam() {

        return numberOfValidPlaysByBlackTeam;

    }

    public int getNumberOfValidPlaysByWhiteTeam() {

        return numberOfValidPlaysByWhiteTeam;

    }

    public int getNumberOfInvalidPlaysByBlackTeam() {

        return numberOfInvalidPlaysByBlackTeam;

    }

    public int getNumberOfInvalidPlaysByWhiteTeam() {

        return numberOfInvalidPlaysByWhiteTeam;

    }

    public Shift getShift() {

        return shift;

    }

    public boolean getFirstCapture() {

        return this.firstCapture;

    }

    private int setAuxiliary(int auxiliary, int defRelationToCenterBoard) {

        int verticalAuxiliary;

        if (defRelationToCenterBoard < 0) {

            verticalAuxiliary = auxiliary - defRelationToCenterBoard;

        } else {

            verticalAuxiliary = auxiliary + defRelationToCenterBoard;

        }

        return verticalAuxiliary;

    }//***************************************

    public List<String> obterSugestoesJogada(int xO, int yO) {

        int leftMaxMov;
        int rightMaxMov;
        int upMaxMov;
        int downMaxMov;
        int leftMinMov;
        int rightMinMov;
        int upMinMov;
        int downMinMov;
        int horizontalRelationToCenterBoard = (xO - (boardSize - 1)/2);
        int verticalRelationToCenterBoard = (yO - (boardSize - 1)/2);
        int verticalAuxiliary;
        int horizontalAuxiliary;

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

                    leftMaxMov = xO - thisPiece.getMaxMovHorizontal();
//                    switch (thisPiece.getType()) {
//
//                        case 0: {
//
//                            leftMaxMov = xO - 1;
//                            rightMaxMov = xO + 1;
//                            leftMinMov = rightMinMov = xO;
//                            upMaxMov = yO - 1;
//                            downMaxMov = yO + 1;
//                            upMinMov = downMinMov = yO;
//
//
//                        }
//                        break;
//
//                        case 1: {
//
//                            leftMaxMov = xO - 5;
//                            rightMaxMov = xO + 5;
//                            leftMinMov = rightMinMov = xO;
//                            upMaxMov = yO - 5;
//                            downMaxMov = yO + 5;
//                            upMinMov = downMinMov = yO;
//
//                        }
//
//                        case 2: {
//
//                            leftMaxMov = xO - 2;
//                            rightMaxMov = xO + 2;
//                            leftMinMov = xO;
//                            rightMinMov =
//                            upMaxMov = yO - 5;
//                            downMaxMov = yO + 5;
//                            upMinMov = downMinMov = yO;
//
//                        }
//
//                    }
                    leftMinMov = xO - thisPiece.getMinMovHorizontal();
                    rightMaxMov = xO + thisPiece.getMaxMovHorizontal();
                    rightMinMov = xO + thisPiece.getMinMovHorizontal();
                    upMaxMov = yO - thisPiece.getMaxMovVertical();
                    upMinMov = yO - thisPiece.getMinMovVertical();
                    downMaxMov = yO + thisPiece.getMaxMovVertical();
                    downMinMov = yO + thisPiece.getMinMovVertical();



//                    horizontal, vertical e diagonal

                    for (int horizontal = leftMaxMov; horizontal <= rightMaxMov; horizontal++) {

                        if (horizontal >= 0 && horizontal < boardSize) {

                            for (int vertical = upMaxMov; vertical <= downMaxMov; vertical++) {

                                if (vertical >= 0 && vertical < boardSize) {

                                    verticalAuxiliary = setAuxiliary(vertical, verticalRelationToCenterBoard);

                                    horizontalAuxiliary = setAuxiliary(horizontal, horizontalRelationToCenterBoard);

                                    if (thisPiece.getMovVertical()) {

                                        if (vertical != yO && horizontal == xO) {

                                            suggestedPlay.add("\"" + xO + "," + vertical + "\"");

                                        }

                                    }

                                    if (thisPiece.getMoveHorizontal()) {

                                        if (horizontal != xO && vertical == yO) {

                                            suggestedPlay.add("\"" + horizontal + "," + yO + "\"");

                                        }

                                    }

                                    if (thisPiece.getMoveDiagonal()) {

                                        if (horizontal != xO && vertical != yO) {

                                            if (horizontalRelationToCenterBoard == 0 && verticalRelationToCenterBoard == 0) {

                                                if (vertical - horizontal == 0 || vertical + horizontal == boardSize - 1) {

                                                    suggestedPlay.add("\"" + horizontal + "," + vertical + "\"");

                                                }

                                            } else {

                                                if (verticalAuxiliary - horizontalAuxiliary == 0 ||
                                                        verticalAuxiliary + horizontalAuxiliary == boardSize - 1) {

                                                    suggestedPlay.add("\"" + horizontal + "," + vertical + "\"");

                                                }

                                            }

                                        }

                                    }

                                }

                            }

                        }

                    }

                    for (int horizontal = leftMinMov + 1; horizontal < rightMinMov; horizontal++) {

                        if (horizontal >= 0 && horizontal < boardSize) {

                            for (int vertical = upMinMov + 1; vertical < downMinMov; vertical++) {

                                verticalAuxiliary = Math.abs(vertical - verticalRelationToCenterBoard);
                                horizontalAuxiliary = Math.abs(horizontal - horizontalRelationToCenterBoard);

                                if (vertical >= 0 && vertical < boardSize) {

                                    verticalAuxiliary = setAuxiliary(vertical, verticalRelationToCenterBoard);

                                    horizontalAuxiliary = setAuxiliary(horizontal, horizontalRelationToCenterBoard);

                                    if (thisPiece.getMovVertical()) {

                                        if (vertical != yO && horizontal == xO) {

                                            suggestedPlay.remove("\"" + xO + "," + vertical + "\"");

                                        }

                                    }

                                    if (thisPiece.getMoveHorizontal()) {

                                        if (horizontal != xO && vertical == yO) {

                                            suggestedPlay.remove("\"" + horizontal + "," + yO + "\"");

                                        }

                                    }

                                    if (thisPiece.getMoveDiagonal()) {

                                        if (horizontal != xO && vertical != yO) {

                                            if (horizontalRelationToCenterBoard == 0 && verticalRelationToCenterBoard == 0) {

                                                if (vertical - horizontal == 0 || vertical + horizontal == boardSize - 1) {

                                                    suggestedPlay.remove("\"" + horizontal + "," + vertical + "\"");

                                                }

                                            } else {

                                                if (verticalAuxiliary - horizontalAuxiliary == 0 ||
                                                        verticalAuxiliary + horizontalAuxiliary == boardSize - 1) {

                                                    suggestedPlay.remove("\"" + horizontal + "," + vertical + "\"");

                                                }

                                            }

                                        }

                                    }

                                }

                            }

                        }

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

                            piece = new ReiPreto(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new ReiBranco(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 1) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new RainhaPreta(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new RainhaBranca(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 2) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new PoneiMagicoPreto(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new PoneiMagicoBranco(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 3) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new PadreDaVilaPreto(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new PadreDaVilaBranco(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 4) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new TorreHPreta(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new TorreHBranca(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 5) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new TorreVPreta(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new TorreVBranca(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 6) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new LebrePreta(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new LebreBranca(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

                        } else {

                            throw new NumberFormatException();

                        }

                    } else if (Integer.parseInt(piecesInfo[1]) == 7) {

                        if (Integer.parseInt(piecesInfo[2]) == 10) {

                            piece = new JokerPreto(Integer.parseInt(piecesInfo[0]), 10, piecesInfo[3]);

                        } else if (Integer.parseInt(piecesInfo[2]) == 20) {

                            piece = new JokerBranco(Integer.parseInt(piecesInfo[0]), 20, piecesInfo[3]);

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

            return nLines == boardSizeMaxIndex;

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

    }

//  Processes the play
    public boolean processaJogada(int xO, int yO, int xD, int yD) {

//      Check if the move is between the board rang
        if (xD >= 0 && xD <= (boardSize - 1) &&
            yD >= 0 && yD <= (boardSize - 1)) {

//          Creates the positionOrigin as the origin position
            Position positionOrigin = new Position(xO, yO);

//          For every piece in the list of pieces in the game
            for (CrazyPiece piece : crazyPiecesInGame) {

//              If the position of this piece is in the positionOrigin
                if (piece.getPosition().equals(positionOrigin)) {

//                  Check if it belongs to the playing team
                    if (piece.getIDTeam() == shift.getIdTeam()) {

//                      Set the difference as the destiny coordinate minus the origin coordinate
                        int xDifference = xD - xO;
                        int yDifference = yD - yO;

//                      Set the module of the difference as them self
                        int xDifferenceABS = xDifference;
                        int yDifferenceABS = yDifference;

//                      If the difference of the X axis is lower of 0
                        if (xDifference < 0) {

//                          Set it's module as symmetric
                            xDifferenceABS *= (-1);

                        }
//                      If the difference of the Y axis is lower of 0
                        if (yDifference < 0) {

//                          Set it's module as symmetric
                            yDifferenceABS *= (-1);

                        }

//                      Check if the it can move that amount of houses
                        if (piece.getMinMovHorizontal() <= xDifferenceABS &&
                                piece.getMaxMovHorizontal() >= xDifferenceABS &&
                                piece.getMinMovVertical() <= yDifferenceABS &&
                                piece.getMaxMovVertical() >= yDifferenceABS) {

//                          Create a destiny Position
                            Position destiny = new Position(xD, yD);

                            if (checkEmptyPosition(piece, destiny)) {

                                if (checkMovement(piece, xDifference, yDifference, destiny)) {

                                    setScores(piece, destiny);

//                                  Change shift and team
                                    shift.addCount();

//                                  Returns true
                                    return true;

                                }

                            }

                        }

                    }

//                  If the piece doesn't belong to the playing team leaves the cycle
                    break;

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

    }

//  private functions
    private boolean checkMovement(CrazyPiece piece, int xDifference, int yDifference, Position destiny) {

//      If the piece moves in the diagonal
        if (xDifference != 0 && yDifference != 0) {

//          Check if it can move like that
            if (piece.getMoveDiagonal()) {

                if (moveDiagonally(piece, xDifference, yDifference, destiny)) {

                    return true;

                }

            }

//      If the piece moves in the vertical
        } else if (xDifference != 0) {

//          Check if it can move like that
            if (piece.getMoveHorizontal()) {

//              Check if it can go tho that position
                if (moveHorizontally(piece, xDifference)) {

                    return true;

                }

            }

//      If the piece moves in the horizontal
        } else if (yDifference != 0) {

//          Check if it can move like that
            if (piece.getMovVertical()) {

//              Check if it can go tho that position
                if (moveVertically(piece, xDifference, yDifference)) {

                    return true;

                }

            }

        }

//      Print in the console the directions that the piece can be moved
        System.out.println("This piece can be moved in this ways: " +
                "\nHorizontal: " + piece.getMoveHorizontal() +
                "\nVertical: " + piece.getMovVertical() +
                "\nDiagonal: " + piece.getMoveDiagonal());

//      Returns false
        return false;

    }

    private boolean moveHorizontally(CrazyPiece piece, int xDifference) {

        if (xDifference > 0) {

            switch (piece.getType()) {

                case 0 : {

//                  Move up and right
                    moveRight(piece, xDifference);

//                  Return true
                    return true;

                }

                case 1 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                      Move up and right
                        moveRight(piece, xDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 5 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                      Move up and right
                        moveRight(piece, xDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 7 : {

//                      Check if there is a piece in the way
                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                          Move up and right
                        moveRight(piece, xDifference);

//                      Return true
                        return true;

                    }

                }

            }

        } else if (xDifference < 0) {

            switch (piece.getType()) {

                case 0 : {

//                  Move up and right
                    moveLeft(piece, xDifference);

//                  Return true
                    return true;

                }

                case 1 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                      Move up and right
                        moveLeft(piece, xDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 5 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                      Move up and right
                        moveLeft(piece, xDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 7 : {

//                      Check if there is a piece in the way
                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), false)) {

//                      Move up and right
                        moveLeft(piece, xDifference);

//                      Return true
                        return true;

                    }

                }

            }

        }

//      Return false
        return false;

    }

    private boolean moveVertically(CrazyPiece piece, int xDifference, int yDifference) {

        if (yDifference > 0) {

            switch (piece.getType()) {

                case 0 : {

//                      Move up and right
                    moveUp(piece, yDifference);

//                  Return true
                    return true;

                }

                case 1 : {

                    if (checkClearPath_Horizontal_Or_Vertical(yDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveUp(piece, yDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 5 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveUp(piece, yDifference);

//                      Return true
                        return true;

                    }

                }
                break;

                case 7 : {

//                      Check if there is a piece in the way
                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveUp(piece, yDifference);

//                      Return true
                        return true;

                    }

                }

            }

        } else if (yDifference < 0) {

            switch (piece.getType()) {

                case 0 : {

//                      Move up and right
                    moveDown(piece, yDifference);

//                  Return true
                    return true;

                }

                case 1 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveDown(piece, yDifference);

//                  Return true
                        return true;

                    }

                }
                break;

                case 5 : {

                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveDown(piece, yDifference);

//                  Return true
                        return true;

                    }

                }
                break;

                case 7 : {

//                      Check if there is a piece in the way
                    if (checkClearPath_Horizontal_Or_Vertical(xDifference, piece.getPosition().getxAtual(),
                            piece.getPosition().getyAtual(), true)) {

//                      Move up and right
                        moveDown(piece, yDifference);

//                  Return true
                        return true;

                    }

                }

            }

        }

//      Return true
        return false;

    }

//  comentar
    private boolean moveDiagonally(CrazyPiece piece, int xDifference, int yDifference, Position destiny) {

//      If xDifference is bigger than 0
        if (xDifference > 0) {

            if (yDifference > 0) {

                switch (piece.getType()) {

                    case 0 : {

//                      Move up and right
                        moveUpRight(piece, xDifference, yDifference);

//                      Return true
                        return true;

                    }

                    case 1 : {

//                      Check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), true)) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 2 : {

//                      If there are no kings in the way
                        if (checkClearPath_magicPony(xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 3 : {

//                      check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), true) && priestMovement(destiny)) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 6 : {


//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {

                            case 1 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), true)) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 2 : {


//                              If there are no kings in the way
                                if (checkClearPath_magicPony(xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 3 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), true) && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            default : {


//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }

                        }

                    }

                }

            } else if (yDifference < 0) {

                switch (piece.getType()) {


                    case 0 : {


//                      Move up and right
                        moveUpLeft(piece, xDifference, yDifference);

//                      Return true
                        return true;

                    }

                    case 1 : {


//                      Check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), true)) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 2 : {


//                      If there are no kings in the way
                        if (checkClearPath_magicPony(xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 3 : {


//                      check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), true) && priestMovement(destiny)) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 6 : {


//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {


                            case 1 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), true)) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 2 : {


//                              If there are no kings in the way
                                if (checkClearPath_magicPony(xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 3 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), true) && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            default : {


//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }

                        }

                    }

                }

            }

        }

//      If xDifference is lower than 0
        else if (xDifference < 0) {

            if (yDifference > 0) {

                switch (piece.getType()) {

                    case 0 : {

//                      Move up and right
                        moveDownRight(piece, xDifference, yDifference);

//                      Return true
                        return true;

                    }

                    case 1 : {


//                      Check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), false)) {

//                          Move up and right
                            moveDownRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 2 : {


//                      If there are no kings in the way
                        if (checkClearPath_magicPony(xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveDownRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 3 : {


//                      check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), false) && priestMovement(destiny)) {

//                          Move up and right
                            moveDownRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 6 : {


//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveDownRight(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 7 : {


                        switch (piece.getPieceId()) {

                            case 1 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), false)) {

//                                  Move up and right
                                    moveDownRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 2 : {


//                              If there are no kings in the way
                                if (checkClearPath_magicPony(xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveDownRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 3 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), false) && priestMovement(destiny)) {

//                                  Move up and right
                                    moveDownRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            default : {


//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveDownRight(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }

                        }

                    }

                }

            } else if (yDifference < 0) {

                switch (piece.getType()) {


                    case 0 : {


//                      Move up and right
                        moveDownLeft(piece, xDifference, yDifference);

//                      Return true
                        return true;

                    }

                    case 1 : {


//                      Check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), false)) {

//                          Move up and right
                            moveDownLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 2 : {


//                      If there are no kings in the way
                        if (checkClearPath_magicPony(xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveDownLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 3 : {


//                      check if there is a piece in the way
                        if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), false) && priestMovement(destiny)) {

//                          Move up and right
                            moveDownLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 6 : {


//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveDownLeft(piece, xDifference, yDifference);

//                          Return true
                            return true;

                        }

                    }
                    break;

                    case 7 : {


                        switch (piece.getPieceId()) {


                            case 1 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), false)) {

//                                  Move up and right
                                    moveDownLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 2 : {


//                              If there are no kings in the way
                                if (checkClearPath_magicPony(xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveDownLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            case 3 : {


//                              check if there is a piece in the way
                                if (checkClearPath_Diagonal(xDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), false) && priestMovement(destiny)) {

//                                  Move up and right
                                    moveDownLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }
                            break;

                            default : {


//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveDownLeft(piece, xDifference, yDifference);

//                                  Return true
                                    return true;

                                }

                            }

                        }

                    }

                }

            }

        }

//      Return true
        return false;

    }

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

//      Remove this piece from crazyPiecesInGame
        crazyPiecesInGame.remove(piece);

//      Set firstCapture as tue
        firstCapture = true;

//      Reset the number of shifts that it hasn't had an capture
        shift.resetCountNoCapture();

//      Change any joker pieceType in the game
        changeJokerType();

    }//************************************************************

    private void setScores(CrazyPiece piece, Position destiny) {

        boolean haveScore = false;

        for (CrazyPiece thisPiece : crazyPiecesInGame) {

            if (thisPiece.getPosition().equals(destiny)) {

//              Call setScores_Capture
                setScores_Capture(piece);

                haveScore = true;

            }

        }

        if (!haveScore) {

            switch (shift.getIdTeam()) {

                case 10: {

                    addScoresStats(0, 0, 0, 1);

                }
                break;

                case 20: {

                    addScoresStats(0, 0, 1, 0);

                }

            }

            shift.addCountNoCapture();

        }

    }//**************************************************

//                       Safe
    private boolean checkEmptyPosition(CrazyPiece piece, Position destiny) {

//      If the type of the piece is queen
        if (piece.getType() == 1) {

//          Search in every piece in the game
            for (CrazyPiece thisPiece : crazyPiecesInGame) {

//              If that same piece is in the newPosition
                if (thisPiece.getPosition().equals(destiny)) {

//                  Check if the piece is form the enemy team
                    if (thisPiece.getIDTeam() != shift.getIdTeam()) {

//                      If this piece is not a queen
                        if (thisPiece.getType() != 1) {

//                          Returns true
                            return true;

                        }

//                      Print in the console that was an enemy queen on that position
                        System.out.println("There's an enemy queen on that position!");

//                      Returns false
                        return false;

                    } else {

//                      Print in the console that was an enemy queen on that position
                        System.out.println("There's already a piece of the same team on that position!");

//                      Returns false
                        return false;

                    }

                }

            }
        } else {

//          Search in every piece in the game
            for (CrazyPiece thisPiece : crazyPiecesInGame) {

//              If that same piece is in the newPosition
                if (thisPiece.getPosition().equals(destiny)) {

//                  Check if the piece is form the enemy team
                    if (thisPiece.getIDTeam() != shift.getIdTeam()) {

//                      Return true
                        return true;

                    }

//                  Print in the console that was an enemy queen on that position
                    System.out.println("There's already a piece of the same team on that position!");

//                  Returns false
                    return false;

                }

            }
        }

//      If there's not a piece in the destiny position return true
        return true;

    }//**************************************

    private boolean priestMovement(Position destiny) {

        int minHorizontal = destiny.getxAtual() - 2;
        int maxHorizontal = destiny.getxAtual() + 2;
        int minVertical = destiny.getyAtual() - 2;
        int maxVertical = destiny.getyAtual() + 2;

        for (int x = minHorizontal; x < maxHorizontal; x++) {

            for (int y = minVertical; y < maxVertical; y++) {

                for (CrazyPiece piece : crazyPiecesInGame) {

                    if (piece.getType() == 1) {

                        return false;

                    }

                }

            }

        }

        return true;

    }

    private boolean checkClearPath_Horizontal_Or_Vertical(int difference, int xActual, int yActual, boolean vertical) {

        int finalPosition;

//      Set finalPosition value
        if (vertical) {

//          Set the finalPosition as the yActual + the difference it wants to move
            finalPosition = yActual + difference;

        } else {

//          Set the finalPosition as the yActual + the difference it wants to move
            finalPosition = xActual + difference;

        }

//      if the difference is negative
        if (difference < 0) {

//          If it wants to move vertically
            if (vertical) {

//              Search from the immediately left position the the actual position until the finalPosition
                for (int position = yActual - 1; position > finalPosition; position--) {

//                  If there's a piece in the way
                    if (searchForAPiece(xActual, position)) {

//                      Return false
                        return false;

                    }

                }

            }
//          If it wants to move horizontally
            else {

//              Search from the immediately left position the the actual position until the finalPosition
                for (int position = xActual - 1; position > finalPosition; position--) {

//                  If there's a piece in the way
                    if (searchForAPiece(position, yActual)) {

//                      Return false
                        return false;

                    }

                }

            }

        }
//      If the difference is positive
        else if (difference > 0) {

            if (vertical) {

//              Search from the immediately left position the the actual position until the finalPosition
                for (int position = yActual + 1; position > finalPosition; position++) {

//                  If there's a piece in the way
                    if (searchForAPiece(xActual, position)) {

//                      Return false
                        return false;

                    }

                }

            }
//          If it wants to move horizontally
            else {

//              Search from the immediately left position the the actual position until the finalPosition
                for (int position = xActual + 1; position > finalPosition; position++) {

//                  If there's a piece in the way
                    if (searchForAPiece(position, yActual)) {

//                      Return false
                        return false;

                    }

                }


            }

        }

//      If the difference is null
        else {

//          Return false
            return false;

        }

//      If there's no piece in the way return true
        return true;

    }

    private boolean checkClearPath_Diagonal(int xDifference, int xActual, int yActual, boolean up) {

        int finalPosition;

        if (up) {

            finalPosition = xActual + xDifference;

            if (xDifference < 0) {

                for (int y = yActual - 1, x = xActual - 1; x < finalPosition; y--, x--) {

                    if (searchForAPiece(x, y)) {

                        return false;

                    }

                }

            }
            else if (xDifference > 0){

                for (int y = yActual - 1, x = xActual + 1; x < finalPosition; y--, x++) {

                    if (searchForAPiece(x, y)) {

                        return false;

                    }

                }

            }
            else {

                return false;

            }


        } else {

            finalPosition = xActual + xDifference;

            if (xDifference < 0) {

                for (int y = yActual + 1, x = xActual - 1; x < finalPosition; y++, x--) {

                    if (searchForAPiece(x, y)) {

                        return false;

                    }

                }

            }
            else if (xDifference > 0){

                for (int y = yActual + 1, x = xActual + 1; x < xDifference; y++, x++) {

                    if (searchForAPiece(x, y)) {

                        return false;

                    }

                }

            }
            else {

                return false;

            }

        }

        return true;

    }

    private boolean checkClearPath_magicPony(int xDifference, int yDifference, int xActual, int yActual) {

        int xFinal = xActual + xDifference;
        int yFinal = yActual + yDifference;

//      Create foundAKing flag
        boolean foundAKing = false;

//      First it will check if is any king in the X axis
        for (int movHorizontal = xActual + 1; movHorizontal <= xFinal; movHorizontal++) {

//          Set foundAKing as the searchForAKing return
            foundAKing = searchForAKing(movHorizontal, yActual);

//          If there was a king in the way
            if (foundAKing) {

//              Leave the cycle
                break;

            }

        }

//      If there wasn't any king in the way
        if (!foundAKing) {

//          Set foundAKing as the searchForAKing return
            foundAKing = searchForAKing(xFinal, yActual + 1);

        }
        else {

//          Reset foundAKing
            foundAKing = false;

//          First it will check if is any king in the X axis
            for (int movVertical = yActual + 1; movVertical <= xFinal; movVertical++) {

//              Set foundAKing as the searchForAKing return
                foundAKing = searchForAKing(xActual, movVertical);

//              If there was a king in the way
                if (foundAKing) {

//                  Leave the cycle
                    break;

                }

            }

//          If there wasn't any king in the way
            if (!foundAKing) {

                return !searchForAKing(xActual + 1, yFinal);

            }

        }

        return true;

    }

    private boolean searchForAKing(int x, int y) {

//      set thisPosition for x and y
        Position thisPosition = new Position(x, y);

//      Search for a piece in thisPosition
        for (CrazyPiece thisPiece : crazyPiecesInGame) {

//          If thisPiece is in thisPosition
            if (thisPiece.getPosition().equals(thisPosition)) {

//              Return true  if thisPiece is a king else return false
                return thisPiece.getType() == 0;

            }

        }

//      Return false
        return false;

    }

    private boolean searchForAPiece(int x, int y) {

//      Set thisPosition for moveLeftRight and movUpDown
        Position thisPosition = new Position(x, y);

//      Search for a piece in thisPosition
        for (CrazyPiece thisPiece : crazyPiecesInGame) {

//          If thisPiece is in thisPosition
            if (thisPiece.getPosition().equals(thisPosition)) {

//              Return true if thisPiece is a king else return false
                return true;

            }

        }

        return false;

    }

    private void moveLeft(CrazyPiece piece, int xDifference) {

        piece.moveLeft((xDifference * (-1)));
        shift.addCount();

    }

    private void moveRight(CrazyPiece piece, int xDifference) {

        piece.moveRight(xDifference);
        shift.addCount();

    }

    private void moveUp(CrazyPiece piece, int yDifference) {

        piece.moveUp(yDifference);
        shift.addCount();

    }

    private void moveDown(CrazyPiece piece, int yDifference) {

        piece.moveDown((yDifference * (-1)));
        shift.addCount();

    }

    private void moveDownLeft(CrazyPiece piece, int xDifference, int yDifference) {

        piece.moveDown((yDifference * (-1)));
        piece.moveLeft((xDifference * (-1)));
        shift.addCount();

    }

    private void moveUpLeft(CrazyPiece piece, int xDifference, int yDifference) {

        piece.moveUp(yDifference);
        piece.moveLeft((xDifference * (-1)));
        shift.addCount();

    }

    private void moveUpRight(CrazyPiece piece, int xDifference, int yDifference) {

        piece.moveUp(yDifference);
        piece.moveRight(xDifference);
        shift.addCount();

    }

    private void moveDownRight(CrazyPiece piece, int xDifference, int yDifference) {

        piece.moveDown((yDifference * (-1)));
        piece.moveRight(xDifference);
        shift.addCount();

    }

    private void addScoresStats(int numberOfBlackPiecesCaptured, int numberOfWhitePiecesCaptured, int numberOfValidPlaysByWhiteTeam,
                                 int numberOfValidPlaysByBlackTeam) {

        this.numberOfBlackPiecesCaptured += numberOfBlackPiecesCaptured;
        this.numberOfWhitePiecesCaptured += numberOfWhitePiecesCaptured;
        this.numberOfValidPlaysByWhiteTeam += numberOfValidPlaysByWhiteTeam;
        this.numberOfValidPlaysByBlackTeam += numberOfValidPlaysByBlackTeam;

    }

    private void addScoresStatsInvalid() {

        switch (shift.getIdTeam()) {

            case 10: {

                numberOfInvalidPlaysByBlackTeam++;

            }break;

            case 20: {

                numberOfInvalidPlaysByWhiteTeam++;

            }

        }

    }

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

    }

}
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

    public List<String> obterSugestoesJogada(int xO, int yO) {

        int leftMaxMov;
        int rightMaxMov;
        int upMaxMov;
        int downMaxMov;
        int leftMinMov;
        int rightMinMov;
        int upMinMov;
        int downMinMov;

//      clear the list
        suggestedPlay.removeAll(suggestedPlay);

//      Create a new position with the destination
        Position origin = new Position(xO, yO);

//      For each piece on crazyPiecesInGame variable
        for (CrazyPiece piece : crazyPiecesInGame) {

            leftMaxMov = xO - piece.getMaxMovHorizontal();
            leftMinMov = leftMaxMov - piece.getMinMovHorizontal();
            rightMaxMov = xO + piece.getMaxMovHorizontal();
            rightMinMov = rightMaxMov - piece.getMinMovHorizontal();
            upMaxMov = yO - piece.getMaxMovVertical();
            upMinMov = upMaxMov - piece.getMaxMovVertical();
            downMaxMov = yO + piece.getMaxMovVertical();
            downMinMov = downMaxMov - piece.getMaxMovVertical();

//          Check if the this piece is in the position created
            if (piece.getPosition().equals(origin)) {

//              Check if this piece belongs to the team that is playing
                if (piece.getIDTeam() == shift.getIdTeam()) {

                    if ((piece.getMoveCima())

//                  For each position that this piece can move horizontally
                    for (int movHorizontal = leftMaxMov; movHorizontal <= rightMaxMov; movHorizontal++) {

                        if (movHorizontal >= 0 && movHorizontal < boardSize -1) {

                            if (movHorizontal < leftMinMov && movHorizontal > rightMinMov) {

//                              For each position that this piece can move Vertically
                                for (int movVertical = upMaxMov; movVertical <= downMaxMov; movVertical++) {

                                    if (movVertical > upMinMov && movVertical < downMinMov) {

                                        continue;

                                    }

                                    if (movHorizontal < boardSize - 1 && movVertical < boardSize - 1) {

                                        if (movHorizontal >)

                                    }

                                    if (processaJogada(xO, yO, movHorizontal, movVertical)) {

                                        suggestedPlay.add("\"" + movHorizontal + ", " + movVertical + "\"");

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

    }

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


//  sets
//    public boolean setPeca(CrazyPiece piece) {
//
//        try {
//
//            this.piecesMalucas.add(piece);
//
//
//            return true;
//
//        } catch (Exception impossibleToAddANexPiece) {
//
//            System.out.println("Impossible to add a new piece");
//
//            return false;
//
//        }
//
//    }
//
//    public boolean removePeca(CrazyPiece piece) {
//
//        try {
//
//            this.piecesMalucas.remove(piece);
//
//            return true;
//
//        } catch (Exception impossibleToAddANexPiece) {
//
//            System.out.println("Impossible to add a new piece");
//
//            return false;
//
//        }
//
//    }

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

//  comentar
    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        if (xD >= 0 && xD <= (boardSize - 1) &&
            yD >= 0 && yD <= (boardSize - 1)) {

            Position positionOrigin = new Position(xO, yO);

            for (CrazyPiece piece : crazyPiecesInGame) {

                if (piece.getPosition().equals(positionOrigin)) {

                    if (piece.getIDTeam() == shift.getIdTeam()) {

                        int xDifference = xD - xO;
                        int yDifference = yD - yO;
                        int xDifferenceABS = xDifference;
                        int yDifferenceABS = yDifference;

                        if (xDifference < 0) {

                            xDifferenceABS = ((xDifference * (-1)));

                        }
                        if (yDifference < 0) {

                            yDifferenceABS = ((yDifference * (-1)));

                        }

                        if (piece.getMinMovHorizontal() <= xDifferenceABS &&
                                piece.getMaxMovHorizontal() >= xDifferenceABS &&
                                piece.getMinMovVertical() <= yDifferenceABS &&
                                piece.getMaxMovVertical() >= yDifferenceABS) {

                            Position destiny = new Position(xD, yD);

                            return checkMovement(piece, xDifference, yDifference, destiny);

                        }

                    }

                    break;

                }

            }

        }

        addScoresStatsInvalid();

        return false;

    }

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

        if (checkEmptyPosition(piece, destiny)) {

            if (piece.getMoveDiagonal()) {

                return moveDiagonally(piece, xDifference, yDifference, destiny);

            } else if (piece.getMoveHorizontal()) {

                if (piece.getMovVertical()) {

                    if (xDifference > 0) {

                        return verificaMovimentoVertical(piece, xDifference, yDifference, 'R', destiny);

                    } else if (xDifference < 0) {

                        return verificaMovimentoVertical(piece, xDifference, yDifference, 'L', destiny);

                    }

                    return verificaMovimentoVertical(piece, xDifference, yDifference, 'N', destiny);

                }

            } else if (piece.getMovVertical()) {

                if (xDifference > 0) {

                    return verificaMovimentoVertical(piece, xDifference, yDifference, 'R', destiny);

                } else if (xDifference < 0) {

                    return verificaMovimentoVertical(piece, xDifference, yDifference, 'L', destiny);

                }

            }

        }

        addScoresStatsInvalid();

        return verificaPossiveisMovimentos(piece);

    }

    private boolean verificaMovimentoVertical(CrazyPiece piece, int xDifference, int yDifference, char leftOrRight,
                                                Position destiny) {

        switch (leftOrRight) {

            case 'R': {

                if (yDifference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveUpRight(piece, xDifference, yDifference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDifference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveDownRight(piece, xDifference, yDifference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);


                }

                if (verificaPosicaoVazia(piece, destiny)) {

                    moveRight(piece, xDifference);

                    return true;

                }

            }

            case 'L': {

                if (yDifference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveUpLeft(piece, xDifference, yDifference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDifference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveDownLeft(piece, xDifference, yDifference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);


                }

                if (verificaPosicaoVazia(piece, destiny)) {

                    moveLeft(piece, xDifference);

                    return true;

                }

            }

            default: {

                if (yDifference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveUp(piece, yDifference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDifference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(piece, destiny)) {

                            moveDown(piece, yDifference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);


                }

                addScoresStatsInvalid();

//              return false because it can not stay in the same position
                return false;

            }

        }

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

                    }
                    break;

                    case 1 : {

//                      Check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R')) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 2 : {

//                      If there are no kings in the way
                        if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 3 : {

//                      check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 6 : {

//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {

                            case 1 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R')) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 2 : {

//                      If there are no kings in the way
                                if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 3 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            default : {

//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }

                        }

                    }
                    break;

                    default : {

                        addScoresStatsInvalid();

                        return false;

                    }

                }

            } else if (yDifference < 0) {

                switch (piece.getType()) {

                    case 0 : {

//                      Move up and right
                        moveUpLeft(piece, xDifference, yDifference);

                    }
                    break;

                    case 1 : {

//                      Check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R')) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 2 : {

//                      If there are no kings in the way
                        if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 3 : {

//                      check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 6 : {

//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {

                            case 1 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R')) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 2 : {

//                              If there are no kings in the way
                                if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 3 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            default : {

//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }

                        }

                    }
                    break;

                    default : {

                        addScoresStatsInvalid();

                        return false;

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
                        moveUpRight(piece, xDifference, yDifference);

                    }
                    break;

                    case 1 : {

//                      Check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R')) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 2 : {

//                      If there are no kings in the way
                        if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 3 : {

//                      check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 6 : {

//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpRight(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {

                            case 1 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R')) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 2 : {

//                      If there are no kings in the way
                                if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 3 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            default : {

//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpRight(piece, xDifference, yDifference);

                                }

                            }

                        }

                    }
                    break;

                    default : {

                        addScoresStatsInvalid();

                        return false;

                    }

                }

            } else if (yDifference < 0) {

                switch (piece.getType()) {

                    case 0 : {

//                      Move up and right
                        moveUpLeft(piece, xDifference, yDifference);

                    }
                    break;

                    case 1 : {

//                      Check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R')) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 2 : {

//                      If there are no kings in the way
                        if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 3 : {

//                      check if there is a piece in the way
                        if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 6 : {

//                      If we are playing in an odd shift
                        if (shift.getIdTeam() %2 != 0) {

//                          Move up and right
                            moveUpLeft(piece, xDifference, yDifference);

                        }

                    }
                    break;

                    case 7 : {

                        switch (piece.getPieceId()) {

                            case 1 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R')) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 2 : {

//                              If there are no kings in the way
                                if (checkClearPath_magicPony(piece, xDifference, yDifference,
                                        piece.getPosition().getxAtual(), piece.getPosition().getyAtual())) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            case 3 : {

//                              check if there is a piece in the way
                                if (checkClearPath(xDifference, yDifference, piece.getPosition().getxAtual(),
                                        piece.getPosition().getyAtual(), 'R') && priestMovement(destiny)) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }
                            break;

                            default : {

//                              If we are playing in an odd shift
                                if (shift.getIdTeam() %2 != 0) {

//                                  Move up and right
                                    moveUpLeft(piece, xDifference, yDifference);

                                }

                            }

                        }

                    }
                    break;

                    default : {

                        addScoresStatsInvalid();

                        return false;

                    }

                }

            }

        }

        addScoresStatsInvalid();

//      return false because a diagonal MUST be able to move in the x axis or because i can not move in the diagonal
//  or staying in the same position
        return false;

    }

    private void changeJokerType() {

        for (CrazyPiece piece : crazyPiecesInGame) {

            if (piece.getType() == 7) {

                piece.changePieceType();

            }

        }

    }

    private void setScores(CrazyPiece piece) {

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

    }

    private boolean checkEmptyPosition(CrazyPiece thisPiece, Position newPosition) {

//      Gets the type of the piece that is moving
        int pieceType = thisPiece.getType();

//      If the type of the piece is:
        //              Search in every piece in the game
        //              Search in every piece in the game
        if (pieceType == 1) {
            for (CrazyPiece piece : crazyPiecesInGame) {

//                  If that same piece is in the newPosition
                if (piece.getPosition().equals(newPosition)) {

//                      Check if the piece is form the enemy team
                    if (piece.getIDTeam() != shift.getIdTeam()) {

//                          If this piece is not a queen
                        if (piece.getType() != 1) {

//                              Call setScores
                            setScores(piece);

//                              Returns true
                            return true;

                        } else {

//                              Print in the console that was an enemy queen on that position
                            System.out.println("There's an enemy queen on that position!");

//                              Add an invalid score to the playing team
                            addScoresStatsInvalid();

//                              Returns false
                            return false;

                        }

                    } else {

//                          Print in the console that was an enemy queen on that position
                        System.out.println("There's already a piece of the same team on that position!");

//                          Add an invalid score to the playing team
                        addScoresStatsInvalid();

//                          Returns false
                        return false;

                    }

                }

            }
        } else {
            for (CrazyPiece piece : crazyPiecesInGame) {

//                  If that same piece is in the newPosition
                if (piece.getPosition().equals(newPosition)) {

//                      Check if the piece is form the enemy team
                    if (piece.getIDTeam() != shift.getIdTeam()) {

//                          Call setScores
                        setScores(piece);

//                          Return true
                        return true;

                    } else {

//                          Print in the console that was an enemy queen on that position
                        System.out.println("There's already a piece of the same team on that position!");

//                          Add an invalid score to the playing team
                        addScoresStatsInvalid();

//                          Returns false
                        return false;

                    }

                }

            }
        }

        switch (shift.getIdTeam()) {

            case 10: {

                addScoresStats(0,0,0,1);

            }break;

            case 20: {

                addScoresStats(0,0,1,0);

            }

        }

        shift.addCountNoCapture();

        return true;

    }

    private void queenMovement(CrazyPiece piece, int xDifference, int yDifference, Position destiny, boolean up) {

        if (up) {

//          Check if the position is empty
            if (verificaPosicaoVazia(piece, destiny)) {

//              If yDifference is bigger than 0
                if (yDifference > 0) {

                } else {

//                  Move up and left
                    moveUpLeft(piece, xDifference, yDifference);

                }

            }

        } else {

//          Check if the position is empty
            if (verificaPosicaoVazia(piece, destiny)) {

//              If yDifference is bigger than 0
                if (yDifference > 0) {

//                  Move down and right
                    moveDownRight(piece, xDifference, yDifference);

                } else {

//                  Move down and left
                    moveDownLeft(piece, xDifference, yDifference);

                }

            }

        }

    }

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

    private boolean checkClearPath(int xDifference, int yDifference, int xActual, int yActual, char up) {

        int finalPosition;

        if (up == 'D') {

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

                finalPosition = yActual + yDifference;

                for (int y = yActual - 1; y < finalPosition; y--) {

                    if (searchForAPiece(xActual, y)) {

                        return false;

                    }

                }

            }


        }
        else if (up == 'R'){

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

                finalPosition = yActual + yDifference;

                for (int y = yActual + 1; y < finalPosition; y++) {

                    if (searchForAPiece(xActual, y)) {

                        return false;

                    }

                }

            }

        }
        else {

            finalPosition = xActual + xDifference;

            if (xDifference < 0) {

                for (int x = xActual - 1; x < finalPosition; x--) {

                    if (searchForAPiece(x, yActual)) {

                        return false;

                    }

                }

            }
            else if (xDifference > 0){

                for (int x = xActual + 1; x < xDifference; x++) {

                    if (searchForAPiece(x, yActual)) {

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

    private boolean checkClearPath_magicPony(CrazyPiece piece, int xDifference, int yDifference, int xActual, int yActual) {

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

    private boolean searchForAQueen(int x, int y) {

//      set thisPosition for x and y
        Position thisPosition = new Position(x, y);

//      Search for a piece in thisPosition
        for (CrazyPiece thisPiece : crazyPiecesInGame) {

//          If thisPiece is in thisPosition
            if (thisPiece.getPosition().equals(thisPosition)) {

//              Return true  if thisPiece is a king else return false
                return thisPiece.getType() == 1;

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

//  comentar
    private boolean verificaPossiveisMovimentos(CrazyPiece piece) {

        System.out.println("This piece can be moved in this ways: " +
                "\nLeft: " + piece.getMoveEsquerda() +
                "\nRight: " + piece.getMoveDireita() +
                "\nUp: " + piece.getMoveCima() +
                "\nDown: " + piece.getMoveBaixo() +
                "\nDown and Left: " + piece.getMoveBaixoEsquerda() +
                "\nDown and Right: " + piece.getMoveBaixoDireta() +
                "\nUp and Left: " + piece.getMoveCimaEsquerda() +
                "\nUp and Right: " + piece.getMoveCimaDireita());

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
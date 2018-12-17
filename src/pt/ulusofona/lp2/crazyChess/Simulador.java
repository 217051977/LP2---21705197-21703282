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

    int boardSize;
    int numberOfBlackPiecesCaptured = 0,
               numberOfWhitePiecesCaptured = 0,
               numberOfValidPlaysByBlackTeam = 0,
               numberOfValidPlaysByWhiteTeam = 0,
               numberOfInvalidPlaysByBlackTeam = 0,
               numberOfInvalidPlaysByWhiteTeam = 0;
    List<CrazyPiece> crazyPieces = new ArrayList<>();
    List<String> authors = new ArrayList<>(), scores = new ArrayList<>(), suggestedPlays = new ArrayList<>();
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

        return crazyPieces;

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

        for (CrazyPiece piece: crazyPieces){

            if (piece.getPosition().equals(position)) {

                return piece.getId();

            }

        }

        //System.out.println("Piece not founded!");

        return 0;

    }

    public int getThisShiftTeamID() {

        return shift.getIdTeam();

    }

    public List<String> getObterSugestoesJogada() {

        return suggestedPlays;

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

                        piece = new Joker();
//
//                    } else if (Integer.parseInt(piecesInfo[1]) == 8) {
//
//                        piece = new Rei();
//
                    } else {

                        throw new NumberFormatException();

                    }

                    crazyPieces.add(piece);

                } else if (nLines < boardSizeMaxIndex) {

                    boardInfo = linha.split(":");

                    if (boardInfo.length != boardSize) {

                        throw new NullPointerException();

                    }

                    for (int index = 0; index < boardInfo.length; index++) {

                        if (Integer.parseInt(boardInfo[index]) != 0) {

                            for (CrazyPiece piece : crazyPieces) {

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

            for (CrazyPiece piece : crazyPieces) {

                if (piece.getPosition().equals(positionOrigin)) {

                    if (piece.getIDTeam() == shift.getIdTeam()) {

                        int xDiference = xD - xO;
                        int yDiference = yD - yO;
                        int xDiferenceABS = xDiference;
                        int yDiferenceABS = yDiference;

                        if (xDiference < 0) {

                            xDiferenceABS = ((xDiference * (-1)));

                        }
                        if (yDiference < 0) {

                            yDiferenceABS = ((yDiference * (-1)));

                        }

                        if (piece.getMinMovHorizontal() <= xDiferenceABS &&
                                piece.getMaxMovHorizontal() >= xDiferenceABS &&
                                piece.getMinMovVertical() <= yDiferenceABS &&
                                piece.getMaxMovVertical() >= yDiferenceABS) {

                            Position newPosition = new Position(xD, yD);

                            return verificaMovimentoHorizontal(piece, xDiference, yDiference, newPosition);

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

        if (crazyPieces.size() == 0) {

            return true;

        } else if (shift.getCountNoCapture() == 10 && firstCapture) {

            addScoreStatsToPrint("EMPATE");

            shift.resetCount();
            shift.resetCountNoCapture();

            return true;

        } else {

            for (CrazyPiece piece : crazyPieces) {

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
    private boolean verificaMovimentoHorizontal(CrazyPiece piece, int xDiference, int yDiference, Position newPosition) {

        if (xDiference > 0) {

            if (piece.getMoveDireita()) {

                return verificaMovimentoVertical(piece, xDiference, yDiference, 'R', newPosition);

            } else if (verificaMovimentoDiagonal(piece, xDiference, yDiference, newPosition)) {

                return true;

            }

            addScoresStatsInvalid();

            return verificaPossiveisMovimentos(piece);

        } else if (xDiference < 0) {

            if (piece.getMoveEsquerda()) {

                return verificaMovimentoVertical(piece, xDiference, yDiference, 'L', newPosition);


            } else if (verificaMovimentoDiagonal(piece, xDiference, yDiference, newPosition)) {

                return true;

            }

            addScoresStatsInvalid();

            return verificaPossiveisMovimentos(piece);

        }

        return verificaMovimentoVertical(piece, xDiference, yDiference, 'N', newPosition);

    }

    private boolean verificaMovimentoVertical(CrazyPiece piece, int xDiference, int yDiference, char leftOrRight,
                                                Position newPosition) {

        switch (leftOrRight) {

            case 'R': {

                if (yDiference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUpRight(piece, xDiference, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDiference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownRight(piece, xDiference, yDiference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);


                }

                if (verificaPosicaoVazia(newPosition)) {

                    moveRight(piece, xDiference);

                    return true;

                }

            }

            case 'L': {

                if (yDiference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUpLeft(piece, xDiference, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDiference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownLeft(piece, xDiference, yDiference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);


                }

                if (verificaPosicaoVazia(newPosition)) {

                    moveLeft(piece, xDiference);

                    return true;

                }

            }

            default: {

                if (yDiference > 0) {

                    if (piece.getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUp(piece, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(piece);

                } else if (yDiference < 0) {

                    if (piece.getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDown(piece, yDiference);

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
    private boolean verificaMovimentoDiagonal(CrazyPiece piece, int xDiference, int yDiference, Position newPosition) {

        if (xDiference > 0) {

            if (piece.getMoveCimaDireita()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpRight(piece, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            } else if (piece.getMoveBaixoDireta()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownRight(piece, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            }

        } else if (xDiference < 0) {

            if (piece.getMoveCimaEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpLeft(piece, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            } else if (piece.getMoveBaixoEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownLeft(piece, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            }

        }

        addScoresStatsInvalid();

//      return false because a diagonal MUST be able to move in the x axis or because i can not move in the diagonal
//  or staying in the same position
        return false;

    }

    private boolean verificaPosicaoVazia(Position newPosition) {

        for (CrazyPiece piece : crazyPieces) {

            if (piece.getPosition().equals(newPosition)) {

                if (piece.getIDTeam() != shift.getIdTeam()) {

                    switch (shift.getIdTeam()) {

                        case 10: {

                            addScoresStats(0,1,0,1);

                        }break;

                        case 20: {

                            addScoresStats(1,0,1,0);

                        }

                    }

                    crazyPieces.remove(piece);
                    firstCapture = true;
                    shift.resetCountNoCapture();

                    return true;

                } else {

                    System.out.println("There's already a piece of the same team on that position!");

                    addScoresStatsInvalid();

                    return false;

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

    private void moveLeft(CrazyPiece piece, int xDiference) {

        piece.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveRight(CrazyPiece piece, int xDiference) {

        piece.moveRight(xDiference);
        shift.addCount();

    }

    private void moveUp(CrazyPiece piece, int yDiference) {

        piece.moveUp(yDiference);
        shift.addCount();

    }

    private void moveDown(CrazyPiece piece, int yDiference) {

        piece.moveDown((yDiference * (-1)));
        shift.addCount();

    }

    private void moveDownLeft(CrazyPiece piece, int xDiference, int yDiference) {

        piece.moveDown((yDiference * (-1)));
        piece.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveUpLeft(CrazyPiece piece, int xDiference, int yDiference) {

        piece.moveUp(yDiference);
        piece.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveUpRight(CrazyPiece piece, int xDiference, int yDiference) {

        piece.moveUp(yDiference);
        piece.moveRight(xDiference);
        shift.addCount();

    }

    private void moveDownRight(CrazyPiece piece, int xDiference, int yDiference) {

        piece.moveDown((yDiference * (-1)));
        piece.moveRight(xDiference);
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
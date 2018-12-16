package pt.ulusofona.lp2.crazyChess.MainsClasses;

import jdk.nashorn.internal.ir.annotations.Ignore;
import pt.ulusofona.lp2.crazyChess.CrazyPiece.Tipo;
import pt.ulusofona.lp2.crazyChess.Position;
import pt.ulusofona.lp2.crazyChess.Shift;
import pt.ulusofona.lp2.crazyChess.Team;

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

        for (CrazyPiece peca: crazyPieces){

            if (peca.getPosition().equals(position)) {

                return peca.getId();

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
//    public boolean setPeca(CrazyPiece peca) {
//
//        try {
//
//            this.pecasMalucas.add(peca);
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
//    public boolean removePeca(CrazyPiece peca) {
//
//        try {
//
//            this.pecasMalucas.remove(peca);
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

                return false;

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

                tenho de ver se n pecas != npecasmax
                tenho de ver se n npecasmax e != maxtabuleiro

                 */

//                quatro partes;
//
//                1 - dimensoes do tabuleiro => int
//                2 - quantidade peças existentes no tabuleiro
//                3 - descreve as pecas existentes no tabuleiro
//                4 - conteudo inicial do tabuleiro ( posicao das pecas)

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

                        return false;

                    }

                    try {

                        Integer.parseInt(piecesInfo[3]);
                        return false;

                    } catch (Exception e) {

                        Ignore isAPiece;

                    }

                    Tipo tipo = new Tipo(Byte.parseByte(piecesInfo[1])); //check if there's only 0 type
                    CrazyPiece peca = new CrazyPiece(Integer.parseInt(piecesInfo[0]),
                            tipo,
                            Integer.parseInt(piecesInfo[2]),
                            piecesInfo[3]);

                    crazyPieces.add(peca);

                } else if (nLines < boardSizeMaxIndex) {

                    boardInfo = linha.split(":");

                    if (boardInfo.length != boardSize) {

                        return false;

                    }

                    for (int index = 0; index < boardInfo.length; index++) {

                        if (Integer.parseInt(boardInfo[index]) != 0) {

                            for (CrazyPiece peca : crazyPieces) { //peca olaf

                                if (peca.getId() == Integer.parseInt(boardInfo[index])) {

                                    Position position = new Position(index, yPosition);
                                    peca.setPosition(position);
                                    peca.estaEmJogo();
                                    System.out.println(peca);
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

            for (CrazyPiece peca : crazyPieces) {

                if (peca.getPosition().equals(positionOrigin)) {

                    if (peca.getIDTeam() == shift.getIdTeam()) {

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

                        if (peca.getTipo().getMinMovHorizontal() <= xDiferenceABS &&
                                peca.getTipo().getMaxMovHorizontal() >= xDiferenceABS &&
                                peca.getTipo().getMinMovVertical() <= yDiferenceABS &&
                                peca.getTipo().getMaxMovVertical() >= yDiferenceABS) {

                            Position newPosition = new Position(xD, yD);

                            return verificaMovimentoHorizontal(peca, xDiference, yDiference, newPosition);

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

            for (CrazyPiece peca : crazyPieces) {

                if (peca.getEmJogo()) {

                    if (peca.getTipo().getid() == 0) {

                        if (peca.getIDTeam() == 10) {

                            nreiPreto++;

                        } else if (peca.getIDTeam() == 20) {

                            nreiBranco++;

                        }

                    }

                }

            }

            if (nreiPreto == 0) {

                System.out.println("ENCERAM AS BRANCAS");

                addScoreStatsToPrint("VENCERAM AS BRANCAS");

                return true;

            } else if (nreiBranco == 0) {

                System.out.println("ENCERAM AS PRETAS");

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
    private boolean verificaMovimentoHorizontal(CrazyPiece peca, int xDiference, int yDiference, Position newPosition) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveDireita()) {

                return verificaMovimentoVertical(peca, xDiference, yDiference, 'R', newPosition);

            } else if (verificaMovimentoDiagonal(peca, xDiference, yDiference, newPosition)) {

                return true;

            }

            addScoresStatsInvalid();

            return verificaPossiveisMovimentos(peca);

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveEsquerda()) {

                return verificaMovimentoVertical(peca, xDiference, yDiference, 'L', newPosition);


            } else if (verificaMovimentoDiagonal(peca, xDiference, yDiference, newPosition)) {

                return true;

            }

            addScoresStatsInvalid();

            return verificaPossiveisMovimentos(peca);

        }

        return verificaMovimentoVertical(peca, xDiference, yDiference, 'N', newPosition);

    }

    private boolean verificaMovimentoVertical(CrazyPiece peca, int xDiference, int yDiference, char leftOrRight,
                                                Position newPosition) {

        switch (leftOrRight) {

            case 'R': {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUpRight(peca, xDiference, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownRight(peca, xDiference, yDiference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);


                }

                if (verificaPosicaoVazia(newPosition)) {

                    moveRight(peca, xDiference);

                    return true;

                }

            }

            case 'L': {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUpLeft(peca, xDiference, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownLeft(peca, xDiference, yDiference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);


                }

                if (verificaPosicaoVazia(newPosition)) {

                    moveLeft(peca, xDiference);

                    return true;

                }

            }

            default: {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveUp(peca, yDiference);

                            return true;

                        }

                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDown(peca, yDiference);

                            return true;

                        }


                    }

                    addScoresStatsInvalid();

                    return verificaPossiveisMovimentos(peca);


                }

                addScoresStatsInvalid();

//              return false because it can not stay in the same position
                return false;

            }

        }

    }

//  comentar
    private boolean verificaMovimentoDiagonal(CrazyPiece peca, int xDiference, int yDiference, Position newPosition) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveCimaDireita()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpRight(peca, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            } else if (peca.getTipo().getMoveBaixoDireta()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownRight(peca, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            }

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveCimaEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpLeft(peca, xDiference, yDiference);

                    return true;

                }

                addScoresStatsInvalid();

            } else if (peca.getTipo().getMoveBaixoEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownLeft(peca, xDiference, yDiference);

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

        for (CrazyPiece peca : crazyPieces) {

            if (peca.getPosition().equals(newPosition)) {

                if (peca.getIDTeam() != shift.getIdTeam()) {

                    switch (shift.getIdTeam()) {

                        case 10: {

                            addScoresStats(0,1,0,1);

                        }break;

                        case 20: {

                            addScoresStats(1,0,1,0);

                        }

                    }

                    crazyPieces.remove(peca);
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
    private boolean verificaPossiveisMovimentos(CrazyPiece peca) {

        System.out.println("This piece can be moved in this ways: " +
                "\nLeft: " + peca.getTipo().getMoveEsquerda() +
                "\nRight: " + peca.getTipo().getMoveDireita() +
                "\nUp: " + peca.getTipo().getMoveCima() +
                "\nDown: " + peca.getTipo().getMoveBaixo() +
                "\nDown and Left: " + peca.getTipo().getMoveBaixoEsquerda() +
                "\nDown and Right: " + peca.getTipo().getMoveBaixoDireta() +
                "\nUp and Left: " + peca.getTipo().getMoveCimaEsquerda() +
                "\nUp and Right: " + peca.getTipo().getMoveCimaDireita());

        return false;

    }

    private void moveLeft(CrazyPiece peca, int xDiference) {

        peca.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveRight(CrazyPiece peca, int xDiference) {

        peca.moveRight(xDiference);
        shift.addCount();

    }

    private void moveUp(CrazyPiece peca, int yDiference) {

        peca.moveUp(yDiference);
        shift.addCount();

    }

    private void moveDown(CrazyPiece peca, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        shift.addCount();

    }

    private void moveDownLeft(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        peca.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveUpLeft(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveUp(yDiference);
        peca.moveLeft((xDiference * (-1)));
        shift.addCount();

    }

    private void moveUpRight(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveUp(yDiference);
        peca.moveRight(xDiference);
        shift.addCount();

    }

    private void moveDownRight(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        peca.moveRight(xDiference);
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
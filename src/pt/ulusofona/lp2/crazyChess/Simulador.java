package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    private int tamanhoTabuleiro;
    private List<CrazyPiece> pecasMalucas = new ArrayList<>();
    private List<String> autores = new ArrayList<>(), resultados = new ArrayList<>();
    private List<Equipa> team = new ArrayList<>();
    private Turno turno = new Turno();
    private boolean primeiraCaptura = false;

//    Construtor(s)

    public Simulador() {}

    public Simulador(int tamanhoTabuleiro) {

        this.tamanhoTabuleiro = tamanhoTabuleiro;

    }


//    gets
    public int getTamanhoTabuleiro() {

        return tamanhoTabuleiro;

    }

    public List<CrazyPiece> getPecasMalucas() {

        return pecasMalucas;

    }

    public List<String> getAutores() {

        if (autores.size() == 0) {

            this.autores.add("Bruno Miguel Dias Leal, nº 21705197");
            this.autores.add("João Domingos, nº 21703282");

        }

        return autores;

    }


//  Nao pode devolver null
    public List<String> getResultados() {

        return resultados;

    }

    public int getIDPeca(int x, int y) {

        Position position = new Position(x, y);

        for (CrazyPiece peca: pecasMalucas){

            if (peca.getPosition().equals(position)) {

                return peca.getId();

            }

        }

        //System.out.println("Piece not founded!");

        return 0;

    }

    public int getIDEquipaAJogar() {

        return turno.getIdTeam();

    }

    public Turno getTurno() {

        return turno;

    }

    public boolean getPrimeiraCaptura() {

        return this.primeiraCaptura;

    }

    public boolean setPeca(CrazyPiece peca) {

        try {

            this.pecasMalucas.add(peca);


            return true;

        } catch (Exception impossibleToAddANexPiece) {

            System.out.println("Impossible to add a new piece");

            return false;

        }

    }

    public boolean removePeca(CrazyPiece peca) {

        try {

            this.pecasMalucas.remove(peca);

            return true;

        } catch (Exception impossibleToAddANexPiece) {

            System.out.println("Impossible to add a new piece");

            return false;

        }

    }

//  falta completar guardar a informaçao lida do ficheiro na memoria
    public boolean iniciaJogo(File ficheiroInicial) {

        try {

            Scanner scan = new Scanner(ficheiroInicial);

            int nPieces = 0,
                    nPiecesMaxIndex,
                    tamanhoTabuleiroMaxIndex,
                    nLines = 0,
                    yPosition = 0;
            String[] piecesInfo;
            String[] boardInfo;

            while (scan.hasNextLine()) {

                String linha = scan.nextLine();
                System.out.println(linha);
                nPiecesMaxIndex = nPieces + 2;
                tamanhoTabuleiroMaxIndex = tamanhoTabuleiro + nPiecesMaxIndex;

                /*

                quatro partes;

                1 - dimensoes do tabuleiro => int
                2 - quantidade peças existentes no tabuleiro
                3 - descreve as pecas existentes no tabuleiro
                4 - conteudo inicial do tabuleiro ( posicao das pecas)


                */


                // check if the number of pieces is lower than the tamnhoTabuleiro^2

                if (nLines == 0) {

                    tamanhoTabuleiro = Integer.parseInt(linha);

                } else if (nLines == 1) {

                    nPieces = Integer.parseInt(linha);

                } else if (nLines < nPiecesMaxIndex) {

                    piecesInfo = linha.split(":");
                    Tipo tipo = new Tipo(Byte.parseByte(piecesInfo[1])); //check if there's only 0 type
                    Equipa equipa = new Equipa(Integer.parseInt(piecesInfo[2]));
                    CrazyPiece peca = new CrazyPiece(Integer.parseInt(piecesInfo[0]),
                            tipo,
                            equipa,
                            piecesInfo[3]);

                    pecasMalucas.add(peca);

                } else if (nLines < tamanhoTabuleiroMaxIndex) {

                    boardInfo = linha.split(":");

                    for (int index = 0; index < boardInfo.length; index++) {

                        if (Integer.parseInt(boardInfo[index]) != 0) {

                            for (CrazyPiece peca : pecasMalucas) { //peca olaf

                                if (peca.getId() == Integer.parseInt(boardInfo[index])) {

                                    Position position = new Position(index, yPosition);
                                    peca.setPosition(position);
                                    System.out.println(peca);

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

            return true;

        } catch (FileNotFoundException e) {

            System.out.println(ficheiroInicial.getName() + " not founded!");

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

        if (xD >= 0 && xD <= (tamanhoTabuleiro - 1) &&
            yD >= 0 && yD <= (tamanhoTabuleiro - 1)) {

            Position positionOrigin = new Position(xO, yO);

            for (CrazyPiece peca : pecasMalucas) {

                if (peca.getPosition().equals(positionOrigin)) {

                    if (peca.getTeam().getId() == turno.getIdTeam()) {

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

                }

            }

        }

        return false;

    }

//  falta a pontuacao
    public boolean jogoTerminado() {

        int reiBranco = 0;
        int reiPreto = 0;

        if (turno.getCountNoCapture() == 10 && primeiraCaptura) {

            turno.resetCount();
            turno.resetCountNoCapture();

            return true;

        } else {

            for (CrazyPiece peca : pecasMalucas) {

                if (peca.getTipo().getid() == 0) {

                    if (peca.getTeam().getId() == 0) {

                        reiBranco++;

                    } else if (peca.getTeam().getId() == 1) {

                        reiPreto++;

                    }

                }

            }

            if (reiPreto == 0) {

                System.out.println("The whites wins!");

                return true;

            } else if (reiBranco == 0) {

                System.out.println("The blacks wins!");

                return true;

            } else if (reiPreto == 1 && reiBranco ==1) {

                System.out.println("It's a draw");

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

            } else if (verificarMovimentoDiagonal(peca, xDiference, yDiference, newPosition)) {

                return true;

            }

            return verificaPossiveisMovimentos(peca);

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveEsquerda()) {

                return verificaMovimentoVertical(peca, xDiference, yDiference, 'L', newPosition);


            } else if (verificarMovimentoDiagonal(peca, xDiference, yDiference, newPosition)) {

                return true;

            }

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

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownRight(peca, xDiference, yDiference);

                            return true;

                        }


                    }

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

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDownLeft(peca, xDiference, yDiference);

                            return true;

                        }


                    }

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

                    return verificaPossiveisMovimentos(peca);

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        if (verificaPosicaoVazia(newPosition)) {

                            moveDown(peca, yDiference);

                            return true;

                        }


                    }

                    return verificaPossiveisMovimentos(peca);


                }

//              return false because it can not stay in the same position
                return false;

            }

        }

    }

//  comentar
    private boolean verificarMovimentoDiagonal(CrazyPiece peca, int xDiference, int yDiference, Position newPosition) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveCimaDireita()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpRight(peca, xDiference, yDiference);

                    return true;

                }

            } else if (peca.getTipo().getMoveBaixoDireta()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownRight(peca, xDiference, yDiference);

                    return true;

                }

            }

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveCimaEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveUpLeft(peca, xDiference, yDiference);

                    return true;

                }

            } else if (peca.getTipo().getMoveBaixoEsquerda()) {

                if (verificaPosicaoVazia(newPosition)) {

                    moveDownLeft(peca, xDiference, yDiference);

                    return true;

                }

            }

        }

//      return false because a diagonal MUST be able to move in the x axis or because i can not move in the diagonal
//  or staying in the same position
        return false;

    }

    private boolean verificaPosicaoVazia(Position newPosition) {

        for (CrazyPiece peca : pecasMalucas) {

            if (peca.getPosition().equals(newPosition)) {

                if (peca.getTeam().getId() != turno.getIdTeam()) {

                    pecasMalucas.remove(peca);
                    primeiraCaptura = true;
                    turno.addCountNoCapture();
                    System.out.println("\n");

                    return true;

                } else {

                    System.out.println("There's already a piece of the same team on that position!");
                    System.out.println("\n");

                    return false;

                }

            }

        }

        turno.resetCountNoCapture();
        System.out.println("\n");

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
        turno.addCount();

    }

    private void moveRight(CrazyPiece peca, int xDiference) {

        peca.moveRight(xDiference);
        turno.addCount();

    }

    private void moveUp(CrazyPiece peca, int yDiference) {

        peca.moveUp(yDiference);
        turno.addCount();

    }

    private void moveDown(CrazyPiece peca, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        turno.addCount();

    }

    private void moveDownLeft(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        peca.moveLeft((xDiference * (-1)));
        turno.addCount();

    }

    private void moveUpLeft(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveUp(yDiference);
        peca.moveLeft((xDiference * (-1)));
        turno.addCount();

    }

    private void moveUpRight(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveUp(yDiference);
        peca.moveRight(xDiference);
        turno.addCount();

    }

    private void moveDownRight(CrazyPiece peca, int xDiference, int yDiference) {

        peca.moveDown((yDiference * (-1)));
        peca.moveRight(xDiference);
        turno.addCount();

    }

}

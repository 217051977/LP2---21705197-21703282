package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

class Simulador {

    private int tamanhoTabuleiro;
    private List<CrazyPiece> pecasMalucas;
    private List<String> autores, resultados;
    private int idEquipaAJogar = 0;
    private List<Equipa> team;
    private Turno turno;
    private boolean primeiraCaptura = false;

//    Construtor(s)
    Simulador(int tamanhoTabuleiro) {

        this.tamanhoTabuleiro = tamanhoTabuleiro;

    }


//    gets
    int getTamanhoTabuleiro() {

        return tamanhoTabuleiro;

    }

    List<CrazyPiece> getPecasMalucas() {

        return pecasMalucas;

    }

    List<String> getAutores() {

        return autores;

    }


//  Nao pode devolver null
    List<String> getResultados() {

        return resultados;

    }

    int getIDPeca(int x, int y) {

        int idPeca = -1;

        try {

            Position position = new Position(x, y);

            for (CrazyPiece peca: pecasMalucas){

                if (peca.getPosition().equals(position)) {

                    idPeca = peca.getID();
                    break;
                }

            }

        }catch(Exception KeyNotFound) {

            System.out.println("Peça not founded!");

        }

        return idPeca;

    }

    int getIDEquipaAJogar() {

        return idEquipaAJogar;

    }

    Turno getTurno() {

        return turno;

    }

    boolean getPrimeiraCaptura() {

        return this.primeiraCaptura;

    }













    boolean setPeca(CrazyPiece peca) {

        try {

            this.pecasMalucas.add(peca);


            return true;

        } catch (Exception impossibleToAddANexPiece) {

            System.out.println("Impossible to add a new piece");

            return false;

        }

    }

    boolean removePeca(CrazyPiece peca) {

        try {

            this.pecasMalucas.remove(peca);

            return true;

        } catch (Exception impossibleToAddANexPiece) {

            System.out.println("Impossible to add a new piece");

            return false;

        }

    }

//  falta completar guardar a informaçao lida do ficheiro na memoria
    boolean iniciaJogo(File ficheiroInicial) {

        try {

            Scanner scan = new Scanner(ficheiroInicial);

            while (scan.hasNextLine()){

                String linha = scan.nextLine();


            }

            return true;

        } catch (FileNotFoundException e) {

            System.out.println(ficheiroInicial.getName() + " not founded!");

            return false;
        }

    }

//  comentar
    boolean processaJogada(int xO, int yO, int xD, int yD) {

        if (xD >= 0 && xD <= (tamanhoTabuleiro - 1) &&
            yD >= 0 && yD <= (tamanhoTabuleiro - 1)) {

            Position newPosition = new Position(xD, yD);

            for (CrazyPiece peca : pecasMalucas) {

                if (peca.getPosition().equals(newPosition)) {

                    if (peca.getTeam().getId() == turno.getIdTeam()) {

                        int xDiference = xD - xO;
                        int yDiference = yD - yO;

                        if (peca.getTipo().getMinMovHorizontal() >= xDiference &&
                                peca.getTipo().getMaxMovHorizontal() <= xDiference &&
                                peca.getTipo().getMinMovVertical() >= yDiference &&
                                peca.getTipo().getMaxMovVertical() <= yDiference) {

                            return verificaMovimentoHorizontal(peca, xDiference, yDiference, newPosition);

                        }

                    }

                }

            }

        }

        return false;

    }

//  falta a pontuacao
    boolean jogoTerminado() {

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

    boolean chageTeam(Equipa nextTeam) {

        try {

            System.out.println(nextTeam.getNome());
            idEquipaAJogar = nextTeam.getId();

            return true;

        } catch (Exception impossibleToChangeTeam) {

            System.out.println("Impossible to change team");

            return false;

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

                moveRight(peca, xDiference);

                return true;

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

                moveLeft(peca, xDiference);

                return true;

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

            if (peca.getPosition() == newPosition) {

                if (peca.getTeam().getId() != turno.getIdTeam()) {

                    pecasMalucas.remove(peca);
                    primeiraCaptura = true;
                    turno.addCountNoCapture();

                } else {

                    System.out.println("There's already a piece of the same team on that position!");
                    return false;

                }

                break;

            }

        }

        turno.resetCountNoCapture();
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

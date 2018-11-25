package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Simulador {

    private int tamanhoTabuleiro;
    private List<CrazyPiece> pecasMalucas;
    private List<String> autores, resultados;
    private int idEquipaAJogar = 0;
    private List<Equipa> team;

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

        if (xD >= 0 && xD <= tamanhoTabuleiro &&
            yD >= 0 && yD <= tamanhoTabuleiro) {

            Position newPosition = new Position(xD, yD);

            for (CrazyPiece peca : pecasMalucas) {

                if (peca.getPosition().equals(newPosition)) {

                    int xDiference = xD - xO;
                    int yDiference = yD - yO;

                    if (peca.getTipo().getMinMovHorizontal() >= xDiference &&
                        peca.getTipo().getMaxMovHorizontal() <= xDiference &&
                        peca.getTipo().getMinMovVertical() >= yDiference &&
                        peca.getTipo().getMaxMovVertical() <= yDiference){

                        return verificaMovimentoHorizontal(peca, xDiference, yDiference);

                    }

                }

            }

        }

        return false;

    }


//  Deve devolver true caso já tenha sido
//alcançada uma das condições de paragem
//do jogo () e false em caso contrário.
    boolean jogoTerminado() {

        //Deve devolver true caso já tenha sido
        //alcançada uma das condições de paragem
        //do jogo () e false em caso contrário.

        return true;

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
    private boolean verificaMovimentoHorizontal(CrazyPiece peca, int xDiference, int yDiference) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveDireita()) {

                return verificaMovimentoVertical(peca, xDiference, yDiference, 'R');

            } else if (verificarMovimentoDiagonal(peca, xDiference, yDiference)) {

                return true;

            }

            return verificaPossiveisMovimentos(peca);

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveEsquerda()) {

                return verificaMovimentoVertical(peca, xDiference, yDiference, 'L');


            }

            return verificaPossiveisMovimentos(peca);

        }

        return verificaMovimentoVertical(peca, xDiference, yDiference, 'N');

    }

    private boolean verificaMovimentoVertical(CrazyPiece peca, int xDiference, int yDiference, char leftOrRight) {

        switch (leftOrRight) {

            case 'R': {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        peca.moveUp(yDiference);
                        peca.moveRight(xDiference);

                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        peca.moveDown((yDiference * (-1)));
                        peca.moveRight(xDiference);


                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }


                }

            } break;

            case 'L': {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        peca.moveUp(yDiference);
                        peca.moveLeft((xDiference * (-1)));

                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        peca.moveDown((yDiference * (-1)));
                        peca.moveLeft((xDiference * (-1)));


                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }


                }

            } break;

            default: {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        peca.moveUp(yDiference);

                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        peca.moveDown((yDiference * (-1)));


                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }


                }

//              return false because it can not stay in the same position
                return false;

            }

        }

        return true;

    }

//  comentar
    private boolean verificarMovimentoDiagonal(CrazyPiece peca, int xDiference, int yDiference) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveCimaDireita()) {

                peca.moveUp(yDiference);
                peca.moveRight(xDiference);
                return true;

            } else if (peca.getTipo().getMoveBaixoDireta()) {

                peca.moveDown((yDiference * (-1)));
                peca.moveRight(xDiference);
                return true;

            }

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveCimaEsquerda()) {

                peca.moveUp(yDiference);
                peca.moveLeft((xDiference * (-1)));
                return true;

            } else if (peca.getTipo().getMoveBaixoEsquerda()) {

                peca.moveDown((yDiference * (-1)));
                peca.moveLeft((xDiference * (-1)));
                return true;

            }

        }

//      return false because a diagonal MUST be able to move in the x axis or because i can not move in the diagonal
//  or staying in the same position
        return false;

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

}

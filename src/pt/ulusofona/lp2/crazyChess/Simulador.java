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

    //    falta completar guardar a informaçao lida do ficheiro na memoria
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

//  Deve tentar executar uma jogada,
//considerando que (xO, yO) representa a
//origem a jogada e (xD, yD) representa o
//destino da jogada.
//  Caso a jogada seja válida, deve executar a
//mesma e devolver true. Em caso
//contrário, deve devolver false.

    boolean processaJogada(int xO, int yO, int xD, int yD) {

        //confirmar se a jogada e valida



        int xDiference = xD - xO;
        int yDiference = yD - yO;

        Position newPosition = new Position(xD, yD);

        if (xDiference <= tamanhoTabuleiro && yDiference <= tamanhoTabuleiro) {

            for (CrazyPiece peca : pecasMalucas) {

                if (peca.getPosition().equals(newPosition)) {

                    verificaMovimentoHorizontal(peca, xDiference, yDiference);

                }

            }

        }



        return true;

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
    private boolean verificaPossiveisMovimentos(CrazyPiece peca) {

        System.out.println("This piece can be moved in this ways: " +
                "\nLeft: " + peca.getTipo().getMoveEsquerda() +
                "\nRight: " + peca.getTipo().getMoveDireita() +
                "\nUp: " + peca.getTipo().getMoveCima() +
                "\nDown: :" + peca.getTipo().getMoveBaixo());

        return false;

    }

    private boolean verificaMovimentoVertical(CrazyPiece peca, int yDiference, int leftOrRight, int xDiference) {

        switch (leftOrRight) {

            case 0: {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        peca.moveUp(yDiference);
                        peca.moveRight(xDiference);

                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        peca.moveDown(yDiference);


                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }


                }

            } break;

            case 1: {

                if (yDiference > 0) {

                    if (peca.getTipo().getMoveCima()) {

                        peca.moveUp(yDiference);
                        peca.moveLeft(xDiference);

                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }

                } else if (yDiference < 0) {

                    if (peca.getTipo().getMoveBaixo()) {

                        peca.moveDown(yDiference);


                    } else {

                        return verificaPossiveisMovimentos(peca);

                    }


                }

            }

        }


        return true;

    }

    private boolean verificaMovimentoHorizontal(CrazyPiece peca, int xDiference, int yDiference) {

        if (xDiference > 0) {

            if (peca.getTipo().getMoveDireita()) {

                verificaMovimentoVertical(peca, yDiference, 0, xDiference);

            } else {

                return verificaPossiveisMovimentos(peca);

            }

        } else if (xDiference < 0) {

            if (peca.getTipo().getMoveEsquerda()) {

                verificaMovimentoVertical(peca, yDiference, 1, xDiference);


            } else {

                return verificaPossiveisMovimentos(peca);

            }


        }

        return true;

    }

}

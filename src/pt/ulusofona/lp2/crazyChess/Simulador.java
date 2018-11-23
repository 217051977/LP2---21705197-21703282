package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Simulador {

    private int tamanhoTabuleiro;
    private List<String> autores, resultados;
    private HashMap<Position, CrazyPiece> Pecas;
    private int idEquipaAJogar = 0;
    private List<Equipa> team;

//    Construtor(s)
    Simulador() {}


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

    List<String> getResultados() {

        return resultados;

    }

    int getIDPeca(int xPosition, int yPosition) {

        try {

            Position position = new Position(xPosition, yPosition);

            return this.Pecas.get(position);

        }catch(Exception KeyNotFound) {

            System.out.println("Peça not founded!");

            return 0;

        }

    }

    int getIDEquipaAJogar() {

        return idEquipaAJogar;

    }

    boolean setPeca(HashMap<Position, Integer> pecas) {

        try {

            this.Pecas = pecas;

            return true;

        } catch (Exception impossibleToAddANexPiece) {

            System.out.println("Impossible to add a new piece");

            return false;

        }

    }

    boolean removePeca(HashMap<Position, Integer> peca) {

        try {

            this.Pecas.put(peca);

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

    boolean processaJogada(int xO, int yO, int xD, int Yd){

        //confirmar se a jogada e valida

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

}

package pt.ulusofona.lp2.crazyChess;

public class TorreV extends CrazyPiece {

    TorreV() {

//      set the movement of the V Tower type piece
        super.moveEsquerda = false;
        super.moveDireita = false;
        super.moveCimaDireita = false;
        super.moveCimaEsquerda = false;
        super.moveBaixoDireita = false;
        super.moveBaixoEsquerda = false;

//      set how much it can move
        super.maxMovHorizontal = 0;
        super.maxMovVertical = Integer.MAX_VALUE;

//      set how much it has to move
        super.minMovHorizontal = 0;
        super.minMovVertical = 1;

//      set the type piece as 5 (V Tower)
        super.type = 5;

//      set the impossibility to change type!
        super.canChangeType = false;

//      set the relative value
        super.valorRelativo = 3;

    }

    public TorreV(int id, String name) {

//      set the movement of the V Tower type piece
        super.moveEsquerda = false;
        super.moveDireita = false;
        super.moveCimaDireita = false;
        super.moveCimaEsquerda = false;
        super.moveBaixoDireita = false;
        super.moveBaixoEsquerda = false;

//      set how much it can move
        super.maxMovHorizontal = 0;
        super.maxMovVertical = 0;

//      set how much it has to move
        super.minMovHorizontal = 1;
        super.minMovVertical = 1;

//      set the type piece as 5 (V Tower)
        super.type = 5;

//      set the impossibility to change type!
        super.canChangeType = false;

//      set the relative value
        super.valorRelativo = 3;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.

}

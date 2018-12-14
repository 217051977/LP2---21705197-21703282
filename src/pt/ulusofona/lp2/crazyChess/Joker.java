package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece{

    private byte id = 7;
    private byte pieceId = 1;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    Joker() {}

    public void changePieceType() {

        if (pieceId == 7) {

            pieceId = 1;

        } else {

            pieceId++;

        }

    }

    public void undoPieceType() {

        if (pieceId != 1) {

            pieceId--;

        } else {

            pieceId = 7;

        }

    }

}

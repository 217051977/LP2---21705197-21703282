package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {

    private byte pieceId = 1;

    Joker() {

//      Because it stats as a queen:
        //  set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 7 (Joker)
        super.type = 7;

//      set the impossibility to change type!
        super.canChangeType = true; // for now

//      set the relative value
        super.valorRelativo = 4;

    }

    public Joker(int id, String name) {

//      Because it stats as a queen:
        //  set how much it can move
        super.maxMovHorizontal = 5;
        super.maxMovVertical = 5;

//      set the type piece as 7 (Joker)
        super.type = 7;

//      set the impossibility to change type!
        super.canChangeType = true; // for now

//      set the relative value
        super.valorRelativo = 4;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    public byte getPieceId() {

        return pieceId;

    }

    @Override
    public void changePieceType() {

        if (pieceId == 6) {

            pieceId = 1;

        } else {

            pieceId++;

        }

    }

    @Override
    public void undoPieceType() {

        if (pieceId != 1) {

            pieceId--;

        } else {

            pieceId = 6;

        }

    }

}

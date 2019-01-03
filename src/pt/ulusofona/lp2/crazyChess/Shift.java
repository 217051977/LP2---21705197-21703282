
package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class Shift {

    int count = 0, countNoCapture = 0;
    byte idTeam = 10;

    public Shift() {}

    public int getCount() {

        return count;

    }

    public int getCountNoCapture() {

        return this.countNoCapture;

    }

    public byte getIdTeam() {

        return idTeam;

    }

    public void addCount(List<CrazyPiece> crazyPiecesInGame) {

        count++;

        if (count % 2 == 0) {

            idTeam = 10;

        } else {

            idTeam = 20;

        }

        for (CrazyPiece piece : crazyPiecesInGame) {

            if (piece.getType() == 7) {

                piece.nextType();

            }

        }


    }

    public void removeCount() {

        this.count--;

        if (idTeam == 20) {

            idTeam = 10;

        } else {

            idTeam = 20;

        }

    }

    public void addCountNoCapture() {

        countNoCapture++;

    }

    public void removeCountNoCapture() {

        countNoCapture--;

    }

    public void undoCountNoCapture(int countNoCapture) {

        this.countNoCapture = countNoCapture;

    }

    public void resetCount() {

        count = countNoCapture = idTeam = 0;

    }

    public void resetCountNoCapture() {

        countNoCapture = 0;

    }

    public void changeIdTeam(int idTeam) {

        this.idTeam = (byte) idTeam;

    }

    public void changeCount(int count) {

        this.count = count;

    }

    @Override
    public String toString() {

        return "Team ID: " + idTeam +
                "\nCount without captures: " + countNoCapture +
                "\nShift counter: " + count;

    }

    public void reset() {

        count = 0;
        countNoCapture = 0;
        idTeam = 10;

    }

}
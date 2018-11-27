package pt.ulusofona.lp2.crazyChess;

public class Turno {

    private int count = 0, countNoCapture = 0;
    private byte idTeam = 0;

    public Turno() {}


    int getCount() {

        return count;

    }

    int getCountNoCapture() {

        return this.countNoCapture;

    }

    byte getIdTeam() {

        return idTeam;

    }

    void addCount() {

        this.count++;

        if (count % 2 == 0) {

            this.idTeam = 0;

        } else {

            this.idTeam = 1;

        }

    }

    void addCountNoCapture() {

        countNoCapture++;

    }


    void resetCount() {

        this.count = 0;

        this.idTeam = 1;

    }

    void resetCountNoCapture() {

        countNoCapture = 0;

    }

}

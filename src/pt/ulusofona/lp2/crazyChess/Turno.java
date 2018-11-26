package pt.ulusofona.lp2.crazyChess;

class Turno {

    private int count = 0, countNoCapture = 0;
    private byte idTeam = 1;

    Turno() {}


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

            this.idTeam = 1;

        } else {

            this.idTeam = 0;

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

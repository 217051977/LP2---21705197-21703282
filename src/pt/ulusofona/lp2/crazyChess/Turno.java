package pt.ulusofona.lp2.crazyChess;

class Turno {

    private int count = 0;
    private byte idTeam;

    Turno() {}


    int getCount() {

        return count;

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

    void resetCount() {

        this.count = 0;

        this.idTeam = 1;

    }

}

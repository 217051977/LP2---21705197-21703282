
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

        count++;

        if (count % 2 == 0) {

            idTeam = 0;

        } else {

            idTeam = 1;

        }

    }

    boolean removeCount() {

        if (count > 0) {

            this.count--;

            if (idTeam == 1) {

                idTeam = 0;

            } else {

                idTeam = 1;

            }

            return true;

        }

        return false;

    }

    void addCountNoCapture() {

        countNoCapture++;

    }

    void removeCountNoCapture() {

        if (countNoCapture > 0) {

            countNoCapture--;

        }

    }

    void resetCount() {

        count = countNoCapture = idTeam = 0;

    }

    void resetCountNoCapture() {

        countNoCapture = 0;

    }

}
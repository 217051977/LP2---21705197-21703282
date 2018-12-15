
package pt.ulusofona.lp2.crazyChess;

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

    public void addCount() {

        count++;

        if (count % 2 == 0) {

            idTeam = 10;

        } else {

            idTeam = 20;

        }

    }

    public boolean removeCount() {

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

    public void addCountNoCapture() {

        countNoCapture++;

    }

    public void removeCountNoCapture() {

        if (countNoCapture > 0) {

            countNoCapture--;

        }

    }

    public void resetCount() {

        count = countNoCapture = idTeam = 0;

    }

    public void resetCountNoCapture() {

        countNoCapture = 0;

    }

    @Override
    public String toString() {

        return "Team ID: " + idTeam +
                "\nCount without captures: " + countNoCapture +
                "\nShift counter: " + count;

    }
}
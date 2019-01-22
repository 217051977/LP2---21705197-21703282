package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class ValidPlay implements Comparable<ValidPlay>{

    private int xDestiny;
    private int yDestiny;
    private int points;

    ValidPlay(int xDestiny, int yDestiny, int points) {

        this.xDestiny = xDestiny;
        this.yDestiny = yDestiny;
        this.points = points;

    }

    public int getxDestiny() {

        return xDestiny;

    }

    public int getyDestiny() {

        return yDestiny;

    }

    public int getPoints() {

        return points;

    }

    @Override
    public int compareTo(ValidPlay validPlay) {

        return points - validPlay.getPoints();
//        if ((validPlay) -> validPlay.get)
/*
        if ((validPlay.getPoints() > points || ((validPlay.getPoints() == 0) && points != 0))) {

            return 1;

        }

        return 0;

    */

    }

    @Override
    public String toString() {

        return xDestiny + ", " + yDestiny + ", " + points;

    }


}

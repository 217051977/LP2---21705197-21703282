package pt.ulusofona.lp2.crazyChess;

import java.util.List;

public class ValidPlay implements Comparable<ValidPlay>{

    private int xDestiny;
    private int yDestiny;
    private int points;

    ValidPlay(){}

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

    }

    @Override
    public String toString() {

        return xDestiny + ", " + yDestiny/* + ", " + points + "/n"*/;

    }


}

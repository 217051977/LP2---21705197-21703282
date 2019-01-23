package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.stream.Stream;

public class ValidPlay implements Comparable<ValidPlay>{

    private int xDestiny;
    private int yDestiny;
    private int points;
    private String relativeValue;

    ValidPlay(int xDestiny, int yDestiny, int points) {

        this.xDestiny = xDestiny;
        this.yDestiny = yDestiny;
        this.points = points;
        this.relativeValue = "0";

    }

    ValidPlay(int xDestiny, int yDestiny, int points, String relativeValue) {

        this.xDestiny = xDestiny;
        this.yDestiny = yDestiny;
        this.points = points;
        this.relativeValue = relativeValue;

    }

    public int getxDestiny() {

        return xDestiny;

    }

    public int getyDestiny() {

        return yDestiny;

    }

    private int getPoints() {

        return points;

    }

    public String getRelativeValue() {

        return relativeValue;

    }

    @Override
    public int compareTo(ValidPlay validPlay) {

        return validPlay.getPoints()- points;

    }

    @Override
    public String toString() {

        return xDestiny + ", " + yDestiny + ", " + relativeValue ;

    }


}

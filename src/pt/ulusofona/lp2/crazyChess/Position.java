
package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class Position {

//  variables
    private Integer xActual, yActual;


//  constructor
    public Position(int xActual, int yActual) {

//      set the variable of this same class as the value received
        this.xActual = xActual;
        this.yActual = yActual;

    }


//  gets
    public Integer getxActual() {

//      return the value of the variable "xActual" of this same class
        return xActual;

    }

    public Integer getyActual() {

//      return the value of the variable "yActual" of this same class
        return yActual;

    }

//  change
    public void changeXPositionRight(int x){

        this.xActual += x;

    }

    public void changeXPositionLeft(int x){

        this.xActual -= x;

    }

    public void changeYPositionUp(int y){

        this.yActual -= y;

    }

    public void changeYPositionDown(int y){

        this.yActual += y;

    }

    public void changePosition(Position destiny) {

        xActual = destiny.getxActual();
        yActual = destiny.getyActual();

    }

//  equals
    public boolean equals(Position position) {

        return position.getxActual().equals(xActual) &&
                position.getyActual().equals(yActual);

    }

    public List<Integer> positionDifferences(Position destiny) {

        List<Integer> positionDifferences = new ArrayList<>();

        if (xActual.equals(destiny.getxActual()) && yActual.equals(destiny.getyActual())) {

            return positionDifferences;

        }

        int xDifference = destiny.getxActual() - xActual;
        positionDifferences.add(xDifference);
        int yDifference = destiny.getyActual() - yActual;
        positionDifferences.add(yDifference);

        return positionDifferences;

    }

    public List<Integer> positionDifferences_ABS(Position destiny) {

        List<Integer> positionDifferences_ABS = new ArrayList<>();

        if (xActual.equals(destiny.getxActual()) && yActual.equals(destiny.xActual)) {

            return positionDifferences_ABS;

        }

        int xDifference_ABS = Math.abs(destiny.getxActual() - xActual);
        positionDifferences_ABS.add(xDifference_ABS);
        int yDifference_ABS = Math.abs(destiny.getyActual() - yActual);
        positionDifferences_ABS.add(yDifference_ABS);

        return positionDifferences_ABS;

    }

    public List<Position> oneSquareBarrier(int boardSize) {

        List<Position> squareBarrier = new ArrayList<>();

        Position barrierPosition;

        for (int horizontal = xActual - 1; horizontal <= xActual + 1; horizontal++) {

            for (int vertical = yActual - 1; vertical <= yActual +1; vertical++) {

                if (horizontal >= 0 && horizontal < boardSize && vertical >= 0 && vertical < boardSize) {

                    barrierPosition = new Position(horizontal, vertical);
                    squareBarrier.add(barrierPosition);

                }

            }

        }

        return squareBarrier;

    }


////  toString
//    public String toString() {
//
//        return "xActual = " + xActual + " | yActual = " + yActual;
//
//    }

}
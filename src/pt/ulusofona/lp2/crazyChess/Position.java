
package pt.ulusofona.lp2.crazyChess;

public class Position {

//  variables
    private Integer xAtual, yAtual, xAnterior, yAnterior;


//  constructor
    public Position() {}

    public Position(int xAtual, int yAtual) {

//      set the variable of this same class as the value received
        this.xAtual = xAtual;
        xAnterior = xAtual;
        this.yAtual = yAtual;
        yAnterior = yAtual;

    }


//  gets
    public Integer getxAtual() {

//      return the value of the variable "xAtual" of this same class
        return xAtual;

    }

    public Integer getyAtual() {

//      return the value of the variable "yAtual" of this same class
        return yAtual;

    }

    public Integer getXAnterior() {

//      return the value of the variable "xAnterior" of this same class
        return xAnterior;

    }

    public Integer getYAnterior() {

//      return the value of the variable "yAnterior" of this same class
        return yAnterior;

    }


//  change
    public void changeXPositionRight(int x){

        this.xAtual += x;

    }

    public void changeXPositionLeft(int x){

        this.xAtual -= x;

    }

    public void changeYPositionUp(int y){

        this.yAtual += y;

    }

    public void changeYPositionDown(int y){

        this.yAtual -= y;

    }


//  reset
    public void undoPositionMoviment() {

        xAnterior = xAtual;
        yAnterior = yAtual;

    }

    public boolean equals(Position position) {

        return position.getxAtual().equals(xAtual) &&
                position.getyAtual().equals(yAtual);

    }


//  erase
    public boolean erasePosition() {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            xAtual = null;
            yAtual = null;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToErasePosition) {

//          print this message in screen
            System.out.println("Impossible to erase the position!");

//          return the value of the variable "false" of this same class
            return false;

        }

    }


//  toString
    public String toString() {

        return "xAtual = " + xAtual + " | yAtual = " + yAtual;

    }

}
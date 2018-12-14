
package pt.ulusofona.lp2.crazyChess;

class Position {

//  variables
    private Integer xAtual, yAtual, xAnterior, yAnterior;


//  constructor
    Position() {}

    Position(int xAnterior, int yAnterior) {

//      set the variable of this same class as the value received
        this.xAnterior = xAnterior;
        xAtual = xAnterior;
        this.yAnterior = yAnterior;
        yAtual = yAnterior;

    }


//  gets
    Integer getxAtual() {

//      return the value of the variable "xAtual" of this same class
        return xAtual;

    }

    Integer getyAtual() {

//      return the value of the variable "yAtual" of this same class
        return yAtual;

    }

    Integer getXAnterior() {

//      return the value of the variable "xAnterior" of this same class
        return xAnterior;

    }

    Integer getYAnterior() {

//      return the value of the variable "yAnterior" of this same class
        return yAnterior;

    }


//  change
    void changeXPositionRight(int x){

        this.xAtual += x;

    }

    void changeXPositionLeft(int x){

        this.xAtual -= x;

    }

    void changeYPositionUp(int y){

        this.yAtual += y;

    }

    void changeYPositionDown(int y){

        this.yAtual -= y;

    }


//  reset
    void undoPositionMoviment() {

        xAnterior = xAtual;
        yAnterior = yAtual;

    }

    boolean equals(Position position) {

        return position.getxAtual().equals(xAtual) &&
                position.getyAtual().equals(yAtual);

    }


//  erase
    boolean erasePosition() {

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
package pt.ulusofona.lp2.crazyChess;

class Position {

//  variables
    private Integer xAtual, yAtual, xInicial, yInicial;


//  constructor
    Position(int xInicial, int yInicial) {

        this.xInicial = xInicial;
        xAtual = xInicial;
        this.yInicial = yInicial;
        yAtual = yInicial;

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

    Integer getxInicial() {

//      return the value of the variable "xInicial" of this same class
        return xInicial;

    }

    Integer getyInicial() {

//      return the value of the variable "yInicial" of this same class
        return yInicial;

    }


//  change
    boolean chagePosition(int x, int y) {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            this.xAtual = x;
            this.yAtual = y;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to change the position!");

//          return the value of the variable "false" of this same class
            return false;

        }

    }


//  reset
    boolean resetAtualPosition() {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            xAtual = xInicial;
            yAtual = yInicial;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to change to the initial position!");

//          return the value of the variable "false" of this same class
            return false;

        }

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
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to erase the position!");

//          return the value of the variable "false" of this same class
            return false;

        }

    }

}

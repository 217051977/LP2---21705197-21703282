//sdfghjkl

package pt.ulusofona.lp2.crazyChess;

import java.awt.*;

public class Equipa {

//  variables
//  can be 1 - white - or 0 - black (starts first)-
    private int id;
    private Color cor;


//  constructor
    Equipa() {}

    Equipa(int id) {

//      set the variable of this same class as the value received
        this.id = id;

        if (id == 1) {

            this.cor = Color.BLACK;

        } else if (id == 0) {

            this.cor = Color.WHITE;

        }

    }


//  gets
    int getId() {

//      return the value of the variable "id" of this same class
        return id;

    }

    Color getCor() {

//      return the value of the variable "cor" of this same class
        return cor;

    }


//  change
    boolean changeColor(Color color) {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            this.cor = color;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to change color!");

//          return the value of the variable "false" of this same class
            return false;

        }

    }


//  toString
    public String toString() {

//      return the print stings of this class
        return "ID: " + id + "\nCor: " + cor;

    }

}
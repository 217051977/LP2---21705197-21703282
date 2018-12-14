//sdfghjkl

package pt.ulusofona.lp2.crazyChess;

import java.awt.*;

public class Equipa {
//
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


//  toString
    public String toString() {

//      return the print stings of this class
        return "ID: " + id + "\nCor: " + cor;

    }

}
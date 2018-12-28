package pt.ulusofona.lp2.crazyChess;

public class Rei extends CrazyPiece {

    Rei() {

//      set the type piece as 0 (king)
        super.type = 0;

//      set typeName as this className
        super.typeName = "Rei";
/*
//      set the relative value
        super.relativeValue = Integer.MAX_VALUE;
*/
    }


    @Override
//  toString (por acabar)
    public String toString() {

//      set a string type variable called string as the toString settings
        String string = this.id +
                " | " + this.typeName +
                " | " + "(infinito)" +
                " | " + this.idTeam +
                " | " + this.name +
                " @ ";

        string += checkPosition();

//      return the value of the variable string
        return string;

    }

}

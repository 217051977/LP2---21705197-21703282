package pt.ulusofona.lp2.crazyChess;

public class ReiGemeo extends Rei{

    ReiGemeo(){

//      set the type piece as 9 (twin king)
        super.type = 9;

//      set typeName as this className
        super.typeName = "ReiGemeo";

//      set the nPoints value
        super.nPoints = 1;

//      set the relative value
        super.relativeValue = String.valueOf(super.nPoints);

    }

}

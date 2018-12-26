package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico() {

//      set the movement of the Ponei magico type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.relativeValue = 5;

    }

    public PoneiMagico(int id, String name) {

//      set the movement of the Ponei magico type piece
        super.moveVertical = false;
        super.moveHorizontal = false;

//      set how much it can move
        super.maxMovHorizontal = 2;
        super.maxMovVertical = 2;

//      set how much it has to move
        super.minMovHorizontal = 2;
        super.minMovVertical = 2;

//      set the type piece as 2 (Ponei magico)
        super.type = 2;

//      set the relative value
        super.relativeValue = 5;

//      from the parameters received:
        //  set the piece ID
        super.id = id;

        //  set the piece name
        super.name = name;

    }

    @Override
    public List<Position> possiblesPositions(int boardSize) {

        List<Position> possiblesPositions = new ArrayList<>();
        Position suggestedPosition;
        suggestedPosition = new Position((super.position.getxActual() - 2), (super.position.getyActual() - 2));
        possiblesPositions.add(suggestedPosition);
        suggestedPosition = new Position((super.position.getxActual() + 2), (super.position.getyActual() + 2));
        possiblesPositions.add(suggestedPosition);
        suggestedPosition = new Position((super.position.getxActual() - 2), (super.position.getyActual() + 2));
        possiblesPositions.add(suggestedPosition);
        suggestedPosition = new Position((super.position.getxActual() + 2), (super.position.getyActual() - 2));
        possiblesPositions.add(suggestedPosition);



        return possiblesPositions;

    }

//  pode passar por cima de outras pecas

}

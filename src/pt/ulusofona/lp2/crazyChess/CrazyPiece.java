package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

public class CrazyPiece {

    protected String imagePNG, name;
    protected Position position;
    protected boolean moveVertical = true, moveHorizontal = true, moveDiagonal = true,
            canChangeType = false, hasChangedType = false,
            inGame = false;
    protected int maxMovHorizontal = 1, maxMovVertical = 1, minMovHorizontal = 0, minMovVertical = 0,
            id, type, idTeam, relativeValue;
    protected byte pieceId = 1;
    protected String typeName;
    protected List<Position> possiblesPositions = new ArrayList<>();


    public boolean equals(CrazyPiece thisPiece) {

        if (imagePNG.equals(thisPiece.getImagePNG())) {

            if (name.equals(thisPiece.getName())) {

                if (id == thisPiece.getId()) {

                    if (type == thisPiece.getType()) {

                        return idTeam == thisPiece.getIDTeam();

                    }

                }

            }

        }

        return false;

    }

//  Constructor
    public CrazyPiece() {

        position = new Position(-1, -1);

    }

    public CrazyPiece(int id, String name) {

//      from the parameters received:
        //  set the piece ID
        this.id = id;

        //  set the piece name
        this.name = name;

    }


//  gets
    public int getId() {

//      return the value of the variable "id" of this same class
        return id;

    }

    public String getImagePNG() {

//      return the value of the variable "imagePNG" of this same class
        return imagePNG;

    }

    public Position getPosition() {

        //System.out.println(this.position);

//      return the value of the variable "position" of this same class
        return this.position;

    }

    public int getType() {

//      return the value of the variable "tipo" of this same class
        return type;

    }

    public boolean getCanChangeType() {

//      return the value of the variable "canChangeType" of this same class
        return canChangeType;

    }

    public boolean getHasChangedType() {

//      return the value of the variable "jahasChangedType" of this same class
        return hasChangedType;

    }

    public int getIDTeam() {

//      return the value of the variable "idTeam" of this same class
        return idTeam;

    }

    public String getName() {

//      return the value of the variable "name" of this same class
        return name;

    }

    public boolean getInGame() {

        return inGame;

    }

    public byte getPieceId(){

        return pieceId;

    }

    public String getTypeName() {

        return typeName;

    }

    public void nextType() {}

    public void changePieceType(int pieceType) {}

    public List<Position> possiblesPositions(int boardSize, List<CrazyPiece> crazyPiecesInGame, Shift shift) {

        possiblesPositions.clear();

        possiblesPositions_Horizontal(boardSize);

        possiblesPositions_Vertical(boardSize);

        possiblesPositions_Diagonal(boardSize);

        possiblesPositions_RemovePosition(crazyPiecesInGame);

        return possiblesPositions;

    }

//  undo
    public void undoPieceType() {}

//  movement
    public String move(Position destiny, int boardSize, List<CrazyPiece> crazyPiecesInGame, List<CrazyPiece> crazyPiece_Removed_From_The_Game_Aux, Shift shift) {

//      Get possiblesPosition
        possiblesPositions(boardSize, crazyPiecesInGame, shift);

//      Set destinyFounded and haveScore as false
        boolean haveScore = false;

        String score = "";

//      search in every possible position
        for (Position thisPosition : possiblesPositions) {

//          If there destiny is on of them
            if (thisPosition.equals(destiny)) {

//              search in the game pieces
                for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                  if there is one in the destiny
                    if (thisPiece.getPosition().equals(destiny)) {

//                      if the team playing is
                        switch (shift.getIdTeam()) {

                            case 10 : {

                                score = "0,1,0,1";

////                              add to black team 1 white piece eaten and 1 valid play
//                                Simulador.addScoresStats(0, 1, 0, 1);

                            }
                            break;

                            case 20 : {

                                score = "1,0,1,0";

////                              add to white team 1 black piece eaten and 1 valid play
//                                Simulador.addScoresStats(1, 0, 1, 0);

                            }

                        }

//                      set CRAZYPIECE_REVOMED_FROM_THE_GAME as thisPiece
                        crazyPiece_Removed_From_The_Game_Aux.add(thisPiece);

//                      remove thisPiece from crazyPiecesInGame
//                        crazyPiecesInGame.remove(thisPiece);

//                      set haveScore as true
                        haveScore = true;

//                      leave the cycle
                        break;

                    }

                }

//              if there's no score setted
                if (!haveScore) {

//                  if the team playing is
                    switch (shift.getIdTeam()) {

                        case 10: {

                            score = "0,0,0,1";

////                          add to black team 1 white piece eaten and 1 valid play
//                            Simulador.addScoresStats(0, 0, 0, 1);

                        }
                        break;

                        case 20: {

                            score = "0,0,1,0";

////                          add to white team 1 black piece eaten and 1 valid play
//                            Simulador.addScoresStats(0, 0, 1, 0);

                        }

                    }

                }

//              change the position of thisPiece to destiny
                position.changePosition(destiny);

//              leave the cycle
                break;

            }

        }

////      if there wasn't any destiny in the possiblesPositions
//        if (!destinyFounded) {
//
////          add an invalid score to the playing team
//            Simulador.addScoresStatsInvalid();
//
////          return true
//            return false;
//
//        }

//      return false
        return score;

    }

//  suggested movement
    public boolean suggestedMove(int xDestiny, int yDestiny) {

        return true;

    }

//  set
    public void setPosition(Position position) {

        this.position = position;

    }

//  remove
    public boolean removeIamgePNG(){

//      it tries to remove the image
        try {

//          set the variable of this same class as null
            this.imagePNG = null;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to remove the image");

//          return the value of the variable "false" of this same class
            return false;

        }

    }


//  has
    public boolean hasImagePNG(String imagePNG) {

//      return true if the image received is the same of this piece
        return this.imagePNG.equals(imagePNG);

    }

    public void moveRight(int countMoves) {

        position.changeXPositionRight(countMoves);

    }

    public void moveLeft(int countMoves) {

        position.changeXPositionLeft(countMoves);

    }

    public void moveUp(int countMoves) {

        position.changeYPositionUp(countMoves);

    }

    public void moveDown(int countMoves) {

        position.changeYPositionDown(countMoves);

    }

//  change
    public void changeType() {



        hasChangedType = true;

    }

    public void undoMov(Position previousPosition) {

        this.position = previousPosition;

    }


    public void isInGame() {

        inGame = true;

    }

    public void isOutOfGame() {

        inGame = false;
        Position destiny = new Position(-1, -1);
        this.position.changePosition(destiny);

    }

//  toString (por acabar)
    public String toString() {

//      set a string type variable called string as the toString settings
        String string = this.id +
                        " | " + this.typeName +
                        " | " + this.relativeValue +
                        " | " + this.idTeam +
                        " | " + this.name +
                        " @ ";

        string += checkPosition();

//      return the value of the variable string
        return string;

    }

    public String saveFile_ToString() {

        return this.id +
                ":" + this.type +
                ":" + this.idTeam +
                ":" + this.name;

    }

    protected String checkPosition() {

        String string;
        Position isOutOfTheGame = new Position(-1, -1);

//      if actual position in x axis as the value of null OR actual position in y axis as the value of null
        if(this.position.equals(isOutOfTheGame)){

//          add this to the variable string
            string = "(n/a)";

        }else {

//          add this to the variable string
            string = "(" + this.position.getxActual() + ", " + this.position.getyActual() + ")";

        }

        return string;

    }

//  protected
    protected void possiblesPositions_Horizontal(int boardSize) {

//      left
        for (int left = position.getxActual() - minMovHorizontal - 1; left >= position.getxActual() - maxMovHorizontal; left--) {

            if (left >= 0) {

                possiblesPositions.add(createPosition_Horizontal(left));

            } else {

                break;

            }

        }

//      right
        for (int right = position.getxActual() + minMovHorizontal + 1; right < position.getxActual() + maxMovHorizontal + 1; right++) {

            if (right < boardSize) {

                possiblesPositions.add(createPosition_Horizontal(right));

            } else {

                break;

            }

        }

    }

    protected void possiblesPositions_Vertical(int boardSize) {

//      up
        for (int up = position.getyActual() - minMovVertical - 1; up >= position.getyActual() - maxMovVertical; up--) {

            if (up >= 0) {

                possiblesPositions.add(createPosition_Vertical(up));

            } else {

                break;

            }

        }

//      down
        for (int down = position.getyActual() + minMovVertical + 1; down < position.getyActual() + maxMovVertical + 1; down++) {

        if (down < boardSize) {

            possiblesPositions.add(createPosition_Vertical(down));

        } else {

            break;

        }

        }

    }

    protected void possiblesPositions_Diagonal(int boardSize) {

//      left up
        for (int left = position.getxActual() - minMovHorizontal - 1, up = position.getyActual() - minMovVertical - 1
             ; left >= position.getxActual() - maxMovHorizontal; left--, up--) {

            if (left >= 0 && up >= 0) {

                possiblesPositions.add(createPosition_Diagonal(left, up));

            }

        }

//      right down
        for (int right = position.getxActual() + minMovHorizontal + 1, down = position.getyActual() + minMovVertical + 1
             ; right <= position.getxActual() + maxMovHorizontal; right++, down++) {

            if (right < boardSize && down < boardSize) {

                possiblesPositions.add(createPosition_Diagonal(right, down));

            }

        }

//      left down
        for (int left = position.getxActual() - minMovHorizontal - 1, down = position.getyActual() + minMovVertical + 1
             ; left >= position.getxActual() - maxMovHorizontal; left--, down++) {

            if (left >= 0 && down < boardSize) {

                possiblesPositions.add(createPosition_Diagonal(left, down));

            }

        }

//      right up
        for (int right = position.getxActual() + minMovHorizontal + 1, up = position.getyActual() - minMovVertical - 1
             ; right <= position.getxActual() + maxMovHorizontal; right++, up--) {

            if (right < boardSize && up >= 0) {

                possiblesPositions.add(createPosition_Diagonal(right, up));

            }

        }

    }

    protected void possiblesPositions_RemovePosition(List<CrazyPiece> crazyPiecesInGame) {

        boolean canMoveUpLeft = true,
                canMoveDownRight = true,
                canMoveDownLeft = true,
                canMoveUpRight = true;

        boolean canMoveLeft = true,
                canMoveRight = true,
                canMoveUp = true,
                canMoveDown = true;

        List<Position> POSITIONS_TO_ERASE = new ArrayList<>();

        for (Position destiny : possiblesPositions) {

            if (destiny.equals(position)) {

                POSITIONS_TO_ERASE.add(destiny);
                continue;

            }

            List<Integer> positionsDifferences = position.positionDifferences(destiny);

//          if it moves down right
            if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) > 0) {

                canMoveDownRight = canMove(canMoveDownRight, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves up right
            else if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) < 0) {

                canMoveUpRight = canMove(canMoveUpRight, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves down left
            else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) > 0) {

                canMoveDownLeft = canMove(canMoveDownLeft, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves up left
            else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) < 0) {

                canMoveUpLeft = canMove(canMoveUpLeft, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves right
            else if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) == 0) {

                canMoveRight = canMove(canMoveRight, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves left
            else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) == 0) {

                canMoveLeft = canMove(canMoveLeft, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves up
            else if (positionsDifferences.get(0) == 0 && positionsDifferences.get(1) < 0) {

                canMoveUp = canMove(canMoveUp, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }
//          if it moves down
            else if (positionsDifferences.get(0) == 0 && positionsDifferences.get(1) > 0) {

                canMoveDown = canMove(canMoveDown, POSITIONS_TO_ERASE, destiny, crazyPiecesInGame);

            }

        }

        possiblesPositions.removeAll(POSITIONS_TO_ERASE);

    }

    private boolean canMove(boolean canMove, List<Position> POSITIONS_TO_ERASE, Position destiny, List<CrazyPiece> crazyPiecesInGame) {

        if (canMove) {

            for (CrazyPiece thisPiece : crazyPiecesInGame) {

                if (destiny.equals(thisPiece.getPosition())) {

                    canMove = false;

                    if (thisPiece.getIDTeam() == idTeam) {

                        POSITIONS_TO_ERASE.add(destiny);

                    }

                    break;

                }

            }

        } else {

            POSITIONS_TO_ERASE.add(destiny);

        }

        return canMove;

    }

    protected Position createPosition_Diagonal(int horizontal, int vertical) {

        return new Position(horizontal, vertical);

    }

    protected Position createPosition_Horizontal(int horizontal) {

        return new Position(horizontal, position.getyActual());

    }

    protected Position createPosition_Vertical(int vertical) {

        return new Position(position.getxActual(), vertical);

    }

}
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
    protected List<Position> possiblesPositions = new ArrayList<>();


//  Constructor
    public CrazyPiece() {

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

    public int getMinMovHorizontal() {

        return minMovHorizontal;

    }

    public int getMinMovVertical() {

        return minMovVertical;

    }

    public int getMaxMovVertical() {

        return maxMovVertical;

    }

    public int getIdTeam() {

        return idTeam;

    }

    public int getMaxMovHorizontal() {

        return maxMovHorizontal;

    }

    public boolean getMovVertical() {

        return moveVertical;

    }

    public boolean getMoveHorizontal() {

        return moveHorizontal;

    }

    public boolean getMoveDiagonal() {

        return moveDiagonal;

    }

    public byte getPieceId(){

        return pieceId;

    }

    public void changePieceType() {

        if (pieceId == 6) {

            pieceId = 1;

        } else {

            pieceId++;

        }

    }

    public List<Position> possiblesPositions(int boardSize) {

        possiblesPositions.removeAll(possiblesPositions);

        possiblesPositions_Horizontal(boardSize);

        possiblesPositions_Vertical(boardSize);

        possiblesPositions_Diagonal(boardSize);

        possiblesPositions_RemovePosition();

        return possiblesPositions;

    }

//  undo
    public void undoPieceType() {

        if (pieceId != 1) {

            pieceId--;

        } else {

            pieceId = 6;

        }

    }

//  movement
    public boolean move(int xDifference, int yDifference) {

        int xPosition = position.getxActual();
        int yPosition = position.getyActual();

        if (xDifference != 0 && yDifference != 0 &&
                Math.abs(xDifference) == Math.abs(yDifference)) {

            if (xDifference > 0) {

                moveRight(xDifference);

            } else {

                moveLeft(xDifference);

            }

            if (yDifference > 0) {

                moveDown(yDifference);

            } else {

                moveUp(yDifference);

            }

        } else if (xDifference != xPosition && yDifference == yPosition) {

            if (xDifference > 0) {

                moveRight(xDifference);

            } else {

                moveLeft(xDifference);

            }

        } else if (xDifference == xPosition && yDifference != yPosition) {

            if (yDifference > 0) {

                moveDown(yDifference);

            } else {

                moveUp(yDifference);

            }

        } else {

            return false;

        }

        return true;

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


//  move

    public boolean move(Position destiny, int boardSize) {

        possiblesPositions.removeAll(possiblesPositions);

        possiblesPositions(boardSize);

        for (Position thisPosition : possiblesPositions) {

            if (destiny.equals(thisPosition)) {

                position.changePosition(destiny);

                return true;
            }

        }

        return false;

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


    public void isInGame() {

        inGame = true;

    }

    public void isOutOfGame() {

        inGame = false;

    }

//  toString (por acabar)
    public String toString() {

//      set a string type variable called string as the toString settings
        String string = id +
                        " | " + type +
                        " | " + idTeam +
                        " | " + name +
                        " @ ";

//      if actual position in x axis as the value of null OR actual position in y axis as the value of null
        if(position.getxActual() == null || position.getyActual() == null){

//          add this to the variable string
            string += "(n/a)";

        }else {

//          add this to the variable string
            string += "(" + position.getxActual() + ", " + position.getyActual() + ")";

        }

//      return the value of the variable string
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

    protected void possiblesPositions_RemovePosition() {

        boolean canMoveUpLeft = true,
                canMoveDownRight = true,
                canMoveDownLeft = true,
                canMoveUpRight = true;

        boolean canMoveLeft = true,
                canMoveRight = true,
                canMoveUp = true,
                canMoveDown = true;

        List<Position> POSITIONS_TO_ERASE = new ArrayList<>();

        for (CrazyPiece thisPiece : Simulador.crazyPiecesInGame) {

            for (Position destiny : possiblesPositions) {

                if (destiny.equals(position)) {

                    possiblesPositions.remove(destiny);
                    continue;

                }

                List<Integer> positionsDifferences = position.positionDifferences(destiny);

                if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) > 0) {

                    canMoveDownRight = canMove(canMoveDownRight, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) < 0) {

                    canMoveUpRight = canMove(canMoveUpRight, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) > 0) {

                    canMoveDownLeft = canMove(canMoveDownLeft, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) < 0) {

                    canMoveUpLeft = canMove(canMoveUpLeft, POSITIONS_TO_ERASE, thisPiece, destiny);

                }

                else if (positionsDifferences.get(0) > 0 && positionsDifferences.get(1) == 0) {

                    canMoveRight = canMove(canMoveRight, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) < 0 && positionsDifferences.get(1) == 0) {

                    canMoveLeft = canMove(canMoveLeft, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) == 0 && positionsDifferences.get(1) < 0) {

                    canMoveUp = canMove(canMoveUp, POSITIONS_TO_ERASE, thisPiece, destiny);

                } else if (positionsDifferences.get(0) == 0 && positionsDifferences.get(1) > 0) {

                    canMoveDown = canMove(canMoveDown, POSITIONS_TO_ERASE, thisPiece, destiny);

                }

            }

        }

        possiblesPositions.removeAll(POSITIONS_TO_ERASE);

    }

    private boolean canMove(boolean canMove, List<Position> POSITIONS_TO_ERASE, CrazyPiece thisPiece, Position destiny) {
        if (canMove) {

            if (destiny.equals(thisPiece.getPosition())) {

                canMove = false;

                if (thisPiece.getIDTeam() == idTeam) {

                    POSITIONS_TO_ERASE.add(destiny);

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
package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {

    protected String imagePNG, name;
    private Position position;
    protected boolean moveCima = true, moveBaixo = true, moveEsquerda = true, moveDireita = true,
            moveCimaDireita = true, moveCimaEsquerda = true, moveBaixoDireita = true, moveBaixoEsquerda = true,
            canChangeType = false, hasChangedType = false,
            inGame = false;
    protected int maxMovHorizontal = 1, maxMovVertical = 1, minMovHorizontal = 0, minMovVertical = 0,
            id, type, idTeam, valorRelativo;


//  Constructor
    public CrazyPiece() {

//      set the piece image as unknown because we don't know witch team it belongs to
        imagePNG = "unknown-piece.png";

    }

    public CrazyPiece(int id, String name) {

//      from the parameters received:
        //  set the piece ID
        this.id = id;

        //  set the piece name
        this.name = name;

//      set the piece image as unknown because we don't know witch team it belongs to
        imagePNG = "unknown-piece.png";

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

    public boolean getMoveCima() {

        return moveCima;

    }

    public boolean getMoveBaixo() {

        return moveBaixo;

    }

    public boolean getMoveEsquerda() {

        return moveEsquerda;

    }

    public boolean getMoveDireita() {

        return moveDireita;

    }

    public boolean getMoveBaixoDireta() {

        return moveBaixoDireita;

    }

    public boolean getMoveBaixoEsquerda() {

        return moveBaixoEsquerda;

    }

    public boolean getMoveCimaDireita() {

        return moveCimaDireita;

    }

    public boolean getMoveCimaEsquerda() {

        return moveCimaEsquerda;

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
        if(position.getxAtual() == null || position.getyAtual() == null){

//          add this to the variable string
            string += "(n/a)";

        }else {

//          add this to the variable string
            string += "(" + position.getxAtual() + ", " + position.getyAtual() + ")";

        }

//      return the value of the variable string
        return string;
    }

}
package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {

    private int ID;
    private String imagePNG = null;
    private Position position;
    private Tipo tipo;
    private boolean podeMudarTipo, mudouTipo = false;
    private Equipa team;
    private String nome;


//  Constructor
    public CrazyPiece() {}

    public CrazyPiece(int ID, Tipo tipo, Equipa team, String nome) {

//      set the variable of this same class as the value received
        this.ID = ID;
        this.tipo = tipo;
        this.team = team;
        this.nome = nome;

        switch (team.getId()) {

            case 0: {

                imagePNG = "crazy_emoji_white.png";

            }break;
            case 1: {

                imagePNG = "crazy_emoji_black.png";

            }

        }

    }


//  gets
    public int getId() {

//      return the value of the variable "id" of this same class
        return ID;

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

    public Tipo getTipo() {

//      return the value of the variable "tipo" of this same class
        return tipo;

    }

    public boolean getPodeMudarTipo() {

//      return the value of the variable "podeMudarTipo" of this same class
        return podeMudarTipo;

    }

    public boolean getMudouTipo() {

//      return the value of the variable "jaMudouTipo" of this same class
        return mudouTipo;

    }

    public Equipa getTeam() {

//      return the value of the variable "idTeam" of this same class
        return team;

    }

    public String getNome() {

//      return the value of the variable "nome" of this same class
        return nome;

    }


//  set
    public void setImagePNG(String imagePNG) {

//      it tries to set image
        try {

//          if the string received doesn't have .png in the end
            if (imagePNG.toLowerCase().charAt(imagePNG.length() - 4) != '.') {

                imagePNG += ".png";

//          if the 4 last letters are different from ".png"
            } else if (imagePNG.toLowerCase().charAt(imagePNG.length() - 3) != 'p' ||
                    imagePNG.toLowerCase().charAt(imagePNG.length() - 2) != 'n' ||
                    imagePNG.toLowerCase().charAt(imagePNG.length() - 1) != 'g') {

//              throws an arithmetic Exception with this message
                throw new ArithmeticException("Format not valid!");

            }



//          set the variable of this same class as the value received
            this.imagePNG = imagePNG;

//      if there is any problem in the above code
        } catch (ArithmeticException formatNotValid) {

            System.out.println(formatNotValid.getMessage());

        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to remove the image");

        }

    }

    public void setPosition(Position position) {

        this.position = position;

    }

    public void setPodeMudarTipo(boolean podeMudarTipo) {

        this.podeMudarTipo = podeMudarTipo;

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


//  change
    public boolean changeType(Tipo tipo) {

//      if this piece can change type AND it hasn't changed yet
        if (podeMudarTipo && !mudouTipo && tipo.changeTipo(tipo)){

//          set this class variable "mudouTipo" as true
            this.mudouTipo = true;

//          return true
            return true;

        }

//      else return false
        return false;

    }

    public boolean changeName(String nome) {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            this.nome = nome;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeName) {

//          print this message in screen
            System.out.println("Impossible to change the name");

//          return the value of the variable "false" of this same class
            return false;

        }

    }


//  move
    public void moveRight(int countMoves) {

        position.changeXPositionRight(countMoves);

    }

    void moveLeft(int countMoves) {

        position.changeXPositionLeft(countMoves);

    }

    void moveUp(int countMoves) {

        position.changeYPositionUp(countMoves);

    }

    void moveDown(int countMoves) {

        position.changeYPositionDown(countMoves);

    }


//  toString (por acabar)
    public String toString() {

//      set a string type variable called string as the toString settings
        String string = ID +
                        " | " + tipo.getid() +
                        " | " + team.getId() +
                        " | " + nome +
                        " | ";

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

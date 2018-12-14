package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {

    private int iD;
    private String imagePNG = null;
    private Position position;
    private Tipo tipo;
    private boolean podeMudarTipo, mudouTipo = false, emJogo = false;
    private int idTeam;
    private String nome;


//  Constructor
    public CrazyPiece() {}

    public CrazyPiece(int iD, Tipo tipo, int idTeam, String nome) {

//      set the variable of this same class as the value received
        this.iD = iD;
        this.tipo = tipo;
        this.idTeam = idTeam;
        this.nome = nome;

        switch (idTeam) {

            case 0: {

                imagePNG = "crazy_emoji_black.png";

            }break;
            case 1: {

                imagePNG = "crazy_emoji_white.png";

            }

        }

    }


//  gets
    public int getId() {

//      return the value of the variable "id" of this same class
        return iD;

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

    public int getIDTeam() {

//      return the value of the variable "idTeam" of this same class
        return idTeam;

    }

    public String getNome() {

//      return the value of the variable "nome" of this same class
        return nome;

    }

    public boolean getEmJogo() {

        return emJogo;

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

    void moveLeft(int countMoves) {

        position.changeXPositionLeft(countMoves);

    }

    void moveUp(int countMoves) {

        position.changeYPositionUp(countMoves);

    }

    void moveDown(int countMoves) {

        position.changeYPositionDown(countMoves);

    }


    public void estaEmJogo() {

        emJogo = true;

    }

    public void estaForaDeJogo() {

        emJogo = false;

    }

    //  toString (por acabar)
    public String toString() {

//      set a string type variable called string as the toString settings
        String string = iD +
                        " | " + tipo.getid() +
                        " | " + idTeam +
                        " | " + nome +
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
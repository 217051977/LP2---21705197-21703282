package pt.ulusofona.lp2.crazyChess;

import java.awt.*;

public class Equipa {

    private int id;
    private String nome;
    private Color cor = Color.RED;

    Equipa(int id, String nome) {

        this.id = id;
        this.nome = nome;

    }

    int getId() {

        return id;

    }

    String getNome() {

        return nome;

    }

    Color getCor() {

        return cor;

    }

    boolean setColor(Color color) {

        try {

            this.cor = color;

            return true;

        } catch (Exception impossibleToAddAColor) {

            System.out.println("Impossible to add a color");

            return false;

        }

    }

    boolean resetColor() {

        try {

            this.cor = Color.RED;

            return true;

        } catch (Exception impossibleToResetColor) {

            System.out.println("Impossible to reset the color!");

            return false;

        }

    }

    boolean changeColor(Color color) {

        try {

            this.cor = color;

            return true;

        } catch (Exception impossibleToChangeColor) {

            System.out.println("Impossible to change color!");

            return false;

        }

    }

    boolean changeName(String nome) {

        try {

            this.nome = nome;

            return true;

        } catch (Exception impossibleToChangeName) {

            System.out.println("Impossible to change the name");

            return false;

        }

    }

    public String toString() {

        return "ID: " + id + "\nNome: " + nome + "\nCor: " + cor;

    }
}

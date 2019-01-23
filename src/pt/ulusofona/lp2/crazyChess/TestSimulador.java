package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TestSimulador {
//
//    @Test
//    public void cenas() {
//        List<Position> possiblesPositions = new ArrayList<>();
//
//        Position destiny = new Position(1,2);
//        possiblesPositions.add(destiny);
//        possiblesPositions.add(new Position(1, 7));
//        possiblesPositions.add(new Position(1, 6));
//        possiblesPositions.add(new Position(1, 5));
//        possiblesPositions.add(new Position(1, 4));
//        possiblesPositions.add(new Position(1, 3));
//
//        long destinyExistes = possiblesPositions.stream().filter((thisPosition) -> (thisPosition.equals(destiny))).count();
//        assertTrue(destinyExistes == 1);
//
//    }

//  Main
    @Test
    public void testMain() {
        Main main = new Main();
        Main main1 = new Main();
        assertTrue("The both Main class must NOT be equal!", !main1.equals(main));
    }

//  Board Size
    @Test
    public void testGetBoardSize() {
        Simulador simulador = new Simulador(4);
        assertEquals("The board size is not correct!", 4, simulador.getTamanhoTabuleiro());
    }

//  Crazy Pieces
    @Test
    public void testGetCrazyPieces() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black(1, 0, 0, simulador);
        createCrazyPiece_King_White(2, 0, 0, simulador);

        assertEquals("Those pieces wasn't the right ones!", simulador.crazyPiecesInGame, simulador.getPecasMalucas());
    }

//  Authors
    @Test
    public void testGetAuthors() {
        Simulador simulador = new Simulador();
        List<Comparable> authors = new ArrayList<>();
        authors.add("Bruno Miguel Dias Leal, nº 21705197");
        authors.add("João Domingos, nº 21703282");
        assertEquals("That wasn't the right author!", authors, simulador.getAutores());
    }

//  Move Up
    @Test
    public void testMoveUp() {
        CrazyPiece piece = new ReiPreto(1, "Black");
        piece.isInGame();
        Position origin = new Position(0, 1);
        piece.setPosition(origin);
        piece.moveUp(1);
        int destiny = piece.getPosition().getyActual();
        assertEquals("Should be 0,0 position!", 0, destiny);
    }

//  Move Down
    @Test
    public void testMoveDown() {
        CrazyPiece piece = new ReiPreto(1, "Black");
        piece.isInGame();
        Position origin = new Position(0, 1);
        piece.setPosition(origin);
        piece.moveDown(1);
        int destiny = piece.getPosition().getyActual();
        assertEquals("Should be 0,0 position!", 2, destiny);
    }

//  Move Left
    @Test
    public void testMoveLeft() {
        CrazyPiece piece = new ReiPreto(1, "Black");
        piece.isInGame();
        Position origin = new Position(0, 1);
        piece.setPosition(origin);
        piece.moveLeft(1);
        int destiny = piece.getPosition().getxActual();
        assertEquals("Should be 0,0 position!", -1, destiny);
    }

//  Move Right
    @Test
    public void testMoveRight() {
        CrazyPiece piece = new ReiPreto(1, "Black");
        piece.isInGame();
        Position origin = new Position(0, 1);
        piece.setPosition(origin);
        piece.moveRight(1);
        int destiny = piece.getPosition().getxActual();
        assertEquals("Should be 0,0 position!", 1, destiny);
    }


//  Number Of Pieces Captured
    @Test
    public void testGetNumberOfWhitePiecesCaptured() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame(1, 0, 1, simulador);
        createCrazyPiece_King_Black_PresentInGame(2, 0, 0, simulador);
        simulador.processaJogada(0,0,0,1);
        assertEquals("Should exist 1 white piece captured!", 1, simulador.getNumberOfWhitePiecesCaptured());
    }

    @Test
    public void testGetNumberOfBlackPiecesCaptured() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_Black_PresentInGame(7, 0, 0, simulador);
        createCrazyPiece_King_White_PresentInGame(8, 0, 1, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Should exist 1 black piece captured!", 1, simulador.getNumberOfBlackPiecesCaptured());
    }

//  Number Of Invalid Plays
    //  White
    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White(1, 0, 0, simulador);
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 1, simulador);
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 4, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 4, simulador);
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_Correct() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 1, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    //  Black
    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 0, simulador);
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1, simulador);
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 2, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 4, simulador);
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_Correct() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

//  Number Of Valid Plays
    //  White
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 0, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 2, simulador);
        simulador.processaJogada(0,2,0,3);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

//  Black
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 0, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1, simulador);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByBlackTeam());
    }

//  Score
    @Test
    public void testGetScore() {
        Simulador simulador = new Simulador();
        List<Comparable> list = new ArrayList<>();
        list.add("JOGO DE CRAZY CHESS");
        list.add("Resultado: " + "EMPATE");
        list.add("---");
        list.add("Equipa das Pretas");
        list.add(" Capturas: " + 0);
        list.add(" Jogadas válidas: " + 0);
        list.add(" Tentativas inválidas: " + 0);
        list.add("Equipa das Brancas");
        list.add(" Capturas: " + 0);
        list.add(" Jogadas válidas: " + 0);
        list.add(" Tentativas inválidas: " + 0);
        assertEquals("Should be" +
                "\"JOGO DE CRAZY CHESS\n" +
                "Resultado: \"EMPATE\"\n" +
                "---\n" +
                "Equipa das Pretas\n" +
                "Capturas: 0\n" +
                "Jogadas válidas: 0\n" +
                "Tentativas inválidas : 0\n" +
                "Equipa das Brancas\n" +
                "Capturas: 0\n" +
                "Jogadas válidas: 0\n" +
                "Tentativas inválidas: 0\"!", list, simulador.getResultados());
    }

//  ID Piece
    @Test
    public void testGetIDPiece_Exists() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black( 1, 0, 0, simulador);
        assertEquals("Should be \"1\"!", 1, simulador.getIDPeca(0, 0));
    }

    @Test
    public void testGetIDPiece_DoesntExists() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black( 1, 0, 0, simulador);
        assertEquals("Should be \"0\"!", 0, simulador.getIDPeca(0, 1));
    }

//  This Shift Team Id
    @Test
    public void testGetThisShiftTeamID() {
        Simulador simulador = new Simulador();
        assertEquals("Should be \"10\"!", 10, simulador.getIDEquipaAJogar());
    }

//  Shift
    @Test
    public void testGetShift() {
        Simulador simulador = new Simulador();
        Shift shift = new Shift();
        assertEquals("Should be:\n Team ID: 10\nCount without captures: 0\nShift counter: 0", shift.toString(), simulador.getShift().toString());
    }

//  First Capture
    //  White
    @Test
    public void testGetFirstCapture_True_AsWhite() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White_PresentInGame( 1, 1, 1, simulador);
        createCrazyPiece_King_Black_PresentInGame( 2, 0, 0, simulador);
        simulador.processaJogada(1,1,0,0);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsWhite() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        createCrazyPiece_King_White( 1, 0, 0, simulador);
        createCrazyPiece_King_Black( 3, 1, 1, simulador);
        simulador.processaJogada(1,1,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

    //  Black
    @Test
    public void testGetFirstCapture_True_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame( 1, 1, 1, simulador);
        createCrazyPiece_King_Black_PresentInGame( 2, 0, 0, simulador);
        simulador.processaJogada(0,0,1,1);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 1, 1, 1, simulador);
        createCrazyPiece_King_Black( 2, 0, 0, simulador);
        simulador.processaJogada(0,0,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

//  Start Game
    @Test
    public void testStartGame_FileNotFounded() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_NOT_FOUNDED.txt");
        try {
            simulador.iniciaJogo(file);
            fail("The file is missing!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Empty() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_EMPTY.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's no info in it!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_SIZE.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board size or numbers of pieces are missing!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Lower_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_LOWER_BOARD_SIZE.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board Size is lower than the layout or is too much info on the layout it self!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Higher_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_HIGHER_BOARD_SIZE.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board Size is higher than the layout or is missing info on the layout it self!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_NUMBER_OF_PIECES.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board size or numbers of pieces are missing!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Lower_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_LOWER_NUMBER_OF_PIECES.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board Size is lower than the layout or is too much info on the layout it self!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Higher_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_HIGHER_NUMBER_OF_PIECES.txt");
        try {
            simulador.iniciaJogo(file);
            fail("Board Size is higher than the layout or is missing info on the layout it self!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Pieces_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_LINE_AND_COLUMN.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Pieces_Line() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_LINE.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Pieces_Column() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_COLUMN.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Board_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_LINE_AND_COLUMN.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Board_Line() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_LINE.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Missing_Board_Column() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_COLUMN.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_2_Pieces_With_The_Same_Id() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_2_PIECES_WITH_THE_SAME_ID.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartGame_Saved_Game() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_GAME_SAVED.txt");
        try {
            simulador.iniciaJogo(file);
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
            fail("Should be able to run this saveFile!");
        }
        assertEquals("The playing team should be 20", 20, simulador.shift.getIdTeam());
        assertEquals("The shift count should be 30", 30, simulador.shift.getCount());
        assertEquals("The number of valid plays by black team should be 15", 15, simulador.getNumberOfValidPlaysByBlackTeam());
        assertEquals("The number of white pieces captured should be 2", 2, simulador.getNumberOfWhitePiecesCaptured());
        assertEquals("The number of invalid plays by black team should be 1", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
        assertEquals("The number of valid plays by white team should be 15", 15, simulador.getNumberOfValidPlaysByWhiteTeam());
        assertEquals("The number of black pieces captured should be 2", 2, simulador.getNumberOfBlackPiecesCaptured());
        assertEquals("The number of invalid plays by white team should be 3", 3, simulador.getNumberOfInvalidPlaysByWhiteTeam());
        searchForAJoker(simulador);
        System.out.println(simulador.getResultados());
    }

    @Test
    public void testStartGame_Correct() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/RUN_FILE.txt");
        try {
            simulador.iniciaJogo(file);
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
            fail("Should work!");
        }
        searchForAJoker(simulador);
    }

    @Test
    public void testStartGame_Correct_All_Pieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/RUN_FULL_FILE.txt");
        try {
            simulador.iniciaJogo(file);
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
            fail("Should work!");
        }
    }

    @Test
    public void testStartGame_Priest_Next_To_Queen() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_PRIS_NEXT_TO_QUEEN.txt");
        try {
            simulador.iniciaJogo(file);
            fail("There's missing info on the layout it self or board Size is higher than the layout!");
        } catch (InvalidSimulatorInputException e) {
            e.printStackTrace();
        }
    }

//  Execute The Move
    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Up() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 1, simulador);
        assertTrue("You can move the piece!", simulador.processaJogada(0,1,0,0));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Down() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 0, simulador);
        assertTrue("You can move the piece!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Left() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 1, 0, simulador);
        assertTrue("You can move the piece!", simulador.processaJogada(1,0,0,0));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Right() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 0, simulador);
        assertTrue("You can move the piece!", simulador.processaJogada(0,0,1,0));
    }

    @Test
    public void testExecuteTheMove_MoveEnemyPiece() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame( 1, 0, 0, simulador);
        assertFalse("You can only move your teams pieces!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame( 1, 0, 0, simulador);
        assertFalse("You can't move to the same position!", simulador.processaJogada(0,0,0,0));
    }

    @Test
    public void testExecuteTheMove_Already_PieceOfTheSameTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame( 1, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame( 2, 1, 0, simulador);
        assertFalse("There's already a piece of this Team!", simulador.processaJogada(0,0,1,0));
    }

    @Test
    public void testExecuteTheMove_MoveToAnOutBoardHouse() {
        Simulador simulador = new Simulador(4);
        assertFalse("You can't move a piece to an out of the board house!", simulador.processaJogada(0,0,0,-1));
    }

    @Test
    public void testExecuteTheMove_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame( 1, 0, 0, simulador);
        assertFalse("There's no piece there!", simulador.processaJogada(0,1,0,2));
    }

    @Test
    public void testExecuteTheMove_MoveFromOutOfTheBoard() {
        Simulador simulador = new Simulador(4);
        assertFalse("You can't move a piece form out of the board!", simulador.processaJogada(0,-1,0,2));
    }

    @Test
    public void testExecuteTheMove_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame( 1, 0, 0, simulador);
        assertFalse("You can't move so many hoses!", simulador.processaJogada(0,0,0,2));
    }

    @Test
    public void testExecuteTheMove_MovePony_With_EnemyPieceInTheWay_NotKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(2, 2, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(2, 1, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertTrue("The Pony can jump over a non king piece!", simulador.processaJogada(2,2,0,0));
    }

    @Test
    public void testExecuteTheMove_MovePony_With_EnemyPieceInTheWay_King() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(2, 2, simulador);
        createCrazyPiece_King_White_PresentInGame(2, 2, 1, simulador);
        createCrazyPiece_King_Black_PresentInGame(3, 1, 2, simulador);
        assertFalse("The Pony can't jump over a king piece!", simulador.processaJogada(2,2,0,0));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_With_EnemyPieceInTheWay_Up() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Bunny_Black(1, 1, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,0));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_With_Up_Valid() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Bunny_Black(1, 0, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,1));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_With_EnemyPieceInTheWay_Up_And_Down() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(1, 3, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(1, 1, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,0));
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,4));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_WithUp_And_Down_Valid() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(1, 4, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(1, 0, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,1));
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,3));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_With_EnemyPieceInTheWay_Down() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(1, 3, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,4));
    }

    @Test
    public void testExecuteTheMove_MoveTowerV_With_Down_Valid() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(1, 4, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        assertFalse("It has a a piece in the way!", simulador.processaJogada(1,2,1,3));
    }

    @Test
    public void testExecuteTheMove_WhitePiece_After_BlackPieceMove() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame_Pony_Black(2, 2, simulador);
        createCrazyPiece_King_White_PresentInGame(2, 2, 1, simulador);
        createCrazyPiecePresentInGame_TowerV_White(simulador);
        createCrazyPiece_King_Black_PresentInGame(3, 1, 2, simulador);
        simulador.processaJogada(2, 2, 4, 4);
        assertFalse("The white piece should be able to move!", simulador.processaJogada(2,1,2,2));
    }

    @Test
    public void testExecuteTheMove_MovePony_AboveKing_After_UndoAndMove_ThatSameKing() {
        Simulador simulador = new Simulador(11);

        CrazyPiece pony = new PoneiMagicoPreto(1, "White");
        Position ponyPosition = new Position(2, 0);
        pony.setPosition(ponyPosition);
        simulador.crazyPiecesInGame.add(pony);
        createCrazyPiece_King_Black_PresentInGame(2, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame(3, 0, 1, simulador);
        createCrazyPiece_King_White_PresentInGame(4, 10, 0, simulador);
        assertTrue("Should be able to move", simulador.processaJogada(0, 1, 1, 2));
        simulador.anularJogadaAnterior();
        assertTrue("Should be able to move!", simulador.processaJogada(0, 1, 1, 2));
        assertTrue("Should be able to move!", simulador.processaJogada(10, 0, 10, 1));
        assertFalse("Shouldn't be able to move!", simulador.processaJogada(2, 0, 0, 2));
    }

    @Test
    public void testExecuteTheMove_MovePriest_CloseToAQueen_Valid() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/RUN_FILE_2.txt");
        try {

            simulador.iniciaJogo(file);

        } catch (InvalidSimulatorInputException e) {

            e.printStackTrace();

        }
        assertTrue("You can move the piece!", simulador.processaJogada(2,3,0,1));
        assertTrue("You can move the piece!", simulador.processaJogada(4,5,4,0));
        assertTrue("You can move the piece!", simulador.processaJogada(0,1, 1,2));
        assertTrue("You can move the piece!", simulador.processaJogada(4,0, 4,5));
        assertFalse("You can't move the piece!", simulador.processaJogada(1,2, 3,4));
        assertTrue("You can move the piece!", simulador.processaJogada(1,2, 2,3));
    }

//  Game Over
    @Test
    public void testGameOver_NoPieces() {
        Simulador simulador = new Simulador(4);
        assertTrue("White team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_10ShiftsWithoutAnyCapture_And_NoFirstCapture() {
        Simulador simulador = new Simulador(4);
        simulador.shift.countNoCapture = 10;
        createCrazyPiece_King_White_PresentInGame( 3, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame( 4, 1, 0, simulador);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_2KingsInGame_And_1OutOfGame() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 2, 3, 4, simulador);
        createCrazyPiece_King_White_PresentInGame( 3, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame( 4, 1, 0, simulador);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_10ShiftsWithoutAnyCapture_And_FirstCapture() {
        Simulador simulador = new Simulador(4);
        simulador.shift.countNoCapture = 10;
        simulador.firstCapture = true;
        createCrazyPiece_King_Black( 5, 0, 0, simulador);
        createCrazyPiece_King_White( 6, 0, 0, simulador);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 7, 0, 0, simulador);
        createCrazyPiece_King_White( 8, 0, 0, simulador);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 9, 0, 0, simulador);
        createCrazyPiece_King_White( 10, 0, 0, simulador);
        assertTrue("White team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 11, 0, 0, simulador);
        createCrazyPiece_King_Black( 12, 2, 0, simulador);
        assertTrue("Black team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKingsAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame(13, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame(14, 1, 0, simulador);
        createCrazyPiece_King_White_PresentInGame(15, 2, 0, simulador);
        assertFalse("With two white kings and one black king the game keeps rolling", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndTwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame(1, 0, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame(4, 1, 0, simulador);
        createCrazyPiece_King_White_PresentInGame(5, 2, 0, simulador);
        assertFalse("With one white king and two black kings the game keeps rolling!", simulador.jogoTerminado());
    }

//  suggested play
    //  King
    @Test
    public void testGetSuggestedPlay_King() {
        Simulador simulador = createSimulator(5);
        createCrazyPiece_King_Black_PresentInGame(1, 2, 2, simulador);
        List<Comparable> result = setKingResult();
        result.forEach(System.out::println);

        assertEquals("Not the the right suggestions!", result.toString(), simulador.obterSugestoesJogada(2, 2).toString());

    }

    @Test
    public void testGetSuggestedPlay_King_2X() {
        Simulador simulador = createSimulator(5);
        createCrazyPiece_King_Black_PresentInGame(1, 2, 2, simulador);
        List<Comparable> result_2x = setKingResult();
        simulador.obterSugestoesJogada(2,2);
        assertEquals("Not the the right suggestions!", result_2x.toString(), simulador.obterSugestoesJogada(2, 2).toString());

    }

    @Test
    public void testGetSuggestedPlay_King_NotCentered() {
        Simulador simulador = createSimulator(5);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 2, simulador);
        List<Comparable> result = new ArrayList<>();
        result.add(new ValidPlay(1, 2, 0));
        result.add(new ValidPlay(0, 1, 0));
        result.add(new ValidPlay(0, 3, 0));
        result.add(new ValidPlay(1, 3, 0));
        result.add(new ValidPlay(1, 1, 0));
        result.forEach(System.out::println);
        assertEquals("Not the the right suggestions!", result.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(0, 2).toString());

    }

    @Test
    public void testGetSuggestedPlay_King_ImpossibleToMove() {
        Simulador simulador = createSimulator(5);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 5, simulador);
        createSeveral_Ponies(simulador);
        assertEquals("Not the the right suggestions!", new ArrayList<>().toString(), simulador.obterSugestoesJogada(0, 2).toString());
    }

    @Test
    public void testGetSuggestedPlay_King_EatAnotherPiece() {
        Simulador simulador = createSimulator(11);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 5, simulador);
        CrazyPiece capturedPiece = createCrazyPiece_King_White_PresentInGame(1, 4, 4, simulador);
        List<Comparable> result_King = new ArrayList<>();
        result_King.add(new ValidPlay(4, 5, 0));
        result_King.add(new ValidPlay(6, 5, 0));
        result_King.add(new ValidPlay(5, 4, 0));
        result_King.add(new ValidPlay(5, 6, 0));
        result_King.add(new ValidPlay(4, 4, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        result_King.addAll(set__6_6__4_6__6_4());
        assertEquals("Not the the right suggestions!", result_King.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());
    }

    //  Queen
    @Test
    public void testGetSuggestedPlay_Queen() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(5, simulador);
        List<Comparable> result_queen = setQueenResult();
        assertEquals("Not the the right suggestions!", result_queen.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Queen_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(0, simulador);
        List<Comparable> result_queen = new ArrayList<>(set__1_5__TO__5_5());
        result_queen.addAll(set__0_0__TO__0_4());
        result_queen.addAll(set__0_6__TO__0_10());
        result_queen.addAll(setQueenResult_NotCentered());
        assertEquals("Not the the right suggestions!", result_queen.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(0, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Queen_EatingAnotherQueen() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(5, simulador);
        createCrazyPiecePresentInGame_Queen_White(1, 5, simulador);
        List<Comparable> result_queen = setQueenResult();
        List<Comparable> result = result_queen.stream().filter((i) -> !i.toString().equals("0, 5, 0")).filter((i) -> !i.toString().equals("1, 5, 0")).collect(Collectors.toList());
        assertEquals("Not the the right suggestions!", result.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Queen_LessThanTwoHousesFromPriest() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_Priest_White(1, 5, simulador);
        List<Comparable> result_queen = new ArrayList<>();
        result_queen.add(new ValidPlay(4, 5, 0));
        result_queen.add(new ValidPlay(3, 5, 0));
        result_queen.add(new ValidPlay(1, 5, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        result_queen.addAll(set__Horizontal_RightOf_5_5__VerticalExcept_5_5__Diagonals_Except_5_5());
        assertEquals("Not the the right suggestions!", result_queen.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Queen_PiecesInTheWay() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_Pony_White(simulador);
        List<Comparable> result_queen = set__Horizontal_Except_5_5();
        result_queen.add(new ValidPlay(4, 4, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        result_queen.addAll(set__Vertical_Except_5_5());
        assertEquals("Not the the right suggestions!", result_queen.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Queen_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Queen_Black(5, simulador);
        createSeveral_Ponies(simulador);
        assertEquals("Not the the right suggestions!", new ArrayList<>().toString(), simulador.obterSugestoesJogada(5, 5).toString());
    }

    //  Pony
    @Test
    public void testGetSuggestedPlay_Pony() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5, simulador);
        List<Comparable> result_pony = setPonyResult();
        assertEquals("Not the the right suggestions!", result_pony.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Pony_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 3, simulador);
        List<Comparable> result_pony = new ArrayList<>();
        result_pony.add(new ValidPlay(7, 5, 0));
        result_pony.add(new ValidPlay(3, 5, 0));
        result_pony.add(new ValidPlay(3, 1, 0));
        result_pony.add(new ValidPlay(7, 1, 0));
        assertEquals("Not the the right suggestions!", result_pony.toString(), simulador.obterSugestoesJogada(5, 3).toString());

    }

    @Test
    public void testGetSuggestedPlay_Pony_NotCentered_OutOfBoard() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 0, simulador);
        List<Comparable> result_pony = new ArrayList<>();
        result_pony.add(new ValidPlay(7, 2, 0));
        result_pony.add(new ValidPlay(3, 2, 0));
        assertEquals("Not the the right suggestions!", result_pony.toString(), simulador.obterSugestoesJogada(5, 0).toString());

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_Valid() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5, simulador);
        createCrazyPiece_King_White_PresentInGame(1, 5, 3, simulador);
        List<Comparable> result_pony = setPonyResult();
        assertEquals("Not the the right suggestions!", result_pony.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_NotValid() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5, simulador);
        createCrazyPiece_King_White_PresentInGame(1, 5, 3, simulador);
        createCrazyPiece_King_Black_PresentInGame(1, 3, 5, simulador);
        List<Comparable> result_pony = new ArrayList<>();
        result_pony.add(new ValidPlay(7, 7, 0));
        result_pony.add(new ValidPlay(3, 7, 0));
        result_pony.add(new ValidPlay(7, 3, 0));
        assertEquals("Not the the right suggestions!", result_pony.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 4, simulador);
        createCrazyPiece_King_Black_PresentInGame(1, 4, 5, simulador);
        createCrazyPiecePresentInGame_Pony_Black(5, 5, simulador);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 6, simulador);
        createCrazyPiece_King_Black_PresentInGame(1, 6, 5, simulador);
        List<Comparable> result_pony = new ArrayList<>();
//        result_pony.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 5));

    }

    //  Priest
    @Test
    public void testGetSuggestedPlay_Priest() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5, simulador);
        List<Comparable> result_priest = new ArrayList<>(setPriestResult());
        assertEquals("Not the the right suggestions!", result_priest.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Priest_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(0, 5, simulador);
        List<Comparable> result = setQueenResult_NotCentered();
        List<Comparable> result_priest = result.stream()
                .filter((i) -> !i.toString().equals("4, 9, 0"))
                .filter((i) -> !i.toString().equals("5, 10, 0"))
                .filter((i) -> !i.toString().equals("4, 1, 0"))
                .filter((i) -> !i.toString().equals("5, 0, 0"))
                .collect(Collectors.toList());
        assertEquals("Not the the right suggestions!", result_priest.toString(), simulador.obterSugestoesJogada(0, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Priest_LessThan2HousesFromAQueen() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_Queen_White(2, 2, simulador);
        List<Comparable> result_priest = new ArrayList<>();
        result_priest.add(new ValidPlay(4, 4, 0));
        result_priest.add(new ValidPlay(2, 2, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        setAndTest_PriestResult(simulador, result_priest);
    }

    @Test
    public void testGetSuggestedPlay_Priest_PiecesInTheWay() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_TowerH_White(simulador);
        List<Comparable> result_priest = new ArrayList<>();
        result_priest.add(new ValidPlay(4, 4, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        setAndTest_PriestResult(simulador, result_priest);
    }

    @Test
    public void testGetSuggestedPlay_Priest_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerH_Black(4, 4, simulador);
        createCrazyPiecePresentInGame_Priest_Black(5, 5, simulador);
        createCrazyPiecePresentInGame_Priest_Black(6, 6, simulador);
        createCrazyPiecePresentInGame_Priest_Black(6, 4, simulador);
        createCrazyPiecePresentInGame_Priest_Black(4, 6, simulador);
        List<Comparable> result_priest = new ArrayList<>();
//        result_priest.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(5, 5));

    }

    //  TowerH
    @Test
    public void testGetSuggestedPlay_TowerH() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5, simulador);
        List<Comparable> result_towerH = set__0_5__TO__4_5();
        result_towerH.addAll(set__6_5__TO__10_5());
        assertEquals("Not the the right suggestions!", result_towerH.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_TowerH_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerH_Black(0, 5, simulador);
        List<Comparable> result = new ArrayList<>(set__1_5__TO__5_5());
        result.addAll(set__6_5__TO__10_5());
        List<Comparable> result_towerH = result.stream()
                .filter((i) -> !i.toString().equals("0, 5, 0"))
                .collect(Collectors.toList());
        assertEquals("Not the the right suggestions!", result_towerH.toString(), simulador.obterSugestoesJogada(0, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_TowerH_PiecesInTheWay() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_Priest_White(6, 5, simulador);
        List<Comparable> result_towerH = set__0_5__TO__4_5();
        result_towerH.add(new ValidPlay(6, 5, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        assertEquals("Not the the right suggestions!", result_towerH.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_TowerH_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(4, 5, simulador);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5, simulador);
        createCrazyPiecePresentInGame_Priest_Black(6, 5, simulador);
        List<Comparable> result_towerH = new ArrayList<>();
        assertEquals("Not the the right suggestions!", result_towerH, simulador.obterSugestoesJogada(5, 5));

    }

    //  TowerV
    @Test
    public void testGetSuggestedPlay_TowerV() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerV_Black(5, simulador);
        List<Comparable> result_towerV = set__5_0__TO__5_4();
        result_towerV.addAll(set__5_6__TO__5_10());
        assertEquals("Not the the right suggestions!", result_towerV.toString(), simulador.obterSugestoesJogada(5, 5).toString());
    }

    @Test
    public void testGetSuggestedPlay_TowerV_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerV_Black(0, simulador);
        List<Comparable> result_towerV = set__0_0__TO__0_4();
        result_towerV.addAll(set__0_6__TO__0_10());
        assertEquals("Not the the right suggestions!", result_towerV.toString(), simulador.obterSugestoesJogada(0, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_TowerV_PiecesInTheWay() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_TowerV_Black(5, simulador);
        CrazyPiece capturedPiece = createCrazyPiecePresentInGame_Priest_White(5, 4, simulador);
        List<Comparable> result_towerV = new ArrayList<>();
        result_towerV.add(new ValidPlay(5, 4, capturedPiece.getNPoints(), capturedPiece.getRelativeValue()));
        result_towerV.addAll(set__5_6__TO__5_10());
        assertEquals("Not the the right suggestions!", result_towerV.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());
    }

    @Test
    public void testGetSuggestedPlay_TowerV_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 4, simulador);
        createCrazyPiecePresentInGame_TowerV_Black(5, simulador);
        createCrazyPiecePresentInGame_Priest_Black(5, 6, simulador);
        List<Comparable> result_towerV = new ArrayList<>();
//        result_towerV.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_towerV, simulador.obterSugestoesJogada(5, 5));

    }

    //  Bunny
    @Test
    public void testGetSuggestedPlay_Bunny_ParShift() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Bunny_Black(5, 5, simulador);
        List<Comparable> result_bunny = setBunnyResult_ParShift();
        assertEquals("Not the the right suggestions!", result_bunny.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Bunny_OddShift() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Bunny_White(simulador);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        List<Comparable> result_bunny = new ArrayList<>();
//        result_bunny.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Bunny_NotCentered() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Bunny_Black(0, 5, simulador);
        List<Comparable> result_bunny = new ArrayList<>();
        result_bunny.add(new ValidPlay(1, 6, 0));
        result_bunny.add(new ValidPlay(1, 4, 0));
        assertEquals("Not the the right suggestions!", result_bunny.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(0, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_Bunny_ImpossibleToMove() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Bunny_Black(4, 4, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(5, 5, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(6, 6, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(4, 6, simulador);
        createCrazyPiecePresentInGame_Bunny_Black(6, 4, simulador);
        List<Comparable> result_bunny = new ArrayList<>();
//        result_bunny.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(5, 5));

    }

    //  Joker
    @Test
    public void testGetSuggestedPlay_BunnyJoker_ParShift() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Joker_Black(simulador);
        List<Comparable> result_BunnyJoker = setBunnyResult_ParShift();
        assertEquals("Not the the right suggestions!", result_BunnyJoker.toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    @Test
    public void testGetSuggestedPlay_BunnyJoker_OddShift() {
        Simulador simulador = createSimulator(11);
        createCrazyPiecePresentInGame_Joker_White(simulador);
        simulador.shift.addCount(simulador.crazyPiecesInGame);
        List<Comparable> result_BunnyJoker= new ArrayList<>();
//        result_BunnyJoker.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_BunnyJoker, simulador.obterSugestoesJogada(5, 5));

    }

//  undo
    @Test
    public void testUndoPlay_CountNoCapture() {
        Simulador simulator = createSimulator(4);
        createCrazyPiece_King_White_PresentInGame(1, 1, 0, simulator);
        createCrazyPiece_King_Black_PresentInGame(2, 0, 0, simulator);
        assertTrue("Should eat the other piece!", simulator.processaJogada(0, 0, 1, 0));
        assertEquals("Should be -1!", -1, simulator.getPreviousCountNoCapture());
    }

    @Test
    public void testUndoPlay_CountNoCapture_After_ValidPlays() {
        Simulador simulator = createSimulator(5);
        testUndoPieces_Aux(simulator);
        assertEquals("Shouldn't be any change!", 3, simulator.getPreviousCountNoCapture());
    }

    @Test
    public void testUndoPlay_CountNoCapture_After_ValidPlays_And_Before_AnotherValidPlay() {
        Simulador simulator = createSimulator(5);
        testUndoPieces_Aux(simulator);
        assertEquals("Shouldn't be any change!", 3, simulator.getPreviousCountNoCapture());
        assertTrue("Should eat the other piece!", simulator.processaJogada(2, 1, 2, 0));
        assertEquals("Shouldn't be any change!", -1, simulator.getPreviousCountNoCapture());
    }

    @Test
    public void testGetFirstCapture_True_AsBlack_After_Undo() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame( 1, 1, 0, simulador);
        createCrazyPiece_King_Black_PresentInGame( 2, 0, 0, simulador);
        simulador.processaJogada(0,0,1,0);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
        simulador.anularJogadaAnterior();
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
        simulador.processaJogada(0,0,1,0);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

//  creates
    private Simulador createSimulator(int boardSize) {

        return new Simulador(boardSize);

    }

    private void createCrazyPiece_King_White(int PieceId, int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiBranco(PieceId, "White");
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiece_King_Black(int PieceId, int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiPreto(PieceId, "Black");
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private CrazyPiece createCrazyPiece_King_White_PresentInGame(int PieceId, int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiBranco(PieceId, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

        return piece;
    }

    private void createCrazyPiece_King_Black_PresentInGame(int PieceId, int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiPreto(PieceId, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private CrazyPiece createCrazyPiecePresentInGame_Queen_White(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new RainhaBranca(2, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);
        return piece;

    }

    private void createCrazyPiecePresentInGame_Queen_Black(int x, Simulador simulador) {

        Position piecePosition = new Position(x, 5);
        CrazyPiece piece = new RainhaPreta(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private CrazyPiece createCrazyPiecePresentInGame_Pony_White(Simulador simulador) {

        Position piecePosition = new Position(4, 4);
        CrazyPiece piece = new PoneiMagicoBranco(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);
        return piece;

    }

    private void createCrazyPiecePresentInGame_Pony_Black(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PoneiMagicoPreto(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private CrazyPiece createCrazyPiecePresentInGame_Priest_White(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PadreDaVilaBranco(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);
        return piece;

    }

    private void createCrazyPiecePresentInGame_Priest_Black(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PadreDaVilaPreto(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private CrazyPiece createCrazyPiecePresentInGame_TowerH_White(Simulador simulador) {

        Position piecePosition = new Position(4, 4);
        CrazyPiece piece = new TorreHBranca(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

        return piece;
    }

    private void createCrazyPiecePresentInGame_TowerH_Black(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new TorreHPreta(2, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_TowerV_White(Simulador simulador) {

        Position piecePosition = new Position(1, 2);
        CrazyPiece piece = new TorreVBranca(3, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_TowerV_Black(int x, Simulador simulador) {

        Position piecePosition = new Position(x, 5);
        CrazyPiece piece = new TorreVPreta(1, "black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Bunny_White(Simulador simulador) {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new LebreBranca(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Bunny_Black(int x, int y, Simulador simulador) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new LebrePreta(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Joker_White(Simulador simulador) {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new JokerBranco(1, "White");
        piece.pieceId = (byte) 7;
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Joker_Black(Simulador simulador) {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new JokerPreto(1, "Black");
        piece.pieceId = (byte) 6;
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPiecesInGame.add(piece);

    }

    private void createSeveral_Ponies(Simulador simulador) {

        createCrazyPiecePresentInGame_Pony_Black(4, 4, simulador);
        createCrazyPiecePresentInGame_Pony_Black(5, 4, simulador);
        createCrazyPiecePresentInGame_Pony_Black(6, 4, simulador);
        createCrazyPiecePresentInGame_Pony_Black(6, 5, simulador);
        createCrazyPiecePresentInGame_Pony_Black(4, 5, simulador);
        createCrazyPiecePresentInGame_Pony_Black(4, 6, simulador);
        createCrazyPiecePresentInGame_Pony_Black(5, 6, simulador);
        createCrazyPiecePresentInGame_Pony_Black(6, 6, simulador);

    }

//  set results and runTestsAuxiliaries
    private List<Comparable> setKingResult() {
        List<Comparable> result = new ArrayList<>();
        result.add(new ValidPlay(1, 2, 0));
        result.add(new ValidPlay(3, 2, 0));
        result.add(new ValidPlay(2, 1, 0));
        result.add(new ValidPlay(2, 3, 0));
        result.add(new ValidPlay(1, 1, 0));
        result.add(new ValidPlay(3, 3, 0));
        result.add(new ValidPlay(1, 3, 0));
        result.add(new ValidPlay(3, 1, 0));
        return result.stream().sorted().collect(Collectors.toList());
    }

    private List<Comparable> setBunnyResult_ParShift() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(4, 4, 0));
        result.addAll(set__6_6__4_6__6_4());

        return result;

    }

    private List<Comparable> setPriestResult() {

        List<Comparable> result = new ArrayList<>();
        result.addAll(set__0_0__TO__4_4());
        result.addAll(set__6_6__TO__10_10());
        result.addAll(set__0_10__TO__4_6());
        result.addAll(set__6_4__TO__10_0());

        return result.stream()
                .filter((i) -> !i.toString().equals("0, 0, 0"))
                .filter((i) -> !i.toString().equals("1, 1, 0"))
                .filter((i) -> !i.toString().equals("0, 10, 0"))
                .filter((i) -> !i.toString().equals("1, 9, 0"))
                .filter((i) -> !i.toString().equals("9, 1, 0"))
                .filter((i) -> !i.toString().equals("10, 0, 0"))
                .filter((i) -> !i.toString().equals("9, 9, 0"))
                .filter((i) -> !i.toString().equals("10, 10, 0"))
                .collect(Collectors.toList());

    }

    private List<Comparable> setQueenResult() {

        List<Comparable> result = set__0_5__TO__4_5();
        result.addAll(set__Horizontal_RightOf_5_5__VerticalExcept_5_5__Diagonals_Except_5_5());

        return result;

    }

    private List<Comparable> setQueenResult_NotCentered() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(1, 6, 0));
        result.add(new ValidPlay(2, 7, 0));
        result.add(new ValidPlay(3, 8, 0));
        result.add(new ValidPlay(4, 9, 0));
        result.add(new ValidPlay(5, 10, 0));
        result.add(new ValidPlay(1, 4, 0));
        result.add(new ValidPlay(2, 3, 0));
        result.add(new ValidPlay(3, 2, 0));
        result.add(new ValidPlay(4, 1, 0));
        result.add(new ValidPlay(5, 0, 0));

        return result;

    }

    private List<Comparable> setPonyResult() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(7, 7, 0));
        result.add(new ValidPlay(3, 7, 0));
        result.add(new ValidPlay(3, 3, 0));
        result.add(new ValidPlay(7, 3, 0));

        return result;

    }

    private List<Comparable> set__0_0__TO__4_4() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(4, 4, 0));
        result.add(new ValidPlay(3, 3, 0));
        result.add(new ValidPlay(2, 2, 0));
        result.add(new ValidPlay(1, 1, 0));
        result.add(new ValidPlay(0, 0, 0));

        return result;

    }

    private List<Comparable> set__6_6__TO__10_10() {

        List<Comparable> result = new ArrayList<>(set__6_6__TO__8_8());
        result.add(new ValidPlay(9, 9, 0));
        result.add(new ValidPlay(10, 10, 0));

        return result;

    }

    private List<Comparable> set__6_6__TO__8_8() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(6, 6, 0));
        result.add(new ValidPlay(7, 7, 0));
        result.add(new ValidPlay(8, 8, 0));

        return result;

    }

    private List<Comparable> set__0_10__TO__4_6() {

        List<Comparable> result = new ArrayList<>(set__2_8__TO__4_6());

        result.add(new ValidPlay(1, 9, 0));
        result.add(new ValidPlay(0, 10, 0));

        return result;

    }

    private List<Comparable> set__2_8__TO__4_6() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(4, 6, 0));
        result.add(new ValidPlay(3, 7, 0));
        result.add(new ValidPlay(2, 8, 0));

        return result;

    }

    private List<Comparable> set__6_4__TO__10_0() {

        List<Comparable> result = new ArrayList<>(set__6_4__TO__8_2());
        result.add(new ValidPlay(9, 1, 0));
        result.add(new ValidPlay(10, 0, 0));

        return result;

    }

    private List<Comparable> set__6_4__TO__8_2() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(6, 4, 0));
        result.add(new ValidPlay(7, 3, 0));
        result.add(new ValidPlay(8, 2, 0));

        return result;

    }

    private List<Comparable> set__5_0__TO__5_4() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(5, 4, 0));
        result.add(new ValidPlay(5, 3, 0));
        result.add(new ValidPlay(5, 2, 0));
        result.add(new ValidPlay(5, 1, 0));
        result.add(new ValidPlay(5, 0, 0));

        return result;

    }

    private List<Comparable> set__5_6__TO__5_10() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(5, 6, 0));
        result.add(new ValidPlay(5, 7, 0));
        result.add(new ValidPlay(5, 8, 0));
        result.add(new ValidPlay(5, 9, 0));
        result.add(new ValidPlay(5, 10, 0));

        return result;

    }

    private List<Comparable> set__0_0__TO__0_4() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(0, 4, 0));
        result.add(new ValidPlay(0, 3, 0));
        result.add(new ValidPlay(0, 2, 0));
        result.add(new ValidPlay(0, 1, 0));
        result.add(new ValidPlay(0, 0, 0));

        return result;

    }

    private List<Comparable> set__0_6__TO__0_10() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(0, 6, 0));
        result.add(new ValidPlay(0, 7, 0));
        result.add(new ValidPlay(0, 8, 0));
        result.add(new ValidPlay(0, 9, 0));
        result.add(new ValidPlay(0, 10, 0));

        return result;

    }

    private List<Comparable> set__0_5__TO__4_5() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(4, 5, 0));
        result.add(new ValidPlay(3, 5, 0));
        result.add(new ValidPlay(2, 5, 0));
        result.add(new ValidPlay(1, 5, 0));
        result.add(new ValidPlay(0, 5, 0));

        return result;

    }

    private List<Comparable> set__6_5__TO__10_5() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(6, 5, 0));
        result.add(new ValidPlay(7, 5, 0));
        result.add(new ValidPlay(8, 5, 0));
        result.add(new ValidPlay(9, 5, 0));
        result.add(new ValidPlay(10, 5, 0));

        return result;

    }

    private List<Comparable> set__1_5__TO__5_5() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(1, 5, 0));
        result.add(new ValidPlay(2, 5, 0));
        result.add(new ValidPlay(3, 5, 0));
        result.add(new ValidPlay(4, 5, 0));
        result.add(new ValidPlay(5, 5, 0));

        return result;

    }

    private List<Comparable> set__6_6__4_6__6_4() {

        List<Comparable> result = new ArrayList<>();

        result.add(new ValidPlay(6, 6, 0));
        result.add(new ValidPlay(4, 6, 0));
        result.add(new ValidPlay(6, 4, 0));

        return result;

    }

    private List<Comparable> set__Horizontal_RightOf_5_5__VerticalExcept_5_5__Diagonals_Except_5_5() {

        List<Comparable> result = new ArrayList<>();

        result.addAll(set__6_5__TO__10_5());
        result.addAll(set__5_0__TO__5_4());
        result.addAll(set__5_6__TO__5_10());
        result.addAll(set__0_0__TO__4_4());
        result.addAll(set__6_6__TO__10_10());
        result.addAll(set__0_10__TO__4_6());
        result.addAll(set__6_4__TO__10_0());

        return result;

    }

    private List<Comparable> set__Horizontal_Except_5_5() {

        List<Comparable> result = new ArrayList<>();

        result.addAll(set__0_5__TO__4_5());
        result.addAll(set__6_5__TO__10_5());
        result.addAll(set__5_0__TO__5_4());
        result.addAll(set__5_6__TO__5_10());

        return result;

    }

    private List<Comparable> set__Vertical_Except_5_5() {

        List<Comparable> result = new ArrayList<>();

        result.addAll(set__6_6__TO__10_10());
        result.addAll(set__0_10__TO__4_6());
        result.addAll(set__6_4__TO__10_0());

        return result;

    }

    private void setAndTest_PriestResult(Simulador simulador, List<Comparable> result_priest) {

        result_priest.addAll(set__6_6__TO__8_8());
        result_priest.addAll(set__2_8__TO__4_6());
        result_priest.addAll(set__6_4__TO__8_2());
        assertEquals("Not the the right suggestions!", result_priest.stream().sorted().collect(Collectors.toList()).toString(), simulador.obterSugestoesJogada(5, 5).toString());

    }

    private void testUndoPieces_Aux(Simulador simulator) {
        createCrazyPiece_King_White_PresentInGame(1, 0, 0, simulator);
        createCrazyPiece_King_Black_PresentInGame(2, 4, 0, simulator);
        createCrazyPiece_King_Black_PresentInGame(3, 2, 1, simulator);
        assertTrue("Should be able to move!", simulator.processaJogada(4, 0, 3, 0));
        assertTrue("Should be able to move!", simulator.processaJogada(0, 0, 1, 0));
        assertTrue("Should be able to move!", simulator.processaJogada(3, 0, 2, 0));
        assertTrue("Should eat the other piece!", simulator.processaJogada(1, 0, 2, 0));
        assertEquals("Should be 2!", 3, simulator.getPreviousCountNoCapture());

    }

    private void searchForAJoker(Simulador simulador) {

        for (CrazyPiece thisPiece : simulador.crazyPiecesInGame) {
            if (thisPiece.getType() == 7) {
                assertEquals("Should be 1", 1, thisPiece.getPieceId());
                assertEquals("Should be Joker/Rainha", "Joker/Rainha", thisPiece.getTypeName());
                break;
            }
        }

    }

}
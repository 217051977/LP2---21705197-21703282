package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class TestSimulador {

//  Main
    @Test
    public void testMain() {
        Main main = new Main();
        Main main1 = new Main();
        assertTrue("The both Main class must NOT be equal!", !main1.equals(main));
    }

//  Board Size
    @Test
    public void testGetTamanhoTabuleiro() {
        Simulador simulador = new Simulador(4);
        assertEquals("The board size is not correct!", 4, simulador.getTamanhoTabuleiro());
    }

//  Crazy Pieces
    @Test
    public void testGetPecasMalucas() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black(1, 0, 0);
        createCrazyPiece_King_White(2, 0, 0);

        assertEquals("Those pieces wasn't the right ones!", Simulador.crazyPiecesInGame, simulador.getPecasMalucas());
    }

//  Authors
    @Test
    public void testGetAutores() {
        Simulador simulador = new Simulador();
        List<String> authors = new ArrayList<>();
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
        createCrazyPiece_King_White_PresentInGame(1, 0, 1);
        createCrazyPiece_King_Black_PresentInGame(2, 0, 0);
        simulador.processaJogada(0,0,0,1);
        assertEquals("Should exist 1 white piece captured!", 1, simulador.getNumberOfWhitePiecesCaptured());
    }

    @Test
    public void testGetNumberOfBlackPiecesCaptured() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_Black(7, 0, 0);
        createCrazyPiece_King_White(8, 0, 1);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Should exist 1 black piece captured!", 1, simulador.getNumberOfBlackPiecesCaptured());
    }

//  Number Of Invalid Plays
    //  White
    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White(1, 0, 0);
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 1);
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 4);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 4);
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_Correct() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 1);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    //  Black
    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 0);
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1);
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 2);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 4);
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_Correct() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1);
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

//  Number Of Valid Plays
    //  White
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 0);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 2);
        simulador.processaJogada(0,2,0,3);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

//  Black
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 0);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 1);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByBlackTeam());
    }

//  Score
    @Test
    public void testGetScore() {
        Simulador simulador = new Simulador();
        simulador.scores.add("ola");
        List<String> list = new ArrayList<>();
        list.add("ola");
        assertEquals("Should be \"ola\"!", list, simulador.getResultados());
    }

//  ID Piece
    @Test
    public void testGetIDPiece_Exists() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black( 1, 0, 0);
        assertEquals("Should be \"1\"!", 1, simulador.getIDPeca(0, 0));
    }

    @Test
    public void testGetIDPiece_DoesntExists() {
        Simulador simulador = new Simulador();
        createCrazyPiece_King_Black( 1, 0, 0);
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
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 1, 1);
        createCrazyPiece_King_Black( 2, 0, 0);
        simulador.processaJogada(1,1,0,0);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsWhite() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.addCount();
        createCrazyPiece_King_White( 1, 0, 0);
        createCrazyPiece_King_Black( 3, 1, 1);
        simulador.processaJogada(1,1,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

    //  Black
    @Test
    public void testGetFirstCapture_True_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 1, 1, 1);
        createCrazyPiece_King_Black( 2, 0, 0);
        simulador.processaJogada(0,0,1,1);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 1, 1, 1);
        createCrazyPiece_King_Black( 2, 0, 0);
        simulador.processaJogada(0,0,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

//  Start Game
    @Test
    public void testStartGame_Empty() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_EMPTY.txt");
        assertFalse("There's no info in it!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_SIZE.txt");
        assertFalse("Board size or numbers of pieces are missing!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Lower_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_LOWER_BOARD_SIZE.txt");
        assertFalse("Board Size is lower than the layout or is too much info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Higher_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_HIGHER_BOARD_SIZE.txt");
        assertFalse("Board Size is higher than the layout or is missing info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_NUMBER_OF_PIECES.txt");
        assertFalse("Board size or numbers of pieces are missing!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Lower_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_LOWER_NUMBER_OF_PIECES.txt");
        assertFalse("Board Size is lower than the layout or is too much info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Higher_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_HIGHER_NUMBER_OF_PIECES.txt");
        assertFalse("Board Size is higher than the layout or is missing info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_LINE_AND_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_Line() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_LINE.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_Column() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_PIECES_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_LINE_AND_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_Line() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_LINE.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_Column() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/FILE_TEST_MISSING_BOARD_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Correct() {
        Simulador simulador = new Simulador();
        File file = new File("test-files/RUN_FILE.txt");
        assertTrue("Should work!", simulador.iniciaJogo(file));
    }

//  Execute The Move
    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Up() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 1);
        assertTrue("You can move the piece!", simulador.processaJogada(0,1,0,0));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Down() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 0);
        assertTrue("You can move the piece!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Left() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 1, 0);
        assertTrue("You can move the piece!", simulador.processaJogada(1,0,0,0));
    }

    @Test
    public void testExecuteTheMove_AbleToMoveThePiece_Right() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 0);
        assertTrue("You can move the piece!", simulador.processaJogada(0,0,1,0));
    }

    @Test
    public void testExecuteTheMove_MoveEnemyPiece() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 1, 0, 0);
        assertFalse("You can only move your teams pieces!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 1, 0, 0);
        assertFalse("You can't move to the same position!", simulador.processaJogada(0,0,0,0));
    }

    @Test
    public void testExecuteTheMove_MoveToAnOutBoardHouse() {
        Simulador simulador = new Simulador(4);
        assertFalse("You can't move a piece to an out of the board house!", simulador.processaJogada(0,0,0,-1));
    }

    @Test
    public void testExecuteTheMove_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 1, 0, 0);
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
        createCrazyPiece_King_White( 1, 0, 0);
        assertFalse("You can't move so many hoses!", simulador.processaJogada(0,0,0,2));
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
        Simulador.shift.countNoCapture = 10;
        createCrazyPiece_King_White( 3, 0, 0);
        createCrazyPiece_King_Black( 4, 0, 0);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_10ShiftsWithoutAnyCapture_And_FirstCapture() {
        Simulador simulador = new Simulador(4);
        Simulador.shift.countNoCapture = 10;
        Simulador.firstCapture = true;
        createCrazyPiece_King_Black( 5, 0, 0);
        createCrazyPiece_King_White( 6, 0, 0);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 7, 0, 0);
        createCrazyPiece_King_White( 8, 0, 0);
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White( 9, 0, 0);
        createCrazyPiece_King_White( 10, 0, 0);
        assertTrue("White team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_Black( 11, 0, 0);
        createCrazyPiece_King_Black( 12, 2, 0);
        assertTrue("Black team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKingsAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame(13, 0, 0);
        createCrazyPiece_King_Black_PresentInGame(14, 1, 0);
        createCrazyPiece_King_White_PresentInGame(15, 2, 0);
        assertFalse("With two white kings and one black king the game keeps rolling", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndTwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece_King_White_PresentInGame(13, 0, 0);
        createCrazyPiece_King_Black_PresentInGame(14, 1, 0);
        createCrazyPiece_King_White_PresentInGame(15, 2, 0);
        assertFalse("With one white king and two black kings the game keeps rolling!", simulador.jogoTerminado());
    }

//  suggested play
    //  King
    @Test
    public void testGetSuggestedPlay_King() {
        Simulador simulador = new Simulador(5);
        createCrazyPiece_King_Black_PresentInGame(1, 2, 2);
        List<String> result = setKingResult();
        assertEquals("Not the the right suggestions!", result, simulador.obterSugestoesJogada(2, 2));

    }

    @Test
    public void testGetSuggestedPlay_King_2X() {
        Simulador simulador = new Simulador(5);
        createCrazyPiece_King_Black_PresentInGame(1, 2, 2);
        List<String> result_2x = setKingResult();
        simulador.obterSugestoesJogada(2,2);
        assertEquals("Not the the right suggestions!", result_2x, simulador.obterSugestoesJogada(2, 2));

    }

    @Test
    public void testGetSuggestedPlay_King_NotCentered() {
        Simulador simulador = new Simulador(5);
        createCrazyPiece_King_Black_PresentInGame(1, 0, 2);
        List<String> result = new ArrayList<>();
        result.add("\"" + 1 + "," + 2 + "\"");
        result.add("\"" + 0 + "," + 1 + "\"");
        result.add("\"" + 0 + "," + 3 + "\"");
        result.add("\"" + 1 + "," + 3 + "\"");
        result.add("\"" + 1 + "," + 1 + "\"");
        assertEquals("Not the the right suggestions!", result, simulador.obterSugestoesJogada(0, 2));

    }

    @Test
    public void testGetSuggestedPlay_King_ImpossibleToMove() {
        Simulador simulador = new Simulador(5);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 5);
        setPonies();
        List<String> result = new ArrayList<>();
        result.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result, simulador.obterSugestoesJogada(0, 2));

    }

    //  Queen
    @Test
    public void testGetSuggestedPlay_Queen() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(5);
        List<String> result_queen = setQueenResult();
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Queen_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(0);
        List<String> result_queen = new ArrayList<>();
        result_queen.add("\"" + 1 + "," + 5 + "\"");
        result_queen.add("\"" + 2 + "," + 5 + "\"");
        result_queen.add("\"" + 3 + "," + 5 + "\"");
        result_queen.add("\"" + 4 + "," + 5 + "\"");
        result_queen.add("\"" + 5 + "," + 5 + "\"");
        result_queen.addAll(set__0_0__TO__0_4());
        result_queen.addAll(set__0_6__TO__0_10());
        result_queen.addAll(setQueenResult_NotCentered());
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(0, 5));

    }

    @Test
    public void testGetSuggestedPlay_Queen_EatingAnotherQueen() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(5);
        createCrazyPiecePresentInGame_Queen_White(1, 5);
        List<String> result_queen = setQueenResult();
        result_queen.remove("\"" + 0 + "," + 5 + "\"");
        result_queen.remove("\"" + 1 + "," + 5 + "\"");
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Queen_LessThanTwoHousesFromPriest() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(5);
        createCrazyPiecePresentInGame_Priest_White(1, 5);
        List<String> result_queen = setQueenResult();
        result_queen.remove("\"" + 0 + "," + 5 + "\"");
        result_queen.remove("\"" + 1 + "," + 5 + "\"");
        result_queen.remove("\"" + 2 + "," + 5 + "\"");
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Queen_PiecesInTheWay() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(5);
        createCrazyPiecePresentInGame_Pony_White();
        List<String> result_queen = setQueenResult();
        result_queen.remove("\"" + 0 + "," + 0 + "\"");
        result_queen.remove("\"" + 1 + "," + 1 + "\"");
        result_queen.remove("\"" + 2 + "," + 2 + "\"");
        result_queen.remove("\"" + 3 + "," + 3 + "\"");
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Queen_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Queen_Black(5);
        setPonies();
        List<String> result_queen = new ArrayList<>();
        result_queen.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_queen, simulador.obterSugestoesJogada(5, 5));

    }

    //  Pony
    @Test
    public void testGetSuggestedPlay_Pony() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5);
        List<String> result_pony = setPonyResult();
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Pony_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 3);
        List<String> result_pony = new ArrayList<>();
        result_pony.add("\"" + 7 + "," + 5 + "\"");
        result_pony.add("\"" + 3 + "," + 5 + "\"");
        result_pony.add("\"" + 3 + "," + 1 + "\"");
        result_pony.add("\"" + 7 + "," + 1 + "\"");
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 3));

    }

    @Test
    public void testGetSuggestedPlay_Pony_NotCentered_OutOfBoard() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 0);
        List<String> result_pony = new ArrayList<>();
        result_pony.add("\"" + 7 + "," + 2 + "\"");
        result_pony.add("\"" + 3 + "," + 2 + "\"");
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 0));

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_Valid() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5);
        createCrazyPiece_King_White_PresentInGame(1, 5, 3);
        List<String> result_pony = setPonyResult();
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_NotValid() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Pony_Black(5, 5);
        createCrazyPiece_King_White_PresentInGame(1, 5, 3);
        createCrazyPiece_King_Black_PresentInGame(1, 3, 5);
        List<String> result_pony = new ArrayList<>();
        result_pony.add("\"" + 7 + "," + 7 + "\"");
        result_pony.add("\"" + 3 + "," + 7 + "\"");
        result_pony.add("\"" + 7 + "," + 3 + "\"");
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Pony_PiecesInTheWay_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 4);
        createCrazyPiece_King_Black_PresentInGame(1, 4, 5);
        createCrazyPiecePresentInGame_Pony_Black(5, 5);
        createCrazyPiece_King_Black_PresentInGame(1, 5, 6);
        createCrazyPiece_King_Black_PresentInGame(1, 6, 5);
        List<String> result_pony = new ArrayList<>();
        result_pony.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_pony, simulador.obterSugestoesJogada(5, 5));

    }

    //  Priest
    @Test
    public void testGetSuggestedPlay_Priest() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5);
        List<String> result_priest = set__0_0__TO__4_4();
        result_priest.remove("\"" + 1 + "," + 1 + "\"");
        result_priest.remove("\"" + 0 + "," + 0 + "\"");
        result_priest.addAll(setPriestResult());
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Priest_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(0, 5);
        List<String> result_priest = setQueenResult_NotCentered();
        result_priest.remove("\"" + 4 + "," + 9 + "\"");
        result_priest.remove("\"" + 5 + "," + 10 + "\"");
        result_priest.remove("\"" + 4 + "," + 1 + "\"");
        result_priest.remove("\"" + 5 + "," + 0 + "\"");
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(0, 5));

    }

    @Test
    public void testGetSuggestedPlay_Priest_LessThan2HousesFromAQueen() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5);
        createCrazyPiecePresentInGame_Queen_White(3, 3);
        List<String> result_priest = setPriestResult();
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Priest_PiecesInTheWay() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 5);
        createCrazyPiecePresentInGame_TowerH_White();
        List<String> result_priest = new ArrayList<>();
        result_priest.add("\"" + 4 + "," + 4 + "\"");
        result_priest.addAll(setPriestResult());
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Priest_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerH_Black(4, 4);
        createCrazyPiecePresentInGame_Priest_Black(5, 5);
        createCrazyPiecePresentInGame_Priest_Black(6, 6);
        createCrazyPiecePresentInGame_Priest_Black(6, 4);
        createCrazyPiecePresentInGame_Priest_Black(4, 6);
        List<String> result_priest = new ArrayList<>();
        result_priest.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_priest, simulador.obterSugestoesJogada(5, 5));

    }

    //  TowerH
    @Test
    public void testGetSuggestedPlay_TowerH() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5);
        List<String> result_towerH = set__0_5__TO__4_5();
        result_towerH.addAll(set__6_5__TO__10_5());
        assertEquals("Not the the right suggestions!", result_towerH, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_TowerH_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerH_Black(0, 5);
        List<String> result_towerH = new ArrayList<>();
        List<String> aux = set__0_5__TO__4_5();
        for (int i = aux.size() - 2; i >= 0; i --) {

            result_towerH.add(aux.get(i));

        }
        result_towerH.add("\"" + 5 + "," + 5 + "\"");
        result_towerH.addAll(set__6_5__TO__10_5());
        assertEquals("Not the the right suggestions!", result_towerH, simulador.obterSugestoesJogada(0, 5));

    }

    @Test
    public void testGetSuggestedPlay_TowerH_PiecesInTheWay() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5);
        createCrazyPiecePresentInGame_Priest_White(6, 5);
        List<String> result_towerH = set__0_5__TO__4_5();
        result_towerH.add("\"" + 6 + "," + 5 + "\"");
        assertEquals("Not the the right suggestions!", result_towerH, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_TowerH_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(4, 5);
        createCrazyPiecePresentInGame_TowerH_Black(5, 5);
        createCrazyPiecePresentInGame_Priest_Black(6, 5);
        List<String> result_towerH = new ArrayList<>();
        result_towerH.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_towerH, simulador.obterSugestoesJogada(5, 5));

    }

    //  TowerV
    @Test
    public void testGetSuggestedPlay_TowerV() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerV_Black(5);
        List<String> result_towerV = set__5_0__TO__5_4();
        result_towerV.addAll(set__5_6__TO__5_10());
        assertEquals("Not the the right suggestions!", result_towerV, simulador.obterSugestoesJogada(5, 5));
    }

    @Test
    public void testGetSuggestedPlay_TowerV_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerV_Black(0);
        List<String> result_towerV = set__0_0__TO__0_4();
        result_towerV.addAll(set__0_6__TO__0_10());
        assertEquals("Not the the right suggestions!", result_towerV, simulador.obterSugestoesJogada(0, 5));

    }

    @Test
    public void testGetSuggestedPlay_TowerV_PiecesInTheWay() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_TowerV_Black(5);
        createCrazyPiecePresentInGame_Priest_White(5, 4);
        List<String> result_towerV = new ArrayList<>();
        result_towerV.add("\"" + 5 + "," + 4 + "\"");
        result_towerV.addAll(set__5_6__TO__5_10());
        assertEquals("Not the the right suggestions!", result_towerV, simulador.obterSugestoesJogada(5, 5));
    }

    @Test
    public void testGetSuggestedPlay_TowerV_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Priest_Black(5, 4);
        createCrazyPiecePresentInGame_TowerV_Black(5);
        createCrazyPiecePresentInGame_Priest_Black(5, 6);
        List<String> result_towerV = new ArrayList<>();
        result_towerV.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_towerV, simulador.obterSugestoesJogada(5, 5));

    }

    //  Bunny
    @Test
    public void testGetSuggestedPlay_Bunny_ParShift() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Bunny_Black(5, 5);
        List<String> result_bunny = setBunnyResult_ParShift();
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Bunny_OddShift() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Bunny_White();
        Simulador.shift.addCount();
        List<String> result_bunny = new ArrayList<>();
        result_bunny.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_Bunny_NotCentered() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Bunny_Black(0, 5);
        List<String> result_bunny = new ArrayList<>();
        result_bunny.add("\"" + 1 + "," + 6 + "\"");
        result_bunny.add("\"" + 1 + "," + 4 + "\"");
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(0, 5));

    }

    @Test
    public void testGetSuggestedPlay_Bunny_ImpossibleToMove() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Bunny_Black(4, 4);
        createCrazyPiecePresentInGame_Bunny_Black(5, 5);
        createCrazyPiecePresentInGame_Bunny_Black(6, 6);
        createCrazyPiecePresentInGame_Bunny_Black(4, 6);
        createCrazyPiecePresentInGame_Bunny_Black(6, 4);
        List<String> result_bunny = new ArrayList<>();
        result_bunny.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_bunny, simulador.obterSugestoesJogada(5, 5));

    }

    //  Joker
    @Test
    public void testGetSuggestedPlay_BunnyJoker_ParShift() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Joker_Black();
        List<String> result_BunnyJoker = setBunnyResult_ParShift();
        assertEquals("Not the the right suggestions!", result_BunnyJoker, simulador.obterSugestoesJogada(5, 5));

    }

    @Test
    public void testGetSuggestedPlay_BunnyJoker_OddShift() {
        Simulador simulador = new Simulador(11);
        createCrazyPiecePresentInGame_Joker_White();
        Simulador.shift.addCount();
        List<String> result_BunnyJoker= new ArrayList<>();
        result_BunnyJoker.add("Pedido inválido");
        assertEquals("Not the the right suggestions!", result_BunnyJoker, simulador.obterSugestoesJogada(5, 5));

    }


    private void createCrazyPiece_King_White (int PieceId, int x, int y) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiBranco(PieceId, "White");
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiece_King_Black (int PieceId, int x, int y) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiPreto(PieceId, "Black");
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiece_King_White_PresentInGame (int PieceId, int x, int y) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiBranco(PieceId, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiece_King_Black_PresentInGame (int PieceId, int x, int y) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiPreto(PieceId, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Queen_White(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new RainhaBranca(0, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Queen_Black(int x) {

        Position piecePosition = new Position(x, 5);
        CrazyPiece piece = new RainhaPreta(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Pony_White() {

        Position piecePosition = new Position(4, 4);
        CrazyPiece piece = new PoneiMagicoBranco(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Pony_Black(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PoneiMagicoPreto(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Priest_White(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PadreDaVilaBranco(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Priest_Black(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new PadreDaVilaPreto(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_TowerH_White() {

        Position piecePosition = new Position(4, 4);
        CrazyPiece piece = new TorreHBranca(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_TowerH_Black(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new TorreHPreta(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_TowerV_Black(int x) {

        Position piecePosition = new Position(x, 5);
        CrazyPiece piece = new TorreVPreta(1, "black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Bunny_White() {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new LebreBranca(1, "White");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Bunny_Black(int x, int y) {

        Position piecePosition = new Position(x, y);
        CrazyPiece piece = new LebrePreta(1, "Black");
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Joker_White() {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new JokerBranco(1, "White");
        piece.pieceId = (byte) 7;
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void createCrazyPiecePresentInGame_Joker_Black() {

        Position piecePosition = new Position(5, 5);
        CrazyPiece piece = new JokerPreto(1, "Black");
        piece.pieceId = (byte) 6;
        piece.isInGame();
        piece.setPosition(piecePosition);
        Simulador.crazyPiecesInGame.add(piece);

    }

    private void setPonies() {

        createCrazyPiecePresentInGame_Pony_Black(4, 4);
        createCrazyPiecePresentInGame_Pony_Black(5, 4);
        createCrazyPiecePresentInGame_Pony_Black(6, 4);
        createCrazyPiecePresentInGame_Pony_Black(6, 5);
        createCrazyPiecePresentInGame_Pony_Black(4, 5);
        createCrazyPiecePresentInGame_Pony_Black(4, 6);
        createCrazyPiecePresentInGame_Pony_Black(5, 6);
        createCrazyPiecePresentInGame_Pony_Black(6, 6);

    }

//  set results
    private List<String> setKingResult() {

        List<String> result = new ArrayList<>();
        result.add("\"" + 1 + "," + 2 + "\"");
        result.add("\"" + 3 + "," + 2 + "\"");
        result.add("\"" + 2 + "," + 1 + "\"");
        result.add("\"" + 2 + "," + 3 + "\"");
        result.add("\"" + 1 + "," + 1 + "\"");
        result.add("\"" + 3 + "," + 3 + "\"");
        result.add("\"" + 1 + "," + 3 + "\"");
        result.add("\"" + 3 + "," + 1 + "\"");

        return result;

    }

    private List<String> setBunnyResult_ParShift() {

        List<String> result = new ArrayList<>();
        result.add("\"" + 4 + "," + 4 + "\"");
        result.add("\"" + 6 + "," + 6 + "\"");
        result.add("\"" + 4 + "," + 6 + "\"");
        result.add("\"" + 6 + "," + 4 + "\"");

        return result;

    }

    private List<String> setPriestResult() {

        List<String> result = set__6_6__TO__10_10();
        result.remove("\"" + 9 + "," + 9 + "\"");
        result.remove("\"" + 10 + "," + 10 + "\"");
        result.addAll(set__0_10__TO__4_6());
        result.remove("\"" + 1 + "," + 9 + "\"");
        result.remove("\"" + 0 + "," + 10 + "\"");
        result.addAll(set__6_4__TO__10_0());
        result.remove("\"" + 9 + "," + 1 + "\"");
        result.remove("\"" + 10 + "," + 0 + "\"");

        return result;

    }

    private List<String> setQueenResult() {

        List<String> result = set__0_5__TO__4_5();
        result.addAll(set__6_5__TO__10_5());
        result.addAll(set__5_0__TO__5_4());
        result.addAll(set__5_6__TO__5_10());
        result.addAll(set__0_0__TO__4_4());
        result.addAll(set__6_6__TO__10_10());
        result.addAll(set__0_10__TO__4_6());
        result.addAll(set__6_4__TO__10_0());

        return result;

    }

    private List<String> setQueenResult_NotCentered() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 1 + "," + 6 + "\"");
        result.add("\"" + 2 + "," + 7 + "\"");
        result.add("\"" + 3 + "," + 8 + "\"");
        result.add("\"" + 4 + "," + 9 + "\"");
        result.add("\"" + 5 + "," + 10 + "\"");
        result.add("\"" + 1 + "," + 4 + "\"");
        result.add("\"" + 2 + "," + 3 + "\"");
        result.add("\"" + 3 + "," + 2 + "\"");
        result.add("\"" + 4 + "," + 1 + "\"");
        result.add("\"" + 5 + "," + 0 + "\"");

        return result;

    }

    private List<String> setPonyResult() {

        List<String> result = new ArrayList<>();
        result.add("\"" + 7 + "," + 7 + "\"");
        result.add("\"" + 3 + "," + 7 + "\"");
        result.add("\"" + 3 + "," + 3 + "\"");
        result.add("\"" + 7 + "," + 3 + "\"");

        return result;

    }

    private List<String> set__0_0__TO__4_4() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 4 + "," + 4 + "\"");
        result.add("\"" + 3 + "," + 3 + "\"");
        result.add("\"" + 2 + "," + 2 + "\"");
        result.add("\"" + 1 + "," + 1 + "\"");
        result.add("\"" + 0 + "," + 0 + "\"");

        return result;

    }

    private List<String> set__6_6__TO__10_10() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 6 + "," + 6 + "\"");
        result.add("\"" + 7 + "," + 7 + "\"");
        result.add("\"" + 8 + "," + 8 + "\"");
        result.add("\"" + 9 + "," + 9 + "\"");
        result.add("\"" + 10 + "," + 10 + "\"");

        return result;

    }

    private List<String> set__0_10__TO__4_6() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 4 + "," + 6 + "\"");
        result.add("\"" + 3 + "," + 7 + "\"");
        result.add("\"" + 2 + "," + 8 + "\"");
        result.add("\"" + 1 + "," + 9 + "\"");
        result.add("\"" + 0 + "," + 10 + "\"");

        return result;

    }

    private List<String> set__6_4__TO__10_0() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 6 + "," + 4 + "\"");
        result.add("\"" + 7 + "," + 3 + "\"");
        result.add("\"" + 8 + "," + 2 + "\"");
        result.add("\"" + 9 + "," + 1 + "\"");
        result.add("\"" + 10 + "," + 0 + "\"");

        return result;

    }

    private List<String> set__5_0__TO__5_4() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 5 + "," + 4 + "\"");
        result.add("\"" + 5 + "," + 3 + "\"");
        result.add("\"" + 5 + "," + 2 + "\"");
        result.add("\"" + 5 + "," + 1 + "\"");
        result.add("\"" + 5 + "," + 0 + "\"");

        return result;

    }

    private List<String> set__5_6__TO__5_10() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 5 + "," + 6 + "\"");
        result.add("\"" + 5 + "," + 7 + "\"");
        result.add("\"" + 5 + "," + 8 + "\"");
        result.add("\"" + 5 + "," + 9 + "\"");
        result.add("\"" + 5 + "," + 10 + "\"");

        return result;

    }

    private List<String> set__0_0__TO__0_4() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 0 + "," + 4 + "\"");
        result.add("\"" + 0 + "," + 3 + "\"");
        result.add("\"" + 0 + "," + 2 + "\"");
        result.add("\"" + 0 + "," + 1 + "\"");
        result.add("\"" + 0 + "," + 0 + "\"");

        return result;

    }

    private List<String> set__0_6__TO__0_10() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 0 + "," + 6 + "\"");
        result.add("\"" + 0 + "," + 7 + "\"");
        result.add("\"" + 0 + "," + 8 + "\"");
        result.add("\"" + 0 + "," + 9 + "\"");
        result.add("\"" + 0 + "," + 10 + "\"");

        return result;

    }

    private List<String> set__0_5__TO__4_5() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 4 + "," + 5 + "\"");
        result.add("\"" + 3 + "," + 5 + "\"");
        result.add("\"" + 2 + "," + 5 + "\"");
        result.add("\"" + 1 + "," + 5 + "\"");
        result.add("\"" + 0 + "," + 5 + "\"");

        return result;

    }

    private List<String> set__6_5__TO__10_5() {

        List<String> result = new ArrayList<>();

        result.add("\"" + 6 + "," + 5 + "\"");
        result.add("\"" + 7 + "," + 5 + "\"");
        result.add("\"" + 8 + "," + 5 + "\"");
        result.add("\"" + 9 + "," + 5 + "\"");
        result.add("\"" + 10 + "," + 5 + "\"");

        return result;

    }

}
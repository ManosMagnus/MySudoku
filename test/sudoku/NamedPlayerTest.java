/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.logic.Classic;
import sudoku.logic.Sudoku;

/**
 *
 * @author Manos
 */
public class NamedPlayerTest {
    
    public NamedPlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasSavedGame method, of class NamedPlayer.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws sudoku.InvalidSudokuException
     */
    @Test
    public void testHasSavedGame() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("hasSavedGame");
        NamedPlayer instance = new NamedPlayer("Test");
        boolean expResult = false;
        boolean result = instance.hasSavedGame();
        assertEquals(expResult, result);
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        instance.saveGame(new Classic (mSudoku.loadSudoku(instance),false,false,mSudoku.getId()));
        expResult = true;
        result = instance.hasSavedGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of clearSavedGame method, of class NamedPlayer.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws sudoku.InvalidSudokuException
     */
    @Test
    public void testClearSavedGame() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("clearSavedGame");
        NamedPlayer instance = new NamedPlayer("Test");
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        instance.saveGame(new Classic (mSudoku.loadSudoku(instance),false,false,mSudoku.getId()));
        instance.clearSavedGame();
        boolean expResult = false;
        boolean result = instance.hasSavedGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSavedGame method, of class NamedPlayer.
     */
    @Test
    public void testGetSavedGame() {
        System.out.println("getSavedGame");
        NamedPlayer instance = new NamedPlayer("Test");
        Sudoku expResult = null;
        Sudoku result = instance.getSavedGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of saveGame method, of class NamedPlayer.
     */
    @Test
    public void testSaveGame() {
        System.out.println("saveGame");
        NamedPlayer instance = new NamedPlayer("Test");
        Sudoku expResult = null;
        Sudoku result = instance.getSavedGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of completeGame method, of class NamedPlayer.
     */
    @Test
    public void testCompleteGame() {
        System.out.println("completeGame");
        String gameId = "test";
        NamedPlayer instance = new NamedPlayer("Test");
        instance.completeGame(gameId, GameType.CLASSIC);
        instance.completeGame(gameId, GameType.HYPER);
        assertEquals("test", instance.getCompleteClassic().get(0));
        assertEquals("test", instance.getCompleteClassic().get(0));
    }

    /**
     * Test of getCompleteHyper method, of class NamedPlayer.
     */
    @Test
    public void testGetCompleteHyper() {
        System.out.println("getCompleteHyper");
        NamedPlayer instance = new NamedPlayer("Test");
        boolean expResult = false;
        boolean result = instance.getCompleteHyper() == null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompleteClassic method, of class NamedPlayer.
     */
    @Test
    public void testGetCompleteClassic() {
        System.out.println("getCompleteHyper");
        NamedPlayer instance = new NamedPlayer("Test");
        boolean expResult = false;
        boolean result = instance.getCompleteClassic() == null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class NamedPlayer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        NamedPlayer instance = new NamedPlayer("Test");
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStats method, of class NamedPlayer.
     */
    @Test
    public void testHasStats() {
        System.out.println("hasStats");
        NamedPlayer instance = new NamedPlayer("test");
        boolean expResult = true;
        boolean result = instance.hasStats();
        assertEquals(expResult, result);
    }

    
}

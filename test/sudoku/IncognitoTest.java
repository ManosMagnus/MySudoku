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

/**
 *
 * @author Manos
 */
public class IncognitoTest {
    public IncognitoTest() {
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
     * Test of hasSavedGame method, of class Incognito.
     */
    @Test
    public void testHasSavedGameFalse() {
        System.out.println("hasSavedGame");
        Incognito instance = new Incognito();
        boolean expResult = false;
        boolean result = instance.hasSavedGame();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testHasSavedGameTrue() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("hasSavedGame2");
        Incognito instance = new Incognito();
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        Classic game = new Classic(mSudoku.loadSudoku(instance) ,false,false,mSudoku.getId());
        instance.saveGame(game);
        boolean expResult = true;
        boolean result = instance.hasSavedGame();
        assertEquals(expResult, result);
    }
    

    /**
     * Test of clearSavedGame method, of class Incognito.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws sudoku.InvalidSudokuException
     */
    @Test
    public void testClearSavedGame() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("clearSavedGame");
        Incognito instance = new Incognito();
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        Classic game = new Classic(mSudoku.loadSudoku(instance) ,false,false,mSudoku.getId());
        instance.saveGame(game);
        instance.clearSavedGame();
        boolean result = instance.hasSavedGame();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of getSavedGame method, of class Incognito.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws sudoku.InvalidSudokuException
     */
    @Test
    public void testGetSavedGame() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("getSavedGame");
        Incognito instance = new Incognito();
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        Classic game = new Classic(mSudoku.loadSudoku(instance) ,false,false,mSudoku.getId());
        instance.saveGame(game);
        String result = instance.getSavedGame().getId();
        String expResult = mSudoku.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of saveGame method, of class Incognito.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws sudoku.InvalidSudokuException
     */
    @Test
    public void testSaveGame() throws IOException, FileNotFoundException, InvalidSudokuException {
        System.out.println("saveGame");
        Incognito instance = new Incognito();
        ManageSudoku mSudoku = new ManageSudoku(GameType.CLASSIC);
        Classic game = new Classic(mSudoku.loadSudoku(instance) ,false,false,mSudoku.getId());
        instance.saveGame(game);
        String result = instance.getSavedGame().getId();
        String expResult = mSudoku.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Incognito.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Incognito instance = new Incognito();
        String expResult = "Incognito";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasStats method, of class Incognito.
     */
    @Test
    public void testHasStats() {
        System.out.println("hasStats");
        Incognito instance = new Incognito();
        boolean expResult = false;
        boolean result = instance.hasStats();
        assertEquals(expResult, result);
    }

    
}

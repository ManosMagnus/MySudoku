package sudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.logic.Sudoku;

/**
 *
 * @author Manos
 */
public class PlayersTest {
    
    public PlayersTest() {
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
     * Test of loadPlayer method, of class Players.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadPlayer_int() throws Exception {
        System.out.println("loadPlayer");
        int index = 1000000;
        Players instance = new Players();
        assertEquals(null, instance.loadPlayer(index));
    }
}

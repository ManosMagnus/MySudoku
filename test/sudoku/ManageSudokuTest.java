/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manos
 */
public class ManageSudokuTest {
    
    public ManageSudokuTest() {
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
     * Test of loadSudoku method, of class ManageSudoku.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadSudoku() throws Exception {
        System.out.println("loadSudoku");
        Player player = null;
        ManageSudoku instance = new ManageSudoku(GameType.CLASSIC);
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.loadSudoku(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ManageSudoku.
     * @throws java.io.IOException
     */
    @Test
    public void testGetId() throws IOException {
        System.out.println("getId");
        ManageSudoku instance = new ManageSudoku(GameType.CLASSIC);
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result);
    }
    
}

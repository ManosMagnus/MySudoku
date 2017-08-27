/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku.logic;

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
public class DuiDokuTest {
    
    public DuiDokuTest() {
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
     * Test of get method, of class DuiDoku.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Sudoku instance = new DuiDoku();
        assertEquals(0, instance.get(0,0));
        assertEquals(-1, instance.get(10,3));
    }

    /**
     * Test of setCell method, of class DuiDoku this test also is test of check,checkRow,checkColumn,checkBox.
     */
    @Test
    public void testSetCell() {
        System.out.println("setCell - check - checkRow - checkColumn - checkBox");
        Sudoku instance = new DuiDoku();
        assertEquals(false, instance.setCell(10, 3, 5));
        assertEquals(false, instance.setCell(0, 0, 20));
        assertEquals(true, instance.setCell(0, 0, 2));
        assertEquals(2, instance.get(0, 0));
        
    }
    /**
     * Test of getAvailables method, of class Classic this test also test removeHelpSet and addHelpset.
     */
    @Test
    public void testGetAvailables() {
        System.out.println("getAvailables - removeHelpSet - addHelpSet");
        Sudoku instance = new DuiDoku();
        assertEquals(true, instance.getAvailables(0,0).contains(1));
        assertEquals(null, instance.getAvailables(10,10));
        instance.removeHelpSet(10, 9, 5);
        instance.removeHelpSet(8, 8, 10);
        instance.removeHelpSet(0, 0, 1);
        assertEquals(false, instance.getAvailables(0,0).contains(1));
        instance.addHelpSet(10, 9, 5);
        instance.addHelpSet(8, 8, 10);
        instance.addHelpSet(0, 0, 1);
        assertEquals(true, instance.getAvailables(0,0).contains(1));
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku.logic;

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
public class ClassicTest {
    
    public ClassicTest() {
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
     * Test of isDefault method, of class Classic.
     */
    @Test
    public void testIsDefault() {
        System.out.println("isDefault");
        ArrayList<String> game = new ArrayList<>();
        game.add("000108000"); game.add("061000700"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        Sudoku instance = new Classic(game,false,false,"test");
        assertEquals(true, instance.isDefault(0, 3));
        assertEquals(false, instance.isDefault(0, 2));
        assertEquals(false, instance.isDefault(0, 20));
    }

    /**
     * Test of get method, of class Classic.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        ArrayList<String> game = new ArrayList<>();
        game.add("000108000"); game.add("061000700"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        Sudoku instance = new Classic(game,false,false,"test");
        assertEquals(0, instance.get(0,0));
        assertEquals(1, instance.get(0,3));
        assertEquals(-1, instance.get(10,3));
    }

    /**
     * Test of setCell method, of class Classic this test also is test of check,checkRow,checkColumn,checkBox.
     */
    @Test
    public void testSetCell() {
        System.out.println("setCell - check - checkRow - checkColumn - checkBox");
        ArrayList<String> game = new ArrayList<>();
        game.add("000108000"); game.add("061000700"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        Sudoku instance = new Classic(game,false,false,"test");
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
        ArrayList<String> game = new ArrayList<>();
        game.add("000108000"); game.add("061000700"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        game.add("000000000"); game.add("000000000"); game.add("000000000");
        Sudoku instance = new Classic(game,true,false,"test");
        assertEquals(false, instance.getAvailables(0,0).contains(1));
        assertEquals(null, instance.getAvailables(10,10));
        instance.addHelpSet(10, 9, 5);
        instance.addHelpSet(8, 8, 10);
        instance.addHelpSet(0, 0, 1);
        assertEquals(true, instance.getAvailables(0,0).contains(1));
        instance.removeHelpSet(10, 9, 5);
        instance.removeHelpSet(8, 8, 10);
        instance.removeHelpSet(0, 0, 1);
        assertEquals(false, instance.getAvailables(0,0).contains(1));
        
    }
    
}

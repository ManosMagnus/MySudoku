/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku;

import java.util.HashSet;
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
public class MyConverterTest {
    
    public MyConverterTest() {
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
    
    @Test
    public void testGetInstance() {
        MyConverter myConverter1 = MyConverter.getInstance();
        MyConverter myConverter2 = MyConverter.getInstance();
        assertEquals(myConverter1, myConverter2);
    }
    

    /**
     * Test of intToChar method, of class MyConverter.
     */
    @Test
    public void testIntToChar() {
        System.out.println("intToChar");
        int a = 0;
        int b = 10;
        int c = -2;
        int d = 3;
        MyConverter instance = MyConverter.getInstance();
        assertEquals("", instance.intToChar(a));
        assertEquals(null, instance.intToChar(b));
        assertEquals(null, instance.intToChar(c));
        assertEquals("C", instance.intToChar(d));
    }

    /**
     * Test of charToInt method, of class MyConverter.
     */
    @Test
    public void testCharToInt() {
        System.out.println("charToInt");
        String a = "";
        String b = "z";
        String c = "RE";
        String d = "c";
        MyConverter instance = MyConverter.getInstance();
        assertEquals(0, instance.charToInt(a));
        assertEquals(-1, instance.charToInt(b));
        assertEquals(-1, instance.charToInt(c));
        assertEquals(3, instance.charToInt(d));
    }

    /**
     * Test of intToCharSet method, of class MyConverter.
     */
    @Test
    public void testIntToCharSet() {
        System.out.println("intToCharSet");
        HashSet<Integer> help = new HashSet<>();
        help.add(9); help.add(10); help.add(3); help.add(2);
        MyConverter instance = MyConverter.getInstance();
        assertEquals(null, instance.intToCharSet(help));
    }
    
}

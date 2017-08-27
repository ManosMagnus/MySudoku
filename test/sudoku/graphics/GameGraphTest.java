/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku.graphics;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.GameType;
import sudoku.NamedPlayer;
import sudoku.Player;
import sudoku.Players;
import sudoku.TheGame;

/**
 *
 * @author Manos
 */
public class GameGraphTest {
    
    public GameGraphTest() {
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
     * Test of setCellText method, of class GameGraph.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testSetCellText() throws IOException, ClassNotFoundException {
        System.out.println("setCellText");
        int x = 20;
        int y = 30;
        String text = "Test";
        NamedPlayer player = new NamedPlayer("Test");
        Players players = new Players();
        GameGraph instance = new GameGraph(new TheGame(GameType.CLASSIC,false,false,player,new StartMenu(player,players),players),false,true);
        instance.setCellText(x, y, text);
    }

    /**
     * Test of setCellOpponents method, of class GameGraph.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testSetCellOpponents() throws IOException, ClassNotFoundException {
        System.out.println("setCellOpponents");
        int x = 20;
        int y = 30;
        String text = "Test";
        NamedPlayer player = new NamedPlayer("Test");
        Players players = new Players();
        GameGraph instance = new GameGraph(new TheGame(GameType.CLASSIC,false,false,player,new StartMenu(player,players),players),false,true);
        instance.setCellOpponents(x, y, text);
    }
    
}

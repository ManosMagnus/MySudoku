/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sudoku;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Manos
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ sudoku.IncognitoTest.class, sudoku.NamedPlayerTest.class, sudoku.logic.LogicSuite.class, sudoku.graphics.GraphicsSuite.class, sudoku.PlayersTest.class, sudoku.ManageSudokuTest.class})
public class SudokuSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
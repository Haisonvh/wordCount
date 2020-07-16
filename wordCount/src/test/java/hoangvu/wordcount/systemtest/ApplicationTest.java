/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangvu.wordcount.systemtest;

import hoangvu.system.Constants;
import hoangvu.system.WordCountApp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HaiSonVH
 */
public class ApplicationTest {
        
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;  
    
    @BeforeEach
    public void setUp(){      
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown(){
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    //The main purpose of this test is to ensure the correct implementation of View,Presenter,Model
    @DisplayName("Should display welcome and instructionde")
    @Test
    public void shouldDisplayWelcomAndInstruction(){
        ByteArrayInputStream in = new ByteArrayInputStream("\r\n".getBytes());
        System.setIn(in);
        WordCountApp.main(null);
        assertTrue(outContent.toString().contains("Welcome to word count application"));
        assertTrue(outContent.toString().contains("Please enter file path:\r\n>>"));
    }
    
    @DisplayName("Should show both cases")
    @Test
    public void shouldShowBothCases() {
        ByteArrayInputStream in = new ByteArrayInputStream("testData_full.txt".getBytes());
        System.setIn(in);
        WordCountApp.main(null);
        assertTrue(outContent.toString().contains(Constants.NOTICE_NUMBER_OF_WORD+6));
        assertTrue(outContent.toString().contains(Constants.NOTICE_LIST_OF_WORD+"\r\n4chars\r\n5chars\r\nmianina"));
    }
}

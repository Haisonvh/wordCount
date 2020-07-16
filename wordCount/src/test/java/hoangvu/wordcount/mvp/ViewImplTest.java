/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangvu.wordcount.mvp;

import hoangvu.system.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HaiSonVH
 */
public class ViewImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private ViewImpl target;
    private PresenterMock presenter;
    public ViewImplTest() {
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        target = new ViewImpl();        
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        target = null;
    }
    
    @DisplayName("Should throw exception when set null presenter")
    @Test
    public void shouldExceptionWhenSetNullPresenter(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setPresenter(null));
        assertEquals(Constants.ERROR_NULL_PRESENTER, exception.getMessage());
    }
    
    @DisplayName("Should successfully set presenter")
    @Test
    public void shouldSuccessfullyWhenSetPresenter(){
        presenter = new PresenterMock();
        assertDoesNotThrow(() -> target.setPresenter(presenter));
    }
    
    @DisplayName("Should successfully print number of word start with M or m")
    @ParameterizedTest
    @ValueSource(longs = {1,0,1234})
    public void shouldSuccessfullyPrintData(long data){
        assertDoesNotThrow(() -> target.updateTotalWordStartWithM(data));
        assertEquals(Constants.NOTICE_NUMBER_OF_WORD+data+"\r\n", outContent.toString());
    }
    
    @DisplayName("Should print error message")
    @ParameterizedTest
    @ValueSource(strings = {"","error"})
    public void shouldSuccessfullyPrintError(String data){
        assertDoesNotThrow(() -> target.showMessage(data));
        assertEquals(data+"\r\n", outContent.toString());
    }
    
    @DisplayName("Should successfully print list of word")
    @Test
    public void shouldSuccessfullyPrintListWord(){
        List<String> data = new ArrayList<>();
        data.add("word1");
        data.add("word2");
        data.add("word3");
        assertDoesNotThrow(() -> target.updateListWord(data));
        assertEquals(Constants.NOTICE_LIST_OF_WORD+"\r\nword1\r\nword2\r\nword3\r\n", outContent.toString());
    }
    
    @DisplayName("Should not print empty list")
    @Test
    public void shouldNotPrintEmptyList(){
        List<String> data = new ArrayList<>();
        assertDoesNotThrow(() -> target.updateListWord(data));
        assertEquals("", outContent.toString());
    }
    
    @DisplayName("Should not print null list")
    @Test
    public void shouldNotPrintNullList(){
        assertDoesNotThrow((Executable) () -> target.updateListWord(null));
        assertEquals("", outContent.toString());
    }
    
    @DisplayName("Should receive file path and pass to presenter")
    @Test
    public void shouldReceiveFilePath(){
        presenter = new PresenterMock();
        target.setPresenter(presenter);
        ByteArrayInputStream in = new ByteArrayInputStream("C:\\test.txt".getBytes());
        System.setIn(in);
        target.openFile();
        assertEquals("C:\\test.txt", presenter.filePath);
    }
    
    @DisplayName("Should display information when initView")
    @Test
    public void shouldDisplayInformationWhenInitView(){
        presenter = new PresenterMock();
        target.setPresenter(presenter);        
        InputStream originalIn = System.in;  
        ByteArrayInputStream in = new ByteArrayInputStream("\r\n".getBytes());
        System.setIn(in);        
        target.initView();
        assertTrue(outContent.toString().contains("Welcome to word count application"));
        assertTrue(outContent.toString().contains("Please enter file path:\r\n>>"));
        System.setIn(originalIn);
    }

    private class PresenterMock implements Presenter {

        public String filePath;

        @Override
        public void onOpenFile(String path) {
            filePath = path;
        }

        @Override
        public void startView() {
        
        }

    }
}

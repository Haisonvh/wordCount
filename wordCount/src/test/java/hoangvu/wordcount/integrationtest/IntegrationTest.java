package hoangvu.wordcount.integrationtest;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hoangvu.system.Constants;
import hoangvu.wordcount.mvp.Model;
import hoangvu.wordcount.mvp.Presenter;
import hoangvu.wordcount.mvp.PresenterImpl;
import hoangvu.wordcount.mvp.View;
import hoangvu.wordcount.mvp.ViewImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HoangVu
 */
public class IntegrationTest {
    private View view;
    private Model model;
    private Presenter presenter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;  
    
    @BeforeEach
    public void setUp(){
        view  = new ViewImpl();
        model = new Model() {
        };
        presenter = new PresenterImpl(model, view);      
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown(){
        view  = null;
        model = null;
        presenter = null;
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    //The main purpose of this test is to ensure the communication from View to Presenter to Model
    //Because each of their unit test already pass. Heace we just need concern the output which is 
    //printed on the console screen.
    @DisplayName("Should print exit when receive empty path")
    @ParameterizedTest
    @ValueSource(strings = {"\r\n"," \r\n"})
    public void shouldPrintExitWhenReceiveEmptyPath(String data){
        ByteArrayInputStream in = new ByteArrayInputStream("\r\n".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.openFile()); 
        assertTrue(outContent.toString().contains("EXIT"));
    }
    
    @DisplayName("Should return error when cannot access file")
    @Test
    public void shouldReturnCannotAccessFile() {
        ByteArrayInputStream in = new ByteArrayInputStream("testData_1.txt".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.openFile()); 
        assertTrue(outContent.toString().contains(Constants.ERROR_CANNOT_ACCESS_FILE));
    }
    
    @DisplayName("Should return path incorrect format")
    @Test
    public void shouldReturnPathIncorrectFormat() {
        ByteArrayInputStream in = new ByteArrayInputStream("testDa**<>/_1".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.openFile()); 
        assertTrue(outContent.toString().contains(Constants.ERROR_INCORRECT_FORMAT_PATH));
    }
    
    @DisplayName("Should show both cases")
    @Test
    public void shouldShowBothCases() {
        ByteArrayInputStream in = new ByteArrayInputStream("testData_full.txt".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.openFile()); 
        assertTrue(outContent.toString().contains(Constants.NOTICE_NUMBER_OF_WORD+6));
        assertTrue(outContent.toString().contains(Constants.NOTICE_LIST_OF_WORD+"\r\n4chars\r\n5chars\r\nmianina"));
    }
    
    @DisplayName("Should update Model")
    @Test
    public void shouldUpdateModel() {
         ByteArrayInputStream in = new ByteArrayInputStream("testData_full.txt".getBytes());
        System.setIn(in);
        assertDoesNotThrow(()-> view.openFile()); 
        assertEquals("testData_full.txt", model.getFilePath());
        assertTrue(model.getCounterMap().containsKey("My"));
        assertTrue(model.getCounterMap().containsKey("name"));
        assertTrue(model.getCounterMap().containsKey("is"));
        assertTrue(model.getCounterMap().containsKey("my"));
        assertTrue(model.getCounterMap().containsKey("4chars"));
        assertTrue(model.getCounterMap().containsKey("5chars"));        
        assertTrue(model.getCounterMap().containsKey("mianina"));
    }
    
}

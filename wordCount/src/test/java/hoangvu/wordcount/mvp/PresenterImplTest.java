/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangvu.wordcount.mvp;

import hoangvu.system.Constants;
import java.util.List;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HaiSonVH
 */
public class PresenterImplTest {

    public PresenterImplTest() {
    }
    private PresenterImpl target;
    private Model model;
    private ViewMock view;

    @BeforeEach
    public void setUp() {
        model = new Model() {
        };
        view = new ViewMock();
        target = new PresenterImpl(model, view);
    }

    @AfterEach
    public void tearDown() {
        target = null;
    }

    @DisplayName("Should count correct when has both cases")
    @Test
    public void shouldCountCorrectWhenHasBothCases() {
        target.onOpenFile("testData_full.txt");
        assertEquals(6, view.totalStartM);
        String[] expect = {"4chars","5chars","mianina"};
        String[] test = new String[1];
        assertArrayEquals(expect, view.listWord.toArray(test));
    }
    
    @DisplayName("Should update Model")
    @Test
    public void shouldUpdateModel() {
        target.onOpenFile("testData_justStartM.txt");
        assertEquals("testData_justStartM.txt", model.getFilePath());
        assertTrue(model.getCounterMap().containsKey("My"));
        assertTrue(model.getCounterMap().containsKey("name"));
        assertTrue(model.getCounterMap().containsKey("is"));
        assertTrue(model.getCounterMap().containsKey("my"));
    }
    
    @DisplayName("Should count correct when has word start with m or M")
    @Test
    public void shouldCountCorrectWhenHasWordStartWithM() {
        target.onOpenFile("testData_justStartM.txt");
        assertEquals(4, view.totalStartM);
        String[] expect = new String[0];
        String[] test = new String[0];
        assertArrayEquals(expect, view.listWord.toArray(test));
    }
    
    @DisplayName("Should count correct when has word longer than 5")
    @Test
    public void shouldCountCorrectWhenHasWordLongerThan5() {
        target.onOpenFile("testData_just5Char.txt");
        assertEquals(0, view.totalStartM);
        String[] expect = {"4chars","5chars"};
        String[] test = new String[1];
        assertArrayEquals(expect, view.listWord.toArray(test));
    }
    
    @DisplayName("Should return 0 when file is empty")
    @Test
    public void shouldReturn0WhenFileEmpty() {
        target.onOpenFile("testData_empty.txt");
        assertEquals(0, view.totalStartM);
        String[] expect = new String[0];
        String[] test = new String[0];
        assertArrayEquals(expect, view.listWord.toArray(test));
    }
    
    @DisplayName("Should return error when open a null path")
    @Test
    public void shouldReturnErrorWhenOpenNullPath() {
        target.onOpenFile(null);
        assertEquals(Constants.ERROR_NULL_FILE_PATH, view.message);
    }
    
    @DisplayName("Should return error when cannot access file ")
    @Test
    public void shouldReturnCannotAccessFile() {
        target.onOpenFile("testData_1.txt");
        assertEquals(Constants.ERROR_CANNOT_ACCESS_FILE, view.message);
    }
    
    @DisplayName("Should return path incorrect format")
    @Test
    public void shouldReturnPathIncorrectFormat() {
        target.onOpenFile("testDa**<>/_1");
        assertEquals(Constants.ERROR_INCORRECT_FORMAT_PATH, view.message);
    }
    
    @DisplayName("Should print exit when receive empty path")
    @ParameterizedTest
    @ValueSource(strings = {""," "})
    public void shouldPrintExitWhenReceiveEmptyPath(String data){
        assertDoesNotThrow(() -> target.onOpenFile(data));
        assertEquals(Constants.NOTICE_EXIT, view.message);
    }

    private class ViewMock implements View {

        public Long totalStartM;
        public String message;
        public List<String> listWord;

        @Override
        public void setPresenter(Presenter presenter) throws NullPointerException {
        }

        @Override
        public void updateTotalWordStartWithM(Long total) {
            totalStartM = total;
        }

        @Override
        public void updateListWord(List<String> data) {
            listWord = data;
        }

        @Override
        public void showMessage(String message) {
            this.message = message;
        }

        @Override
        public void openFile() {
        }

        @Override
        public void initView() {
        
        }
    }
}

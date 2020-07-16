package hoangvu.wordcount.mvp;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
public class ModelTest {

    Model target;

    public ModelTest() {

    }

    @BeforeEach
    public void setUp() {
        target = new Model() {
        };
    }

    @AfterEach
    public void tearDown() {
        target = null;
    }
    
    @DisplayName("Should successfully set path")
    @ParameterizedTest
    @ValueSource(strings = {"test.txt", "C:\\path",""})
    public void shouldSuccessfullySetPath(String path) {
        assertDoesNotThrow(()->target.setFilePath(path));
        assertEquals(path,target.getFilePath());
    }
    
    @DisplayName("Should throw exception when set null path")
    @Test
    public void shouldThrowExceptionWhenSetNullPath() {
        NullPointerException ex = assertThrows(NullPointerException.class,()->target.setFilePath(null));
        assertEquals("File path should not be null",ex.getMessage());
    }
    
    @DisplayName("Should successfully set empty map data")
    @Test
    public void shouldSuccessfullySetEmptyMapData() {
        assertDoesNotThrow(()->target.setCounterMap(new HashMap<>()));
        assertEquals(0,target.getCounterMap().size());
    }
    
    @DisplayName("Should successfully set map data")
    @Test
    public void shouldSuccessfullySetMapData() {
        Map<String,Long> temp = new HashMap<>();
        temp.put("Data", Long.valueOf(1));
        assertDoesNotThrow(()->target.setCounterMap(temp));
        assertTrue(target.getCounterMap().containsKey("Data"));
    }
    
    @DisplayName("Should throw exception when set null map Data")
    @Test
    public void shouldThrowExceptionWhenSetNullMapData() {
        NullPointerException ex = assertThrows(NullPointerException.class,()->target.setCounterMap(null));
        assertEquals("CounterMap should not be null",ex.getMessage());
    }
}

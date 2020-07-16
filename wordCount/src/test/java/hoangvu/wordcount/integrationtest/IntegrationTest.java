package hoangvu.wordcount.integrationtest;

import hoangvu.wordcount.mvp.Model;
import hoangvu.wordcount.mvp.Presenter;
import hoangvu.wordcount.mvp.View;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
}

package hoangvu.wordcount.mvp;

import hoangvu.system.Constants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author HoangVu
 */
public class PresenterImpl implements Presenter {

    private Model model;
    private View view;

    public PresenterImpl(Model model, final View view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onOpenFile(String path) {
        try {
            model.setFilePath(path);
            if (path.trim().equals("")) {
                view.showMessage(Constants.NOTICE_EXIT);
            } else {
                Map<String, Long> counterMap = Files.lines(Paths.get(path))
                        .flatMap(line -> Arrays.stream(line.trim().split(" ")))
                        .parallel()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                model.setCounterMap(counterMap);

                //process and update view
                Long totalM = counterMap.entrySet().stream()
                        .parallel()
                        .filter(w -> w.getKey().toLowerCase().startsWith("m"))
                        .map(map -> map.getValue())
                        .reduce(Long.valueOf(0), Long::sum);
                view.updateTotalWordStartWithM(totalM);

                List<String> fiveCharsWordList = counterMap.keySet().stream()
                        .parallel()
                        .filter(w -> w.length() > 5)
                        .collect(Collectors.toList());
                view.updateListWord(fiveCharsWordList);
            }

        } catch (IOException ex) {
            Logger.getLogger(PresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
            view.showMessage(Constants.ERROR_CANNOT_ACCESS_FILE);
        } catch (InvalidPathException ex) {
            Logger.getLogger(PresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
            view.showMessage(Constants.ERROR_INCORRECT_FORMAT_PATH);
        } catch (NullPointerException ex) {
            Logger.getLogger(PresenterImpl.class.getName()).log(Level.SEVERE, null, ex);
            view.showMessage(ex.getMessage());
        }

    }

    @Override
    public void startView() {
        view.initView();
    }
}

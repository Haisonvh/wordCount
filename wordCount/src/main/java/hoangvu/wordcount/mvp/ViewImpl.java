
package hoangvu.wordcount.mvp;

import hoangvu.system.Constants;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HoangVu
 */
public class ViewImpl implements View{
    private Presenter presenter;
    
    @Override
    public void setPresenter(final Presenter presenter) throws NullPointerException {
        if (presenter == null)
            throw new NullPointerException(Constants.ERROR_NULL_PRESENTER);
        this.presenter = presenter;
    }

    @Override
    public void updateTotalWordStartWithM(Long total) {
        System.out.println(Constants.NOTICE_NUMBER_OF_WORD+total);
    }

    @Override
    public void updateListWord(List<String> data) {
        if (data!=null && !data.isEmpty()){
            System.out.println(Constants.NOTICE_LIST_OF_WORD);
            data.forEach(line -> {System.out.println(line);});
        }        
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void openFile() {
        Scanner scanner = new Scanner(System.in);        
        String path = scanner.nextLine();
        presenter.onOpenFile(path);
    }

    @Override
    public void initView() {
        System.out.println("------------------------------");
        System.out.println("Welcome to word count application");
        System.out.println("------------------------------");
        System.out.println("Please enter file path:");
        System.out.print(">>");
        openFile();
    }
    
}

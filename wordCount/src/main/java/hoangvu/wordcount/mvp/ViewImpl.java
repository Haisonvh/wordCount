
package hoangvu.wordcount.mvp;

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
            throw new NullPointerException("Presenter is null");
        this.presenter = presenter;
    }

    @Override
    public void updateTotalWordStartWithM(Long total) {
        System.out.println("Number of Word start with M or m: "+total);
    }

    @Override
    public void updateListWord(List<String> data) {
        if (data!=null && !data.isEmpty()){
            System.out.println("List of word longer than 5 characters:");
            data.forEach(line -> {System.out.println(line);});
        }        
    }

    @Override
    public void showError(String message) {
        System.out.println("ERROR: "+message);
    }

    @Override
    public void openFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------");
        System.out.println("Welcome to word count application");
        System.out.println("------------------------------");
        System.out.println("Please enter file path:");
        System.out.print(">>");
        String path = scanner.nextLine();
        presenter.onOpenFile(path);
    }
    
}

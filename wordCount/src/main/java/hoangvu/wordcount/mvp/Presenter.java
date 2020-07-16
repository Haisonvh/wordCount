
package hoangvu.wordcount.mvp;

/**
 * @author HoangVu
 */
public interface Presenter {

    /**
     * this method allow to open file via the path
     * @param path directory of the file
     */
    void onOpenFile(String path);
    
    /**
     * 
     */
    void startView();
}

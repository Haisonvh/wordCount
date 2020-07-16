/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangvu.wordcount.mvp;

import java.util.List;

/**
 * This interface represent the view of application
 * Display information and interact with user
 * @author HaiSonVH
 */
public interface View {
    /**
     * Set the Presenter for the View
     * @param presenter 
     * @throws NullPointerException when the presenter is null;
     */
    void setPresenter(final Presenter presenter) throws NullPointerException;
    
    /**
     * This method will allow view update detail of total word start with M
     * @param total number of word
     */
    void updateTotalWordStartWithM(Long total);
    
    /**
     * This method will allow view update List of word which has more than 5 characters
     * @param data List of word
     */
    void updateListWord(List<String> data);
    
    /**
     * This method will allow view display message
     * @param message error message need to be displayed
     */
    void showMessage(String message);
    
    
    /**
     * This method allows to open the file 
     */
    void openFile();
    
    /**
    *This method allow to start the view 
    */
    void initView();
}

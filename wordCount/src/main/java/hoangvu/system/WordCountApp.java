/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangvu.system;

import hoangvu.wordcount.mvp.Model;
import hoangvu.wordcount.mvp.Presenter;
import hoangvu.wordcount.mvp.PresenterImpl;
import hoangvu.wordcount.mvp.View;
import hoangvu.wordcount.mvp.ViewImpl;

/**
 *
 * @author HaiSonVH
 */
public class WordCountApp {
    public static void main(String[] args) {        
        View view = new ViewImpl();
        Model model = new Model() {
        };
        Presenter presenter = new PresenterImpl(model, view);
        presenter.startView();
    }
}

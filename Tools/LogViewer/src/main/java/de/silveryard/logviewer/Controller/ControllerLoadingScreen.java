package de.silveryard.logviewer.Controller;

import de.silveryard.logviewer.Utility.IAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * Created by Sebif on 11.04.2017.
 */
public class ControllerLoadingScreen extends Dialog {
    @FXML
    private ProgressBar pbProgress;
    @FXML
    private Label lblStatus;

    private ILoadingScreenHandler handler;

    protected ControllerLoadingScreen(Stage primaryStage, String title, ILoadingScreenHandler handler) {
        super(primaryStage, title, "/de/silveryard/logviewer/fxml/loadingscreen.fxml", 400, 200);
        this.handler = handler;
        Thread thread = new Thread(this::runThread);
        thread.start();
    }

    private void runThread(){
        handler.invoke(this::statusSetter);
        runOnUIThread(getStage()::close);
    }
    private void statusSetter(String status){
        runOnUIThread(new IAction() {
            @Override
            public void invoke() {
                lblStatus.setText(status);
            }
        });
    }

    private void runOnUIThread(IAction action){
        Platform.runLater(action::invoke);
    }
}

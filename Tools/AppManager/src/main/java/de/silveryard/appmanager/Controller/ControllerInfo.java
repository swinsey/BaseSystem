package de.silveryard.appmanager.Controller;

import de.silveryard.appmanager.Utility.GraphicsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by Sebif on 14.03.2017.
 */
public class ControllerInfo extends Dialog {
    @FXML
    private ImageView ivIcon;
    @FXML
    private Label tbText;
    @FXML
    private Button btnBad;
    @FXML
    private Button btnGood;

    protected ControllerInfo(Stage primaryStage, String title, InfoType type, String text, String goodText, String badText) {
        super(primaryStage, title, "/de/silveryard/appmanager/fxml/info.fxml", 400, 200);

        tbText.setText(text);
        if(goodText == null){
            btnGood.setDisable(true);
            btnGood.setVisible(false);
        }else{
            btnGood.setText(goodText);
        }
        if(badText == null){
            btnBad.setDisable(true);
            btnBad.setVisible(false);
        }else{
            btnBad.setText(badText);
        }

        Image image = null;
        switch(type){
            case INFO:
                image = GraphicsManager.getInfoIcon();
                break;
            case ERROR:
                image = GraphicsManager.getErrorIcon();
                break;
            case QUESTION:
                image = GraphicsManager.getQuestionIcon();
                break;
        }
        ivIcon.setImage(image);
    }

    @FXML
    private void btnBad_Click(ActionEvent event){
        result = DialogResult.CANCEL;
        getStage().close();
    }
    @FXML
    private void btnGood_Click(ActionEvent event){
        result = DialogResult.OK;
        getStage().close();
    }
}

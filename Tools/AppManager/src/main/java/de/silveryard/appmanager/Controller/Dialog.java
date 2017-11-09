package de.silveryard.appmanager.Controller;

import de.silveryard.appmanager.Utility.GraphicsManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Sebif on 14.03.2017.
 */
public class Dialog {
    private Stage stage;
    protected DialogResult result;

    protected Dialog(Stage primaryStage, String title, String resource, int width, int height){
        try {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            stage.setTitle(title);
            GraphicsManager.setIcons(stage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            loader.setController(this);
            Parent root = loader.load();
            Scene dialogScene = new Scene(root, width, height);
            stage.setScene(dialogScene);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected Stage getStage(){
        return stage;
    }

    public DialogResult showDialog(){
        stage.showAndWait();
        return result;
    }
}

package de.silveryard.appmanager.Utility;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 14.03.2017.
 */
public abstract class GraphicsManager {
    private static List<Image> icons;
    public static void setIcons(Stage stage){
        if(icons == null){
            icons = new ArrayList<>();
            icons.add(new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/icon/icon_32.png")));
            icons.add(new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/icon/icon_64.png")));
            icons.add(new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/icon/icon_128.png")));
            icons.add(new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/icon/icon_256.png")));
        }

        for(Image icon : icons){
            stage.getIcons().add(icon);
        }
    }

    private static Image unknownIcon;
    public static Image getUnknownIcon(){
        if(unknownIcon == null){
            unknownIcon = new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/images/unknown_icon.png"));
        }

        return unknownIcon;
    }

    private static Image infoIcon;
    public static Image getInfoIcon(){
        if(infoIcon == null){
            infoIcon = new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/images/info_128.png"));
        }

        return infoIcon;
    }

    private static Image errorIcon;
    public static Image getErrorIcon(){
        if(errorIcon == null){
            errorIcon = new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/images/notification_128.png"));
        }

        return errorIcon;
    }

    private static Image questionIcon;
    public static Image getQuestionIcon(){
        if(questionIcon == null){
            questionIcon = new Image(GraphicsManager.class.getResourceAsStream("/de/silveryard/appmanager/images/question_128.png"));
        }

        return questionIcon;
    }
}

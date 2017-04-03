package de.silveryard.appmanager.Controller;

import de.silveryard.appmanager.Model.App;
import de.silveryard.appmanager.Model.AppManager;
import de.silveryard.appmanager.Utility.IActionP1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.ByteArrayInputStream;


/**
 * Created by Sebif on 14.03.2017.
 */
public class ControllerAppListItem extends Parent {
    private App app;

    private boolean hovered;
    private Background defaultBackground;
    private Background pressedBackground;

    private IActionP1<ControllerAppListItem> clickHandler;

    @FXML
    private GridPane gridPaneBackground;
    @FXML
    private Label lblAppName;
    @FXML
    private Label lblAppIdentifier;
    @FXML
    private ImageView ivImage;

    private Image appIcon;

    public ControllerAppListItem(App app, IActionP1<ControllerAppListItem> clickHandler){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/silveryard/appmanager/fxml/applistitem.fxml"));
            loader.setController(this);
            Parent parent = loader.load();
            this.getChildren().add(parent);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        lblAppName.setText(app.getAppName());
        lblAppIdentifier.setText(app.getAppIdentifier());

        defaultBackground = gridPaneBackground.getBackground();
        pressedBackground = new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY));

        this.clickHandler = clickHandler;
        this.app = app;

        byte[] iconData = AppManager.getAppIcon(app.getAppIdentifier(), 128);
        appIcon = new Image(new ByteArrayInputStream(iconData));
        ivImage.setImage(appIcon);
    }

    public App getApp(){
        return app;
    }

    public Image getAppIcon(){
        return appIcon;
    }

    @FXML
    private void item_Clicked(MouseEvent event){
        clickHandler.invoke(this);
    }

    public void item_MouseEntered(MouseEvent mouseEvent) {
        hovered = true;
    }
    public void item_MouseExited(MouseEvent mouseEvent) {
        hovered = false;
    }

    public void item_MousePressed(MouseEvent mouseEvent) {
        hovered = true;
        gridPaneBackground.setBackground(pressedBackground);
    }
    public void item_MouseReleased(MouseEvent mouseEvent) {
        gridPaneBackground.setBackground(defaultBackground);
    }
}

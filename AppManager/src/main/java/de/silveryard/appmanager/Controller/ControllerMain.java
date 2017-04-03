package de.silveryard.appmanager.Controller;

import de.silveryard.appmanager.Model.App;
import de.silveryard.appmanager.Model.AppManager;
import de.silveryard.appmanager.Model.Connection;
import de.silveryard.appmanager.Utility.GraphicsManager;
import de.silveryard.appmanager.Utility.IActionP1;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 14.03.2017.
 */
public class ControllerMain extends Application {
    private class InstallData{
        public Path path;
        public boolean force;
        public boolean done;
    }

    private Stage stage;

    @FXML
    private TextField tfConnection;
    @FXML
    private Button btnConnection;
    @FXML
    private Label lblConnectionStatus;

    @FXML
    private VBox vboxAppList;

    @FXML
    private ImageView ivIcon;
    @FXML
    private Label lblAppName;
    @FXML
    private Label lblAppIdentifier;
    @FXML
    private Label lblVersion;
    @FXML
    private Label lblVersionLabel;
    @FXML
    private Button btnUninstall;
    @FXML
    private Button btnInstall;

    private List<ControllerAppListItem> appListItems;
    private int selectedApp;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("App Manager");
        stage.setMaximized(true);
        GraphicsManager.setIcons(stage);
        URL resource = getClass().getResource("/de/silveryard/appmanager/fxml/main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        appListItems = new ArrayList<>();
        selectedApp = -1;
        updateAppSelection();

        Connection.registerDisconnectHandler(this::connection_Disconnect);
    }

    private void refreshAppList(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vboxAppList.getChildren().clear();
                appListItems.clear();

                if(!Connection.isConnected()){
                    return;
                }

                List<App> apps = AppManager.refreshAppList();
                for(int i = 0; i < apps.size(); i++){
                    ControllerAppListItem item = new ControllerAppListItem(apps.get(i), ControllerMain.this::appClickHandler);
                    vboxAppList.getChildren().add(item);
                    appListItems.add(item);
                }
            }
        });
    }
    private void appClickHandler(ControllerAppListItem item){
        selectedApp = appListItems.indexOf(item);
        updateAppSelection();
    }
    private void updateAppSelection(){
        ivIcon.setDisable(true);
        lblAppName.setDisable(true);
        lblAppIdentifier.setDisable(true);
        lblVersion.setDisable(true);
        lblVersionLabel.setDisable(true);
        btnUninstall.setDisable(true);
        if(selectedApp == -1){
            return;
        }

        ControllerAppListItem item = appListItems.get(selectedApp);
        App app = item.getApp();
        ivIcon.setDisable(false);
        lblAppName.setDisable(false);
        lblAppIdentifier.setDisable(false);
        lblVersion.setDisable(false);
        lblVersionLabel.setDisable(false);
        btnUninstall.setDisable(false);

        ivIcon.setImage(item.getAppIcon());
        lblAppName.setText(app.getAppName());
        lblAppIdentifier.setText(app.getAppIdentifier());
        lblVersion.setText(app.getVersion());
    }

    @FXML
    private void btnConnection_Click(ActionEvent actionEvent) {
        if(Connection.isConnected()){
            Connection.disconnect();
        }else{
            ControllerLoadingScreen cls = new ControllerLoadingScreen(stage, "Connecting...", this::connectionThread);
            cls.showDialog();
        }
    }
    @FXML
    private void btnUninstall_Click(ActionEvent actionEvent){
        ControllerLoadingScreen cls = new ControllerLoadingScreen(stage, "Uninstalling...", this::uninstallThread);
        cls.showDialog();
    }
    @FXML
    private void btnInstall_Click(ActionEvent actionEvent){
        ControllerLoadingScreen cls = new ControllerLoadingScreen(stage, "Installing Appplication Package File", this::installThread);
        cls.showDialog();
    }

    private void connection_Disconnect(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lblConnectionStatus.setText("Not Connected");
                tfConnection.setDisable(false);
                tfConnection.setVisible(true);
                tfConnection.setText("");
                btnConnection.setText("Connect");

                selectedApp = -1;
                updateAppSelection();
                refreshAppList();
            }
        });
    }

    private void connectionThread(IActionP1<String> statusSetter){
        statusSetter.invoke("Connecting...");
        boolean result = Connection.connect(tfConnection.getText());
        if(!result){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ControllerInfo info = new ControllerInfo(stage, "Failed to connect", InfoType.ERROR, "Failed to connect to the device", "OK", null);
                    info.showDialog();
                }
            });
            return;
        }

        statusSetter.invoke("Getting Apps...");
        refreshAppList();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lblConnectionStatus.setText("Connected");
                tfConnection.setDisable(true);
                tfConnection.setVisible(false);
                btnConnection.setText("Disconnect");
            }
        });
    }
    private void uninstallThread(IActionP1<String> statusSetter){
        statusSetter.invoke("Uninstalling");
        App app = appListItems.get(selectedApp).getApp();
        int result = AppManager.uninstallApp(app);

        if(result != 1){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ControllerInfo info = new ControllerInfo(stage,
                            "Uninstallation failed", InfoType.ERROR,
                            "Failed to uninstall app. Error Code: " + result, "OK", null);
                    info.showDialog();
                }
            });
        }

        statusSetter.invoke("Refreshing apps");
        refreshAppList();
    }
    private void installThread(IActionP1<String> statusSetter){
        InstallData installData = new InstallData();

        statusSetter.invoke("Prepairing...");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Application Package File");
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Application Package Files", "*.apf"));
                File file = fileChooser.showOpenDialog(null);
                if(file == null){
                    return;
                }

                ControllerInfo info = new ControllerInfo(
                        stage, "Force Installation?", InfoType.QUESTION,
                        "Would you like to force the installation? In case there is already an application with same or higher" +
                                " version, you can only install with force option enabled",
                        "Yes", "No"
                );
                DialogResult result = info.showDialog();
                boolean force = result == DialogResult.OK;

                Path path = Paths.get(file.toString());

                installData.path = path;
                installData.force = force;
                installData.done = true;
            }
        });

        while(!installData.done){
            try{
                Thread.sleep(200);
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        statusSetter.invoke("Installing...");
        int result = AppManager.installApp(installData.path, installData.force);
        if(result != 1){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ControllerInfo info = new ControllerInfo(stage,
                            "Installation failed", InfoType.ERROR,
                            "Failed to install app. Error Code: " + result, "OK", null);
                    info.showDialog();
                }
            });
        }

        statusSetter.invoke("Refreshing apps");
        refreshAppList();
    }
}

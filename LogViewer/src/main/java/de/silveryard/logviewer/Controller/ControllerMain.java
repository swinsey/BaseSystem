package de.silveryard.logviewer.Controller;

import de.silveryard.logviewer.Model.Connection;
import de.silveryard.logviewer.Model.LogMessageType;
import de.silveryard.logviewer.Utility.GraphicsManager;
import de.silveryard.logviewer.Utility.IActionP1;
import de.silveryard.transport.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebif on 11.04.2017.
 */
public class ControllerMain extends Application {
    private class InstallData{
        public Path path;
        public boolean force;
        public boolean done;
    }

    private Stage stage;
    private Map<String, Tab> tabs;
    private Map<Tab, ListView<String>> nodes;

    @FXML
    private TextField tfConnection;
    @FXML
    private Button btnConnection;
    @FXML
    private Label lblConnectionStatus;
    @FXML
    private TabPane tpLogs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Log Viewer");
        stage.setMaximized(true);
        GraphicsManager.setIcons(stage);
        URL resource = getClass().getResource("/de/silveryard/logviewer/fxml/main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        tabs = new HashMap<>();
        nodes = new HashMap<>();

        Connection.registerDisconnectHandler(this::connection_Disconnect);
    }

    private void addTab(String title){
        ListView<String> node = new ListView<>();
        Tab tab = new Tab(title, node);

        tabs.put(title, tab);
        nodes.put(tab, node);

        tpLogs.getTabs().add(tab);
    }
    private void clearTabs(){
        tpLogs.getTabs().clear();
        tabs.clear();
        nodes.clear();
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

    private void connection_Disconnect(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lblConnectionStatus.setText("Not Connected");
                tfConnection.setDisable(false);
                tfConnection.setVisible(true);
                tfConnection.setText("");
                btnConnection.setText("Connect");
                clearTabs();
            }
        });
    }

    private void handleLogMessage(Message message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String logger = message.getParams().get(0).getString();
                String msg = message.getParams().get(1).getString();
                LogMessageType type = LogMessageType.getEnumValue(message.getParams().get(2).getInt());

                Tab tab = tabs.get(logger);
                if(tab == null){
                    addTab(logger);
                    tab = tabs.get(logger);
                }
                ListView<String> node = nodes.get(tab);
                String str = "[" + type + "]: " + msg;
                node.getItems().add(str);

                tpLogs.getSelectionModel().select(tab);
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

        clearTabs();
        Connection.registerMessageHandler("de.silveryard.basesystem.networkinterface.logviewer.onMessageLog", this::handleLogMessage);
        Connection.sendMessage("de.silveryard.basesystem.networkinterface.logviewer.registerLogListener", new ArrayList<>());

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
}

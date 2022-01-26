/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.IndexRange;
import javafx.stage.Stage;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 *
 * @author ahmedsalah
 */
public class Notepad extends Application {

    @Override
    public void start(Stage primaryStage) {

        MenuBar manubar = new MenuBar();
        Menu fileMenu = new Menu("file");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        MenuItem newOption = new MenuItem("New");
        MenuItem openOption = new MenuItem("Open");
        MenuItem saveOption = new MenuItem("Save");
        MenuItem exitOption = new MenuItem("Exit");

        MenuItem undoOption = new MenuItem("Undo");
        MenuItem cutOption = new MenuItem("Cut");
        MenuItem copyOption = new MenuItem("Copy");
        MenuItem pastOption = new MenuItem("Past");
        MenuItem deleteOption = new MenuItem("Delete");
        MenuItem selectAllOption = new MenuItem("Select All");

        MenuItem aboutOption = new MenuItem("About Notepad");

        fileMenu.getItems().addAll(newOption, openOption, saveOption, exitOption);
        editMenu.getItems().addAll(undoOption, cutOption, copyOption, pastOption, deleteOption, selectAllOption);
        helpMenu.getItems().addAll(aboutOption);

        manubar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        TextArea txtEditor = new TextArea();

        BorderPane pane = new BorderPane();
        pane.setTop(manubar);
        pane.setCenter(txtEditor);

        Scene s = new Scene(pane, 300, 400);
        txtEditor.setMaxWidth(s.getWidth());
        txtEditor.setMaxHeight(s.getHeight());

        Stage st = new Stage();
        st.setScene(s);
        st.show();

        newOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.clear();
        });

        saveOption.setOnAction((ActionEvent event) -> {
            FileChooser fc = new FileChooser();
            File saveFile = fc.showSaveDialog(null);
            try {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("save path");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                dialog.setContentText(saveFile.toString());
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        exitOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Platform.exit();
        });

        undoOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.undo();
        });

        cutOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.cut();
        });

        copyOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.copy();
        });

        pastOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.paste();
        });

        deleteOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            IndexRange selection = txtEditor.getSelection();
            int start = selection.getStart();
            int end = selection.getEnd();
            txtEditor.deleteText(start, end);
        });

        selectAllOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txtEditor.selectAll();
        });

        openOption.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            FileChooser fc = new FileChooser();
            File openFile = fc.showOpenDialog(st);
            try {
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("open path");
                ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                dialog.setContentText(openFile.toString());
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }

        });
        aboutOption.setOnAction((ActionEvent event) -> {

            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("about");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            dialog.setContentText("ahmed mohamed salah\nOpenSource Application intake 42");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

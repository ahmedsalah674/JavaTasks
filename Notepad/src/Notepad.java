/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
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
        newOption.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        MenuItem openOption = new MenuItem("Open");
        openOption.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        MenuItem saveOption = new MenuItem("Save");
        saveOption.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        MenuItem exitOption = new MenuItem("Exit");
        exitOption.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
        
        MenuItem undoOption = new MenuItem("Undo");
        undoOption.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        MenuItem cutOption = new MenuItem("Cut");
        cutOption.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        MenuItem copyOption = new MenuItem("Copy");
        copyOption.setAccelerator(KeyCombination.keyCombination("Ctrl+c"));
        MenuItem pastOption = new MenuItem("Past");
        pastOption.setAccelerator(KeyCombination.keyCombination("Ctrl+v"));
        MenuItem deleteOption = new MenuItem("Delete");
        deleteOption.setAccelerator(KeyCombination.keyCombination("Ctrl+d"));
        MenuItem selectAllOption = new MenuItem("Select All");
        selectAllOption.setAccelerator(KeyCombination.keyCombination("Ctrl+a"));
        
        MenuItem aboutOption = new MenuItem("About Notepad");
        aboutOption.setAccelerator(KeyCombination.keyCombination("Ctrl+i"));
        SeparatorMenuItem sep = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        
        fileMenu.getItems().addAll(newOption, openOption, saveOption, sep, exitOption);
        editMenu.getItems().addAll(undoOption, cutOption, copyOption, pastOption, deleteOption, sep2, selectAllOption);
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
            File savefile = fc.showSaveDialog(null);
            try {
                FileWriter fw = new FileWriter(savefile);
                fw.write(txtEditor.getText());
                fw.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
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
            if (openFile != null) {
                FileInputStream fis = null;
                int size;
                byte[] byto;
                try {
                    fis = new FileInputStream(openFile);
                    size = fis.available();
                    byto = new byte[size];
                    fis.read(byto);
                    txtEditor.setText(new String(byto));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
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

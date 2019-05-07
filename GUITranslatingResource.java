import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent; 
import java.io.FileNotFoundException;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class GUITranslatingResource extends Scene{

    private static VBox leftPane, rightPane;
    private static HBox controls;
    private static TextField txtTranslation;
    private static Label lblTitle, lblOriginal;
    private static Button bttnSave, bttnFree, bttnToAmend;
    private static BorderPane mainPane;

    public GUITranslatingResource(Recurso r) {
        super(new Translating(r));
    }

    public static class Translating extends GridPane{
        public Translating(Recurso r) {
            r.setCurrentStatus("translating");

            mainPane= new BorderPane();
            getChildren().add(mainPane);
            
            bttnSave = new Button("Save");
            controls.getChildren().add(bttnSave);
            bttnSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    save(r);
                }
            });
            bttnFree = new Button("Free");
            controls.getChildren().add(bttnFree);
            bttnFree.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    free(r);
                    bttnFree.setOnAction(f -> GUILogin.changeScene(new GUITranslator()));
                }
            });
            bttnToAmend = new Button("Amend");
            controls.getChildren().add(bttnToAmend);
            bttnToAmend.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    amend(r);
                    bttnToAmend.setOnAction(a -> GUILogin.changeScene(new GUITranslator()));
                }
            });
            
            lblTitle = new Label(r.getTitle());
            lblOriginal = new Label(r.getTextualContent());
            leftPane = new VBox(lblTitle, lblOriginal);

            txtTranslation = new TextField(r.getTranslatedConent());
            controls = new HBox(bttnSave);
            rightPane = new VBox(txtTranslation, controls);

            lblTitle.setPrefWidth(100);
            lblOriginal.setPrefWidth(100);
            lblOriginal.setPrefHeight(500);
            
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
        }
        private static void save(Recurso r){
            r.setTranslatedContent(txtTranslation.getText());
            /*
            try{
                ArrayList<Recurso> tempArrayList = AppLogin.getArraylistResource();
                for(Recurso r: tempArrayList){
                    r[i] = data.get(i);
                }
                tempArrayList.add(r);
                AppLogin.setArraylistResource();
                FileOutputStream fos = new FileOutputStream("Movies.oop");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(tempArrayList);
                oos.close();
            } catch(FileNotFoundException fe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File not found");
                alert.setHeaderText("Error while reading the file");
                alert.setContentText(fe.getMessage());
                alert.showAndWait();
            } catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
            */
        }
        private static void free(Recurso r){
            r.setCurrentStatus("finding");
        }
        private static void amend(Recurso r){
            r.setCurrentStatus("to amend");
        }
    }
}

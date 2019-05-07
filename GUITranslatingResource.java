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
import javafx.geometry.VPos;

public class GUITranslatingResource extends Scene{

    private static ArrayList<Recurso> resources;
    private static Recurso r;
    private static BorderPane mainPane;
    private static VBox leftPane, rightPane;
    private static Text lblOriginal;
    private static TextArea txtTranslation;
    private static HBox controls;
    private static Button bttnSave, bttnToAmend, bttnFreeResource;
    private static int index;
    
    public GUITranslatingResource() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane{
        public SignUpPane() {
            
            //select the resource to translate
            resources = new ArrayList<Recurso>();
            readData();
            for(int i = 0; i<resources.size(); i++){
                if(resources.get(i).getTranslating()){
                    index = i;
                    r = resources.get(i);
                    break;
                }
            }    
            mainPane = new BorderPane();
            mainPane.setPrefWidth(900);
            mainPane.setPrefHeight(620);
            getChildren().add(mainPane);
            controls = new HBox();

            bttnSave = new Button("Save");
            controls.getChildren().add(bttnSave);
            bttnSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    saveData();
                }
            });

            bttnToAmend = new Button("Amend");
            controls.getChildren().add(bttnToAmend);
            bttnToAmend.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    amend();
                }
            });
            
            bttnFreeResource = new Button("Free resource");
            controls.getChildren().add(bttnFreeResource);
            bttnFreeResource.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    FreeResource();
                }
            });

            lblOriginal = new Text(0,0,r.getTextualContent());
            lblOriginal.setWrappingWidth(300);
            lblOriginal.setTextAlignment(TextAlignment.JUSTIFY);
            lblOriginal.setTextOrigin(VPos.TOP);
            txtTranslation = new TextArea(r.getTranslatedConent());
            txtTranslation.setWrapText(true);
            
            txtTranslation.setPrefHeight(500);
            txtTranslation.setPrefWidth(300);

            rightPane = new VBox();
            rightPane.getChildren().add(txtTranslation);
            rightPane.getChildren().add(controls);
            leftPane = new VBox();
            leftPane.getChildren().add(lblOriginal);
            mainPane.setRight(rightPane);
            mainPane.setLeft(leftPane);
            mainPane.setMargin(rightPane, new Insets(20, 50, 10, 0));
            mainPane.setMargin(leftPane, new Insets(30, 0, 10, 50));
            
        }
        public static void saveData() {
            ArrayList<Recurso> tempArray = AppLogin.getArraylistResource();

            tempArray.get(index).setTranslatedContent(txtTranslation.getText());

            AppLogin.setArraylistResource(tempArray);
        }
        private void amend(){

            try{
                r.setCurrentStatus("to amend");
            } catch (EmptyFieldException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Title cannot be null");
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

            GUILogin.changeScene(new GUITranslator());
        }
        private void FreeResource(){
            try{
                r.setCurrentStatus("freed");
            } catch (EmptyFieldException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Title cannot be null");
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                resources.add(AppLogin.getArraylistResource().get(i));
            }
        }
    }
}

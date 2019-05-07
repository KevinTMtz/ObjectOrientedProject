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

    private static ArrayList<Recurso> resources;
    private static Recurso r;
    private static BorderPane mainPane;
    private static VBox leftPane, rightPane;
    private static Label lblOriginal;
    private static TextArea txtTranslation;
    private static HBox controls;
    private static Button bttnSave, bttnToAmend;
    
    public GUITranslatingResource() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane{
        public SignUpPane() {
            
            //select the resource to translate
            resources = new ArrayList<Recurso>();
            readData();
            for(int i = 0; i<resources.size(); i++)
                if(resources.get(i).getCurrentStatus().equals("translating")){
                    r = resources.get(i);
                    break;
                }
                
            mainPane = new BorderPane();
            getChildren().add(mainPane);
            controls = new HBox();

            bttnSave = new Button("Save");
            controls.getChildren().add(bttnSave);
            bttnSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    save();
                    /*
                    Arreglar cuando da OutOfMemory
                    */
                }
            });

            bttnToAmend = new Button("Amend");
            controls.getChildren().add(bttnToAmend);
            bttnToAmend.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    amend();
                }
            });

            try{
                r.setTextualContent("[This is the textual content and it is exciting because it has automatic text wrap, you can see this because it continues in the next line]");
            } catch(EmptyFieldException e){
                System.out.println("Cannot add an empty content");
            }

            lblOriginal = new Label(r.getTextualContent());
            lblOriginal.setWrapText(true);
            lblOriginal.setTextAlignment(TextAlignment.JUSTIFY);
            txtTranslation = new TextArea(r.getTranslatedConent());
            txtTranslation.setWrapText(true);
            
            lblOriginal.setPrefHeight(500);
            lblOriginal.setPrefWidth(300);
            txtTranslation.setPrefHeight(500);
            txtTranslation.setPrefWidth(300);

            rightPane = new VBox();
            rightPane.getChildren().add(txtTranslation);
            rightPane.getChildren().add(controls);
            leftPane = new VBox();
            leftPane.getChildren().add(lblOriginal);
            mainPane.setRight(rightPane);
            mainPane.setLeft(leftPane);
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            
        }
        private void save(){
            ArrayList<Recurso> temporalArray = resources;
            resources.clear();
            r.setTranslatedContent(txtTranslation.getText());
            resources.add(r);
            for(int i = 0; i<temporalArray.size(); i++)
                resources.add(temporalArray.get(i));
            
            try {
                FileOutputStream userResource = new FileOutputStream("build/arrayListResources");
                ObjectOutputStream resourceWrite = new ObjectOutputStream(userResource);
                
                resourceWrite.writeObject(resources);
                resourceWrite.close();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
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
        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                resources.add(AppLogin.getArraylistResource().get(i));
            }
        }
    }
}

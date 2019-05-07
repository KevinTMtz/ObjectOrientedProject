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

    private static FlowPane leftPane, rightPane;
    private static HBox controls;
    private static TextField txtTranslation;
    private static Label lblTitle, lblOriginal;
    private static Button bttnSave, bttnToAmend;
    private static BorderPane mainPane;
    private static Recurso r;
    private static ArrayList<Recurso> data;

    public GUITranslatingResource() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane{
        public SignUpPane() {
            readData();
            for(int i = 0; i<data.size(); i++)
                if(data.get(i).getCurrentStatus().equals("translating")){
                    r = data.get(i);
                    break;
                }


            mainPane= new BorderPane();
            getChildren().add(mainPane);
            leftPane= new FlowPane();
            rightPane= new FlowPane();
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));

            bttnSave = new Button("Save");
            controls.getChildren().add(bttnSave);
            bttnSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    save();
                }
            });

            bttnToAmend = new Button("Amend");
            controls.getChildren().add(bttnToAmend);
            bttnToAmend.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    amend();
                }
            });
            
            lblTitle = new Label(r.getTitle());
            lblOriginal = new Label(r.getTextualContent());

            txtTranslation = new TextField(r.getTranslatedConent());
            controls = new HBox(bttnSave);

            lblTitle.setPrefWidth(100);
            lblOriginal.setPrefWidth(100);
            lblOriginal.setPrefHeight(500);
            
        }
        private void save(){
            ArrayList<Recurso> temporalArray = data;
            data.clear();
            r.setTranslatedContent(txtTranslation.getText());
            data.add(r);
            for(int i = 0; i<temporalArray.size(); i++)
                data.add(temporalArray.get(i));

            try {
                FileOutputStream userResource = new FileOutputStream("build/arrayListResources");
                ObjectOutputStream resourceWrite = new ObjectOutputStream(userResource);
                
                resourceWrite.writeObject(data);
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
                data.add(AppLogin.getArraylistResource().get(i));
            }
        }
    }
}

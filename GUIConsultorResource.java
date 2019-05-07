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

public class GUIConsultorResource extends Scene{

    private static ArrayList<Recurso> resources;
    private static Recurso r;
    private static BorderPane mainPane;
    private static VBox leftPane, rightPane;
    private static Text lblOriginal;
    private static Text txtTranslation;
    private static HBox controls;
    private static Button bttnSave, bttnToAmend, bttnFreeResource, bttnBack;
    private static int index;
    
    public GUIConsultorResource() {
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

            bttnBack = new Button("Back");
            controls.getChildren().add(bttnBack);
            bttnBack.setOnAction(e -> goBack());

            lblOriginal = new Text(0,0,r.getTextualContent());
            lblOriginal.setWrappingWidth(300);
            lblOriginal.setTextAlignment(TextAlignment.JUSTIFY);
            lblOriginal.setTextOrigin(VPos.TOP);
            txtTranslation = new Text(r.getTranslatedConent());
            
            txtTranslation.prefHeight(500);
            txtTranslation.prefWidth(300);

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
        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                resources.add(AppLogin.getArraylistResource().get(i));
            }
        }
        private void goBack(){
        }
    }
}

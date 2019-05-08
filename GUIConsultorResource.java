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

    private static BorderPane mainPane;
    private static VBox leftPane, rightPane;
    private static Text lblOriginal;
    private static Text txtTranslation;
    private static HBox controls;
    private static Button bttnBack;
    
    
    public GUIConsultorResource(int index) {
        super(new SignUpPane(index));
    }

    public static class SignUpPane extends GridPane{
        private int index;
        public SignUpPane(int index) {
            setIndex(index);
            this.setAlignment(Pos.CENTER);
            
            //select the resource to translate
            resources = new ArrayList<Recurso>();  
            mainPane = new BorderPane();
            mainPane.setPrefWidth(900);
            mainPane.setPrefHeight(620);
            getChildren().add(mainPane);
            controls = new HBox();

            bttnBack = new Button("Back");
            controls.getChildren().add(bttnBack);
            bttnBack.setOnAction(e -> goBack());
            
            lblOriginal = new Text(0,0,"");
            lblOriginal.setWrappingWidth(300);
            lblOriginal.setTextAlignment(TextAlignment.JUSTIFY);
            lblOriginal.setTextOrigin(VPos.TOP);
            txtTranslation = new Text();
            txtTranslation.setWrappingWidth(300);
            setInfo();
            txtTranslation.prefHeight(500);
            txtTranslation.prefWidth(300);
            lblOriginal.prefHeight(500);
            lblOriginal.prefWidth(300);

            
            rightPane = new VBox();

            rightPane.getChildren().add(txtTranslation);
            mainPane.setBottom(controls);
            controls.setAlignment(Pos.CENTER);
            leftPane = new VBox();
            leftPane.getChildren().add(lblOriginal);
            mainPane.setRight(rightPane);
            mainPane.setLeft(leftPane);
            mainPane.setMargin(rightPane, new Insets(20, 50, 10, 0));
            mainPane.setMargin(leftPane, new Insets(30, 0, 10, 50));
            
        }
        private void setInfo(){
            String translation = AppLogin.getArraylistResource().get(index).getTranslatedConent();
            txtTranslation.setText(translation);
            String original = AppLogin.getArraylistResource().get(index).getTextualContent();
            lblOriginal.setText(original);
        }
        private void goBack(){
            GUILogin.changeScene(new GUIConsultant());
        }
        private void setIndex(int index){
            this.index = index;
        }
    }
}

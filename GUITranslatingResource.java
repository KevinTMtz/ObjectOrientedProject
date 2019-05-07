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
    private static Button bttnSave, bttnSaveAndQuit;
    private static BorderPane mainPane;

    public GUITranslatingResource(Resource r) {
        super(new Translating(r));
    }

    public static class Translating extends GridPane{
        public  Translating(Resource r) {

            mainPane= new BorderPane();
            getChildren().add(mainPane);
            controls = new HBox(bttnSave, bttnSaveAndQuit);
            leftPane = new VBox(lblTitle, txtOriginal);
            rightPane = new VBox(txtTranslation, controls);
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
    
            lblOriginal = new TextField(r.getTextualContent());
            txtTranslation = new TextField(r.getTranslatedConent());
    
            bttnSave = new Button("Save");
            bttnSaveAndQuit = new Button("Save and quit");

        }
    }
}

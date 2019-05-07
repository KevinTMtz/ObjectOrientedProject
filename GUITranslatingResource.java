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
    private static Button bttnSave;
    private static BorderPane mainPane;
    private static Resource r;

    public GUITranslatingResource(Resource r) {
        super(new Translating(r));
    }

    public static class Translating extends GridPane{
        public Translating(Resource r) {

            this.r = r;

            mainPane= new BorderPane();
            getChildren().add(mainPane);
            
            bttnSave = new Button("Save");
            controls.getChildren().add(bttnSave);
            bttnSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                public void handle(MouseEvent e) {
                    r.setTranslatedContent(txtTranslation.getText());
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
        private static void save(){
            /*
            try{
                Movie[] m = new Movie[totalMovies];
                for(int i = 0; i<m.length; i++)
                    m[i] = data.get(i);
                FileOutputStream fos = new FileOutputStream("Movies.oop");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(m);
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
    }
}

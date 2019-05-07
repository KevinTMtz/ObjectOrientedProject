import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

public class GUITranslator extends Scene {
    private static ObservableList<Recurso> data;
    private static ListView<Recurso> lvResource;
    private static ArrayList<Recurso> resources = new ArrayList<Recurso>();
    private static Text txtTitle, txtLanguage, txtRegion, txtResponsible;
    private static Label lblInfo, lblTitle, lblLanguage, lblRegion, lblResponsible, lblClass;
    private static Text txtPages, txtWords;
    private static Label lblPages, lblWords;
    private static Text txtDuration, txtPath;
    private static Label lblDuration, lblPath;
    private static Button bttnTranslate;
    private static BorderPane mainPane;
    private static FlowPane leftPane, rightPane;
    private static GridPane listPane, infoPane;
    private static Stage newStage;

    public GUITranslator() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane {
        public SignUpPane() {
            mainPane= new BorderPane();
            getChildren().add(mainPane);
            leftPane= new FlowPane();
            rightPane= new FlowPane();
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
            

            //ListPane-->Left Side and the list
            listPane=new GridPane();
            Text user = new Text("Translator");
            listPane.add(user, 0, 0);
            //getChildren().add(user);
            data = FXCollections.observableArrayList();
            lvResource = new ListView<>(data);
            listPane.add(lvResource, 0, 1);
            leftPane.getChildren().add(listPane);
            lvResource.setMaxWidth(220);
            readData();
            
            //RightPane-->Right Side and the info
            infoPane=new GridPane();
            infoPane.setVgap(10);
            infoPane.setHgap(15);
                lblInfo=new Label("Information: ");
                lblInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
                infoPane.add(lblInfo, 0, 0);

                lblTitle=new Label("Title");
                infoPane.add(lblTitle, 0, 1);
                txtTitle=new Text("-");
                infoPane.add(txtTitle, 1, 1);

                lblLanguage=new Label("Language:");
                infoPane.add(lblLanguage, 0, 2);
                txtLanguage=new Text("-");
                infoPane.add(txtLanguage, 1, 2);

                lblRegion=new Label("Region");
                infoPane.add(lblRegion, 0, 3);
                txtRegion=new Text("-");
                infoPane.add(txtRegion, 1, 3);
                
                lblResponsible=new Label("Responsible");
                infoPane.add(lblResponsible, 0, 4);
                txtResponsible=new Text("-");
                infoPane.add(txtResponsible, 1, 4);

                lblClass=new Label();

                //If it is a text
                lblPages= new Label("Number of Pages:");
                txtPages= new Text("-");
                lblWords=new Label("Number of Words");
                txtWords= new Text("-");

                //If it is a recording
                lblDuration=new Label("Duration:");
                txtDuration= new Text("-");
                lblPath=new Label("Path:");
                txtPath=new Text("-");
                
                bttnTranslate = new Button("Translate");
                bttnTranslate.setOnAction(e -> beginTranslate());

                lblClass.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
                lvResource.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent a) {
                        selectResource();
                    }
                });
                
                //For the width
                lblDuration.setPrefWidth(100);
                lblLanguage.setPrefWidth(100);
                lblPages.setPrefWidth(100);
                lblPath.setPrefWidth(100);
                lblRegion.setPrefWidth(100);
                lblResponsible.setPrefWidth(100);
                lblTitle.setPrefWidth(100);
                lblWords.setPrefWidth(100);

                lblInfo.setAlignment(Pos.CENTER_RIGHT);
                lblClass.setAlignment(Pos.CENTER_RIGHT);
                lblDuration.setAlignment(Pos.CENTER_RIGHT);
                lblLanguage.setAlignment(Pos.CENTER_RIGHT);
                lblPages.setAlignment(Pos.CENTER_RIGHT);
                lblPath.setAlignment(Pos.CENTER_RIGHT);
                lblRegion.setAlignment(Pos.CENTER_RIGHT);
                lblResponsible.setAlignment(Pos.CENTER_RIGHT);
                lblTitle.setAlignment(Pos.CENTER_RIGHT);
                lblWords.setAlignment(Pos.CENTER_RIGHT);

                //For the margins
                infoPane.setMargin(lblClass, new Insets(10, 0, 0, 0));

                //Escribe tu código a partir de aquií. Agrega los botones al rightPane
            
            rightPane.getChildren().add(infoPane);
        }

        private void beginTranslate(){
            try{
                lvResource.getSelectionModel().getSelectedItem().setCurrentStatus("translating");
            } catch (EmptyFieldException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Title cannot be null");
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            GUILogin.changeScene(new GUITranslatingResource());
        }


        private void selectResource() {
            try{
                if(lvResource.getSelectionModel().getSelectedIndex()!=-1){
                    //Remove previous nodes
                    infoPane.getChildren().remove(lblPages);
                    infoPane.getChildren().remove(txtPages);
                    infoPane.getChildren().remove(lblWords);
                    infoPane.getChildren().remove(txtWords);
                    infoPane.getChildren().remove(lblDuration);
                    infoPane.getChildren().remove(txtDuration);
                    infoPane.getChildren().remove(lblPath);
                    infoPane.getChildren().remove(txtPath);
                    infoPane.getChildren().remove(lblClass);
                    infoPane.getChildren().remove(bttnTranslate);

                    infoPane.add(bttnTranslate, 1, 8);
                    
                    if(lvResource.getSelectionModel().getSelectedItem() instanceof Textual){
                        System.out.println("The selected resource is: textual");
                        Textual t=(Textual) lvResource.getSelectionModel().getSelectedItem();
                        setResource(t);
                        lblClass.setText("Text info:");
                        infoPane.add(lblClass, 0, 5);
                        infoPane.add(lblPages, 0, 6);
                        infoPane.add(txtPages, 1, 6);
                        infoPane.add(lblWords, 0, 7);
                        infoPane.add(txtWords, 1, 7);
                    }
                    if(lvResource.getSelectionModel().getSelectedItem() instanceof Recording){
                        System.out.println("The selected resource is: recording");
                        Recording r=(Recording) lvResource.getSelectionModel().getSelectedItem();
                        setResource(r);
                        lblClass.setText("Audio info: ");
                        infoPane.add(lblClass, 0, 5);
                        infoPane.add(lblDuration, 0, 6);
                        infoPane.add(txtDuration, 1, 6);
                        infoPane.add(lblPath, 0, 7);
                        infoPane.add(txtPath, 1, 7);
                    }
                }
                else throw new NullPointerException();
            } catch(NullPointerException npe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Translating resource");
                alert.setHeaderText("Error while translating a new resource");
                alert.setContentText("You have not selected a resource");
                alert.showAndWait();
            }
        }

        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                data.add(AppLogin.getArraylistResource().get(i));
            }
        }
        
        private void setResource(Textual t){
            txtTitle.setText(t.getTitle());
            txtLanguage.setText(t.getLanguage());
            txtRegion.setText(t.getRegionOfOrigin());
            txtResponsible.setText(t.getResponsibleOfTheFinding());
            txtWords.setText(""+t.getNumberOfWords());
            txtPages.setText(""+t.getNumberOfPages());
        }
        private void setResource(Recording r){
            txtTitle.setText(r.getTitle());
            txtLanguage.setText(r.getLanguage());
            txtRegion.setText(r.getRegionOfOrigin());
            txtResponsible.setText(r.getResponsibleOfTheFinding());
            txtDuration.setText(""+r.getDuration());
            txtPath.setText(r.getPathOfFile());
        }
    }
}
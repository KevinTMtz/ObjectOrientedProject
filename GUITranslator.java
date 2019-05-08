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
    private static int index;

    public GUITranslator() {
        super(new SignUpPane());
        this.getStylesheets().add(GUITranslator.class.getResource("css/CulturalHeritage.css").toExternalForm());
    }

    public static class SignUpPane extends GridPane {
        public SignUpPane() {
            this.setAlignment(Pos.CENTER);

            mainPane= new BorderPane();
            getChildren().add(mainPane);
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(20, 0, 40, 0));
            Text titletxt = new Text("Translator");
            titletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
            titletxt.setFill(Color.BLACK);
            title.getChildren().add(titletxt);
            mainPane.setTop(title);
            mainPane.setPrefWidth(900);
            mainPane.setPrefHeight(620);
            leftPane= new FlowPane();
            rightPane= new FlowPane();
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
            

            //ListPane-->Left Side and the list
            listPane=new GridPane();

            data = FXCollections.observableArrayList();
            lvResource = new ListView<>(data);
            lvResource.setMinWidth(300);
            listPane.add(lvResource, 0, 1);
            leftPane.getChildren().add(listPane);
            lvResource.setMaxWidth(220);
            readData();
            
            //RightPane-->Right Side and the info
            infoPane=new GridPane();
            infoPane.setVgap(10);
            infoPane.setHgap(15);
                lblInfo=new Label("Information:");
                lblInfo.setMinWidth(150);
                lblInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
                infoPane.add(lblInfo, 0, 0);

                lblTitle=new Label("Title:");
                lblTitle.setMinWidth(150);
                infoPane.add(lblTitle, 0, 1);
                txtTitle=new Text("-");
                infoPane.add(txtTitle, 1, 1);

                lblLanguage=new Label("Language:");
                lblLanguage.setMinWidth(150);
                infoPane.add(lblLanguage, 0, 2);
                txtLanguage=new Text("-");
                infoPane.add(txtLanguage, 1, 2);

                lblRegion=new Label("Region:");
                lblRegion.setMinWidth(150);
                infoPane.add(lblRegion, 0, 3);
                txtRegion=new Text("-");
                infoPane.add(txtRegion, 1, 3);
                
                lblResponsible=new Label("Responsible:");
                lblResponsible.setMinWidth(150);
                infoPane.add(lblResponsible, 0, 4);
                txtResponsible=new Text("-");
                infoPane.add(txtResponsible, 1, 4);

                lblClass=new Label();

                //If it is a text
                lblPages= new Label("Num. of Pages:");
                lblPages.setMinWidth(150);
                txtPages= new Text("-");
                lblWords=new Label("Num. of Words");
                lblWords.setMinWidth(150);
                txtWords= new Text("-");

                //If it is a recording
                lblDuration=new Label("Duration:");
                lblDuration.setMinWidth(150);
                txtDuration= new Text("-");
                lblPath=new Label("Path:");
                lblPath.setMinWidth(150);
                txtPath=new Text("-");
                
                bttnTranslate = new Button("Translate");
                bttnTranslate.setOnAction(e -> beginTranslate());
                
                Button bttnLogOut=new Button("Log out");
                infoPane.add(bttnLogOut, 1, 15);
                bttnLogOut.setAlignment(Pos.BOTTOM_RIGHT);
                bttnLogOut.setOnAction(e -> GUILogin.backLogin());

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
            
            rightPane.getChildren().add(infoPane);
        }

        private void beginTranslate(){
            try{
                if(lvResource.getSelectionModel().getSelectedItem().getCurrentStatus().equals("to amend")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Translation");
                    alert.setHeaderText("Error while translating");
                    alert.setContentText("Cannot translate a resource with \"to amend\" status");
                    alert.showAndWait();
                }
                else{
                    lvResource.getSelectionModel().getSelectedItem().setCurrentStatus("translating");
                    index = lvResource.getSelectionModel().getSelectedIndex();
                    GUILogin.changeScene(new GUITranslatingResource(index));

                }
            } catch (EmptyFieldException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Title cannot be null");
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
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
                        lblClass.setMinWidth(150);
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
                        lblClass.setText("Audio info:");
                        lblClass.setMinWidth(150);
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
        public static int getIndex() {
            return index;
        } 
    }
}
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

public class GUIConsultant extends Scene {
    private static ObservableList<Recurso> data;
    private static ListView<Recurso> lvResource;
    private static ArrayList<Recurso> resources = new ArrayList<Recurso>();
    private static Text txtTitle, txtLanguage, txtRegion, txtResponsible;
    private static Label lblInfo, lblTitle, lblLanguage, lblRegion, lblResponsible, lblClass;
    private static Text txtPages, txtWords;
    private static Label lblPages, lblWords;
    private static Text txtDuration, txtPath;
    private static Label lblDuration, lblPath;
    private static BorderPane mainPane;
    private static FlowPane leftPane, rightPane;
    private static GridPane listPane, infoPane;
    private static Button bttnconsult;

    public GUIConsultant() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane {
        public SignUpPane() {
            this.setAlignment(Pos.CENTER);
            mainPane= new BorderPane();
            getChildren().add(mainPane);
            leftPane= new FlowPane();
            rightPane= new FlowPane();
            mainPane.setPrefWidth(900);
            mainPane.setPrefHeight(620);
            mainPane.setLeft(leftPane);
            mainPane.setRight(rightPane);
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
            
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(20, 0, 40, 0));
            Text titletxt = new Text("Consultor");
            titletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
            titletxt.setFill(Color.BLACK);
            title.getChildren().add(titletxt);
            mainPane.setTop(title);
            //ListPane-->Left Side and the list
            listPane=new GridPane();
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

                bttnconsult = new Button("Consult");
                bttnconsult.setOnAction(e -> beginConsult());

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
                Button bttnLogOut=new Button("Log out");
                infoPane.add(bttnLogOut, 1, 15);
                bttnLogOut.setAlignment(Pos.BOTTOM_RIGHT);
                bttnLogOut.setOnAction(e -> GUILogin.backLogin());

                //Escribe tu código a partir de aquií. Agrega los botones al rightPane
            
            rightPane.getChildren().add(infoPane);
        }
        private void selectResource() {
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
            infoPane.getChildren().remove(bttnconsult);

            infoPane.add(bttnconsult, 1, 11);

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
        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                if(AppLogin.getArraylistResource().get(i).getCurrentStatus().equalsIgnoreCase("freed")){
                    data.add(AppLogin.getArraylistResource().get(i));
                }
            }
        }
        /*private void saveData(){
            // Create the arraylist
            ArrayList<Recurso> temp = new ArrayList<>();
            temp = resources;
            // Add the resources
            try {
                // Save the file
                File resourceFile = new File("resources/resources.list");
                resourceFile.delete();
                FileOutputStream fos = new FileOutputStream("resources/resources.list");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(temp);
                oos.close();
                fos.close();
            } catch (FileNotFoundException fe) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            } 
        }*/
        
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
        private void beginConsult(){
            int index = lvResource.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            GUILogin.changeScene(new GUIConsultorResource(index));
        }
    }
}
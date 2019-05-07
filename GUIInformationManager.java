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

public class GUIInformationManager extends Scene {
    private static ObservableList<Recurso> data;
    private static ListView<Recurso> lvResource;
    private static ArrayList<Recurso> resources = new ArrayList<Recurso>();
    private static TextField txtTitle, txtLanguage, txtRegion, txtResponsible;
    private static Text txtStatus;
    private static Label lblInfo, lblTitle, lblLanguage, lblRegion, lblResponsible, lblStatus;
    private static TextField txtPages, txtWords;
    private static Label lblPages, lblWords;
    private static TextField txtDuration, txtPath;
    private static Label lblDuration, lblPath;
    private static BorderPane mainPane;
    private static FlowPane leftPane, rightPane;
    private static GridPane listPane, infoPane, controlsPane;
    private static CheckBox recording, textual;
    private static Button bttnContent;

    public GUIInformationManager() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane {
        public SignUpPane() {
            mainPane= new BorderPane();
            getChildren().add(mainPane);
            leftPane= new FlowPane();
            rightPane= new FlowPane();
            mainPane.setLeft(leftPane);
            mainPane.setCenter(rightPane);
            rightPane.setAlignment(Pos.TOP_CENTER);
            
            mainPane.setMargin(leftPane, new Insets(10, 0, 10, 10));
            mainPane.setMargin(rightPane, new Insets(10, 10, 10, 0));
            

            //ListPane-->Left Side and the list
            listPane=new GridPane();
            Text user = new Text("Information Manager ");
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
                txtTitle=new TextField();
                txtTitle.setPromptText("Title");
                infoPane.add(txtTitle, 1, 1);

                lblLanguage=new Label("Language:");
                infoPane.add(lblLanguage, 0, 2);
                txtLanguage=new TextField();
                txtLanguage.setPromptText("Language");
                infoPane.add(txtLanguage, 1, 2);

                lblRegion=new Label("Region");
                infoPane.add(lblRegion, 0, 3);
                txtRegion=new TextField();
                txtRegion.setPromptText("Region");
                infoPane.add(txtRegion, 1, 3);
                
                lblResponsible=new Label("Responsible");
                infoPane.add(lblResponsible, 0, 4);
                txtResponsible=new TextField();
                txtResponsible.setPromptText("Responsible");
                infoPane.add(txtResponsible, 1, 4);

                //If it is a text
                lblPages= new Label("Number of Pages:");
                txtPages= new TextField();
                txtPages.setPromptText("Pages");
                lblWords=new Label("Number of Words");
                txtWords= new TextField();
                txtWords.setPromptText("Words");

                //If it is a recording
                lblDuration=new Label("Duration:");
                txtDuration= new TextField();
                txtDuration.setPromptText("Duration");
                lblPath=new Label("Path:");
                txtPath=new TextField();
                txtPath.setPromptText("Path");

                recording=new CheckBox("Recording");
                textual=new CheckBox("Textual");
                Label lblSelect=new Label("Select one: ");
                lblSelect.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
                infoPane.add(lblSelect, 0, 7);
                infoPane.add(recording, 1, 8);
                infoPane.add(textual, 1, 9);

                lblStatus=new Label("Status:");
                txtStatus=new Text();

                //Buttons
                controlsPane=new GridPane();
                Button bttnAdd=new Button("Add");
                controlsPane.add(bttnAdd, 0, 0);
                Button bttnDelete=new Button("Delete");
                controlsPane.add(bttnDelete, 1, 0);
                Button bttnUpdate= new Button("Update");
                controlsPane.add(bttnUpdate, 2, 0);
                Button bttnNew= new Button("New");
                controlsPane.add(bttnNew, 3, 0);
                mainPane.setBottom(controlsPane);
                controlsPane.setAlignment(Pos.CENTER_LEFT);

                bttnContent=new Button("Check content");
                bttnContent.setAlignment(Pos.CENTER);
                bttnContent.setOnAction(e -> GUILogin.changeScene(new GUIManager()));

                infoPane.add(bttnContent, 1, 11);
                //bttnContent.setOnAction(e->newStage);



                //The events
                lvResource.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent a) {
                        selectResource();
                    }
                });
                bttnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle (MouseEvent a){
                        addResource();
                    }
                });
                bttnNew.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle (MouseEvent a){
                        newResource();
                    }
                });
                bttnDelete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle (MouseEvent a){
                        deleteResource();
                    }
                });
                bttnUpdate.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle (MouseEvent a){
                        updateResource();
                    }
                });
                EventHandler<ActionEvent> eventRecording = new EventHandler<ActionEvent>() { 
  
                    public void handle(ActionEvent e) 
                    { 
                        recording.setSelected(true);
                        txtWords.setText("");
                        txtPages.setText("");
                        infoPane.getChildren().remove(lblPages);
                        infoPane.getChildren().remove(txtPages);
                        infoPane.getChildren().remove(lblWords);
                        infoPane.getChildren().remove(txtWords);
                        infoPane.getChildren().remove(lblDuration);
                        infoPane.getChildren().remove(txtDuration);
                        infoPane.getChildren().remove(lblPath);
                        infoPane.getChildren().remove(txtPath);
                        textual.setSelected(false);
                        infoPane.add(lblDuration, 0, 5);
                        infoPane.add(txtDuration, 1, 5);
                        infoPane.add(lblPath, 0, 6);
                        infoPane.add(txtPath, 1, 6);
                    } 
      
                }; 
                EventHandler<ActionEvent> eventTextual = new EventHandler<ActionEvent>() { 
  
                    public void handle(ActionEvent e) 
                    { 
                        textual.setSelected(true);
                        //Remove what was there before
                        txtDuration.setText("");
                        txtPath.setText("");
                        infoPane.getChildren().remove(lblPages);
                        infoPane.getChildren().remove(txtPages);
                        infoPane.getChildren().remove(lblWords);
                        infoPane.getChildren().remove(txtWords);
                        infoPane.getChildren().remove(lblDuration);
                        infoPane.getChildren().remove(txtDuration);
                        infoPane.getChildren().remove(lblPath);
                        infoPane.getChildren().remove(txtPath);
                        recording.setSelected(false);
                        infoPane.add(lblPages, 0, 5);
                        infoPane.add(txtPages, 1, 5);
                        infoPane.add(lblWords, 0, 6);
                        infoPane.add(txtWords, 1, 6);
                    } 
      
                };

                // set event to checkbox 
                recording.setOnAction(eventRecording); 
                textual.setOnAction(eventTextual); 

                //For the width
                lblInfo.setPrefWidth(100);
                lblDuration.setPrefWidth(100);
                lblLanguage.setPrefWidth(100);
                lblPages.setPrefWidth(100);
                lblPath.setPrefWidth(100);
                lblRegion.setPrefWidth(100);
                lblResponsible.setPrefWidth(100);
                lblTitle.setPrefWidth(100);
                lblWords.setPrefWidth(100);
                recording.setPrefWidth(100);
                textual.setPrefWidth(100);
                lblStatus.setPrefWidth(100);
                lblSelect.setPrefWidth(100);

                //For the text-aligment
                lblInfo.setAlignment(Pos.CENTER_RIGHT);
                lblDuration.setAlignment(Pos.CENTER_RIGHT);
                lblLanguage.setAlignment(Pos.CENTER_RIGHT);
                lblPages.setAlignment(Pos.CENTER_RIGHT);
                lblPath.setAlignment(Pos.CENTER_RIGHT);
                lblRegion.setAlignment(Pos.CENTER_RIGHT);
                lblResponsible.setAlignment(Pos.CENTER_RIGHT);
                lblTitle.setAlignment(Pos.CENTER_RIGHT);
                lblSelect.setAlignment(Pos.CENTER_RIGHT);
                lblWords.setAlignment(Pos.CENTER_RIGHT);
                recording.setAlignment(Pos.CENTER_RIGHT);
                textual.setAlignment(Pos.CENTER_RIGHT);
                lblStatus.setAlignment(Pos.CENTER_RIGHT);
                
                //For the font-weight
                lblStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
                txtStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

                //For the margins
                infoPane.setMargin(lblSelect, new Insets(10, 0, 0, 0));
                infoPane.setMargin(lblStatus, new Insets(20, 0, 0, 0));
                infoPane.setMargin(txtStatus, new Insets(20, 0, 0, 0));
                controlsPane.setMargin(bttnAdd, new Insets(0, 5, 20, 10));
                controlsPane.setMargin(bttnDelete, new Insets(0, 5, 20, 5));
                controlsPane.setMargin(bttnNew, new Insets(0, 10, 20, 5));
                controlsPane.setMargin(bttnUpdate, new Insets(0, 5, 20, 5));

            rightPane.getChildren().add(infoPane);
        }
        //Buttons and selector
        private void newResource(){
            //Clear all textField
            setResource(new Recording());
            setResource(new Textual());
            recording.setSelected(false);
            textual.setSelected(false);
            //Remove the selective info and status
            infoPane.getChildren().remove(lblPages);
            infoPane.getChildren().remove(txtPages);
            infoPane.getChildren().remove(lblWords);
            infoPane.getChildren().remove(txtWords);
            infoPane.getChildren().remove(lblDuration);
            infoPane.getChildren().remove(txtDuration);
            infoPane.getChildren().remove(lblPath);
            infoPane.getChildren().remove(txtPath);
            infoPane.getChildren().remove(lblStatus);
            infoPane.getChildren().remove(txtStatus);

            

        }
        private void addResource(){
            try{
                //Check if a box is checked
                if(recording.isSelected()==false && textual.isSelected()==false) 
                    throw new UncheckedBoxException("You have to select if it is a recording or a text");
                // Read the information for a resource and create an object
                else{
                    Recording r=new Recording();
                    Textual t=new Textual();
                    if(recording.isSelected()){
                        getResource(r);
                        AppInformationalManager.addToArray(r);
                        data.add(r);
                    }
                    if(textual.isSelected()) {
                        getResource(t);
                        AppInformationalManager.addToArray(t);
                        data.add(t);
                    }      
                    
                        // Clear the Form
                        newResource();
                        //saveData();
                }
                
            }catch(EmptyFieldException efe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add/Update a resource");
                alert.setHeaderText("Error while adding/updating a new resource");
                alert.setContentText(efe.getMessage());
                alert.showAndWait();
            }catch(SameResourceException sre){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add a resource");
                alert.setHeaderText("Error while adding a new resource");
                alert.setContentText(sre.getMessage());
                alert.showAndWait();
            }catch(UncheckedBoxException ube){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add a resource");
                alert.setHeaderText("Error while adding a new resource");
                alert.setContentText(ube.getMessage());
                alert.showAndWait();
            }
        }
        private void deleteResource(){
            try {
                int index=lvResource.getSelectionModel().getSelectedIndex();
                if(index!=-1){
                    //Delete it with App...
                    AppInformationalManager.deleteFromArrayList(index);
                    data.remove(lvResource.getSelectionModel().getSelectedItem());
                    // Empty the fields
                    newResource();
                    //saveData();
                    lvResource.getSelectionModel().clearSelection();
                }else throw new NullPointerException();

            } catch(NullPointerException npe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete a resource");
                alert.setHeaderText("Error while deleting a resource");
                alert.setContentText("You have not selected a resource");
                alert.showAndWait();
            }
        }
        private void updateResource(){
            
            try {
                int index=lvResource.getSelectionModel().getSelectedIndex();
                if(index!=-1){
                    //Delete it with App...
                    AppInformationalManager.deleteFromArrayList(index);
                    data.remove(lvResource.getSelectionModel().getSelectedItem());
                    // Empty the fields
                    //saveData();
                    lvResource.getSelectionModel().clearSelection();
                    addResource();
                }else throw new NullPointerException();

            } catch(NullPointerException npe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete a resource");
                alert.setHeaderText("Error while deleting a resource");
                alert.setContentText("You have not selected a resource");
                alert.showAndWait();
            }
            /*
            try {
                addResource();
                int index=lvResource.getSelectionModel().getSelectedIndex();
                if(index!=-1){
                    //Delete it with App...
                    AppInformationalManager.deleteFromArrayList(index);
                    data.remove(lvResource.getSelectionModel().getSelectedItem());
                    // Empty the fields
                    newResource();
                    //saveData();
                    lvResource.getSelectionModel().clearSelection();
                }else throw new NullPointerException();
            } catch(NullPointerException npe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete a resource");
                alert.setHeaderText("Error while deleting a resource");
                alert.setContentText("You have not selected a resource");
                alert.showAndWait();
            }*/
        }
        private void selectResource() {
            //Remove previous nodes
            setResource(new Recording());
            setResource(new Textual());
            infoPane.getChildren().remove(lblPages);
            infoPane.getChildren().remove(txtPages);
            infoPane.getChildren().remove(lblWords);
            infoPane.getChildren().remove(txtWords);
            infoPane.getChildren().remove(lblDuration);
            infoPane.getChildren().remove(txtDuration);
            infoPane.getChildren().remove(lblPath);
            infoPane.getChildren().remove(txtPath);
            infoPane.getChildren().remove(lblStatus);
            infoPane.getChildren().remove(txtStatus);
            infoPane.getChildren().remove(bttnContent);

            infoPane.add(bttnContent, 1, 11);

            //Display the staus (Hide it if nothing is selected)
            infoPane.add(lblStatus, 0, 10);
            txtStatus.setText(lvResource.getSelectionModel().getSelectedItem().getCurrentStatus().toUpperCase());
            infoPane.add(txtStatus, 1, 10);

            if(lvResource.getSelectionModel().getSelectedItem() instanceof Textual){
                System.out.println("The selected resource is: textual");
                Textual t=(Textual) lvResource.getSelectionModel().getSelectedItem();
                setResource(t);
                infoPane.add(lblPages, 0, 5);
                infoPane.add(txtPages, 1, 5);
                infoPane.add(lblWords, 0, 6);
                infoPane.add(txtWords, 1, 6);
                textual.setSelected(true);
                recording.setSelected(false);
            }
            if(lvResource.getSelectionModel().getSelectedItem() instanceof Recording){
                System.out.println("The selected resource is: recording");
                Recording r=(Recording) lvResource.getSelectionModel().getSelectedItem();
                setResource(r);
                infoPane.add(lblDuration, 0, 5);
                infoPane.add(txtDuration, 1, 5);
                infoPane.add(lblPath, 0, 6);
                infoPane.add(txtPath, 1, 6);
                textual.setSelected(false);
                recording.setSelected(true);
            }
        }
        //Getters and setters
        private void setResource(Textual t){
            txtTitle.setText(t.getTitle());
            txtLanguage.setText(t.getLanguage());
            txtRegion.setText(t.getRegionOfOrigin());
            txtResponsible.setText(t.getResponsibleOfTheFinding());
            txtWords.setText(t.getNumberOfWords());
            txtPages.setText(t.getNumberOfPages());
        }
        private void setResource(Recording r){
            txtTitle.setText(r.getTitle());
            txtLanguage.setText(r.getLanguage());
            txtRegion.setText(r.getRegionOfOrigin());
            txtResponsible.setText(r.getResponsibleOfTheFinding());
            txtDuration.setText(r.getDuration());
            txtPath.setText(r.getPathOfFile());
        }
        private void getResource(Textual t) throws EmptyFieldException{
            t.setTitle(txtTitle.getText());
            t.setLanguage(txtLanguage.getText());
            t.setNumberOfPages(txtPages.getText());
            t.setNumberOfWords(txtWords.getText());
            t.setRegionOfOrigin(txtRegion.getText());
            t.setResponsibleOfTheFinding(txtResponsible.getText());
            t.setCurrentStatus("finding");
            t.setTranslatedContent("");
        }
        private void getResource(Recording r) throws EmptyFieldException{
            r.setTitle(txtTitle.getText());
            r.setLanguage(txtLanguage.getText());
            r.setDuration(txtDuration.getText());
            r.setPathOfFile(txtPath.getText());
            r.setRegionOfOrigin(txtRegion.getText());
            r.setResponsibleOfTheFinding(txtResponsible.getText());
            r.setCurrentStatus("finding");
            r.setTranslatedContent("");
        }

        /*
        private void saveData(){
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
        private void readData(){
            for(int i=0; i<AppLogin.getArraylistResource().size(); i++){
                data.add(AppLogin.getArraylistResource().get(i));
            }
        }
        
    }
}
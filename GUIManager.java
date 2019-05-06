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

public class GUIManager extends Scene {
    public GUIManager() {
        super(new SignUpPane());
    }

    public static class SignUpPane extends GridPane {
        private TextField txtDirector, txtTitle, txtDuration, txtYear, txtClassification;
        private ComboBox typeOfUser;
	    //private ObservableList<Movie> data;
	    //private ListView<Movie> lvMovie;

        public SignUpPane() {
            this.setAlignment(Pos.CENTER);
            
            BorderPane mainPane = new BorderPane();
            

            //Check This
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(0, 0, 30, 0));

            Text titletxt = new Text("User manager");
            titletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
            titletxt.setFill(Color.BLACK);
            title.getChildren().add(titletxt);
            //Check This
            

            FlowPane contentPane = new FlowPane();
            contentPane.setAlignment(Pos.CENTER);
            contentPane.setHgap(30);
            
            FlowPane controlsPane = new FlowPane();
            controlsPane.setAlignment(Pos.CENTER);
            
            mainPane.setTop(title);
            mainPane.setCenter(contentPane);
            mainPane.setBottom(controlsPane);

            // Content
            GridPane moviePane = new GridPane();
            moviePane.setPadding(new Insets(20));
            moviePane.setHgap(20);
            moviePane.setVgap(10);

            Label lblTitle = new Label("Title");
            txtTitle = new TextField();
            moviePane.add(lblTitle, 0, 0);
            moviePane.add(txtTitle, 1, 0);

            Label lblDirector = new Label("Director");
            txtDirector = new TextField();
            moviePane.add(lblDirector, 0, 1);
            moviePane.add(txtDirector, 1, 1);
            
            String users[] = { "Manager", "Information Manager", "Translator", "Consultant" };
        
            Label lblTypeOfUserRegister = new Label("Type of user");
            typeOfUser = new ComboBox(FXCollections.observableArrayList(users));
            typeOfUser.setValue("Manager");
            moviePane.add(lblTypeOfUserRegister, 0, 2);
            moviePane.add(typeOfUser, 1, 2);

            contentPane.getChildren().add(moviePane);

            /*
            // List
            data = FXCollections.observableArrayList();
            lvMovie = new ListView(data);
            lvMovie.setPrefHeight(80);
            contentPane.getChildren().add(lvMovie);

            lvMovie.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    //checkInfoMovie();
                }
            });
            */

            // Controls
            GridPane buttonPane = new GridPane();

            Button buttonAdd = new Button("Add");
            controlsPane.getChildren().add(buttonAdd);
            buttonAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    //addMovie();
                }
            });

            Button buttonDelete = new Button("Delete");
            controlsPane.getChildren().add(buttonDelete);
            buttonDelete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    //deleteMovie();
                }
            });

            Button buttonSave = new Button("Save");
            controlsPane.getChildren().add(buttonSave);
            buttonSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    //updateMovie();
                }
            });
            
            controlsPane.setAlignment(Pos.CENTER);
            controlsPane.setPadding(new Insets(20));
            controlsPane.setVgap(10);
            controlsPane.setHgap(10);
            contentPane.getChildren().add(buttonPane);

            getChildren().add(mainPane);
        
        /*

        // Add movie
        private void addMovie() {
            try {
                Movie m = new Movie();
                
                // Read the information for the movie an create an object
                m.setTitle(txtTitle.getText());
                m.setDirector(txtDirector.getText());
                m.setDuration(txtDuration.getText());
                m.setYear(txtYear.getText());
                m.setClassification(txtClassification.getText());
                
                // Add the object to my list
                data.add(m);
                
                //Clear the form
                txtTitle.setText("");
                txtDirector.setText("");
                txtDuration.setText("");
                txtClassification.setText("");
                txtYear.setText("");
            } catch (EmptyFieldException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Textfield empty");
                alert.setHeaderText("Error while adding new movie");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }

        // Delete selected movie
        private void deleteMovie() {
            // Obtain the selected movie
            Movie m = lvMovie.getSelectionModel().getSelectedItem();

            // Remove it from the data
            data.remove(m);

            // Clear the form
            txtTitle.setText("");
            txtDirector.setText("");
            txtDuration.setText("");
            txtClassification.setText("");
            txtYear.setText("");
        }

        // Display movie info
        private void checkInfoMovie() {
            // Obtain the selected movie
            Movie m = lvMovie.getSelectionModel().getSelectedItem();

            txtTitle.setText(m.getTitle());
            txtDirector.setText(m.getDirector());
            txtDuration.setText(m.getDuration());
            txtClassification.setText(m.getClassification());
            txtYear.setText(m.getYear());
        }

        // Update selected movie info
        private void updateMovie() {
            try {
                // Obtain the selected movie
                Movie m = lvMovie.getSelectionModel().getSelectedItem();

                Movie n = new Movie();

                n.setTitle(txtTitle.getText());
                n.setDirector(txtDirector.getText());
                n.setDuration(txtDuration.getText());
                n.setYear(txtYear.getText());
                n.setClassification(txtClassification.getText());

                // Clear the form
                txtTitle.setText("");
                txtDirector.setText("");
                txtDuration.setText("");
                txtClassification.setText("");
                txtYear.setText("");

                data.set(data.indexOf(m), n);
            } catch (EmptyFieldException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Textfield empty");
                alert.setHeaderText("Error while updating movie");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }*/
        }
    }
}
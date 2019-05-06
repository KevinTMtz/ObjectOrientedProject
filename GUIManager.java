import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
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
        private TextField txtPassword, txtUsername, txtDuration, txtYear, txtClassification;
        private ComboBox typeOfUser;
	    private ObservableList<User> data;
	    private ListView<User> lvUser;

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
            GridPane userPane = new GridPane();
            userPane.setPadding(new Insets(20));
            userPane.setHgap(20);
            userPane.setVgap(10);

            Label lblUsername = new Label("Username");
            txtUsername = new TextField();
            userPane.add(lblUsername, 0, 0);
            userPane.add(txtUsername, 1, 0);

            Label lblPassword = new Label("Password");
            txtPassword = new TextField();
            userPane.add(lblPassword, 0, 1);
            userPane.add(txtPassword, 1, 1);
            
            String users[] = { "Manager", "Information Manager", "Translator", "Consultant" };
            
            Label lblTypeOfUserRegister = new Label("Type of user");
            typeOfUser = new ComboBox(FXCollections.observableArrayList(users));
            typeOfUser.setValue("Manager");
            userPane.add(lblTypeOfUserRegister, 0, 2);
            userPane.add(typeOfUser, 1, 2);

            contentPane.getChildren().add(userPane);

            // List
            data = FXCollections.observableArrayList();
            lvUser = new ListView<User>(data);
            lvUser.setPrefHeight(80);
            lvUser.setPrefWidth(200);
            contentPane.getChildren().add(lvUser);
            /*
            lvUser.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    // Check User Info();
                }
            });
            */

            Button buttonAdd = new Button("Add");
            controlsPane.getChildren().add(buttonAdd);
            buttonAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    // addUser();
                }
            });

            Button buttonDelete = new Button("Delete");
            controlsPane.getChildren().add(buttonDelete);
            buttonDelete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    // deleteUser();
                }
            });

            Button buttonSave = new Button("Save");
            controlsPane.getChildren().add(buttonSave);
            buttonSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    // updateMovie();
                }
            });
            
            controlsPane.setAlignment(Pos.CENTER);
            controlsPane.setPadding(new Insets(20));
            controlsPane.setVgap(10);
            controlsPane.setHgap(10);

            getChildren().add(mainPane);
        }
    }
}
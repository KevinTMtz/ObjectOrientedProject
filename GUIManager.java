import java.util.ArrayList;
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

    public static class SignUpPane extends BorderPane {
        private TextField txtPassword, txtUsername;
        private ComboBox typeOfUser;
	    private ObservableList<User> data;
	    private ListView<User> lvUser;

        public SignUpPane() {
            //Check This
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(40, 0, 20, 0));

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
            
            this.setTop(title);
            this.setCenter(contentPane);
            this.setBottom(controlsPane);

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
            lvUser.setPrefWidth(350);
            contentPane.getChildren().add(lvUser);

            lvUser.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    checkUserInfo();
                }
            });

            Button buttonAdd = new Button("Add");
            controlsPane.getChildren().add(buttonAdd);
            buttonAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    addUser();
                }
            });

            Button buttonDelete = new Button("Delete");
            controlsPane.getChildren().add(buttonDelete);
            buttonDelete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    deleteUser();
                }
            });

            Button buttonSave = new Button("Save");
            controlsPane.getChildren().add(buttonSave);
            buttonSave.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    updateUser();
                }
            });
            
            controlsPane.setAlignment(Pos.CENTER);
            controlsPane.setPadding(new Insets(20));
            controlsPane.setVgap(10);
            controlsPane.setHgap(10);

            loadUsers();
        }

        public void loadUsers() {
            ArrayList<User> tempArrayList = AppLogin.getArraylistUser();

            for (int i=0; i<tempArrayList.size(); i++) {
                data.add(tempArrayList.get(i));
            }
        }

        public void checkUserInfo() {
            User u = lvUser.getSelectionModel().getSelectedItem();

            txtUsername.setText(u.getUsername());
            txtPassword.setText(u.getPassword());
            typeOfUser.setValue(u.getUserType());
        }

        public void addUser() {
            try {
                ArrayList<User> tempArrayList = AppLogin.getArraylistUser();

                User newUser = null;
    
                if (((String) typeOfUser.getValue()).equals("Manager")) {
                    newUser = new Manager();
                } else if (((String) typeOfUser.getValue()).equals("Information Manager")) {
                    newUser = new InformationManager();
                } else if (((String) typeOfUser.getValue()).equals("Translator")) {
                    newUser = new Translator();
                } else if (((String) typeOfUser.getValue()).equals("Consultant")) {
                    newUser = new Consultant();
                }
    
                newUser.setUsername(txtUsername.getText());
                newUser.setPassword(txtPassword.getText());
                newUser.setUserType((String) typeOfUser.getValue());
    
                data.add(newUser);
                tempArrayList.add(newUser);
                AppLogin.setArraylistUser(tempArrayList);
            } catch (EmptyFieldException efe) {
                System.out.println(efe.getMessage());
            }
        }

        public void deleteUser() {
            ArrayList<User> tempArrayList = AppLogin.getArraylistUser();

            User u = lvUser.getSelectionModel().getSelectedItem();
            int userIndex = lvUser.getSelectionModel().getSelectedIndex();

            data.remove(u);
            tempArrayList.remove(userIndex);
            AppLogin.setArraylistUser(tempArrayList);
        }

        public void updateUser() {
            try {
                ArrayList<User> tempArrayList = AppLogin.getArraylistUser();
                int userIndex = lvUser.getSelectionModel().getSelectedIndex();
    
                User updatedUser = null;
                
                if (((String) typeOfUser.getValue()).equals("Manager")) {
                    updatedUser = new Manager();
                } else if (((String) typeOfUser.getValue()).equals("Information Manager")) {
                    updatedUser = new InformationManager();
                } else if (((String) typeOfUser.getValue()).equals("Translator")) {
                    updatedUser = new Translator();
                } else if (((String) typeOfUser.getValue()).equals("Consultant")) {
                    updatedUser = new Consultant();
                }
    
                updatedUser.setUsername(txtUsername.getText());
                updatedUser.setPassword(txtPassword.getText());
                updatedUser.setUserType((String) typeOfUser.getValue());
    
                data.set(userIndex, updatedUser);
                tempArrayList.set(userIndex, updatedUser);
                AppLogin.setArraylistUser(tempArrayList);
            } catch (EmptyFieldException efe) {
                System.out.println(efe.getMessage());
            }
        }
    }
}
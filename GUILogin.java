import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
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
import javafx.stage.Stage;

public class GUILogin extends Application {
    private static Stage newStage;
    private static Scene signInScene, signUpScene;
    private static TextField txtUserLogin, txtPasswordLogin, txtUserRegister, txtPasswordRegister;
	private static ComboBox typeOfUser;

    public void start(Stage stage) throws Exception {
        newStage = stage;
        newStage.setTitle("Cultural Heritage");
        newStage.setWidth(900);
        newStage.setHeight(800);

        /***************************************/
        /*            Sign in pane             */
        /***************************************/
        GridPane signInGrid = new GridPane();
        signInGrid.maxHeight(350);
        signInGrid.setAlignment(Pos.CENTER);

        BorderPane signInPane = new BorderPane();
        signInPane.setMaxHeight(300);
        
        FlowPane signInTitle = new FlowPane();
        signInTitle.setAlignment(Pos.CENTER);
        signInTitle.setPadding(new Insets(0, 0, 30, 0));

        Text signInTitletxt = new Text("Login");
        signInTitletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        signInTitletxt.setFill(Color.BLACK);
        signInTitle.getChildren().add(signInTitletxt);

        FlowPane signInContentPane = new FlowPane();
        signInContentPane.setAlignment(Pos.CENTER);
        signInContentPane.setHgap(30);
        
        FlowPane signInControlsPane = new FlowPane();
        signInControlsPane.setAlignment(Pos.CENTER);
        
        signInPane.setTop(signInTitle);
        signInPane.setCenter(signInContentPane);
        signInPane.setBottom(signInControlsPane);

        // Content
        GridPane loginPane = new GridPane();
        loginPane.setPadding(new Insets(20));
        loginPane.setHgap(20);
        loginPane.setVgap(10);

        Label lblUserLogin = new Label("User");
        txtUserLogin = new TextField();
        loginPane.add(lblUserLogin, 0, 0);
        loginPane.add(txtUserLogin, 1, 0);

        Label lblPasswordLogin = new Label("Password");
        txtPasswordLogin = new TextField();
        loginPane.add(lblPasswordLogin, 0, 1);
        loginPane.add(txtPasswordLogin, 1, 1);

        signInContentPane.getChildren().add(loginPane);

        // Controls
        GridPane loginButtonPane = new GridPane();

        Button signInButton = new Button("Sign in");
        signInControlsPane.getChildren().add(signInButton);
        signInButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle (MouseEvent e) {
                AppLogin.signIn();
            }
        });
        
        Button buttonChangeToSignUp = new Button("Or sign up");
        buttonChangeToSignUp.setOnAction(e -> newStage.setScene(signUpScene));
        signInControlsPane.getChildren().add(buttonChangeToSignUp);
        
        signInControlsPane.setAlignment(Pos.CENTER);
        signInControlsPane.setPadding(new Insets(20));
        signInControlsPane.setVgap(10);
        signInControlsPane.setHgap(10);
        signInContentPane.getChildren().add(loginButtonPane);

        signInGrid.add(signInPane, 0, 0);
        signInScene = new Scene(signInGrid);

        /***************************************/
        /*            Sign up pane             */
        /***************************************/
        GridPane signUpGrid = new GridPane();
        signUpGrid.maxHeight(350);
        signUpGrid.setAlignment(Pos.CENTER);

        BorderPane signUpPane = new BorderPane();

        FlowPane signUpTitle = new FlowPane();
        signUpTitle.setAlignment(Pos.CENTER);
        signUpTitle.setPadding(new Insets(0, 0, 30, 0));

        Text signUpTitletxt = new Text("Sign Up");
        signUpTitletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        signUpTitletxt.setFill(Color.BLACK);
        signUpTitle.getChildren().add(signUpTitletxt);

        FlowPane signUpContentPane = new FlowPane();
        signUpContentPane.setAlignment(Pos.CENTER);
        signUpContentPane.setHgap(30);
        
        FlowPane signUpControlsPane = new FlowPane();
        signUpControlsPane.setAlignment(Pos.CENTER);
        
        signUpPane.setTop(signUpTitle);
        signUpPane.setCenter(signUpContentPane);
        signUpPane.setBottom(signUpControlsPane);

        // Content
        GridPane registerPane = new GridPane();
        registerPane.setPadding(new Insets(20));
        registerPane.setHgap(20);
        registerPane.setVgap(10);

        Label lblUserRegister = new Label("User");
        txtUserRegister = new TextField();
        registerPane.add(lblUserRegister, 0, 0);
        registerPane.add(txtUserRegister, 1, 0);

        Label lblPasswordRegister = new Label("Password");
        txtPasswordRegister = new TextField();
        registerPane.add(lblPasswordRegister, 0, 1);
        registerPane.add(txtPasswordRegister, 1, 1);

        signUpContentPane.getChildren().add(registerPane);

        String users[] = { "Manager", "Information Manager", "Translator", "Consultant" };
        
        Label lblTypeOfUserRegister = new Label("Type of user");
        typeOfUser = new ComboBox(FXCollections.observableArrayList(users));
        typeOfUser.setValue("Manager");
        registerPane.add(lblTypeOfUserRegister, 0, 2);
        registerPane.add(typeOfUser, 1, 2);
        
        // Controls
        GridPane registerButtonPane = new GridPane();

        Button signUpButton = new Button("Sign up");
        signUpControlsPane.getChildren().add(signUpButton);
        signUpButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle (MouseEvent e) {
                AppLogin.registerUser();
                
            }
        });

        Button buttonChangeToSignIn = new Button("Cancel sign up");
        buttonChangeToSignIn.setOnAction(e -> newStage.setScene(signInScene));
        signUpControlsPane.getChildren().add(buttonChangeToSignIn);
        
        signUpControlsPane.setAlignment(Pos.CENTER);
        signUpControlsPane.setPadding(new Insets(20));
        signUpControlsPane.setVgap(10);
        signUpControlsPane.setHgap(15);
        signUpControlsPane.getChildren().add(registerButtonPane);

        signUpGrid.add(signUpPane, 0, 0);
        signUpScene = new Scene(signUpGrid);

        // For loading all users
        AppLogin.retrieve();

        //Add style to the scene
        signInScene.getStylesheets().add(GUILogin.class.getResource("css/CulturalHeritage.css").toExternalForm());
        signUpScene.getStylesheets().add(GUILogin.class.getResource("css/CulturalHeritage.css").toExternalForm());
        
        newStage.setOnHiding( event -> {
            AppLogin.persist();
        });
        
        newStage.setScene(signInScene);
        newStage.show();
    }

    public static String getUsernameLoginValue() {
        return(txtUserLogin.getText());
    }

    public static String getPasswordLoginValue() {
        return(txtPasswordLogin.getText());
    }

    public static String getUsernameRegisterValue() {
        return(txtUserRegister.getText());
    }

    public static String getPasswordRegisterValue() {
        return(txtPasswordRegister.getText());
    }

    public static String getUserTypeRegister() {
        return((String) typeOfUser.getValue());
    }

    public static void changeScene(Scene scene) {
        newStage.setScene(scene);
    }
    public static void backLogin(){
        changeScene(signInScene);
        txtUserLogin.setText("");
        txtPasswordLogin.setText("");
        txtPasswordRegister.setText("");
        txtUserRegister.setText("");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
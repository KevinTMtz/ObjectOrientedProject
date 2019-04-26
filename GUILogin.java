import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
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

public class GUILogin extends Application {
    Scene signInScene, signUpScene;
    private TextField txtUserLogin, txtPasswordLogin, txtUserRegister, txtPasswordRegister;
	private ComboBox typeOfUser;

    public void start(Stage stage) throws Exception {
        stage.setTitle("Login");
        stage.setWidth(800);
        stage.setHeight(500);

        // 1. Create the base/root node
        /***************************************/
        /*            Sign in pane             */
        /***************************************/
        BorderPane signInPane = new BorderPane();
        
        FlowPane signInTitle = new FlowPane();
        signInTitle.setAlignment(Pos.CENTER);
        signInTitle.setPadding(new Insets(30));

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
        
        Button buttonChangeToSignUp = new Button("Or sign up");
        buttonChangeToSignUp.setOnAction(e -> stage.setScene(signUpScene));
        signInControlsPane.getChildren().add(buttonChangeToSignUp);
        
        signInControlsPane.setAlignment(Pos.CENTER);
        signInControlsPane.setPadding(new Insets(20));
        signInControlsPane.setVgap(10);
        signInControlsPane.setHgap(10);
        signInContentPane.getChildren().add(loginButtonPane);

        /***************************************/
        /*            Sign up pane             */
        /***************************************/
        BorderPane signUpPane = new BorderPane();

        FlowPane signUpTitle = new FlowPane();
        signUpTitle.setAlignment(Pos.CENTER);
        signUpTitle.setPadding(new Insets(30));

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

        // Combo box
        String users[] = { "Manager", "Information Manager", "Translator", "Consultant" };
        
        Label lblTypeOfUserRegister = new Label("Type of user");
        typeOfUser = new ComboBox(FXCollections.observableArrayList(users));
        registerPane.add(lblTypeOfUserRegister, 0, 2);
        registerPane.add(typeOfUser, 1, 2);
        
        // Controls
        GridPane registerButtonPane = new GridPane();

        Button signUpButton = new Button("Sign up");
        signUpControlsPane.getChildren().add(signUpButton);
        
        Button buttonChangeToSignIn = new Button("Cancel sign up");
        buttonChangeToSignIn.setOnAction(e -> stage.setScene(signInScene));
        signUpControlsPane.getChildren().add(buttonChangeToSignIn);
        
        signUpControlsPane.setAlignment(Pos.CENTER);
        signUpControlsPane.setPadding(new Insets(20));
        signUpControlsPane.setVgap(10);
        signUpControlsPane.setHgap(10);
        signUpControlsPane.getChildren().add(registerButtonPane);

        
        /***************************************/
        // 2. Create the main scene
        /***************************************/
        signInScene = new Scene(signInPane);
        signUpScene = new Scene(signUpPane);

        //scene.getStylesheets().add(GUILogin.class.getResource("Application.css").toExternalForm());
        stage.setScene(signInScene);

        // 3. Call the show method for the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
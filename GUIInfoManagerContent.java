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

public class GUIInfoManagerContent extends Scene {
    public GUIInfoManagerContent() {
        super(new AddContentPane());
        this.getStylesheets().add(GUIInfoManagerContent.class.getResource("css/CulturalHeritage.css").toExternalForm());
    }

    public static class AddContentPane extends BorderPane {
        private static TextArea txtContent;
        private Button saveButton, returnButton;

        public AddContentPane() {
            
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(20, 0, 40, 0));

            Text titletxt = new Text("Add content");
            titletxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
            titletxt.setFill(Color.BLACK);
            title.getChildren().add(titletxt);

            FlowPane contentPane = new FlowPane();
            contentPane.setAlignment(Pos.CENTER);
            contentPane.setHgap(30);

            txtContent = new TextArea();
            txtContent.setPrefWidth(850);
            txtContent.setPrefHeight(400);
            txtContent.setWrapText(true);
            contentPane.getChildren().add(txtContent);
            
            FlowPane controlsPane = new FlowPane();
            controlsPane.setAlignment(Pos.CENTER);
            
            this.setTop(title);
            this.setCenter(contentPane);
            this.setBottom(controlsPane);

            saveButton = new Button("Save");
            controlsPane.getChildren().add(saveButton);
            saveButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    saveContent();
                }
            });

            returnButton = new Button("Return");
            controlsPane.getChildren().add(returnButton);
            returnButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle (MouseEvent e) {
                    GUILogin.changeScene(new GUIInformationManager());
                }
            });
            
            controlsPane.setAlignment(Pos.CENTER);
            controlsPane.setPadding(new Insets(20));
            controlsPane.setVgap(10);
            controlsPane.setHgap(10);

            setInfo();
        }

        public static void setInfo() {
            String content = AppLogin.getArraylistResource().get(GUIInformationManager.getIndex()).getTextualContent();

            txtContent.setText(content);
        }

        public static void saveContent() {
            try {
                ArrayList<Recurso> tempArray = AppLogin.getArraylistResource();

                tempArray.get(GUIInformationManager.getIndex()).setTextualContent(txtContent.getText());
                if(tempArray.get(GUIInformationManager.getIndex()).getCurrentStatus().equals("to amend")){
                    tempArray.get(GUIInformationManager.getIndex()).setCurrentStatus("translating");
                    Alert amend = new Alert(Alert.AlertType.ERROR);
                    amend.setTitle("Succesful Operation");
                    amend.setHeaderText("Amend requested has been solved");
                    amend.setContentText("Back to translating state");
                    amend.showAndWait();
                }
                AppLogin.setArraylistResource(tempArray);
            } catch (EmptyFieldException efe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TextArea empty");
                alert.setHeaderText("Error while adding content");
                alert.setContentText("Content can't be empty");
                alert.showAndWait();
            }
        }
    }
}
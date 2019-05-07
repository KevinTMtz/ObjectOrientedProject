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
    }

    public static class AddContentPane extends BorderPane {
        private TextArea txtContent;
        private Button saveButton, returnButton;

        public AddContentPane() {
            FlowPane title = new FlowPane();
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(40, 0, 20, 0));

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

            //setInfo();
        }

        /*public void setInfo() {
            String content = AppLogin.getArraylistResource().get(GUIInformationManager.getSelectedIndex()).getTextualContent();

            txtContent.setText(content);
        }*/
    }
}
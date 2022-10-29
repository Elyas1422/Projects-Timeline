package Application;

import java.util.ArrayList;

import Entities.Project;
import Entities.ProjectStage;
import FilesProcessing.ExcelFileReader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Application.TimelineDraw;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage arg0) throws Exception {
        Scene scene = new Scene(getProjecstListView(ExcelFileReader.readProject()),800,400); 
        arg0.setResizable(false);
        arg0.setTitle("SWE316 HW1");
        arg0.setScene(scene);
        arg0.show();
    }
    public static VBox getProjecstListView(ArrayList<Project> projectsArray){
        Text title= new Text("Project Id: "+projectsArray.get(0).getCustomerProjectID());
        HBox titleBox = new HBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Calibri",FontWeight.BOLD,18 ));
        titleBox.setPadding(new Insets(10,0,0,0));
        VBox box = new VBox(); 
        box.getChildren().add(titleBox);
        ListView<Project> projectsView= new ListView();
        ObservableList<Project> projectViewItems=FXCollections.observableArrayList(projectsArray);
        projectsView.setItems(projectViewItems);
        box.getChildren().add(projectsView);
        projectsArray.get(0).setStages();
        AnchorPane TLD=TimelineDraw.getTimeLineDraw(projectsArray.get(0));
        box.getChildren().add(TLD);
        EventHandler<MouseEvent> handler = e -> { 
            Project p = projectsView.getSelectionModel().getSelectedItem();
            p.setStages();
            box.getChildren().remove(2);
            title.setText("Project Id: "+p.getCustomerProjectID());
            box.getChildren().add(TimelineDraw.getTimeLineDraw(p)) ;
        };
        projectsView.setOnMouseClicked(handler);
        return box;
    }
    private static void updateTimeLine(AnchorPane timeLine,AnchorPane newTimeLine) {
        timeLine=newTimeLine;
    }
}
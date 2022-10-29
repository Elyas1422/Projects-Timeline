package Application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Entities.Project;
import Entities.ProjectStage;
import FilesProcessing.ExcelFileReader;
public class TimelineDraw extends Application {
    public static void main(String[] args) throws ParseException {
        launch(args);
    }
    public void start(Stage arg0) throws Exception {
        
        ArrayList<Project> p = ExcelFileReader.readProject();
        p.get(0).setStages();
        System.out.println(p.get(0).getDuration());
        System.out.println();
        ArrayList<ProjectStage> s = p.get(0).getStages();
        Scene scene = new Scene(getTimeLineDraw(s)); 
        arg0.setTitle("Lab22");
        arg0.setScene(scene);
        arg0.show();
    }
    public AnchorPane getTimeLineDraw(ArrayList<ProjectStage> stages) {
        int s= stages.size();
         Text startDate=new Text(stages.get(0).toString());
         startDate.setLayoutX(80);
         startDate.setLayoutY(20);
        AnchorPane container= new AnchorPane();
       container.getChildren().add(startDate);
        HBox x= new HBox();
        x.setMinHeight(100);
        x.setMinWidth(800);
        container.getChildren().add(x);
        Line timeline=new Line();
        timeline.setStartY(50);
        timeline.setEndY(50);
        timeline.setStartX(100);
        timeline.setEndX(700);
        for (int i=0; i<s;i++) {
            Line stage= new Line();
            stage.setStartY(30);
            stage.setEndY(80);
            stage.setStartX(100+600*(((1.0)*i)/s));
            stage.setEndX(100+600*(((1.0)*i)/s));
            container.getChildren().add(stage);
        }
        container.getChildren().add(timeline);
        return container;
    };
    private int differenceInDays(ProjectStage smaller, ProjectStage larger) {
        return (int) TimeUnit.MILLISECONDS.toDays(larger.getDate().getTime() - smaller.getDate().getTime());  
    }
   
}

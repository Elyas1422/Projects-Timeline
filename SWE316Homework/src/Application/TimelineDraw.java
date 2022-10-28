package Application;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import Entities.ProjectTimeline;
public class TimelineDraw extends Application {
    public static void main(String[] args) {
        Date[] d= new Date[3];
        d[0]= new Date(2010,0,3);
        d[1]=new Date(2010,0,10);
        d[2]=new Date(2010,0,12);
        launch(args);
    }
    public void start(Stage arg0) throws Exception {
        Date[] d= new Date[5];
        d[0]= new Date(2010-1900,0,3);
        d[1]=new Date(2010-1900,0,10);
        d[2]=new Date(2010-1900,0,12);
        ProjectTimeline p= new ProjectTimeline( "1111", "2222",
                null);
        Scene scene = new Scene(getTimeLineDraw(p)); 
        arg0.setTitle("Lab22");
        arg0.setScene(scene);
        arg0.show();
    }
    public AnchorPane getTimeLineDraw(ProjectTimeline p) {
        int s= p.getStages().length;

        
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(p.getStages()[0]);
        Text startDate=new Text(stringDate);
        startDate.setLayoutX(80);
        startDate.setLayoutY(20);
        
        System.out.println(stringDate);
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
   
}

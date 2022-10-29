package Application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Style;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import Entities.Project;
import Entities.ProjectStage;
import FilesProcessing.ExcelFileReader;
public class TimelineDraw {
    
    public static AnchorPane getTimeLineDraw(Project p) {
        ArrayList<ProjectStage> stages= p.getStages();
        int s= p.getDuration();
        Text startDate=new Text(stages.get(0).toString());
        startDate.setLayoutX(80);
        startDate.setLayoutY(20);
        AnchorPane container= new AnchorPane();
        //container.getChildren().add(startDate);
        HBox x= new HBox();
        x.setMinHeight(150);
        x.setMinWidth(800);
        container.getChildren().add(x);
        Line timeline=new Line();
        timeline.setStartY(50);
        timeline.setEndY(50);
        timeline.setStartX(100);
        timeline.setEndX(700);
        ArrayList<VBox> linesList= new ArrayList<VBox>();
        for (int i=0; i<=s;i++) {
            VBox linebox =new VBox();
            Text date = new Text();
            linebox.getChildren().add(date);
            Line stageLine= new Line();
            stageLine.setStartY(40);
            stageLine.setEndY(50);
            linebox.setLayoutY(30);
            linebox.setLayoutX(100+600*(((1.0)*i)/s));
            linebox.getChildren().add(stageLine);
            linesList.add(linebox);
            container.getChildren().add(linebox);
            
        }
        
        for (ProjectStage ps : stages) {
            int dif =differenceInDays( stages.get(0),  ps);
            
            
            Text stageText= new Text(ps.getNewValue()+"");
            if (!(ps.getProgress()))
                stageText.setFill(Color.RED);
            linesList.get(dif).getChildren().add(stageText);
            
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM");
            String stringDate= DateFor.format(ps.getDate());
            Text n = (Text)linesList.get(dif).getChildren().get(0);
            n.setText(stringDate);
            Line linex = (Line)linesList.get(dif).getChildren().get(1);
            linex.setStartY(40);
            linex.setEndY(65);

            
        }
        container.getChildren().add(timeline);
        return container;
    };
    private static int differenceInDays(ProjectStage smaller, ProjectStage larger) {
        return (int) TimeUnit.MILLISECONDS.toDays(larger.getDate().getTime() - smaller.getDate().getTime());  
    }

}






































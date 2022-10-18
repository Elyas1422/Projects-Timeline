package Application;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class lab22_a extends Application {
  public static void main(String[] args) {
    launch(args);
  }
public void start(Stage arg0) throws Exception {
	StackPane titlepane  = new StackPane();
	Text text = new Text("Hello World");
	HBox chboxes = new HBox(); 
	chboxes.setSpacing(10);
	CheckBox bold =new CheckBox("bold");
	CheckBox italic  =new CheckBox("italic");
	chboxes.getChildren().addAll(bold,italic);
	
	String [] sizes = new String [100];
	for (int i = 0; i< 100; i++)
		sizes[i]= i+1 +"";
	ComboBox<String> sizeBox = new ComboBox<>(); 
    ObservableList<String> items1 = FXCollections.observableArrayList(sizes);
	sizeBox.getItems().addAll(items1);
	ComboBox<String> fontBox = new ComboBox<>(); 
	ObservableList<String> items2 = FXCollections.observableArrayList(Font.getFontNames());
	fontBox.getItems().addAll(items2);
	HBox comBoxes = new HBox();
	comBoxes.setAlignment(Pos.CENTER);
	comBoxes.setSpacing(15);
	comBoxes.getChildren().addAll(new Text("Font"),fontBox,new Text("Font size"),sizeBox);
	EventHandler<ActionEvent> handler = e -> { 
	      if (bold.isSelected() && italic.isSelected()) {
	        text.setFont(Font.font(fontBox.getValue(),FontWeight.BOLD, FontPosture.ITALIC, Integer.parseInt(sizeBox.getValue())));
	      }
	      else if (bold.isSelected()) {
	    	  text.setFont(Font.font(fontBox.getValue(),FontWeight.BOLD, FontPosture.REGULAR, Integer.parseInt(sizeBox.getValue())));
	      }
	      else if (italic.isSelected()) {
	    	  text.setFont(Font.font(fontBox.getValue(),FontWeight.NORMAL, FontPosture.ITALIC, Integer.parseInt(sizeBox.getValue())));
	      }      
	      else {
	    	  text.setFont(Font.font(fontBox.getValue(),FontWeight.NORMAL, FontPosture.REGULAR, Integer.parseInt(sizeBox.getValue())));
	      }
	    };
	    
	    bold.setOnAction(handler);
	    italic.setOnAction(handler);
	    sizeBox.setOnAction(handler);
	    fontBox.setOnAction(handler);
	titlepane.getChildren().add(text);
	BorderPane mainPane = new BorderPane();
	mainPane.setCenter(titlepane);
	mainPane.setBottom(chboxes);
	mainPane.setTop(comBoxes);
	chboxes.setAlignment(Pos.CENTER);
	Scene scene = new Scene(mainPane,600,400); 
	arg0.setTitle("Lab22");
	arg0.setScene(scene);
	arg0.show();
}
}
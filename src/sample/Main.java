package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application{
    GridPane root = new GridPane();
    String str="";
    calculator cal = new calculator();
    @Override
    public void start(Stage primaryStage) {
        Label number = new Label();
        number.setText("0");
        Button [] numberButtons = new Button[10];
        Button [] operationButtons = new Button[8];
        Character [] operations = {'+','-','*','/','c','=','(',')'};
        for (int i=0;i<operationButtons.length;i++){
            operationButtons[i] = new Button();
            int temp=i;
            operationButtons[i].setText(String.valueOf(operations[i]));
            if(i!=4&&i!=5){
            operationButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    str+=operationButtons[temp].getText();
                    number.setText(str);
                }
            });}else if(i==4){
                operationButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        str="";
                        number.setText(str);
                    }
                });
            }else{
                operationButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        number.setText(String.valueOf(cal.cal(str)));
                        str="";
                    }
                });
            }
            root.add(operationButtons[i],3+i%3,i/3);
        }
        for (int i=0;i<numberButtons.length;i++){
            numberButtons[i]=new Button();
            int temp=i;
            numberButtons[i].setText(String.valueOf(temp));
            numberButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    str+=numberButtons[temp].getText();
                    number.setText(str);
                }
            });
            root.add(numberButtons[i],i%3,i/3);
        }
        root.add(number,5,5);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
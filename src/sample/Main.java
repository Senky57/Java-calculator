package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double x;
    private double y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //posun s oknem
        root.setOnMousePressed(event ->{
                x = event.getSceneX();
                y = event.getSceneY();
        });

        root.setOnMouseDragged(event ->{
                primaryStage.setX(event.getSceneX()-x);
                primaryStage.setY(event.getSceneY()-y);
        });

        //konec posunu s oknem

        primaryStage.setTitle("Kalkulačka");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/calc.png")));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        root.requestFocus();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

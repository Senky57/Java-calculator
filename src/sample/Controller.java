package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {
    String number1;
    boolean newdigit = true;

    @FXML
    private BorderPane BasicPane;
    private Stage myStage = null;

    @FXML
    TextField display;

    @FXML
    ImageView maxIcon;

    @FXML
    void exitCalc(MouseEvent event) {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    void pressDigit(ActionEvent event){
        Button btn = (Button)event.getSource();
        String tmp = display.getText();
        if(Double.parseDouble(tmp)==0 || newdigit) {
            display.setText(btn.getText());
            newdigit = false;
        }
        else {
            myStage = (Stage) BasicPane.getScene().getWindow();
            if (tmp.length()*17>display.getWidth()) myStage.setWidth(myStage.getWidth()+17);
            display.setText(display.getText() + btn.getText());
        }
    }

    @FXML
    public void callC(ActionEvent event){
        number1 = null;
        display.setText("0");
    }

    @FXML
    public void callCE(ActionEvent event){
        display.setText("0");
    }

    //Rekurzivní volání fce = funkce volá sama sebe do té doby, než se to vyřeší
    long factorial(long num){
        if (num >= 1) return num * factorial(num - 1);
        else return 1;
    }

        // 5! = 5 * 4 * 3 * 2 * 1, funguje jen z kladného čisla
    @FXML
    public void callFactorial(ActionEvent event){
        String tmp = display.getText();
        if(tmp.indexOf('.')!=-1){
            //error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Faktoriál");
            alert.setHeaderText("Chyba při práci s faktoriálem");
            alert.setContentText("Faktoriál je definován pouze z celých čísel");
            alert.showAndWait();
            //error
            //System.err.println("Nemůžeš..."); //vypíše chybu, napříkal ". a foktorial"
            return;
        }
          long num = Integer.parseInt(tmp);
        display.setText(String.valueOf(factorial(num)));
    }

    @FXML
    public void callPoint(ActionEvent event){
        String tmp = display.getText();
        if(tmp.indexOf('.')==-1) display.setText(tmp + ".");

    }

    @FXML
    void maxCalc(MouseEvent event) {
        myStage = (Stage) BasicPane.getScene().getWindow();
        if (myStage.isMaximized()) {
            myStage.setMaximized(false);
            maxIcon.setImage(new Image(getClass().getResourceAsStream("../images/maxbutt.png")));
        } else{
            myStage.setMaximized(true);
        maxIcon.setImage(new Image(getClass().getResourceAsStream("../images/maximize.png")));
            }
    }

    @FXML
    void minCalc(MouseEvent event) {
        myStage = (Stage)BasicPane.getScene().getWindow();
        myStage.setIconified(true);

    }

    public void callOperation(ActionEvent actionEvent) {
        if(number1!=null){
            char operation = number1.charAt(number1.length()-1);
            double number = Double.parseDouble(number1.substring(0,number1.length()-1));
            double number2 = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation) {
                case '+':
                    result = number + number2;
                    break;
                case '-':
                    result = number - number2;
                    break;
                case '*':
                    result = number * number2;
                    break;
                case '/':
                    result = number / number2;
                    break;
            }
            display.setText(String.valueOf(result));
        }
        newdigit = true;
        number1 = display.getText() + ((Button) actionEvent.getSource()).getText(); //number1 == 98+, nebo 185/, nebo 45-
        //System.out.println(number1);
    }

    public void callRovno(ActionEvent actionEvent) {
        if(number1 != null){
            char operation = number1.charAt(number1.length()-1);
            double number = Double.parseDouble(number1.substring(0,number1.length()-1));
            double number2 = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation) {
                case '+':
                    result = number + number2;
                    break;
                case '-':
                    result = number - number2;
                    break;
                case '*':
                    result = number * number2;
                    break;
                case '/':
                    result = number / number2;
                    break;
            }
            display.setText(String.valueOf(result));
            number1 = null;
        }
        newdigit = true;
        //number1 = display.getText() + ((Button) actionEvent.getSource()).getText(); //number1 == 98+, nebo 185/, nebo 45-
        //System.out.println(number1);
    }

    public void callProcenta(ActionEvent actionEvent) {
        if(number1 != null){
            char operation = number1.charAt(number1.length()-1);
            double number = Double.parseDouble(number1.substring(0,number1.length()-1));
            double number2 = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation) {
                case '+': //number=základ, number2=pocet procent o kolik se má navýšit
                    result = number * ((100 + number2)/100);
                    break;
                case '-': //number=základ, number2=pocet procent o kolik se má snížit
                    result = number - ((number/100)*number2);
                    break;
                case '*': //number=základ, number2=pocet procent ze zakladu
                    result = (number/100) * number2;
                    break;
                case '/': //number=procentuální část, number2=základ
                    result = number / (number2/100);
                    break;
            }
            display.setText(String.valueOf(result));
            number1 = null;
        }
        newdigit = true;
        //number1 = display.getText() + ((Button) actionEvent.getSource()).getText(); //number1 == 98+, nebo 185/, nebo 45-
        //System.out.println(number1);
    }
}

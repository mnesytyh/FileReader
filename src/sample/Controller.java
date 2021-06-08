package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.nio.charset.Charset;

public class Controller {
    @FXML
    private Button btnFileChoose;
    @FXML
    private TextArea taFileContent;

    @FXML
    public void initialize(){
        btnFileChoose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Window wnd = ((Node) (actionEvent.getSource())).getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(wnd);
                FileInputStream fis=null;
                if(file != null) {
                    try {
                        fis = new FileInputStream((file));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (fis != null) {
                        try (BufferedReader br = new BufferedReader(
                                new InputStreamReader(fis,Charset.forName("UTF-8")))) {
                            String str, str1 = "";
                            while ((str = br.readLine()) != null) {
                                str1 += str + "\n";
                            }
                            taFileContent.setText(str1);
                        } catch (Exception e) {
                            Alert errMessage = new Alert(Alert.AlertType.ERROR);
                            errMessage.setTitle("Ошибка");
                            errMessage.setContentText(e.toString());
                            errMessage.showAndWait();
                        }
                    }
                }
                actionEvent.consume();
            }
        });
    }
}

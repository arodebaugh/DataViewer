package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.Thread;

public class Controller {
    // Fields
    public TextField inputText;
    public Button sendButton;
    public Button uploadButton;
    public ListView<String> listView;

    // Constructor
    private Data data;
    private Get get;
    private Stage primaryStage;

    private Thread getThread;

    private String fileName;

    public Controller() { }

    public void initialize() {
        data = new Data(true, 100, listView);
        get = new Get(data);
        getThread = new Thread(get);
        getThread.start();
    }

    public void setStage(Stage p) {
        primaryStage = p;
    }

    // Methods
    public void onButtonClick() {
        data.put(inputText.getText());
        inputText.setText("");
    }

    public void onUploadButtonClick() {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);

        fileName = new File(fileChooser.showOpenDialog(primaryStage).getAbsolutePath()).toURI().toString();

        Image openedImage = new Image(fileName);

        data.put(openedImage);
    }
}

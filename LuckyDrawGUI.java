import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class LuckyDrawGUI extends Application {

    private Stage mainWindow;
    private Scene shuffleScene;
    private Scene winnerScene;
    private Scene startScene;

    public Button goButton;
    public Button winnerButton;

    public Label firstWinner;
    public Label secondWinner;
    public Label thirdWinner;

    public VBox winnerVbox;

    private static LuckyDraw luckyDraw;

    /*{
        try {
            luckyDraw = new LuckyDraw("LuckyDrawParticipants.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    /**
     * The method that starts the window of the GUi up.
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //needs to read from text or Excel file
        this.mainWindow = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SceneBuilder.fxml"));
        Parent root = loader.load();
        ( (LuckyDrawGUI) loader.getController() ).setPrimaryStage(primaryStage);
        startScene = new Scene(root, 800,600);

        mainWindow.setTitle("Lucky Draw!");
        mainWindow.setScene(startScene);


        //FXMLLoader loader2 = new FXMLLoader (getClass().getResource("ShuffleScene.fxml"));
        //Parent shuffleRoot = loader2.load();
        //shuffleScene = new Scene(shuffleRoot, 800,600);
        //mainWindow.setScene(shuffleScene);

        //( (LuckyDrawGUI) loader.getController() ).setPrimaryStage(primaryStage);
        mainWindow.show();
    }

    public void goButtonPressed() throws IOException{
        FXMLLoader loader2 = new FXMLLoader (getClass().getResource("ShuffleScene.fxml"));
        Parent shuffleRoot = loader2.load();
        ( (LuckyDrawGUI) loader2.getController() ).setPrimaryStage(mainWindow);
        shuffleScene = new Scene(shuffleRoot, 800,600);
        //LuckyDraw luckyDraw;

        {
            try {
                luckyDraw = new LuckyDraw("LuckyDrawParticipants.txt");
            } catch (IOException e) {
                luckyDraw = null;
                e.printStackTrace();
            }
        }
        int random;
       // ArrayList<String> numList = luckyDraw.getNumList();
       // random = luckyDraw.getRandomIndex();
        //System.out.println(numList.get(random));
       // luckyDraw.addPersonToWinList(numList.get(random));
        //luckyDraw.removePersonFromList(random);
        //System.out.println(numList);


        mainWindow.setScene(shuffleScene);
    }

    public void randomWinnerButtonPressed() throws IOException{
        int random;
        ArrayList<String> numList = luckyDraw.getNumList();
        random = luckyDraw.getRandomIndex();
        System.out.println(numList.get(random));
        firstWinner = new Label(numList.get(random));
        winnerVbox.getChildren().add(firstWinner);
        luckyDraw.addPersonToWinList(numList.get(random));
        luckyDraw.removePersonFromList(random);
        //System.out.println(numList);
    }

    public void finishButtonPressed() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SceneBuilder.fxml"));
        Parent root = loader.load();
        ( (LuckyDrawGUI) loader.getController() ).setPrimaryStage(mainWindow);
        startScene = new Scene(root, 800,600);

        mainWindow.setTitle("Lucky Draw!");
        mainWindow.setScene(startScene);
    }

    public void setPrimaryStage(Stage stage){
        this.mainWindow = stage;
    }
}

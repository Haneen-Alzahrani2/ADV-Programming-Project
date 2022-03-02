import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Profile1Controller implements Initializable {

    @FXML Label name;
    @FXML Label email;
    @FXML Label number;
    @FXML Label level;
    @FXML AnchorPane sc;
    @FXML Button buEntery;
    @FXML Label label1;
    private double sec = 0.5; 
    private String username ;
    
    public void set(Stage s){
        this.username = s.getUserData().toString();
    }
    
    public void getinfo(){
        System.out.println(username + "= em ");
        User u = mysql.UserData(username);
        email.setText(u.getUsername());
        name.setText(u.getName());
        number.setText(u.getMobile());
        level.setText(u.getUserLevel());
    }
    
    public void bindL(){
        label1.textProperty().bind(name.textProperty());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Timeline timer = new Timeline ();
            timer.setCycleCount(1);//Timeline.INDEFINITE
            if (timer!=null)
                timer.stop();
            KeyFrame frame = new KeyFrame (Duration.seconds(1), e->{
                sec= sec-1; 
                System.out.println(sec);
                if (sec <= 0){
                    timer.stop();
                    bindL();
                    getinfo();
                }
            });
            timer.getKeyFrames().add(frame);
            timer.playFromStart();
    }  
     @FXML
    private void back(MouseEvent e) throws IOException{
        Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
        String page ;
        page = "signHome.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(page));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
}
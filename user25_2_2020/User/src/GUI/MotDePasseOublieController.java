/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javax.swing.JOptionPane;
import util.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class MotDePasseOublieController implements Initializable {

    @FXML
    private JFXButton RecupererMDP;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXButton Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RecupererMDP(ActionEvent event) {
        AdminService as=new AdminService();
        if(as.existeParEmail(Email.getText()))
        {
            Random r=new Random();
            String ran="";
            String alphabet="123456789abcdefghijklmnopqrstuvwxzyABCDEFGHIJKLMNOPQRSTUVWXYZ:?!#";
            for(int i=0;i<15;i++)
            {
                ran+=alphabet.charAt(r.nextInt(alphabet.length()));
                
            }
            JavaMailUtil.SendMail(Email.getText(), ran);
            as.majMdp(Email.getText(), ran);
            JOptionPane.showMessageDialog(null, "Verifier votre boite mail");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Adresse mail non trouvé");
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));

        Parent root=fxml.load();

       Email.getScene().setRoot(root);
    }
    
}

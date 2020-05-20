/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ParentService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField mdp;
    @FXML
    private JFXButton connercter;
    @FXML
    private Hyperlink mdpO;
    @FXML
    private JFXButton CreerCompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connecter(ActionEvent event) throws IOException {
        ParentService ps =new ParentService();
        System.out.println("hi");
        entity.Parent p=ps.authentification(email.getText(), mdp.getText());
        System.out.println("hi2");
        System.out.println(p);
        if(p!=null)
        {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
            
            Parent root=fxml.load();
            
            mdp.getScene().setRoot(root);
            
            InterfaceParentController ipc= fxml.getController();
            System.out.println("hi3");
            ipc.setP(p);
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erreur d'authentification");
        }
        
    }

    @FXML
    private void motDepasseOublié(ActionEvent event) {
    }

    @FXML
    private void creerUnCompte(ActionEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("CreerCompteParent.fxml"));
        Parent root=fxml.load();
        mdp.getScene().setRoot(root);
        //RecuperationController rc =fxml.getController();
    }
    
}

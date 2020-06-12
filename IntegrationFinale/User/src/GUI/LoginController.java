/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import Service.EmployeService;
import Service.ParentService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Admin;
import entity.Employe;
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
    private JFXPasswordField mdp;
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
        AdminService as=new AdminService();
        EmployeService es=new EmployeService();
        entity.Parent p=ps.authentification(email.getText(), mdp.getText());
        if(p!=null)
        {
            System.out.println(p);
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
            Parent root=fxml.load();
            
            mdp.getScene().setRoot(root);
            
            InterfaceParentController ipc= fxml.getController();
            ipc.setP(p);
            
        }
        else
        {
            Admin a=as.authentification(email.getText(), mdp.getText());
            if(a!=null)
            {
                System.out.println(a);
                FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAdmin.fxml"));
            
                Parent root=fxml.load();
            
                mdp.getScene().setRoot(root);
            
                InterfaceAdminController iac= fxml.getController();
                iac.setA(a);
            }
            else
            {
                Employe e=es.authentification(email.getText(), mdp.getText());
                if(e!=null)
                {
                    System.out.println(e);
                    FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceEmploye.fxml"));

                    Parent root=fxml.load();

                    mdp.getScene().setRoot(root);

                    InterfaceEmployeController iec= fxml.getController();
                    System.out.println("hi3");
                    iec.setE(e);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erreur d'authentification");
                }
                
            }
            
        }
        
    }

    @FXML
    private void motDepasseOublié(ActionEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("MotDePasseOublie.fxml"));

        Parent root=fxml.load();

        mdp.getScene().setRoot(root);
    }

    @FXML
    private void creerUnCompte(ActionEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("CreerCompteParent.fxml"));
        Parent root=fxml.load();
        mdp.getScene().setRoot(root);
        //RecuperationController rc =fxml.getController();
    }
    
}

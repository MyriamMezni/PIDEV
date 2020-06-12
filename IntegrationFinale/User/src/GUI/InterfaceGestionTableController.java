/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import entity.Menu;
import entity.table;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import Service.chaiseservice;
import Service.manuservice;
import Service.tableservice;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceGestionTableController implements Initializable {

    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private TitledPane cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox SignOut;
    @FXML
    private JFXTabPane tabpane;
    @FXML
    private JFXTextField capacite;
    @FXML
    private Tab Afficher;
    @FXML
    private TableView<table> tableView;
    @FXML
    private JFXButton supprimer;
    @FXML
    private Tab ModifierTab;
    @FXML
    private ComboBox<Integer> id_table;
    @FXML
    private JFXTextField capacite2;
    @FXML
    private JFXButton modifier;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    @FXML
    private HBox Activite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    

    @FXML
    private void Ajouter(ActionEvent event) {
        tableservice ts=new tableservice();
        Integer i = new Integer(capacite.getText());
        
        ts.insert(new table(i));
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        capacite.setText("");
    }
    public ObservableList<table> getTables()
    {
        ObservableList<table> o=FXCollections.observableArrayList();
        tableservice ts=new tableservice();
        for(table t:ts.displayAll())
        {
            o.add(t);
        }
        return o;
        
    }
    @FXML
    private void supprimer(ActionEvent event) {
        chaiseservice cs= new chaiseservice();
        tableservice ts=new tableservice();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            cs.deleteParTable(tableView.getSelectionModel().getSelectedItem().getId());
            ts.delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getColumns().remove(0,tableView.getColumns().size());
            TableColumn<table,Integer> idColumn=new TableColumn<>("Id");
            idColumn.setMinWidth(100);
            idColumn.setCellValueFactory(new PropertyValueFactory<table,Integer>("id"));
            TableColumn<table,Integer> CapaciteColumn=new TableColumn<>("Capacite");
            CapaciteColumn.setMinWidth(200);
            CapaciteColumn.setCellValueFactory(new PropertyValueFactory<table,Integer>("capacite"));
            tableView.setItems(getTables());
            tableView.getColumns().addAll(idColumn,CapaciteColumn);
            
        }
        
    }

    @FXML
    private void Afficher_les_tables(Event event) {
       
       /*for ( int i = 0; i<tableView.getItems().size(); i++) {
           // tableView.getItems().clear();
            //tableView.getColumns().remove(i);
            
        }*/
        //tableView.getColumns().r
        
        //tableView.getItems().clear();
        if(Afficher.isSelected())
        {
            tableView.getColumns().remove(0,tableView.getColumns().size());
            TableColumn<table,Integer> idColumn=new TableColumn<>("Id");
            idColumn.setMinWidth(100);
            idColumn.setCellValueFactory(new PropertyValueFactory<table,Integer>("id"));
            TableColumn<table,Integer> CapaciteColumn=new TableColumn<>("Capacite");
            CapaciteColumn.setMinWidth(200);
            CapaciteColumn.setCellValueFactory(new PropertyValueFactory<table,Integer>("capacite"));
            tableView.setItems(getTables());
            tableView.getColumns().addAll(idColumn,CapaciteColumn);
        }
    }

    @FXML
    private void Id_Modififé(ActionEvent event) {
        if(ModifierTab.isSelected())
        {
            if(id_table.getSelectionModel().getSelectedItem()!=null)
            {
                System.out.println(id_table.getSelectionModel().getSelectedItem());
                tableservice ts=new tableservice();
                table t=ts.getTable(id_table.getSelectionModel().getSelectedItem());
                capacite2.setText(Integer.toString(t.getCapacite()));
            }
            
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        tableservice ts=new tableservice();
        table t=new table(id_table.getSelectionModel().getSelectedItem(), new Integer(capacite2.getText()));
        ts.update(t);
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        
    }

    @FXML
    private void RafrechirInfos(Event event) {
        id_table.getItems().removeAll(id_table.getItems());
        tableservice ts=new tableservice();
        for (Integer i : ts.getIds())
        {
            id_table.getItems().add(i);
        }
        id_table.getSelectionModel().select(ts.getIds().get(0));
    }

    @FXML
    private void gestionParents(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionParents.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEmploye(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionEmploye.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("reponse.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void Evenement(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Evenements.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    /**/

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Activite(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Actprog.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void CantineOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void CantineOn(MouseEvent event) {
        //info.setText("Gerer les chaises,tables et menus");
    }

    @FXML
    private void ParentOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ParentOn(MouseEvent event) {
        //info.setText("Gerer les parents");
    }

    @FXML
    private void EmployeOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EmployeOn(MouseEvent event) {
        //info.setText("Gerer les employes");
    }

    @FXML
    private void EnfantOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EnfantOn(MouseEvent event) {
        //info.setText("Gerer les enfants");
    }

    @FXML
    private void EvenementOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EvenementOn(MouseEvent event) {
        //info.setText("Gerer les evenements");
    }

    @FXML
    private void ActiviteOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ActiviteOn(MouseEvent event) {
        //info.setText("Gerer les activites");
    }

    @FXML
    private void Chaise(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceGestionDesChaise.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Table(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceGestionTable.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Menu(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAjouterMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    
}

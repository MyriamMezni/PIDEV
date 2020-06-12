/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import entity.Chaise;
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
import Service.tableservice;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceGestionDesChaiseController implements Initializable {

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
    private ComboBox<Integer> Ids_tables;
    @FXML
    private Tab Afficher;
    @FXML
    private TableView<Chaise> tableView;
    @FXML
    private JFXButton supprimer;
    @FXML
    private Tab ModifierTab;
    @FXML
    private ComboBox<Integer> id_table;
    @FXML
    private JFXButton modifier;
    @FXML
    private ComboBox<Integer> id_chaise;
    @FXML
    private ComboBox<String> etatPlat;
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
        etatPlat.getItems().removeAll(etatPlat.getItems());
        etatPlat.getItems().add("Vide");
        etatPlat.getItems().add("Rempli");
        Ids_tables.getItems().removeAll(Ids_tables.getItems());
        tableservice ts=new tableservice();
        for (Integer i: ts.getIdsDispo())
        {
            Ids_tables.getItems().add(i);
        }
        Ids_tables.getSelectionModel().select(0);
        // TODO
    }    


    

    @FXML
    private void Ajouter(ActionEvent event) {
        chaiseservice cs=new chaiseservice();
        cs.insert(new Chaise("Vide", Ids_tables.getSelectionModel().getSelectedItem()));
        tableservice ts=new tableservice();
        ts.update(new table(Ids_tables.getSelectionModel().getSelectedItem(),ts.getCapacite(Ids_tables.getSelectionModel().getSelectedItem())-1));
        //ts.decrimenter(Ids_tables.getSelectionModel().getSelectedItem());
        Ids_tables.getItems().removeAll(Ids_tables.getItems());
        for (Integer i: ts.getIdsDispo())
        {
            Ids_tables.getItems().add(i);
        }
        Ids_tables.getSelectionModel().select(0);
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            chaiseservice cs=new chaiseservice();
            cs.delete(tableView.getSelectionModel().getSelectedItem().getId_chaise());
            tableservice ts=new tableservice();
            ts.update(new table(tableView.getSelectionModel().getSelectedItem().getNum_table(), ts.getCapacite(tableView.getSelectionModel().getSelectedItem().getNum_table())+1));
            if(Afficher.isSelected())
            {
                tableView.getColumns().remove(0,tableView.getColumns().size());
                TableColumn<Chaise,Integer> idColumn=new TableColumn<>("Id chaise");
                idColumn.setMinWidth(100);
                idColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("id_chaise"));
                TableColumn<Chaise,Integer> enfantColumn=new TableColumn<>("Id enfant");
                enfantColumn.setMinWidth(200);
                enfantColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("id_enfant"));
                TableColumn<Chaise,Integer> tableColumn=new TableColumn<>("Num Table");
                tableColumn.setMinWidth(200);
                tableColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("num_table"));
                TableColumn<Chaise,String> platColumn=new TableColumn<>("Etat Plat");
                platColumn.setMinWidth(200);
                platColumn.setCellValueFactory(new PropertyValueFactory<Chaise,String>("etat_plat"));
                tableView.setItems(getChaise());
                tableView.getColumns().addAll(idColumn,enfantColumn,tableColumn,platColumn);
            }
        }
        
        
    }
    public ObservableList<Chaise> getChaise()
    {
        ObservableList<Chaise> o=FXCollections.observableArrayList();
        chaiseservice cs=new chaiseservice();
        for(Chaise c:cs.displayAll())
        {
            o.add(c);
        }
        return o;
        
    }
    @FXML
    private void Afficher_les_tables(Event event) {
        if(Afficher.isSelected())
        {
            tableView.getColumns().remove(0,tableView.getColumns().size());
            TableColumn<Chaise,Integer> idColumn=new TableColumn<>("Id chaise");
            idColumn.setMinWidth(100);
            idColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("id_chaise"));
            TableColumn<Chaise,Integer> enfantColumn=new TableColumn<>("Id enfant");
            enfantColumn.setMinWidth(200);
            enfantColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("id_enfant"));
            TableColumn<Chaise,Integer> tableColumn=new TableColumn<>("Num Table");
            tableColumn.setMinWidth(200);
            tableColumn.setCellValueFactory(new PropertyValueFactory<Chaise,Integer>("num_table"));
            TableColumn<Chaise,String> platColumn=new TableColumn<>("Etat Plat");
            platColumn.setMinWidth(200);
            platColumn.setCellValueFactory(new PropertyValueFactory<Chaise,String>("etat_plat"));
            tableView.setItems(getChaise());
            tableView.getColumns().addAll(idColumn,enfantColumn,tableColumn,platColumn);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        tableservice ts=new tableservice();
        chaiseservice cs=new chaiseservice();
        Chaise c= cs.getChaise(id_chaise.getSelectionModel().getSelectedItem());
        Chaise c2= new Chaise(id_chaise.getSelectionModel().getSelectedItem(), etatPlat.getSelectionModel().getSelectedItem(), id_table.getSelectionModel().getSelectedItem());
        ts.update(new table(c.getNum_table(), ts.getCapacite(c.getNum_table())+1));
        ts.update(new table(c2.getNum_table(), ts.getCapacite(c2.getNum_table())-1));
        cs.update(c2);
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        
        
    }

    @FXML
    private void Id_Modififé(ActionEvent event) {
        if(ModifierTab.isSelected())
        {
            if(id_chaise.getSelectionModel().getSelectedItem()!=null)
            {
                //System.out.println(id_chaise.getSelectionModel().getSelectedItem());
                tableservice ts=new tableservice();
                chaiseservice cs=new chaiseservice();
                Chaise c=cs.getChaise(id_chaise.getSelectionModel().getSelectedItem());
               // System.out.println(c);
                id_table.getItems().removeAll(id_table.getItems());
                for (Integer i: ts.getIdsDispo())
                {
                    id_table.getItems().add(i);
                }
                
                id_table.getSelectionModel().select(new Integer(c.getNum_table()));
                etatPlat.getSelectionModel().select(c.getEtat_plat());
            }
            
        }
    }

    @FXML
    private void RafrechirInfos(Event event) {
        id_chaise.getItems().removeAll(id_chaise.getItems());
        chaiseservice cs=new chaiseservice();
        for (Integer i : cs.getIds())
        {
            id_chaise.getItems().add(i);
        }
        id_chaise.getSelectionModel().select(cs.getIds().get(0));
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

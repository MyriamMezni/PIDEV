/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Menu;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import Service.manuservice;
import Service.menu_commandeservice;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import rest.file.uploader.tn.FileUploader;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceAjouterMenuController implements Initializable {

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
    private JFXTextField nom;
    @FXML
    private JFXTextArea Description;
    @FXML
    private JFXButton photo;
    @FXML
    private ImageView imageP;
    @FXML
    private JFXCheckBox dimanche;
    @FXML
    private JFXCheckBox mardi;
    @FXML
    private JFXCheckBox lundi;
    @FXML
    private JFXCheckBox mercredi;
    @FXML
    private JFXCheckBox jeudi;
    @FXML
    private JFXCheckBox vendredi;
    @FXML
    private JFXCheckBox samedi;
    private String chemin;
    private String chemin2;
    private String jours="";
    @FXML
    private TableView<MenuAffichage> tableView;

    @FXML
    private JFXTabPane tabpane;
    @FXML
    private ComboBox<Integer> id_menu;
    @FXML
    private JFXTextField nom2;
    @FXML
    private JFXTextArea description2;
    @FXML
    private JFXButton photo2;
    @FXML
    private JFXCheckBox dimanche2;
    @FXML
    private JFXCheckBox lundi2;
    @FXML
    private JFXCheckBox mardi2;
    @FXML
    private JFXCheckBox mercredi2;
    @FXML
    private JFXCheckBox jeudi2;
    @FXML
    private JFXCheckBox vendredi2;
    @FXML
    private JFXCheckBox samedi2;
    @FXML
    private JFXButton modifier;
    @FXML
    private Tab ModifierTab;
    @FXML
    private ImageView imageP2;
    private String jours2="";
    @FXML
    private JFXButton supprimer;
    @FXML
    private Tab Afficher;
    @FXML
    private Tab Statistiques;
    @FXML
    private PieChart pieChart;
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
        System.out.println("hello");
        // TODO
    }    


    

    @FXML
    private void ChoisirPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg"));
        File f=fileChooser.showOpenDialog(null);
        String ext=f.getName().substring(f.getName().lastIndexOf("."));
        int ran=(int)(Math.random()*10000);
        String pathran=ran+ext;
        File fo=new File(staticvar.Image_URL+pathran);
        staticvar.copyFileUsingStream(f,fo);
        chemin=pathran;
        Image i= new Image(f.toURI().toString());
        imageP.setImage(i);
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        /*if(dimanche.isSelected())
        {
            jours+="1";
        }*/
        if(lundi.isSelected())
        {
            jours+="2";
        }
        if(mardi.isSelected())
        {
            jours+="3";
        }
        if(mercredi.isSelected())
        {
            jours+="4";
        }
        if(jeudi.isSelected())
        {
            jours+="5";
        }
        if(vendredi.isSelected())
        {
            jours+="6";
        }
        if(samedi.isSelected())
        {
            jours+="7";
        }
        manuservice ms=new manuservice();
        /*FileUploader fu = new FileUploader("localhost/src/");
        String fileNameInServer="";
        try {
            fileNameInServer = fu.upload(chemin);
            System.out.println(fileNameInServer);
        } catch (ProtocolException ex) {
            Logger.getLogger(InterfaceAjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceAjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        ms.insert(new Menu(nom.getText(),chemin,Description.getText() , jours));
        jours="";
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        nom.setText("");
        Description.setText("");
        File f=new File("");
        Image im =new Image(f.toURI().toString());
        imageP.setImage(im);
        chemin="";
        lundi.setSelected(false);
        mardi.setSelected(false);
        mercredi.setSelected(false);
        jeudi.setSelected(false);
        vendredi.setSelected(false);
        samedi.setSelected(false);
        dimanche.setSelected(false);
    }
    public ObservableList<MenuAffichage> getMenus()
    {
        ObservableList<MenuAffichage> o=FXCollections.observableArrayList();
        manuservice ms=new manuservice();
        for(Menu m:ms.displayAll())
        {
            MenuAffichage ma= new MenuAffichage();
            ma.tranformer(m);
            o.add(ma);
        }
        return o;
    }

    @FXML
    private void Afficher_les_menus(Event event) {
        
        
        
        if(Afficher.isSelected())
        {
            System.out.println(tableView.getColumns().size());
            if(tableView.getItems().size()!=0)
            {
                tableView.getColumns().remove(0, 4);
            }
            tableView.getItems().clear();
            TableColumn<MenuAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
            imagesColumn.setMinWidth(100);
            imagesColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,ImageView>("i"));
            TableColumn<MenuAffichage,String> NomColumn=new TableColumn<>("Nom");
            NomColumn.setMinWidth(200);
            NomColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("nom"));
            TableColumn<MenuAffichage,String> DescriptionColumn=new TableColumn<>("Description");
            DescriptionColumn.setMinWidth(250);
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("description"));
            TableColumn<MenuAffichage,String> JoursColumn=new TableColumn<>("Jours");
            JoursColumn.setMinWidth(300);
            JoursColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("jours"));
            tableView.setItems(getMenus());
            tableView.getColumns().addAll(imagesColumn,NomColumn,DescriptionColumn,JoursColumn);
        }
    }


    @FXML
    private void choisirPhoto2(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg"));
        File f=fileChooser.showOpenDialog(null);
        String ext=f.getName().substring(f.getName().lastIndexOf("."));
        int ran=(int)(Math.random()*10000);
        String pathran=ran+ext;
        File fo=new File(staticvar.Image_URL+pathran);
        staticvar.copyFileUsingStream(f,fo);
        chemin2=pathran;
        Image i= new Image(f.toURI().toString());
        imageP2.setImage(i);
        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        chemin2=f.getPath();
        Image i= new Image(f.toURI().toString());
        imageP2.setImage(i);
        FileUploader fu = new FileUploader("localhost/src/");
        String fileNameInServer="";
        try {
            fileNameInServer = fu.upload(chemin2);
            //System.out.println(fileNameInServer);
            chemin2="C:\\wamp64\\www\\src\\uploads\\"+fileNameInServer;
        } catch (ProtocolException ex) {
            Logger.getLogger(InterfaceAjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceAjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }

    @FXML
    private void RafrechirInfos(Event event) {
        id_menu.getItems().removeAll(id_menu.getItems());
        manuservice ms=new manuservice();
        for (Integer i : ms.getIds())
        {
            id_menu.getItems().add(i);
        }
        id_menu.getSelectionModel().select(ms.getIds().get(0));
    }

    @FXML
    private void Id_Modififé(ActionEvent event) {
        
        manuservice ms= new manuservice();
        if(ModifierTab.isSelected())
        {
            if(id_menu.getSelectionModel().getSelectedItem()!=null)
            {
                //System.out.println(id_menu.getSelectionModel().getSelectedItem()); 
                Menu m=ms.rechercher_menuId(id_menu.getSelectionModel().getSelectedItem());
                nom2.setText(m.getNom());
                description2.setText(m.getDescription());
                File f= new File(staticvar.Image_URL+m.getImage());
                Image im =new Image(f.toURI().toString());
                imageP2.setImage(im);
                imageP2.setFitHeight(100);
                imageP2.setFitWidth(100);
                if(m.getJour_de_la_semaine().contains("1"))
                {
                    dimanche2.setSelected(true);
                }
                else
                {
                    dimanche2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("2"))
                {
                    lundi2.setSelected(true);
                }
                else
                {
                    lundi2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("3"))
                {
                    mardi2.setSelected(true);
                }
                else
                {
                    mardi2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("4"))
                {
                    mercredi2.setSelected(true);
                }
                else
                {
                    mercredi2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("5"))
                {
                    jeudi2.setSelected(true);
                }
                else
                {
                    jeudi2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("6"))
                {
                    vendredi2.setSelected(true);
                }
                else
                {
                    vendredi2.setSelected(false);
                }
                if(m.getJour_de_la_semaine().contains("7"))
                {
                    samedi2.setSelected(true);
                }
                else
                {
                    samedi2.setSelected(false);
                }
                chemin2=m.getImage();
                

               
            }
        }
        
        
        
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        manuservice ms=new manuservice();
        //Menu m =new Menu(id_menu.getSelectionModel().getSelectedItem(), nom2.getText(), chemin,description2.getText() jours);
       /* if(dimanche2.isSelected())
        {
            jours2+="1";
        }*/
        if(lundi2.isSelected())
        {
            jours2+="2";
        }
        if(mardi2.isSelected())
        {
            jours2+="3";
        }
        if(mercredi2.isSelected())
        {
            jours2+="4";
        }
        if(jeudi2.isSelected())
        {
            jours2+="5";
        }
        if(vendredi2.isSelected())
        {
            jours2+="6";
        }
        if(samedi2.isSelected())
        {
            jours2+="7";
        }
        System.out.println(jours2);
        System.out.println(chemin2);
        Menu m =new Menu(id_menu.getSelectionModel().getSelectedItem().intValue(),nom2.getText(), chemin2, description2.getText(), jours2);
        ms.update(m);
        jours2="";
        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
        selectionModel.select(1);
        //tableView.getSelectionModel().getSelectedItem()
    }

    @FXML
    private void supprimer(ActionEvent event) {
        manuservice ms=new manuservice();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            ms.delete(tableView.getSelectionModel().getSelectedItem().getId());
            if(Afficher.isSelected())
            {
                if(tableView.getItems().size()!=0)
                {
                    tableView.getColumns().remove(0, 4);
                }
                tableView.getItems().clear();
                TableColumn<MenuAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,ImageView>("i"));
                TableColumn<MenuAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(200);
                NomColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("nom"));
                TableColumn<MenuAffichage,String> DescriptionColumn=new TableColumn<>("Description");
                DescriptionColumn.setMinWidth(250);
                DescriptionColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("description"));
                TableColumn<MenuAffichage,String> JoursColumn=new TableColumn<>("Jours");
                JoursColumn.setMinWidth(300);
                JoursColumn.setCellValueFactory(new PropertyValueFactory<MenuAffichage,String>("jours"));
                tableView.setItems(getMenus());
                tableView.getColumns().addAll(imagesColumn,NomColumn,DescriptionColumn,JoursColumn);
            }
        }
        
        //System.out.println(tableView.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void Statistiques(Event event) {
        /*PieDataset dataset=createDataSet();
        JFreeChart chart=createChart(dataset,"Title");
        ChartPanel chartPanel=new ChartPanel(chart);*/
        manuservice ms=new manuservice();
        
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
        menu_commandeservice msc=new menu_commandeservice();
        int total=msc.getTotal();
        for(Integer i:ms.getIds())
        {
            int val=msc.getStat(i);
            System.out.println("i:"+i);
            System.out.println("val:"+val);
            int stat=val*100/total;
            System.out.println(stat);
            if(stat>0)
                pieChartData.add(new PieChart.Data(i.toString(),stat));
        }
        pieChart.setData(pieChartData);
        
        
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
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAjouterUnMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    
}

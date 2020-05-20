/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Babysitting;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBabysitting;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author HEDI MSELMI
 */
public class afficheBabysitting extends SideMenuBaseForm  {
   EncodedImage enc;
    Image img;
    User u;
     String url="http://localhost/PidevF2/pidev/web/Images/";
    Image profilePic ;
     TextField tx_typedem;
    TextField tx_descriptiondem;
    TextField tx_maildem;
    Picker s;
    Button bt_supprimer;
    Button bt_modifier;
   // User u;
   // User u;
    Resources res;
    Button bt_ajout,bt_affiche,bt_chercher;
    TableLayout tl;
         int spanButton = 2;
    
   
     public afficheBabysitting(Resources res,User u)  {
         super(BoxLayout.y());
        this.u=u;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
            
         img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic = img;
        //Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
              //  add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("Affiche Babysitter", "WelcomeBlue")
                                
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
   
        ServiceBabysitting serviceDemande =new ServiceBabysitting();
        for (int i = 0; i < serviceDemande.getListReclamations().size(); ++i)  {
  int iddem = serviceDemande.getListReclamations().get(i).getIdBabysitter();
  int heuredebut = serviceDemande.getListReclamations().get(i).getHeureDebut();
  int heurefin = serviceDemande.getListReclamations().get(i).getHeureFin();
  int prixheure = (int) serviceDemande.getListReclamations().get(i).getPrixHeure();
     String  jour = serviceDemande.getListReclamations().get(i).getJourSemaine();
            
  
            Label ll =new Label("Date: ");
ll.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(ll);
      // AfficheDemande.add(new Label( formatter.format(datedem)));
 
            Label l =new Label("Type: ");
l.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(l);

        //AfficheDemande.add(new Label(typedem));
    
       
               Label l112 =new Label("Description: ");
l112.getAllStyles().setFgColor(0x0E36D7);
Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
          Label l_id=new Label("identifiant");
          Label l_heured=new Label("heure debut");
          Label l_heuref=new Label("heure fin");
          Label l_jour=new Label("jour de semaine");
        //  AfficheBabysitting.add("test");
         l_id.getAllStyles().setFgColor(0xFF0000);
          C2.add(l_id);
          if(iddem==15)
              
          {  Label n=new Label("NaimE2");
             n.getAllStyles().setFgColor(0x000000);
              C2.add(n); }
          else{
              Label n1=new Label("NaimP5");
             n1.getAllStyles().setFgColor(0x000000);
              C2.add(n1);
              
          }
   l_heured.getAllStyles().setFgColor(0xFF0000);
 C2.add(l_heured);
             String t = Integer.toString(heuredebut);
             Label k=new Label(t);
             k.getAllStyles().setFgColor(0x000000);
C2.add(k);
 l_heuref.getAllStyles().setFgColor(0xFF0000);
 C2.add(l_heuref);
  String tt = Integer.toString(heurefin);
             Label kk=new Label(tt);
             kk.getAllStyles().setFgColor(0x000000);
C2.add(kk);
 l_jour.getAllStyles().setFgColor(0xFF0000);
C2.add(l_jour);
Label jj=new Label(jour);
jj.getAllStyles().setFgColor(0x000000);
C2.add(jj);
add(C2);
//show();
       // AfficheDemande.add(new Label(descriptiondem));
        
 
             
         Label l3 =new Label("Etat: ");
l3.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(l3);
//Label l1113 =new Label("_______________");
//l1113.getAllStyles().setFgColor(0xEAEAEA);
       // AfficheDemande.add(new Label(etatdem));
        //AfficheDemande.add(l1113);
  
         
         bt_supprimer=new Button("Supprimer");
        
         bt_supprimer.addActionListener((e)->{
            ServiceBabysitting ser = new ServiceBabysitting();
           // Reclamation t = new Reclamation();
            ser.SupprimerBabysitting(iddem);
             Dialog.show("Success","suppression accepted",new Command("OK"));
         });
         add(bt_supprimer);
         
         
         bt_modifier=new Button("modifier");
      
  bt_modifier.addActionListener(new ActionListener() {
       User u;
      EncodedImage enc;
    Image img;
    Resources res;
            @Override
            public void actionPerformed(ActionEvent evt) {
                 //modifierBabysitter = new Form(" Demande "+ iddem);
               Form aff= new Form(" Modifier babysitter "+ iddem);
               Button bt_ajout;
                this.u=u;
              
               
                Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        
        Button retour = new Button("retour");
     //   menuButton.setUIID("Title");
        //FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> new afficheBabysitting(res,this.u).show());
           
        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        
            
      Container c1 = new Container(BoxLayout.y());
        Label l_heuredebut= new Label ("heure debut :");
        l_heuredebut.getAllStyles().setFgColor(0x000000);
         Label l_heurefin= new Label ("heure fin :");
         l_heurefin.getAllStyles().setFgColor(0x000000);
         Label l_joursemaine= new Label ("jour de  semaine :");
         l_joursemaine.getAllStyles().setFgColor(0x000000);
         Label l_prixheure= new Label ("prix heure :");
         l_prixheure.getAllStyles().setFgColor(0x000000);
       TextField tx_prixheure = new TextField("","Prix:",70, TextArea.ANY);
       tx_prixheure.getAllStyles().setFgColor(0x4169E1);
        //  c1.add(t);
             Picker timePicker = new Picker();
             timePicker.getAllStyles().setFgColor(0x4169E1);
       timePicker.setType(Display.PICKER_TYPE_TIME);
       Picker timePicker1 = new Picker();
       timePicker1.getAllStyles().setFgColor(0x4169E1);
       timePicker1.setType(Display.PICKER_TYPE_TIME);
          c1.add(l_heuredebut);
             
          c1.add(timePicker);
          c1.add(l_heurefin);
              
         c1.add(timePicker1);
      // setupSideMenu(res,this.u); 
      //  modifierBabysitter.add(c1);
        Container c2 = new Container(BoxLayout.x());
        CheckBox lundi= new CheckBox("lundi");
        lundi.getAllStyles().setFgColor(0x4169E1);
        CheckBox mardi= new CheckBox("mardi");
        mardi.getAllStyles().setFgColor(0x4169E1);
        CheckBox mercredi= new CheckBox("mercredi");
        mercredi.getAllStyles().setFgColor(0x4169E1);
        CheckBox jeudi= new CheckBox("jeudi");
        jeudi.getAllStyles().setFgColor(0x4169E1);
        CheckBox vendredi= new CheckBox("vendredi");
        vendredi.getAllStyles().setFgColor(0x4169E1);
        Container s = new Container(BoxLayout.y());
        s.add(vendredi);
        c2.add(lundi);
        c2.add(mardi);
        c2.add(mercredi);
        c2.add(jeudi);
         aff.add(l_joursemaine);
        aff.add(c2);
        aff.add(s);
       c1.add(l_prixheure);
        c1.add(tx_prixheure);
        bt_ajout = new Button("modifier");
        bt_ajout.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent evt) {
                      if ((tx_prixheure.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   
        
                      String s="";
        if(lundi.isSelected())
                { s+="lundi";}
        if(mardi.isSelected())
        {s+=" mardi";}
        if(mercredi.isSelected())
        {s+=" mercredi";}
        if(jeudi.isSelected())
        {s+=" jeudi";}
        if(vendredi.isSelected())
        {s+=" vendredi";}
                         ServiceBabysitting ser = new ServiceBabysitting();
           // Reclamation t = new Reclamation();
          
            ser.SupprimerBabysitting(iddem);
        Babysitting  t = new Babysitting(iddem, (int) timePicker.getValue(), (int) timePicker1.getValue(),s,Integer.parseInt(tx_prixheure.getText()),new Enfant(5));
                         ServiceBabysitting.getInstance().AjoutBabysitter(t,iddem);
                         Dialog.show("Success","modification accepted",new Command("OK"));
                         
                    
        
       
       
      
                
                
                
            }
                   }
        });
                 aff.getToolbar().addCommandToLeftBar("back", null, e -> showBack());              
        retour.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent evt) {
                     
                     ProfileForm s=new ProfileForm(res, u);
                     s.show();
                   }
        });
                  
        
          aff.add(c1);
          aff.add(bt_ajout);
           
              aff.show();
        }

           
                          }); 
      add(bt_modifier);
                
              //Label l11 =new Label("---------------------------------------------------------------");
              Label l11 =new Label("__________________________________________________________________");
l11.getAllStyles().setFgColor(0xC0C0C0);
//AfficheDemande.add(l11);
       
        
        
              
        
        
        }
        setupSideMenu(res,this.u);
    
    
}

   

    public Button getBt_supprimer() {
        return bt_supprimer;
    }

    public void setBt_supprimer(Button bt_supprimer) {
        this.bt_supprimer = bt_supprimer;
    }

    public Button getBt_modifier() {
        return bt_modifier;
    }

    public void setBt_modifier(Button bt_modifier) {
        this.bt_modifier = bt_modifier;
    }

    @Override
    protected void showOtherForm(Resources res) {
      //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

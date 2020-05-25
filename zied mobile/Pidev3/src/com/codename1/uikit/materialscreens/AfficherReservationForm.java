/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.EnfantComboBox;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.MenuCommande;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServiceEnfant;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ben younes
 */
public class AfficherReservationForm extends SideMenuBaseForm{
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
    Image profilePic ;
    String jour;
    Container Lundi;
    Container Mardi;
    Container Mercredi;
    Container Jeudi;
    Container Vendredi;
    Container Samedi;
    Boolean OkL;
    Boolean OkMa;
    Boolean OkMe;
    Boolean OkJ;
    Boolean OkV;
    Boolean OkS;
    public AfficherReservationForm(Resources res,User u)
    {
        super(BoxLayout.y());
        Lundi=new Container(BoxLayout.x());
        Mardi=new Container(BoxLayout.x());
        Mercredi=new Container(BoxLayout.x());
        Jeudi=new Container(BoxLayout.x());
        Vendredi=new Container(BoxLayout.x());
        Samedi=new Container(BoxLayout.x());
        this.u=u;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic= img;
        //Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label(" "+u.getPrenom()+" ", "WelcomeBlue"),
                                new Label(u.getNom(), "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(/*BorderLayout.NORTH,*/ separator);
        ArrayList<Enfant> enfantsAll= ServiceEnfant.getInstance().getEnfantsPourParent(u.getIdUser());
        ArrayList<Enfant> enfants=new ArrayList<>();
        for (Enfant e:enfantsAll)
        {
            if(e.getCantine()==1)
            {
                enfants.add(e);
            }
        }
        ComboBox<EnfantComboBox> CBenfants=new ComboBox<>();
        for(Enfant e:enfants)
        {
            EnfantComboBox ecb=new EnfantComboBox(e);
            CBenfants.addItem(ecb);
        }
        Container Reservations=new Container(BoxLayout.y());
        add(CBenfants);
        System.out.println("ok");
        Enfant e1=CBenfants.getSelectedItem().getE();
        System.out.println("e1:"+e1);
        Label Llundi=new Label("Lundi");
        Label Lmardi=new Label("Mardi");
        Label Lmercredi=new Label("Mercredi");
        Label Ljeudi=new Label("Jeudi");
        Label Lvendredi=new Label("Vendredi");
        Label Lsamedi=new Label("Samedi");
        Container CMLundi=new Container(new FlowLayout(CENTER,TOP));
        Container CMMardi=new Container(new FlowLayout(CENTER,TOP));
        Container CMMercredi=new Container(new FlowLayout(CENTER,TOP));
        Container CMJeudi=new Container(new FlowLayout(CENTER,TOP));
        Container CMVendredi=new Container(new FlowLayout(CENTER,TOP));
        Container CMSamedi=new Container(new FlowLayout(CENTER,TOP));
        
        Container CLLundi=new Container(new FlowLayout(CENTER,TOP));
        Container CLMardi=new Container(new FlowLayout(CENTER,TOP));
        Container CLMercredi=new Container(new FlowLayout(CENTER,TOP));
        Container CLJeudi=new Container(new FlowLayout(CENTER,TOP));
        Container CLVendredi=new Container(new FlowLayout(CENTER,TOP));
        Container CLSamedi=new Container(new FlowLayout(CENTER,TOP));
        
        Container CLundi=new Container(BoxLayout.y());
        Container CMardi=new Container(BoxLayout.y());
        Container CMercredi=new Container(BoxLayout.y());
        Container CJeudi=new Container(BoxLayout.y());
        Container CVendredi=new Container(BoxLayout.y());
        Container CSamedi=new Container(BoxLayout.y());
        OkL=false;
        OkMa=false;
        OkMe=false;
        OkJ=false;
        OkV=false;
        OkS=false;
        ArrayList<MenuCommande> commandes1=ServiceCommande.getInstance().getCommandesDenfant(e1.getId_enfant());
        for(MenuCommande mc:commandes1)
        {
            System.out.println("idEnfant: "+mc.getIdEnfant()+" IdMenu:"+mc.getIdMenu().getId()+" JourDeLaSemaine:"+mc.getJourDeLaSemaine());
            
            if(mc.getJourDeLaSemaine().contains("2"))
            {
                OkL=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Lundi.add(imgv1);
                Lundi.add(Text1);
            }
            
            if(mc.getJourDeLaSemaine().contains("3"))
            {
                OkMa=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Mardi.add(imgv1);
                Mardi.add(Text1);
            }
            
            if(mc.getJourDeLaSemaine().contains("4"))
            {
                OkMe=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Mercredi.add(imgv1);
                Mercredi.add(Text1);
            }
            
            if(mc.getJourDeLaSemaine().contains("5"))
            {
                OkJ=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Jeudi.add(imgv1);
                Jeudi.add(Text1);
            }
            
            if(mc.getJourDeLaSemaine().contains("6"))
            {
                OkV=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Vendredi.add(imgv1);
                Vendredi.add(Text1);
            }
            
            if(mc.getJourDeLaSemaine().contains("7"))
            {
                OkS=true;
                Container Text1=new Container(BoxLayout.y());
                ImageViewer imgv1=new ImageViewer();
                url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                imgv1.setImage(img);
                Label nom1=new Label(mc.getIdMenu().getNom());
                Label description1=new Label(mc.getIdMenu().getDescription());
                Text1.add(nom1);
                Text1.add(description1);
                Samedi.add(imgv1);
                Samedi.add(Text1);
            }
            
        }
        if(!OkL)
        {
            Lundi.add(new Label("Pas de menu reservé!"));
        }
        if(!OkMa)
        {
            Mardi.add(new Label("Pas de menu reservé!"));
        }
        if(!OkMe)
        {
            Mercredi.add(new Label("Pas de menu reservé!"));
        }
        if(!OkJ)
        {
            Jeudi.add(new Label("Pas de menu reservé!"));
        }
        if(!OkV)
        {
            Vendredi.add(new Label("Pas de menu reservé!"));
        }
        if(!OkS)
        {
            Samedi.add(new Label("Pas de menu reservé!"));
        }

        
        CBenfants.addActionListener((evt) -> {
            Lundi.removeAll();
            Mardi.removeAll();
            Mercredi.removeAll();
            Jeudi.removeAll();
            Vendredi.removeAll();
            Samedi.removeAll();
            OkL=false;
            OkMa=false;
            OkMe=false;
            OkJ=false;
            OkV=false;
            OkS=false;
            Enfant e=CBenfants.getSelectedItem().getE();
            ArrayList<MenuCommande> commandes=ServiceCommande.getInstance().getCommandesDenfant(e.getId_enfant());
            for(MenuCommande mc:commandes)
            {
                if(mc.getJourDeLaSemaine().contains("2"))
                {
                    OkL=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Lundi.add(imgv1);
                    Lundi.add(Text1);
                }

                if(mc.getJourDeLaSemaine().contains("3"))
                {
                    OkMa=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Mardi.add(imgv1);
                    Mardi.add(Text1);
                }

                if(mc.getJourDeLaSemaine().contains("4"))
                {
                    OkMe=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Mercredi.add(imgv1);
                    Mercredi.add(Text1);
                }

                if(mc.getJourDeLaSemaine().contains("5"))
                {
                    OkJ=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Jeudi.add(imgv1);
                    Jeudi.add(Text1);
                }

                if(mc.getJourDeLaSemaine().contains("6"))
                {
                    OkV=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Vendredi.add(imgv1);
                    Vendredi.add(Text1);
                }

                if(mc.getJourDeLaSemaine().contains("7"))
                {
                    OkS=true;
                    Container Text1=new Container(BoxLayout.y());
                    ImageViewer imgv1=new ImageViewer();
                    url="http://localhost/PidevF2/pidev/web/Images/"+mc.getIdMenu().getImage();
                    img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv1.setImage(img);
                    Label nom1=new Label(mc.getIdMenu().getNom());
                    Label description1=new Label(mc.getIdMenu().getDescription());
                    Text1.add(nom1);
                    Text1.add(description1);
                    Samedi.add(imgv1);
                    Samedi.add(Text1);
                }
            }
            if(!OkL)
            {
                Lundi.add(new Label("Pas de menu reservé!"));
            }
            if(!OkMa)
            {
                Mardi.add(new Label("Pas de menu reservé!"));
            }
            if(!OkMe)
            {
                Mercredi.add(new Label("Pas de menu reservé!"));
            }
            if(!OkJ)
            {
                Jeudi.add(new Label("Pas de menu reservé!"));
            }
            if(!OkV)
            {
                Vendredi.add(new Label("Pas de menu reservé!"));
            }
            if(!OkS)
            {
                Samedi.add(new Label("Pas de menu reservé!"));
            }
            refreshTheme();
        });
        CLLundi.add(Llundi);
        CLundi.add(CLLundi);
        CLundi.add(Lundi);
        CMLundi.add(CLundi);
        
        CLMardi.add(Lmardi);
        CMardi.add(CLMardi);
        CMardi.add(Mardi);
        CMMardi.add(CMardi);
        
        CLMercredi.add(Lmercredi);
        CMercredi.add(CLMercredi);
        CMercredi.add(Mercredi);
        CMMercredi.add(CMercredi);
        
        CLJeudi.add(Ljeudi);
        CJeudi.add(CLJeudi);
        CJeudi.add(Jeudi);
        CMJeudi.add(CJeudi);
        
        CLVendredi.add(Lvendredi);
        CVendredi.add(CLVendredi);
        CVendredi.add(Vendredi);
        CMVendredi.add(CVendredi);
        
        CLSamedi.add(Lsamedi);
        CSamedi.add(CLSamedi);
        CSamedi.add(Samedi);
        CMSamedi.add(CSamedi);
        
        Reservations.add(CMLundi);
        //Reservations.add(Lundi);
        Reservations.add(CMMardi);
        //Reservations.add(Mardi);
        Reservations.add(CMMercredi);
        //Reservations.add(Mercredi);
        Reservations.add(CMJeudi);
        //Reservations.add(Jeudi);
        Reservations.add(CMVendredi);
        //Reservations.add(Vendredi);
        Reservations.add(CMSamedi);
        //Reservations.add(Samedi);
        add(Reservations);
        setupSideMenu(res,u);
    }
    @Override
    protected void showOtherForm(Resources res) {
        //new ProfileForm(res).show();
    }
    
}

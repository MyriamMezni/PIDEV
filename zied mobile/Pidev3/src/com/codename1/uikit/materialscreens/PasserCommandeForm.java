/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.mycompany.myapp.entities.EnfantComboBox;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceEnfant;
import com.mycompany.myapp.services.ServiceMenu;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ben younes
 */
public class PasserCommandeForm extends SideMenuBaseForm{
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
    Image profilePic ;
    String jour;
    ImageViewer imgv;
    Menu m;
    Enfant ef;
    String jourS;
    public PasserCommandeForm(Resources res,User u,Menu m,String jour)
    {
        super(new BorderLayout());
        this.u=u;
        this.m=m;
        this.jour=jour;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
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
        add(BorderLayout.NORTH, separator);
        Container content=new Container(BoxLayout.y());
        Container Day=new Container(new FlowLayout(CENTER, TOP));
        jourS=null;
        if(jour.equals("2"))
        {
            jourS="Lundi";
        }
        
        if(jour.equals("3"))
        {
            jourS="Mardi";
        }
        
        if(jour.equals("4"))
        {
            jourS="Mercredi";
        }
        
        if(jour.equals("5"))
        {
            jourS="Jeudi";
        }
        
        if(jour.equals("6"))
        {
            jourS="Vendredi";
        }
        
        if(jour.equals("7"))
        {
            jourS="Samedi";
        }
        Label Lday=new Label(jourS);
        Day.add(Lday);
        content.add(CBenfants);
        content.add(Day);
        imgv=new ImageViewer();
        String urlm="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
        Image Imenu=URLImage.createToStorage(enc, urlm, urlm, URLImage.RESIZE_SCALE);
        Container root=new Container(new FlowLayout(CENTER,CENTER));
        Container Cmenu=new Container(BoxLayout.x());
        Container CText=new Container(new FlowLayout(LEFT,CENTER));
        Container CCText=new Container(BoxLayout.y());
        Label Nmenu=new Label("Nom: "+m.getNom());
        Label Dmenu=new Label("Description: "+m.getDescription());
        CCText.add(Nmenu);
        CCText.add(Dmenu);
        CText.add(CCText);
        Cmenu.add(Imenu);
        Cmenu.add(CText);
        root.add(Cmenu);
        content.add(root);
        Button reserver=new Button("Reserver");
        reserver.setUIID("Reserver");
        content.add(reserver);
        reserver.addActionListener((evt) -> {
           ef=CBenfants.getSelectedItem().getE();
           if(Dialog.show("Confirmation", "Etes-vous sur de reserver le menu "+m.getNom()+" a "+ef.getPrenom()+" "+ef.getNom()+" pour le " +jourS.toLowerCase()+"?", "Oui", "Non"))
           {
                if(ServiceMenu.getInstance().reserver(ef.getId_enfant(),m.getId(), jour))
                {
                    Dialog.show("Succès!", "Menu reservé!","ok",null);
                }
                else
                {
                     Dialog.show("Erreur!", "Une erreure s'est produite!","ok",null);
                }
           }
            
        });
        add(BorderLayout.NORTH,content);
        setupSideMenu(res,u);
        
    }
    @Override
    protected void showOtherForm(Resources res) {
        //new ProfileForm(res).show();
    }
    
}

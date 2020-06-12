/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceMenu;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ben younes
 */
public class AllMenusForm extends SideMenuBaseForm{
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
    Image profilePic ;
    public AllMenusForm(Resources res,User u)
    {
        super(BoxLayout.y());
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
        Container Menus= new Container(BoxLayout.y());
        ArrayList<Menu> allMenus=ServiceMenu.getInstance().getAllMenus();
        
        ArrayList<Menu> Lmenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("2"))
            {
                Lmenus.add(m);
            }     
        }
        Container CLundi=new Container(BoxLayout.y());
        Container CTLundi=new Container(new FlowLayout(CENTER, TOP));
        Label Llundi=new Label("Lundi");
        CTLundi.add(Llundi);
        CLundi.add(CTLundi);
        if(Lmenus.size()!=0)
        { 
            for(Menu m:Lmenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(new FlowLayout(LEFT, CENTER));
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "2").show();
                });
                
                CLundi.add(Content);
                CLundi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CLundi.add(Empty);
        }
        Menus.add(CLundi);
        
        
        ArrayList<Menu> Mamenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("3"))
            {
                Mamenus.add(m);
            }     
        }
        Container CMardi=new Container(BoxLayout.y());
        Container CTMardi=new Container(new FlowLayout(CENTER, TOP));
        Label Lmardi=new Label("Mardi");
        CTMardi.add(Lmardi);
        CMardi.add(CTMardi);
        if(Mamenus.size()!=0)
        { 
            for(Menu m:Mamenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(BoxLayout.y());
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "3").show();
                });
                CMardi.add(Content);
                CMardi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CMardi.add(Empty);
        }
        Menus.add(CMardi);
        
        
        ArrayList<Menu> Memenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("4"))
            {
                Memenus.add(m);
            }     
        }
        Container CMercredi=new Container(BoxLayout.y());
        Container CTMercredi=new Container(new FlowLayout(CENTER, TOP));
        Label Lmercredi=new Label("Mercredi");
        CTMercredi.add(Lmercredi);
        CMercredi.add(CTMercredi);
        if(Memenus.size()!=0)
        { 
            for(Menu m:Memenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(new FlowLayout(LEFT, CENTER));
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "4").show();
                });
                CMercredi.add(Content);
                CMercredi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CMercredi.add(Empty);
        }
        Menus.add(CMercredi);
        
        ArrayList<Menu> Jmenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("5"))
            {
                Jmenus.add(m);
            }     
        }
        Container CJeudi=new Container(BoxLayout.y());
        Container CTJeudi=new Container(new FlowLayout(CENTER, TOP));
        Label Ljeudi=new Label("Jeudi");
        CTJeudi.add(Ljeudi);
        CJeudi.add(CTJeudi);
        if(Jmenus.size()!=0)
        { 
            for(Menu m:Jmenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(new FlowLayout(LEFT, CENTER));
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "5").show();
                });
                CJeudi.add(Content);
                CJeudi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CJeudi.add(Empty);
        }
        Menus.add(CJeudi);
        
        ArrayList<Menu> Vmenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("6"))
            {
                Vmenus.add(m);
            }     
        }
        Container CVendredi=new Container(BoxLayout.y());
        Container CTVendredi=new Container(new FlowLayout(CENTER, TOP));
        Label Lvendredi=new Label("Vendredi");
        CTVendredi.add(Lvendredi);
        CVendredi.add(CTVendredi);
        if(Vmenus.size()!=0)
        { 
            for(Menu m:Vmenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(new FlowLayout(LEFT, CENTER));
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "6").show();
                });
                CVendredi.add(Content);
                CVendredi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CVendredi.add(Empty);
        }
        Menus.add(CVendredi);
        
        ArrayList<Menu> Smenus=new ArrayList<>();
        for (Menu m:allMenus)
        {
            if(m.getJour_de_la_semaine().contains("7"))
            {
                Smenus.add(m);
            }     
        }
        Container CSamedi=new Container(BoxLayout.y());
        Container CTSamedi=new Container(new FlowLayout(CENTER, TOP));
        Label Lsamedi=new Label("Samedi");
        CTSamedi.add(Lsamedi);
        CSamedi.add(CTSamedi);
        if(Smenus.size()!=0)
        { 
            for(Menu m:Smenus)
            {
                Container Content=new Container(BoxLayout.x());
                Container CMiddleText=new Container(new FlowLayout(LEFT, CENTER));
                Container CCText=new Container(BoxLayout.y());
                ImageViewer imgv= new ImageViewer();
                String urlMenu="http://localhost/PidevF2/pidev/web/Images/"+m.getImage();
                Image Imenu=URLImage.createToStorage(enc, urlMenu, urlMenu, URLImage.RESIZE_SCALE);
                imgv.setImage(Imenu);
                Label Nmenu=new Label("Nom: "+m.getNom());
                Label Dmenu=new Label("Description: "+m.getDescription());
                CCText.add(Nmenu);
                CCText.add(Dmenu);
                CMiddleText.add(CCText);
                Content.add(imgv);
                Content.add(CMiddleText);
                Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
                reserver.addActionListener((evt) -> {
                    new PasserCommandeForm(res, u, m, "7").show();
                });
                CSamedi.add(Content);
                CSamedi.add(reserver);
            }
        }
        else
        {
            Container Empty=new Container(new FlowLayout(CENTER));
            Empty.add(new Label("Pas de menu planifié!"));
            CSamedi.add(Empty);
        }
        Menus.add(CSamedi);
        Button stat=new Button("Statistiques");
        stat.setUIID("Stat");
        stat.addActionListener((evt) -> {
           new BudgetPieChart().execute(res,this.u).show(); 
        });
        Menus.add(stat);
        
        add(Menus);
        setupSideMenu(res,this.u);
    }
    @Override
    protected void showOtherForm(Resources res) {
        //new ProfileForm(res).show();
    }
    
}

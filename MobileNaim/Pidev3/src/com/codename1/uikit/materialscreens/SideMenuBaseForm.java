/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
         Form current;
           public Resources theme;

    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    User u;
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res,User u) {
        current = this; 
                        theme = UIManager.initFirstTheme("/theme");

        this.u=Statics.CONNECTED_USER;
        url=url+u.getImage();
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        Image profilePic = img;
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(u.getUsername(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD,  e ->new ProfileForm(res, this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Envoyer un E-mail", FontImage.MATERIAL_MAIL,  e ->new SendMailForm(res, this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Menus", FontImage.MATERIAL_RESTAURANT_MENU,  e -> new AllMenusForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Reservations", FontImage.MATERIAL_CHECK_BOX,  e -> new AfficherReservationForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Reservations", FontImage.MATERIAL_CHECK_BOX,  e -> new AfficherReservationForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Ajout Enfant", FontImage.MATERIAL_CHECK_BOX,  e -> new AjouterEnfant(res,this.u).show());
        Form AjouterTask = new Form("Ajouter une demande", BoxLayout.y());
       getToolbar().addMaterialCommandToSideMenu("  Affiche Enfant", FontImage.MATERIAL_CHECK_BOX,  e -> {
            try {
                new ListTasksForm(res, this.u).show();
              //  new testina(res, this.u).show();
            } catch (IOException ex) {
               
            }
        });
       getToolbar().addMaterialCommandToSideMenu("  Ajout Babysitter", FontImage.MATERIAL_CHECK_BOX,  e -> new AjoutBabysitter(res, this.u).show());
       getToolbar().addMaterialCommandToSideMenu("  Affiche Babysitter", FontImage.MATERIAL_CHECK_BOX,  e -> new afficheBabysitting(res,this.u).show());
      getToolbar().addMaterialCommandToSideMenu("  Litse des activités",  FontImage.MATERIAL_SETTINGS, e -> new ListActiviteForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Rating Activite", FontImage.MATERIAL_ACCESS_TIME,  e -> new AddRatingForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu("  Statistiques des Activite",FontImage.MATERIAL_TRENDING_UP, e-> new StatsForm(res,this.u).show());
        getToolbar().addMaterialCommandToSideMenu(" Evenements",FontImage.MATERIAL_TRENDING_UP, e-> new ListEvents(res,this.u).show());
         
       getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
        
    }
    
    protected abstract void showOtherForm(Resources res);
}

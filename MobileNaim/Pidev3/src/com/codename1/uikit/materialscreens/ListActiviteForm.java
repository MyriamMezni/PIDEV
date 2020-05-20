/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ActiviteService;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ListActiviteForm extends SideMenuBaseForm{
    Form current;
    Form next;
     String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
    Image profilePic ;
      
     public ListActiviteForm(Resources res,User u) {
         this.u=u;
        url=url+u.getImage();
                        setupSideMenu(res,this.u);
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
        
        setTitle("Liste des activités");
         Button btnPlus = new Button("Plus d'information?");
         add(btnPlus);
     current = this;     
       
             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_SPORTS_GOLF, "Label", 3);

         for (int i=0; i<ActiviteService.getInstance().getAllTasks().size()-1; i+=3) 
{ 
    
        
        addButtonBottom(arrowDown,ActiviteService.getInstance().getAllTasks().get(i).toString(), 0xd997f1, true);
        addButtonBottom(arrowDown,ActiviteService.getInstance().getAllTasks().get(i+1).toString(), 0x5ae29d, false);
        addButtonBottom(arrowDown,ActiviteService.getInstance().getAllTasks().get(i+2).toString(), 0x4dc2ff, false);

}
                setupSideMenu(res,this.u);

         //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new HomeForm().show());
    
        
     }
    
    

  private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
    }

}

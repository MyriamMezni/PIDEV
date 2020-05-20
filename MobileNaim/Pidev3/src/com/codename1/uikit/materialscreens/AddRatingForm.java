    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.ratingactivite;
import com.mycompany.myapp.services.ActiviteService;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class AddRatingForm extends SideMenuBaseForm{
 Form current;
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
        Image profilePic ;

    public AddRatingForm(Resources res,User u) {
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
        
        current = this;  
        setTitle("rating");
       setLayout(BoxLayout.y());
         Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
     derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
       
       
        TextField TF=new TextField("intitule");
        Button btnValider = new Button("Noter");
         ComboBox CB= new ComboBox();
         CB.addItem("Anglais");
         CB.addItem("Math");
         CB.addItem("Coloriage");
         CB.addItem("Dessin");
         CB.addItem("Sport");

  Label l1=new Label("Choisir une activité : ");
         add(l1);
        Container C=new Container(new FlowLayout(CENTER, TOP));
C.add(CB);
         current.add(C);
             current.add(FlowLayout.encloseCenter(starRank));
 ImageViewer img = new ImageViewer(theme.getImage("kids.png"));
     
       // current.add(TF);
   current.add(btnValider);
   current.add(img);
    btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                 try {
                     
                       
                        ratingactivite t = new ratingactivite(CB.getSelectedItem().toString(),starRank.getProgress());
                        if( ActiviteService.getInstance().addRate(t))
                            Dialog.show("Success","Merci d avoir noté cette activité ",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "erreur", new Command("OK"));
                    }
                    
                
                
                
            }
        });
                setupSideMenu(res,this.u);

     //      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new HomeForm().show());

    }
 
      private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

    @Override
    protected void showOtherForm(Resources res) {
    }

}

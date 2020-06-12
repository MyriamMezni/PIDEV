/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Menu;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.staticvar;

/**
 *
 * @author ben younes
 */
public class MenuAffichage {
    private ImageView i;
    String nom;
    String description;
    String jours="";
    int id;

    public MenuAffichage() {
        i=new ImageView();
    }
    
    

    public ImageView getI() {
        return i;
    }

    public void setI(ImageView i) {
        this.i = i;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }
    
    public void tranformer(Menu m)
    {
        
        this.id=m.getId();
        if (m.getImage()==null) {
            m.setImage("");
        }
        File f= new File(staticvar.Image_URL+m.getImage());
        Image im =new Image(f.toURI().toString());
        i.setImage(im);
        i.setFitHeight(100);
        i.setFitWidth(100);
        
        
        nom=m.getNom();
        description=m.getDescription();
        /*if(m.getJour_de_la_semaine().contains("1"))
        {
            jours+="Dimanche";
        }*/
        if(m.getJour_de_la_semaine().contains("2"))
        {
            jours+=" Lundi";
        }
        if(m.getJour_de_la_semaine().contains("3"))
        {
            jours+=" Mardi";
        }
        if(m.getJour_de_la_semaine().contains("4"))
        {
            jours+=" Mercredi";
        }
        if(m.getJour_de_la_semaine().contains("5"))
        {
            jours+=" Jeudi";
        }
        if(m.getJour_de_la_semaine().contains("6"))
        {
            jours+=" Vendredi";
        }
        if(m.getJour_de_la_semaine().contains("7"))
        {
            jours+=" Samedi";
        }
        

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}

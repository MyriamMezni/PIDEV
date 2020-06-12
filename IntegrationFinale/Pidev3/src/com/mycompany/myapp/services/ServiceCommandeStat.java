/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.CommandeStat;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ben younes
 */
public class ServiceCommandeStat {
    
    public ArrayList<CommandeStat> commandes;
    
    public static ServiceCommandeStat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ServiceCommandeStat()
    {
        req = new ConnectionRequest();
    }
    public static ServiceCommandeStat getInstance() {
        if (instance == null) {
            instance = new ServiceCommandeStat();
        }
        return instance;
    }
    public ArrayList<CommandeStat> parseCommandes(String jsonText){
        try {
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CommandeStat c = new CommandeStat();
                Menu m=new Menu();
                float idMenu = Float.parseFloat(obj.get("id").toString());
                m.setId((int)idMenu);
                String nom=obj.get("nom").toString();
                m.setNom(nom);
                String image=obj.get("image").toString();
                m.setImage(image);
                String description=obj.get("description").toString();
                m.setDescription(description);
                /*String jour_de_la_semaine=obj.get("jour_de_la_semaine_M").toString();
                m.setJour_de_la_semaine(jour_de_la_semaine);*/
                c.setM(m);
                float nbr = Float.parseFloat(obj.get("nbr").toString());
                c.setNbr((int)nbr);

                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                if(c.getNbr()>0)
                    commandes.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return commandes;
    }
    
    public ArrayList<CommandeStat> getStat()
    {
        String url = Statics.BASE_URL+"parsing/StatCommandes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }
    
}

<?php

namespace PidevBundle\Controller;

use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use PidevBundle\Entity\Chaise;
use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\Menu;
use PidevBundle\Entity\MenuCommande;
use PidevBundle\Entity\User;
use PidevBundle\Form\MenuType;
use PidevBundle\Form\ReserverType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Validator\Constraints\Choice;

class MenuController extends Controller
{
    public function AjouterMenuBackAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $menu=new Menu();
                $form=$this->createForm(MenuType::class,$menu);
                $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
                $em=$this->getDoctrine()->getManager();
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $menu->transferer();
                        $file=$form['image']->getData();
                        $extension = $file->guessExtension();
                        if (!$extension) {
                            // extension cannot be guessed
                            $extension = 'bin';
                        }
                        $name=rand(1, 99999).'.'.$extension;
                        $menu->setImage($name);
                        $file->move($directory, $name);
                        $em->persist($menu);
                        $em->flush();
                        return $this->redirectToRoute("AfficherMenusBack");
                    }
                }
                return $this->render("@Pidev/Menu/Back/AjouterMenu.html.twig",array("form"=>$form->createView()));

            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
    public function AfficherMenusBackAction()
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $rep=$this->getDoctrine()->getRepository(Menu::class);
                $menus=$rep->findAll();
                return $this->render("@Pidev/Menu/Back/AfficherMenus.html.twig",array("menus"=>$menus));
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }

    public function ModifierMenuBackAction(Request $request,$idMenu)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $repM=$this->getDoctrine()->getRepository(Menu::class);
                $menu=$repM->find($idMenu);
                $menu->transfererInverse();
                $menu->setImage(null);
                $form=$this->createForm(MenuType::class,$menu);
                $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
                $em=$this->getDoctrine()->getManager();
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $menu->transferer();
                        $file=$form['image']->getData();
                        $extension = $file->guessExtension();
                        if (!$extension) {
                            // extension cannot be guessed
                            $extension = 'bin';
                        }
                        $name=rand(1, 99999).'.'.$extension;
                        $menu->setImage($name);
                        $file->move($directory, $name);
                        $em->persist($menu);
                        $em->flush();
                        return $this->redirectToRoute("AfficherMenusBack");
                    }
                }
                return $this->render("@Pidev/Menu/Back/ModifierMenu.html.twig",array("form"=>$form->createView()));

            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
    public function SupprimerMenuBackAction($idMenu)
    {

        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $repMC=$this->getDoctrine()->getRepository(MenuCommande::class);
                $repM=$this->getDoctrine()->getRepository(Menu::class);
                $em=$this->getDoctrine()->getManager();
                $menu=$repM->find($idMenu);
                $menusC=$repMC->findByIdMenu($idMenu);
                foreach ($menusC as $c)
                {
                    $em->remove($c);
                }
                $em->remove($menu);
                $em->flush();
                return $this->redirectToRoute("AfficherMenusBack");
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }


    }

    public function AfficherMenusFrontAction()
    {
        $rep=$this->getDoctrine()->getRepository(Menu::class);
        $menus2=$rep->AfficherMenusParJour("2");
        $menus3=$rep->AfficherMenusParJour("3");
        $menus4=$rep->AfficherMenusParJour("4");
        $menus5=$rep->AfficherMenusParJour("5");
        $menus6=$rep->AfficherMenusParJour("6");
        $menus7=$rep->AfficherMenusParJour("7");
        return $this->render("@Pidev/Menu/Front/AfficherMenuTotal.html.twig",array(
            "menus2"=>$menus2,
            "menus3"=>$menus3,
            "menus4"=>$menus4,
            "menus5"=>$menus5,
            "menus6"=>$menus6,
            "menus7"=>$menus7));


    }
    public function ReserverAction(Request $request,$idmenu,$jour)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Parent")
            {
                $commande=new MenuCommande();
                $doctrine=$this->getDoctrine();
                $enfants=$doctrine->getRepository(Enfant::class)->findBy([
                    'idParent'=>$this->getUser(),
                    'cantine'=>true,
                ]);
                if(empty($enfants))
                {
                    return $this->redirectToRoute("AjouterACantine");
                }
                $enfants2=array();
                foreach ($enfants as $tmp)
                {
                    $enfants2[$tmp->getPrenom()." ".$tmp->getNom()]=$tmp->getIdEnfant();
                }
                $form=$this->createForm(ReserverType::class,$commande,array('enfants'=>$enfants2));
                $repC=$doctrine->getRepository(MenuCommande::class);
                $repM=$doctrine->getRepository(Menu::class);
                $menu=$repM->find($idmenu);
                $repe=$doctrine->getRepository(Enfant::class);
                $em=$doctrine->getManager();

                $form->handleRequest($request);
                if($form->isSubmitted() && $form->isValid())
                {
                    $e=$commande->getIdEnfant();
                    $commandes=$repC->findByIdEnfant($e);//verifier s'il ya des commande au nom de l'enfant selectionné
                    if(!empty($commandes))//il existe ds commande au nom de l'enfant
                    {
                        foreach ($commandes as $c)
                        {
                            $jourS=strval($jour);
                            if(strpos($c->getJourDeLaSemaine(),$jourS)!==false)
                            {
                                $s=substr_replace($c->getJourDeLaSemaine(),"",strpos($c->getJourDeLaSemaine(),$jour),1);
                                $c->setJourDeLaSemaine($s);
                                if(strlen($s)>0)
                                {
                                    $em->persist($c);
                                }
                                else{
                                    $em->remove($c);
                                }
                                break;
                            }
                        }
                        $commande2=$repC->findOneBy([
                            'idEnfant'=>$e,
                            'idMenu'=>$idmenu,
                        ]);
                        if($commande2)
                        {
                            $joursemaine=$commande2->getJourDeLaSemaine().$jour;
                            $commande2->setJourDeLaSemaine($joursemaine);
                            $em->persist($commande2);
                        }
                        else
                        {
                            $commande3=new MenuCommande();
                            $commande3->setIdEnfant($e);
                            $commande3->setJourDeLaSemaine($jour);
                            $commande3->setIdMenu($idmenu);
                            $em->persist($commande3);
                        }
                    }
                    else//il n'existe aucune commande du nom de l'enfant
                    {
                        $commande3=new MenuCommande();
                        $commande3->setIdEnfant($e);
                        $commande3->setJourDeLaSemaine($jour);
                        $commande3->setIdMenu($idmenu);
                        $em->persist($commande3);
                    }
                    $em->flush();
                    return $this->redirectToRoute("AfficherCommandes");

                }
                if($jour=="2")
                {
                    $jourC="Lundi";
                }
                if($jour=="3")
                {
                    $jourC="Mardi";
                }
                if($jour=="4")
                {
                    $jourC="Mercredi";
                }
                if($jour=="5")
                {
                    $jourC="Jeudi";
                }

                if($jour=="6")
                {
                    $jourC="Vendredi";
                }
                if($jour=="7")
                {
                    $jourC="Samedi";
                }

                return $this->render("@Pidev/Menu/Front/Reserver.html.twig",array('menu'=>$menu,'jour'=>$jourC,'form'=>$form->createView()));
            }
            else
            {
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }

    public function ajouterACantineAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Parent")
            {
                $commande=new MenuCommande();
                $doctrine=$this->getDoctrine();
                $vide=0;
                $enfants=$doctrine->getRepository(Enfant::class)->findBy([
                    'idParent'=>$this->getUser(),
                    'cantine'=>false,
                ]);
                if(empty($enfants))
                {
                    $vide=1;
                }
                $enfants2=array();
                foreach ($enfants as $tmp)
                {
                    $enfants2[$tmp->getPrenom()." ".$tmp->getNom()]=$tmp->getIdEnfant();
                }
                $form=$this->createForm(ReserverType::class,$commande,array('enfants'=>$enfants2));
                $em=$doctrine->getManager();
                $repC=$doctrine->getRepository(Chaise::class);
                $chaise=$repC->findOneBy([
                    'idEnfant'=>null,
                ]);
                if($chaise==null)
                {
                    $vide=2;
                }
                $repe=$doctrine->getRepository(Enfant::class);
                $form->handleRequest($request);
                if($form->isValid() && $form->isSubmitted())
                {
                    $enfant=$repe->find($commande->getIdEnfant());
                    $enfant->setCantine(true);
                    $chaise->setIdEnfant($enfant);
                    $em->persist($chaise);
                    $em->persist($enfant);
                    $em->flush();
                    return $this->redirectToRoute("AfficherMenusFront");
                }


                return $this->render("@Pidev/Menu/Front/AjouterACantine.html.twig",array('form'=>$form->createView(),'vide'=>$vide));
            }
            else
            {
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
    public function afficherCommandeAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Parent")
            {
                $commande=new MenuCommande();
                $doctrine=$this->getDoctrine();
                $enfants=$doctrine->getRepository(Enfant::class)->findBy([
                    'idParent'=>$this->getUser(),
                    'cantine'=>true,
                ]);
                $enfants2=array();
                foreach ($enfants as $tmp)
                {
                    $enfants2[$tmp->getPrenom()." ".$tmp->getNom()]=$tmp->getIdEnfant();
                }
                $form=$this->createForm(ReserverType::class,$commande,array('enfants'=>$enfants2));
                $em=$doctrine->getManager();
                $repC=$doctrine->getRepository(MenuCommande::class);
                $repM=$doctrine->getRepository(Menu::class);
                $repe=$doctrine->getRepository(Enfant::class);
                $form->handleRequest($request);
                $menus=array();
                $menu2=null;
                $menu3=null;
                $menu4=null;
                $menu5=null;
                $menu6=null;
                $menu7=null;
                $menu2id=null;
                $menu3id=null;
                $menu4id=null;
                $menu5id=null;
                $menu6id=null;
                $menu7id=null;
                if($form->isValid() && $form->isSubmitted())
                {
                    $commandes=$repC->findBy([
                        'idEnfant'=>$commande->getIdEnfant()
                    ]);
                    foreach ($commandes as $c)
                    {
                        if(strpos($c->getJourDeLaSemaine(),"2")!==false)
                        {
                            $menu2id=$c->getIdMenu();
                        }
                        if(strpos($c->getJourDeLaSemaine(),"3")!==false)
                        {
                            $menu3id=$c->getIdMenu();
                        }
                        if(strpos($c->getJourDeLaSemaine(),"4")!==false)
                        {
                            $menu4id=$c->getIdMenu();
                        }
                        if(strpos($c->getJourDeLaSemaine(),"5")!==false)
                        {
                            $menu5id=$c->getIdMenu();
                        }
                        if(strpos($c->getJourDeLaSemaine(),"6")!==false)
                        {
                            $menu6id=$c->getIdMenu();
                        }
                        if(strpos($c->getJourDeLaSemaine(),"7")!==false)
                        {
                            $menu7id=$c->getIdMenu();
                        }
                    }
                    if($menu2id)
                        $menu2=$repM->find($menu2id);
                    if($menu3id)
                        $menu3=$repM->find($menu3id);
                    if($menu4id)
                        $menu4=$repM->find($menu4id);
                    if($menu5id)
                        $menu5=$repM->find($menu5id);
                    if($menu6id)
                        $menu6=$repM->find($menu6id);
                    if($menu7id)
                        $menu7=$repM->find($menu7id);
                    array_push($menus,$menu2,$menu3,$menu4,$menu5,$menu6,$menu7);

                }


                return $this->render("@Pidev/Menu/Front/AfficherCommandes.html.twig",array('form'=>$form->createView(),'menus'=>$menus));
            }
            else
            {
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }

    public function parseAllMenusAction()
    {
        $menus=$this->getDoctrine()->getRepository(Menu::class)->findAll();
        $data=array();
        foreach ($menus as $key=>$m)
        {
            $data[$key]['id']=$m->getId();
            $data[$key]['nom']=$m->getNom();
            $data[$key]['image']=$m->getImage();
            $data[$key]['description']=$m->getDescription();
            $data[$key]['jour_de_la_semaine']=$m->getJourDeLaSemaine();
        }
        return new JsonResponse($data);
    }

    public function parseAllMenusForDayAction($day)
    {
        $menus=$this->getDoctrine()->getRepository(Menu::class)->AfficherMenusParJour($day);
        $data=array();
        foreach ($menus as $key=>$m)
        {
            $data[$key]['id']=$m->getId();
            $data[$key]['nom']=$m->getNom();
            $data[$key]['image']=$m->getImage();
            $data[$key]['description']=$m->getDescription();
            $data[$key]['jour_de_la_semaine']=$m->getJourDeLaSemaine();
        }
        return new JsonResponse($data);
    }
    public function parseChildrenOfParentAction($id)
    {
        $user=$this->getDoctrine()->getRepository(User::class)->find($id);
        $enfants=$this->getDoctrine()->getRepository(Enfant::class)->findBy([
            'idParent'=>$user
        ]);
        $data=array();
        foreach ($enfants as $key=>$e)
        {
            $data[$key]['idEnfant']=$e->getIdEnfant();
            $data[$key]['nom']=$e->getNom();
            $data[$key]['prenom']=$e->getPrenom();
            $data[$key]['datenaissance']=$e->getDatenaissance();
            $data[$key]['remarque']=$e->getRemarque();
            $data[$key]['image']=$e->getImage();
            $data[$key]['document']=$e->getDocument();
            $data[$key]['cantine']=$e->isCantine();
            $data[$key]['status']=$e->getStatus();

        }
        return new JsonResponse($data);

    }
    public function ReserverParMobileAction($idenfant,$idmenu,$jour)
    {
        $commande=new MenuCommande();
        $commande->setIdEnfant($idenfant);
        $e=$commande->getIdEnfant();
        $doctrine=$this->getDoctrine();
        $em=$doctrine->getManager();
        $repC=$doctrine->getRepository(MenuCommande::class);

        $commandes=$repC->findByIdEnfant($e);//verifier s'il ya des commande au nom de l'enfant selectionné
        if(!empty($commandes))//il existe ds commande au nom de l'enfant
        {
            foreach ($commandes as $c)
            {
                $jourS=strval($jour);
                if(strpos($c->getJourDeLaSemaine(),$jourS)!==false)
                {
                    $s=substr_replace($c->getJourDeLaSemaine(),"",strpos($c->getJourDeLaSemaine(),$jour),1);
                    $c->setJourDeLaSemaine($s);
                    if(strlen($s)>0)
                    {
                        $em->persist($c);
                    }
                    else{
                        $em->remove($c);
                    }

                    break;
                }
            }
            $commande2=$repC->findOneBy([
                'idEnfant'=>$e,
                'idMenu'=>$idmenu,
            ]);
            if($commande2)
            {
                $joursemaine=$commande2->getJourDeLaSemaine().$jour;
                $commande2->setJourDeLaSemaine($joursemaine);
                $em->persist($commande2);
            }
            else
            {
                $commande3=new MenuCommande();
                $commande3->setIdEnfant($e);
                $commande3->setJourDeLaSemaine($jour);
                $commande3->setIdMenu($idmenu);
                $em->persist($commande3);
            }
        }
        else//il n'existe aucune commande du nom de l'enfant
        {
            $commande3=new MenuCommande();
            $commande3->setIdEnfant($e);
            $commande3->setJourDeLaSemaine($jour);
            $commande3->setIdMenu($idmenu);
            $em->persist($commande3);
        }
        $em->flush();
        return new JsonResponse($commande);
    }

    public function parseAllCommandesEnfantAction($idEnfant)
    {
        $commandes=$this->getDoctrine()->getRepository(MenuCommande::class)->findBy([
            'idEnfant'=>$idEnfant
        ]);
        $repM=$this->getDoctrine()->getRepository(Menu::class);
        $data=array();
        $c=new MenuCommande();
        $m=new Menu();
        foreach ($commandes as $key=>$c)
        {
            $m=$repM->find($c->getIdMenu());
            $data[$key]['idMenu']=$c->getIdMenu();
            $data[$key]['nom']=$m->getNom();
            $data[$key]['image']=$m->getImage();
            $data[$key]['description']=$m->getDescription();
            //$data[$key]['jour_de_la_semaine_M']=$m->getJourDeLaSemaine();
            $data[$key]['idEnfant']=$c->getIdEnfant();
            $data[$key]['jour_de_la_semaine']=$c->getJourDeLaSemaine();

        }
        return new JsonResponse($data);
    }

    public function parseStatsAction()
    {
        $menus=$this->getDoctrine()->getRepository(Menu::class)->StatSurLesCommandes();
        $data=array();
        foreach ($menus as $key=>$m)
        {
            $data[$key]['id']=$m[0]->getId();
            $data[$key]['nom']=$m[0]->getNom();
            $data[$key]['image']=$m[0]->getImage();
            $data[$key]['description']=$m[0]->getDescription();
            $data[$key]['jour_de_la_semaine']=$m[0]->getJourDeLaSemaine();
            $data[$key]['nbr']=$m[1];
        }
        return new JsonResponse($data);
    }

    public function StatsMenuAction()
    {
        $menus=$this->getDoctrine()->getRepository(Menu::class)->StatSurLesCommandes();
        $nbL=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("2")[0][1];
        $nbMa=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("3")[0][1];
        $nbMe=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("4")[0][1];
        $nbJ=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("5")[0][1];
        $nbV=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("6")[0][1];
        $nbS=(int)$this->getDoctrine()->getRepository(Menu::class)->AfficherNbMenusParJour("7")[0][1];
        $nbrs=array([$nbL,$nbMa,$nbMe,$nbJ,$nbV,$nbS]);
        $data=array();
        $data[0][0]="Menu";
        $data[0][1]="Nombre de commande";
        foreach ($menus as $m)
        {
            $data[array_search($m,$menus)+1][0]=$m[0]->getNom();
            $data[array_search($m,$menus)+1][1]=(int)$m[1];
        }
        $dataRow=array();
        foreach ($menus as $m)
        {
            $dataRow[array_search($m,$menus)][0]=$m[0]->getNom();
            $dataRow[array_search($m,$menus)][1]=(int)$m[1];
        }
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable($data);
        $pieChart->getOptions()->setTitle('Statistique sur les menus commandé');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        //var_dump($pieChart);
        return $this->render("@Pidev/Menu/Back/Stat.html.twig",array("piechart"=>$pieChart,"data"=>$dataRow,"nbrs"=>$nbrs[0]));
        /*$data=array();
        foreach ($menus as $key=>$m)
        {
            $data[$key]['id']=$m[0]->getId();
            $data[$key]['nom']=$m[0]->getNom();
            $data[$key]['image']=$m[0]->getImage();
            $data[$key]['description']=$m[0]->getDescription();
            $data[$key]['jour_de_la_semaine']=$m[0]->getJourDeLaSemaine();
            $data[$key]['nbr']=$m[1];
        }
        return new JsonResponse($data);*/
    }

}

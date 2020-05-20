<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Commentaire;
use PidevBundle\Entity\Notification;
use PidevBundle\Entity\Sujet;
use PidevBundle\Entity\User;

use PidevBundle\Form\SujetType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class SujetController extends Controller
{
    public function AjouterBackAction(Request $request)
    {

        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $sujet=new Sujet();
                $form=$this->createForm(SujetType::class,$sujet);
                $form->handleRequest($request);
                $doctrine=$this->getDoctrine();
                if($form->isSubmitted())
                {
                    date_default_timezone_set('Africa/Tunis');
                    $datet=new \DateTime('now');
                    $sujet->setDatecreation($datet);
                    $sujet->setIdUser($this->getUser());
                    $em=$this->getDoctrine()->getManager();
                    $em->persist($sujet);
                    $users=$this->getDoctrine()->getRepository(User::class)->findAll();
                    $pusher = $this->get('mrad.pusher.notificaitons');
                    foreach ($users as $u)
                    {
                        if($u->getId() != $this->getUser()->getId())
                        {
                            var_dump($u->getId());
                            $notification = new Notification();
                            $notification
                                ->setTitle('Nouveau Sujet!')
                                ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a ajouter un sujet")
                                /*->setRoute("SetSeen")*/
                                ->setParameters(array("sujetId"=>$sujet->getId()))
                                ->setIcon($this->getUser()->getImage())
                                ->setIdUser($u)
                            ;
                            $em->persist($notification);

                            $pusher->trigger($notification);
                        }
                    }
                    $em->flush();


                    return $this->redirectToRoute("Afficher_Sujets_Back");
                }
                return $this->render("@Pidev/Sujet/Back/AjouterSujet.html.twig",array("form"=>$form->createView()));
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
    public function AfficherBackAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $doctrine=$this->getDoctrine();
                $rep=$doctrine->getRepository(Sujet::class);
                $sujets=$rep->findAll();
                $sujets=$rep->AfficherSujetEtUser();

                return $this->render("@Pidev/Sujet/Back/AfficherSujets.html.twig",array("sujets"=>$sujets));
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
    public function ModifierBackAction(Request $request,$idSujet)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $doctrine=$this->getDoctrine();
                $repS=$doctrine->getRepository(Sujet::class);
                $sujet=$repS->find($idSujet);
                $form=$this->createForm(SujetType::class,$sujet);
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    date_default_timezone_set('Africa/Tunis');
                    $datet=new \DateTime('now');
                    $sujet->setDateModification($datet);
                    $em=$this->getDoctrine()->getManager();
                    $em->persist($sujet);
                    $users=$this->getDoctrine()->getRepository(User::class)->findAll();
                    $pusher = $this->get('mrad.pusher.notificaitons');
                    foreach ($users as $u)
                    {
                        if($u != $this->getUser())
                        {
                            $notification = new Notification();
                            $notification
                                ->setTitle('Sujet Moifié!')
                                ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a modifié un sujet")
                                ->setParameters(array("sujetId"=>null))
                                ->setIcon($this->getUser()->getImage())
                                ->setIdUser($u)
                            ;
                            $em->persist($notification);
                            $pusher->trigger($notification);
                        }
                    }
                    $em->flush();
                    return $this->redirectToRoute("Afficher_Sujets_Back");
                }
                return $this->render("@Pidev/Sujet/Back/ModifierSujet.html.twig",array("form"=>$form->createView()));
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

    public function AjouterFrontAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            $sujet=new Sujet();
            $form=$this->createForm(SujetType::class,$sujet);
            $form->handleRequest($request);
            $doctrine=$this->getDoctrine();
            $repU=$doctrine->getRepository(User::class);
            if($form->isSubmitted())
            {
                date_default_timezone_set('Africa/Tunis');
                $datet=new \DateTime('now');
                $sujet->setDatecreation($datet);
                $sujet->setIdUser($this->getUser());
                $sujet->setCategorie("Reclamation");
                $em=$this->getDoctrine()->getManager();
                $em->persist($sujet);
                $users=$this->getDoctrine()->getRepository(User::class)->AfficherAdminsEtEmployes();
                $pusher = $this->get('mrad.pusher.notificaitons');
                foreach ($users as $u)
                {
                    if($u->getId() != $this->getUser()->getId())
                    {
                        $notification = new Notification();
                        $notification
                            ->setTitle('Nouvelle reclamation!')
                            ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a ajouté une réclamation")
                            /*->setRoute("SetSeen")*/
                            ->setParameters(array("sujetId"=>$sujet->getId()))
                            ->setIcon($this->getUser()->getImage())
                            ->setIdUser($u)
                        ;
                        $em->persist($notification);

                        $pusher->trigger($notification);
                    }
                }
                $em->flush();
                return $this->redirectToRoute("Afficher_Sujets_Front");
            }
            return $this->render("@Pidev/Sujet/Front/AjouterReclamation.html.twig",array("form"=>$form->createView()));
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }


    public function AfficherFrontAction(Request $request)
    {
        $doctrine=$this->getDoctrine();
        $rep=$doctrine->getRepository(Sujet::class);
        $sujets=$rep->findAll();
        $sujets=$rep->AfficherSujetEtUser();

        return $this->render("@Pidev/Sujet/Front/AfficherSujets.html.twig",array("sujets"=>$sujets));


    }
    public function ModifierFrontAction(Request $request,$idSujet)
    {
        if($this->getUser()!=null)
        {
            $doctrine=$this->getDoctrine();
            $repS=$doctrine->getRepository(Sujet::class);
            $sujet=$repS->find($idSujet);
            $form=$this->createForm(SujetType::class,$sujet);
            $form->handleRequest($request);
            if($form->isSubmitted())
            {
                date_default_timezone_set('Africa/Tunis');
                $datet=new \DateTime('now');
                $sujet->setDateModification($datet);
                $sujet->setCategorie("Reclamation");
                $em=$this->getDoctrine()->getManager();
                $em->persist($sujet);
                $users=$this->getDoctrine()->getRepository(User::class)->AfficherAdminsEtEmployes();
                $pusher = $this->get('mrad.pusher.notificaitons');
                foreach ($users as $u)
                {
                    if($u->getId() != $this->getUser()->getId())
                    {
                        $notification = new Notification();
                        $notification
                            ->setTitle('Reclamation modifiée!')
                            ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a modifié une reclamation")
                            /*->setRoute("SetSeen")*/
                            ->setParameters(array("sujetId"=>null))
                            ->setIcon($this->getUser()->getImage())
                            ->setIdUser($u)
                        ;
                        $em->persist($notification);

                        $pusher->trigger($notification);
                    }
                }
                $em->flush();
                return $this->redirectToRoute("Afficher_Sujets_Front");
            }
            return $this->render("@Pidev/Sujet/Front/ModifierReclamation.html.twig",array("form"=>$form->createView()));
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }

    public function SupprimerAction(Request $request,$idSujet)
    {
        if($this->getUser()!=null)
        {
            if ($this->getUser()->getRole() == "Employe" or $this->getUser()->getRole() == "Admin")
            {
                $em=$this->getDoctrine()->getManager();
                $repS=$this->getDoctrine()->getRepository(Sujet::class);
                $repC=$this->getDoctrine()->getRepository(Commentaire::class);
                $commantaires=$repC->findByIdSujet($idSujet);
                foreach ($commantaires as $c)
                {
                    $em->remove($c);
                }
                $sujet=$repS->find($idSujet);
                $em->remove($sujet);
                $em->flush();
                return $this->redirectToRoute("Afficher_Sujets_Back");
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }
}

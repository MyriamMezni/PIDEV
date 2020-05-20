<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Commentaire;
use PidevBundle\Entity\Notification;
use PidevBundle\Entity\Sujet;
use PidevBundle\Entity\User;
use PidevBundle\Form\CommentaireType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CommentaireController extends Controller
{
    public function AfficherCommentaireBackAction($idSujet,Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $doctrine=$this->getDoctrine();
                $repS=$doctrine->getRepository(Sujet::class);
                $repU=$doctrine->getRepository(User::class);
                $repC=$doctrine->getRepository(Commentaire::class);
                $sujet=$repS->find($idSujet);
                $commentaires=$repC->findBy(array(
                    'idSujet'=>$idSujet,
                ));
                $users1=$repU->AfficherAdminsEtEmployes();
                $users2=array();
                foreach ($commentaires as $c)
                {
                    array_push($users2,$c->getIdUser());
                }
                $user3=$sujet->getIdUser();
                $users=array();
                foreach ($users1 as $u)
                {
                    if(in_array($u,$users)===false)
                    {
                        array_push($users,$u);
                    }
                }
                foreach ($users2 as $u)
                {
                    if(in_array($u,$users)===false)
                    {
                        array_push($users,$u);
                    }
                }
                if(in_array($user3,$users)===false)
                {
                    array_push($users,$user3);
                }

                $commentaire=new Commentaire();
                $form=$this->createForm(CommentaireType::class,$commentaire);
                $form->handleRequest($request);
                if ($form->isSubmitted())
                {
                    $commentaire->setIdUser($this->getUser());
                    $commentaire->setIdSujet($sujet);
                    date_default_timezone_set('Africa/Tunis');
                    $datet=new \DateTime('now');
                    $commentaire->setDatec($datet);
                    $em=$doctrine->getManager();
                    $em->persist($commentaire);
                    $pusher = $this->get('mrad.pusher.notificaitons');
                    foreach ($users as $u)
                    {
                        if($u->getId() != $this->getUser()->getId())
                        {
                            $notification = new Notification();
                            $notification
                                ->setTitle('Nouveau Commantaire!')
                                ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a ajoute un commantaire a la publication: ".$sujet->getTitre())
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
                }
                $commentaires=$repC->AfficherCommantairesEtUserEtSujet($idSujet);
                return $this->render("@Pidev/Commentaire/Back/AfficherCommantaires.html.twig",array("sujet"=>$sujet,"commentaires"=>$commentaires,"form"=>$form->createView()));
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

    public function AfficherCommentairesFrontAction($idSujet,Request $request)
    {

        if($this->getUser()!=null)
        {
            $doctrine=$this->getDoctrine();
            $repS=$doctrine->getRepository(Sujet::class);
            $repU=$doctrine->getRepository(User::class);
            $repC=$doctrine->getRepository(Commentaire::class);
            $sujet=$repS->find($idSujet);
            $commentaires=$repC->findBy(array(
                'idSujet'=>$idSujet,
            ));
            $users1=$repU->AfficherAdminsEtEmployes();
            $users2=array();
            foreach ($commentaires as $c)
            {
                array_push($users2,$c->getIdUser());
            }
            $user3=$sujet->getIdUser();
            $users=array();
            foreach ($users1 as $u)
            {
                if(in_array($u,$users)===false)
                {
                    array_push($users,$u);
                }
            }
            foreach ($users2 as $u)
            {
                if(in_array($u,$users)===false)
                {
                    array_push($users,$u);
                }
            }
            if(in_array($user3,$users)===false)
            {
                array_push($users,$user3);
            }

            $commentaire=new Commentaire();
            $form=$this->createForm(CommentaireType::class,$commentaire);
            $form->handleRequest($request);
            if ($form->isSubmitted())
            {
                $commentaire->setIdUser($this->getUser());
                $commentaire->setIdSujet($sujet);
                date_default_timezone_set('Africa/Tunis');
                $datet=new \DateTime('now');
                $commentaire->setDatec($datet);
                $em=$doctrine->getManager();
                $em->persist($commentaire);
                $pusher = $this->get('mrad.pusher.notificaitons');
                foreach ($users as $u)
                {
                    if($u->getId() != $this->getUser()->getId())
                    {
                        $notification = new Notification();
                        $notification
                            ->setTitle('Nouveau Commantaire!')
                            ->setDescription($this->getUser()->getPrenom()." ".$this->getUser()->getNom()." a ajoute un commantaire a la publication: ".$sujet->getTitre())
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
            }
            $commentaires=$repC->AfficherCommantairesEtUserEtSujet($idSujet);
            return $this->render("@Pidev/Commentaire/Front/AfficherCommentaires.html.twig",array("sujet"=>$sujet,"commentaires"=>$commentaires,"form"=>$form->createView()));
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }


    }
}

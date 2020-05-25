<?php

namespace PidevBundle\Controller;

use FOS\UserBundle\Event\FormEvent;
use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\Mailemp;
use PidevBundle\Entity\User;
use PidevBundle\Form\EnfantType;
use PidevBundle\Form\RechercheEnfantType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Knp\Bundle\SnappyBundle\Snappy\Response\JpegResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Validator\ValidatorInterface;

/**
 * Enfant controller.
 *
 * @Route("enfant")
 */
class EnfantController extends Controller
{
    /**
     * Lists all enfant entities.
     *
     * @Route("/", name="enfant_index")
     * @Method("GET")
     */
    public function indexAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $enfant=$em->getRepository(Enfant::class)->find($id);
        $enfants = $em->getRepository('PidevBundle:Enfant')->findAll();


        return $this->render('@Pidev/Enfant/Front/affiche.html.twig', array(
            'enfants' => $enfants,
            'enfant' => $enfant,
        ));

    }

    public function rechercherEnfantAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $enfant= new Enfant();
                $em = $this->getDoctrine()->getManager();

                $form= $this->createForm(RechercheEnfantType::class,$enfant);
                $form->handleRequest($request);
                $enfant= $this->getDoctrine()->getRepository(Enfant::class)
                    ->findAll();
                if($form->isSubmitted()){
                    $enfant= new Enfant();
                    $form= $this->createForm(RechercheEnfantType::class,$enfant);
                    $form->handleRequest($request);

                    $enfant= $this->getDoctrine()->getRepository(Enfant::class)
                        ->findBy(array('nom'=>$enfant->getNom(), 'prenom'=>$enfant->getPrenom()));
                    return $this->render("@Pidev/Enfant/Back/search.html.twig" , array("form"=>$form->createView(),'enfant'=>$enfant));
                }

                $paginator=$this->get('knp_paginator');

                $pagination = $paginator->paginate(
                    $enfant, /* query NOT result */
                    $request->query->getInt('page', 1),

                     /*page number*/
                    3 /*limit per page*/
                );
                return $this->render("@Pidev/Enfant/Back/affiche.html.twig",array( "form"=>$form->createView(),'enfant'=>$enfant,'pagination'=>$pagination));
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



    /**
     * Creates a new enfant entity.
     *
     * @Route("/new", name="enfant_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        if($this->getUser()!=null)
    {
        if($this->getUser()->getRole()=="Parent")
        {
            $enfant = new Enfant();

            $form = $this->createForm('PidevBundle\Form\EnfantType', $enfant);
            $form->handleRequest($request);

            $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
            if ($form->isSubmitted() && $form->isValid()) {

                $em = $this->getDoctrine()->getManager();
                $file=$form['image']->getData();
                $file1=$form['document']->getData();
                $extension = $file->guessExtension();
                if (!$extension) {
                    // extension cannot be guessed
                    $extension = 'bin';
                }
                $extension1 = $file1->guessExtension();
                if (!$extension1) {
                    // extension cannot be guessed
                    $extension1 = 'bin';
                }
                $name=rand(1, 99999).'.'.$extension;
                $name1=rand(1, 99999).'.'.$extension1;
                $enfant->setImage($name);
                $enfant->setDocument($name1);
                $file->move($directory, $name);
                $file1->move($directory, $name1);
                $event = new FormEvent($form, $request);
                $user = $this->container->get('security.token_storage')->getToken()->getUser();
                $enfant->setIdParent($this->container->get('security.token_storage')->getToken()->getUser());
                $em->persist($enfant);
                $this->get('session')->getFlashBag()->add('success', 'ajout aves success ');
                $em->flush();

                return $this->redirectToRoute('ajoutEnfant', array('idEnfant' => $enfant->getIdenfant()));
            }

            return $this->render('@Pidev/Enfant/Front/ajoutEnfant.html.twig', array(
                'enfant' => $enfant,
                'form' => $form->createView(),
            ));
        }
        else{
            return $this->render('Back.html.twig');
        }
    }
    else
    {
        return $this->redirectToRoute("fos_user_security_login");
    }


    }




    /**
     * Finds and displays a enfant entity.
     *
     * @Route("/{idEnfant}", name="enfant_show")
     * @Method("GET")
     */
    public function showAction(Enfant $enfant)
    {
        $deleteForm = $this->createDeleteForm($enfant);

        return $this->render('enfant/show.html.twig', array(
            'enfant' => $enfant,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function getParentAction(){


        $em = $this->getDoctrine()->getManager();

        $RAW_QUERY = 'SELECT nom FROM my_table where my_table.field = 1 LIMIT 5;';

        $statement = $em->getConnection()->prepare($RAW_QUERY);
        $statement->execute();

        $result = $statement->fetchAll();

    }

    /**
     * Displays a form to edit an existing enfant entity.
     *
     * @Route("/{idEnfant}/edit", name="enfant_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Enfant $enfant)
    {
        $deleteForm = $this->createDeleteForm($enfant);
        $editForm = $this->createForm('PidevBundle\Form\EnfantType', $enfant);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('enfant_edit', array('idEnfant' => $enfant->getIdenfant()));
        }

        return $this->render('enfant/edit.html.twig', array(
            'enfant' => $enfant,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public  function updateAction(Request $request, $id)
    {

        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $em=$this->getDoctrine()->getManager();
                $enfant=$em->getRepository(Enfant::class)->find($id);
                $enfants=$em->getRepository(Enfant::class)->findAll();
                if($request->isMethod('POST'))
                {
                    $enfant->setNom($request->get('nom'));
                    $enfant->setPrenom($request->get('prenom'));
                    $enfant->setCantine($request->get('cantine'));
                    $enfant->setRemarque($request->get('remarque'));
                   // $enfant->setDatenaissance(new \DateTime('2016/01/01'));
                    $em->flush();

                    return $this->redirectToRoute('afficheEnfant');

                }

                return $this->render('@Pidev/Enfant/Back/exemple.html.twig',array('enfant'=>$enfant));
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

    /**
     * Deletes a enfant entity.
     *
     * @Route("/{idEnfant}", name="enfant_delete")
     * @Method("DELETE")
     */
    public function deleteAction($id)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {    $em= $this->getDoctrine()->getManager();
                $enfant= $this->getDoctrine()->getRepository(Enfant::class)->find($id);
                $this->supMailAction($id);
                $em->remove($enfant);
                $em->flush();


                return $this->redirectToRoute('afficheEnfant');

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

    public function supMailAction($id) {
        $mail=new Mailemp();

        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository(Enfant::class)->find($id);
        $form= $this->createForm(RechercheEnfantType::class,$enfant);

        $subject=$mail->getSubject();
        $object=$mail->getObject();
        $mail=$mail->getMail();
        //$object=$request ->get('form')['object'];
        $username="mohamedmselmi407@gmail.com";
        $message=\Swift_Message::newInstance()
            ->setSubject("inscription")
            ->setBody("inscription invalide")
            ->setFrom($username)
            ->setTo($enfant->getIdparent()->getEmail());


        //$sujets=$rep->AfficherSujetEtUser();
        $this->get('mailer')->send($message);








        return  $this->redirectToRoute('afficheEnfant');

    }

    /**
     * Creates a form to delete a enfant entity.
     *
     * @param Enfant $enfant The enfant entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Enfant $enfant)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('enfant_delete', array('idEnfant' => $enfant->getIdenfant())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }

    public function getCustomInformations()
    {
        $rawSql = "SELECT m.id, (SELECT COUNT(i.id) FROM item AS i WHERE i.myclass_id = m.id) AS total FROM myclass AS m";

        $stmt = $this->getEntityManager()->getConnection()->prepare($rawSql);
        $stmt->execute([]);

        return $stmt->fetchAll();
    }

    public function azertyAction(Request $request,$id)
    {
        // replace this example code with whatever you need
        $snappy = $this->get("knp_snappy.pdf");
        $enfant= new Enfant();
        $rootDir=$this->get('kernel')->getRootDir().'/..';
        $form= $this->createForm(EnfantType::class,$enfant);
        $form->handleRequest($request);

        //$directory=$this->get('kernel')->getProjectDir()."\web\images\\";

        $enfants=$this->getDoctrine()->getRepository(Enfant::class)->findAll();
        $enfant=$this->getDoctrine()->getRepository(Enfant::class)->find($id);

        //$html=$this->renderView("@Pidev/Enfant/Back/pdf.html.twig",array('enfants'=>$enfants));
        $html = $this->renderView('@Pidev/Enfant/Back/pdf.html.twig', array(
            'enfants'=>$enfants,
            'enfant'=>$enfant,
            'rootDir'=>$this->get('kernel')->getProjectDir()."",
            'rootDir1'=>$this->get('kernel')->getProjectDir()."",
        ));

        $filename="costom_pdf_from_twig";
        return new Response(
            $snappy->getoutputFromHtml($html,array('images'=> true,'enable-external-links' => true)),
            200,
            array(
                'images' =>true,
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'attachment; filename="inscrit.pdf"'
            )
        );
    }

    public function pdfAction(Request $request, $id)
    {

        // replace this example code with whatever you need
        $snappy = $this->get("knp_snappy.pdf");
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository(Enfant::class)->find($id);
        $enfants=$em->getRepository(Enfant::class)->findAll();

        if($request->isMethod('POST'))
        {
            $enfant->setNom($request->get('nom'));
            $enfant->setPrenom($request->get('prenom'));
            $enfant->setCantine($request->get('cantine'));
            $enfant->setRemarque($request->get('remarque'));
            $enfant->setImage($request->get('image'));
            $em->flush();



        }
        $html = $this->renderView('@Pidev/Enfant/Back/PDF1.html.twig', array(
            'enfant'=>$enfant,
            'rootDir'=>$this->get('kernel')->getProjectDir()."",
        ));

        $filename="costom_pdf_from_twig";
        return new Response(
            $snappy->getoutputFromHtml($html,array('images'=> true,'enable-external-links' => true)),
            200,
            array(
                'images' =>true,
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'attachment; filename="azerty.pdf"'
            )
        );
    }

    public function ajoutAction(Request $request,$id){

        $em=$this->getDoctrine()->getManager();
        $enfant=new Enfant();
        $user=new User(12);
        //    $enfant->setIdEnfant($request->get('id_enfant'));
        $enfant->setNom($request->get('nom'));
        $enfant->setPrenom($request->get('prenom'));

        $enfant->setDatenaissance(new \DateTime('2016-1-1'));
        $enfant->setRemarque($request->get('remarque'));

        $enfant->setImage($request->get('image'));
        $enfant->setCantine($request->get('cantine'));
        $enfant->setIdParent($em->getRepository(User::class)->find($id));
        $enfant->setDocument($request->get('document'));
        $enfant->setStatus("en attente");
        $em->persist($enfant);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($enfant);
        return new JsonResponse($formatted);
    }

    public function allAction(){
        $tasks=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:User')
            ->findAll();

        $a=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:Enfant')
            ->findAll();

        $person = new Enfant();
        $person->setNom('test');
        $person->setPrenom('test');


        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(1);

        $normalizer->setCircularReferenceHandler(function () {
            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Enfant')->findAll();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($a);



        return new JsonResponse($formatted);


    }


}


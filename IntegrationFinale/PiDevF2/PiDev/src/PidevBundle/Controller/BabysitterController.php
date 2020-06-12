<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Babysitter;
use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\User;
use PidevBundle\Form\BabysitterType;
use PidevBundle\Form\RechercheBabysitterType;
use PidevBundle\Form\RechercheEnfantType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
/**
 * Babysitter controller.
 *
 * @Route("babysitter")
 */
class BabysitterController extends Controller
{
    /**
     * Lists all babysitter entities.
     *
     * @Route("/", name="babysitter_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {
        $babysitter= new Babysitter();
        $id=$babysitter->getIdbabysitter();
        $em = $this->getDoctrine()->getManager();

        //  $aa= $this->getDoctrine()->getRepository(Babysitter::class)->find($id);
        //  $aa->handleRequest($aa);
        $form= $this->createForm(RechercheBabysitterType::class,$babysitter);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $babysitter= new Babysitter();
            $form= $this->createForm(RechercheBabysitterType::class,$babysitter);
            $form->handleRequest($request);
            $babysitter= $this->getDoctrine()->getRepository(Babysitter::class)
                ->findBy(array('joursemaine'=>$babysitter->getJoursemaine(), 'idEnfant'=>$babysitter->getIdEnfant()));
            return $this->render('@Pidev/Babysitter/Back/search.html.twig', array(
                "form"=>$form->createView(),
                'babysitter' => $babysitter,
                //'aa' =>$aa,
            ));
        }

            $babysitter= $this->getDoctrine()->getRepository(Babysitter::class)
                ->findAll();
        $paginator=$this->get('knp_paginator');

        $pagination = $paginator->paginate(
            $babysitter, /* query NOT result */
            $request->query->getInt('page', 1),

            /*page number*/
            3 /*limit per page*/
        );



        return $this->render('@Pidev/Babysitter/Back/afficheBabysitter.html.twig', array(
            "form"=>$form->createView(),
            'babysitter' => $babysitter,
            'pagination'=>$pagination
            //'aa' =>$aa,
        ));
    }

    /**
     * Creates a new babysitter entity.
     *
     * @Route("/new", name="babysitter_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $babysitter = new Babysitter();
        $form = $this->createForm('PidevBundle\Form\BabysitterType', $babysitter);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $babysitter->setJoursemaine(implode(" ",$_POST["trame"]));
            $em->persist($babysitter);
            $this->get('session')->getFlashBag()->add('success', 'ajout aves success ');
            $em->flush();

            return $this->redirectToRoute('afficheBabysitter', array('idbabysitter' => $babysitter->getIdbabysitter()));
        }

        return $this->render('@Pidev/Babysitter/Back/ajout.html.twig', array(
            'babysitter' => $babysitter,
            'form' => $form->createView(),
        ));
    }


    public function test2Action($idParent)
    { $a = $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findBy(array(

        'idEnfant' => $this->getDoctrine()->getManager()->getRepository('PidevBundle:Enfant')->findBy(
            array('idParent'=>$idParent,
            ))
    ));

        //    $bs = $this->getDoctrine()->getRepository(Babysitter::class)->findAll();
        $data = array();
        foreach ($a as $key => $u) {
            $data[$key]['heuredebut'] = $u->getHeuredebut();
            $data[$key]['heurefin'] = $u->getHeurefin();
            $data[$key]['joursemaine'] = $u->getJoursemaine();
            $data[$key]['prixheure'] = $u->getPrixheure();
            $data[$key]['idbabysitter'] = $u->getIdbabysitter();
            $data[$key]['IdEnfant'] = $u->getIdEnfant();
        }

        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function ($idParent) {
            $a = $this->getDoctrine()->getManager()->getRepository('PidevBundle:Enfant')->findBy(array(
                'idParent' => $idParent
            ));
            foreach ($a as $c) {

                return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findBy(array(
                    'idEnfant' => $c,


                ));
            }
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($data);
        return new JsonResponse($formatted);
    }
    /*public function test2Action()
    {
        $bs=$this->getDoctrine()->getRepository(Babysitter::class)->findAll();
        $data=array();
        foreach ($bs as $key=>$u)
        {
            $data[$key]['heuredebut']=$u->getHeuredebut();
            $data[$key]['heurefin']=$u->getHeurefin();
            $data[$key]['joursemaine']=$u->getJoursemaine();
            $data[$key]['prixheure']=$u->getPrixheure();
            $data[$key]['idbabysitter']=$u->getIdbabysitter();
            $data[$key]['IdEnfant']=$u->getIdEnfant();
        }
        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function () {

            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findAll();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($data);
        return new JsonResponse($formatted);
    }*/
    /**
     * Finds and displays a babysitter entity.
     *
     * @Route("/{idbabysitter}", name="babysitter_show")
     * @Method("GET")
     */
    public function showAction(Babysitter $babysitter)
    {
        $deleteForm = $this->createDeleteForm($babysitter);

        return $this->render('babysitter/show.html.twig', array(
            'babysitter' => $babysitter,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing babysitter entity.
     *
     * @Route("/{idbabysitter}/edit", name="babysitter_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Babysitter $babysitter)
    {
        $deleteForm = $this->createDeleteForm($babysitter);
        $editForm = $this->createForm('PidevBundle\Form\BabysitterType', $babysitter);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('babysitter_edit', array('idbabysitter' => $babysitter->getIdbabysitter()));
        }

        return $this->render('babysitter/edit.html.twig', array(
            'babysitter' => $babysitter,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a babysitter entity.
     *
     * @Route("/{idbabysitter}", name="babysitter_delete")
     * @Method("DELETE")
     */
    public function deleteAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $babysitter= $this->getDoctrine()->getRepository(Babysitter::class)->find($id);
        $em->remove($babysitter);
        $em->flush();

        return $this->redirectToRoute('afficheBabysitter');
    }

    /**
     * Creates a form to delete a babysitter entity.
     *
     * @param Babysitter $babysitter The babysitter entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Babysitter $babysitter)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('babysitter_delete', array('idbabysitter' => $babysitter->getIdbabysitter())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }

    public  function updateAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
      //  $u = $babysitter->getUser();
      //  $id = $u->getId();

        $babysitter=$em->getRepository(Babysitter::class)->find($id);
        $babysitters=$em->getRepository(Babysitter::class)->findAll();
        $form= $this->createForm(BabysitterType::class,$babysitter);
        $form->handleRequest($request);
        if ($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $babysitter->setJoursemaine(implode(" ",$_POST["trame"]));
            $em->persist($babysitter);
            $em->flush();


            return $this->redirectToRoute("afficheBabysitter");
        }

        return $this->render('@Pidev/Babysitter/Back/update.html.twig',array('form' => $form->createView(),'babysitter'=>$babysitter));
    }


    public function RemoveAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $babysitter= $this->getDoctrine()->getRepository(Babysitter::class)->find($id);

        $data=array();
        foreach ($babysitter as $key=>$u)
        {
            $data[$key]['heuredebut']=$u->getHeuredebut();
            $data[$key]['heurefin']=$u->getHeurefin();
            $data[$key]['joursemaine']=$u->getJoursemaine();
            $data[$key]['prixheure']=$u->getPrixheure();
            $data[$key]['idbabysitter']=$u->getIdbabysitter();
            $data[$key]['IdEnfant']=$u->getIdEnfant();
        }
        $em->remove($babysitter);
        $em->flush();

        $tasks=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:User')
            ->findAll();

        $babysitter= new Babysitter();

        $a=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:Babysitter')
            ->findAll();




        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function () {

            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findAll();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($data);



        return new JsonResponse($formatted);
    }

    /*public function RemoveAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $babysitter= $this->getDoctrine()->getRepository(Babysitter::class)->find($id);
        $em->remove($babysitter);
        $em->flush();

        $tasks=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:User')
            ->findAll();

        $babysitter= new Babysitter();

        $a=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:Babysitter')
            ->findAll();




        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function () {

            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findAll();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($a);



        return new JsonResponse($formatted);
    }*/

    public function testAction()
    {



        $tasks=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:User')
            ->findAll();

        $babysitter= new Babysitter();

        $a=$this->getDoctrine()->getManager()
            ->getRepository('PidevBundle:Babysitter')
            ->findAll();




        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function () {

            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->findAll();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($a);



        return new JsonResponse($formatted);
    }

    public function ajoutAction(Request $request,$id,$s){

        $em=$this->getDoctrine()->getManager();
        $babysitter=new Babysitter();

        $babysitter->setIdbabysitter($em->getRepository(User::class)->find($id));
        $babysitter->setHeuredebut($request->get('heuredebut'));
        $babysitter->setHeurefin($request->get('heurefin'));
        $babysitter->setJoursemaine($request->get('joursemaine'));
        $babysitter->setPrixheure($request->get('prixheure'));
        $babysitter->setIdEnfant($em->getRepository(Enfant::class)->find($s));
        $em->persist($babysitter);
        $em->flush();
        $bs=$this->getDoctrine()->getRepository(Babysitter::class)->findAll();
        $t=$this->getDoctrine()->getRepository(User::class)->find($id);
        $data=array();
        foreach ($bs as $key=>$u)
        {
            $data[$key]['heuredebut']=$u->getHeuredebut();
            $data[$key]['heurefin']=$u->getHeurefin();
            $data[$key]['joursemaine']=$u->getJoursemaine();
            $data[$key]['prixheure']=$u->getPrixheure();
            $data[$key]['idbabysitter']=$babysitter->getIdEnfant();
            $data[$key]['IdEnfant']=$u->getIdEnfant();
        }

        $normalizer = new ObjectNormalizer();


        $normalizer->setCircularReferenceHandler(function ($id) {

            //return $this->getDoctrine()->getManager()->getRepository('PidevBundle:Babysitter')->AfficheEnfant($id);
            return $this->getDoctrine()->getManager()->getRepository('PidevBundle:User')->AfficherBabysitterParId($id);

        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers);

        $formatted =$serializer->normalize($data);
        return new JsonResponse($formatted);

    }


    public function UpdateMobileAction($id,$s,$t,$p,$k,$q)
    {
        $em= $this->getDoctrine()->getManager();
        $b= $this->getDoctrine()->getRepository(User::class)->find($id);
        $x= $this->getDoctrine()->getRepository(Babysitter::class)->find($id);
        // $em->remove($x);
        //  $em->flush();
        $babysitter=new Babysitter();


        $x->setHeuredebut($s);
        $x->setHeurefin($t);
        $x->setJoursemaine($p);
        $x->setPrixheure($k);
        $x->setIdbabysitter($b);
        $x->setIdEnfant($this->getDoctrine()->getRepository(Enfant::class)->find($q));
        $em->persist($x);
        $em->flush();
        $data = array();
        foreach ($babysitter as $key => $u) {
            $data[$key]['heuredebut'] = $u->getHeuredebut();
            $data[$key]['heurefin'] = $u->getHeurefin();
            $data[$key]['joursemaine'] = $u->getJoursemaine();
            $data[$key]['prixheure'] = $u->getPrixheure();
            $data[$key]['idbabysitter'] = $u->getIdbabysitter();
            $data[$key]['IdEnfant'] = $u->getIdEnfant();
        }










        return new JsonResponse($data);
    }



}

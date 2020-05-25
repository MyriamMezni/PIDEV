<?php


namespace PidevBundle\Controller;


use blackknight467\StarRatingBundle\Form\RatingType;
use PidevBundle\Entity\Ratingactivite;
use PidevBundle\Form\RatingactiviteType;
use Skies\QRcodeBundle\DineshBarcode\QRcode;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class RatingactiviteController extends Controller
{
public function RatingactiviteAction(Request $request,$intitule)
{


    $Rating= new Ratingactivite();
    $form=$this->createForm(RatingactiviteType::class,$Rating);
    $form=$form->handleRequest($request);
    if($form->isValid())
    {
        $em=$this->getDoctrine()->getManager();
        $Rating->setIntitule($intitule);
        $em->persist($Rating);
        $em->flush();

        return $this->redirectToRoute('ReadActiviteFront');
    }

    return $this->render('@Pidev/Activite/CreateRatingactivite.html.twig',array('intitule'=>$intitule,'form'=>$form->createView()));


}
public function newAction(Request $request)
{

    $em=$this->getDoctrine()->getManager();
    $task=new Ratingactivite();
    $task->setIntitule($request->get('intitule'));
    $task->setRate($request->get('rate'));
    $em->persist($task);
    $em->flush();
    $serializer=new Serializer([new ObjectNormalizer()]);
    $formatted=$serializer->normalize($task);
    return new JsonResponse($formatted);
}


    public function allAction()
    {
        $activite=$this->getDoctrine()->getManager()->getRepository('PidevBundle:Ratingactivite')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($activite);

        return new JsonResponse($formatted);
    }



}
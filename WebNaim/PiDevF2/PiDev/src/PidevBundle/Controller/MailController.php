<?php
/**
 * Created by PhpStorm.
 * User: HEDI MSELMI
 * Date: 27/03/2020
 * Time: 17:05
 */

namespace PidevBundle\Controller;


use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\Mailemp;
use PidevBundle\Form\MailempType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class MailController extends Controller
{
    public function sendMailAction(Request $request, $id) {
        $mail=new Mailemp();
        $form=$this->createForm(MailempType::class,$mail);
        $form=$form->handleRequest($request);
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository(Enfant::class)->find($id);

        if($form->isSubmitted() && $form->isValid())
        {
            $subject=$mail->getSubject();
            $object=$mail->getObject();
            $mail=$mail->getMail();
            //$object=$request ->get('form')['object'];
            $username="mohamedmselmi407@gmail.com";
            $message=\Swift_Message::newInstance()->setSubject("test")
                ->setSubject($subject)
                ->setBody($object)
                ->setFrom($username)
                ->setTo($enfant->getIdparent()->getEmail())
            ;
            $enfant=$em->getRepository(Enfant::class)->UpdateStatus($id);
            //$sujets=$rep->AfficherSujetEtUser();
            $this->get('mailer')->send($message);





            $this->get('session')->getFlashBag()->add('notice','the message was sent');

        }
        return $this->render('@Pidev\Enfant\Back\email.html.twig', array(
            'form'=>$form->createView()));

    }



}
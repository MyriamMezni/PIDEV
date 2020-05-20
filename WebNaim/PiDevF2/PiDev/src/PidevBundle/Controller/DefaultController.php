<?php

namespace PidevBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PidevBundle:Default:index.html.twig');
    }
    public function backAction(Request $request)
    {
        // replace this example code with whatever you need
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                return $this->render('Back.html.twig');
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


    public function frontAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->render('Front.html.twig');
    }
    public function redirectAction()
    {
        $check=$this->getUser()->getRole();
        if($check=='Parent')
        {

            return $this->redirectToRoute('front');
        }
        else
        {
            if($check=="Admin" || $check=="Employe")
            {
                return $this->redirectToRoute('back');
            }
        }
    }
}

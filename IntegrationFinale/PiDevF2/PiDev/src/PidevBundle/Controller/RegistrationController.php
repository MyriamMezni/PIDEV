<?php

namespace PidevBundle\Controller;

use FOS\UserBundle\Event\FilterUserResponseEvent;
use FOS\UserBundle\Event\FormEvent;
use FOS\UserBundle\Form\Factory\FactoryInterface;
use FOS\UserBundle\FOSUserEvents;
use FOS\UserBundle\Model\UserManagerInterface;
use Symfony\Component\EventDispatcher\EventDispatcherInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use FOS\UserBundle\Controller\RegistrationController as BaseController;
use FOS\UserBundle\Event\GetResponseUserEvent;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\UploadedFile;

class RegistrationController extends BaseController
{
    public function __construct()
    {
    }


    public function registerUserAction(Request $request,$role)
    {
        /** @var $formFactory FactoryInterface */
        $formFactory = $this->get('fos_user.registration.form.factory');
        /** @var $userManager UserManagerInterface */
        $userManager = $this->get('fos_user.user_manager');
        /** @var $dispatcher EventDispatcherInterface */
        $dispatcher = $this->get('event_dispatcher');

        $user = $userManager->createUser();
        $user->setEnabled(true);
        $user->setRole($role);
        if($role=='Parent')
        {
            $user->addRole('ROLE_PARENT');
        }
        if($role=='Admin' or $role=='Employe')
        {
            $user->addRole('ROLE_ADMIN');
        }


        $event = new GetResponseUserEvent($user, $request);
        $dispatcher->dispatch(FOSUserEvents::REGISTRATION_INITIALIZE, $event);

        if (null !== $event->getResponse()) {
            return $event->getResponse();
        }

        $form = $formFactory->createForm();
        $form->setData($user);
        $form->handleRequest($request);
        $directory=$this->get('kernel')->getProjectDir()."\web\images\\";

        if ($form->isSubmitted())
        {
            if ($form->isValid())
            {
                $user->setMdp($form['plainPassword']->getData());
                if($role=='Parent')
                {
                    $user->setTypeEmploye(null);
                }
                if($role=='Admin')
                {
                    $user->setTypeEmploye(null);
                }
                $file=$form['image']->getData();
                $extension = $file->guessExtension();
                if (!$extension) {
                    // extension cannot be guessed
                    $extension = 'bin';
                }
                var_dump($file);
                $name=rand(1, 99999).'.'.$extension;
                $user->setImage($name);
                $file->move($directory, $name);

                $event = new FormEvent($form, $request);

                $dispatcher->dispatch(FOSUserEvents::REGISTRATION_SUCCESS, $event);
                $userManager->updateUser($user);




                if (null === $response = $event->getResponse()) {
                    $url = $this->generateUrl('fos_user_registration_confirmed');
                    $response = new RedirectResponse($url);
                }

                $dispatcher->dispatch(FOSUserEvents::REGISTRATION_COMPLETED, new FilterUserResponseEvent($user, $request, $response));

                return $response;
            }

            $event = new FormEvent($form, $request);
            $dispatcher->dispatch(FOSUserEvents::REGISTRATION_FAILURE, $event);

            if (null !== $response = $event->getResponse()) {
                return $response;
            }
        }

        return $this->render('@FOSUser/Registration/register.html.twig', array(
            'form' => $form->createView(),'role'=>$role
        ));
    }



}

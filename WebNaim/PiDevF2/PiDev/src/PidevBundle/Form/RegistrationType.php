<?php
// src/AppBundle/Form/RegistrationType.php

namespace PidevBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\BirthdayType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class RegistrationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('prenom')
            ->add('nom')
            ->add('image',FileType::class)
            ->add('numtel')
            ->add('datenaissance',BirthdayType::class)
            ->add('salaire')
            ->add('region',ChoiceType::class, [
                'choices'  => [
                'Tunis' => 'Tunis',
                'Ariana' => 'Ariana',
                'Ben Arous' => 'Ben Arous',
                ],
            ])
            ->add('ville')
            ->add('rue')
            ->add('codepostal')
            ->add('nbheures')
            ->add('typeemploye',ChoiceType::class, [
                'choices'  => [
                    'Activite' => 'Activite',
                    'Cantine' => 'Cantine',
                    'BabySitter' => 'BabySitter',
                ],
            ])
            ->add('tarif')
            ->add('nbenfant');
    }

    public function getParent()
    {
        return 'FOS\UserBundle\Form\Type\RegistrationFormType';
    }

    public function getBlockPrefix()
    {
        return 'app_user_registration';
    }

    // For Symfony 2.x
    public function getNom()
    {
        return $this->getBlockPrefix();
    }
}
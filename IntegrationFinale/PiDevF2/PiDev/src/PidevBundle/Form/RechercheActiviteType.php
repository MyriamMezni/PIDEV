<?php


namespace PidevBundle\Form;


use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;

class RechercheActiviteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {

        $builder  ->add('intitule')
            ->add('submit',SubmitType::class);


    }
}
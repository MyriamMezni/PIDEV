<?php

namespace PidevBundle\Form;

use Doctrine\ORM\EntityRepository;
use PidevBundle\Entity\Babysitter;
use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\User;
use PidevBundle\PidevBundle;
use PidevBundle\Repository\UserRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\RadioType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class BabysitterType extends AbstractType
{

    const CHOIX1 = 'choix 1';
    const CHOIX2 = 'choix 2';
    const CHOIX3 = 'choix 3';
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {

                      $var=array(1 => 'Oui', 0 => 'Non', 'input'=>'string');

                      $s=implode("",array(1 => 'Oui', 0 => 'Non', 'input'=>'string'));
        $choices = [
            'texte 1' => 'valeur1',
            'texte 2' => 'valeur2'
        ];
        $propertyValues = [$choices];
        parent::buildForm($builder, $options);
        $builder->add('heuredebut', TimeType::class,array(
            'input'  => 'timestamp',
            'widget' => 'choice',
        ))->add('heurefin', TimeType::class,array(
            'input'  => 'timestamp',
            'widget' => 'choice',
        ))->add('prixheure')->add('idEnfant', EntityType::class,
            array(
                'class' => Enfant::class,
                'choice_label' => 'nom',



            ))->add('idbabysitter', EntityType::class,
           array(
                'class' => User::class,
               'choice_label' => 'username',
               'query_builder' => function(EntityRepository $er) use ($options){
                   return $er->createQueryBuilder('u')->where('u.typeemploye = :typeemploye')->setParameter('typeemploye', 'BabySitter');}


        ))->add('modifier',SubmitType::class);


    }




    /**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Babysitter'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_babysitter';
    }

    public function value($choices)
    {
        foreach($choices as $value){
            //Print the element out.
            echo $value, '<br>';
        }
    }
}

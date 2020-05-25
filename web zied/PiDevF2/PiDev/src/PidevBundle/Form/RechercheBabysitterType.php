<?php
/**
 * Created by PhpStorm.
 * User: HEDI MSELMI
 * Date: 30/03/2020
 * Time: 10:51
 */

namespace PidevBundle\Form;



use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilder;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;



class RechercheBabysitterType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder-> add('joursemaine')
            ->add('idEnfant',EntityType::class,array(
                'class'=>'PidevBundle:Enfant',
                'choice_label'=>'nom',
                'multiple'=>false
            ))->add('submit',SubmitType::class);


    }/**
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

}
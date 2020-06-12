<?php

namespace PidevBundle\Form;

use Doctrine\ORM\EntityRepository;
use PidevBundle\Entity\Chaise;
use PidevBundle\Entity\Enfant;
use PidevBundle\Entity\TableCantine;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReserverType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $enfants=$options['enfants'];
        $builder->add('idEnfant',ChoiceType::class,[
            //'class'=>Enfant::class,
            /*'query_builder' => function (EntityRepository $er,$idUser) {
                return $er->createQueryBuilder('e')
                    ->where('e.cantine>:c')->andWhere('e.idParent=:p')
                    ->setParameter('c',true)
                    ->setParameter('p',$idUser);
            },*/
            'choices'=>$enfants,
            /*'choice_label'=>function ($enfant) {
                return $enfant->getPrenom().$enfant->getNom();
            }*/
        ])->add('Valider',SubmitType::class);
    }/**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\MenuCommande',
            'enfants'=>null
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_chaise';
    }


}

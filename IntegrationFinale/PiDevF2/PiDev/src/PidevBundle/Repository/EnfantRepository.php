<?php
/**
 * Created by PhpStorm.
 * User: HEDI MSELMI
 * Date: 05/04/2020
 * Time: 14:18
 */

namespace PidevBundle\Repository;


use Doctrine\ORM\EntityRepository;

class EnfantRepository Extends EntityRepository
{
    public function UpdateStatus($id)
    {
        $query=$this->getEntityManager()->createQuery('UPDATE  PidevBundle:Enfant s SET s.status =:tt WHERE s.idEnfant= :id ');
        $query->setParameter('tt','valide');
        $query->setParameter('id',$id);
        return $query->getResult();
    }

    public function AfficherNomEnfant($nom)
    {
        $q=$this->getEntityManager()->createQuery('SELECT t FROM PidevBundle:Enfant t where t.nom = :nom ');
        $q->setParameter('nom',$nom);
        return $q->getResult();
    }
    public function AfficherNomSelonIdEnfant($idEnfant)
    {
        $q=$this->getEntityManager()->createQuery('SELECT t FROM PidevBundle:Enfant t where t.idEnfant = :idEnfant ');
        $q->setParameter('idEnfant',$idEnfant);
        return $q->getResult();
    }
}
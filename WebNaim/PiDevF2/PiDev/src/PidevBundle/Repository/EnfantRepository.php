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
}
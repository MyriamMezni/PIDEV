<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
class MenuRepository extends EntityRepository
{
     public function AfficherMenusParJour($jour)
     {
         $q=$this->getEntityManager()->createQuery('SELECT m FROM PidevBundle:Menu m where m.jourDeLaSemaine LIKE :j')->setParameter("j","%".$jour."%");
         return $q->getResult();
     }
    public function AfficherNbMenusParJour($jour)
    {
        $q=$this->getEntityManager()->createQuery('SELECT count(m) FROM PidevBundle:MenuCommande m where m.jourDeLaSemaine LIKE :j')->setParameter("j","%".$jour."%");
        return $q->getResult();
    }
    public function StatSurLesCommandes()
    {
        //$q=$this->getEntityManager()->createNativeQuery('select m.*,(select length(group_concat(mc.jour_de_la_semaine seperator "")) from menu_commande mc where mc.idMenu=m.id) from menu m');
        //$q=$this->getEntityManager()->createQuery('SELECT m,(select length(group_concat(mc.jourDeLaSemaine seperator "")) from PidevBundle:MenuCommande mc where mc.idMenu=m.id) FROM PidevBundle:Menu m');
        $q=$this->getEntityManager()->createQuery("select m,(select count(mc) from PidevBundle:MenuCommande mc where mc.idMenu=m.id) from PidevBundle:Menu m");
        //$q=$this->getEntityManager()->createQuery("select m,length(m.jourDeLaSemaine) from PidevBundle:MenuCommande m");
        return $q->getResult();
    }
}
<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
class CommentaireRepository extends EntityRepository
{
    public function AfficherCommantairesEtUserEtSujet($id)
    {
        $q=$this->getEntityManager()->createQuery('SELECT c,u,s FROM PidevBundle:Commentaire c JOIN c.idUser u JOIN c.idSujet s WHERE c.idSujet=:id ')
        ->setParameter('id',$id);
        return $q->getResult();
    }

    public function AfficherUsersDuSujet($sujet)
    {
        $q=$this->getEntityManager()->createQuery('SELECT c.idUser FROM PidevBundle:Commentaire c WHERE c.idSujet=:sujet ')
            ->setParameter('sujet',$sujet);
        return $q->getResult();
    }
}

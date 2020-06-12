<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="fk_commentaire_user", columns={"id_user"}), @ORM\Index(name="fk_commentaire_sujet", columns={"id_sujet"})})
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\CommentaireRepository")
 */
class Commentaire
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Texte", type="string", length=10000, nullable=false)
     */
    private $texte;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateC", type="datetime", nullable=false)
     */
    private $datec;

    /**
     * @var \Sujet
     *
     * @ORM\ManyToOne(targetEntity="Sujet")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_sujet", referencedColumnName="id")
     * })
     */
    private $idSujet;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="IdUser")
     * })
     */
    private $idUser;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getTexte()
    {
        return $this->texte;
    }

    /**
     * @param string $texte
     */
    public function setTexte($texte)
    {
        $this->texte = $texte;
    }

    /**
     * @return \DateTime
     */
    public function getDatec()
    {
        return $this->datec;
    }

    /**
     * @param \DateTime $datec
     */
    public function setDatec($datec)
    {
        $this->datec = $datec;
    }

    /**
     * @return \Sujet
     */
    public function getIdSujet()
    {
        return $this->idSujet;
    }

    /**
     * @param \Sujet $idSujet
     */
    public function setIdSujet($idSujet)
    {
        $this->idSujet = $idSujet;
    }

    /**
     * @return \User
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * @param \User $idUser
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;
    }


}


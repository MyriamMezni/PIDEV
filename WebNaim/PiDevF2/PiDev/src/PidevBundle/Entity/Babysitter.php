<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Babysitter
 *
 * @ORM\Table(name="babysitter", indexes={@ORM\Index(name="id_enfant", columns={"id_enfant"})})
 * @ORM\Entity
 */
class Babysitter
{
    /**
     * @var integer
     *
     * @ORM\Column(name="heureDebut", type="integer", nullable=false)
     */
    private $heuredebut;

    /**
     * @var integer
     *
     * @ORM\Column(name="heureFin", type="integer", nullable=false)
     */
    private $heurefin;

    /**
     * @var string
     *
     * @ORM\Column(name="jourSemaine", type="string", length=255, nullable=false)
     */
    private $joursemaine;

    /**
     * @var integer
     *
     * @ORM\Column(name="prixHeure", type="integer", nullable=false)
     */
    private $prixheure;

    /**
     * @var \Enfant
     *
     * @ORM\ManyToOne(targetEntity="Enfant")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_enfant", referencedColumnName="id_enfant")
     * })
     */
    private $idEnfant;

    /**
     * @var \User
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idBabysitter", referencedColumnName="IdUser")
     * })
     */
    private $idbabysitter;
    private $commentairesNonLus;

    public function __construct() {
        $this->commentairesNonLus = array();
    }

    /**
     * @return int
     */
    public function getHeuredebut ()
    {
        return $this->heuredebut;
    }

    /**
     * @param int $heuredebut
     */
    public function setHeuredebut ( $heuredebut )
    {
        $this->heuredebut = $heuredebut;
    }

    /**
     * @return int
     */
    public function getHeurefin ()
    {
        return $this->heurefin;
    }

    /**
     * @param int $heurefin
     */
    public function setHeurefin ( $heurefin )
    {
        $this->heurefin = $heurefin;
    }

    /**
     * @return string
     */
    public function getJoursemaine ()
    {
        return $this->joursemaine;
    }

    /**
     * @param string $joursemaine
     */
    public function setJoursemaine ( $joursemaine )
    {
        $this->joursemaine = $joursemaine;
    }

    /**
     * @return int
     */
    public function getPrixheure ()
    {
        return $this->prixheure;
    }

    /**
     * @param int $prixheure
     */
    public function setPrixheure ( $prixheure )
    {
        $this->prixheure = $prixheure;
    }

    /**
     * @return \Enfant
     */
    public function getIdEnfant ()
    {
        return $this->idEnfant;
    }

    /**
     * @param \Enfant $idEnfant
     */
    public function setIdEnfant ( $idEnfant )
    {
        $this->idEnfant = $idEnfant;
    }

    /**
     * @return \User
     */
    public function getIdbabysitter ()
    {
        return $this->idbabysitter;
    }

    /**
     * @param \User $idbabysitter
     */
    public function setIdbabysitter ( $idbabysitter )
    {
        $this->idbabysitter = $idbabysitter;
    }

    /**
     * @return array
     */
    public function getCommentairesNonLus ()

    {
        return $this->getCommentairesNonLus();
    }

    /**
     * @param array $commentairesNonLus
     */
    public function setCommentairesNonLus ( $commentairesNonLus )
    {
        $this->commentairesNonLus = $commentairesNonLus;
    }
    public function addCommentaireNonLus( $commentaire)
    {

        $this->commentairesNonLus[] = $commentaire;
    }


}


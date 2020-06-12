<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Activite
 *
 * @ORM\Table(name="activite")
 * @ORM\Entity
 */
class Activite
{
    /**
     * @var integer
     *
     * @ORM\Column(name="IdActivite", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idactivite;

    /**
     * @var string
     *
     * @ORM\Column(name="Intitule", type="string", length=30, nullable=false)
     */
    private $intitule;

    /**
     * @var string
     *
     * @ORM\Column(name="Niveau", type="string", length=30, nullable=false)
     */
    private $niveau;

    /**
     * @var string
     *
     * @ORM\Column(name="Responsable", type="string", length=30, nullable=false)
     */
    private $responsable;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=30, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="HeureDebut", type="text", nullable=false)
     */
    private $heuredebut;

    /**
     * @var string
     *
     * @ORM\Column(name="HeureFin", type="string", length=30, nullable=false)
     */
    private $heurefin;

    /**
     * Activite constructor.
     * @param int $idactivite
     * @param string $intitule
     * @param string $niveau
     * @param string $responsable
     * @param string $type
     * @param string $heuredebut
     * @param string $heurefin
     */


    /**
     * @return int
     */
    public function getIdactivite()
    {
        return $this->idactivite;
    }

    /**
     * @param int $idactivite
     */


    /**
     * @return string
     */
    public function getIntitule()
    {
        return $this->intitule;
    }

    /**
     * @param string $intitule
     */
    public function setIntitule($intitule)
    {
        $this->intitule = $intitule;
    }

    /**
     * @return string
     */
    public function getNiveau()
    {
        return $this->niveau;
    }

    /**
     * @param string $niveau
     */
    public function setNiveau($niveau)
    {
        $this->niveau = $niveau;
    }

    /**
     * @return string
     */
    public function getResponsable()
    {
        return $this->responsable;
    }

    /**
     * @param string $responsable
     */
    public function setResponsable($responsable)
    {
        $this->responsable = $responsable;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getHeuredebut()
    {
        return $this->heuredebut;
    }

    /**
     * @param string $heuredebut
     */
    public function setHeuredebut($heuredebut)
    {
        $this->heuredebut = $heuredebut;
    }

    /**
     * @return string
     */
    public function getHeurefin()
    {
        return $this->heurefin;
    }

    /**
     * @param string $heurefin
     */
    public function setHeurefin($heurefin)
    {
        $this->heurefin = $heurefin;
    }
    public function __toString()
    {
        return (string) $this->getHeurefin();
    }


}


<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Programme
 *
 * @ORM\Table(name="programme")
 * @ORM\Entity
 */
class Programme
{
    /**
     * @var integer
     *
     * @ORM\Column(name="IdProgramme", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idprogramme;

    /**
     * @var integer
     *
     * @ORM\ManyToOne(targetEntity="Activite")
     * @ORM\JoinColumn(name="IdActivite",referencedColumnName="IdActivite", onDelete="CASCADE")
     *
     */

    private $idactivite;

    /**
     * @return int
     */
    public function getIdprogramme()
    {
        return $this->idprogramme;
    }

    /**
     * @param int $idprogramme
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
    public function setIdactivite($idactivite)
    {
        $this->idactivite = $idactivite;
    }

    /**
     * @return string
     */
    public function getCours()
    {
        return $this->cours;
    }

    /**
     * @param string $cours
     */
    public function setCours($cours)
    {
        $this->cours = $cours;
    }

    /**
     * @return string
     */
    public function getDebut()
    {
        return $this->debut;
    }

    /**
     * @param string $debut
     */
    public function setDebut($debut)
    {
        $this->debut = $debut;
    }

    /**
     * @return string
     */
    public function getFin()
    {
        return $this->fin;
    }

    /**
     * @param string $fin
     */
    public function setFin($fin)
    {
        $this->fin = $fin;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="Cours", type="text", nullable=false)
     */
    private $cours;

    /**
     * @var string
     *
     * @ORM\Column(name="Debut", type="string", length=30, nullable=false)
     */
    private $debut;

    /**
     * @var string
     *
     * @ORM\Column(name="Fin", type="string", length=30, nullable=false)
     */
    private $fin;










}


<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Ratingactivite
 *
 * @ORM\Table(name="ratingactivite")
 * @ORM\Entity
 */
class Ratingactivite
{
    /**
     * @var integer
     *
     * @ORM\Column(name="IdRate", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrate;

    /**
     * @var string
     *
     * @ORM\Column(name="Intitule", type="string", length=25, nullable=false)
     */
    private $intitule;

    /**
     * @var integer
     *
     * @ORM\Column(name="Rate", type="integer", nullable=false)
     */
    private $rate;

    /**
     * @return int
     */
    public function getIdrate()
    {
        return $this->idrate;
    }

    /**
     * @param int $idrate
     */
    public function setIdrate($idrate)
    {
        $this->idrate = $idrate;
    }

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
     * @return int
     */
    public function getRate()
    {
        return $this->rate;
    }

    /**
     * @param int $rate
     */
    public function setRate($rate)
    {
        $this->rate = $rate;
    }


}


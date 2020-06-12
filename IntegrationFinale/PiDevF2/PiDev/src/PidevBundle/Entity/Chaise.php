<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Chaise
 *
 * @ORM\Table(name="chaise", indexes={@ORM\Index(name="fk_enfant_chaise", columns={"id_enfant"}), @ORM\Index(name="fk_table_chaise", columns={"num_table"})})
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\ChaiseRepository")
 */
class Chaise
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_chaise", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idChaise;

    /**
     * @var string
     *
     * @ORM\Column(name="etat_plat", type="string", length=200, nullable=false)
     */
    private $etatPlat;

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
     * @var \TableCantine
     *
     * @ORM\ManyToOne(targetEntity="TableCantine")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="num_table", referencedColumnName="Id_table")
     * })
     */
    private $numTable;

    /**
     * @return int
     */
    public function getIdChaise()
    {
        return $this->idChaise;
    }

    /**
     * @param int $idChaise
     */
    public function setIdChaise($idChaise)
    {
        $this->idChaise = $idChaise;
    }

    /**
     * @return string
     */
    public function getEtatPlat()
    {
        return $this->etatPlat;
    }

    /**
     * @param string $etatPlat
     */
    public function setEtatPlat($etatPlat)
    {
        $this->etatPlat = $etatPlat;
    }

    /**
     * @return \Enfant
     */
    public function getIdEnfant()
    {
        return $this->idEnfant;
    }

    /**
     * @param \Enfant $idEnfant
     */
    public function setIdEnfant($idEnfant)
    {
        $this->idEnfant = $idEnfant;
    }

    /**
     * @return \TableCantine
     */
    public function getNumTable()
    {
        return $this->numTable;
    }

    /**
     * @param \TableCantine $numTable
     */
    public function setNumTable($numTable)
    {
        $this->numTable = $numTable;
    }




}


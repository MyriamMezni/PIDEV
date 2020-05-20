<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * TableCantine
 *
 * @ORM\Table(name="table_cantine")
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\TableCantineRepository")
 */
class TableCantine
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id_table", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idTable;

    /**
     * @var integer
     *
     * @ORM\Column(name="capacite", type="integer", nullable=false)
     */
    private $capacite;

    /**
     * @return int
     */
    public function getIdTable()
    {
        return $this->idTable;
    }

    /**
     * @param int $idTable
     */
    public function setIdTable($idTable)
    {
        $this->idTable = $idTable;
    }

    /**
     * @return int
     */
    public function getCapacite()
    {
        return $this->capacite;
    }

    /**
     * @param int $capacite
     */
    public function setCapacite($capacite)
    {
        $this->capacite = $capacite;
    }




}


<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Mailemp
 *
 * @ORM\Table(name="mailemp")
 * @ORM\Entity
 */
class Mailemp
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="subject", type="string", length=255, nullable=false)
     */
    private $subject;

    /**
     * @var string
     *
     * @ORM\Column(name="mail", type="string", length=255, nullable=false)
     */
    private $mail;

    private $sujet;

    /**
     * @return mixed
     */
    public function getSujet ()
    {
        return $this->sujet;
    }

    /**
     * @param mixed $sujet
     */
    public function setSujet ( $sujet )
    {
        $this->sujet = $sujet;
    }


    /**
     * @var string
     *
     * @ORM\Column(name="object", type="string", length=255, nullable=false)
     */
    private $object;

    /**
     * @return int
     */
    public function getId ()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId ( $id )
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getSubject ()
    {
        return $this->subject;
    }

    /**
     * @param string $subject
     */
    public function setSubject ( $subject )
    {
        $this->subject = $subject;
    }

    /**
     * @return string
     */
    public function getMail ()
    {
        return $this->mail;
    }

    /**
     * @param string $mail
     */
    public function setMail ( $mail )
    {
        $this->mail = $mail;
    }

    /**
     * @return string
     */
    public function getObject ()
    {
        return $this->object;
    }

    /**
     * @param string $object
     */
    public function setObject ( $object )
    {
        $this->object = $object;
    }

    /**
     * @return string
     */



}

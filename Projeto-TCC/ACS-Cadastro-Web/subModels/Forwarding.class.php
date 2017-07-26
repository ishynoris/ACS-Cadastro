<?php

class Forwarding
{
    const FORWARDING = "FORWARDING";
    const IN_DAY = "IN_DAY";
    const SPECIALIZED_SERVICE = "SPECIALIZED_SERVICE";
    const CAPS = "CAPS";
    const HOSPITALIZATION = "HOSPITALIZATION";
    const URGENCY = "URGENCY";
    const HOME_CARE_SERVICE = "HOME_CARE_SERVICE";
    const INTERSECTORAL = "INTERSECTORAL";

    private $inDay, $specializedService, $caps, $hospitalization, $urgency, $homeCareService, $intersectorial;

    public function __construct($inDay, $specializedService, $caps, $hospitalization, $urgency, $homeCareService, $intersectorial)
    {
        $this->inDay = $inDay;
        $this->specializedService = $specializedService;
        $this->caps = $caps;
        $this->hospitalization = $hospitalization;
        $this->urgency = $urgency;
        $this->homeCareService = $homeCareService;
        $this->intersectorial = $intersectorial;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(self::IN_DAY => $this->inDay,
            self::SPECIALIZED_SERVICE => $this->specializedService,
            self::CAPS => $this->caps,
            self::HOSPITALIZATION => $this->hospitalization,
            self::URGENCY => $this->urgency,
            self::HOME_CARE_SERVICE => $this->homeCareService,
            self::INTERSECTORAL => $this->intersectorial);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new Forwarding($array[self::IN_DAY], $array[self::SPECIALIZED_SERVICE], $array[self::CAPS],
            $array[self::HOSPITALIZATION], $array[self::URGENCY], $array[self::HOME_CARE_SERVICE], $array[self::INTERSECTORAL]);
    }

}
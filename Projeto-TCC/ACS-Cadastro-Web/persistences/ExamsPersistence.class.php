<?php

class ExamsPersistence
{

    public static function insert(AcsDataBase $db, ExamsModel $exams)
    {
        $query = array(ExamsModel::EXAMS => self::insertExams(),
            EvaluatedExams::EVALUATED_EXAMS => self::insertEvaluatedExams(),
            RequestExams::REQUEST_EXAMS => self::insertRequestExams());
        return $exams->save($db, $query);
    }

    private static function insertExams()
    {
        return "INSERT INTO TB_EXAMS(PIC, ID_REQUEST_EXAMS, ID_EVALUATED_EXAMS) 
                VALUES (:PIC, :ID_REQUEST_EXAMS, :ID_EVALUATED_EXAMS)";
    }

    private static function insertEvaluatedExams()
    {
        return "INSERT INTO TB_EVALUATED_EXAMS(TOTAL_CHOLESTEROL, CREATINE, EAS_EQU, ELECTROCARDIOGRAM, HEMOGLOBIN, 
                  SPIROMETRY, SPUTUM, GLYCEMIA, HDL, GLYCEMIC_HEMOGLIBIN, BLOOD_COUNT, LDL, EYES, SYPHILIS, DENGUE, HIV, 
                  HUMAN_ANTIGLOBULIN, EAR_TEST, PREGNANCY_TEST, EYE_TEST, FOOT_TEST, ULTRASONOGRAPHY, UROCULTURE) 
                VALUES (:TOTAL_CHOLESTEROL, :CREATINE, :EAS_EQU, :ELECTROCARDIOGRAM, :HEMOGLOBIN, 
                  :SPIROMETRY, :SPUTUM, :GLYCEMIA, :HDL, :GLYCEMIC_HEMOGLIBIN, :BLOOD_COUNT, :LDL, :EYES, :SYPHILIS, :DENGUE, :HIV, 
                  :HUMAN_ANTIGLOBULIN, :EAR_TEST, :PREGNANCY_TEST, :EYE_TEST, :FOOT_TEST, :ULTRASONOGRAPHY, :UROCULTURE)";
    }

    private static function insertRequestExams()
    {
        return "INSERT INTO TB_REQUEST_EXAMS(TOTAL_CHOLESTEROL, CREATINE, EAS_EQU, ELECTROCARDIOGRAM, HEMOGLOBIN, 
                  SPIROMETRY, SPUTUM, GLYCEMIA, HDL, GLYCEMIC_HEMOGLIBIN, BLOOD_COUNT, LDL, EYES, SYPHILIS, DENGUE, HIV, 
                  HUMAN_ANTIGLOBULIN, EAR_TEST, PREGNANCY_TEST, EYE_TEST, FOOT_TEST, ULTRASONOGRAPHY, UROCULTURE) 
                VALUES (:TOTAL_CHOLESTEROL, :CREATINE, :EAS_EQU, :ELECTROCARDIOGRAM, :HEMOGLOBIN, 
                  :SPIROMETRY, :SPUTUM, :GLYCEMIA, :HDL, :GLYCEMIC_HEMOGLIBIN, :BLOOD_COUNT, :LDL, :EYES, :SYPHILIS, :DENGUE, :HIV, 
                  :HUMAN_ANTIGLOBULIN, :EAR_TEST, :PREGNANCY_TEST, :EYE_TEST, :FOOT_TEST, :ULTRASONOGRAPHY, :UROCULTURE)";
    }
}
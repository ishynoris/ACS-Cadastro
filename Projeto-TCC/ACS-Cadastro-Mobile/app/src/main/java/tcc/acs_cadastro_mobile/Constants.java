package tcc.acs_cadastro_mobile;

import tcc.acs_cadastro_mobile.subModels.Anthropometric;

public class Constants {

    public enum Citizen {
        DESCRIPTION, ANOTHER, DONT_KNOWN,

        //StreetSituationModel.class
        STREET_SITUATION, STREET, BENEFIT, FAMILY,
        HYGIENE, BATH, SANITARY, ORAL,
        FAMILY_VISIT,
        ANOTHER_INSTITUTION,
        FEEDING, FOOD_PER_DAY, RESTAURANT, RESTAURANT_DONATION, RELIGIOUS_GROUP, POPULAR,

        //HealthConditionsModel.class
        HEALTH_CONDITIONS, WEIGHT,
        PLANT,
        INTERMENT,
        RESPIRATORY_DISEASE, ASTHMA, EMPHYSEMA,
        KIDNEY_DISEASE, INSUFFICIENCY,
        HEART_DISEASE,
        DISEASES, SMOKER, HYPERTENSION, ALCOHOL, DRUGS, DIABETES, AVC, HEART_ATTACK, LEPROSY, TUBERCULOSIS,
        CANCER, IN_BED, DOMICILED, OTHER_PRACTICES, MENTAL_HEALTH,
        PREGNANT,

        //SocialDemographicModel.class
        TB_DEFICIENCY, DEFICIENCY, HEARING, VISUAL, INTELLECTUAL, PHYSICAL,
        SEXUAL_ORIENTATION1, SEXUAL_ORIENTATION,
        COMMUNITY_TRADITIONAL1, COMMUNITY_TRADITIONAL,
        HEALTH_GROUP, CAREGIVER, COMMUNITY_GROUP, HEALTH_PLAN,
        EDUCATION_EMPLOYMENT, SCHOOL, OCCUPATION, EDUCATION, EMPLOYMENT,
        SOCIAL_DEMOGRAPHIC, KINSHIP, KIDS_09,

        //PersonalDataModel.class
        CONTACT, PHONE, EMAIL,
        TB_NATIONALITY, NATIONALITY, NATION_BIRTH, UF, CITY,
        GENDER_RACE, GENDER, RACE,
        TB_RESPONSIBLE, RESPONSIBLE, NUM_SUS, BIRTH_DATE,
        MOTHER, KNOWN, NAME,
        PARTICULAR, NUM_NIS, SOCIAL_NAME,
        PERSONAL_DATA
    }

    public enum Accompany {

        //RecordDataModel.class
        RECORD_DETAILS, RECORD, PLACE_CARE, TYPE_CARE, SHIFT,
        ANTHROPOMETRIC, WEIGHT, HEIGHT, VACCINATES,
        KID_PREGNANT, BREAST_FEEDING, DUM, PLANNED_PREGNANCY, WEEKS, PREVIOUS, CHILD_BIRTH, HOME_CARE,

        //ConditionsModel.class


        RECORD_DATA, ;

    }
}
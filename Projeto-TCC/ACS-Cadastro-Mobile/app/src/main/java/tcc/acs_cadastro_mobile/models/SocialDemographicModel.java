package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.CommunityTraditional;
import tcc.acs_cadastro_mobile.subModels.Deficiency;
import tcc.acs_cadastro_mobile.subModels.EducationEmployment;
import tcc.acs_cadastro_mobile.subModels.HealthAndGroup;
import tcc.acs_cadastro_mobile.subModels.SexualOrientation;

public class SocialDemographicModel extends RealmObject implements Serializable {

    private String kinship, kids09;
    private EducationEmployment educationEmployment;
    private HealthAndGroup healthAndGroup;
    private CommunityTraditional communityTraditional;
    private SexualOrientation sexualOrientation;
    private Deficiency deficiency;


    public static SocialDemographicModel newInstance(Realm realm, String kinship, EducationEmployment educationEmployment,
            HealthAndGroup healthAndGroup, String kids09, CommunityTraditional communityTraditional,
             SexualOrientation sexualOrientation, Deficiency deficiency){

        realm.beginTransaction();
        SocialDemographicModel model = realm.createObject(SocialDemographicModel.class);
        model.setKinship(kinship);
        model.setEducationEmployment(educationEmployment);
        model.setHealthAndGroup(healthAndGroup);
        model.setKids09(kids09);
        model.setCommunityTraditional(communityTraditional);
        model.setSexualOrientation(sexualOrientation);
        model.setDeficiency(deficiency);
        realm.commitTransaction();
        return model;
    }

    public static EducationEmployment getEducationEmployment(Realm realm, String occupation,
                                                             String education, String employment) {
        realm.beginTransaction();
        EducationEmployment object = realm.createObject(EducationEmployment.class);
        object.setOccupation(occupation);
        object.setEducation(education);
        object.setEmployment(employment);
        realm.commitTransaction();
        return object;
    }

    public static HealthAndGroup getHealthAndGroup(Realm realm, boolean caregiver, boolean communityGroup,
                                                   boolean healthPlan) {
        realm.beginTransaction();
        HealthAndGroup object = realm.createObject(HealthAndGroup.class);
        object.setCaregiver(caregiver);
        object.setCommunityGroup(communityGroup);
        object.setHealthPlan(healthPlan);
        realm.commitTransaction();
        return object;
    }

    public static CommunityTraditional getCommunityTraditional(Realm realm, boolean isCommunityTraditional,
                                                               String value) {
        realm.beginTransaction();
        CommunityTraditional object = realm.createObject(CommunityTraditional.class);
        object.setCommunityTraditional(isCommunityTraditional);
        object.setValue(value);
        realm.commitTransaction();
        return object;
    }

    public static SexualOrientation getSexualOrientation(Realm realm, boolean isOrientation,
                                                         String value) {
        realm.beginTransaction();
        SexualOrientation object = realm.createObject(SexualOrientation.class);
        object.setSexualOrientation(isOrientation);
        object.setValue(value);
        realm.commitTransaction();
        return object;
    }

    public static Deficiency getDeficiency(Realm realm, boolean isDeficiency, boolean[] deficiencys) {
        realm.beginTransaction();
        Deficiency object = realm.createObject(Deficiency.class);
        object.setDeficiency(isDeficiency);
        object.setDeficiencys(deficiencys);
        realm.commitTransaction();
        return object;
    }


    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }

    public EducationEmployment getEducationEmployment() {
        return educationEmployment;
    }

    public void setEducationEmployment(EducationEmployment educationEmployment) {
        this.educationEmployment = educationEmployment;
    }

    public String getKids09() {
        return kids09;
    }

    public void setKids09(String kids09) {
        this.kids09 = kids09;
    }

    public HealthAndGroup getHealthAndGroup() {
        return healthAndGroup;
    }

    public void setHealthAndGroup(HealthAndGroup healthAndGroup) {
        this.healthAndGroup = healthAndGroup;
    }

    public CommunityTraditional getCommunityTraditional(){
        return communityTraditional;
    }

    public void setCommunityTraditional(CommunityTraditional communityTraditional) {
        this.communityTraditional = communityTraditional;
    }

    public SexualOrientation getSexualOrientation(){
        return sexualOrientation;
    }

    public void setSexualOrientation(SexualOrientation sexualOrientation){
        this.sexualOrientation = sexualOrientation;
    }

    public Deficiency getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(Deficiency deficiency){
        this.deficiency = deficiency;
    }

    public String getOccupation() {
        return getEducationEmployment().getOccupation();
    }

    public boolean isSchool() {
        return getEducationEmployment().isSchool();
    }

    public String getEducation() {
        return getEducationEmployment().getEducation();
    }

    public String getEmployment() {
        return getEducationEmployment().getEmployment();
    }

    public String getKids() {
        return kids09;
    }

    public boolean isCaregiver() {
        return getHealthAndGroup().isCaregiver();
    }

    public boolean isCommunityGroup() {
        return getHealthAndGroup().isCommunityGroup();
    }

    public boolean isHealthPlan() {
        return healthAndGroup.isHealthPlan();
    }

    public boolean isCommunityTraditional() {
        return getCommunityTraditional().isCommunityTraditional();
    }

    public String getCommunityName() {
        return getCommunityTraditional().getValue();
    }

    public boolean isSexualOrientation() {
        return getSexualOrientation().isSexualOrientation();
    }

    public String getOrientation() {
        return getSexualOrientation().getValue();
    }

    public boolean isDeficiency() {
        return getDeficiency().isDeficiency();
    }

    public boolean[] getDeficiencys(){
        return getDeficiency().getDeficiencys();
    }
}

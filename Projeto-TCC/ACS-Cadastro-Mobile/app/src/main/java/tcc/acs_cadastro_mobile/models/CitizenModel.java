package tcc.acs_cadastro_mobile.models;

import java.util.Random;

public class CitizenModel {

    public static final int INDEX = 0;
    public static final int VALUE = 1;

    private static int count = 1;
    private static final int SUS_NUM = 111111111;

    private String name;
    private int susNum;
    private int numberAddress;

    private PersonalDataModel personalData;
    private SocialDemographicDataModel socioDemographicData;
    private HealthConditionsDataModel healthConditions;
    private StreetSituationDataModel streetSituation;

    public CitizenModel(String name){
        this.name = name;
        this.susNum = SUS_NUM * (count++);
        this.numberAddress = new Random().nextInt(1000);

        this.personalData = new PersonalDataModel();
        this.socioDemographicData = new SocialDemographicDataModel();
        this.healthConditions = new HealthConditionsDataModel();
        this.streetSituation = new StreetSituationDataModel();
    }

    public CitizenModel(PersonalDataModel personalData, SocialDemographicDataModel socioDemographicData,
                        HealthConditionsDataModel healthConditions, StreetSituationDataModel streetSituation){

        this.personalData = personalData;
        this.socioDemographicData = socioDemographicData;
        this.healthConditions = healthConditions;
        this.streetSituation = streetSituation;
    }

    public void save(){

    }

    public boolean containsKey(String key){
        return name.toUpperCase().contains(key.toUpperCase().trim());
    }

    public String getName() {
        return name;
    }

    public String getAddress(){
        return "Rua " + name + " " + numberAddress + ". Bairro";
    }

    public String getSusNum(){
        return "Numero do SUS: " + susNum;
    }

    public String getPhone(){
        return "Telefone: " + susNum /2;
    }

    @Override
    public String toString() {
        return name;
    }
}
package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import java.io.Serializable;


public class StreetSituation extends RealmObject implements Serializable {

    private boolean isStreetSituation;
    private String value;

    public StreetSituation() {
        this(false, "");
    }

    public StreetSituation(boolean isStreetSituation, String value) {
        this.isStreetSituation = isStreetSituation;
        this.value = value;
    }

    public boolean isStreetSituation() {
        return isStreetSituation;
    }

    public void setStreetSituation(boolean streetSituation) {
        isStreetSituation = streetSituation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.STREET_STIUATION.name(), isStreetSituation);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}

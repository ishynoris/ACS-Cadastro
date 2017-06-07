package tcc.acs_cadastro_mobile.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.adapters.Adapter;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;

public class ResidenceStepTwoController extends StepsController {

    private final int RURAL = 2;
    private final int BRICK = 1;
    private final int TAIPA = 2;
    private final int ANOTHER_TYPE = 3;

    private Fragment fragment;
    private AdapterView.OnItemSelectedListener itemSelectedListener;
    private RadioGroup.OnCheckedChangeListener onChangeListener;

    public ResidenceStepTwoController(Fragment fragment) {
        super(fragment);
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener() {
        if (itemSelectedListener == null) {
            itemSelectedListener = new ItemSelectedListener();
        }
        return itemSelectedListener;
    }

    public RadioGroup.OnCheckedChangeListener getOnChangeListener() {

        if (onChangeListener == null) {
            onChangeListener = new OnChangeListener();
        }
        return onChangeListener;
    }

    public HousingConditionsModel.HousingSituation getHousingSituation(Spinner spnHousingSituation,
                                                                       Spinner spnLocation, Spinner spnLocationIf) {
        return new HousingConditionsModel.HousingSituation(getFields(spnHousingSituation),
                getFields(spnLocation), getFields(spnLocationIf));
    }

    public HousingConditionsModel.House getHouse(Spinner spnResidenceType, EditText edtResidents, EditText edtRooms,
                                                 Spinner spnResidenceAccess, Spinner spnResidenceConstruction, Spinner spnConstructionType) {
        String type = getFields(spnResidenceType);
        int nResidents = Integer.parseInt(getFields(edtResidents));
        int nRooms = Integer.parseInt(getFields(edtRooms));
        String access = getFields(spnResidenceAccess);
        String construction = getFields(spnResidenceConstruction);
        String constructionType = getFields(spnConstructionType);
        return new HousingConditionsModel.House(type, nResidents, nRooms, access, construction, constructionType);
    }

    public HousingConditionsModel.WaterAndSanitation getWaterAndSanitation(Spinner spnWaterSupply,
                                                                           Spinner spnWaterTreatment, Spinner spnBathroom) {
        return new HousingConditionsModel.WaterAndSanitation(getFields(spnWaterSupply),
                getFields(spnWaterTreatment), getFields(spnBathroom));
    }

    public boolean getElectricEnergy(RadioGroup rgrpElectricEnergy) {
        return isYesGroup(rgrpElectricEnergy, R.id.rgrp_rsd_electric_energy_y);
    }

    public HousingConditionsModel.Pet getPets(RadioGroup radioGroup, EditText edtPets, CheckBox... checkboxes) {
        boolean hasPet = isYesGroup(radioGroup, R.id.rgrp_rsd_pets_y);
        boolean pets[] = getFields(checkboxes);
        int nPets = Integer.parseInt(getFields(edtPets));
        return new HousingConditionsModel.Pet(hasPet, pets, nPets);
    }

    public int getOwnershipIndex(int locationIndex, String ownership) {
        if (locationIndex == RURAL) {
            getIndex(ownership, R.array.ownership);
        }
        return 0;
    }

    public int getConstructionTypeIndex(int constructionIndex, String constructionType) {

        int array;

        if (constructionIndex == BRICK || constructionIndex == TAIPA) {
            array = R.array.construction_coating;
        } else if (constructionIndex == ANOTHER_TYPE) {
            array = R.array.construction_another;
        } else {
            return 0;
        }
        return getIndex(constructionType, array);
    }

    public void fillElectricEnergy(RadioGroup rgrpElectricEnergy, boolean electricEnergy) {
        fillField(rgrpElectricEnergy, electricEnergy, R.id.rgrp_rsd_electric_energy_y, R.id.rgrp_rsd_electric_energy_n);
    }

    public void fillPets(RadioGroup rgrpPets, boolean hasPets, boolean[] values, CheckBox... checkBoxes) {
        fillField(rgrpPets, hasPets, checkBoxes, values, R.id.rgrp_rsd_pets_y, R.id.rgrp_rsd_pets_n);
    }

    private void enableLocation(int index) {
        boolean enable = index == RURAL;
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_rsd_location_if);
        if (!enable) {
            fillField(spinner, 0);
        }
        enableView(spinner, enable);
    }

    private void enableConstructionType(int index) {

        boolean enable;
        Spinner spinner = (Spinner) fragment.getActivity().findViewById(R.id.spn_rsd_construction_type);

        if (enable = (index == BRICK || index == TAIPA)) {
            spinner.setAdapter(getSpinnerAdapter(R.array.construction_coating));
        } else if (enable = (index == ANOTHER_TYPE)) {
            spinner.setAdapter(getSpinnerAdapter(R.array.construction_another));
        } else {
            fillField(spinner, 0);
        }
        enableView(spinner, enable);
    }

    private void enablePets(int id) {
        boolean enable = id == R.id.rgrp_rsd_pets_y;
        Activity view = fragment.getActivity();
        CheckBox chbCat = (CheckBox) view.findViewById(R.id.chb_rsd_cat);
        CheckBox chbDog = (CheckBox) view.findViewById(R.id.chb_rsd_dog);
        CheckBox chbBird = (CheckBox) view.findViewById(R.id.chb_rsd_bird);
        CheckBox chbBreeding = (CheckBox) view.findViewById(R.id.chb_rsd_breeding);
        CheckBox chbAnother = (CheckBox) view.findViewById(R.id.chb_rsd_pet_another);
        EditText edtPetsTotal = (EditText) view.findViewById(R.id.edt_rsd_pets_total);

        if (!enable) {
            fillField(new boolean[6], chbCat, chbDog, chbBird, chbBreeding, chbAnother);
            fillField(edtPetsTotal, "");
        }
        enableView(enable, chbCat, chbDog, chbBird, chbBreeding, chbAnother);
        enableView(edtPetsTotal, enable);
    }

    private class OnChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            switch (radioGroup.getId()) {
                case R.id.rgrp_rsd_pets:
                    enablePets(id);
                    break;
            }
        }
    }

    private class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
            switch (adapterView.getId()) {
                case R.id.spn_rsd_location:
                    enableLocation(index);
                    break;
                case R.id.spn_rsd_residence_construction:
                    enableConstructionType(index);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}

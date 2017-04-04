package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import tcc.acs_cadastro_mobile.adapters.Adapter;

public class CitizenStepThreeController {

    private Fragment fragment;

    public CitizenStepThreeController(Fragment fragment) {
        this.fragment = fragment;
    }

    public ArrayAdapter<String> getSpinnerAdapter(int arrayResource) {
        return new Adapter(fragment.getContext()).getSpinnerAdapter(arrayResource);
    }
}

package com.example.medievalrebuild;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medievalrebuild.Equipable.Equipable;

import java.util.ArrayList;

public class ShopViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Equipable>> equipablesLiveData = new MutableLiveData<>();


    public LiveData<ArrayList<Equipable>> getEquipablesLiveData(){
        return equipablesLiveData;
    }

    public void setEquipablesLiveData(ArrayList<Equipable> equipables){
        equipablesLiveData.setValue(equipables);
    }

}

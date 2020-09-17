package com.smartrider24.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.Interfaces.Datum;
import com.smartrider24.R;

import java.util.ArrayList;
import java.util.List;

public  class Confirmed extends Fragment {

//    ListAdapter listAdapterC;
    List<Datum> datumListC = new ArrayList<>();
    RecyclerView recyclerView;

    public Confirmed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.layout_fragment_confirmed, container, false);
        recyclerView = view.findViewById(R.id.recyclerConfirm);

        confirmDta();
        return view;
    }


    private void confirmDta() {

        Datum datum = new Datum("1", "3.5", "Bangalore", "Bangalore", "Gurgaon", "Haryana", "BMW", "32 ft mxl", "02:00 PM,Today", "Eletronics Goods", "55 Km.", "3900");
        datumListC.add(datum);
        datum = new Datum("2", "4.5", "Gurgaon", "Haryana", "Noida", "Delhi", "Duster", "32 ft mxl", "12:00 PM,Today", "Servicing Goods", "50 Km.", "3000");
        datumListC.add(datum);
        datum = new Datum("3", "2.5", "Mumbai", "Maharastra", "Pune", "Maharastra", "BMW", "16 ft mxl", "05:00 PM,Tomorrow", "Furnitures", "15 Km.", "1100");
        datumListC.add(datum);
        datum = new Datum("4", "3.0", "Ahemdabad", "Gujarat", "Surat", "Gujarat", "Maruti Suzuki", "36 fr mxl", "07:00 PM,Today", "Wires", "5 Km.", "3000");
        datumListC.add(datum);
//
//        listAdapterC = new ListAdapter(datumListC);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(listAdapterC);
    }
}

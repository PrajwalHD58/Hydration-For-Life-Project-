package com.example.proj_1.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proj_1.R;
import com.example.proj_1.fragmentViewmodel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DashboardFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int a,b,c,d,e,f,t;
    private TextView water, juice, energydrink, coffee,wine ,beer;
    private  PieChart pieChart;
    private fragmentViewmodel viewModel;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view,savedInstanceState);

            water=view.findViewById(R.id.tvR);
            juice= view.findViewById(R.id.tvPython);
            coffee= view.findViewById(R.id.tvCPP);
            energydrink=view.findViewById(R.id.tvJava);
            wine= view.findViewById(R.id.wine);
            beer= view.findViewById(R.id.beer);
            pieChart=view.findViewById(R.id.piechart);



        viewModel = new ViewModelProvider(getActivity()).get(fragmentViewmodel.class);


        viewModel.getWaterdrink().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                a=Integer.parseInt(s);
            }
        });
        viewModel.getMcoffe().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                b=Integer.parseInt(s);
            }
        });
        viewModel.getMenergyd().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                c=Integer.parseInt(s);
            }
        });
        viewModel.getMfruitd().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                d=Integer.parseInt(s);
            }
        });
        viewModel.getMwine().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                e=Integer.parseInt(s);
            }
        });
        viewModel.getMbeer().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                f=Integer.parseInt(s);
            }
        });

        t=a+b+c+d+e+f;
        if(t==0)
        {
            a=60;
            b=10;
            c=10;
            d=10;
            e=5;
            f=5;
        }
        else {
            b = (b * 100) / t;
            c = (c * 100) / t;
            d = (d * 100) / t;
            e = (e * 100) / t;
            f = (f * 100) / t;
            a = 100 - b - c - d - e - f;
        }
        water.setText(Integer.toString(a));
        juice.setText(Integer.toString(b));
        coffee.setText(Integer.toString(c));
        energydrink.setText(Integer.toString(d));
        wine.setText(Integer.toString(e));
        beer.setText(Integer.toString(f));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Water Drink",
                        a,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Fruit Juice",
                        d,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Coffe or Tea",
                        b,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Energy/Sport Drink",
                        c,
                        Color.parseColor("#29B6F6")));

        pieChart.addPieSlice(
                new PieModel(
                        "wine",
                        e,
                        Color.parseColor("#FF000000")));

        pieChart.addPieSlice(
                new PieModel(
                        "Beer",
                        f,
                        Color.parseColor("#FF3700B3")));

        // To animate the pie chart
        pieChart.startAnimation();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Dashboard");
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}

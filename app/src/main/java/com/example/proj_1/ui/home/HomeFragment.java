package com.example.proj_1.ui.home;



import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proj_1.R;
import com.example.proj_1.fragmentViewmodel;
import com.example.proj_1.ui.whetherReportmodel;

import java.nio.channels.Selector;

public class HomeFragment extends Fragment {
    private boolean progressEnable=true;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private fragmentViewmodel viewModel;
    private String prev;
    private int temp2;
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SmsFragment.
     */


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView temp,targettext,targettext2;
        TextView tempmax;
        ImageView weather;
        EditText city;
        Button button;
        TextView state;
        FrameLayout menu1,menu2;
        ProgressBar progressBar;

        viewModel = new ViewModelProvider(getActivity()).get(fragmentViewmodel.class);

        temp=(TextView) view.findViewById(R.id.temp);
        tempmax=(TextView) view.findViewById(R.id.tempmax);
        weather=(ImageView) view.findViewById(R.id.imgWeather);
        city=(EditText) view.findViewById(R.id.cityname);
        button=(Button) view.findViewById(R.id.settarget);
        state=(TextView) view.findViewById(R.id.weatherstate);
        menu1=(FrameLayout) view.findViewById(R.id.popup1);
        menu2=(FrameLayout) view.findViewById(R.id.popup2);
        targettext=(TextView) view.findViewById(R.id.targetid);
        targettext2=(TextView) view.findViewById(R.id.targetid2);
        progressBar=(ProgressBar) view.findViewById(R.id.determinateBar);

            extractdata Extractdata = new extractdata(requireContext());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Extractdata.getforcastbycity(requireContext(), city.getText().toString(), new extractdata.ForcastbyID() {
                        @Override
                        public void onError(String message) {
                            Toast toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT);
                            toast.show();
                        }


                        @Override
                        public void onResponse(whetherReportmodel response) {

                            temp.setText(Float.toString(response.getMin_temp()));
                            viewModel.setMtempmin(Float.toString(response.getMin_temp()));

                            tempmax.setText(Float.toString(response.getMax_temp()));
                            viewModel.setMtempmax(Float.toString(response.getMax_temp()));

                            state.setText(response.getWeather_state_name());
                            viewModel.setMweather(response.getWeather_state_name());

                            String weather_state = response.getWeather_abbr();


                            if (weather_state == "sn" || weather_state == "sl") {
                                weather.setImageResource(R.drawable.snow);
                            } else if (weather_state == "t" || weather_state == "h") {
                                weather.setImageResource(R.drawable.thunder);
                            } else if (weather_state == "hr") {
                                weather.setImageResource(R.drawable.heavyrain);
                            } else if (weather_state == "lr" || weather_state == "s") {
                                weather.setImageResource(R.drawable.lighrain);
                            } else if (weather_state == "hc" || weather_state == "lc") {
                                weather.setImageResource(R.drawable.clouds);
                            } else if (weather_state == "c") {
                                weather.setImageResource(R.drawable.clear);
                            } else {
                                weather.setImageResource(R.drawable.lighrain);
                            }
                        }
                    });
                }
            });


        viewModel.getmweather().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                state.setText(msg);
            }
        });
        viewModel.gettempmax().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                tempmax.setText(msg);
            }
        });

        viewModel.gettempmin().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String msg) {
                temp.setText(msg);
            }
        });
        viewModel.getMwaterd().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                prev = s;
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            int temp3;
            @Override
            public void onClick(View v) {
                temp2=Integer.parseInt(prev);
                viewModel.getWaterdrink().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        temp3=Integer.parseInt(s);
                    }
                });
                PopupMenu popupMenu=new PopupMenu(requireContext(),menu1);
                popupMenu.getMenuInflater().inflate(R.menu.waterdrink,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ml50:
                                String curr=String.valueOf(temp2+50);
                                viewModel.setMwaterd(curr);
                                viewModel.setWaterdrink(String.valueOf(temp3+50));
                                return true;
                            case R.id.ml100:
                                String curr2=String.valueOf(temp2+100);
                                viewModel.setMwaterd(curr2);
                                viewModel.setWaterdrink( String.valueOf(temp3+100));
                                return true;
                            case R.id.ml150:
                                String curr3=String.valueOf(temp2+150);
                                viewModel.setMwaterd(curr3);
                                viewModel.setWaterdrink(String.valueOf(temp3+150));
                                return true;
                            case R.id.ml250:
                                String curr4=String.valueOf(temp2+250);
                                viewModel.setMwaterd(curr4);
                                viewModel.setWaterdrink(String.valueOf(temp3+250));
                                return true;
                            case R.id.ml500:
                                String curr5=String.valueOf(temp2+500);
                                viewModel.setMwaterd(curr5);
                                viewModel.setWaterdrink(String.valueOf(temp3+500));
                                return true;
                            default:
                                String curr6=String.valueOf(temp2+0);
                                viewModel.setMwaterd(curr6);
                                viewModel.setWaterdrink(String.valueOf(temp3+0));
                                return true;
                        }
                    }
                });

                popupMenu.show();//showing popup menu
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            int temp3;
            @Override
            public void onClick(View v) {

                temp2=Integer.parseInt(prev);
                PopupMenu popupMenu=new PopupMenu(requireContext(),menu2);
                popupMenu.getMenuInflater().inflate(R.menu.sugar_drink,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Fruitjuice:
                                viewModel.getMfruitd().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        temp3=Integer.parseInt(s);
                                    }
                                });
                                String curr=String.valueOf(temp2+250);
                                viewModel.setMwaterd(curr);
                                viewModel.setMfruitd(String.valueOf(temp3+250));
                                Log.v(getTag(),String.valueOf(temp3+250));
                                return true;
                            case R.id.Coffe:
                                viewModel.getMcoffe().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        temp3=Integer.parseInt(s);
                                    }
                                });
                                String curr2=String.valueOf(temp2+100);
                                viewModel.setMwaterd(curr2);
                                viewModel.setMcoffe(String.valueOf(temp3+100));
                                return true;
                            case R.id.sportd:
                                viewModel.getMenergyd().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        temp3=Integer.parseInt(s);
                                    }
                                });
                                String curr3=String.valueOf(temp2+250);
                                viewModel.setMwaterd(curr3);
                                viewModel.setMenergyd(String.valueOf(temp3+250));
                                return true;
                            case R.id.wine:
                                viewModel.getMwine().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        temp3=Integer.parseInt(s);
                                    }
                                });
                                String curr4=String.valueOf(temp2+125);
                                viewModel.setMwaterd(curr4);
                                viewModel.setMwine(String.valueOf(temp3+125));
                                return true;
                            case R.id.beer:
                                viewModel.getMbeer().observe(getViewLifecycleOwner(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        temp3=Integer.parseInt(s);
                                    }
                                });
                                String curr5=String.valueOf(temp2+250);
                                viewModel.setMwaterd(curr5);
                                viewModel.setMbeer(String.valueOf(temp3+250));
                                return true;
                            default:
                                return true;
                        }
                    }
                });

                popupMenu.show();//showing popup menu
            }
        });

        viewModel.getMwaterd().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int tar=viewModel.getTarget();
                temp2=Integer.parseInt(s);
                int prog=temp2*100/tar;

                if(temp2<tar)
                {
                    targettext2.setText("Keep it up!");
                }
                else{
                    targettext2.setText("Congratulations : target Completed");
                }
                targettext.setText("Today:- "+s+"ml/"+tar+"ml." );

                progressBar.setProgress((int)prog) ;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");
        return view;
    }


}

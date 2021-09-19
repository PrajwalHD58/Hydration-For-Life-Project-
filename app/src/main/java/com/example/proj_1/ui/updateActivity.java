package com.example.proj_1.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.proj_1.MainActivity;
import com.example.proj_1.R;
import com.example.proj_1.databinding.ActivityMainBinding;
import com.example.proj_1.databinding.UpdateProfileBinding;
import com.example.proj_1.fragmentViewmodel;

public class updateActivity extends AppCompatActivity {

    public EditText name,age,weight,height;
    private Button button;
    fragmentViewmodel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        viewModel = new ViewModelProvider(this).get(fragmentViewmodel.class);
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.iconsback);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        name=(EditText)findViewById(R.id.update_name);
        age=(EditText)findViewById(R.id.update_age);
        weight=(EditText)findViewById(R.id.update_weight);
        height=(EditText)findViewById(R.id.update_height);
        button=(Button)findViewById(R.id.done);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setMname(name.getText().toString());
                viewModel.setMage(age.getText().toString());
                viewModel.setMweight(weight.getText().toString());
                viewModel.setMheight(height.getText().toString());
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(updateActivity.this, ProfileFragment.class));
        return super.onOptionsItemSelected(item);
    }

}

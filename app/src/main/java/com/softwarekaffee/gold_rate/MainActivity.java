package com.softwarekaffee.gold_rate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.softwarekaffee.gold_rate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    final double percentage_of_gold_purity = 4.166;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        TextView txtGoldWeight =findViewById(R.id.txtGoldWeight);
        TextView txtGoldCarate =findViewById(R.id.txtGoldCaret);
        TextView txtGoldMakingCharges =findViewById(R.id.txtMakingCharges);
        TextView txtGoldRate =findViewById(R.id.txtGoldRate);
        TextView txtGsts =findViewById(R.id.txtGstCharges);
        Button btnCalculateGoldRate = findViewById(R.id.btnCalculate);

        btnCalculateGoldRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double goldWeight = Double.parseDouble(txtGoldWeight.getText().toString());
                double goldCarate = Double.parseDouble(txtGoldCarate.getText().toString());
                double goldMakingCharges = Double.parseDouble(txtGoldMakingCharges.getText().toString());
                double goldRate = Double.parseDouble(txtGoldRate.getText().toString());
                double gst = Double.parseDouble(txtGsts.getText().toString());

                double percentage_of_pure_gold  = percentage_of_gold_purity * goldCarate;
                double pure_gold_weight = goldWeight * (percentage_of_pure_gold / 100);
                double pure_gold_rate_per_gm =  pure_gold_weight * goldRate;
                double gold_rate_after_gst = pure_gold_rate_per_gm * gst;
                double final_gold_rate = gold_rate_after_gst + goldMakingCharges;
                String result = String.format("Gold Rate : %.5f", final_gold_rate);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });

    }

}
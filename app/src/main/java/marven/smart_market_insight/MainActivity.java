package marven.smart_market_insight;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import marven.smart_market_insight.databinding.ActivityMainBinding;
import marven.smartmarketinsight.TermsAndConditionsDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fortam in timpul dezvoltarii ca aplicatia sa aiba o tema light sau tema dark
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Verificam daca au fost acceptati Termenii si Conditiile de utilizare
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean termsAccepted = prefs.getBoolean("terms_accepted", false);
        if(!termsAccepted){
            TermsAndConditionsDialog terms=new TermsAndConditionsDialog();
            terms.show(getSupportFragmentManager());
        }
    }

}
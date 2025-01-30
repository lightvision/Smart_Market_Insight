package marven.smartmarketinsight;



import static android.app.PendingIntent.getActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import androidx.navigation.Navigation;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import marven.smartmarketinsight.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        android.util.Log.v("ZZ","GOT ROOT, show the dialog");

        // la prima rulare afisam termenii si conditiile, daca nu au fost acceptati
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean termsAccepted = prefs.getBoolean("terms_accepted", false);



        // meniuri...
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.trendsFragment, R.id.forexFragment, R.id.newsFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if(!termsAccepted){
            TermsAndConditionsDialog terms=new TermsAndConditionsDialog();
            navView.setVisibility(BottomNavigationView.GONE);
            terms.show(getSupportFragmentManager());

        }
        navView.setVisibility(BottomNavigationView.VISIBLE);
        android.util.Log.v("ZZ","It is still running, so basically here we should call for terms");
    }

}

package marven.smartmarketinsight;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import marven.smartmarketinsight.databinding.ActivityMainBinding;
import marven.smartmarketinsight.financial_news.FinancialNewsFragment;
import marven.smartmarketinsight.market_sentiment.MarketSentimentFragment;


public class MainActivity extends AppCompatActivity  {

    // FUNCTIE BUNA DAR NU MI SE SCHIMBA CONTENTUL LA SCHIMBAREA MENIULUI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // SFARSIT FUNCTIE BUNA.

//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//
//        // Setează destinațiile de nivel superior (fără back stack)
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.marketSentimentFragment, R.id.financialNews)
//                .build();
//
//        // Creează NavController pentru gestionarea navigării
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//
//        // Debug: Adaugă un listener pentru a intercepta selecțiile din meniu
//        navView.setOnItemSelectedListener(item -> {
//            // Obține ID-ul elementului selectat
//            int id = item.getItemId();
//
//            // Obține titlul elementului selectat
//            String title = item.getTitle().toString();
//
//            // Afișează detalii în Logcat
//            Log.d("MenuDebug", "Selected ID: " + id);
//            Log.d("MenuDebug", "Selected Title: " + title);
//
//            // Navigare implicită (pasul necesar pentru funcționalitate)
//            return NavigationUI.onNavDestinationSelected(item, navController);
//        });
//    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        // Obține ID-ul și numele opțiunii selectate
//        int id = item.getItemId();
//        String title = item.getTitle().toString();
//
//        // Afișează detalii în consola Logcat
//        Log.d("NavigationDebug", "Selected ID: " + id); // Afișează ID-ul
//        Log.d("NavigationDebug", "Selected Title: " + title); // Afișează titlul elementului
//        return true;
//    }


//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_MarketSentiment)
//                .build();
//
//        NavController navController=Navigation.findNavController(this,R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Inflate layout-ul principal
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Configurare BottomNavigationView
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_MarketSentiment)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_financial_news) {
//            // Navighează către FinancialNewsFragment
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new FinancialNewsFragment())
//                    .addToBackStack(null)
//                    .commit();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        Log.i("Menu", "Click on menu + " + id);
//        return true;
//    }




}
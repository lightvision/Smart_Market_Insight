package marven.smartmarketinsight;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TermsAndConditionsDialog extends DialogFragment {
    private CheckBox acceptCheckbox;
    private Button okButton, exitButton;
    private ScrollView scrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate layout
        View view=inflater.inflate(R.layout.fragment_activity_terms, container, false);

        // Inițializăm elementele UI
        acceptCheckbox = view.findViewById(R.id.accept_checkbox);
        okButton = view.findViewById(R.id.ok_button);
        exitButton = view.findViewById(R.id.exit_button);
        scrollView = view.findViewById(R.id.terms_scroll_view);

        // Inițial butonul OK este dezactivat
        okButton.setEnabled(false);

        // Eveniment Scroll -> Activează checkbox-ul doar dacă s-a ajuns la final
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (!scrollView.canScrollVertically(1)) { // 1 = jos, -1 = sus
                acceptCheckbox.setEnabled(true);
            }
        });

        // Eveniment pe checkbox -> Activează butonul OK doar dacă este bifat
        acceptCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> okButton.setEnabled(isChecked));

        // Eveniment buton OK -> Salvăm în `SharedPreferences` și închidem dialogul
        okButton.setOnClickListener(v -> {
            saveAcceptTerms();
            dismiss();
        });

        // Ataasam eveniment butonului de iesire
        exitButton.setOnClickListener(v -> {
            // inchidem complet aplicatia, utilizatorul nu a acceptat termenii si conditiile de utilizare
            requireActivity().finishAndRemoveTask();
            System.exit(0);
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        return dialog;
    }


    public void show(FragmentManager fragmentManager){
//        FragmentManager fragmentManager = getSupportFragmentManager();
        TermsAndConditionsDialog newFragment = new TermsAndConditionsDialog();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();
    }

    private void saveAcceptTerms(){
        SharedPreferences prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("terms_accepted", true);
        editor.apply();


    }

}

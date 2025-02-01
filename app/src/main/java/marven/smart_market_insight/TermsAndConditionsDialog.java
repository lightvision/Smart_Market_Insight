package marven.smartmarketinsight;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import marven.smart_market_insight.R;

public class TermsAndConditionsDialog extends DialogFragment {
    private CheckBox acceptCheckbox;
    private Button okButton, exitButton;
    private ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.terms_and_conditions_dialog, container, false);

        // Inițializăm elementele UI
        acceptCheckbox = view.findViewById(R.id.accept_terms_checkbox);
        okButton = view.findViewById(R.id.ok_button);
        exitButton = view.findViewById(R.id.exit_button);
        scrollView = view.findViewById(R.id.terms_scroll_view);

        // Inițial butonul OK este dezactivat
        okButton.setEnabled(false);

        // Eveniment Scroll -> Activează checkbox-ul doar dacă s-a ajuns la final
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!scrollView.canScrollVertically(1)) {
                    acceptCheckbox.setEnabled(true);
                    scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                }
            }
        });

        // Eveniment pe checkbox -> Activează butonul OK doar dacă este bifat
        acceptCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> okButton.setEnabled(isChecked));

        // Eveniment buton OK -> Salvăm în `SharedPreferences` și închidem dialogul
        okButton.setOnClickListener(v -> {
            saveAcceptTerms();
            dismiss();
        });

        // Buton de iesire
        exitButton.setOnClickListener(v -> requireActivity().finishAndRemoveTask());

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


    public void show(FragmentManager fragmentManager) {
//        if (!this.isAdded()) {
//            super.show(fragmentManager, "terms_dialog");
//        }
        if (fragmentManager.findFragmentByTag("terms_dialog") == null) {
            super.show(fragmentManager, "terms_dialog");
        }
    }

    private void saveAcceptTerms() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("terms_accepted", true);
        editor.apply();
    }
}

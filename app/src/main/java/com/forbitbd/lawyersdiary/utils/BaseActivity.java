package com.forbitbd.lawyersdiary.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.forbitbd.lawyersdiary.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private GoogleSignInClient client;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
        loadLocale();
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void loadLocale() {
        if (AppPreference.getInstance(this).getLanguage().equals("EN")){
            setLocale("en");
        }else {
            setLocale("bn");
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void setupToolbar(int id){
        Toolbar toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
//        toolbar.inflateMenu(R.menu.bottom_menu);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public GoogleSignInClient getGoogleApiClient() {
        return client;
    }

    public FirebaseAuth getAuth(){
        return mAuth;
    }

    public void signOut(){
        client.signOut();
        mAuth.signOut();
    }

}

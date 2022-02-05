package com.forbitbd.lawyersdiary.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.ui.addcasetype.AddCaseTypeFragment;
import com.forbitbd.lawyersdiary.ui.addclient.AddClientFragment;
import com.forbitbd.lawyersdiary.ui.addcourt.AddCourtFragment;
import com.forbitbd.lawyersdiary.ui.appointment.AppointmentActivity;
import com.forbitbd.lawyersdiary.ui.calender.CalenderActivity;
import com.forbitbd.lawyersdiary.ui.cases.CasesActivity;
import com.forbitbd.lawyersdiary.ui.clients.ClientsActivity;
import com.forbitbd.lawyersdiary.ui.evidence.EvidenceActivity;
import com.forbitbd.lawyersdiary.ui.home.HomeFragment;
import com.forbitbd.lawyersdiary.ui.login.LoginActivity;
import com.forbitbd.lawyersdiary.ui.notification.NotificationFragment;
import com.forbitbd.lawyersdiary.ui.schedule.ScheduleFragment;
import com.forbitbd.lawyersdiary.ui.settings.SettingsFragment;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements MainContract.View, Communicator {

    private MainPresenter mPresenter;
    private Toolbar toolbar;

    private Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        mPresenter.getDashboard(AppPreference.getInstance(this).getLawyer().get_id());

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void logOutFromApp() {
        signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void startAddClientDialog() {
        AddClientFragment addClientFragment = new AddClientFragment();
        addClientFragment.setCancelable(true);
        addClientFragment.show(getSupportFragmentManager(),"jjjjjjjj");
    }

    public void startCalenderActivity() {
        startActivity(new Intent(MainActivity.this, CalenderActivity.class));
    }

    public void startEvidenceActivity() {
        startActivity(new Intent(MainActivity.this, EvidenceActivity.class));
    }

    public void startAppointmentActivity() {
        startActivity(new Intent(MainActivity.this, AppointmentActivity.class));
    }

    public void startCasesActivity() {
        startActivity(new Intent(MainActivity.this, CasesActivity.class));
    }

    public void startClientActivity() {
        startActivity(new Intent(MainActivity.this, ClientsActivity.class));
    }

    public void startAddCourtDialog() {
        AddCourtFragment addCourtFragment = new AddCourtFragment();
        addCourtFragment.setCancelable(true);
        addCourtFragment.show(getSupportFragmentManager(),"kkkkkkkk");
    }

    public void startAddCaseActivity() {
        startAddCaseActivity(dashboard);
    }

    public void startAddCaseTypeDialog() {
        AddCaseTypeFragment caseTypeFragment = new AddCaseTypeFragment();
        caseTypeFragment.setCancelable(true);
        caseTypeFragment.show(getSupportFragmentManager(),"jjjjjjjj");
    }

    @Override
    public void initUI(Dashboard dashboard) {
        this.dashboard = dashboard;
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home){
                    loadFragment(new HomeFragment());
                    toolbar.setTitle(R.string.app_name);
                    return true;
                }else if (id == R.id.schedule){
                    loadFragment(new ScheduleFragment());
                    toolbar.setTitle(R.string.schedule);
                    return true;
                }else if (id == R.id.notification){
                    loadFragment(new NotificationFragment());
                    toolbar.setTitle(R.string.notification);
                    return true;
                }else if (id == R.id.settings){
                    loadFragment(new SettingsFragment());
                    toolbar.setTitle(R.string.settings);
                    return true;
                }
                return false;
            }
        });
    }
}

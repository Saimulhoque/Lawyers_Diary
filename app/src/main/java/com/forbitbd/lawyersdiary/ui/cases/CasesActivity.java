package com.forbitbd.lawyersdiary.ui.cases;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class CasesActivity extends BaseActivity implements CasesContract.View, CasesAdapter.CaseClickListener {

    private CasesPresenter mPresenter;
    private RecyclerView recyclerView;
    private CasesAdapter adapter;
    private ArrayList<Case> casesList;
    private Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        mPresenter = new CasesPresenter(this);
        setupToolbar(R.id.toolbar);
        dashboard = (Dashboard) getIntent().getSerializableExtra(Constant.DASHBOARD);
        String lawyerId = AppPreference.getInstance(this).getLawyer().get_id();
        mPresenter.getCases(lawyerId);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        casesList = new ArrayList<>();
        adapter = new CasesAdapter(casesList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        searchView.setIconifiedByDefault(true);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void addCase(List<Case> cases) {
        for (Case x : cases) {
            adapter.AddCases(x);
        }
    }

    @Override
    public void onEditClick(Case aCase) {

    }

    @Override
    public void onCloseClick(Case aCase) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you want to close the case?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    @Override
    public void onDeleteClick(Case aCase) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you want to delete the case?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    @Override
    public Client getClient(String objectId) {
        for (Client x:dashboard.getClients()){
            if(x.get_id().equals(objectId)){
                return x;
            }
        }
        return null;
    }

    @Override
    public CaseType getCaseType(String objectId) {
        for (CaseType x:dashboard.getCaseTypes()){
            if(x.get_id().equals(objectId)){
                return x;
            }
        }
        return null;
    }
}


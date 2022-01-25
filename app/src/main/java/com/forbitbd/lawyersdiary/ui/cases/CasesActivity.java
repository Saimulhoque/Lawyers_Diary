package com.forbitbd.lawyersdiary.ui.cases;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.utils.BaseActivity;

import java.util.ArrayList;

public class CasesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private CasesAdapter adapter;
    private ArrayList<Case> casesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        setupToolbar(R.id.toolbar);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        casesList = new ArrayList<>();
        casesList.add(new Case("This is the first Case", "Attempt to Murder", "High Court", "30/01/2022", "Abdul Hakim", "Complainant", "Mohammad Ali", "Fakhrul Islam"));
        casesList.add(new Case("This is the Second Case", "Attempt to Murder", "High Court", "30/01/2022", "Abdul Hakim", "Complainant", "Mohammad Ali", "Fakhrul Islam"));
        casesList.add(new Case("This is the third Case", "Attempt to Murder", "High Court", "30/01/2022", "Abdul Hakim", "Complainant", "Mohammad Ali", "Fakhrul Islam"));
        adapter = new CasesAdapter(casesList);
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
}
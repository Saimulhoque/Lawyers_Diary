package com.forbitbd.lawyersdiary.ui.clients;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Clients;
import com.forbitbd.lawyersdiary.utils.BaseActivity;

import java.util.ArrayList;

public class ClientsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ArrayList<Clients> clientsList;
    private ClientsAdapter adapter;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        setupToolbar(R.id.toolbar);
        initView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        clientsList = new ArrayList<>();
        clientsList.add(new Clients(R.drawable.ic_baseline_person_24,"Saimul Hoque", "01881269553", "01982204475", "Mirpur-1, Dhaka-1216", "saimulhoque8214@gmail.com", "21-11-2000", "31/04/2022"));
        clientsList.add(new Clients(R.drawable.ic_baseline_person_24,"Abid Ahmed Sobhan", "01821465858", "01982204475", "Mirpur-1, Dhaka-1216", "abidasobhan10@gmail.com", "21-11-2000", "23/03/2022"));
        clientsList.add(new Clients(R.drawable.ic_baseline_person_24,"Samim Ahmed", "01984748748", "01982204475", "Mirpur-1, Dhaka-1216", "samsul.ent@gmail.com", "21-11-2000", "12/02/2022"));
        clientsList.add(new Clients(R.drawable.ic_baseline_person_24,"Sazzad Hossain", "01790489484", "01982204475", "Mirpur-1, Dhaka-1216", "sazzadhossain22@gmail.com", "21-11-2000", "12/05/2022"));
        clientsList.add(new Clients(R.drawable.ic_baseline_person_24,"Abdullah Al Mamum", "0184584785", "01982204475", "Mirpur-1, Dhaka-1216", "smjalsaba420@gmail.com", "21-11-2000", "14/04/2022"));
        adapter = new ClientsAdapter(clientsList, new ClientsClickListener() {
            @Override
            public void OnItemClick(Clients clients) {

            }

            @Override
            public void OnCallClick(Clients clients) {
                dialCall(clients.getPhone1(),clients);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void dialCall(String phone1, Clients clients) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you want to call, "+clients.getName()+"?");
        builder.setCancelable(true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("tel:" + phone1));
                startActivity(intent);
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

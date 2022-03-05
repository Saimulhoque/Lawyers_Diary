package com.forbitbd.lawyersdiary.ui.clients;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.forbitbd.lawyersdiary.utils.BaseActivity;
import com.forbitbd.lawyersdiary.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class ClientsActivity extends BaseActivity implements ClientsContract.View{

    private ClientsPresenter mPresenter;
    private RecyclerView recyclerView;
    private ClientsAdapter adapter;
    private ArrayList<Client> clientList;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        mPresenter = new ClientsPresenter(this);
        setupToolbar(R.id.toolbar);

        String lawyerId = AppPreference.getInstance(this).getLawyer().get_id();

        mPresenter.getClients(lawyerId);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        clientList = new ArrayList<>();
        adapter = new ClientsAdapter(clientList, new ClientsAdapter.ClientsClickListener() {
            @Override
            public void OnItemClick(Client client) {
//                ClientDialogFragment clientDialogFragment = new ClientDialogFragment();
//                Bundle data = new Bundle();
//                data.putSerializable(Constant.CLIENT,client);
//                clientDialogFragment.setArguments(data);
//                clientDialogFragment.setCancelable(true);
//                clientDialogFragment.show(getSupportFragmentManager(),"JJJJJJJ");
            }

            @Override
            public void OnCallClick(Client client) {
                dialCall(client.getPhone_one(),client);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void dialCall(String phone1, Client client) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Did you want to call, "+client.getName()+"?");
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

    @Override
    public void responseClient(List<Client> clientList) {
        Log.d("JJJJJJ", "onResponse: "+clientList);
        for (Client x : clientList) {
            adapter.AddClients(x);
        }
    }
}

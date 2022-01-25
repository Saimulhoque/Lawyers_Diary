package com.forbitbd.lawyersdiary.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Settings;
import com.forbitbd.lawyersdiary.ui.aboutus.AboutUsActivity;
import com.forbitbd.lawyersdiary.ui.editprofile.EditProfileActivity;
import com.forbitbd.lawyersdiary.ui.main.Communicator;
import com.forbitbd.lawyersdiary.ui.privacypolicy.PrivacyPolicyActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Settings> settingsList;
    private SettingsAdapter adapter;
    private CircleImageView userimage;
    private TextView username, useremail;
    private Button btneditprofile;
    private Communicator communicator;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator = (Communicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        userimage = view.findViewById(R.id.user_image);
        username = view.findViewById(R.id.user_name);
        useremail = view.findViewById(R.id.user_email);
        btneditprofile = view.findViewById(R.id.edit_profile);
        btneditprofile.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        settingsList = new ArrayList<>();
        settingsList.add(new Settings(R.drawable.ic_baseline_language_24,getString(R.string.language)));
        settingsList.add(new Settings(R.drawable.ic_baseline_help_outline_24,getString(R.string.help)));
        settingsList.add(new Settings(R.drawable.ic_baseline_sync_24,getString(R.string.check_for_update)));
        settingsList.add(new Settings(R.drawable.ic_baseline_mobile_screen_share_24,getString(R.string.share_this_app)));
        settingsList.add(new Settings(R.drawable.ic_baseline_help_outline_24,getString(R.string.about_us)));
        settingsList.add(new Settings(R.drawable.ic_baseline_policy_24,getString(R.string.privacy_policy)));
        settingsList.add(new Settings(R.drawable.ic_baseline_power_settings_new_24,getString(R.string.logout)));

        adapter = new SettingsAdapter(settingsList, new SettingsAdapter.SettingsClickListener() {
            @Override
            public void OnItemClick(int adapterPosition) {
                if (adapterPosition == 0){
                    showAlertDialog();
                }else if (adapterPosition == 1){

                }else if (adapterPosition == 2){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
                    startActivity(intent);
                }else if (adapterPosition == 3){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(""));
                    startActivity(intent);
                }else if (adapterPosition == 4){
                    startActivity(new Intent(getContext(),AboutUsActivity.class));
                }else if (adapterPosition == 5){
                    startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));
                }else if (adapterPosition == 6){
                    communicator.logOutFromApp();
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void showAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Did you want to change app language?")
                .setMessage("Select a Language")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Bangla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setIcon(R.drawable.ic_baseline_language_24)
                .show();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }
}
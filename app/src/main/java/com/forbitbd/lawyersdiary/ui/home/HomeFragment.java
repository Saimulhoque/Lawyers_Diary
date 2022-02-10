package com.forbitbd.lawyersdiary.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forbitbd.lawyersdiary.R;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.model.Features;
import com.forbitbd.lawyersdiary.ui.addcase.AddCaseActivity;
import com.forbitbd.lawyersdiary.ui.addcasetype.AddCaseTypeFragment;
import com.forbitbd.lawyersdiary.ui.addclient.AddClientFragment;
import com.forbitbd.lawyersdiary.ui.addcourt.AddCourtFragment;
import com.forbitbd.lawyersdiary.ui.appointment.AppointmentActivity;
import com.forbitbd.lawyersdiary.ui.calender.CalenderActivity;
import com.forbitbd.lawyersdiary.ui.cases.CasesActivity;
import com.forbitbd.lawyersdiary.ui.clients.ClientsActivity;
import com.forbitbd.lawyersdiary.ui.evidence.EvidenceActivity;
import com.forbitbd.lawyersdiary.ui.main.Communicator;
import com.forbitbd.lawyersdiary.utils.AppPreference;
import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeContract.View {

    private TextView tvCaseAdded, tvScheduleToday, tvActiveCases, tvCaseClosed, tvTotalClients, tvCaseTypes;
    private MaterialCardView cardCaseAdded, cardScheduleToday, cardActiveCases, cardCaseClosed;
    private RecyclerView recyclerView;
    private ArrayList<Features> featuresList;
    private FeatureAdapter adapter;
    private Communicator communicator;
    private HomePresenter mPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator = (Communicator) getActivity();
        mPresenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String lawyerId = AppPreference.getInstance(getContext()).getLawyer().get_id();

        initView(view);
        mPresenter.getDashboardInfo(lawyerId);

        return view;
    }

    private void initView(View view) {
        tvCaseAdded = view.findViewById(R.id.case_added);
        tvScheduleToday = view.findViewById(R.id.schedule_today);
        tvActiveCases = view.findViewById(R.id.active_cases);
        tvCaseClosed = view.findViewById(R.id.closed_cases);
        tvTotalClients = view.findViewById(R.id.total_clients);
        tvCaseTypes = view.findViewById(R.id.case_types);

        cardCaseAdded = view.findViewById(R.id.card_case_added);
        cardScheduleToday = view.findViewById(R.id.card_schedule_today);
        cardActiveCases = view.findViewById(R.id.card_active_cases);
        cardCaseClosed = view.findViewById(R.id.card_closed_cases);

        cardCaseAdded.setOnClickListener(this);
        cardScheduleToday.setOnClickListener(this);
        cardActiveCases.setOnClickListener(this);
        cardCaseClosed.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        featuresList = new ArrayList<>();
        featuresList.add(new Features(R.drawable.client,"Add Client"));
        featuresList.add(new Features(R.drawable.add_case,"Add Case"));
        featuresList.add(new Features(R.drawable.court,"Add Court"));
        featuresList.add(new Features(R.drawable.add_case_type,"Add Case Type"));
        featuresList.add(new Features(R.drawable.clients,"Clients"));
        featuresList.add(new Features(R.drawable.cases,"Cases"));
        featuresList.add(new Features(R.drawable.appointment,"Appointment"));
        featuresList.add(new Features(R.drawable.evidence,"Evidence"));
        featuresList.add(new Features(R.drawable.calendar,"Calender"));
        featuresList.add(new Features(R.drawable.add_case_type,"Add Case Date"));

        adapter = new FeatureAdapter(featuresList, new FeatureAdapter.FeatureClickListener() {
            @Override
            public void OnItemClick(int adapterPosition) {
                if (adapterPosition == 0){
                    communicator.startAddClientDialog();
                }else if (adapterPosition == 1){
                    communicator.startAddCaseActivity();
                }else if (adapterPosition == 2){
                    communicator.startAddCourtDialog();
                }else if (adapterPosition == 3){
                    communicator.startAddCaseTypeDialog();
                }else if (adapterPosition == 4){
                    communicator.startClientActivity();
                }else if (adapterPosition == 5){
                    communicator.startCasesActivity();
                }else if (adapterPosition == 6){
                    communicator.startAppointmentActivity();
                }else if (adapterPosition == 7){
                    communicator.startEvidenceActivity();
                }else if (adapterPosition == 8){
                    communicator.startCalenderActivity();
                }else if (adapterPosition == 9){
                    communicator.startAddCaseDateActivity();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.card_case_added){
            startActivity(new Intent(getContext(), CasesActivity.class));
        }else if (id == R.id.card_schedule_today){

        }else if (id == R.id.card_active_cases){

        }else if (id == R.id.card_closed_cases){

        }
    }

    @Override
    public void addDashboard(String dashboard) {

    }
}

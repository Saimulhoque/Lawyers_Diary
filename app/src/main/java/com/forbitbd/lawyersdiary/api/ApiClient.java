package com.forbitbd.lawyersdiary.api;

import com.forbitbd.lawyersdiary.model.AppointmentRequest;
import com.forbitbd.lawyersdiary.model.AppointmentResponse;
import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseDate;
import com.forbitbd.lawyersdiary.model.CaseFees;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.model.Lawyer;
import com.forbitbd.lawyersdiary.model.OthersAppointmentRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("/dairy/lawyer")
    Call<Lawyer> register(@Body Lawyer data);

    @POST("/dairy/casetype")
    Call<CaseType> postCaseType(@Body CaseType caseType);

    @POST("/dairy/client")
    Call<Client> postClient(@Body Client client);

    @GET("/dairy/client/{id}")
    Call<List<Client>> getAllClients(@Path("id") String id);

    @POST("/dairy/court")
    Call<Court> postCourt(@Body Court court);

    @GET("/dairy/dashboard/{id}")
    Call<Dashboard> getDashboard(@Path("id") String id);

    @GET("/dairy/case/{id}")
    Call<List<Case>> getAllCases(@Path("id") String id);

    @POST("/dairy/case")
    Call<Case> saveCase(@Body Case aCase);

    @POST("/dairy/casedate")
    Call<CaseDate> saveCaseDate(@Body CaseDate caseDate);

    @POST("/dairy/appointment/others")
    Call<AppointmentResponse> saveOthersAppointment(@Body OthersAppointmentRequest appointmentRequest);

    @POST("/dairy/appointment")
    Call<AppointmentResponse> saveAppointment(@Body AppointmentRequest appointmentRequest);



    @GET("/dairy/appointment/{id}")
    Call<List<AppointmentResponse>> getAllAppointment(@Path("id") String id);

    @POST("/dairy/case_fees")
    Call<CaseFees> saveCaseFees(@Body CaseFees caseFees);

}

package com.forbitbd.lawyersdiary.api;

import com.forbitbd.lawyersdiary.model.Case;
import com.forbitbd.lawyersdiary.model.CaseType;
import com.forbitbd.lawyersdiary.model.Client;
import com.forbitbd.lawyersdiary.model.Court;
import com.forbitbd.lawyersdiary.model.Dashboard;
import com.forbitbd.lawyersdiary.model.Lawyer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("/lawyer")
    Call<Lawyer> register(@Body Lawyer data);

    @POST("/casetype")
    Call<CaseType> postCaseType(@Body CaseType caseType);

    @POST("/client")
    Call<Client> postClient(@Body Client client);

    @GET("/client/{id}")
    Call<List<Client>> getAllClients(@Path("id") String id);

    @POST("/court")
    Call<Court> postCourt(@Body Court court);

    @GET("/dashboard/{id}")
    Call<Dashboard> getDashboard(@Path("id") String id);

    @GET("/case/{id}")
    Call<List<Case>> getAllCases(@Path("id") String id);
//
//    @GET("/dashboard/{id}")
//    Call <List<Dashboard>> getDashboard(@Path("id") String id);

}

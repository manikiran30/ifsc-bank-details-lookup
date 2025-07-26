package com.example.ifscverifier.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IfscApiService {
    @GET("{ifsc}")
    Call<BankDetails> getBankDetails(@Path("ifsc") String ifscCode);
}

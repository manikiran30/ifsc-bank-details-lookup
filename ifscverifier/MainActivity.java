package com.example.ifscverifier;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ifscverifier.network.IfscApiService;
import com.example.ifscverifier.network.RetrofitClient;
import com.example.ifscverifier.network.BankDetails;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etIfscCode;
    private TextView tvBankDetails;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIfscCode = findViewById(R.id.etIfscCode);
        btnVerify = findViewById(R.id.btnVerify);
        tvBankDetails = findViewById(R.id.tvBankDetails);

        btnVerify.setOnClickListener(v -> {
            String ifscCode = etIfscCode.getText().toString().trim();

            if (ifscCode.isEmpty()) {
                Toast.makeText(this, "Please enter an IFSC code", Toast.LENGTH_SHORT).show();
                return;
            }

            fetchBankDetails(ifscCode);
        });
    }

    private void fetchBankDetails(String ifscCode) {
        IfscApiService apiService = RetrofitClient.getClient().create(IfscApiService.class);
        Call<BankDetails> call = apiService.getBankDetails(ifscCode);

        call.enqueue(new Callback<BankDetails>() {
            @Override
            public void onResponse(Call<BankDetails> call, Response<BankDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BankDetails details = response.body();
                    String bankInfo = "Bank: " + details.getBank() + "\n" +
                            "Branch: " + details.getBranch() + "\n" +
                            "Address: " + details.getAddress() + "\n" +
                            "State: " + details.getState();
                    tvBankDetails.setText(bankInfo);
                } else {
                    tvBankDetails.setText("Invalid IFSC Code");
                }
            }

            @Override
            public void onFailure(Call<BankDetails> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

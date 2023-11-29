package com.cricketprediction.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cricketprediction.database.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;

    User user;

    Userdatabase userdatabase;

    Userdao userdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user= (User) getIntent().getSerializableExtra("pos");


        userdatabase=Userdatabase.getInstance(this);
        userdao=userdatabase.getdao();

        binding.entername.setText(user.getName());
        binding.enteraddress.setText(user.getAddress());
        binding.entergender.setText(user.getGender());
        binding.enterlastname.setText(user.getLastname());


        binding.submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user1=new User(user.getId(),binding.entername.getText().toString(),binding.enteraddress.getText().toString(),binding.entergender.getText().toString(),binding.enterlastname.getText().toString());
                userdao.update(user1);
                finish();
            }
        });
    }
}
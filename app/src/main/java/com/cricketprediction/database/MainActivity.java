package com.cricketprediction.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cricketprediction.database.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapterinsnner {

    ActivityMainBinding binding;

    User user;

    private Userdatabase userdatabase;
    private Userdao userdao;

    private Useradapter useradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        userdatabase = Userdatabase.getInstance(this);
        userdao = userdatabase.getdao();
        useradapter=new Useradapter(this,this);
        binding.recyclerview.setAdapter(useradapter);


        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=binding.entername.getText().toString();
                String lastname=binding.enteraddress.getText().toString();
                String gander=binding.entergender.getText().toString();
                String address=binding.enterlastname.getText().toString();

                        User user=new User(0,name,lastname,gander,address);
                        userdao.insert(user);
                        useradapter.adduser(user);
                        binding.entername.setText("");
                        binding.enteraddress.setText("");
                        binding.entergender.setText("");
                        binding.enterlastname.setText("");

                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void fetchdatabse(){
        useradapter.cleardata();
        List<User>userList=userdao.getAllUsers();

        for(int i=0;i<userList.size();i++){
            User user=userList.get(i);
            useradapter.adduser(user);
        }
    }



    @Override
    public void OnUpdate(User user) {
        Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("pos",user);
        startActivity(intent);
    }

    @Override
    public void OnDeleted(int id, int pos) {
        userdao.delete(id);
        useradapter.removeuser(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchdatabse();
    }
}
package com.example.android_week7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Mail> arrayDisplay=new ArrayList<>();
    ImageButton menuBtn;
    ImageButton searchBtn;
    EditText editText;
    Button btn;
    String hint;
    ArrayList<Mail>  arrayList;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Faker faker=new Faker();
        AnhXa();
        arrayList=new ArrayList<>();
        for(int i=0;i<20;i++){
            Mail mail=new Mail(faker.name.name(),faker.lorem.paragraph(),faker.lorem.paragraph(),faker.time.toString(),faker.color.hexColor());
            mail.time="12:00 P.M";
            arrayList.add(mail);
        }
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        final MailAdapter mailAdapter=new MailAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mailAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=2) mailAdapter.search(s.toString());
                else mailAdapter.seeAll();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
                    mailAdapter.seeFavorite();
                    i++;
                }else{
                    mailAdapter.seeAll();
                    i--;
                }
            }
        });
    }
    void AnhXa(){
        recyclerView=findViewById(R.id.recycleView);
        menuBtn=findViewById(R.id.menuBtn);
        searchBtn=findViewById(R.id.searchBtn);
        editText=findViewById(R.id.editText);
        btn=findViewById((R.id.btn));
    }
}

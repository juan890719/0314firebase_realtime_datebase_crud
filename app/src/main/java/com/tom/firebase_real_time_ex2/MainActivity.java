package com.tom.firebase_real_time_ex2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit, btnUpdate, btnRemove, btnRead;
    private EditText etPosition, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etPosition = findViewById(R.id.et_position);
        btnSubmit = findViewById(R.id.btn_submit);
        btnRead = findViewById(R.id.btn_read);
        btnUpdate = findViewById(R.id.btn_update);
        btnRemove = findViewById(R.id.btn_remove);
        DAOEmployee dao = new DAOEmployee();

        // 新增
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee emp = new Employee(etName.getText().toString(), etPosition.getText().toString());
                dao.add(emp).addOnSuccessListener(suc -> {
                    Toast.makeText(MainActivity.this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(MainActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        // 讀取
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.read("-My5S9e2iMqZHorjYiVT");
            }
        });

        // 更新
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", etName.getText().toString());
                hashMap.put("position", etPosition.getText().toString());
                dao.update("-My5S9e2iMqZHorjYiVT", hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(MainActivity.this, "Record is updated", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(MainActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        // 刪除
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.remove("-My5S9e2iMqZHorjYiVT").addOnSuccessListener(suc -> {
                    Toast.makeText(MainActivity.this, "Record is removed", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(MainActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}
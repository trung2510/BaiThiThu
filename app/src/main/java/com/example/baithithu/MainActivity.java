package com.example.baithithu;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    private EditText edtId;
    private EditText edtName;
    private Button btnThem;
    private Button btnSua;
    private ArrayList<SinhVien> arraysinhvien = new ArrayList<>();
    private RecyclerView rvView;
    SinhVienAdapter adapter;
    int tramanh,xxx;

    private void GetDataSinhVien() {
        //select data
        arraysinhvien.clear();
        Cursor dataSinhVien = database.GetData("SELECT * FROM SinhVien");
        while (dataSinhVien.moveToNext()) {
            String id = dataSinhVien.getString(1);
            String name = dataSinhVien.getString(2);
            arraysinhvien.add(new SinhVien(dataSinhVien.getInt(0),id, name));
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);

        rvView = (RecyclerView) findViewById(R.id.rvView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layoutManager);
        adapter = new SinhVienAdapter(MainActivity.this, arraysinhvien, R.layout.item);
        rvView.setAdapter(adapter);


        //tạo database baithithu
        database = new Database(this, "BaiThiThu.sqlite", null, 1);

        //Tạo bảng SinhVien
        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(_id INTEGER PRIMARY KEY AUTOINCREMENT,Id VARCHAR, TenSV VARCHAR(50))");

        //insert data
        //database.QueryData("INSERT INTO SinhVien VALUES(null,'PH06941','Phan Quốc Trung2')");

        GetDataSinhVien();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                boolean is=true;
                if (!arraysinhvien.isEmpty()){

                    if(id.equals("")){
                        Toast.makeText(MainActivity.this, "Hãy nhập ID", Toast.LENGTH_SHORT).show();
                        return;
                        }
                    for (int i=0;i<arraysinhvien.size();i++){

                       if (id.equals(arraysinhvien.get(i).getID())){
                            edtId.setError("ID đã tồn tại");
                            is=false;
                            break;
                        }

                    }
                    if(name.equals("")){
                        Toast.makeText(MainActivity.this, "Hãy nhập tên", Toast.LENGTH_SHORT).show();
                        return;
                    }

                      if (is){
                          database.QueryData("INSERT INTO SinhVien VALUES(null,'" + id + "','" + name + "')");
                          Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                          GetDataSinhVien();
                      }

                }
                else {
                    database.QueryData("INSERT INTO SinhVien VALUES(null,'" + id + "','" + name + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    GetDataSinhVien();
                }
            }
        });


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String tenMoi = edtName.getText().toString().trim();
                if (id.equals("")){
                    return;
                }
                if (tenMoi.equals("")){
                    return;
                }
                database.QueryData("UPDATE SinhVien SET Id = '"+ id +"',TenSV = '"+ tenMoi +"' WHERE _id = '"+ tramanh +"'");
                arraysinhvien.set(xxx,new SinhVien(tramanh,id,tenMoi));
                adapter.notifyDataSetChanged();

            }
        });


    }

    public void hienThi(SinhVien sinhvien,int i) {
        edtId.setText(sinhvien.getID());
        edtName.setText(sinhvien.getName());
        tramanh=sinhvien.getmID();
        xxx=i;
    }

    public void xoa(final int id, String name, final int i) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa: "+name+"\tkhông?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM SinhVien WHERE _id = '"+ id +"'");
                arraysinhvien.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.show();
        Log.e("TAG",i+"");

    }



}

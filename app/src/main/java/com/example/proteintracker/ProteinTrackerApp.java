package com.example.proteintracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ProteinTrackerApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein_tracker_app);
        String contextMenus[]={"Protein","Vitamin","Mineral"};
        ListView lv = (ListView) findViewById(R.id.listViewMenu);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(ProteinTrackerApp.this,android.R.layout.simple_list_item_1,contextMenus);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
        Button settingBtn = (Button)findViewById(R.id.btnSetting);
        settingBtn.setOnClickListener(settingButtonListener);
        SharedPreferences prefs = ProteinTrackerApp.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myBtnLoginClick);
        if (statusLogin != null){
            btnLogin.setText("Logout");
        }else{
            btnLogin.setText("Login"); }
    }
    private View.OnClickListener settingButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ProteinTrackerApp.this, SettingsActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener myBtnLoginClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = ProteinTrackerApp.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();
            Button btnLogin = (Button)findViewById(R.id.btnLogin);
            if (statusLogin != null){
                edit.putString("isLogin",null);
                btnLogin.setText("Login");
            }else{
                edit.putString("isLogin","Admin");
                btnLogin.setText("Logout");
            }
            edit.commit();
        }
    };
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("silahkan memilih");
        menu.add(0,v.getId(),0,"Simpan");
        menu.add(0,v.getId(),0,"Tidak");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Simpan"){
            Toast.makeText(getApplicationContext(),"Sedang Menyimpan, Harap Tunggu",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Tidak"){
            Toast.makeText(getApplicationContext(),"Tidak Jadi Menyimpan",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Terpilih",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Terpilih",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Terpilih",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

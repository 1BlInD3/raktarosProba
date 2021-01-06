package com.example.myrecview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.sql.*;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    private ItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String URL = "jdbc:jtds:sqlserver://10.0.0.18;databaseName=EllenallasMeres;user=MvWrite;password=Mv2019";
   // private String URL = "jdbc:jtds:sqlserver://scala1;databaseName=Fusetech;user=scala_read;password=scala_read";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Item> myItems = new ArrayList<>();

        Log.d("DONKEY","1");
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL);
            if(connection != null)
            {
                Log.d("DONKEY","Csatlakoztatva");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 40 MertErtek,Datum,Rajzszam  FROM Hal2EllenallasTemp ");
                while (resultSet.next())
                {
                    Log.d("DONKEY","Csinálom");
                    myItems.add(new Item(resultSet.getString("MertErtek"),resultSet.getString("Datum"),resultSet.getString("Rajzszam")));
                }
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("DONKEY",String.valueOf(e));
        }


        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ItemAdapter(myItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new ItemAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(getApplicationContext(),myItems.get(position).getmMertErtek(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
package fr.josselin.memo.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DatabaseOpenHelper doh;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MainAdapter(MainActivity.this, getLayoutInflater()));


        doh = new DatabaseOpenHelper(this);
        db = doh.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM memo", null);
        int nameIndex = cursor.getColumnIndex("titre");
        int idIndex = cursor.getColumnIndex("id");
        int idContent = cursor.getColumnIndex("content");

        while(cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            int id = cursor.getInt(idIndex);
            String content = cursor.getString(idContent);
            Memo memo = new Memo(id, name, content);
            ((MainAdapter) rv.getAdapter()).add(memo);
        }
        cursor.close();
        Log.d("test", "ca marche");


    }

    public void add() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater lif = getLayoutInflater();
        View dialogView = lif.inflate(R.layout.dialog_view, null);
        builder.setView(dialogView);
        builder.setTitle("Nouveau Memo");



        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {


                Log.d("MainActivity", "ok");

                AlertDialog alertDialog = (AlertDialog) dialog;
                EditText et = alertDialog.findViewById(R.id.txt);
                String Name = et.getText().toString();

                Log.d("TAG", "valeur = "+ Name);
                Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();

                // MainAdapter ma = (MainAdapter) rv.getAdapter();
                try{
                    db.execSQL("INSERT INTO memo (titre) VALUES ('" + Name +"')");
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                }


                Cursor cursor = db.rawQuery("SELECT id FROM memo", null);
                int idCol = cursor.getColumnIndex("id");
                cursor.moveToLast();
                Integer id = cursor.getInt(idCol);
                ((MainAdapter) rv.getAdapter()).add(new Memo(id, Name, " "));

                intent(id.toString(), Name, " ");

            }
        });


        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("MainActivity", "annuler");
            }
        });

        //AlertDialog dialog = builder.create();
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {

           add();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void intent(String id, String titre, String content) {

                Toast tartine = Toast.makeText(this, "Ouverture", Toast.LENGTH_SHORT);
                tartine.show();

                Intent intent = new Intent(this, EditActivity.class);
                String[] tab = {id, titre, content};
                intent.putExtra("string", tab);
                startActivity(intent);

    }

}

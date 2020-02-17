package fr.josselin.memo.memo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText txt;
    private DatabaseOpenHelper doh;
    private SQLiteDatabase db;
    String res;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txt = findViewById(R.id.txt);


        doh = new DatabaseOpenHelper(this);

        db = doh.getReadableDatabase();

        Intent i = getIntent();

        final String[] tab = i.getStringArrayExtra("string");
        Log.d("tag", "ta mere la pute : "+ tab[0]);
        this.setTitle(tab[1]);
        txt.setText(tab[2]);
        id = tab[0];

    }

    private void save(String id) {
        ContentValues cv = new ContentValues();
        cv.put("content", txt.getText().toString());

        //Log.d("tag", "ta mere la pute " + id);

        db.update("memo", cv, "id = ?", new String[]{id});

        goToMainActivity();
    }

    private void goToMainActivity() {
        Intent i = new Intent(EditActivity.this, MainActivity.class);
        startActivity(i);
        finishAffinity();
    }

    private void delete(String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);

        LayoutInflater lif = getLayoutInflater();
        builder.setTitle("Voulez-vous Supprimer ce memo ?");
        final String Id = id;

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("MainActivity", "annuler");
            }
        });

        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {


                Log.d("MainActivity", "ok");

                Toast.makeText(EditActivity.this, "DELETING", Toast.LENGTH_SHORT).show();

                //db.execSQL("INSERT INTO memo (titre) VALUES ('" + Name +"')");
                db.delete("memo", "id = ?", new String[]{Id});

                goToMainActivity();

            }
        });



        //AlertDialog dialog = builder.create();
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {

            delete(id);
            return true;

        }
        if (item.getItemId() == R.id.save) {
            save(id);
            return true;
        }

        if (item.getItemId() == R.id.share) {
            res = txt.getText().toString();
            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
            ShareIntent.setType("text/plain");
            ShareIntent.putExtra(Intent.EXTRA_TEXT, res);
            startActivity(ShareIntent);
            Log.d("Tag", "Sharing");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        save(id);
    }


}

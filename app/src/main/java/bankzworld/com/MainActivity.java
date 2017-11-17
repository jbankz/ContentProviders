package bankzworld.com;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mAdd, mRetrieve;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdd = (Button) findViewById(R.id.btnAdd);
        mRetrieve = (Button) findViewById(R.id.btnRetrieve);
        mTextView = (TextView) findViewById(R.id.display_data);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMethod();
            }
        });
        mRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveMethod();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void addMethod() {
        // Add a new developer record
        ContentValues values = new ContentValues();

        values.put(DevelopersProvider.NAME,
                ((EditText) findViewById(R.id.etName)).getText().toString());

        values.put(DevelopersProvider.POSITION,
                ((EditText) findViewById(R.id.etPosition)).getText().toString());

        Uri uri = getContentResolver().insert(
                DevelopersProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void retrieveMethod() {
        // Retrieve developers records
        String URL = "content://bankzworld.com/programmers";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");
        if (c.moveToFirst()) {
            do {

                mTextView.append(c.getString(c.getColumnIndex(DevelopersProvider._ID)) +
                        ".     " + c.getString(c.getColumnIndex(DevelopersProvider.NAME)) +
                        ".     " + c.getString(c.getColumnIndex(DevelopersProvider.POSITION)) + "\n");

            } while (c.moveToNext());
        }
    }
}

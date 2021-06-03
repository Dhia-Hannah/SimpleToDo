package sg.edu.rp.c346.id20031826.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnTask;
    EditText et;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView listViewTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTask = findViewById(R.id.spinner);
        et = findViewById(R.id.EditTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        listViewTask = findViewById(R.id.ListViewTask);

        alTasks = new ArrayList<String>();

        aaTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTasks);
        listViewTask.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTask = et.getText().toString();
                alTasks.add(inputTask);
                aaTasks.notifyDataSetChanged();
                et.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(et.getText().toString());
                if (pos > -1 && pos < alTasks.size()) {
                    alTasks.remove(pos);
                    aaTasks.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(),"You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.size() != 0) { //if array not empty
                    int size = alTasks.size() - 1;
                    for(int i = 0; i > -1; i--) {
                        alTasks.remove(i);
                    }
                    aaTasks.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid index", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //add task
                        et.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1: //remove task
                        et.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
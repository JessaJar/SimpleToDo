package sg.edu.rp.c346.id20014241.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerAddOrRemove;
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView listViewTask;
    ArrayList<String> arrayListToDo;
    ArrayAdapter<String> arrayAdapterToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAddOrRemove = findViewById(R.id.spinnerAddOrRemove);
        etTask = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        listViewTask = findViewById(R.id.listViewTasks);

        arrayListToDo = new ArrayList<String>();

        arrayAdapterToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListToDo);
        listViewTask.setAdapter(arrayAdapterToDo);

        spinnerAddOrRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Add new task");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (etTask.length() != 0) {
                                    String newTask = etTask.getText().toString();
                                    arrayListToDo.add(newTask);
                                    arrayAdapterToDo.notifyDataSetChanged();
                                    etTask.setText("");
                                } else {
                                    Toast.makeText(MainActivity.this, "No input detected", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                arrayListToDo.clear();
                                arrayAdapterToDo.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTask.setHint("Index of the task to be removed");
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (arrayListToDo.size() == 0) {
                                    Toast.makeText(MainActivity.this, "No task to remove", Toast.LENGTH_LONG).show();
                                } else {
                                    if (etTask.length() != 0) {
                                        int positionTask = Integer.parseInt(etTask.getText().toString());
                                        if (positionTask > (arrayListToDo.size() - 1)) {
                                            Toast.makeText(MainActivity.this, "Index number does not exist", Toast.LENGTH_LONG).show();
                                        } else {
                                            arrayListToDo.remove(positionTask);
                                            arrayAdapterToDo.notifyDataSetChanged();
                                            etTask.setText("");
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "No input detected", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                arrayListToDo.clear();
                                arrayAdapterToDo.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
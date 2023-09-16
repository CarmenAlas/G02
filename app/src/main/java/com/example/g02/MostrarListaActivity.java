package com.example.g02;

import static com.example.g02.MainActivity.lstPersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.g02.clases.Persona;

public class MostrarListaActivity extends AppCompatActivity {

    private ListView lsvPersonas;
    EditText edtNombre, edtApellido, edtEdad, edtCorreo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);

        lsvPersonas = findViewById(R.id.lsvPersona);

        ArrayAdapter<Persona> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, lstPersonas);

        lsvPersonas.setAdapter(arrayAdapter);


        lsvPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Persona personaSeleccionada = lstPersonas.get(position);

                Intent intent = new Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
                intent.putExtra("posicion", position);
                intent.putExtra("persona",personaSeleccionada);
                startActivity(intent);



            }


        });


        lsvPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position, long l) {

                new AlertDialog.Builder(MostrarListaActivity.this)
                        .setTitle("Quiere eliminar"+lstPersonas.get(position)+"de la lista")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lstPersonas.remove(position);
                                arrayAdapter.notifyDataSetChanged();


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

                            return false;


            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayAdapter<Persona> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,lstPersonas);
        arrayAdapter.notifyDataSetChanged();
        lsvPersonas.setAdapter(arrayAdapter);
    }
}

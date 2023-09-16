package com.example.g02;

import static com.example.g02.MainActivity.lstPersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.g02.clases.Persona;


public class EditarPersonaActivity extends AppCompatActivity {

    EditText edtNombre, edtApellido, edtEdad, edtCorreo;
    Button btnModificar, btnRegresar;
    private Persona personaSeleccionada;
    private String nom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtEdad = findViewById(R.id.edtEdad);
        edtCorreo = findViewById(R.id.edtCorreo);
        btnModificar = findViewById(R.id.btnModificar);
        btnRegresar = findViewById(R.id.btnRegresar);




        Intent intent = getIntent();
        int posicion = intent.getIntExtra("posicion",0);
        int idPersona = intent.getIntExtra("idPersona",0);
        if (intent != null && intent.hasExtra("persona")) {
            personaSeleccionada = (Persona) intent.getSerializableExtra("persona");



            if (personaSeleccionada != null) {
                edtNombre.setText(personaSeleccionada.getNombrePersona());
                edtApellido.setText(personaSeleccionada.getApellidoPersona());
                edtEdad.setText(String.valueOf(personaSeleccionada.getEdadPersona()));
                edtCorreo.setText(personaSeleccionada.getCorreoPersona());



            }
        }

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstPersonas.set(posicion, new Persona(idPersona,edtNombre.getText().toString(),edtApellido.getText().toString(),Integer.parseInt(edtEdad.getText().toString()),edtCorreo.getText().toString()));
                Toast.makeText(EditarPersonaActivity.this, "Datos modificados", Toast.LENGTH_SHORT).show();
                finish();




            }
        });




        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

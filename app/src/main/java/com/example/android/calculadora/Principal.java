package com.example.android.calculadora;

import android.content.res.Resources;
import android.os.StrictMode;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    private TextView res;
    private EditText n1,n2;
    private Spinner operaciones;
    private String op[];
    private Resources resources;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        res =(TextView)findViewById(R.id.lblResultado);
        n2= (EditText)findViewById(R.id.txt2);
        n1= (EditText)findViewById(R.id.txt1);
        operaciones = (Spinner)findViewById(R.id.cmboperaciones);
        resources =this.getResources();
        op = resources.getStringArray(R.array.opciones);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,op);
                operaciones.setAdapter(adapter);
    }

    public void calcular(View v){
        int opciones;
        double nm2,nm1,resultado=0;
        if (validar()) {
            opciones = operaciones.getSelectedItemPosition();
            nm1 = Double.parseDouble(n1.getText().toString());
            nm2 = Double.parseDouble(n2.getText().toString());


            switch (opciones) {
                case 0:
                    resultado = nm1 + nm2;
                    break;

                case 1:
                    resultado = nm1 - nm2;
                    break;

                case 2:
                    resultado = nm1 * nm2;
                    break;

                case 3:
                    resultado = nm1 / nm2;
                    break;
            }
            res.setText(String.format("%.2f", resultado));

        }
    }
    public void borrar(View v){
        n1.setText("");
        n2.setText("");
        res.setText("");
        operaciones.setSelection(0);
        n1.requestFocus();
    }
    private boolean validar(){
        int opcion = operaciones.getSelectedItemPosition();
        if (n1.getText().toString().isEmpty()){
            n1.setError(resources.getString(R.string.error));
            n1.requestFocus();
            return false;
        }if (n2.getText().toString().isEmpty()){
            n2.setError(resources.getString(R.string.error_dos));
            n2.requestFocus();
            return false;
        }if (Double.parseDouble(n2.getText().toString())==0 && opcion ==3){
            n2.setError(resources.getString(R.string.error_tres));
            n2.requestFocus();
            return false;
        }


        return true;
    }




}

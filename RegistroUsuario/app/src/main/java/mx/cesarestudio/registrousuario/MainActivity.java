package mx.cesarestudio.registrousuario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button botonSiguiente;
    private Button botonElegirFecha;
    private TextInputEditText tietNombre;
    private TextInputEditText tietNumero;
    private TextInputEditText tietEmail;
    private TextInputEditText tietDescripcion;
    private TextInputEditText tietFecha;
    private int dia;
    private int mes;
    private int año;
    private String fecha="";
    private String nombre="";
    private Calendar c = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dpdFecha;
    private static final int TIPO_DIALOGO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tietNombre          = (TextInputEditText)findViewById(R.id.tietNombre);
        tietFecha           = (TextInputEditText)findViewById(R.id.tietFechaNac);
        tietNumero          = (TextInputEditText)findViewById(R.id.tietTelefono);
        tietEmail           = (TextInputEditText)findViewById(R.id.tietEmail);
        tietDescripcion     = (TextInputEditText)findViewById(R.id.tietDescripcion);
        botonElegirFecha    = (Button)findViewById(R.id.btnFecha);

        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        año = c.get(Calendar.YEAR);

        dpdFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int a, int m, int d) {
                año = a;
                mes = m+1;
                dia = d;
                mostrarFecha();
            }
        };
        botonSiguiente = (Button)findViewById(R.id.botonSiguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreConfirmacion = new Intent(MainActivity.this,ConfirmarDatos.class);
                abreConfirmacion.putExtra(getResources().getString(R.string.putNombre),tietNombre.getText().toString());
                abreConfirmacion.putExtra(getResources().getString(R.string.putFecha),fecha);
                abreConfirmacion.putExtra(getResources().getString(R.string.putTel), tietNumero.getText().toString());
                abreConfirmacion.putExtra(getResources().getString(R.string.putEmail), tietEmail.getText().toString());
                abreConfirmacion.putExtra(getResources().getString(R.string.putDescripcion), tietDescripcion.getText().toString());
                startActivity(abreConfirmacion);




            }
        });
        botonElegirFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIPO_DIALOGO);
            }
        });



    }

    @Override
    protected void onResume() {
        recuperarDatos();
        super.onResume();
    }


    @Override
    protected Dialog onCreateDialog(int id) {

        return new DatePickerDialog(MainActivity.this,dpdFecha,año,mes,dia);
    }


    public void mostrarFecha(){
        fecha = dia +"/"+mes+"/"+año;
        tietFecha.setText(fecha);
    }
    public void recuperarDatos(){
        if( getIntent().getExtras()!=null){
            Bundle recupera = getIntent().getExtras();
            String nombre      = recupera.getString(getResources().getString(R.string.putNombre));
            String fecha       = recupera.getString(getResources().getString(R.string.putFecha));
            String numero      = recupera.getString(getResources().getString(R.string.putTel));
            String email       = recupera.getString(getResources().getString(R.string.putEmail));
            String descripcion = recupera.getString(getResources().getString(R.string.putDescripcion));

            tietNombre.setText(nombre);
            tietFecha.setText(fecha);
            tietNumero.setText(numero);
            tietEmail.setText(email);
            tietDescripcion.setText(descripcion);
        }



    }

}

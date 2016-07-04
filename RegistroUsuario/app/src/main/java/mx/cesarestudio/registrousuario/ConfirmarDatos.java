package mx.cesarestudio.registrousuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {
    private TextView tvNC;
    private TextView tvFecha;
    private TextView tvNumero;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos);

        tvNC          = (TextView)findViewById(R.id.tvNC);
        tvFecha       = (TextView)findViewById(R.id.tvFecha);
        tvNumero      = (TextView)findViewById(R.id.tvTel);
        tvEmail       = (TextView)findViewById(R.id.tvEmail);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcion);


        Bundle datos       = getIntent().getExtras();
        String nombre      = datos.getString(getResources().getString(R.string.putNombre));
        String fecha       = datos.getString(getResources().getString(R.string.putFecha));
        String numero      = datos.getString(getResources().getString(R.string.putTel));
        String email       = datos.getString(getResources().getString(R.string.putEmail));
        String descripcion = datos.getString(getResources().getString(R.string.putDescripcion));

        tvNC.setText(nombre);
        tvFecha.setText(fecha);
        tvNumero.setText(numero);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

        btnEditar = (Button)findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(ConfirmarDatos.this,MainActivity.class);
                intento.putExtra(getResources().getString(R.string.putNombre),tvNC.getText().toString());
                intento.putExtra(getResources().getString(R.string.putFecha),tvFecha.getText().toString());
                intento.putExtra(getResources().getString(R.string.putTel), tvNumero.getText().toString());
                intento.putExtra(getResources().getString(R.string.putEmail), tvEmail.getText().toString());
                intento.putExtra(getResources().getString(R.string.putDescripcion), tvDescripcion.getText().toString());
                startActivity(intento);


            }
        });


    }
}

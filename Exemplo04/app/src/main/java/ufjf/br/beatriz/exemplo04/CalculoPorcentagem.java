package ufjf.br.beatriz.exemplo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoPorcentagem extends AppCompatActivity {
    private Double valorPresente;
    private Double valorFinal;
    private Double porcentagem;
    private TextView textViewValorPresente;
    private TextView textViewResultado;
    private TextView textViewPorcentagem;
    private EditText editTextValorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_porcentagem);
        textViewValorPresente = findViewById(R.id.textViewValorPresente);
        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");
        textViewValorPresente.setText(valorPresente.toString());
        textViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        textViewResultado = findViewById(R.id.textViewResultado);
        editTextValorFinal = findViewById(R.id.editTextValorFinal);
    }

    public void retornarClick(View view){
        Intent resultado = new Intent();
        resultado.putExtra("valorFinal", valorFinal);
        resultado.putExtra("porcentagem",porcentagem);
        setResult(MainActivity.RESULT_CALCULO, resultado);
        finish();
    }

    public void calcularClick(View view ){
        valorFinal = Double.parseDouble(editTextValorFinal.getText().toString());
        porcentagem = valorFinal/valorPresente * 100;
        textViewPorcentagem.setText(String.format("%.2f", porcentagem)+"%");
        textViewResultado.setText(String.format("%.2f", valorFinal));
    }
}
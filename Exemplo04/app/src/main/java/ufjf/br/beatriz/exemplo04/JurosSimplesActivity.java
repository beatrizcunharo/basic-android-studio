package ufjf.br.beatriz.exemplo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JurosSimplesActivity extends AppCompatActivity {

    private Double valorPresente;
    private Double valorFinal;
    private Double porcentagem;
    private TextView textViewValorPresente;
    private EditText editTextTaxaDeJuros;
    private EditText editTextPeriodos;
    private TextView textViewResultado;
    private TextView textViewPorcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juros_simples);
        textViewValorPresente = findViewById(R.id.textViewValorPresente);
        Bundle extras = getIntent().getExtras();
        valorPresente = extras.getDouble("valorPresente");
        textViewValorPresente.setText(valorPresente.toString());
        textViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        editTextTaxaDeJuros = findViewById(R.id.editTextValorFinal);
        editTextPeriodos = findViewById(R.id.editTextPeriodos);
        textViewResultado = findViewById(R.id.textViewResultado);
    }

    public void retornarClick(View view){
        Intent resultado = new Intent();
        resultado.putExtra("valorFinal", valorFinal);
        resultado.putExtra("porcentagem",porcentagem);
        setResult(MainActivity.RESULT_JUROS_SIMPLES, resultado);
        finish();
    }

    public void calcularClick(View view ){
        Double taxaDeJuros;
        Integer periodos;
        taxaDeJuros = Double.parseDouble(editTextTaxaDeJuros.getText().toString())/100.0;
        periodos = Integer.parseInt(editTextPeriodos.getText().toString());
        valorFinal = valorPresente*(1+taxaDeJuros*periodos);
        porcentagem = valorFinal/valorPresente * 100;
        textViewPorcentagem.setText(String.format("%.2f", porcentagem)+"%");
        textViewResultado.setText(String.format("%.2f", valorFinal));
    }
}
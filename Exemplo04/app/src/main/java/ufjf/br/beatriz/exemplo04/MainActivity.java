package ufjf.br.beatriz.exemplo04;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_JUROS_SIMPLES = 1;
    public static final int RESULT_JUROS_COMPOSTOS = 2;
    public static final int RESULT_CALCULO = 3;
    ActivityResultLauncher<Intent> launcher;
    EditText editTextValorPresente;
    TextView textViewValorFinal;
    TextView textViewPercFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextValorPresente = findViewById(R.id.editTextValorPresente);
        textViewValorFinal = findViewById(R.id.textViewValorFinal);
        textViewPercFinal = findViewById(R.id.textViewPercFinal);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Double valorFinal;
                        Double porcentagem;
                        Bundle extras;
                        switch (result.getResultCode()){
                            case RESULT_JUROS_SIMPLES:
                                extras = result.getData().getExtras();
                                valorFinal = extras.getDouble("valorFinal");
                                porcentagem = extras.getDouble("porcentagem");
                                textViewValorFinal.setText(String.format("Simples: R$ %.2f",valorFinal));
                                textViewPercFinal.setText(String.format("%.2f", porcentagem)+"%");
                                break;
                            case RESULT_JUROS_COMPOSTOS:
                                extras = result.getData().getExtras();
                                valorFinal = extras.getDouble("valorFinal");
                                porcentagem = extras.getDouble("porcentagem");
                                textViewValorFinal.setText(String.format("Compostos: R$ %.2f",valorFinal));
                                textViewPercFinal.setText(String.format("%.2f", porcentagem)+"%");
                                break;
                            case RESULT_CALCULO:
                                extras = result.getData().getExtras();
                                valorFinal = extras.getDouble("valorFinal");
                                porcentagem = extras.getDouble("porcentagem");
                                textViewValorFinal.setText(String.format("Calculo: R$ %.2f",valorFinal));
                                textViewPercFinal.setText(String.format("%.2f", porcentagem)+"%");
                                break;
                        }
                    }
                }
        );
    }

    public void jurosSimplesClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosSimplesActivity.class);
            intent.putExtra("valorPresente", valorPresente);
            //startActivityForResult(intent, REQUEST_JUROS_SIMPLES);
            launcher.launch(intent);
        } catch (Exception e){
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }
    }

    public void jurosCompostosClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, JurosCompostosActivity.class);
            intent.putExtra("valorPresente", valorPresente);
//            startActivityForResult(intent, REQUEST_JUROS_COMPOSTOS);
            launcher.launch(intent);
        } catch (Exception e){
            e.printStackTrace();
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }

    }

    public void calcularClick(View view){
        try {
            Double valorPresente = Double.parseDouble(editTextValorPresente.getText().toString());
            Intent intent = new Intent(MainActivity.this, CalculoPorcentagem.class);
            intent.putExtra("valorPresente", valorPresente);
            launcher.launch(intent);
        } catch (Exception e){
            e.printStackTrace();
            editTextValorPresente.selectAll();
            editTextValorPresente.requestFocus();
        }

    }
}
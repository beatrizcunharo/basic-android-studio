package ufjf.br.beatriz.exemplo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdicionarEspecie extends AppCompatActivity {
    public static final String KEY_AVISTAMENTO = "key_avistamento";
    public static final String KEY_NOME = "key_nome";
    public static final String KEY_ESPECIE = "key_especie";

    EditText editTextNome;
    EditText editTextEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adiciona_especie);
        editTextNome = findViewById(R.id.editTextNome);
        editTextEspecie = findViewById(R.id.editTextEspecie);
    }

    public void onClickVoltar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickSalvar(View view) {
       String nome = editTextNome.getText().toString();
       String especie = editTextEspecie.getText().toString();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(KEY_NOME, nome);
       intent.putExtra(KEY_ESPECIE, especie);
        intent.putExtra(KEY_AVISTAMENTO, "0");
        setResult(RESULT_OK, intent);
        finish();
    }
}
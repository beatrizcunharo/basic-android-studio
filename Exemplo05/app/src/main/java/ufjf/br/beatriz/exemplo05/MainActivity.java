package ufjf.br.beatriz.exemplo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final  String KEY_PONTOS = "key_pontos";
    public static final  String KEY_ARREMESSO = "key_arremesso";
    public static final  String KEY_BLOQUEIO = "key_bloqueio";
    public static final  String KEY_DISTANCIA = "key_distancia";
    public static final  String KEY_NAME = "key_name";

    EstatisticasRepository repo;
    TextView textViewPontos;
    TextView textViewArremesso;
    TextView textViewBloqueio;
    Button btnPontosInc;
    Button btnPontosDec;
    Button btnArremessoInc;
    Button btnArremessoDec;
    Button btnBloqueioInc;
    Button btnBloqueioDec;
    EditText editTextNome;
    Button btnRegistrar;
    TextView textViewJogador;
    Button btnDistanciaDec;
    Button btnDistanciaInc;
    TextView textViewDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPontos = findViewById(R.id.textViewPontos);
        textViewArremesso = findViewById(R.id.textViewArremesso);
        textViewBloqueio = findViewById(R.id.textViewBloqueio);
        btnPontosInc = findViewById(R.id.btnPontosInc);
        btnPontosDec = findViewById(R.id.btnPontosDec);
        btnArremessoInc = findViewById(R.id.btnArremessoInc);
        btnArremessoDec = findViewById(R.id.btnArremessoDec);
        btnBloqueioInc = findViewById(R.id.btnBloqueioInc);
        btnBloqueioDec = findViewById(R.id.btnBloqueioDec);
        editTextNome = findViewById(R.id.editTextNome);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        textViewJogador = findViewById(R.id.textViewJogador);
        btnDistanciaDec = findViewById(R.id.btnDistanciaDec);
        btnDistanciaInc = findViewById(R.id.btnDistanciaInc);
        textViewDistancia = findViewById(R.id.textViewDistancia);

        repo = new EstatisticasRepository(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        repo.getPreferences().registerOnSharedPreferenceChangeListener(this);
        repo.setValue(0, KEY_PONTOS);
        repo.setValue(0, KEY_ARREMESSO);
        repo.setValue(0, KEY_BLOQUEIO);
        repo.setValue(0, KEY_DISTANCIA);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repo.getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void clickIncDec(View view){
        switch (view.getId()){
            case R.id.btnPontosInc:
                repo.incValue(KEY_PONTOS);
                textViewPontos.setText(repo.getValue(KEY_PONTOS).toString());
                break;
            case R.id.btnPontosDec:
                repo.decValue(KEY_PONTOS);
                textViewPontos.setText(repo.getValue(KEY_PONTOS).toString());
                break;
            case R.id.btnArremessoInc:
                repo.incValue(KEY_ARREMESSO);
                textViewArremesso.setText(repo.getValue(KEY_ARREMESSO).toString());
                break;
            case R.id.btnArremessoDec:
                repo.decValue(KEY_ARREMESSO);
                textViewArremesso.setText(repo.getValue(KEY_ARREMESSO).toString());
                break;
            case R.id.btnBloqueioInc:
                repo.incValue(KEY_BLOQUEIO);
                textViewBloqueio.setText(repo.getValue(KEY_BLOQUEIO).toString());
                break;
            case R.id.btnBloqueioDec:
                repo.decValue(KEY_BLOQUEIO);
                textViewBloqueio.setText(repo.getValue(KEY_BLOQUEIO).toString());
                break;
            case R.id.btnDistanciaInc:
                repo.incValue(KEY_DISTANCIA);
                textViewDistancia.setText(repo.getValue(KEY_DISTANCIA).toString());
                break;
            case R.id.btnDistanciaDec:
                repo.decValue(KEY_DISTANCIA);
                textViewDistancia.setText(repo.getValue(KEY_DISTANCIA).toString());
                break;
        }
    }

    public void onClickRegistrar(View view)
    {
        String nome = editTextNome.getText().toString();
        repo.setNome(nome, KEY_NAME);
        if(!nome.equals(""))
        {
            repo.setNome(nome, KEY_NAME);
            textViewJogador.setText("Bem vindo, "+repo.getNome(KEY_NAME)+"!");
        }else{
            textViewJogador.setText("Bem vindo!");
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // vazio
    }
}
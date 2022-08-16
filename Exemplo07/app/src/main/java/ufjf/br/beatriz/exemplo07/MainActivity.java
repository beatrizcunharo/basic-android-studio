package ufjf.br.beatriz.exemplo07;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private RecyclerView recyclerAvistamento;
    private List<Avistamento> avistamentos = new ArrayList<>();
    private AvistamentoAdapter avistamentoAdapter;
    ActivityResultLauncher<Intent> launcher;
    SharedPreferences shared;
    SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        shared = context.getSharedPreferences("atividade07-beatriz", MODE_PRIVATE);
        sharedEditor = shared.edit();

        recyclerAvistamento = findViewById(R.id.recyclerAvistamento);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAvistamento.setLayoutManager(layoutManager);

            for(Integer i=0; shared.contains(i.toString()); i++) {
                if(shared!=null) {
                    String [] valor = shared.getString(i.toString(), "").split(";");
                     avistamentos.add(new Avistamento(valor[0], valor[1], Integer.parseInt(valor[2])));
                    recyclerAvistamento.setAdapter(avistamentoAdapter);
                } else {
                    break;
                }
        }

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bundle extras;
                        extras = result.getData().getExtras();
                        avistamentos.add(new Avistamento(extras.getString("key_nome"), extras.getString("key_especie"), extras.getInt("key_avistamento")));
                        adicionaEspecie(avistamentos.size()-1, extras.getString("key_nome")+';'+extras.getString("key_especie")+';'+avistamentos.get(avistamentos.size()-1).getAvistamentos());
                        recyclerAvistamento.setAdapter(avistamentoAdapter);
                    }
                }
        );

            AvistamentoAdapter.OnAvistamentoClickListener listener = new AvistamentoAdapter.OnAvistamentoClickListener() {
                @Override
                public void OnAvistamentoClick(View view, int position) {
                    Avistamento avistamento = avistamentos.get(position);
                    avistamento.setAvistamentos(avistamento.getAvistamentos()+1);
                    avistamentoAdapter.notifyItemChanged(position);
                    adicionaEspecie(position, avistamento.getNome()+';'+avistamento.getEspecie()+';'+avistamento.getAvistamentos());
                }
            };
                avistamentoAdapter = new AvistamentoAdapter(avistamentos, listener);
                recyclerAvistamento.setAdapter(avistamentoAdapter);

    }

    public void onClickAdicionar(View view) {
        Intent intent = new Intent(this, AdicionarEspecie.class);
        launcher.launch(intent);
    }

    public void adicionaEspecie (Integer key, String value) {
        sharedEditor = shared.edit();
        sharedEditor.putString(key.toString(), value);
        sharedEditor.apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //vazio
    }
}
package ufjf.br.beatriz.exemplo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listaNomes;
    private TextView nomeTextField;
    private TextView nomeTextView;
    private TextView listaNomesTextView;
    private String outputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaNomes = new ArrayList<String>();
        nomeTextField = findViewById(R.id.nomeTextField);
        nomeTextView = findViewById(R.id.nomeTextView);
        listaNomesTextView = findViewById(R.id.listaNomesTextView);
        outputMessage = "";
    }

    public void onClickAdicionar (View origem) {
        String nome = nomeTextField.getText().toString();
        String outputList = "";
        if(nome.equals(""))
        {
            outputMessage = String.format("Campo nome vazio, insira um nome.");
        }
        else {
            outputMessage = String.format("%s adicionado!", nome);
            listaNomes.add(nome);
        }
        nomeTextView.setText(outputMessage);
        for(int i=0; i<listaNomes.size();i++)
        {
            outputList = outputList.concat(String.format("- %s \n", listaNomes.get(i).toString()));
        }
        listaNomesTextView.setText(outputList);
        nomeTextField.setText("");
    }

    public void onClickSortear (View origem) {
        Random random = new Random();
        int numeroSorteado = 0;
        if(listaNomes.isEmpty())
        {
            outputMessage = String.format("A lista está vazia. Insira um nome na lista.");
        }else {
            numeroSorteado = random.nextInt(listaNomes.size());
            outputMessage = String.format("Parabéns, %s, você foi sorteado!",listaNomes.get(numeroSorteado).toString());
        }

        nomeTextView.setText(outputMessage);
    }

    public void onClickLimpar (View origem) {
        if(listaNomes.isEmpty())
        {
            outputMessage = String.format("A lista já estava vazia.");
        }else{
            listaNomes.clear();
            outputMessage = String.format("Lista apagada. Digite um nome para preencher a nova lista.");
        }

        nomeTextView.setText(outputMessage);
        listaNomesTextView.setText("");
    }
}
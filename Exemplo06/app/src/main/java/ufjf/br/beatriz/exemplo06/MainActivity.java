package ufjf.br.beatriz.exemplo06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public enum Jogada {
        PEDRA(0), PAPEL(1), TESOURA(2), SPOCK(3), LAGARTO(4);
        private final int valor;
        Jogada(int valor)
        {
            this.valor = valor;
        }
    }

    public enum Resultado {
        DERROTA(-1), EMPATE(0), VITORIA(1);
        private final int valor;
        Resultado(int valor)
        {
            this.valor = valor;
        }
    }

    public static Resultado TABELA[][] = {
            {Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA},
            {Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA},
            {Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE}
    };

    private Integer pontosComputador = 0;
    private Integer pontosHumano = 0;

    private ProgressBar progressBarComputador;
    private ProgressBar progressBarHumano;
    private TextView textViewStatus;
    private TextView textViewJogadaUsuario;
    private TextView textViewJogadaPc;
    private TextView textViewGanhador;

    private Random dado = new Random();
    private MessagemGame messagemGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Componentes de saída
        progressBarComputador = findViewById(R.id.progressBarComputador);
        progressBarHumano = findViewById(R.id.progressBarHumano);
        messagemGame = new MessagemGame();
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewJogadaUsuario = findViewById(R.id.textViewJogadaUsuario);
        textViewJogadaPc = findViewById(R.id.textViewJogadaPc);
        textViewGanhador = findViewById(R.id.textViewGanhador);
    }

    public void buttonPedraClick(View view)
    {
        rodada(Jogada.PEDRA);
    }

    public void buttonPapelClick(View view)
    {
        rodada(Jogada.PAPEL);
    }

    public void buttonTesouraClick(View view)
    {
        rodada(Jogada.TESOURA);
    }

    public void buttonSpockClick(View view)
    {
        rodada(Jogada.SPOCK);
    }

    public void buttonLagartoClick(View view)
    {
        rodada(Jogada.LAGARTO);
    }

    public void rodada(Jogada jogada)
    {
        Jogada jogadaComputador = Jogada.values()[dado.nextInt(5)];
        String mensagem = messagemGame.returnMessage(TABELA[jogada.valor][jogadaComputador.valor].toString(),jogada.valor,jogadaComputador.valor);
        textViewJogadaUsuario.setText(jogada.toString());
        textViewJogadaPc.setText(jogadaComputador.toString());
        switch (TABELA[jogada.valor][jogadaComputador.valor]){
            case VITORIA:
                textViewGanhador.setText("Usuário levou essa!");
                pontosHumano += 3;
                break;
            case DERROTA:
                textViewGanhador.setText("Computador levou essa!");
                pontosComputador += 3;
                break;
            case EMPATE:
                textViewGanhador.setText("Empate!");
                pontosHumano += 1;
                pontosComputador += 1;
                break;
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        atualizaStatus();
    }

    private void atualizaStatus()
    {
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);
        if(pontosHumano < 100 && pontosHumano < 100)
        {
            textViewStatus.setText("Escolha uma opção...");
        }else {
            if(pontosHumano >= 100 && pontosComputador<100){
                textViewStatus.setText("O humano venceu o torneio!");
                iniciaTorneio();
            }else {
                if(pontosHumano < 100 && pontosComputador >= 100)
                {
                    textViewStatus.setText("O computador venceu o torneio!");
                    iniciaTorneio();
                }else {
                    textViewStatus.setText("O torneio terminou empatado!");
                    iniciaTorneio();
                }
            }
        }
    }

    private void iniciaTorneio()
    {
        pontosComputador = 0;
        pontosHumano = 0;
    }

    public void textViewStatusClick(View view)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Reiniciar o torneio?");
        dialogBuilder.setMessage("Deseja reiniciar o torneio zerando o estado atual?");
        dialogBuilder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iniciaTorneio();
                atualizaStatus();
            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
    }


}
package ufjf.br.beatriz.exemplo03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText precoBaseTextField;
    EditText pesoProdutoTextField;
    CheckBox embrulhadoCheckbox;
    CheckBox envioCheckbox;
    RadioGroup pagamentoRadioGroup;
    TextView precoBaseTextView;
    TextView envioTextView;
    TextView pagamentoTextView;
    TextView freteTextView;
    TextView precoFinalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        precoBaseTextField = findViewById(R.id.precoBaseTextField);
        pesoProdutoTextField = findViewById(R.id.pesoProdutoTextField);
        embrulhadoCheckbox = findViewById(R.id.embrulhadoCheckbox);
        envioCheckbox = findViewById(R.id.envioCheckbox);
        pagamentoRadioGroup = findViewById(R.id.pagamentoRadioGroup);
        precoBaseTextView = findViewById(R.id.precoBaseTextView);
        envioTextView = findViewById(R.id.envioTextView);
        pagamentoTextView = findViewById(R.id.pagamentoTextView);
        freteTextView = findViewById(R.id.freteTextView);
        precoFinalTextView = findViewById(R.id.precoFinalTextView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCalcular(v);
            }
        };

        embrulhadoCheckbox.setOnClickListener(listener);
        envioCheckbox.setOnClickListener(listener);
        pagamentoRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onClickCalcular(group);
            }
        });
        pesoProdutoTextField.setOnClickListener(listener);
    }

    public void onClickCalcular(View view)
    {
        Double precoFinal = 0.0;
        Double precoBase = 0.0;
        Double pesoProduto = 0.0;
        Double valorEnvio = 0.0;
        Double pagamento = 0.0;
        Double frete = 0.0;

        Locale locale = new Locale("pt","br");

        // recuperando peso e preço base do produto
        try {
            precoBase = Double.parseDouble(precoBaseTextField.getText().toString());
            pesoProduto = Double.parseDouble(pesoProdutoTextField.getText().toString());
        }catch (Exception e)
        {
            precoBaseTextField.requestFocus();
        }

        precoFinal = precoBase;

        // verificando se algum checkbox foi clicado
        if(embrulhadoCheckbox.isChecked())
        {
            valorEnvio += 5.00;
            precoFinal+=valorEnvio;
        }
        if(envioCheckbox.isChecked())
        {
            valorEnvio+= 12.00;
            precoFinal+=valorEnvio;

        }
        if(valorEnvio != 0.0)
        {
            envioTextView.setText(NumberFormat.getCurrencyInstance(locale).format(valorEnvio));
        } else {
            envioTextView.setText(String.format("R$ 00,00"));
        }

        // recuperando o valor do radio group para saber a decisão de pagamento
        switch (pagamentoRadioGroup.getCheckedRadioButtonId())
        {
            case R.id.umCartaoRadioButton:
                pagamento = 0.03*precoBase;
                break;
            case R.id.tresCartaoRadioButton:
                pagamento=0.06*precoBase;
                break;
            case R.id.seisCartaoRadioButton:
                pagamento=0.09*precoBase;
                break;
        }
        if(pagamento != 0.0)
        {
            precoFinal+=pagamento;
            pagamentoTextView.setText(NumberFormat.getCurrencyInstance(locale).format(pagamento));
        }else {
            pagamentoTextView.setText(String.format("R$ 00,00"));
        }

        // adicionando valor de frete + peso do produto
        if(pesoProduto == 0.0)
        {
            frete = 10.00;
        }else {
            frete = 10.00 + Math.pow(4,pesoProduto);
        }

        precoFinal+=frete;

        freteTextView.setText(NumberFormat.getCurrencyInstance(locale).format(frete));
        precoBaseTextView.setText(NumberFormat.getCurrencyInstance(locale).format(precoBase));
        precoFinalTextView.setText(NumberFormat.getCurrencyInstance(locale).format(precoFinal));
    }
}
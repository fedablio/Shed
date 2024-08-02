package br.com.fedablio.shd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.fedablio.dao.PilotoDAO;
import br.com.fedablio.model.Piloto;
import br.com.fedablio.utility.Porta;

public class CherryActivity extends Activity {

    private EditText etId;
    private EditText etSenha;
    private EditText etNome;
    private EditText etCanal;
    private Spinner spBanda;
    private String _IDI_ = "";
    private String _SEN_ = "";
    private String _NOM_ = "";
    private String _CAN_ = "";
    private String _BAN_ = "";
    Porta porta = new Porta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cherry);
        etId = (EditText) findViewById(R.id.etIdCherry);
        etSenha = (EditText) findViewById(R.id.etSenhaCherry);
        etNome = (EditText) findViewById(R.id.etNomeCherry);
        etCanal = (EditText) findViewById(R.id.etCanalCherry);
        spBanda = (Spinner) findViewById(R.id.spBandaCherry);
        ArrayAdapter<CharSequence> adapterSpiner1 = ArrayAdapter.createFromResource(this, R.array.array01, R.layout.spinner);
        adapterSpiner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBanda.setAdapter(adapterSpiner1);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            _IDI_ = bundle.getString("_IDI_");
            _SEN_ = bundle.getString("_SEN_");
            _NOM_ = bundle.getString("_NOM_");
            _CAN_ = bundle.getString("_CAN_");
            _BAN_ = bundle.getString("_BAN_");
        }
        SharedPreferences shaAutAuto = getSharedPreferences("SENHA_SESSAO", MODE_PRIVATE);
        String senha = shaAutAuto.getString("_senha_", null);
        etId.setText(_IDI_);
        if (senha.length() > 0) {
            etSenha.setText(senha);
        } else {
            etSenha.setText(_SEN_);
        }
        etNome.setText(_NOM_);
        etCanal.setText(_CAN_);
        if (_BAN_.equals("Analog")) {
            spBanda.setSelection(1);
        } else {
            if (_BAN_.equals("Digital")) {
                spBanda.setSelection(2);
            } else {
                spBanda.setSelection(0);
            }
        }
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#757575")));
    }

    public void salvar_cherry(View view) {
        String id = etId.getText().toString();
        String senha = etSenha.getText().toString();
        String nome = etNome.getText().toString();
        String canal = etCanal.getText().toString();
        String banda = spBanda.getSelectedItem().toString();
        if (nome.length() > 0 && canal.length() > 0 && !banda.equals("Your band")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 1
                            // 1
                        }
                    });
                    // 2
                    Piloto pil = new Piloto();
                    PilotoDAO pld = new PilotoDAO();
                    if (id.equals("")) {
                        pil.setSenha_piloto(senha);
                        pil.setNome_piloto(porta.fecha(nome));
                        pil.setCanal_piloto(porta.fecha(canal));
                        pil.setBanda_piloto(porta.fecha(banda));
                        pld.inserir(pil);
                    } else {
                        pil.setNome_piloto(porta.fecha(nome));
                        pil.setCanal_piloto(porta.fecha(canal));
                        pil.setBanda_piloto(porta.fecha(banda));
                        pil.setSenha_piloto(senha);
                        pil.setId_piloto(Integer.parseInt(id));
                        pld.alterar(pil);
                    }
                    // 2
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 3
                            Intent intent = new Intent(CherryActivity.this, BananaActivity.class);
                            startActivity(intent);
                            finish();
                            // 3
                        }
                    });
                }
            }).start();
        } else {
            Toast.makeText(CherryActivity.this, R.string.ttCampoBranco, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cherry, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnCherryBanana) {
            Intent intent = new Intent(CherryActivity.this, BananaActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void excluir_cherry(View view) {
        String id = etId.getText().toString();
        String senha = etSenha.getText().toString();
        if (id.length() > 0 && senha.length() > 0) {
            final Dialog dialog = new Dialog(CherryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_excluir);
            Button btSim = dialog.findViewById(R.id.btSimCherry);
            Button btNao = dialog.findViewById(R.id.btNaoCherry);
            btSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 1
                                    // 1
                                }
                            });
                            // 2
                            PilotoDAO pld = new PilotoDAO();
                            pld.excluir(Integer.parseInt(id), senha);
                            // 2
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 3
                                    Intent intent = new Intent(CherryActivity.this, BananaActivity.class);
                                    startActivity(intent);
                                    finish();
                                    // 3
                                }
                            });
                        }
                    }).start();
                    dialog.dismiss();
                }
            });
            btNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // yes
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            Toast.makeText(CherryActivity.this, R.string.ttNadaExcluir, Toast.LENGTH_SHORT).show();
        }
    }

}
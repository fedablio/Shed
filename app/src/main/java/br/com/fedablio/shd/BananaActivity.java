package br.com.fedablio.shd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.fedablio.adapter.BananaAdapter;
import br.com.fedablio.dao.PilotoDAO;
import br.com.fedablio.model.Piloto;
import br.com.fedablio.utility.Porta;

public class BananaActivity extends Activity {
    private ListView lvPiloto;
    private ArrayAdapter adapter;
    private ArrayList<Piloto> listarPiloto = null;
    private Spinner spBanda;
    private ProgressDialog progressDialog = null;
    private TextView tvSenha;
    private String senha = "";
    Porta porta = new Porta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana);
        SharedPreferences shaAutAuto = getSharedPreferences("SENHA_SESSAO", MODE_PRIVATE);
        senha = shaAutAuto.getString("_senha_", null);
        lvPiloto = (ListView) findViewById(R.id.lvPilotoBanana);
        spBanda = (Spinner) findViewById(R.id.spBandaBanana);
        ArrayAdapter<CharSequence> adapterSpiner1 = ArrayAdapter.createFromResource(this, R.array.array01, R.layout.spinner);
        adapterSpiner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBanda.setAdapter(adapterSpiner1);
        tvSenha = (TextView) findViewById(R.id.tvSenhaBanana);
        tvSenha.setText(porta.abre(senha));
        listar();
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#757575")));
    }

    private void listar() {
        String banda = spBanda.getSelectedItem().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 1
                        progressDialog = new ProgressDialog(BananaActivity.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.dialog_progress);
                        progressDialog.setCancelable(true);
                        // 1
                    }
                });
                // 2
                listarPiloto = new PilotoDAO().listarPilotoSenha(senha, porta.fecha(banda));
                adapter = new BananaAdapter(BananaActivity.this, listarPiloto);
                // 2
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 3
                        progressDialog.dismiss();
                        lvPiloto.setAdapter(adapter);
                        lvPiloto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                String id = String.valueOf(listarPiloto.get(pos).getId_piloto());
                                String senha = String.valueOf(listarPiloto.get(pos).getSenha_piloto());
                                String nome = listarPiloto.get(pos).getNome_piloto();
                                String canal = listarPiloto.get(pos).getCanal_piloto();
                                String banda = listarPiloto.get(pos).getBanda_piloto();
                                Intent intent = new Intent(BananaActivity.this, CherryActivity.class);
                                intent.putExtra("_IDI_", id);
                                intent.putExtra("_SEN_", senha);
                                intent.putExtra("_NOM_", nome);
                                intent.putExtra("_CAN_", canal);
                                intent.putExtra("_BAN_", banda);
                                startActivity(intent);
                                finish();
                            }
                        });
                        // 3
                    }
                });
            }
        }).start();
    }

    public void pesquisar_banana(View view) {
        listar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_banana, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnBananaCherry) {
            Intent intent = new Intent(BananaActivity.this, CherryActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.mnBananaApple) {
            Intent intent = new Intent(BananaActivity.this, AppleActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

package br.com.fedablio.shd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.fedablio.dao.AcessoDAO;
import br.com.fedablio.dao.ConnectionFactory;
import br.com.fedablio.model.Acesso;
import br.com.fedablio.utility.Porta;
import br.com.fedablio.utility.Utilitario;

public class AppleActivity extends Activity {

    private EditText etSenha;
    private ImageView ivDrone;
    Porta porta = new Porta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utilitario uti = new Utilitario();
        if (uti.internet(AppleActivity.this)) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_apple);
            etSenha = (EditText) findViewById(R.id.etSenhaApple);
            ivDrone = (ImageView) findViewById(R.id.tvDroneApple);
            entra();
            SharedPreferences sp = getSharedPreferences("SENHA_SESSAO", MODE_PRIVATE);
            String senha = sp.getString("_senha_", null);
            if (senha == null) {
                etSenha.setText("");
            } else {
                etSenha.setText(porta.abre(senha));
            }
        } else {
            Intent intent = new Intent(AppleActivity.this, GuavaActivity.class);
            startActivity(intent);
            finish();
        }
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#757575")));
    }

    private void entra() {
        ConnectionFactory.url = porta.abre("J9QaxU2GzeRX/nM2ppSX");
        ConnectionFactory.por = porta.abre("Z5VcxA==");
        ConnectionFactory.ban = porta.abre("IZ9VwU+J2r8RvUs34Jic");
        ConnectionFactory.use = porta.abre("IZ9VwU+J2r8RvUsg7ZWL");
        ConnectionFactory.pas = porta.abre("FZIO0Q3sj9lD7X4G");
    }

    private void registra() {
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
                Acesso ace = new Acesso();
                AcessoDAO acd = new AcessoDAO();
                ace.setData(new java.sql.Date(new java.util.Date().getTime()));
                ace.setHora(new java.sql.Time(new java.util.Date().getTime()));
                acd.registrar(ace);
                // 2
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 3
                        // 3
                    }
                });
            }
        }).start();
    }

    public void abrir_apple(View view) {
        String senha = etSenha.getText().toString();
        if (senha.length() > 0) {
            SharedPreferences.Editor editor = getSharedPreferences("SENHA_SESSAO", MODE_PRIVATE).edit();
            editor.putString("_senha_", porta.fecha(senha));
            editor.commit();
            registra();
            Intent intent = new Intent(AppleActivity.this, BananaActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(AppleActivity.this, R.string.ttCampoBranco, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_apple, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnAppleCocoa) {
            Intent intent = new Intent(AppleActivity.this, CocoaActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void curioso(View view){
        Toast.makeText(AppleActivity.this, R.string.ttCurioso, Toast.LENGTH_SHORT).show();
    }

}
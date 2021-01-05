package com.example.agendaalunos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.agendaalunos.converter.AlunoConverter;
import com.example.agendaalunos.dao.AlunoDAO;
import com.example.agendaalunos.modelo.Aluno;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class EnviaAlunosTask extends AsyncTask<Object, Object,String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Aguarde","Enviando alunos...",true,true);
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = null;
        try {
            json = conversor.converterParaJSON(alunos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebClient client = new WebClient();
        String resposta = null;
        try {
            resposta = client.post(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta;
    }
    // executa pós execução
    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta, Toast.LENGTH_LONG).show();    }
}

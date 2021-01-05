package com.example.agendaalunos.converter;

import com.example.agendaalunos.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class AlunoConverter {
    public String converterParaJSON(List<Aluno> alunos) throws JSONException {
        JSONStringer js = new JSONStringer();

        js.object().key("list").array().object().key("alunos").array();
        for(Aluno aluno : alunos){
            js.object();
            js.key("nome").value(aluno.getNome());
            js.key("nota").value(aluno.getNota());
            js.endObject();
        }
        js.endArray().endObject().endArray().endObject();

        return js.toString();
    }
}

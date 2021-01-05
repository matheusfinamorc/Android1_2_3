package com.example.agendaalunos;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.agendaalunos.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ListaProvasFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);
        List<String> topicosPort = Arrays.asList("Sujeito","Objeto Direto","Objeto Indireto");
        Prova provaPortugues = new Prova("Portugues","04/01/2021",topicosPort);

        List<String> topicosMat = Arrays.asList("Trigonometria","Matriz");
        Prova provaMatematica = new Prova("Matematica","05/01/2021",topicosMat);

        List<Prova> provas = Arrays.asList(provaMatematica, provaPortugues);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,provas);

        ListView lista = view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // pega o item na posicaçao "i" para mostrar
                Prova prova = (Prova) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(),"Clicou na prova "+prova,Toast.LENGTH_LONG).show();
                Intent vaiParaDetalhes = new Intent(getContext(),DetalhesProvasActivity.class);
                vaiParaDetalhes.putExtra("prova",prova);

                startActivity(vaiParaDetalhes);
            }
        });

        return view;
    }
}

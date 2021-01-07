package com.example.agendaalunos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.agendaalunos.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        //substitui quem pelo oque
        tx.replace(R.id.frame_principal, new ListaProvasFragment());

        //devolve o false ou true
        if (getResources().getBoolean(R.bool.modoPaisagem)) {
            tx.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }
        tx.commit();

    }

    public void selecionarProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if(!getResources().getBoolean(R.bool.modoPaisagem)) {
            FragmentTransaction tx = manager.beginTransaction();

            DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova",prova);
            detalhesProvaFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal,detalhesProvaFragment);
            tx.addToBackStack(null);
            tx.commit();
        }else{
            DetalhesProvaFragment detalhesFragment =
                    (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.poupulaCamposCom(prova);
        }
    }
}


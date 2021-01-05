package com.example.agendaalunos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agendaalunos.ListaAlunosActivity;
import com.example.agendaalunos.R;
import com.example.agendaalunos.modelo.Aluno;

import java.util.List;

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null) {

            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }
        TextView campoNome = view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        TextView campoSite = view.findViewById(R.id.item_site);
        if(campoSite != null){
            campoSite.setText(aluno.getSite());
        }

        TextView campoEndereco = view.findViewById(R.id.item_endereco);
        if(campoEndereco !=null){
            campoEndereco.setText(aluno.getEndereco());
        }


        ImageView campoFoto = view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //reduzir a escala da foto
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}

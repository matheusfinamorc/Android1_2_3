package com.example.agendaalunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.agendaalunos.dao.AlunoDAO;

public class SMSreceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object [] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte [] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        // pega o celular que mandou a mensagem
        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);
        if(dao.ehAluno(telefone)){
            Toast.makeText(context,"Chegou um SMS de Aluno", Toast.LENGTH_LONG).show();
        }
        dao.close();
    }
}

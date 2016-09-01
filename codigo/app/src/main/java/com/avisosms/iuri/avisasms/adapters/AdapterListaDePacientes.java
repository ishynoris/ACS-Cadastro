package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.nhaarman.listviewanimations.ArrayAdapter;

import java.util.List;

import io.realm.Realm;

/**
 * Created by iuri on 6/8/2016.
 */
public class AdapterListaDePacientes extends ArrayAdapter<Paciente> {

    Context mContext;

    public AdapterListaDePacientes(Context context, List<Paciente> objects) {

        addAll(objects);
        this.mContext = context;
    }

    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    static class ViewHolder {
        TextView textDescricao;
        TextView textSubDescricao;
        CheckBox checkBox;
        View view;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Paciente p = getItem(position);
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
                    /*if (row == null) {*/ //Checkbox precisa ter uma lista exclusiva, http://webcache.googleusercontent.com/search?q=cache:http://stackoverflow.com/questions/16350670/listview-viewholder-checkbox-state&gws_rd=cr&ei=5sFXV_G6M4f4wASc5LjIDg
            // row = LayoutInflater.from(mContext).inflate(R.layout.dynamiclistview_list_row, parent, false);
            /*LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dynamiclistview_list_row, null/*, parent, false);*/

            holder = new ViewHolder();
            row = LayoutInflater.from(mContext).inflate(R.layout.dynamiclistview_list_row, parent, false);

            holder.textDescricao = (TextView) row.findViewById(R.id.list_row_draganddrop_textview_titulo);
            holder.textSubDescricao = (TextView) row.findViewById(R.id.list_row_draganddrop_textview_subtitulo);
            holder.checkBox = (CheckBox) row.findViewById(R.id.list_row_draganddrop_checkbox);
            holder.view = (View) row.findViewById(R.id.list_row_draganddrop_cor);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textDescricao.setText(p.getNome());
        holder.textSubDescricao.setText(p.getTelefone());

        final ViewHolder finalHolder = holder;
        final View finalRow = row;
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()) {
                    p.setAtendido(true);
                } else {
                    p.setAtendido(false);
                }

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(p.getTelefone().split("/")[0].replaceAll("\\(","").replaceAll("\\)","").replaceAll(" ","").replaceAll("/","").replaceAll("-",""), null, "sms message", null, null);

                Toast.makeText(v.getContext(), p.getId() + " " + p.getNome(), Toast.LENGTH_SHORT).show();
                /*SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(p.getTelefone().split("/")[0].replaceAll("\\(","").replaceAll("\\)","").replaceAll(" ","").replaceAll("/","").replaceAll("-",""), null, "sms message", null, null);

                Toast.makeText(v.getContext(), p.getId() + " " + p.getNome(), Toast.LENGTH_SHORT).show();*/
                realm.commitTransaction();
                realm.close();

               // notifyDataSetInvalidated();
            }
        });
        
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        finalHolder.view.setBackground(finalRow.getResources().getDrawable(R.drawable.icon_retangulo_azul));
                        finalHolder.textDescricao.setPaintFlags(finalHolder.textDescricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        finalHolder.textSubDescricao.setPaintFlags(finalHolder.textSubDescricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    //getItem(position).setAtendido(true);

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        finalHolder.view.setBackground(finalRow.getResources().getDrawable(R.drawable.icon_retangulo_amarelo));
                        finalHolder.textDescricao.setPaintFlags(finalHolder.textDescricao.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        finalHolder.textSubDescricao.setPaintFlags(finalHolder.textSubDescricao.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                    //getItem(position).setAtendido(false);
                }

                // Toast.makeText(buttonView.getContext(), getItemId(position) + " " + marcados.size(), Toast.LENGTH_SHORT).show();
            }

        });

        if (p.isAtendido()) {//o paciente ja foi atendido
            holder.checkBox.setChecked(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.view.setBackground(row.getResources().getDrawable(R.drawable.icon_retangulo_azul));
            } else {
                holder.view.setBackgroundDrawable(convertView.getResources().getDrawable(R.drawable.icon_retangulo_azul));
            }
            holder.textDescricao.setPaintFlags(holder.textDescricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textSubDescricao.setPaintFlags(holder.textSubDescricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {//ainda nao foi atendido
            holder.checkBox.setChecked(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.view.setBackground(row.getResources().getDrawable(R.drawable.icon_retangulo_amarelo));
            } else {
                holder.view.setBackgroundDrawable(convertView.getResources().getDrawable(R.drawable.icon_retangulo_amarelo));
            }

            holder.textDescricao.setPaintFlags(holder.textDescricao.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textSubDescricao.setPaintFlags(holder.textSubDescricao.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }


        //set on click

        /*((TextView) view.findViewById(R.id.list_row_draganddrop_textview_titulo)).setText("Paciente:" + getItem(position));
        ((TextView) view.findViewById(R.id.list_row_draganddrop_textview_subtitulo)).setText("Telefone: (79) 9 9670-2237");
*/
        return row;
    }

    @NonNull
    public View getUndoView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.dynamiclistview_undo_row, parent, false);
        }
        return view;
    }

    @NonNull
    public View getUndoClickView(@NonNull final View view) {
        return view.findViewById(R.id.undo_row_undobutton);
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}

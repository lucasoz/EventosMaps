package com.lucas.oz.eventify;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment {

    private ListView mListViewNoticias;
    private List<Evento> mListaEventos;

    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragmento = inflater.inflate(R.layout.fragment_noticias, container, false);
        mListViewNoticias = viewFragmento.findViewById(R.id.miListView);
        mListaEventos = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> listaEventosParce, ParseException e) {
                if (e == null) {
                    Log.d("evento", "Obtenidos " + listaEventosParce.size() + " eventos");

                    for(ParseObject objetoParse:listaEventosParce){
                        String titulo = (String) objetoParse.get("titulo");
                        String enlace = (String) objetoParse.get("enlace");
                        String enlaceImagen = (String) objetoParse.get("enlaceImagen");
                        String categoria = (String) objetoParse.get("categoria");
                        double latitud = (double) objetoParse.get("longitud");
                        double longitud = (double) objetoParse.get("latitud");
                        Evento nuevoEvento = new Evento(titulo,enlace,enlaceImagen,categoria,latitud,longitud);
                        mListaEventos.add(nuevoEvento);
                    }

                    mListViewNoticias.setAdapter(new AdaptadorEventos());
                } else {
                    Log.d("evento", "Error: " + e.getMessage());
                }
            }
        });

        mListViewNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoPresionado = mListaEventos.get(position);
                String  enlace = eventoPresionado.getEnlace();
                abrirNavegador(enlace);
            }
        });
        return viewFragmento;

    }

    public void abrirNavegador(String enlace){
        Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(enlace));
        startActivity(intentNavegador);
    }

    private class AdaptadorEventos extends BaseAdapter{

        @Override
        public int getCount() {
            return mListaEventos.size();
        }

        @Override
        public Object getItem(int position) {
            return mListaEventos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View filaView = getActivity().getLayoutInflater().inflate(R.layout.fila,null);

            Evento nuevoEvento = mListaEventos.get(position);

            TextView tVFila = filaView.findViewById(R.id.textView);
            tVFila.setText(nuevoEvento.getTitulo());

            ImageView imageViewFila = filaView.findViewById(R.id.imageViewNoticia);
            Picasso.get().load(nuevoEvento.getEnlace_imagen()).into(imageViewFila);
            return filaView;
        }
    }
}

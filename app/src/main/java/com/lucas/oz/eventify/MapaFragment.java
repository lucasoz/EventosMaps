package com.lucas.oz.eventify;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean mLocationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                0);

        getMapAsync(this);

    }

    public void onMapReady(GoogleMap map) {
        mMap = map;

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
                        mMap.addMarker(opcionesMarcadorDeEvento(nuevoEvento));
                    }

                } else {
                    Log.d("evento", "Error: " + e.getMessage());
                }
            }
        });


        //localizacion actual;
        getLocationPermission();
        updateLocationUI();

    }

    public MarkerOptions opcionesMarcadorDeEvento (Evento evento){
        MarkerOptions opcionesMarcador = new MarkerOptions();
        opcionesMarcador.position(new LatLng(evento.getLatitud(),evento.getLongitud()));
        opcionesMarcador.icon(evento.iconoDeCategoria());
        opcionesMarcador.title(evento.getTitulo());
        return opcionesMarcador;
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {


            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

}

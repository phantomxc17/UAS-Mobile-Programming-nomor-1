package com.example.uas1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.map);
        map.setMultiTouchControls(true);

        GeoPoint binus = new GeoPoint(-6.9153653, 107.5886954);
        GeoPoint braga = new GeoPoint(-6.9178283, 107.6045685);
        GeoPoint alunAlun = new GeoPoint(-6.9218295, 107.6021967);
        GeoPoint gazibu = new GeoPoint(-6.9002779, 107.6161296);

        addMarker(binus, "Kampus Binus");
        addMarker(braga, "Braga");
        addMarker(alunAlun, "Alun-Alun Kota Bandung");
        addMarker(gazibu, "Lapangan Gazibu");

        map.getController().setZoom(14.0);
        map.getController().setCenter(binus);

        findViewById(R.id.btnBinus).setOnClickListener(v -> recenterMap(binus));
        findViewById(R.id.btnBraga).setOnClickListener(v -> recenterMap(braga));
        findViewById(R.id.btnAlunAlun).setOnClickListener(v -> recenterMap(alunAlun));
        findViewById(R.id.btnGazibu).setOnClickListener(v -> recenterMap(gazibu));
    }

    private void addMarker(GeoPoint position, String title) {
        Marker marker = new Marker(map);
        marker.setPosition(position);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);
        map.getOverlays().add(marker);
    }

    private void recenterMap(GeoPoint location) {
        map.getController().animateTo(location);
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}

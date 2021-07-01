package com.example.androidapi_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class FilterActivity extends AppCompatActivity {

    // esta clase va ligada a la pestaña donde se seleccionan los filtros.

    private Button btnMint;
    private Button btnMaterial;
    private Button btnDenomination;
    private Button btnCancelar;
    private Button btnAplicarFiltro;
    private Button btnBorrarFiltros;

    private List<String> listaMint;
    private List<String> listaMaterial;
    private List<String> listaDenomination;

    private Set<String> mintFiltersSet;
    private Set<String> materialFiltersSet;
    private Set<String> denominationFiltersSet;


    private static String selectedMint;
    private static String selectedDenomination;
    private static String selectedMaterial;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initializeFilters();

        btnMint = findViewById(R.id.btnFiltrarPorMint);
        btnMaterial = findViewById(R.id.btnFiltrarPorMaterial);
        btnDenomination = findViewById(R.id.btnFiltrarPorDenomination);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnAplicarFiltro = findViewById(R.id.btnAplicarFiltros);
        btnBorrarFiltros = findViewById(R.id.btnRemoveFilters);

        addListenersButtons();

    }

    //-------------------metodo para iniciar filtros
    private void initializeFilters() {

        mintFiltersSet = new HashSet<>();
        materialFiltersSet = new HashSet<>();
        denominationFiltersSet = new HashSet<>();

        List<Coin> coins = MainActivity.getCoinList();

        for (int i = 0; i < coins.size(); i++) {
            mintFiltersSet.add(coins.get(i).getMint());
            materialFiltersSet.add(coins.get(i).getMaterial());
            denominationFiltersSet.add(coins.get(i).getDenomination());
        }

        listaMint = new ArrayList<>(mintFiltersSet);
        listaMaterial = new ArrayList<>(materialFiltersSet);
        listaDenomination = new ArrayList<>(denominationFiltersSet);

    }

    //-----------------Metodo para mostrar lista de filtros con "AlertDialog"
    private void showOptions(List<String> filters, String filter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] arraydatos = filters.toArray(new String[0]);
        builder.setTitle("Elige el " + filter + ": ")
                .setItems(arraydatos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedFilter = arraydatos[which];
                        if (FilterConstants.DENOMINATION_FILTER.equals(filter)) {
                            selectedDenomination = selectedFilter;
                        } else if (FilterConstants.MATERIAL_FILTER.equals(filter)) {
                            selectedMaterial = selectedFilter;
                        } else if (FilterConstants.MINT_FILTER.equals(filter)) {
                            selectedMint = selectedFilter;
                        }
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
    }

    //-------- on click listeners

    private void addListenersButtons() {
        btnMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions(listaMint, FilterConstants.MINT_FILTER);
            }
        });

        btnMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions(listaMaterial, FilterConstants.MATERIAL_FILTER);
            }
        });

        btnDenomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions(listaDenomination, FilterConstants.DENOMINATION_FILTER);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar(v);
            }
        });

        btnAplicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrar();
            }
        });

        btnBorrarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFilters();
            }
        });

    }

    public static String getSelectedMint() {
        return selectedMint;
    }

    public static void setSelectedMint(String selectedMint) {
        FilterActivity.selectedMint = selectedMint;
    }

    public static String getSelectedDenominacion() {
        return selectedDenomination;
    }

    public static void setSelectedDenominacion(String selectedDenominacion) {
        FilterActivity.selectedDenomination = selectedDenominacion;
    }

    public static String getSelectedMaterial() {
        return selectedMaterial;
    }

    public static void setSelectedMaterial(String selectedMaterial) {
        FilterActivity.selectedMaterial = selectedMaterial;
    }

// otros metodos

    private void cancelar(View view) {
        super.onBackPressed();
    }

    private void filtrar() {
        onBackPressed();
    }

    public void removeFilters() {
        MainActivity.removeFilters();
        selectedDenomination = null;
        selectedMint = null;
        selectedMaterial = null;
    }

}
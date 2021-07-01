package com.example.androidapi_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements CoinAdapter.OnClickListenerCoin {

    private RecyclerView recyclerView;
    private FloatingActionButton btnFlotante;
    private static List<Coin> coinList;
    private static List<Coin> filteredCoins;
    private CoinAdapter coinAdapter;


    FloatingActionButton floatingButtonPrevious;
    FloatingActionButton floatingButtonNext;
    private int totalCoins;
    private int counterLimit = 10;
    private int offest = 0; // cogerlo de la coin activity
    private String offset2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        coinList = new ArrayList<>();
        filteredCoins = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        btnFlotante = findViewById(R.id.floatingActionButton);

        floatingButtonPrevious = findViewById(R.id.floatingButtonPrevious);
        floatingButtonNext = findViewById(R.id.floatingButtonNext);

        //--------------------
        offset2 = getIntent().getStringExtra("offset");
        if(offset2 == null){

        } else {
            offest = Integer.parseInt(offset2);
        }
        //--------------------

        /*
        GetData getData = new GetData();
        getData.execute();

         */

        new Thread1().start();

        btnFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFilters();
            }
        });

        setBtnListeners();
    }

    @Override
    public void onResume() {
        //sobre escribimos este metodo porque es aqui donde se retrocede al cargar la aplicacion

        //coinList = getCoinList();

        super.onResume();



        // codigo aqui..
        Toast.makeText(this, "OnResume Cargado", Toast.LENGTH_SHORT).show();
        // aqui hacer que se actualice la lista de monedas cuando se devuelva un filtro.

        String selectedDenomination = FilterActivity.getSelectedDenominacion();
        String selectedMaterial = FilterActivity.getSelectedMaterial();
        String selectedMint = FilterActivity.getSelectedMint();

        Log.d("Mint>>>>", selectedMint+"");
        Log.d("Material>>>>", selectedMaterial+"");
        Log.d("Denomination>>>>", selectedDenomination+"");

        // mediante un predicate se realiza el filtrado de monedas.
        Predicate<Coin> denominationPredicate = Objects.isNull(selectedDenomination) ? coin -> true : coin -> coin.getDenomination().equals(selectedDenomination);
        Predicate<Coin> mintPredicate = Objects.isNull(selectedMint) ? coin -> true : coin -> coin.getMint().equals(selectedMint);
        Predicate<Coin> materialPredicate = Objects.isNull(selectedMaterial) ? coin -> true : coin -> coin.getMaterial().equals(selectedMaterial);

        if (Objects.isNull(selectedDenomination) && Objects.isNull(selectedMaterial) && Objects.isNull(selectedMint)) {
            //filteredCoins.addAll(coinList); // problema aqui
            filteredCoins.clear();
            floatingButtonPrevious.setEnabled(true);
            floatingButtonNext.setEnabled(true);
            floatingButtonNext.setVisibility(View.VISIBLE);
            floatingButtonPrevious.setVisibility(View.VISIBLE);


            PutDataIntoRecyclerView(coinList);
            Log.d("HH","HH");

        } else {
            //se vacia la lista de monedas ty se vuelve a rellenar
            filteredCoins.clear();
            filteredCoins.addAll(coinList.stream()
                    .filter(denominationPredicate.and(mintPredicate).and(materialPredicate))
                    .collect(Collectors.toList()));

            floatingButtonNext.setEnabled(false);
            floatingButtonPrevious.setEnabled(false);
            floatingButtonNext.setVisibility(View.INVISIBLE);
            floatingButtonPrevious.setVisibility(View.INVISIBLE);


            Log.d("AA","AA");
        }

        if (Objects.isNull(coinAdapter)) {
            coinAdapter = new CoinAdapter(this, new ArrayList<>(), this);
        }

        Log.d("tamaño monedas filtradas-_> ", String.valueOf(filteredCoins.size()));

        //coinAdapter.refresh(filteredCoins);

        if(filteredCoins.size() != 0){
            PutDataIntoRecyclerView(filteredCoins);
        } else {
            PutDataIntoRecyclerView(coinList);
        }



    }

    //-----------------------Metodo que se encarga de obtener los datos del recyclerView--------------------------
    // lo que hace es distinguir entre que lista está cargada, obtener el indice y meter los datos en un "Intent"
    @Override
    public void onClickCoin(int position) {
        Intent intent = new Intent(this, CoinActivity.class);
         Log.d("POSICION CLICK -->",position+"");


        if(filteredCoins.size() != 0){
            String id = filteredCoins.get(position).getId();
            String number = filteredCoins.get(position).getNumber() ;
            String mint = filteredCoins.get(position).getMint();
            String image_obverse = filteredCoins.get(position).getImage_obverse();
            String image_reverse = filteredCoins.get(position).getImage_reverse();
            String date_in = filteredCoins.get(position).getDate_in();
            String date_out = filteredCoins.get(position).getDate_out();
            String material = filteredCoins.get(position).getMaterial();
            String denomination = filteredCoins.get(position).getDenomination();

            Log.d("Moneda...>", filteredCoins.get(position).toString());

            intent.putExtra("id", id);
            intent.putExtra("number", number);
            intent.putExtra("mint", mint);
            intent.putExtra("image_obverse", image_obverse);
            intent.putExtra("image_reverse", image_reverse);
            intent.putExtra("dete_in", date_in);
            intent.putExtra("date_out", date_out);
            intent.putExtra("material", material);
            intent.putExtra("denomination", denomination);

            intent.putExtra("offset", offest+"");

        } else if(coinList.size() != 0){
            String id = coinList.get(position).getId();
            String number = coinList.get(position).getNumber() ;
            String mint = coinList.get(position).getMint();
            String image_obverse = coinList.get(position).getImage_obverse();
            String image_reverse = coinList.get(position).getImage_reverse();
            String date_in = coinList.get(position).getDate_in();
            String date_out = coinList.get(position).getDate_out();
            String material = coinList.get(position).getMaterial();
            String denomination = coinList.get(position).getDenomination();

            Log.d("Moneda...>", coinList.get(position).toString());


            intent.putExtra("id", id);
            intent.putExtra("number", number);
            intent.putExtra("mint", mint);
            intent.putExtra("image_obverse", image_obverse);
            intent.putExtra("image_reverse", image_reverse);
            intent.putExtra("dete_in", date_in);
            intent.putExtra("date_out", date_out);
            intent.putExtra("material", material);
            intent.putExtra("denomination", denomination);

            intent.putExtra("offset", offest+"");

        }

        startActivity(intent);
    }

    //-----------------------------------------------------

    //con esta clase se realiza la conexion, se crea un hilo de ejecucion en segundo plano.
    private class Thread1 extends Thread {
        ConnectionHandler connectionHandler = new ConnectionHandler();

        @Override
        public void run() {
            try {
                ConnectionFilter connectionFilter = new ConnectionFilter();

                connectionFilter.setDedalo_get("records");
                connectionFilter.setTable("coins");
                connectionFilter.setCode("654asdiKrhdTetQksluoQaW2");
                connectionFilter.setDb_name("web_numisdata_mib"); // utilizar esta: web_numisdata_mib
                connectionFilter.setLang("lg-eng");
                connectionFilter.setOrder("section_id ASC");
                connectionFilter.setLimit(counterLimit);
                connectionFilter.setOffset(offest);
                HttpsURLConnection conn = connectionHandler.makeConnection(connectionFilter);
                Log.d("Lista-->", coinList.toString());
                coinList.removeAll(coinList);
                Log.d("Lista borr->", coinList.toString());
                coinList = connectionHandler.getJsonData(conn);
                Log.d("Lista-- Carr>", coinList.toString());

                // con este metodo se accede al hilo principal para mostrar la informacion
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PutDataIntoRecyclerView(coinList);
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public List<Coin> getCoinList(){
            return  coinList;
        }
    }


    public static List<Coin> getCoinList() {
        return coinList;
    }


    // metodo para mostrar la informacion
    private void PutDataIntoRecyclerView(List<Coin> listaDatos) {
        CoinAdapter coinAdapter = new CoinAdapter(this, listaDatos, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(coinAdapter);
    }

    private void goToFilters() {
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }
    public static void removeFilters() {
        filteredCoins.clear();
        filteredCoins.addAll(getCoinList());
    }


    public void setBtnListeners(){
        floatingButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offest == 0){
                    return;
                }
                offest -= 10;

                new Thread1().start();
            }
        });

        floatingButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offest += 10;

                new Thread1().start();

            }
        });

    }


    /*
    private class GetData extends AsyncTask<String, String, ArrayList<Coin>> {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        ArrayList<Coin> lista_datos = new ArrayList<Coin>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected ArrayList<Coin> doInBackground(String... strings) {
            ConnectionFilter connectionFilter = new ConnectionFilter();

            connectionFilter.setDedalo_get("records");
            connectionFilter.setTable("coins");
            connectionFilter.setCode("654asdiKrhdTetQksluoQaW2");
            connectionFilter.setDb_name("web_numisdata_mib");
            connectionFilter.setLang("lg-eng");
            connectionFilter.setLimit("20");



           HttpsURLConnection conn = connectionHandler.makeConnection(connectionFilter);
            Log.d("------> 1; ","1");

            lista_datos = connectionHandler.getJsonData(conn);
            Log.d("-------->1;" ,"2");
            return lista_datos;
        }

        @Override
        protected void onPostExecute(ArrayList<Coin> s) {
            super.onPostExecute(s);

            PutDataIntoRecyclerView(lista_datos);


        }
    }

     */


}
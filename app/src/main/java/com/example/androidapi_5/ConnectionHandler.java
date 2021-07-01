package com.example.androidapi_5;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionHandler {
    /*
    Clase encargada para hacer la conexion a la API
     */


    List<Coin> lista_datos = new ArrayList<Coin>();
    Coin coin;

    //---------------------Construcotr---------------
    public ConnectionHandler() {

    }


    //--------------------Meotodo para la conexion-----------------------------
    public HttpsURLConnection makeConnection(ConnectionFilter connectionFilter) {

        HttpsURLConnection conn = null;

        try {
            // definimos URL y tipo de conexion
            URL url = new URL("https://monedaiberica.org/dedalo/lib/dedalo/publication/server_api/v1/json/");
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            Log.d("Prueba 1 --->", "Todo OK");

            // cargamos los parametros que se pasará a la API dentro de un JSON
            JSONObject jsonParam = new JSONObject();

            jsonParam.put("dedalo_get",connectionFilter.getDedalo_get());
            jsonParam.put("code",connectionFilter.getCode());
            jsonParam.put("db_name",connectionFilter.getDb_name());
            jsonParam.put("table",connectionFilter.getTable());
            jsonParam.put("ar_fields",connectionFilter.getAr_fields());
            jsonParam.put("section_id",connectionFilter.getSection_id());
            jsonParam.put("sqñ_fullselect",connectionFilter.getSql_fullselect());
            jsonParam.put("sql_filter",connectionFilter.getSql_filter());
            jsonParam.put("lang",connectionFilter.getLang());
            jsonParam.put("order",connectionFilter.getOrder());
            jsonParam.put("limit",connectionFilter.getLimit());
            jsonParam.put("group",connectionFilter.getGroup());
            jsonParam.put("offset",connectionFilter.getOffset());
            jsonParam.put("count",connectionFilter.isCount());


            //Log.d("Responese code: -->", String.valueOf(conn.getResponseCode()));
            Log.d("Prueba 2 --->", "Todo OK");

            Log.d("JSON", jsonParam.toString());

            // emviamos el JSON a la API.
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            // limpiamos canasles y cerramos flujos de datos
            os.flush();
            os.close();



        } catch (Exception ex) {
            //Log.d("Exception 1 --> ", ex.toString());
            ex.printStackTrace();
        }

        return conn;
    }

    //-------------------------Metodo para obtener los datos---------------------------------------
    public List<Coin> getJsonData(HttpsURLConnection conn) {
        try {
            //Log.d("Responese code: -->", String.valueOf(conn.getResponseCode()));
            if (conn.getResponseCode() == 200) {

                // obtenemos el resultado de la conexion
                InputStream in = new BufferedInputStream(conn.getInputStream());

                // lo guardamos todo dentro de un string
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();


                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                Log.d("Prueba 3 --->", "Todo OK");
                // lo guardamos todo dentro de un JSON
                String jsonStr = sb.toString();

                Log.d("RESPUESTA", "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.d("JSON", String.valueOf(jsonObj));


                    // Obtenemos la informacion del JSON (accedemos al nodo principal)
                    JSONArray datos = jsonObj.getJSONArray("result");


                    // bucle para sacar todos los datos (se guardan en una lista)
                    for (int i = 0; i < datos.length(); i++) {
                        coin = new Coin();
                        JSONObject objetoJson = datos.getJSONObject(i);

                        coin.setId(objetoJson.getString("section_id"));
                        coin.setNumber(objetoJson.getString("number"));
                        coin.setMint(objetoJson.getString("mint_name")); //probar con mint o mint_name

                        String urlImageObverse = objetoJson.getString("image_obverse");
                        String urlImageReverse = objetoJson.getString("image_reverse");

                        if(urlImageObverse.contains("http") || urlImageReverse.contains("http")){
                            coin.setImage_obverse(objetoJson.getString("image_obverse"));
                            coin.setImage_reverse(objetoJson.getString("image_reverse"));
                        } else {
                            coin.setImage_obverse("https:monedaiberica.org"+objetoJson.getString("image_obverse"));
                            coin.setImage_reverse("https:monedaiberica.org"+objetoJson.getString("image_reverse"));
                        }
                        coin.setDate_in(objetoJson.getString("date_in"));
                        coin.setDate_out(objetoJson.getString("date_out"));
                        coin.setMaterial(objetoJson.getString("material"));
                        coin.setDenomination(objetoJson.getString("denomination"));

                        lista_datos.add(coin);

                        /*
                        Log.d("tabla--------->", coin.getId() + " " +coin.getNumber() + " " + coin.getMint() + " " + coin.getImage_obverse() + " " + coin.getImage_reverse()
                                + " " + coin.getDate_in() + " " + coin.getDate_out() + " " + coin.getMaterial() + " " + coin.getDenomination());

                         */
                    }
                }
                Log.d("Prueba 4 --->", "Todo OK");

            }


        } catch (Exception ex) {
            ex.printStackTrace();
            //Log.d("Exception 2--> ", ex.toString());
        }
        return lista_datos;
    }

}

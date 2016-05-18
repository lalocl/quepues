package com.wordpress.appsandroidsite.quepues.soap;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Aula10 on 12/05/2016.
 */
public class Peticion {
    private static final String TAG = "Peticion";


    private JSONObject jsonParam;
    private JSONArray jsonArray;
    private String metodo;
    private HttpURLConnection urlConnection;
  //  private JsonTransformer json;
    private  ConectarSW conexion;
    Url nuevaUrl= null;
    ArrayList<Url> lista=null;


    public ArrayList<Url> verListaUrls(String mod){
        String fecha="";

        if(mod!=null) {
           fecha = "?ultima_mod=" + mod;
        }


        lista= new ArrayList<Url> ();

        JsonArrayRequest rq = new JsonArrayRequest(Constants.getHTTP_ext_url()+fecha, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    if(response.length()>0){
                        for(int i =0;i<response.length();i++){
                            jsonParam= response.getJSONObject(i);
                            nuevaUrl= new Url();
                            nuevaUrl.setUrl(jsonParam.getString("url"));
                            nuevaUrl.setSubCategoria(jsonParam.getString("subCategoria"));
                            nuevaUrl.setCodigo_categoria(jsonParam.getString("categoria"));
                            nuevaUrl.setCodigo_test(jsonParam.getString("test"));
                            nuevaUrl.setUltima_mod(jsonParam.getString("ultimaMod"));
                            nuevaUrl.setUrl_ID(jsonParam.getInt("id"));
                            lista.add(nuevaUrl);
                        }
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG,"Error: "+ error.getMessage());
            }

        });


        return lista;
    }

/*
    public ArrayList<Url> verListaUrls(){

        Url nuevaUrl=null;
        conexion= new ConectarSW();
        conexion.on("Url", "GET");
        urlConnection= conexion.getUrlConnection();
        ArrayList<Url> lista= new ArrayList<Url> ();


        try{

     //       HttpResponse resp = conexion.getHTTP().execute();


            Log.i(TAG,"Recibir datos");
            BufferedReader recv = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //Los mostramos por pantalla
            String s=recv.readLine();
            String stringJson;
            String stringArrayJson=s;
            while(s!=null){

                Log.i(TAG,s);
                stringArrayJson=stringArrayJson+s;
                s=recv.readLine();


            }
            Log.i(TAG,"Enviado");
            jsonArray= new JSONArray();

            jsonArray=(JSONArray) new JSONTokener(stringArrayJson).nextValue();

            org.json.JSONObject j;

            for(int i=0;i< jsonArray.length();i++){
                jsonParam=jsonArray.getJSONObject(i);
                stringJson=jsonParam.toString();


                nuevaUrl.setUrl(jsonParam.getString("url"));
                nuevaUrl.setSubCategoria(jsonParam.getString("subCategoria"));
                nuevaUrl.setCodigo_categoria(jsonParam.getString("categoria"));
                nuevaUrl.setCodigo_test(jsonParam.getString("test"));
                nuevaUrl.setUltima_mod(jsonParam.getString("ultimaMod"));
                nuevaUrl.setUrl_ID(jsonParam.getInt("id"));

                Log.i(TAG,nuevaUrl.codigo_categoria);
                Log.i(TAG,nuevaUrl.subCategoria);
                Log.i(TAG,nuevaUrl.url);
                lista.add(nuevaUrl);
            }


        } catch (MalformedURLException e) {

            e.printStackTrace();

        }catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(urlConnection!=null)
                conexion.off();
        }
        return lista;

    }
*/



}
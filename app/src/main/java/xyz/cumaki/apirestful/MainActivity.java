package xyz.cumaki.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new WebService(
                "https://dummyjson.com/users"
                        ,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

}
    @Override
    public void processFinish(String result) throws JSONException {

        TextView txtSaludo=(TextView) findViewById(R.id.txtSaludo);


        String WebS="";
        JSONObject jsonLista = new JSONObject(result);
        JSONArray usuarios = jsonLista.getJSONArray("users");

        for(int i=0; i<usuarios.length();i++){
            JSONObject ListaUsers = usuarios.getJSONObject(i);
            WebS = WebS+ "\n" + ListaUsers.getString("firstName").toString()+", " +ListaUsers.getString("lastName").toString()
                    +", " +ListaUsers.getString("age").toString() + ", "+ ListaUsers.getString("email").toString()
                    +", "+ ListaUsers.getString("phone").toString();
        }
        txtSaludo.setText("Resp: " + WebS );

    }
}
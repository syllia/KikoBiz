package bj.kiko.projects.kikobiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import bj.kiko.projects.kikobiz.Util.LocalSavedPref;
import bj.kiko.projects.kikobiz.Util.Util;

public class LoginActivity extends AppCompatActivity {
    private Spinner codeSpinner;
    private EditText numero;

    private Button send;
    private String fragmentReload;
    ArrayAdapter adapterSpinnerCode;
    List<String> codes= new ArrayList<>();
    private LocalSavedPref lc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_login);
        if (intent.getStringExtra("name")!=null){
          fragmentReload=intent.getStringExtra("name");
        }else{
            fragmentReload=getResources().getString(R.string.FragmentHomeName);
        }
        numero= (EditText)findViewById(R.id.numeroeditText);

        codeSpinner =(Spinner)findViewById(R.id.spinnerNumeroCell);
        adapterSpinnerCode = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                codes);
        lc= new LocalSavedPref(this);
        send=(Button)findViewById(R.id.validerbutton);
        loadCodeList();
        setSpinner();
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    generateCode();

            }
        });

    }
    private void loadCodeList(){
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this, "/countries"))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject obj = result.get(i).getAsJsonObject();
                            int code = obj.get("code").getAsInt();
                            codes.add("+" + Integer.toString(code));
                        }
                        adapterSpinnerCode.notifyDataSetChanged();
                    }
                });
    }
    private void generateCode(){
        String n=codeSpinner.getSelectedItem().toString() + numero.getText();
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this, "/generatecode/" +n ))
        .asString()
        .setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
    Log.d("dd",result);
                showAlertDialog();
            }
        });



    }
    private void getUser(String codeValue){
        String n =codeSpinner.getSelectedItem().toString() + numero.getText().toString();
        int n2= Integer.parseInt(codeValue);
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this, "/login/" + n + "/"+n2))
                       // codeSpinner.getSelectedItem().toString().replace("+", "") +
                      //  numero.getText()+ "/"+ codeAuth.getText()))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("user", result.toString());
                        int numero = result.get("user").getAsInt();
                        lc.setNumero(Integer.toString(numero));
                        Intent refresh = new Intent(getBaseContext(), MainActivity.class);
                        refresh.putExtra("name", fragmentReload);
                        startActivity(refresh);
                        finish();
                    }
                });

    }
    public void setSpinner(){


        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(codeSpinner);
            popupWindow.setHeight(100);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
        }

        adapterSpinnerCode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        codeSpinner.setAdapter(adapterSpinnerCode);

    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tapez le code reçu en message");

// Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);


       // input.setBackground(getResources().getDrawable(R.drawable.editext_orange));
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isEmpty(input)){
                    getUser(input.getText().toString());
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}

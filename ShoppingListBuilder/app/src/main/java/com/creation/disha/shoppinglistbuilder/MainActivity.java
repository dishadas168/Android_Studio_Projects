package com.creation.disha.shoppinglistbuilder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    int textCount = 0;

    public static final int TEXT_REQUEST = 1;

    private EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            textCount = savedInstanceState.getInt("textCount");

        }
        setContentView(R.layout.activity_main);

        address = (EditText) findViewById(R.id.address);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
    }

   public void onSaveInstanceState(Bundle data){
        super.onSaveInstanceState(data);
        data.putInt("textCount", textCount);

   }

    public void addItem(View view) {
        Intent intent = new Intent(this, Shop.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == TEXT_REQUEST){
            if (resultCode==RESULT_OK) {
                if (textCount < 10) {
                    String text = data.getStringExtra("addToList");
                    TextView textView = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    textView.setText(text);
                    linearLayout.addView(textView, params);
                    textCount++;
                }else{
                    Toast.makeText(this, "You can't add anymore items",  Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void findStore(View view) {
        String shop_loc= address.getText().toString();
        Uri uri = Uri.parse("geo:0,0?q=" + shop_loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents", "No go brother");
        }
    }
}

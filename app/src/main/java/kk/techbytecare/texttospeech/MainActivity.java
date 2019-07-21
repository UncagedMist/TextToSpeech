package kk.techbytecare.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    ImageButton btnSpeak;
    EditText edtQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpeak = findViewById(R.id.btnSpeak);
        edtQuery = findViewById(R.id.txtQuery);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)   {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the text from edittext and convert it into string

                String query = edtQuery.getText().toString();
                //make the query to pop string as a toast message
                Toast.makeText(MainActivity.this, ""+query, Toast.LENGTH_SHORT).show();

                //set the text to text to speech to speak
                textToSpeech.speak(query,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    //override destroy method

    //close texttospeech
    @Override
    protected void onDestroy() {
        if (textToSpeech != null)   {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}

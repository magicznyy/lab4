package pollub.ism.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static java.lang.Character.getNumericValue;

public class MainActivity extends AppCompatActivity {

    private static int counter = 0;
    private static int tab[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
        int id = v.getId();
        int victory1 = 0;
        int victory2 = 0;


        Button button = (Button) findViewById(id);
        String buttonId = button.getResources().getResourceEntryName(id);
        char lastChar = buttonId.charAt(buttonId.length() - 1);
        int buttonNo = Character.getNumericValue(lastChar);

        if (counter % 2 == 0 && tab[buttonNo] == 0) {
            tab[buttonNo] = 1;
            button.setText("X");
        } else if (tab[buttonNo] == 0) {
            tab[buttonNo] = 2;
            button.setText("O");
        }

        if (counter >= 3) {
            for (int i = 1; i <= 9; i = i + 3) {
                for (int j = i; j < i + 3; j++) {
                    if (tab[j] == 1)
                        victory1++;
                    if (tab[j] == 2)
                        victory2++;
                    if (victory1 >= 3){
                        Restart("1 won");
                    }
                    if (victory2 >= 3){
                        Restart("2 won");
                    }
                }
                victory1 = 0;
                victory2 = 0;
            }

            for (int i = 1; i <= 3; i++) {
                for (int j = i; j <= i + 6; j = j + 3) {
                    if (tab[j] == 1)
                        victory1++;
                    if (tab[j] == 2)
                        victory2++;
                    if (victory1 >= 3){
                        Restart("1 won");
                    }
                    if (victory2 >= 3){
                        Restart("2 won");
                    }

                }
                victory1 = 0;
                victory2 = 0;
            }

            if ((tab[1] == 1 && tab[5] == 1 && tab[9] == 1) || (tab[3] == 1 && tab[5] == 1 && tab[7] == 1)) {
                Restart("1 won");
            }

            if ((tab[1] == 2 && tab[5] == 2 && tab[9] == 2) || (tab[3] == 2 && tab[5] == 2 && tab[7] == 2)) {
                Restart("2 won");
            }
        }
        counter++;
    }

    private void Restart(String winner) {
        TextView text = (TextView) findViewById(R.id.editTextTextPersonName);
        text.setText(winner);
        for(int i=0;i<10;i++)
        {
            tab[i]=0;
        }
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

}
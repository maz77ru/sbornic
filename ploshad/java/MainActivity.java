package kz.mazur.ploshad;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView razmeri,obs_ploshad;
    final Context context = this;
    double ploshadi=0;
    String otvet="";
    int shet=0;
    final String DIR_SD = "Project";
    String FILENAME_SD = "file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        razmeri=(TextView)findViewById(R.id.razmeri);
        obs_ploshad=(TextView)findViewById(R.id.ob_ploshad) ;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void startDialog() {
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_shema, null);
        AlertDialog.Builder buld=new AlertDialog.Builder(this);
        //Настраиваем prompt.xml для нашего AlertDialog:
        buld.setView(promptsView);
        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText visotka = (EditText) promptsView.findViewById(R.id.visotka);
        final EditText shirinka = (EditText) promptsView.findViewById(R.id.shirinka);
        final EditText koli = (EditText) promptsView.findViewById(R.id.koli);
        final EditText prim = (EditText) promptsView.findViewById(R.id.prim);
        buld.setTitle(R.string.dialog_message);
        buld.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        buld.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                shet++;
                int visota=Integer.parseInt(visotka.getText().toString());
                int shir=Integer.parseInt(shirinka.getText().toString());
                int kol=Integer.parseInt(koli.getText().toString());

                if (TextUtils.isEmpty(razmeri.getText().toString())){
                  //  otvet=""+visota+" - "+shir+" - "+kol+"  -  "+ prim.getText().toString()+"\n";
                } else { otvet=razmeri.getText().toString();

                }
                otvet+=""+shet+ " )   "+visota+" - "+shir+" - "+kol+"  -  "+ prim.getText().toString()+"\n";
              razmeri.setText(otvet);

                ploshadi=(visota*shir)*kol;
                ploshadi=ploshadi/1000000;
                //  П р о в е р я е м   п о л я   н а   п у с т о т у
                if (TextUtils.isEmpty(obs_ploshad.getText().toString())){// если пусто то
                }
                else { ploshadi+=Double.parseDouble(obs_ploshad.getText().toString());

                }
                obs_ploshad.setText(""+ploshadi);
            }
        });
        AlertDialog aler=buld.create();
        aler.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id ==R.id.action_sbros){
            razmeri.setText("");
            obs_ploshad.setText("");
            shet=0;
            otvet="";
            ploshadi=0;
        }
        if (id==R.id.action_sohran){
           sohranenie();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sohranenie(){
    /*    //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater lin = LayoutInflater.from(context);
        View promptsView = lin.inflate(R.layout.dialog_sohran, null);

        //Создаем AlertDialog
        android.app.AlertDialog.Builder mDialogBuilder = new android.app.AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);
        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText failneim = (EditText) promptsView.findViewById(R.id.failneme);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setTitle(R.string.men_sohran)
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                FILENAME_SD = failneim.getText().toString();
                                FILENAME_SD=FILENAME_SD+".txt";
                                // проверяем доступность SD
                                if (!Environment.getExternalStorageState().equals(
                                        Environment.MEDIA_MOUNTED)) {

                                }
                                // получаем путь к SD
                                File sdPath = Environment.getExternalStorageDirectory();
                                // добавляем свой каталог к пути
                                sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
                                // создаем каталог
                                sdPath.mkdirs();
                                // формируем объект File, который содержит путь к файлу
                                File sdFile = new File(sdPath, FILENAME_SD);
                                try {
                                    // открываем поток для записи
                                    BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                                    // пишем данные     String text = textBox.getText().toString();
                                    bw.write(razmeri.getText().toString());
                                    // закрываем поток
                                    bw.close();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        //Создаем AlertDialog:
        android.app.AlertDialog alertDialog = mDialogBuilder.create();
        //и отображаем его:
        alertDialog.show();*/
    }
}

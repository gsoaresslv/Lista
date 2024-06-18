package soares.guilherme.lista.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import soares.guilherme.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1; 
    Uri photoSelected = null; // declarando a variável que armazenará a imagem selecionada

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_new_item); //setando a interface pro usuário

        ImageButton imbCl = findViewById(R.id.imbCl); // capturando o botão de imagem
        imbCl.setOnClickListener(new View.OnClickListener(){ // criando um listener para o botão
            @Override
            public void onClick(View v){ // método que será chamado quando o botão for clicado
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // criando um intent para abrir a galeria
                photoPickerIntent.setType("image/*"); // definindo o tipo de arquivo que será aberto
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); // iniciando a activity de seleção de imagem
            }
        });
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                } 
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
            
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
            
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (desc.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // método que será chamado quando a activity de seleção de imagem for finalizada
        super.onActivityResult(requestCode, resultCode, data); // chamando o método da superclasse
            if(requestCode == PHOTO_PICKER_REQUEST) { 
                if(resultCode == Activity.RESULT_OK) { // verificando se a activity que foi finalizada é a de seleção de imagem e se foi bem sucedida
                    photoSelected = data.getData(); // armazenando a imagem selecionada
                    ImageView imvfotoPreview = findViewById(R.id.imvfotoPreview); // capturando a ImageView que exibirá a imagem selecionada
                    imvfotoPreview.setImageURI(photoSelected); // exibindo a imagem selecionada pro usuário
            }
        }
    }
}
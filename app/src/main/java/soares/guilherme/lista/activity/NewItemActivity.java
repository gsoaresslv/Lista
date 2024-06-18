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
        7. if (photoSelected == null) {
        8. Toast.makeText(NewItemActivity.this, "É necessário
        selecionar uma imagem!", Toast.LENGTH_LONG).show();
        9. return;
        10. }
        11.
        12. EditText etTitle = findViewById(R.id.etTitle);
        13. String title = etTitle.getText().toString();
        14. if (title.isEmpty()) {
        15. Toast.makeText(NewItemActivity.this, "É necessário inserir
        um título", Toast.LENGTH_LONG).show();
        16. return;
        17. }
        18.
        19. EditText etDesc = findViewById(R.id.etDesc);
        20. String description = etDesc.getText().toString();
        21. if (desc.isEmpty()) {
        22. Toast.makeText(NewItemActivity.this, "É necessário inserir
        uma descrição", Toast.LENGTH_LONG).show();
        23. return;
        24. }
        25.
        26. Intent i = new Intent();
        27. i.setData(photoSelected);
        28. i.putExtra("title", title);
        29. i.putExtra("description", description);
        30. setResult(Activity.RESULT_OK, i);
        31. finish();
        32. }
        33.});
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
package com.puzzle15;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SettingsChoseLibraryPicture extends  MainActivity {

    ImageView mImageView;
    Button mChooseBtn, btnToMenu, btnConfirm;
    OutputStream outputStream;
    List<String> fileList = new ArrayList<String>();
    TextView txtConfirm;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_chose_library_picture);

        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.chose_image_btn);
        btnToMenu = findViewById(R.id.btnSettingsChosePictureToMenu);
        btnConfirm = findViewById(R.id.chose_image_btn2);
        txtConfirm = findViewById(R.id.txtSettingsChosePictureConfirmation);

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsChoseLibraryPicture.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveImage();
                    txtConfirm.setText("Picture applied successfully");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    }
                    else {
                        //permission already granted
                        pickImageFromGallery();

                    }
                }
                else{
                    //system os is less than marshmallow
                    pickImageFromGallery();
                }
            }
        });

    }

    private void saveImage() {

        File dir = new File(getFilesDir().getAbsolutePath(), "SavedImage");

        if (!dir.exists()) {
            dir.mkdir();
        }
        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        //Jei jau yra, istrinam
        File file2 = new File(dir, "Uploaded.jpg");    //nebeistrininet po testavimo
        file2.delete();                                     //in cache lieka

        File file = new File(dir, "Uploaded.jpg");

        try {
            outputStream = new FileOutputStream(file);  //issaugo i faila pirma dalis
        } catch (Exception e) {
            e.printStackTrace();
        }


        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); //issaugo i faila antra dalis

        ArrayList<Bitmap> bs = new ArrayList<Bitmap>();

        Bitmap b = bitmap;
        divideImages(bs, b);

        //Sicia paimt root kai reikes pridet prie paveiksliuku    <<<---------------------
        File root = new File(getFilesDir().getAbsolutePath(), "SavedImage");
        //Atspausdina dirrectory file list'a
        System.out.println("In directory:" + root.getPath());
        saveFiles(bs, root.getPath());

        //testavimui
//        Bitmap bmImg = BitmapFactory.decodeFile(root.getPath() + "/pic3.jpg");
//        mImageView2.setImageBitmap(bmImg);

        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("In directory:" + root.getPath());
        soutFilesOfDir(root);

    }

    /**
     * I konsole isveda failu pavadinimus rastus nurodytoje papkeje
     * @param f
     */
    private void soutFilesOfDir(File f) {
        File[] files = f.listFiles();
        fileList.clear();
        for (File file : files) {
            fileList.add(file.getPath());
            System.out.println(file.getPath());
        }
    }

    /**
     * Issaugo paveikliukus is masyvo nurodytoje vietoje
     * @param files paveiksliuku rinkinys
     * @param path target directory
     */
    private void saveFiles(ArrayList<Bitmap> files, String path) {
        OutputStream os = new OutputStream() {
            @Override
            public void write(int i) throws IOException {
            }
        };
        int i = 0;
        for (Bitmap bt : files) {
            i++;
            System.out.println("Dirbam su pav nr: " + i);
            File file = new File(path, "pic" + i + ".jpg");
            try {
                os = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bt.compress(Bitmap.CompressFormat.JPEG, 100, os); //issaugo i faila antra dalis

            try {
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(file.getPath());
        }
    }

    /**
     * "sukarpo" paveiksliuka i 16-a daliu (4x4) ir issaugo Bitmap paveiksliuku arrayList'e
     * @param bs paveiksliuku sarasas i kuri issaugoma
     * @param b duotas paveiksliukas "karpymui"
     */
    private void divideImages(ArrayList<Bitmap> bs, Bitmap b) {

        final int n = 4;

        final int width = b.getWidth();
        final int height = b.getHeight();

        final int pixelByCol = width / n;
        final int pixelByRow = height / n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int startx = pixelByCol * j;
                int starty = pixelByRow * i;
                Bitmap b1 = Bitmap.createBitmap(b, startx, starty, pixelByCol, pixelByRow);
                bs.add(b1);
                b1 = null;
            }
        }
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }


    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    //permission was not granted
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //besimokant laborui tiks, rimtai programuojant sitas budas atgyvenes, privengt
    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            mImageView.setImageURI(data.getData());
            btnConfirm.setEnabled(true);
        }
    }
}
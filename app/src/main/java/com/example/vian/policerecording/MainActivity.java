package com.example.vian.policerecording;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText CaseTitle;
    private EditText offender;
    private EditText numberPlate;
    private EditText caseAndDescribe;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CaseTitle = findViewById(R.id.case_name);
        offender = findViewById(R.id.offender_name);
        numberPlate = findViewById(R.id.number_plate);
        caseAndDescribe = findViewById(R.id.case_desc);

        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.textview_view_products).setOnClickListener(this);

        db = FirebaseFirestore.getInstance();
    }

    private boolean hasValidationErrors(String Case, String involved, String number, String desc) {
        if (Case.isEmpty()) {
            CaseTitle.setError("Case Title Recquired");
            CaseTitle.requestFocus();
            return true;
        }

        if (involved.isEmpty()) {
            offender.setError("Name of Offender required");
            offender.requestFocus();
            return true;
        }

        if (number.isEmpty()) {
            caseAndDescribe.setError("A simple Description of the case is Required");
            caseAndDescribe.requestFocus();
            return true;
        }

        if (desc.isEmpty()) {
            numberPlate.setError("Vehicle number Plate required");
            numberPlate.requestFocus();
            return true;
        }

        return false;
    }

    private void saveProduct(){

        Toast.makeText(MainActivity.this, "Make Sure You Are Connected to Internet.", Toast.LENGTH_LONG).show();
        String Title = CaseTitle.getText().toString().trim();
        String Offender = offender.getText().toString().trim();
        String NoPlate = numberPlate.getText().toString().trim();
        String Description = caseAndDescribe.getText().toString().trim();


        if (!hasValidationErrors(Title, Offender, NoPlate, Description)) {

            CollectionReference dbProducts = db.collection("Cases");

            Product product = new Product(
                    Title,
                    Offender,
                    NoPlate,
                    Description

            );

            dbProducts.add(product)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity.this, "Case Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button_save:
                saveProduct();
                break;
                /*
            case R.id.textview_view_products:
                startActivity(new Intent(this, ProductsActivity.class));
                break;
                */
        }

    }

}

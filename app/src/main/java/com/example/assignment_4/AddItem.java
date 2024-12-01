package com.example.assignment_4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddItem extends AppCompatActivity {

    private EditText itemNameEditText, quantityEditText, priceEditText;
    private Button addButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        db = FirebaseFirestore.getInstance();

        itemNameEditText = findViewById(R.id.etItemName);
        quantityEditText = findViewById(R.id.etQuantity);
        priceEditText = findViewById(R.id.etPrice);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            String itemName = itemNameEditText.getText().toString();
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            double price = Double.parseDouble(priceEditText.getText().toString());

            // Add item to Firestore
            ShoppingItem newItem = new ShoppingItem(itemName, quantity, price);
            db.collection("shoppingList").add(newItem)
                    .addOnSuccessListener(documentReference -> {
                        finish();
                    })
                    .addOnFailureListener(e -> {
                    });
        });
    }
}

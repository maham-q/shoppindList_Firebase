package com.example.assignment_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ShoppingListAdapter extends FirestoreRecyclerAdapter<ShoppingItem, ShoppingListAdapter.ShoppingItemViewHolder> {

    private final Context context;

    public ShoppingListAdapter(@NonNull FirestoreRecyclerOptions<ShoppingItem> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ShoppingItemViewHolder holder, int position, @NonNull ShoppingItem model) {
        holder.itemName.setText(model.getItemName());
        holder.quantity.setText("Quantity: " + model.getQuantity());
        holder.price.setText("Price: " + model.getPrice());

        holder.deleteButton.setOnClickListener(v -> {
            String documentId = getSnapshots().getSnapshot(position).getId();
            deleteItem(documentId);
            notifyItemRemoved(position);
        });
    }


    @NonNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item_layout, parent, false);
        return new ShoppingItemViewHolder(view);
    }

    public static class ShoppingItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, quantity, price;
        Button deleteButton;

        public ShoppingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private void deleteItem(String documentId) {
        FirebaseFirestore.getInstance().collection("shoppingList")
                .document(documentId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
                });
    }
}

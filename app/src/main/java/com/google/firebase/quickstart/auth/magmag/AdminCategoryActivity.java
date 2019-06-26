package com.google.firebase.quickstart.auth.magmag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity
{

    private ImageView sport2, product_4, product_8;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);






        sport2 = (ImageView) findViewById(R.id.sport2);
        product_4 = (ImageView) findViewById(R.id.product_4);
        product_8 = (ImageView) findViewById(R.id.product_8);











        sport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "sport2 ");
                startActivity(intent);
            }
        });


        product_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "product_4");
                startActivity(intent);
            }
        });


        product_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "product_8");
                startActivity(intent);
            }
        });



    }
}
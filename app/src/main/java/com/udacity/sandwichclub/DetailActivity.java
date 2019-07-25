package com.udacity.sandwichclub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI( sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView NameTextView,ingerdientTextView=null,alsoKnownAsTextView=null,OriginTextView,descriptionTextView;
        NameTextView=(TextView) findViewById(R.id.MainName_Tv);
        NameTextView.setText(sandwich.getMainName());

        descriptionTextView=(TextView) findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());


        OriginTextView=(TextView) findViewById(R.id.origin_tv);
        OriginTextView.setText(sandwich.getPlaceOfOrigin());


        ingerdientTextView=(TextView) findViewById(R.id.ingredients_tv);
        if (sandwich.getIngredients().size()>0)
            for (String Ingredient : sandwich.getIngredients())
                if(Ingredient.length()==0)
                    ingerdientTextView.append("No available ingredients " );

                else

                    ingerdientTextView.append(Ingredient + "\n");
        else
            ingerdientTextView.append("No available ingredients " );

        alsoKnownAsTextView=(TextView) findViewById(R.id.also_known_tv);
        if (sandwich.getAlsoKnownAs().size()>0)
            for (String name :  sandwich.getAlsoKnownAs())
                if(name.length()==0)
                    alsoKnownAsTextView.append("No other names available " );

                else
                    alsoKnownAsTextView.append(name +"\n");
        else
            alsoKnownAsTextView.append("No other names available " );


    }
}

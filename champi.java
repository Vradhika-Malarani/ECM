package com.example.management;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CampaignManagementActivity extends AppCompatActivity {

    private EditText sloganInput;
    private TextView sloganDisplay;
    private Button saveButton;

    // Key for SharedPreferences
    private static final String PREFS_NAME = "CampaignPrefs";
    private static final String SLOGAN_KEY = "campaignSlogan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_management);

        sloganInput = findViewById(R.id.sloganInput);
        sloganDisplay = findViewById(R.id.sloganDisplay);
        saveButton = findViewById(R.id.saveButton);

        // Load the saved slogan (if any)
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedSlogan = preferences.getString(SLOGAN_KEY, "No slogan set");
        sloganDisplay.setText(savedSlogan);

        // Save the slogan when the button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSlogan = sloganInput.getText().toString();

                if (!newSlogan.isEmpty()) {
                    // Save to SharedPreferences
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(SLOGAN_KEY, newSlogan);
                    editor.apply();

                    // Update the display
                    sloganDisplay.setText(newSlogan);
                    sloganInput.setText(""); // Clear input field after saving
                }
            }
        });
    }
}

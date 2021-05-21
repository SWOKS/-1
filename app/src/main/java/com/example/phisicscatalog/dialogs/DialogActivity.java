package com.example.phisicscatalog.dialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phisicscatalog.R;

public class DialogActivity extends AppCompatActivity {

    private String formula;
    private String description;

    TextView formulaTextView;
    TextView descriptionTextView;


    public DialogActivity(){
        this.formula = "formula";
        this.description = "description";
    }

    public DialogActivity(String formula, String description) {
        this.formula = formula;
        this.description = description;
    }

    public DialogActivity(int contentLayoutId, String formula, String description) {
        super(contentLayoutId);
        this.formula = formula;
        this.description = description;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_fragment_part);

        formulaTextView = findViewById(R.id.text_formula);
        descriptionTextView = findViewById(R.id.text_description);

        formulaTextView.setText(formula);
        descriptionTextView.setText(description);

    }


    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

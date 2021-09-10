package by.kos.homeworkmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

import by.kos.homeworkmap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Map<String, String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        countries = new TreeMap<>();
        showInfo();

        binding.buttonAdd.setOnClickListener(v -> addInfo());

        binding.editTextCountyCommon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                binding.textViewCountyCity.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (countries.containsKey(s.toString().trim()) && !binding.editTextCountyCommon.getText().toString().isEmpty()) {
                    binding.textViewCountyCity.setText(String.format("%s - %s", s.toString(), countries.get(s.toString())));
                    binding.textViewCountyCity.setVisibility(View.VISIBLE);
                } else {
                    binding.textViewCountyCity.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void addInfo() {
        if (!binding.editTextCountry.getText().toString().isEmpty() &&
                !binding.editTextCity.getText().toString().isEmpty()) {
            countries.put(binding.editTextCountry.getText().toString().trim(),
                    binding.editTextCity.getText().toString().trim());
        } else Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
        showInfo();
        binding.editTextCountry.getText().clear();
        binding.editTextCity.getText().clear();
    }

    private void showInfo() {
        StringBuilder sb = new StringBuilder();
        if (!countries.isEmpty()) {
            for (String key : countries.keySet()) {
                sb.append(key).append(" - ").append(countries.get(key)).append("\n");
            }
            binding.textViewInfo.setText(sb);
        }
    }
}
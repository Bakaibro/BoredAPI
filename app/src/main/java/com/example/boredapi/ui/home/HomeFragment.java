package com.example.boredapi.ui.home;

import static android.os.Trace.isEnabled;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.boredapi.R;
import com.example.boredapi.databinding.FragmentHomeBinding;
import com.example.boredapi.models.ModelDo;
import com.example.boredapi.remote_data.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    NavController navController;
    private FragmentHomeBinding binding;
    private RetrofitBuilder retrofitBuilder;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setUpOnBackPressed();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGo.setOnClickListener(v-> {
            RetrofitBuilder.getInstance().getActivities().enqueue(new Callback<ModelDo>() {
                @Override
                public void onResponse(Call<ModelDo> call, Response<ModelDo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        binding.activity.setText(response.body().getActivity());
                        binding.price.setText(" $"+response.body().getPrice());
                        binding.link.setText(response.body().getLink());
                        binding.category.setText(response.body().getType());
                        binding.participate.setText(String.valueOf(response.body().getParticipants()));
                        binding.desc.setText(response.body().getAccessibility());


                    }
                }

                @Override
                public void onFailure(Call<ModelDo> call, Throwable t) {
                    Toast.makeText(requireActivity(),"No Data"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }





            });
        });
        binding.zoomBtn.setOnClickListener(v2-> {
            if(binding.link.getText().toString() != null){
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(binding.link.getText().toString()));
                    startActivity(myIntent);
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(requireActivity(), "This activity has not any link"+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.back.setOnClickListener(v3-> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_home_to_navigation_board);
        });
    }
    private void setUpOnBackPressed(){
        requireActivity().getOnBackPressedDispatcher().addCallback(
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if(isEnabled()){
                            requireActivity().finish();
                        }
                    }
                }
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
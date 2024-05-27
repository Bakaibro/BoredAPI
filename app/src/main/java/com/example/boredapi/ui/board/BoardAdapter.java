package com.example.boredapi.ui.board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boredapi.Prefs;
import com.example.boredapi.R;
import com.example.boredapi.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    ItemBoardBinding binding;
    NavController navController;
    private  int[] images = new int[]{
            R.drawable.physical,
            R.drawable.guitar,
            R.drawable.puzzle};

    private  String[] titles = new String[]{
            "Do you like sport? ",
            "Or do you like making art?",
            "We will find any activity you like"};
    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView.getRootView());
        }

        public void bind(int position) {
            binding.textBoard.setText(titles[position]);
            binding.imageBoard.setImageResource(images[position]);

            if(position==titles.length-1){
                binding.btnStart.setVisibility(View.VISIBLE);
            }else {
                binding.btnStart.setVisibility(View.INVISIBLE);
            }

            binding.btnStart.setOnClickListener(v->{

                new Prefs((Activity) itemView.getContext()).saveBoardState();

                navController = Navigation.findNavController((Activity) itemView.getContext(), R.id.nav_host_fragment_activity_main);

                navController.navigate(R.id.action_navigation_board_to_navigation_home);
            });
        }
    }
}
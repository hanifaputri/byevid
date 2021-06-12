package com.example.byevid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_slide_layout, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_onboarding;
        private TextView tx_onboarding_heading, tx_onboarding_desc;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_onboarding_heading = itemView.findViewById(R.id.tx_slide_heading);
            tx_onboarding_desc = itemView.findViewById(R.id.tx_slide_desc);
            img_onboarding = itemView.findViewById(R.id.img_ss);
        }

        void setOnboardingData(OnboardingItem onboardingItem) {
            tx_onboarding_heading.setText(onboardingItem.getHeading());
            tx_onboarding_desc.setText(onboardingItem.getDesc());
            img_onboarding.setImageResource(onboardingItem.getImg());
        }
    }
}

package com.example.cardflipanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AnimatorSet frontAnimation;
    private AnimatorSet backAnimation;
    private boolean isFront = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the camera distance for the card
        float scale = getResources().getDisplayMetrics().density;
        TextView front = findViewById(R.id.card_front);
        TextView back = findViewById(R.id.card_back);
        Button flipButton = findViewById(R.id.flip_btn);

        front.setCameraDistance(8000 * scale);
        back.setCameraDistance(8000 * scale);

        // Load front and back animations
        frontAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.front_animator);
        backAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.back_animator);

        // Set click listener for flip button
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront) {
                    frontAnimation.setTarget(front);
                    backAnimation.setTarget(back);
                    frontAnimation.start();
                    backAnimation.start();
                    isFront = false;
                } else {
                    frontAnimation.setTarget(back);
                    backAnimation.setTarget(front);
                    backAnimation.start();
                    frontAnimation.start();
                    isFront = true;
                }
            }
        });
    }
}

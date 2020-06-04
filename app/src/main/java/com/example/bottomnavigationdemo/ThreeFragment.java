package com.example.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProviders;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * @author Surface Pro 6
 */
public class ThreeFragment extends Fragment {

    private ThreeViewModel mViewModel;
    private ImageView mImageView;
    private Button mButton;

    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        mButton = view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ThreeViewModel.class);
        // TODO: Use the ViewModel
        mImageView.setX(mViewModel.translationX);
        mImageView.setY(mViewModel.translationY);
        //ofFloat:对象，动画类型，数值
        final ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(mImageView, "TranslationX", 0, 0);
        final ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mImageView, "TranslationY", 0, 0);
        //组合动画
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
        animatorSet.setDuration(500);
        final Random random = new Random();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animatorSet.isRunning()) {
                    float x = mImageView.getTranslationX();
                    float y = mImageView.getTranslationY();
                    float dx = random.nextBoolean() ? 100 : -100;
                    float dy = random.nextBoolean() ? 100 : -100;
                    objectAnimatorX.setFloatValues(x, x + dx);
                    objectAnimatorY.setFloatValues(y, y + dy);
                    mViewModel.translationX += dx;
                    mViewModel.translationY += dy;
                    animatorSet.start();
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = mImageView.getTranslationX();
                float y = mImageView.getTranslationY();
                objectAnimatorX.setFloatValues(x, 0);
                objectAnimatorY.setFloatValues(y, 0);
                mViewModel.translationX = 0;
                mViewModel.translationY = 0;
                animatorSet.start();
            }
        });
    }
}
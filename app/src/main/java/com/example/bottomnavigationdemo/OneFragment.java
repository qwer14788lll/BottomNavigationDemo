package com.example.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author Surface Pro 6
 */
public class OneFragment extends Fragment {

    private OneViewModel mViewModel;
    private ImageView mImageView;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OneViewModel.class);
        // TODO: Use the ViewModel
        //ofFloat:对象，动画类型，数值
        final ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(mImageView,"rotation",0,0);
        objectAnimator.setDuration(500);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!objectAnimator.isRunning())
                {
                    float f=mImageView.getRotation();
                    objectAnimator.setFloatValues(f,f+100);
                    objectAnimator.start();
                }
            }
        });
    }

}
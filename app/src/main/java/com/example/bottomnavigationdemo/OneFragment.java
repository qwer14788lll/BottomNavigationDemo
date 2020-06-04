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
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Surface Pro 6
 */
public class OneFragment extends Fragment {

    private OneViewModel mViewModel;
    private ImageView mImageView;
    private Button mButton;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        mButton=view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(OneViewModel.class);
        // TODO: Use the ViewModel
        mImageView.setRotation(mViewModel.rotation);
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
                    mViewModel.rotation+=100;
                    objectAnimator.start();
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float f=mImageView.getRotation();
                f=f%360;
                objectAnimator.setFloatValues(f,0);
                mViewModel.rotation=0;
                objectAnimator.start();
            }
        });
    }

}
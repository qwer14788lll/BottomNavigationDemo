package com.example.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProviders;

import android.animation.AnimatorSet;
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
public class TwoFragment extends Fragment {

    private TwoViewModel mViewModel;
    private ImageView mImageView;
    private Button mButton;

    public static TwoFragment newInstance() {
        return new TwoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.two_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        mButton=view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(TwoViewModel.class);
        // TODO: Use the ViewModel
        mImageView.setScaleX(mViewModel.scale);
        mImageView.setScaleY(mViewModel.scale);
        //ofFloat:对象，动画类型，数值
        final ObjectAnimator objectAnimatorX =ObjectAnimator.ofFloat(mImageView,"ScaleX",0,0);
        final ObjectAnimator objectAnimatorY =ObjectAnimator.ofFloat(mImageView,"ScaleY",0,0);
        //组合动画
        final AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.playTogether(objectAnimatorX,objectAnimatorY);
        animatorSet.setDuration(500);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animatorSet.isRunning())
                {
                    float x=mImageView.getScaleX();
                    float y=mImageView.getScaleY();
                    objectAnimatorX.setFloatValues(x,x+1);
                    objectAnimatorY.setFloatValues(y,y+1);
                    mViewModel.scale+=1;
                    animatorSet.start();
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x=mImageView.getScaleX();
                float y=mImageView.getScaleY();
                objectAnimatorX.setFloatValues(x,1);
                objectAnimatorY.setFloatValues(y,1);
                mViewModel.scale=1;
                animatorSet.start();
            }
        });
    }

}
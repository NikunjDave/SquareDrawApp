package com.nikiaitestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.nikiaitestapp.custom.Square;
import com.nikiaitestapp.custom.SquareView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SquareView mSquareView;

    private SeekBar mSeekBar;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottom_sheet;
    private RecyclerView rcvCoords;
    private List<Square> squareList = new ArrayList<>();
    private  CoordinateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSquareView = findViewById(R.id.squareDrawView);
        mSeekBar= findViewById(R.id.seekBar);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        rcvCoords = findViewById(R.id.rcvCoords);

        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        setCoordinatesAdapter();
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float factor = (float) progress /10f;
                System.out.println("scale  is "+factor);
                System.out.println("factor is "+(progress /10));

                mSquareView.scaleSquare(factor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void performClick(View view) {
        switch (view.getId()) {
            case R.id.scale:
                mSeekBar.setVisibility(View.VISIBLE);
                break;
            case R.id.coords:
                if(mSquareView.getSquareList() != null && mSquareView.getSquareList().size() > 0) {
                    adapter.notifyDataSetChanged();
                    if (sheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN || sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }else{
                    Toast.makeText(this,"No Square found ",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reset:
                mSquareView.scaleSquare(1f);
                mSquareView.clearAll();
                mSeekBar.setProgress(1);
                mSeekBar.setVisibility(View.GONE);
                break;

            case R.id.btnClose:
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;

        }

    }

    private void setCoordinatesAdapter(){
        adapter = new CoordinateAdapter(mSquareView.getSquareList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvCoords.setLayoutManager(manager);
        rcvCoords.setAdapter(adapter);
        rcvCoords.setHasFixedSize(true);

    }




}

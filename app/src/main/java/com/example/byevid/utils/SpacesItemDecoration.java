package com.example.byevid.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int col;

    public SpacesItemDecoration(int space, int col) {
        this.space = space;
        this.col = col;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Set horizontal margin
        if ((parent.getChildLayoutPosition(view) + 1) % 2 == 0) {
            outRect.left = space * 2;
        } else {
            outRect.right = space * 2;
        }

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) < col) {
            outRect.top = 0;
        } else {
            outRect.top = space * 4;
        }
    }
}
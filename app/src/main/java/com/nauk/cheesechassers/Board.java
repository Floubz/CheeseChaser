package com.nauk.cheesechassers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;

public class Board extends LinkedList<BlockImage> {
    int tableauModel[][];

    public Board(int[][] tableauModel) {
        this.tableauModel = tableauModel;
        new LinkedList<>();
    }

    public int determinerX(int xTab) {

        int xPix = xTab * 200;

        return xPix;
    }

    public int determinerY(int yTab) {

        int yPix = yTab * 200;

        return yPix;
    }

    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);



        for (BlockImage blockImage: this
                ) {

            int x = determinerX(blockImage.x);
            int y = determinerY(blockImage.y);
            Log.d("Board", "val de x : "+x);
            Log.d("Board", "val de y : "+y);
            // Dabord gerer le deplacement et le zoom
            //canvas.drawBitmap(blockImage.img,x,y,paint);
            // A supprimé un fois le deplacement géré
            canvas.drawBitmap(blockImage.img,blockImage.x,blockImage.y,paint);
        }
    }
}


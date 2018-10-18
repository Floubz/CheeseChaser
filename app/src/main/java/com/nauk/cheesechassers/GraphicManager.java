package com.nauk.cheesechassers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GraphicManager extends View implements View.OnTouchListener {

    private int y;
    private int x;
    private Board board;
    private Modele modele;
    private boolean mBooleanIsPressed = false;


    public GraphicManager(Context context) {
        super(context);
        init();
    }

    public GraphicManager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphicManager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GraphicManager(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init() {

        modele = new Modele();
        board = new Board(modele.getMonTableau());
        x = 40;
        y = 40;
        int valeur = modele.getMonTableau()[x][y];
        Log.d("GraphicManager", "init valeur carte : " + valeur);
        choisirImage(valeur);
        this.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        x = (int) motionEvent.getX();
        y = (int) motionEvent.getY();
        int xTab = determinationCaseTableauX(x);
        int yTab = determinationCaseTableauY(y);
        int valeur = modele.getMonTableau()[xTab][yTab];
        boolean enCours;
        enCours = modele.jouer(xTab, yTab);

        Log.d("GraphicManager", "onTouch valeur carte : " + valeur);
        if (enCours) {
            Log.d("GraphicManager", "MON MOTION EVENT : "+motionEvent.getAction());
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("GraphicManager", "Appuie");
                    Log.d("GraphicManager", "valeur de X : " + x);
                    Log.d("GraphicManager", "valeur de Y : " + y);

                    break;
                case MotionEvent.ACTION_UP:
                    // Si clique court
                    if(mBooleanIsPressed == false)
                    {
                        Log.d("GraphicManager", "Doigt enlevé (court)");
                        choisirImage(valeur);
                    }
                    else
                    {
                        Log.d("GraphicManager", "Doigt enlevé (long)");
                        mBooleanIsPressed = false;

                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("GraphicManager", "Je bouge");

                    break;
                default:
                    Log.d("GraphicManager", "DEFAULT !!!!!!!!!!!");
                    break;
            }
        }
        this.invalidate();
        return true;
    }

    public int determinationCaseTableauX(int xPix) {
        int xTab = xPix / 200;
        return xTab;
    }

    public int determinationCaseTableauY(int yPix) {
        int yTab = yPix / 200;
        return yTab;
    }

    public void choisirImage(int valCarte) {
        Bitmap drawable = null;
        Log.d("GraphicManager", "appel chosirImage(" + valCarte + ")");
        switch (valCarte) {
            case -1:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.arrowdown);
                break;
            case 0:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.peinture);
                break;
            case 1:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.mouse);
                break;
            case 2:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
                break;
            case 3:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
                break;
            case 4:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.mousetrap);
                break;
            case 5:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.mousetrapdesac);
                break;
            case 6:
                drawable = BitmapFactory.decodeResource(getResources(), R.drawable.mousetrapdesac);
                break;
            default:
                break;
        }
        board.add(new BlockImage(x, y, drawable));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.draw(canvas);
        Log.d("debug", "REDRAWING");
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

}


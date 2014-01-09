package com.example.spots;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class CirclesView extends View{

	Paint p = null;
	int color = 0;
	Random r = null;
	int width = 0;
	int height = 0;
	int elements = 0;
	int sizePoint = 15;
	
	public CirclesView(Context context, int color, int width, int height, int elems) {
		super(context);
		setFocusable(true);
		p = new Paint();
		this.color = color;
		r = new Random();
		this.width = width;
		this.height = height;
		this.elements = elems;
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for(int i= 0; i < this.elements; i++){
			int x = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
			int y = Utils.randInt(sizePoint + 5,  height-(sizePoint + 5));
			addCircle(canvas, x, y);
		}		
	}
	
	private void addCircle(Canvas canvas, int x, int y){
		p.setAntiAlias(true);
		p.setColor(color);
		p.setStyle(Paint.Style.FILL); 
		canvas.drawCircle(x, y, sizePoint, p);	
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    this.width = getMeasuredWidth();
	    this.height = getMeasuredHeight();

	}

}

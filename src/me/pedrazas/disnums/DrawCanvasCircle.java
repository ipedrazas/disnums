package me.pedrazas.disnums;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawCanvasCircle extends View
{
	Paint p = null;
	
	public DrawCanvasCircle(Context context) {
		super(context);
		setFocusable(true);
		p = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.WHITE);
		for(int i=0; i<6;i++){
			addCircle(canvas, Color.RED, 10 + 60*i, 10 + 60*i);
		}
		for(int i=0; i<6;i++){
			addCircle(canvas, Color.GREEN, 150 + 60*i, 10 + 60*i);				
		}
		
	}
	
	private void addCircle(Canvas canvas, int color, int x, int y){
		p.setAntiAlias(true);
		p.setColor(color);
		p.setStyle(Paint.Style.FILL); 
		canvas.drawCircle(x, y, 10, p);	
	}


}
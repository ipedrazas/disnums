package me.pedrazas.disnums.views;

import java.util.ArrayList;
import java.util.List;

import me.pedrazas.disnums.om.Position;
import me.pedrazas.disnums.utils.Utils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ImageCirclesView extends View{

	int color = 0;	
	int width = 0;
	int height = 0;
	int elements = 0;
	int sizePoint = 0;
	int x = 0;
	int y = 0;
	List<Position> positions = null;
	
	public ImageCirclesView(Context context, int color, int width, int height, int elems) {
		super(context);
		setFocusable(true);
//		Log.d("Circles", "ImageCircleView: (" + x + ", " + y + " - " + width + ", " + height);
		this.color = color;
		this.width = width;
		this.height = height;
		this.elements = elems;
		this.positions = new ArrayList<Position>();		

	}

	public ImageCirclesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ImageCirclesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageCirclesView(Context context) {
		super(context);
	}

	/**
	 * @param x
	 * @param y
	 * @return boolean that states if here's any other dot in that position
	 * 		We want to avoid overlapping dots
	 * 
	 */
	private boolean isUsed(int x, int y){
		if(this.isNearABorder(x, y)){
			return true;
		}
		for (Position p : this.positions){
			if(this.isInSurroundings(p, x, y)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isNearABorder(int x, int y){
		if(x<this.sizePoint){
			return true;
		}
		if(y<this.sizePoint){
			return true;
		}
		if(this.width-this.sizePoint < x){
			return true;
		}
		if(this.height-this.sizePoint < y){
			return true;
		}
		return false;
	}
	
	private boolean isInSurroundings(Position p, int x, int y){
		int initX = p.getX() - this.sizePoint;
		int initY = p.getY() - this.sizePoint;
		int deltaX = p.getX() + this.sizePoint;
		int deltaY = p.getY() + this.sizePoint;
		
//		Log.d("Circles", "Entry: " + x + ", " + y + " position: " + p.getX() + "-" + deltaX + ", " + p.getY() + "-" + deltaY);
		
		if((x > initX && x < deltaX) && (y > initY && y < deltaY)){
			return true;
		}

		return false;
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		for(int i= 0; i < this.elements; i++){			
			addCircle(canvas);
		}		
	}
	
	private void addCircle(Canvas canvas){
		Bitmap dot = BitmapFactory.decodeResource(getResources(), this.color);
		this.sizePoint = dot.getWidth();
		int x = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
		int y = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
		while(isUsed(x,y)){
			x = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
			y = Utils.randInt(sizePoint + 5,  height-(sizePoint + 5));
//			Log.d("Circles", "Postion: " + x + ", " + y);
		}
		this.positions.add(new Position(x, y));
		Paint p = new Paint();
		
		p.setAntiAlias(true);
		canvas.drawBitmap(dot, x, y, new Paint());
		Log.d("Circles", "Circle drawn at: " + x + ", " + y + " in " + this.width + ", " + this.height + " of " + this.elements + " circles: " + dot.getWidth() + "-" + dot.getHeight());
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    this.width = getMeasuredWidth();
	    this.height = getMeasuredHeight();

	}

}

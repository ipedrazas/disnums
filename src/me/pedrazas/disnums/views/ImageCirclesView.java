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

	Paint p = null;
	int color = 0;	
	int width = 0;
	int height = 0;
	int elements = 0;
	int sizePoint = 26;
	List<Position> positions = null;
	
	public ImageCirclesView(Context context, int color, int width, int height, int elems) {
		super(context);
		setFocusable(true);
		p = new Paint();
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
		boolean res = false;
		for (Position p : this.positions){
//			if(x == p.getX() || y == p.getY()){
//				res = true;
//			}
			return this.isInSurroundings(p, x, y);
		}
		return res;
	}
	
	private boolean isInSurroundings(Position p, int x, int y){
		// don't want dots on the borders
		if(x==0 || y==0){
			return true;
		}
		if(x > (p.getX() - this.sizePoint) && x < (p.getX() + this.sizePoint)){
			if(y > (p.getY() - this.sizePoint) && y < (p.getY() + this.sizePoint)){
				return true;
			}
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
		int x = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
		int y = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
		while(isUsed(x,y)){
			x = Utils.randInt(sizePoint + 5, width-(sizePoint + 5));
			y = Utils.randInt(sizePoint + 5,  height-(sizePoint + 5));
			Log.d("Circles", "Postion: " + x + ", " + y);
		}
		this.positions.add(new Position(x, y));
		Bitmap mBackground = BitmapFactory.decodeResource(getResources(), this.color);
//		p.setAntiAlias(true);
//		p.setColor(color);
//		p.setStyle(Paint.Style.FILL); 
//		canvas.drawCircle(x, y, sizePoint, p);
		canvas.drawBitmap(mBackground, x, y, new Paint());
		Log.d("Circles", "Circle drawn at: " + x + ", " + y + " of " + this.elements + " circles");
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    this.width = getMeasuredWidth();
	    this.height = getMeasuredHeight();

	}

}

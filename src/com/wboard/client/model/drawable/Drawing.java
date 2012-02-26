/**
 * WObject �� �׸���(Drawing) ��ü�� ���� Ŭ����
 */

package com.wboard.client.model.drawable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import com.wboard.client.util.Constants;
import com.wboard.common.WObject;


public class Drawing extends WClientObject{

	private static final long serialVersionUID = -4354373214511330044L;
	
	private ArrayList<Point> drawingPath;	// Drawing ��ü�� Point �� �����ϴ� �迭

	/* ������ */
	public Drawing(Point startPoint) {
		super(OBJTYPE.FREE_SHAPE, startPoint);
		this.drawingPath = new ArrayList<Point>();
	}

	/* WObject Ŭ������ Object ���� �Լ� �������̵�  */
	public void setWObject(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.width = endPoint.x - startPoint.x;
		this.height = endPoint.y - startPoint.y;
		this.setBoundary();
	}

	/* Select & Move: Drawing �迭�� ��� ����Ʈ�� �̵� */
	public void moveDrawing(int movingX, int movingY){
		int size = this.getPathSize();
		if(size > 1){
			for(int i = 0; i < size; i++){
				Point p = this.getDrawingPoint(i);
				p.x = p.x + movingX;
				p.y = p.y + movingY;
			}	
		}
	}

	/* Drawing �迭�� ��� Point �� ���  */
	@Override
	public void draw(GC gc){
		int size = this.getPathSize();
		if(size > 1){
			for(int i = 1; i < size; i++){
				Point p1 = this.getDrawingPoint(i - 1);
				Point p2 = this.getDrawingPoint(i);
				gc.drawLine(p1.x, p1.y, p2.x, p2.y);
			}		
		}
	}

	/* Drawing ��ü�� Area �� ��� */
	public void computeArea(){
		int top = Constants.DUMP_MAX;
		int left = Constants.DUMP_MAX;
		int right = Constants.DUMP_MIN;
		int bottom = Constants.DUMP_MIN;

		for(int i = 0; i < this.getPathSize(); i++){
			int x = drawingPath.get(i).x;
			int y = drawingPath.get(i).y;

			if(x < left){
				left = x;
			}
			if(x > right){
				right = x;
			}
			if(y < top){
				top = y;
			}
			if(y > bottom){
				bottom = y;
			}
		}
		this.setWObject(new Point(left, top), new Point(right, bottom));	// ���� ������ Drawing Object Area�� ����
	}
	
	public void setDrawingPath(ArrayList<Point> drawingPath) {
		this.drawingPath = drawingPath;
	}

	public ArrayList<Point> getDrawingPath() {
		return drawingPath;
	}

	/* Drawing �迭�� Point ���� */
	public void addDrawingPath(Point p){
		drawingPath.add(p);
	}

	/* Drawing �迭 ��ȯ */
	private Point getDrawingPoint(int index){
		return drawingPath.get(index);
	}

	/* �迭�� ���� ��ȯ */
	private int getPathSize(){
		return drawingPath.size();
	}

	@Override
	public long getOid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(WObject o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
}

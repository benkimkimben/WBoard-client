/**
 * ���� ��ü(����) �׸��⸦ ����ϴ� ������
 * */
package com.wboard.client.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


import com.wboard.client.object.Shape;
import com.wboard.client.object.Shape.SHAPETYPE;
import com.wboard.client.object.shape.Arrow;
import com.wboard.client.ui.WBoard;
import com.wboard.client.ui.WBoard.MODE;
import com.wboard.client.util.SALAD;


public class ShapeListener implements Listener {
	// set shape type
	private SHAPETYPE shapeType;
	private WBoard wBoard;
	private static boolean drawShape;	// ���ο� ��ü�� �׸��� �ִ� ���� ��Ÿ���� flag

	/* ���� ��ü ������ �ʿ��� ��ǥ ���� */
	private static int startX;
	private static int startY;
	private static int endX;
	private static int endY;
	private static int width;
	private static int height;

	/* ������ */
	public ShapeListener(WBoard wBoard, SHAPETYPE shapeType){
		this.wBoard = wBoard;
		this.shapeType = shapeType;
	}

	@Override
	public void handleEvent(Event event) {
		if(wBoard.getMode().equals(MODE.SHAPE)){
			Canvas canvas = wBoard.getCanvas();
			Cursor cursor = new Cursor(event.display, SWT.CURSOR_CROSS);	// ���� �׸��� ���� Ŀ�� ����
			canvas.setCursor(cursor);

			GC gc = new GC(canvas);
			gc.setLineWidth(wBoard.getLineWidth());

			switch(event.type){
			case SWT.MouseDown:
				startX = event.x;	// ������ ����
				startY = event.y;
				canvas.redraw();
				break;

			case SWT.MouseMove:
				if((event.stateMask & SWT.BUTTON1) == 0) break;
				drawShape = true;	// ���� �׸��� ��� ����

				canvas.redraw();

				endX = event.x;	// ������ ������ �� ����
				endY = event.y;

				width = endX - startX;	// ����, ���� ��� 
				height = endY - startY;
				break;

			case SWT.Paint:
				if(!drawShape) break;	// �׸��� ��尡 �ƴ� ��� break

				if(wBoard.getLineColor()!= null){
					gc.setForeground(wBoard.getLineColor());
				}
				if(wBoard.getFaceColor()!= null){
					gc.setBackground(wBoard.getFaceColor());
				}
				gc.setLineWidth(wBoard.getLineWidth());
				gc.setLineCap(wBoard.getLineCap());
				
				/* �� ������ ��Ÿ�Ͽ� �°� ����� ȭ�鿡 �ǽð����� �׸��� �׸�*/
				if(shapeType == SHAPETYPE.LINE){
					gc.drawLine(startX, startY, endX, endY);
				}
				else if(shapeType == SHAPETYPE.OVAL){
					if(wBoard.getFaceColor()!= null && !wBoard.getFaceColor().equals(SALAD.WHITE)){	// WHITE �϶� ����
						gc.fillOval(startX, startY, width, height);						
					}
					gc.drawOval(startX, startY, width, height);
				}
				else if(shapeType == SHAPETYPE.RECTANGLE){
					if(wBoard.getFaceColor()!=null && !wBoard.getFaceColor().equals(SALAD.WHITE)){
						gc.fillRectangle(startX, startY, width, height);
					}
					gc.drawRectangle(startX, startY, width, height);
				}
				else if(shapeType == SHAPETYPE.TRIANGLE){
					if(wBoard.getFaceColor()!=null  && !wBoard.getFaceColor().equals(SALAD.WHITE)){
						gc.fillPolygon(new int[]{startX + width / 2, startY,	// �ﰢ���� �׸��� ���� int array
								startX, endY, endX, endY});
					}
					gc.drawPolygon(new int[]{startX + width / 2, startY,	// �ﰢ���� �׸��� ���� int array
							startX, endY, endX, endY});
				}

				else if(shapeType == SHAPETYPE.ARROW){
					if(wBoard.getFaceColor()!=null && !wBoard.getFaceColor().equals(SALAD.WHITE)){
						gc.fillPolygon(new int[]{startX, startY + height/3,
								startX,	startY + (2*height)/3,
								startX + (2*width)/3, startY + (2*height)/3,
								startX + (2*width)/3, startY + height,
								startX + width, startY + height/2,
								startX + (2*width)/3, startY,
								startX + (2*width)/3, startY + height/3});
					}
					gc.drawPolygon(new int[]{startX, startY + height/3,
							startX,	startY + (2*height)/3,
							startX + (2*width)/3, startY + (2*height)/3,
							startX + (2*width)/3, startY + height,
							startX + width, startY + height/2,
							startX + (2*width)/3, startY,
							startX + (2*width)/3, startY + height/3});
				}


				drawShape = false;	// �׸��Ⱑ ���� �� �׸��� ��� ��ü
				break;
				
			case SWT.MouseUp:
				Shape shape = null;
				switch(shapeType){
					case ARROW: 
						shape = new Arrow(new Point(startX, startY), new Point(endX, endY), wBoard.getLineColor(), wBoard.getFaceColor());
					//TODO: add more shape
				}
				if(shape != null){
					shape.setLineWidth(wBoard.getLineWidth());
					wBoard.addWClientObject(shape);
				}
				break;
			}	
			gc.dispose();
		}
	}
}

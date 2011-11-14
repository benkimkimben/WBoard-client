/**
 * �׸��� ��ü�� ȭ�鿡 �׸��� �����ϴ� ������ 
 */

package com.wboard.client.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


import com.wboard.client.object.Drawing;
import com.wboard.client.ui.WBoard;
import com.wboard.client.ui.WBoard.MODE;



public class DrawingListener implements Listener {

	private WBoard wBoard;
	Drawing drawing ;
	private int lastX;	// ���� ��ǥ ����
	private int lastY;	// ���� ��ǥ ����
	
	/* ������ */
	public DrawingListener(WBoard wBoard){
		this.wBoard = wBoard;
	}
	
	@Override
	public void handleEvent(Event event) {
		if(wBoard.getMode() == MODE.FREE_SHAPE){
			Canvas canvas = (Canvas)event.widget;
			Cursor cursor = new Cursor(event.display, SWT.CURSOR_UPARROW);	// �׸��� ���� Ŀ�� ����
			canvas.setCursor(cursor);
			
			GC gc = new GC(canvas); 
			
			switch(event.type){
				case SWT.MouseDown:	
					lastX = event.x;	// �ʱ�ȭ
					lastY = event.y;
					canvas.redraw();
					drawing = new Drawing(new Point(event.x, event.y));	// ���������� �׸��ⰴü ����
					break;	
					
				case SWT.MouseMove:
					if((event.stateMask & SWT.BUTTON1) == 0) break;
					
					// show drawing progress actively to the user
					gc.setForeground(wBoard.getFaceColor());
					gc.setLineWidth(wBoard.getLineWidth());
					gc.setLineCap(wBoard.getLineCap());
					gc.drawLine(lastX, lastY, event.x, event.y);	// ���� ��ǥ�� �� Event ��ǥ��ŭ Line �׸���
					
					// add drawing path to the drawing object
					drawing.addDrawingPath(new Point(event.x, event.y));
					
					lastX = event.x;	// ��ǥ ����
					lastY = event.y;
					break;
					
				case SWT.MouseUp:

					if(drawing.getDrawingPath().size() < 5){break;}
					
					// when drawing is done set its properties 
					drawing.setWObject(new Point(event.x, event.y));
					drawing.computeArea();	// �׸��� ��ü�� ���� ���	
					drawing.setLineWidth(wBoard.getLineWidth());
					drawing.setFaceColor(wBoard.getFaceColor());
					drawing.setLineColor(wBoard.getLineColor());
					drawing.setLineCap(wBoard.getLineCap());
					
					wBoard.addWClientObject(drawing);
				
					break;
			}
			gc.dispose();
		}
	}
}

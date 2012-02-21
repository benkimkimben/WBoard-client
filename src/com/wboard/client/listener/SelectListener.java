/**
 * ��ü ���� ��忡���� ������ ����ϴ� ������
 */

package com.wboard.client.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.wboard.client.model.drawable.Drawing;
import com.wboard.client.model.drawable.WClientObject;
import com.wboard.client.ui.WBoard;
import com.wboard.client.ui.WBoard.MODE;



public class SelectListener implements Listener {
	private WBoard wBoard;
	private WClientObject selected;	// ���õ� ��ü ����
	private static int lastX;	// ���� ��ǥ ����
	private static int lastY;

	public SelectListener(WBoard wBoard){
		this.wBoard = wBoard;
		this.selected = wBoard.getSelected();
	}

	@Override
	public void handleEvent(Event event) {
		if(wBoard.getMode()== MODE.SELECT){

			Canvas canvas = wBoard.getCanvas();
			Cursor cursor = new Cursor(event.display, SWT.CURSOR_ARROW);	// Select ��� Ŀ�� ����
			canvas.setCursor(cursor);

			switch(event.type){
			case SWT.MouseDown:
				lastX = event.x;	// �ʱ�ȭ
				lastY = event.y;

				for(int i = wBoard.getWObjectList().size() - 1; i >= 0 ; i--){
					WClientObject wcObject = wBoard.getWObjectList().get(i);
										
					if(wcObject.getBoundary().contains(event.x, event.y)){
						selected = wcObject;		// ��ü�� ������ event �� �߻��ϸ� ���� �ֱ��� selected ���� ����
						wBoard.setSelected(selected);
						break;
					}
				}

				canvas.redraw();
				break;

			case SWT.MouseMove:
				if((event.stateMask & SWT.BUTTON1) == 0) break;

				int movingX = event.x - lastX;	// ������ �Ÿ� ��� 
				int movingY = event.y - lastY;

			
				selected.setWObject(new Point(selected.getStartPoint().x + movingX,	// ������ ��ŭ ��ü �̵�
						selected.getStartPoint().y + movingY),
						new Point(selected.getEndPoint().x + movingX,
								selected.getEndPoint().y + movingY));
				if(selected instanceof Drawing){	// �׸��� ��ü�� ��� ��� Point �̵�
					((Drawing)selected).moveDrawing(movingX, movingY);
				}
				
				lastX = event.x;	// ��ǥ ����
				lastY = event.y;
				canvas.redraw();
				
				break;
			}
		}
	}
}

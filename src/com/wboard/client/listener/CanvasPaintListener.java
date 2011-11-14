/**
 * �� ĵ������ ��� ��ü�� �ٽ� �׷��ִ� ������
 * Paint Event �� ����
 */

package com.wboard.client.listener;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import com.wboard.client.ui.WBoard;


public class CanvasPaintListener implements PaintListener {
	
	private WBoard wBoard;
	
	public CanvasPaintListener(WBoard wBoard){
		this.wBoard = wBoard;
	}
	
	@Override
	public void paintControl(PaintEvent event) {
		wBoard.redraw();
	}
}

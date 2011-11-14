package com.wboard.client.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ColorDialog;

import com.wboard.client.object.WClientObject;
import com.wboard.client.ui.WBoard;
import com.wboard.client.ui.WBoard.MODE;



public class ColorSetting{
	private MODE mode;
	private WClientObject wcObject;

	private Color color;
	private RGB rgb;
	private ColorDialog colordialog;

	public ColorSetting(WBoard wBoard){
		this.mode = wBoard.getMode();

		if(mode.equals(MODE.LINE_COLOR) || mode.equals(MODE.FACE_COLOR)){
			colordialog = new ColorDialog(wBoard.getBoardGUI().getMainShell());
			colordialog.setRGB(rgb);
			rgb = colordialog.open();	// �� ����â����
			if(rgb != null){			// ������ â���� �׳� �ݾ������ �������� ����
				color = new Color(wBoard.getBoardGUI().getMainShell().getDisplay(), rgb);	//������ �� ����
			}
			else{
				return;	
			}
			
			Canvas canvas = wBoard.getCanvas();
			wBoard.setMode(wBoard.getPrevMode());	// ���� ���� ����
			
			if( wBoard.getSelected() == null){
				if(mode.equals(MODE.LINE_COLOR)){
					wBoard.setLineColor(color);
				}
				else if (mode.equals(MODE.FACE_COLOR)){
					wBoard.setFaceColor(color);
				}
				return;
			}
			else{
				wcObject =  wBoard.getSelected();
			}
			
			if( wBoard.getSelected() == null){
				return;
			}
			else{
				wcObject =  wBoard.getSelected();
			}	
			
			// ������ ��ü�����ؼ� ���� �ٲٱ�
			if(mode.equals(MODE.LINE_COLOR)){	// ����
				wcObject.setLineColor(color);
			}
			else if(mode.equals(MODE.FACE_COLOR)){	// ���
				wcObject.setFaceColor(color);				
			}
			canvas.redraw();
		}
	}
}

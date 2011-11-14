/**
 *  ĵ������ ���� ��带 �����ϴ� ������
 */

package com.wboard.client.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.wboard.client.ui.WBoard;
import com.wboard.client.ui.WBoard.MODE;
import com.wboard.client.util.ColorSetting;



public class ModeSettingListener implements Listener{

	private WBoard wBoard;	
	public ModeSettingListener(WBoard wBoard){
		this.wBoard = wBoard;
	}
	
	@Override
	public void handleEvent(Event event) {
		if(event.type == SWT.Selection){
			MODE mode = MODE.valueOf(event.widget.getData().toString());
			
			if(mode == MODE.FREE_SHAPE){			// �׸��� ���
				wBoard.setMode(MODE.FREE_SHAPE);
			}
			else if(mode == MODE.SHAPE){		// ���� ���� ���
				wBoard.setMode(MODE.SHAPE);
			}
			else if(mode == MODE.SELECT){		// ��ü ���� ���
				wBoard.setMode(MODE.SELECT);
				wBoard.getBoardGUI().getItemList().deselectAll();			// ����Ʈ�� ���� ��ü ����
				wBoard.getCanvas().redraw();
			}
			else if(mode == MODE.FACE_COLOR){	// ���� ���� ���
				wBoard.setMode(MODE.FACE_COLOR);
				new ColorSetting(wBoard);
			}
			else if(mode == MODE.LINE_COLOR){	// ���� ���� ���
				wBoard.setMode(MODE.LINE_COLOR);
				new ColorSetting(wBoard);
			}
			else if(mode == MODE.TEXT){		// �ؽ�Ʈ �ڽ� ���� ���
				wBoard.setMode(MODE.TEXT);
			}
			else if(mode == MODE.LINE_WIDTH){	// �� ���� ���� ���
				wBoard.setMode(MODE.LINE_WIDTH);
			}

			if(!(mode == MODE.LINE_WIDTH) || mode == MODE.FACE_COLOR || mode == MODE.LINE_COLOR)
				wBoard.setPrevMode(wBoard.getMode());	// ���� ��� ����
			
			if(mode == MODE.SHAPE || mode == MODE.FREE_SHAPE)
				wBoard.setSelected(null);			// ���� ���� ��ü ����
		}
	}
}
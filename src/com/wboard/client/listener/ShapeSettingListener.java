/**
 * ���尴ü(����) �׸��� ����� �� �� ������ ��Ÿ���� �����ϴ� ������
 */
package com.wboard.client.listener;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.wboard.client.object.Shape.SHAPETYPE;
import com.wboard.client.ui.WBoard;

/**
 * TODO: code
 * @author SKCCADMIN
 *
 */
public class ShapeSettingListener implements Listener{

	private WBoard wBoard;
	
	public ShapeSettingListener(WBoard wBoard){
		this.wBoard = wBoard;
	}
	// TODO: code
	@Override
	public void handleEvent(Event event) {
		SHAPETYPE shapeType = SHAPETYPE.valueOf(event.widget.getData().toString());
	}
}

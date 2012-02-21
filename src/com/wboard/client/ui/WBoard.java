package com.wboard.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;

import com.wboard.client.model.drawable.WClientObject;
import com.wboard.client.util.Constants;
import com.wboard.common.model.User;



public class WBoard {
	public static enum MODE {FREE_SHAPE, SHAPE, SELECT, FACE_COLOR, LINE_COLOR, TEXT, LINE_WIDTH};

	private User user;
	private List<User> userList;	// user list

	private List<WClientObject> WObjectList; // WObject list

	private Canvas canvas;
	
	private BoardGUI boardGUI;
	
	/* Instance Variable */
	private MODE mode;						// ���� ���
	private MODE prevMode;					// ���� ���� ���; ���� ���� ���� �� ���� �� ���� ���� ���ư���

	private WClientObject selected;					// ���õ� ��ü ����
	
	// GUI Style ���� ����
	private Color lineColor; 
	private Color faceColor;
	private int lineWidth;
	private int lineCap;
	private Font boardFont;

	public WBoard(User user){
		// initialize
		this.user = user;
		userList = new ArrayList<User>();
		WObjectList = new ArrayList<WClientObject>();
		
		
		
		// setup board GUI
		boardGUI = new BoardGUI();
		canvas = new Canvas(boardGUI.getMainShell(), SWT.NONE);
		
		boardGUI.addToolbar(this);
		boardGUI.addBoardArea(this, canvas);  // layout board area and initialize canvas
		boardGUI.addObjectControl(this);
		
		boardFont = new Font(boardGUI.getMainShell().getDisplay(), "Arial", 15, SWT.BOLD);
		mode = MODE.FREE_SHAPE;		// �׸��� ���� �ʱ�ȭ
		prevMode = this.mode;
		
		selected = null;
		lineColor = Constants.BLACK;
		faceColor = Constants.WHITE;
		lineWidth = Constants.DEFAULT_LINE;
		lineCap = SWT.CAP_ROUND;
		

	}
	public void open(){
		boardGUI.openShell();
	}
	public void redraw(){
		boardGUI.redraw(this);
	}
	
	public void addWClientObject(WClientObject wcObj){
		WObjectList.add(wcObj);
		boardGUI.addListItem(wcObj);
	}
		
	/** Getters & Setters **/
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserList() {
		return userList;
	}
	public List<WClientObject> getWObjectList() {
		return WObjectList;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	public WClientObject getSelected() {
		return selected;
	}
	public void setSelected(WClientObject selected) {
		this.selected = selected;
	}
	public MODE getMode() {
		return mode;
	}
	public void setMode(MODE mode) {
		this.mode = mode;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public Color getFaceColor() {
		return faceColor;
	}
	public void setFaceColor(Color faceColor) {
		this.faceColor = faceColor;
	}
	public int getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	public int getLineCap() {
		return lineCap;
	}
	public void setLineCap(int lineCap) {
		this.lineCap = lineCap;
	}
	public MODE getPrevMode() {
		return prevMode;
	}
	public void setPrevMode(MODE prevMode) {
		this.prevMode = prevMode;
	}
	public BoardGUI getBoardGUI() {
		return boardGUI;
	}
	public Font getBoardFont() {
		return boardFont;
	}
	public void setBoardFont(Font boardFont) {
		this.boardFont = boardFont;
	}

}

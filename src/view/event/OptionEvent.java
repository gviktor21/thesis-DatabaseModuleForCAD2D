package view.event;

import java.util.*;

public class OptionEvent extends EventObject {
	private int option;
	private int etype;
	
	public static final int EVENT_TYPE_METHOD = 1;
	public static final int EVENT_TYPE_OPTION = 2;
	
	
	public OptionEvent(Object source, int type, int opt) {
		super(source);
		System.out.println("OptionEvent is created.");
		this.option = opt;
		this.etype = type;
	}
	public int getOption() {
		return this.option;
	}
	public int getType() {
		return this.etype;
	}
}

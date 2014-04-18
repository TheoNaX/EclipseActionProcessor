package sk.fiit.theo.eclipseproc.actions;

import java.util.HashMap;

public enum ActionType {
	ADD_FILE(1),
	ADD_PACKAGE(2),
	ADD_PROJECT(3),
	CLOSE_FILE(4),
	DELETE_FILE(5),
	DELETE_PACKAGE(6),
	OPEN_FILE(7),
	REFACTOR_FILE(8),
	REFACTOR_PACKAGE(9),
	SWITCH_FILE(10);
	
	private final int value;
    private ActionType(final int value) {
        this.value = value;
    }
    
    private static HashMap<ActionType, String> wekaNames = new HashMap<ActionType, String>(); 

    public int getValue() {
        return this.value;
    }
    
    public static String getActionTypeForWeka(final String type) {
    	return wekaNames.get(ActionType.valueOf(type));
    }
    
    public static String getActionTypeForWeka(final int type) {
    	return wekaNames.get(getActionTypeFromInt(type));
    }
    
    private static ActionType getActionTypeFromInt(final int type) {
    	switch (type) {
    	case 1:
    		return ADD_FILE;
    	case 2:
    		return ADD_PACKAGE;
    	case 3:
    		return ADD_PROJECT;
    	case 4:
    		return CLOSE_FILE;
    	case 5:
    		return DELETE_FILE;
    	case 6:
    		return DELETE_PACKAGE;
    	case 7: 
    		return OPEN_FILE;
    	case 8:
    		return REFACTOR_FILE;
    	case 9:
    		return REFACTOR_PACKAGE;
    	case 10:
    		return SWITCH_FILE;
    	}
    	
    	return SWITCH_FILE;
    }
    
    public static void main(final String[] args) {
		System.out.println(getActionTypeForWeka("ADD_FILE"));
	}
    
    private static void initWekaNames() {
    	wekaNames.put(ADD_FILE, "add-file");
    	wekaNames.put(ADD_PACKAGE, "add-package");
    	wekaNames.put(ADD_PROJECT, "add-project");
    	wekaNames.put(CLOSE_FILE, "close-file");
    	wekaNames.put(DELETE_FILE, "delete-file");
    	wekaNames.put(DELETE_PACKAGE, "delete-package");
    	wekaNames.put(OPEN_FILE, "open-file");
    	wekaNames.put(REFACTOR_FILE, "refactor-file");
    	wekaNames.put(REFACTOR_PACKAGE, "refactor-package");
    	wekaNames.put(SWITCH_FILE, "switch-file");
    }
    
    static {
    	initWekaNames();
    }
}

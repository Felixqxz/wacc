package utils;

import instructions.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable<Type> {

  // Parent table
  private SymbolTable<Type> parentTable;
  // Children tables
  private final List<SymbolTable<Type>> children = new ArrayList<>();
  // Map that stores variable names and types
  private final Map<String, Type> typeMap = new HashMap<>();;
  // Map that stores function names and function entity
  private final Map<String, Function> functionMap = new HashMap<>();
  // Map that stores variable names and their addresses in current stack
  private final Map<String, Integer> addressMap = new HashMap<>();

  // Current stack pointer
  private int stackPointer = 0;
  // Max size required in current stack
  private int stackSize = 0;
  // Shift all variable addresses
  private int offSetValue = 0;

  private Label breakLabel = null;
  private Label continueLabel = null;

  private boolean isLoop = false;
  private boolean isForLoop = false;

  public final int STACK_SIZE_LIMIT = 1024;

  /* constructor for top level symbol table. */
  public SymbolTable() {}

  public SymbolTable(SymbolTable<Type> parentTable) {
    this.parentTable = parentTable;
  }

  public void setAsLoop() {
    this.isLoop = true;
  }

  public void setAsForLoop() {
    this.isForLoop = true;
  }

  public int getStackSize() {
    return stackSize;
  }

  public Map<String, Function> getFunctionMap() {
    return functionMap;
  }

  public Label getBreakLabel() {
    if (isLoop) {
      return breakLabel;
    }
    return parentTable.getBreakLabel();
  }

  public Label getContinueLabel() {
    if (continueLabel != null) {
      return continueLabel;
    }
    return parentTable.getContinueLabel();
  }

  public int getLoopSize() {
    if (isLoop) {
      return stackSize;
    }
    return stackSize + parentTable.getLoopSize();
  }

  public boolean isInForLoop() {
    return isForLoop;
  }

  public int getLoopSizeWithCondition() {
    if (isLoop || parentTable.isInForLoop()) {
      return stackSize;
    }
    return stackSize + parentTable.getLoopSizeWithCondition();
  }

  public void setBreakLabel(Label breakLabel) {
    this.breakLabel = breakLabel;
  }

  public void setContinueLabel(Label continueLabel) {
    this.continueLabel = continueLabel;
  }

  public void addStackSizeBy(Type type) {
    if (type.toString().equals("BOOL") || type.toString().equals("CHAR")) {
      stackSize ++;
    } else {
      stackSize += 4;
    }
  }

  public void addOffSetValueBy(int offSet) {
    this.offSetValue += offSet;
  }

  public List<SymbolTable<Type>> getChildren() {
    return children;
  }

  public void addChildTable(SymbolTable<Type> child) {
    this.children.add(child);
  }

  public SymbolTable<Type> getParentTable() {
    return parentTable;
  }

  public Map<String, Type> getStringTypeMap() {
    return typeMap;
  }

  public void addVariable(String name, Type obj) {
    typeMap.put(name, obj);
  }

  public void addFunction(String name, Function func) {
    functionMap.put(name, func);
  }

  public void reverselyAddAddress(String name, Type type) {
    if (type.toString().equals("BOOL") || type.toString().equals("CHAR")) {
      stackPointer++;
    } else {
      stackPointer += 4;
    }
    addressMap.put(name, stackSize - stackPointer);
  }

  public void addAddress(String name, Type type) {
    addressMap.put(name, stackPointer);
    if (type.toString().equals("BOOL") || type.toString().equals("CHAR")) {
      stackPointer++;
    } else {
      stackPointer += 4;
    }
  }

  public int lookupAddress(String name) {
    if (parentTable == null) {
      return addressMap.get(name) + offSetValue;
    }
    if (addressMap.containsKey(name)) {
      return addressMap.get(name) + offSetValue;
    } else {
      return stackSize + parentTable.lookupAddress(name) + offSetValue;
    }
  }

  public Type lookupCurrent(String name) {
    return typeMap.get(name);
  }

  /* Lookup name in current and enclosing. */
  public Type lookup(String name) {

    /* lookup reached to the top level symbol table. */
    if (parentTable == null) {
      return typeMap.get(name);
    }
    if (typeMap.containsKey(name)) {
      return typeMap.get(name);
    } else {
      return parentTable.lookup(name);
    }
  }

  public int getTotalStackSize() {
    int size = 0;
    for (Map.Entry<String, Type> entry : typeMap.entrySet()) {
      Type type = entry.getValue();
      if (type.toString().equals("BOOL") || type.toString().equals("CHAR")) {
        size++;
      } else {
        size += 4;
      }
    }
    for (SymbolTable<Type> child : children) {
      size += child.getTotalStackSize();
    }
    return size;
  }

}

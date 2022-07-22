package instructions;

public class Label extends Instruction {

  String label;

  /* Constructor for user defined labels. */
  public Label(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Label)) {
      return false;
    }
    Label that = (Label) obj;
    // For user defined labels, they are treated the same only if they have the same label name.
    return this.label.equals(that.label);
  }

  @Override
  public int hashCode() {
    return label.hashCode();
  }

  @Override
  public String toString() {
    if (label.equals(".data") || label.equals(".text")) {
      return label + "\n\n";
    }
    if (label.equals(".global main")) {
      return label + "\n";
    }
    if (label.equals(".ltorg")) {
      return "\t\t" + label + "\n";
    }
    return label + ":\n";
  }
}
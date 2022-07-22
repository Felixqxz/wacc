package instructions;

public class Branch extends Instruction {

    private Link link = Link.WITHOUT_LINK;
    private Cond cond = Cond.AL;
    private final Label label;

    public Branch(Link link, Cond cond, Label label) {
        this.label = label;
        this.cond = cond;
        this.link = link;
    }

    public Branch(Link link, Label label) {
        this.link = link;
        this.label = label;
    }

    public Branch(Cond cond, Label label) {
        this.cond = cond;
        this.label = label;
    }

    public Branch(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "\t\tB" + link + cond + " " + label.getLabel() + "\n";
    }
}

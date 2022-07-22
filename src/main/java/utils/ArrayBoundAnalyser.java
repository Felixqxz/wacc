package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayBoundAnalyser {

    private int length;
    private List<ArrayBoundAnalyser> children = new ArrayList<>();

    public ArrayBoundAnalyser(int length) {
        this.length = length;
    }

    public void addChild(ArrayBoundAnalyser analyser) {
        children.add(analyser);
    }

    public ArrayBoundAnalyser get(int i) {
        return children.get(i);
    }

    public int getLength() {
        return length;
    }

}

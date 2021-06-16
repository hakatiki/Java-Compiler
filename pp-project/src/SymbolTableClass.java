package pp.block5.cc.homework;

import javafx.util.Pair;
import pp.block5.cc.simple.Type;

import java.util.*;


public class SymbolTableClass implements SymbolTable{
    private java.util.Stack<Map<String,Integer>> setStack = new java.util.Stack<>();
    private List<Pair<String,Integer>> list = new LinkedList<Pair<String, Integer>>();
    @Override
    public void openScope() {
        setStack.push( new HashMap<String, Integer>() );
    }

    @Override
    public void closeScope() {
        int remove = setStack.peek().size();
        int begin = list.size()-1;
        int end = list.size()-remove -1;
        for (int i = begin; i >end; i--)
            list.remove(i);
        setStack.pop();
    }

    @Override
    public boolean add(String id, int val) {
        if (setStack.size() == 0)
            setStack.push(new HashMap<String, Integer>() );
        if (setStack.peek().containsKey(id))
            return false;
        setStack.peek().put(id, val);
        list.add(new Pair<>(id, val));
        return true;
    }

    @Override
    public int getValue(String id) {
        for (int i = list.size()-1; i >=0 ; i--){
            Pair <String, Integer> entry = list.get(i);
            String fst = entry.getKey();
            if (fst.equals(id))
                return entry.getValue();
        }
        return -1;
    }


    public void raiseItem(String id) {
        int value = getValue(id);
        if (value != -1) {
            int newVal = value + 1;
            if (!setStack.peek().containsKey(id)) {
                setStack.peek().put(id, newVal);
                list.add(new Pair<>(id, newVal));
            }
            else{
                setStack.peek().put(id, newVal);
                for (int i = list.size() - 1; i >= 0; i--)
                    if (list.get(i).getKey().equals(id))
                        list.set(i, new Pair<>(id, newVal));
            }
        } else {
            setStack.peek().put(id, 1);
            list.add(new Pair<>(id, 1));
        }
    }
}

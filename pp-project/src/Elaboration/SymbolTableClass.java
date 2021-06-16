package Elaboration;


import Elaboration.SymbolTable;

import java.util.*;

public class SymbolTableClass implements SymbolTable {
    private Stack<Set<String>> setStack = new Stack<>();
    private List<String> list = new LinkedList<>();
    @Override
    public void openScope() {
        setStack.push( new HashSet<>() );
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
    public boolean add(String id) {
        if (setStack.size() == 0)
            setStack.push(new HashSet<>());
        if (setStack.peek().contains(id))
            return false;
        setStack.peek().add(id);
        list.add(id);
        return true;
    }

    @Override
    public boolean contains(String id) {
        return list.contains(id);
    }
}

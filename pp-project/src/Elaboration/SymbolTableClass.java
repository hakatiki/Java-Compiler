package Elaboration;


import java.util.*;


public class SymbolTableClass{
    private java.util.Stack<Map<String,Type>> setStack = new java.util.Stack<>();
    private List<Pair<String,Type>> list = new LinkedList <>();

    public void openScope() { setStack.push( new HashMap<>() ); }

    public void closeScope() {
        int remove = setStack.peek().size();
        int begin = list.size()-1;
        int end = list.size()-remove -1;
        for (int i = begin; i > end; i--)
            list.remove(i);
        setStack.pop();
    }

    public boolean add(String id, Type val) {
        if (setStack.size() == 0)
            setStack.push(new HashMap<String, Type>() );
        if (setStack.peek().containsKey(id))
            return false;
        setStack.peek().put(id, val);
        list.add(new Pair<>(id, val));
        return true;
    }

    public Type getValue(String id) {
        for (int i = list.size()-1; i >=0 ; i--){
            Pair <String, Type> entry = list.get(i);
            String fst = entry.getFirst();
            if (fst.equals(id))
                return entry.getSecond();
        }
        return Type.NotInScope;
    }

}

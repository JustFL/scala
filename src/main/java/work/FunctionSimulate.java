package work;

import java.util.ArrayList;
import java.util.List;

interface Operate{
    public Object change(Object o);
}

class MyList {

    private List<Object> myList = null;
    private int length = 0;

    public MyList(List<Object> list){
        myList = list;
        length = list.size();
    }

    @Override
    public String toString() {
        return myList.toString();
    }

    public void map(Operate op){
        for (int i = 0; i < myList.size(); i++) {
            myList.set(i,op.change(myList.get(i)));
        }
    }
}


public class FunctionSimulate {
    public static void main(String[] args) {
        List<Object> strings = new ArrayList<>();
        strings.add("huangbo");
        strings.add("dengchao");
        strings.add("luhan");

        System.out.println(strings);

        MyList list = new MyList(strings);
        Operate op = new Operate() {
            @Override
            public Object change(Object o) {
                if (o.getClass().isInstance("String")){
                    System.out.println("envoke");
                    return "hello " + o;
                }
                return null;
            }
        };

        list.map(op);

        System.out.println(list);
    }
}

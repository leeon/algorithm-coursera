import java.util.Iterator;


public class TestDeque {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(1);
        dq.addLast(3);

        Iterator it = dq.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}

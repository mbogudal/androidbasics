package purplestudio.androidbasics.lib.model;

import java.util.ArrayList;
import java.util.List;

/*thread safe generic for lists*/
public class SharedList<T> extends Mutex
{

    private List<T> list;

    public SharedList(){
        list = new ArrayList<>();
    }

    public void setList(List<T> list){
        super.acquire();
        this.list = list;
        super.release();
    }

    public List<T> getList(){
        super.acquire();
        List<T> tmp = new ArrayList<>(list);
        super.release();

        return tmp;
    }

    public void add(T item){
        super.acquire();
        list.add(item);
        super.release();
    }

    public T get(int i){
        super.acquire();
        T item = list.get(i);
        super.release();

        return item;
    }

    public int getId(T item){
        super.acquire();
        int id = list.indexOf(item);
        super.release();

        return id;
    }

}

package utils;

/**
 * Created by SeventhSense on 8/20/2017.
 */
public final class ImmutablePair<T,U>{
    private final T obj1;
    private final U obj2;

    public ImmutablePair(T t, U u){
        this.obj1 = t;
        this.obj2 = u;
    }

    public T getFirst(){
        return this.obj1;
    }

    public U getSecond(){
        return this.obj2;
    }
}
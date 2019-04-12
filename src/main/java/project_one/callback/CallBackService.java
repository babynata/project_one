package project_one.callback;

public interface CallBackService<T> {

    T doBefore();

    T doInMiddle();

    T doAfter();

}

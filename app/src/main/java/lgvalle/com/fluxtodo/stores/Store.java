package lgvalle.com.fluxtodo.stores;

import lgvalle.com.fluxtodo.actions.Action;
import lgvalle.com.fluxtodo.dispatcher.Dispatcher;

/**
 * Store模块包含了App状态和业务逻辑，会对Dispatcher派发的Action做出反应，并执行
 * 行管的业务逻辑，并且产生一个"change"事件。
 * <p/>
 * 系统中的其他任何模块都不需要知道此时App状态。（这句话不是很理解）
 * <p/>
 * Store必须暴露一个接口来让View获取App状态，这样View模块就可以访问Store模块来更新UI了。在本例中，
 * 直接通过{@link Dispatcher}来暴露了接口（直接复用了Dispatcher传递Action给Store的方法）。
 * <p/>
 * Created by lgvalle on 02/08/15.
 */
public abstract class Store {

    final Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    /** 注册Store中状态改变时的回调（订阅Store中状态的改变） */
    public void register(Object object) {
        dispatcher.register(object);
    }

    /** 取消Store中状态改变时的回调（取消订阅Store中状态的改变） */
    public void unRegister(Object object) {
        dispatcher.unregister(object);
    }

    abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);

    public interface StoreChangeEvent {}
}

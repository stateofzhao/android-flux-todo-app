package lgvalle.com.fluxtodo.actions;

import java.util.HashMap;

/**
 * 简单的java POJO 类，保存type和数据。传递给Dispatcher然后Dispatcher再分发给所有对此Action感兴趣
 * 的Store（在此例子中是把 所有已存在Store都注册给了Dispatcher，Dispatcher会把所有传递过来的
 * Action分发给所有Store，然后Store根据Action的type来决定是否执行以及执行何种操作），
 * 之后Store根据Action的type来进行相应的操作，之后Store通知View来更新View状态。
 * <p/>
 * Created by lgvalle on 22/07/15.
 */
public class Action {
    private final String type;
    private final HashMap<String, Object> data;

    Action(String type, HashMap<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public static Builder type(String type) {
        return new Builder().with(type);
    }

    public String getType() {
        return type;
    }

    public HashMap getData() {
        return data;
    }

    public static class Builder {

        private String type;
        private HashMap<String, Object> data;

        Builder with(String type) {
            if (type == null) {
                throw new IllegalArgumentException("Type may not be null.");
            }
            this.type = type;
            this.data = new HashMap<>();
            return this;
        }

        public Builder bundle(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("Key may not be null.");
            }

            if (value == null) {
                throw new IllegalArgumentException("Value may not be null.");
            }
            data.put(key, value);
            return this;
        }

        public Action build() {
            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("At least one key is required.");
            }
            return new Action(type, data);
        }
    }
}

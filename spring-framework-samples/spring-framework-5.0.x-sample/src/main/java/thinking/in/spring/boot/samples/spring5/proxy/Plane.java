package thinking.in.spring.boot.samples.spring5.proxy;

import org.springframework.stereotype.Component;

/**
 * 飞机，具备飞行能力
 */
@Component
public class Plane implements Flyable {

    // 飞机型号
    private String type;
    // 乘客
    private Human passenger;

    public Plane() {}

    public Plane(String type, Human human) {
        this.type = type;
        this.passenger = human;
    }

    @Override
    public void fly() {
        System.out.println(passenger.getName() + " 搭乘飞机 " + type + " 翱翔于蓝天...");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

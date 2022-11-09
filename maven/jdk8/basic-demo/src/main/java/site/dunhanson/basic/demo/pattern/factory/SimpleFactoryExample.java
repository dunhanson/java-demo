package site.dunhanson.basic.demo.pattern.factory;

/**
 * 工厂类包含必要的判断逻辑，可以决定再什么时候创建哪一个产品类的实例<br>
 * 客户端可以免除直接创建产品对象的职责，而仅仅“消费”产品<br>
 * 简单工厂模式实现了对象创建和使用的分离
 * @author dunhanson
 * @since 2022-11-09
 */
public class SimpleFactoryExample {
    interface Animal {
        void run();
    }

    static class Cat implements Animal {
        @Override
        public void run() {
            System.out.println("cut run.");
        }
    }

    static class Dog implements Animal {
        @Override
        public void run() {
            System.out.println("dog run.");
        }
    }

    static class AnimalFactory {
        public static Animal getAnimal(String category) {
            Animal animal = null;
            if("cat".equals(category)) {
                animal = new Cat();
            } else if("dog".equals(category)) {
                animal = new Dog();
            }
            return animal;
        }
    }

    public static void main(String[] args) {
        // cut
        Animal animal1 = AnimalFactory.getAnimal("cat");
        animal1.run();
        // dog
        Animal animal2 = AnimalFactory.getAnimal("dog");
        animal2.run();
    }
}

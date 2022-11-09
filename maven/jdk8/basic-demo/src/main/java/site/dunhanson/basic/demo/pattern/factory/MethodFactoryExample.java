package site.dunhanson.basic.demo.pattern.factory;

public class MethodFactoryExample {
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

    interface AnimalFactory {
        Animal getAnimal();
    }

    static class CatFactory implements AnimalFactory {

        @Override
        public Animal getAnimal() {
            return new Cat();
        }

        public static AnimalFactory getFactory() {
            return new CatFactory();
        }
    }

    static class DogFactory implements AnimalFactory {

        @Override
        public Animal getAnimal() {
            return new Dog();
        }

        public static DogFactory getFactory() {
            return new DogFactory();
        }
    }


    public static void main(String[] args) {
        // cat
        AnimalFactory factory1 = CatFactory.getFactory();
        Animal animal1 = factory1.getAnimal();
        animal1.run();
        // dog
        AnimalFactory factory2 = DogFactory.getFactory();
        Animal animal2 = factory2.getAnimal();
        animal2.run();
    }
}

interface Predator {
    String getFood();
}

interface Barkable {
    String bark();
}

class Animal {
    String name;

    void setName(String name) {
        this.name = name;
    }
}

class Tiger extends Animal implements Predator, Barkable{
    public String getFood() {
        return "apple";
    }
    public String bark() {
        return "어흥";
    }
}

class Lion extends Animal implements Predator, Barkable{
    public String getFood() {
        return "banana";
    }
    public String bark() {
        return "으르렁";
    }
}

class Zookeeper {
    void feed(Predator predator) {
        System.out.println("feed "+ predator.getFood());
    }
}

class Bouncer {
    void barkAnimal(Animal animal) {
        if (animal instanceof Tiger) {
            System.out.println("어흥");
        }
        if (animal instanceof Lion) {
            System.out.println("으르렁");
        }
    }
    void bark_inter(Barkable animal) {
        System.out.println(animal.bark());
    }
}

public class Sample {
    public static void main(String[] args) {
        Zookeeper zookeeper = new Zookeeper();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();
        zookeeper.feed(lion);
        zookeeper.feed(tiger);
        Bouncer bouncer = new Bouncer();
        bouncer.barkAnimal(lion);
    }
}

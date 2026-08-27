import java.util.*;

public class Main {
    public static void main(String[] args) {

        Animal dog = new Cachorro();

        dog.emitirSom();
    }
}

class Animal {
    void emitirSom(){
        System.out.println("som generico");
    }
}

class Cachorro extends Animal {

    @Override
    void emitirSom(){
        System.out.println("au au");
    }

    void latir(){
        System.out.println("au");
    }
}
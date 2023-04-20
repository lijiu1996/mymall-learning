package com.lijiawei.practice.mymall.learning.init;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 *  案例学习Java 泛型
 */
@Slf4j
public class RawTypeTest {

//    @Test
//    public void test() {
//
//        Herbivore herbivore = new Herbivore();
//        Herbivore spawn = herbivore.spawn();
//
////        Animal animal = new Animal();
////        Animal spawn1 = animal.spawn();
//
//        Carnivore carnivore = new Carnivore();
//        Carnivore spawn2 = carnivore.spawn();
//
//        Creature<Herbivore> c1 = herbivore;
//        Herbivore spawn3 = c1.spawn();
//
////        Creature<Animal> c2 = animal;
////        Animal spawn4 = c2.spawn();
//
//        Creature<Carnivore> c3 = carnivore;
//        Carnivore spawn5 = carnivore.spawn();
//
//
//    }
//
//    @Test
//    public void test2() {
//
//
//    }

    // 需求一 返回生物的接口 存在一个繁衍方法 该方法返回的类型可能有多个 根据T传入
    // 需求二 限定T的类型必须是生物
    // 需求三 增加一个吃的方法 动物可以吃指定生物
    //

    // 生物
//    static abstract class Creature<T extends Creature>  {
//
//        public abstract T spawn();
//    }
//
//    static class Herb {
//
//    }
//
//    // 添加了一层动物 动物有吃东西的方法
//    static abstract class Animal<T extends Animal, V extends Food> extends Creature<T> {
//
//        public abstract void eating(V food);
//
//    }
//
//    // 食肉动物
//    static class Carnivore extends Animal<Carnivore,Meat> {
//
//        @Override
//        public Carnivore spawn() {
//            log.info("繁衍 食肉动物");
//            return new Carnivore();
//        }
//
//
//        @Override
//        public void eating(Meat food) {
//            log.info("食肉动物吃肉");
//        }
//    }
//
//    // 食草动物
//    static class Herbivore extends Animal<Herbivore,Herb> {
//
//        @Override
//        public Herbivore spawn() {
//            log.info("繁衍 食草动物");
//            return new Herbivore();
//        }
//
//        @Override
//        public void eating(Herb food) {
//            log.info("食草动物吃草");
//        }
//    }

}

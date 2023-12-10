package secondQuestion;

import java.lang.reflect.Field;
import java.util.*;

class Monster {
    String eyeColor;
    String featherColor;
    String magicalAbilities;
    String size;
    String strength;
    String durability;
    String weakness;
    String aggressionLevel;
    public Monster() {
    }
    public Monster(String eyeColor, String featherColor, String magicalAbilities,
                   String size, String strength, String durability, String weakness,
                   String aggressionLevel) {
        this.eyeColor = eyeColor;
        this.featherColor = featherColor;
        this.magicalAbilities = magicalAbilities;
        this.size = size;
        this.strength = strength;
        this.durability = durability;
        this.weakness = weakness;
        this.aggressionLevel = aggressionLevel;
    }

    public Monster(Monster parent) {
        this.eyeColor = parent.eyeColor;
        this.featherColor = parent.featherColor;
        this.magicalAbilities = parent.magicalAbilities;
        this.size = parent.size;
        this.strength = parent.strength;
        this.durability = parent.durability;
        this.weakness = parent.weakness;
        this.aggressionLevel = parent.aggressionLevel;
    }

    public void displayTraits() {
        System.out.println("Monster Traits:");
        System.out.println("Eye Color: " + eyeColor);
        System.out.println("Feather Color: " + featherColor);
        System.out.println("Magical Abilities: " + magicalAbilities);
        System.out.println("Size: " + size);
        System.out.println("Strength: " + strength);
        System.out.println("Durability: " + durability);
        System.out.println("Weakness: " + weakness);
        System.out.println("Aggression Level: " + aggressionLevel);
    }

    private static Object getRandomTrait(Object traitValue1, Object traitValue2) {
        return Math.random() < 0.5 ? traitValue1 : traitValue2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monster monster)) return false;
        return Objects.equals(eyeColor, monster.eyeColor) && Objects.equals(featherColor, monster.featherColor) && Objects.equals(magicalAbilities, monster.magicalAbilities) && Objects.equals(size, monster.size) && Objects.equals(strength, monster.strength) && Objects.equals(durability, monster.durability) && Objects.equals(weakness, monster.weakness) && Objects.equals(aggressionLevel, monster.aggressionLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eyeColor, featherColor, magicalAbilities, size, strength, durability, weakness, aggressionLevel);
    }


    public static List<Monster> createBabyMonsters(Monster parent1, Monster parent2) {
        Set<Monster> children = new HashSet<>();

        Class<?> monsterClass = Monster.class;

        Field[] fields = monsterClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {
                // Get the trait values from the parents
                Object traitValue1 = fields[i].get(parent1);
                Object traitValue2 = fields[i].get(parent2);
                // Create a child with the combination of traits
                Monster child1 = new Monster(parent1);
                Monster child2 = new Monster(parent2);
                fields[i].setAccessible(true);
                fields[i].set(child1, getRandomTrait(traitValue1, traitValue2));
                fields[i].set(child2, getRandomTrait(traitValue1, traitValue2));
                // Add the child to the list
                children.add(child1);
                children.add(child2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return children.stream().toList();
    }
}
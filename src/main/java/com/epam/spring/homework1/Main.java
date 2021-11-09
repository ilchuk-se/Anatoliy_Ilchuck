package com.epam.spring.homework1;

import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        Pet pet = context.getBean(Pet.class);
        pet.printPets();

        String cheetahName = "cheetah";
        Cheetah cheetahByName = (Cheetah)context.getBean(cheetahName);
        Cheetah cheetahByType = context.getBean(Cheetah.class);

        System.out.println(System.lineSeparator() + "Cheetah by name: " + cheetahByName + ", hash: " + cheetahName.hashCode());
        System.out.println("Cheetah by type: " + cheetahByType + ", hash: " + cheetahByType.hashCode());
        System.out.println("Is they are equals: " + cheetahByName.equals(cheetahByType));
    }
}

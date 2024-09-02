package registro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Registro {
    Persona persona = new Persona();
    Scanner sc = new Scanner(System.in);
    ArrayList<Persona> personList = new ArrayList<Persona>(10);

    public void entry () {

        while(personList.size() <= 10 ) {
            System.out.println("Ingrese nuevo nombre de usuario: ");
            persona.setUser(sc.nextLine());
            passValidate();
            System.out.println("Ingrese nombre completo: ");
            persona.setName(sc.nextLine());
            dateValidate();
            personList.add(persona);
            persona = new Persona();
            showList();
        }
    }

    private void passValidate() {
        boolean flag = false;

        while (!flag) {
            System.out.println("Ingrese contrasena: ");
            persona.setPassword(sc.nextLine());
            String regex = "^(?=.*[A-Z].*[A-Z])(?=(.*[0-9\\W].*){3,}).{11,}$";
//            flag = true;

            flag = persona.getPassword().matches(regex) ? true : false;

            if (flag) {
                System.out.println("Cumple los requisitos");
            }    else {
                System.out.println("No cumple los requisitos");
            }
//            if (persona.getPassword().length() <= 10) {
//                flag = false;
//                System.out.println("Contraseña debe ser de 10 carácteres");
//            }
//
//            if (!persona.getPassword().matches(".*[A-Z].*[A-Z].*")) {
//                flag = false;
//                System.out.println("Contraseña debe ser tener al menos 2 letras mayúsculas");
//            }

//            if (!persona.getPassword().matches(".*([0-9\\W].*){3,}.*")) {
//                flag = false;
//                System.out.println("Contraseña debe ser tener al menos 3 caracteres que sean números o símbolos");
//            }
        }
    }

    private void dateValidate() {
        System.out.println("Ingrese fecha de nacimiento (DD-MM-AAAA): ");
        persona.setBirthDate(sc.nextLine());
        int age = LocalDate.now().getYear() - Integer.parseInt(persona.getBirthDate().substring(6, 10));

        if (age >= 18 ) {
            System.out.println("Puede pasar la zona para mayores de 18 años");
        } else {
            System.out.println("Puede pasar, pero con verguenza por ser menor de edad");
        }
    }

    private void showList() {
        int i = 1;
        for(Persona persona : personList) {
            System.out.println(i +"- "+ persona.getName());
            i++;
        }
        if (personList.size() == 10) {
            System.out.println("La lista de usuarios se encuentra llena");
        }
    }
}

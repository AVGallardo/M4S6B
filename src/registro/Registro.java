package registro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Registro {
    Scanner sc = new Scanner(System.in);
    ArrayList<Persona> personList = new ArrayList<Persona>(10);

    public void entry () {

        while(personList.size() <= 10 ) {
            Persona persona = new Persona();

            System.out.println("Ingrese nuevo nombre de usuario: ");
            persona.setUser(sc.nextLine());

            passValidate(persona);

            System.out.println("Ingrese nombre completo: ");
            persona.setName(sc.nextLine());

            dateValidate(persona);

            personList.add(persona);
            showList();
        }
    }

    private void passValidate(Persona persona) {
        String regex = "^(?=.*[A-Z].*[A-Z])(?=(.*[0-9\\W].*){3,}).{11,}$";

        while (true) {
            System.out.println("Ingrese contrasena: ");
            String password = sc.nextLine();
            if (password.matches(regex)) {
                persona.setPassword();
                System.out.println("Cumple con los requisitos.");
                break;
            } else {
                System.out.println("No cumple con los requisitos.");

            }
        }
    }

    private void dateValidate(Persona persona) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while(true) {
            System.out.println("Ingrese fecha de nacimiento (DD-MM-AAAA)");
            String dateString = sc.nextLine();

            try {
                LocalDate birthDate = LocalDate.parse(dateString, formatter);
                persona.setBirthDate(dateString);
                int age = LocalDate.now().getYear() - birthDate.getYear();
                if (age >= 18 ) {
                    System.out.println("Puede pasar la zona para mayores de 18 años");
                } else {
                    System.out.println("Puede pasar, pero con verguenza por ser menor de edad");
                }
            } catch(DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use DD-MM-AAAA.");
            }
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

package LabExercise1;

import LabExercise2.StringUtil;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here

        // Read Cities and Countries - Exercise 1 - a)
//        System.out.println("Exercise 1 - a) Read Cities and Countries");
        String citiesFilePath = "E:\\ITI\\7 - Java and UML_Amr Elshafey\\Day3\\cities.csv";
        String countriesFilePath = "E:\\ITI\\7 - Java and UML_Amr Elshafey\\Day3\\countries.csv";

        CSVDAO cityCSVDAO = new CSVDAO(() -> new City());
        CSVDAO countryCSVDAO = new CSVDAO(() -> new Country());

        List<City> cities = cityCSVDAO.readAllFromCSV(citiesFilePath);
        List<Country> countries = countryCSVDAO.readAllFromCSV(countriesFilePath);

        cities.forEach(c-> System.out.println(c));

        // create map of countries and cities - Exercise 1 - b)
        System.out.println("Exercise 1 - b) create map of countries and cities");
        Map<String, List<City>> myMap = cities.stream()
                .collect(Collectors.groupingBy(City::getCode));

        myMap.forEach( (k,v) -> System.out.println("key = " + k + ", value = " + v) );

        // Exercise 1 - c) For a given country code sort the cities according to the population
        System.out.println("Exercise 1 - c) For a given country code sort the cities according to the population");
        String countryCode = "BHR";
        List<City> specific_cities = myMap.get(countryCode);
        Collections.sort(specific_cities);
        specific_cities.forEach(elem -> System.out.println(elem.getName() + "  " + elem.getPopulation()));

        // Exercise 2 - a)
//        ( s1, s2) -> s1.compareTo(s2) > 0 ? true : false
        System.out.println(StringUtil.betterString("Hello", "Hey", StringUtil::compareStrings) );

        // Exercise 2 - b) isAlphapeticOnly
        System.out.println(StringUtil.isAlphapeticOnly("Hello", Character::isLetter));

        // Highest population city of each country
        System.out.println("Highest population city of each country");
        cities.stream()
                .collect(Collectors.groupingBy(
                        City::getCode,
                        Collectors.maxBy(Comparator.comparing(City::getPopulation))
                    ))
                .forEach((k,v)->System.out.println(k + " - " + v));

        // Highest population city by continent
        System.out.println("Highest population city by continent");
        Optional<City> maxPopByContin = cities.stream()
                .filter(city ->
                        countries.stream().map(Country::getFIFA).anyMatch(s -> s.equals(city.getCode()))
                )
                .max(Comparator.comparing(City::getPopulation));
        System.out.println(maxPopByContin.orElseThrow(NoSuchElementException::new));

        Map<Stream<String>, List<City>> citiesByCon = cities.stream()
                .collect(Collectors.groupingBy(
                        city -> countries.stream()
                                .filter(country -> country.getFIFA().equals(city.getCode()))
                                .map(Country::getContinent)));
        citiesByCon.forEach((k,v)-> System.out.println(k + " - " + v.stream().max(Comparator.comparing(City::getPopulation))));

//
//        collect.values().forEach(list -> list.stream()
//                .max(Comparator.comparing(City::getPopulation))
//                .ifPresent(s -> System.out.println(s)));
    }
}

package LabExercise1;

import java.util.Comparator;

public class City implements Create<City>, Comparable<City>, Comparator<City> {
    private String Name;
    private String Country;
    private String Code;
    private Long Population;
    private boolean isCapital;

    public City get()
    {
        return this;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setPopulation(Long population) {
        Population = population;
    }

    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public String getCode() {
        return Code;
    }

    public Long getPopulation() {
        if(Population != null)
            return Population;
        else
            return new Long(0);
    }

    public boolean isCapital() {
        return isCapital;
    }

    @Override
    public String toString() {
        return "City{" +
                "Name='" + Name + '\'' +
                ", Country='" + Country + '\'' +
                ", Code='" + Code + '\'' +
                ", Population=" + Population +
                ", isCapital=" + isCapital +
                '}';
    }

    @Override
    public int compareTo(City object) {
        return this.Population.compareTo(object.getPopulation());
    }

    @Override
    public City create (String rowData)
    {
        String[] inputs = rowData.split("\",\"", -1);
        String result = inputs[0].replaceAll("\"", "");
        setName(result);
        setCode(inputs[6]);
        setCountry(inputs[4]);
        if(inputs[9].length() != 0)
        {
            if (inputs[9].contains(".")) {
                double population = Double.parseDouble(inputs[9]);
                setPopulation(new Double(population).longValue());
            } else
                setPopulation(Long.parseLong(inputs[9]));
        }
        if(inputs[8].length() != 0)
            setCapital(inputs[8].equalsIgnoreCase("primary"));
        return this;
    }

    @Override
    public int compare(City object1, City object2) {
        return object1.Population.compareTo(object2.getPopulation());
    }
}
